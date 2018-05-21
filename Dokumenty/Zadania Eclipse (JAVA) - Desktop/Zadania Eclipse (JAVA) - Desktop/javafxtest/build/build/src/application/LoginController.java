package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private TextField usernameField;

	@FXML
	private TextField passwordField;

	@FXML
	private Button loginButton;

	@FXML
	void loginAction(ActionEvent event) {
		boolean errorOccured = false;

		try {
			Connection conn = ConnectionFactory.createConnection();

			final String username = usernameField.getText();
			final String password = passwordField.getText();

			final String encryptedPassword = Base64.getEncoder().encodeToString(password.getBytes());

			PreparedStatement cs = conn.prepareStatement("select * from doctor where username = ?");
			cs.setString(1, username);
			ResultSet rs = cs.executeQuery();
			if (rs.next() == false) {
				errorOccured = true;
				Alert alert = new Alert(AlertType.ERROR, "Uzytkownik " + username + " nie istnieje w bazie danych",
						ButtonType.CLOSE);
				alert.show();
			} else {
				final String encryptedRetrievedPassword = rs.getString("password");
				final Integer doctorId = rs.getInt("id");

				if (!encryptedRetrievedPassword.equals(encryptedPassword)) {
					errorOccured = true;
					Alert alert = new Alert(AlertType.ERROR, "Bledne haslo", ButtonType.CLOSE);
					alert.show();
				} else {
					Main.loggedDoctorId = doctorId;
				}
			}

			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
			new Alert(AlertType.ERROR, "Blad: " + e.getMessage(), ButtonType.CLOSE).showAndWait();
		}

		if (!errorOccured) {
			Stage loginStage = (Stage) loginButton.getScene().getWindow();
			loginStage.close();
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("meetings.fxml"));
				Parent root1;
				root1 = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root1));
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
