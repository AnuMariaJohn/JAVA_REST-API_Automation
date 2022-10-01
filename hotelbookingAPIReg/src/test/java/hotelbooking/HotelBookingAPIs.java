package hotelbooking;

import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HotelBookingAPIs {
	String BASE_URI = "https://restful-booker.herokuapp.com";
	int bookingId;
	String token;

	// End point setting
	public RequestSpecification setup() {
		RestAssured.baseURI = BASE_URI;
		RequestSpecification request = RestAssured.given();
		return request;
	}

	public void tokenGenration() {
		try {
			HotelBookingAPIs req = new HotelBookingAPIs();
			RequestSpecification request = req.setup();
			Response tokenResponse = request.header("Content-Type", "application/json").body(payloads.getTokenPayload())
					.when().post("/auth");
			token = tokenResponse.jsonPath().getString("token");
			System.out.println("****Token Generated Successfully and value is: " + token);
			Assert.assertTrue(tokenResponse.getStatusCode() == 200);
		} catch (Exception e) {
			System.out.println("Error occurred while generating token and exception occurred is : " + e);
		}

	}

	public void createBooking() {
		try {
			HotelBookingAPIs req = new HotelBookingAPIs();
			RequestSpecification request = req.setup();
			Response createBookingResponse = request.header("Content-Type", "application/json")
					.body(payloads.createBookingPayload()).when().post("/booking");
			String jsonStringBookingId = createBookingResponse.asString();
			bookingId = JsonPath.from(jsonStringBookingId).get("bookingid");
			System.out.println("****CREATE: Booking created and Booking ID is : " + bookingId);
			System.out.println("****API call's expected response status is 200.");
			System.out.println("****API call's Actual response status is : " + createBookingResponse.getStatusCode());
			Assert.assertTrue(createBookingResponse.getStatusCode() == 200);

		} catch (Exception e) {
			System.out.println("Error occurred while Booking and exception occurred is : " + e);
		}

	}

	public void getBookedDetails() {
		try {
			HotelBookingAPIs req = new HotelBookingAPIs();
			RequestSpecification request = req.setup();
			Response getBookingDetailsResponse = request.header("Content-Type", "application/json").when()
					.get("/booking/" + bookingId);
			System.out.println(
					"****GET: Booked details of newly created bookingID-" + bookingId + " is retrieved successfully.");
			System.out.println("****API call's expected response status is 200. ");
			System.out
					.println("****API call's Actual response status is : " + getBookingDetailsResponse.getStatusCode());
			Assert.assertTrue(getBookingDetailsResponse.statusCode() == 200);
		} catch (Exception e) {
			System.out.println("Error occurred while retriving booked details and exception occurred is : " + e);
		}

	}

	public void updateBookedDetails() {
		try {
			HotelBookingAPIs req = new HotelBookingAPIs();
			RequestSpecification request = req.setup();
			Response updateBookingResponse = request.header("Content-Type", "application/json")
					.header("Cookie", "token=" + token).body(payloads.updateBookingPayload()).when()
					.put("/booking/" + bookingId);
			System.out.println("****UPDATE: Updated details of newly created booking with BookingID: " + bookingId);
			System.out.println("****API call's expected response status is 200 ");
			System.out.println("****API call's Actual response status is : " + updateBookingResponse.getStatusCode());
			Assert.assertTrue(updateBookingResponse.statusCode() == 200);
		} catch (Exception e) {
			System.out.println("Error occurred while updating booked details and exception occurred is : " + e);
		}

	}

	public void deleteBookedDetai1s() {
		try {
			HotelBookingAPIs req = new HotelBookingAPIs();
			RequestSpecification request = req.setup();
			Response deleteBookingResponse = request.header("Cookie", "token=" + token)
					.header("Content-Type", "application/json").when().delete("/booking/" + bookingId);
			System.out.println("****DELETE: Deleted the newly created booking details of - " + bookingId);
			System.out.println("****API call's expected response status is 201 ");
			System.out.println("****API call's Actual response status is : " + deleteBookingResponse.getStatusCode());
			Assert.assertTrue(deleteBookingResponse.statusCode() == 201);
		} catch (Exception e) {
			System.out.println("Error occurred while deleting booked details and exception occurred is : " + e);
		}

	}

	public void verifyDeletedBooking() {
		try {
			RestAssured.baseURI = BASE_URI;
			RequestSpecification request = RestAssured.given().header("Content-Type", "application/json");
			Response getUpdatedBookingResponse = request.when().get("/booking/" + bookingId);
			System.out.println("****GET THE DELETED: Deleted entry of booking ID-" + bookingId
					+ " is not available in Database!!");
			System.out.println("****API call's expected response status is 404 ");
			System.out
					.println("****API call's Actual response status is : " + getUpdatedBookingResponse.getStatusCode());
			Assert.assertTrue(getUpdatedBookingResponse.statusCode() == 404);
		} catch (Exception e) {
			System.out.println("Booking details are not deleted and exception occurred is : " + e);
		}

	}

}
