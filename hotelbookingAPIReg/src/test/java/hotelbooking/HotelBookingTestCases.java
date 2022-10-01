package hotelbooking;

//import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HotelBookingTestCases {

	@Test(priority = 1)
	public void verifyCreateBooking() {
		System.out.println(
				"-----Test Case 1: User is booking hotel for a customer and details are available in Database------");
		HotelBookingAPIs verifyNewBookingCall = new HotelBookingAPIs();
		verifyNewBookingCall.createBooking();
		verifyNewBookingCall.getBookedDetails();
	}

	@Test(priority = 2)
	public void updateCreatedBooking() {
		System.out.println("-----Test Case 2: User is booking hotel and updating the customer  details.------");
		HotelBookingAPIs verifyUpdateBookingCall = new HotelBookingAPIs();
		verifyUpdateBookingCall.tokenGenration();
		verifyUpdateBookingCall.createBooking();
		verifyUpdateBookingCall.updateBookedDetails();
		verifyUpdateBookingCall.getBookedDetails();
	}

	@Test(priority = 3)
	public void deleteCreatedBooking() {
		System.out.println("-----Test Case 3: User is booking hotel and deleted the booked entry.------");
		HotelBookingAPIs verifyDeleteBookingCall = new HotelBookingAPIs();
		verifyDeleteBookingCall.tokenGenration();
		verifyDeleteBookingCall.createBooking();
		verifyDeleteBookingCall.updateBookedDetails();
		verifyDeleteBookingCall.deleteBookedDetai1s();
		verifyDeleteBookingCall.verifyDeletedBooking();
	}

}
