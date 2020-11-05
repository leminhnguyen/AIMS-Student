package subsystem.interbank;

import java.io.IOException;

import entity.exception.InternalServerErrorException;
import utils.API;

public class InterbankBoundary {

	String query(String url, String data) {
		String response = null;
		try {
			System.out.println(url);
			System.out.println(data);
			 response = API.post(url, data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new InternalServerErrorException("ERROR: Internal Server Error!");
		}
		return response;
	}

}
