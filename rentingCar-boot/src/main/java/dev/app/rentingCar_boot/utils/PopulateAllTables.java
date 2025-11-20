package dev.app.rentingCar_boot.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PopulateAllTables {

    @Autowired
    private PopulateCar populateCar;

    @Autowired
    private PopulateClient populateClient;

    @Autowired
    private PopulateBooking populateBooking;

    @Autowired
    private PopulateDrivingCourse populateDrivingCourse;

    @Autowired
    private PopulateInssuranceContract populateInssuranceContract;


    @Transactional
    public String populateAllTables(int qty) {
        // Populate cars first
        PopulateStatus populateCarStatus = populateCar.populateCar(qty);
        System.out.println("\nPopulate Car operations: " + populateCarStatus.getQty() +
                " \n" + populateCarStatus.getMessage());

        // Populate clients
        PopulateStatus populateClientStatus = null;
        if (populateCarStatus.isStatus()) {
            populateClientStatus = populateClient.populateClient(qty);
            System.out.println("\nPopulate Client operations: " + populateClientStatus.getQty() +
                    " \n" + populateClientStatus.getMessage());
        } else {
            return "Populate Car operations failed";
        }

        // Populate bookings
        PopulateStatus populateBookingStatus = null;
        if (populateClientStatus.isStatus()) {
            populateBookingStatus = populateBooking.populateBooking(qty);
            System.out.println("\nPopulate Booking operations: " + populateBookingStatus.getQty() +
                    " \n" + populateBookingStatus.getMessage());
        } else {
            return "Populate Client operations failed";
        }

        // Populate driving courses
        PopulateStatus populateDrivingCourseStatus = null;
        if (populateBookingStatus.isStatus()) {
            populateDrivingCourseStatus = populateDrivingCourse.populateDrivingCourse(qty);
            System.out.println("\nPopulate DrivingCourse operations: " + populateDrivingCourseStatus.getQty() +
                    " \n" + populateDrivingCourseStatus.getMessage());

            } else {
                return "Populate Booking operations failed";
            }
        // Populate Inssurance Contracts, he hecho lo mismo que los anteriores
        PopulateStatus populateInssuranceStatus = populateInssuranceContract.populateInssuranceContract(qty);
        System.out.println("\nPopulate InssuranceContract operations: " + populateInssuranceStatus.getQty() +
                " \n" + populateInssuranceStatus.getMessage());

        if (!populateInssuranceStatus.isStatus()) {
            return "Populate InssuranceContract operations failed";
        }

            if (!populateDrivingCourseStatus.isStatus()) {
                return "Populate DrivingCourse operations failed";
            }

            return "Populate All Tables operations completed successfully";






    }

}
