package hotelbooking;

import com.github.javafaker.Faker;

public class payloads {

	public static String getTokenPayload() {
		return "{\r\n" + "    \"username\" : \"admin\",\r\n" + "    \"password\" : \"password123\"\r\n" + "}";
	}

	public static String createBookingPayload() {
		Faker faker = new Faker();
		return "{\r\n" + "    \"firstname\" : \"" + faker.name().firstName() + "\",\r\n" + "    \"lastname\" : \""
				+ faker.name().lastName() + "\",\r\n" + "    \"totalprice\" : 333,\r\n"
				+ "    \"depositpaid\" : true,\r\n" + "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2022-07-02\",\r\n" + "        \"checkout\" : \"2022-07-18\"   \r\n"
				+ "    },\r\n" + "    \"additionalneeds\" : \"Breakfast\"\r\n" + "}";
	}

	public static String updateBookingPayload() {
		Faker faker = new Faker();
		return "{ \"firstname\" : \"" + faker.name().firstName() + "\",\r\n" + "    \"lastname\" : \""
				+ faker.name().lastName() + "\",\r\n" + "   \r\n" + "    \"totalprice\" : 9413,\r\n"
				+ "    \"depositpaid\" : false,\r\n" + "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2023-07-01\",\r\n" + "        \"checkout\" : \"2023-07-08\"   \r\n"
				+ "    },\r\n" + "    \"additionalneeds\" : \"Dinner\"\r\n" + "}";
	}

}
