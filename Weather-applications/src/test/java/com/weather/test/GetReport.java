package com.weather.test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class GetReport
{
	public void validateWeatherInfo()
	{
		Response response = (Response) given().header("Accept-Encoding", "application/json").when()
				.get("http://api.openweathermap.org/data/2.5/weather?zip=75024,us&appid=caed8708f6879aba819ba1b75e2ad390");

		// validation step
		response.then().statusCode(200).contentType(ContentType.JSON).body("name", equalTo("Frisco")).body("sys.country", equalTo("US"));

		// System.out.println(response.prettyPrint());

		// writing to file
		String fileName = "C:\\Users\\priya\\Desktop\\weatherData.txt";
		PrintWriter writer = null;
		try
		{
			writer = new PrintWriter(fileName, "UTF-8");
			writer.println(response.prettyPrint());
			writer.close();
		}
		catch (FileNotFoundException e)
		{
		}
		catch (UnsupportedEncodingException e)
		{
		}
	}
}