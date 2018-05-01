package application;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class MeetingsController {

	@FXML
	private TableView<Meeting> meetingsTable;

	@FXML
	private TableColumn<Meeting, String> meetingIdColumn;

	@FXML
	private TableColumn<Meeting, String> meetingDateColumn;

	@FXML
	private TableColumn<Meeting, String> userColumn;

    @FXML
    private TextArea userComments;

	@FXML
	private TextArea meetingsResult;

	@FXML
	private Button saveButton;

	@FXML
	void initialize() {
        assert meetingsTable != null : "fx:id=\"meetingsTable\" was not injected: check your FXML file 'meetings.fxml'.";
        assert meetingIdColumn != null : "fx:id=\"meetingIdColumn\" was not injected: check your FXML file 'meetings.fxml'.";
        assert meetingDateColumn != null : "fx:id=\"meetingDateColumn\" was not injected: check your FXML file 'meetings.fxml'.";
        assert userColumn != null : "fx:id=\"userColumn\" was not injected: check your FXML file 'meetings.fxml'.";
        assert userComments != null : "fx:id=\"userComments\" was not injected: check your FXML file 'meetings.fxml'.";
        assert meetingsResult != null : "fx:id=\"meetingsResult\" was not injected: check your FXML file 'meetings.fxml'.";
        assert saveButton != null : "fx:id=\"saveButton\" was not injected: check your FXML file 'meetings.fxml'.";

		final ObservableList<Meeting> data = FXCollections.observableArrayList();

		meetingsTable.setItems(data);

		meetingIdColumn.setCellValueFactory(new PropertyValueFactory<Meeting, String>("meetingId"));
		meetingDateColumn.setCellValueFactory(new PropertyValueFactory<Meeting, String>("startDate"));
		userColumn.setCellValueFactory(new PropertyValueFactory<Meeting, String>("user"));

		try {
			Connection conn = ConnectionFactory.createConnection();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			final String currentDate = sdf.format(new Date());

			PreparedStatement cs = conn.prepareStatement(
					"select m.id, m.startDate, concat(u.username_canonical, ' ', u.pesel) as username "
					+ "from meeting m inner join user u on (m.user_id = u.id) "
					+ "where m.doctor_id = ? and DATE_FORMAT(m.startDate, '%Y-%m-%d') = ?");
			cs.setInt(1, Main.loggedDoctorId);
			cs.setString(2, currentDate);
			
			ResultSet rs = cs.executeQuery();

			while (rs.next()) {
				data.add(new Meeting(rs.getInt("id"), sdf.format(rs.getDate("startDate")), rs.getString("username")));
			}

			conn.close();

			meetingsTable.refresh();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
			new Alert(AlertType.ERROR, "Blad: " + e.getMessage(), ButtonType.CLOSE).showAndWait();
		}
		
		meetingsTable.setOnMouseClicked(e -> {
			final Meeting selectedMeeting = meetingsTable.getSelectionModel().getSelectedItem();

			try {
				Connection conn = ConnectionFactory.createConnection();

				CallableStatement cs = conn
						.prepareCall("select meetingResult, details from meeting where id = " + selectedMeeting.getMeetingId());
				ResultSet rs = cs.executeQuery();
				rs.next();

				meetingsResult.setText(rs.getString("meetingResult"));
				userComments.setText(rs.getString("details"));

				conn.close();
			} catch (Exception ex) {
				System.err.println("Got an exception! ");
				System.err.println(ex.getMessage());
				new Alert(AlertType.ERROR, "Blad: " + ex.getMessage(), ButtonType.CLOSE).showAndWait();
			}
		});
	}

	@FXML
	void saveButtonAction(ActionEvent event) {
		final Meeting selectedMeeting = meetingsTable.getSelectionModel().getSelectedItem();

		try {
			Connection conn = ConnectionFactory.createConnection();

			PreparedStatement cs = conn
					.prepareStatement("UPDATE meeting SET meetingResult=? WHERE id=?");
			cs.setString(1, meetingsResult.getText());
			cs.setInt(2, selectedMeeting.getMeetingId());
			
			cs.execute();

			conn.close();

			new Alert(AlertType.INFORMATION, "Pomyslnie zapisano w bazie danych", ButtonType.OK).showAndWait();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
			new Alert(AlertType.ERROR, "Blad: " + e.getMessage(), ButtonType.CLOSE).showAndWait();
		}
	}

	public static class Meeting {

		private final int meetingId;
		private final String startDate;
		private final String user;

		public Meeting(int meetingId, String startDate, String user) {
			super();
			this.meetingId = meetingId;
			this.startDate = startDate;
			this.user = user;
		}

		public int getMeetingId() {
			return meetingId;
		}

		public String getStartDate() {
			return startDate;
		}

		public String getUser() {
			return user;
		}
	}

}
