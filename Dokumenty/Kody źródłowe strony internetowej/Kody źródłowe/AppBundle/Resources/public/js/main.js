// var slideIndex = 1;
// showDivs(slideIndex);
//
// function plusDivs(n) {
//     showDivs(slideIndex += n);
// }
//
// function currentDiv(n) {
//     showDivs(slideIndex = n);
// }

// function showDivs(n) {
//     var i;
//     var x = document.getElementsByClassName("mySlides");
//     var dots = document.getElementsByClassName("demo");
//     if (n > x.length) {slideIndex = 1}
//     if (n < 1) {slideIndex = x.length}
//     for (i = 0; i < x.length; i++) {
//         x[i].style.display = "none";
//     }
//     for (i = 0; i < dots.length; i++) {
//         dots[i].className = dots[i].className.replace(" w3-white", "");
//     }
//     x[slideIndex-1].style.display = "block";
//     dots[slideIndex-1].className += " w3-white";
// }

$( ".datepicker-jquery" ).datepicker({
    changeMonth: true,
    changeYear: true,
    regional: "pl",
    dateFormat: 'dd-mm-yy',
    beforeShowDay: noWeekendsOrHolidays
});

function noWeekendsOrHolidays(date) {
    var noWeekend = $.datepicker.noWeekends(date);
    if (noWeekend[0]) {
        return nationalDays(date);
    } else {
        return noWeekend;
    }
}

natDays = [
    [1, 1, 'au'], [5, 1, 'nz'], [5, 3, 'ie'],
    [8, 15, 'za'], [11, 11, 'ar'], [12, 25, 'se'],
    [12, 26, 'us'], [11, 1, 'id']
];

function nationalDays(date) {
    for (i = 0; i < natDays.length; i++) {
        if (date.getMonth() == natDays[i][0] - 1
            && date.getDate() == natDays[i][1]) {
            return [false, natDays[i][2] + '_day'];
        }
    }
    return [true, ''];
}

$(document).ready(function() {

    // page is now ready, initialize the calendar...

    $('#calendar').fullCalendar({
        locale: 'pl'
    });

    $('.slider').bxSlider();

    $('.hour-visit label').click(function(){
        if(!$(this).hasClass('active') && !$(this).hasClass('occupate')){
            $('.hour-visit label').removeClass('active');
            $('.hour-visit label input').prop('checked', false);
            $(this).addClass('active');
            $(this).find('input').prop('checked', true);
        }
        return false;
    });
    $('.show-hide').click(function(){
       $('.hide-on-click').toggle();
    });
});


var initMap = function() {
    var uluru = {lat: 53.183913, lng: 22.05254};
    var map = new google.maps.Map(document.getElementById('map-place'), {
        zoom: 16,
        center: uluru
    });
    var marker = new google.maps.Marker({
        position: uluru,
        map: map
    });
};