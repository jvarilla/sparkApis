package Api3.jvarilla.api3;
import static spark.Spark.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
//import org.apache.log4j.BasicConfigurator;

import com.google.gson.*;

public class App {
	private static ArrayList<String> records = new ArrayList<String>();
	public static void main(String[] args) {
		//BasicConfigurator.configure();

		Gson gson = new Gson();
		allowCors();
		//port(9001);
		get("/makedeal/:amount", (request, response) -> {
//			double dealAmount = Double.parseDouble(request.params(":amount"));
			System.out.println(request.body());
			return gson.toJson("Hello");
		});
		
		post("/records", (request, response) -> {
			String record = request.body();
			System.out.println(record);
			records.add(record);
			return gson.toJson("Thanks"); 
		});
		
		get("/records", (request, response) -> {
			return gson.toJson(records);
		});
        
	}
	
	public static String generateCurrentTimestamp() {
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		return ts.toString();
	}
	
	private static void allowCors() {
		options("/*",
		        (request, response) -> {

		            String accessControlRequestHeaders = request
		                    .headers("Access-Control-Request-Headers");
		            if (accessControlRequestHeaders != null) {
		                response.header("Access-Control-Allow-Headers",
		                        accessControlRequestHeaders);
		            }

		            String accessControlRequestMethod = request
		                    .headers("Access-Control-Request-Method");
		            if (accessControlRequestMethod != null) {
		                response.header("Access-Control-Allow-Methods",
		                        accessControlRequestMethod);
		            }

		            return "OK";
		        });

		before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
	}
}