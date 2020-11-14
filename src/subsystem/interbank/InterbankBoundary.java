package subsystem.interbank;

import common.exception.UnrecognizedException;
import utils.API;

public class InterbankBoundary {

	String query(String url, String data) {
		String response = null;
		try {
//			System.out.println(url);
//			System.out.println(data);
			response = API.post(url, data);
			System.out.println(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new UnrecognizedException();
		}
		return response;
	}

}
