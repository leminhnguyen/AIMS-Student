import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Test extends Application {

	private class PaymentTask extends Task<String> {
		@Override
		protected String call() throws Exception {
			String res = post(PROCESS_TRANSACTION_URL, POST_DATA);
			System.out.println(res);
			return res;
		}

	}

	@Override
	public void start(Stage primaryStage) {
		PaymentTask copyTask = new PaymentTask();
		ProgressIndicator progressIndicator = new ProgressIndicator();

		progressIndicator.progressProperty().unbind();
		progressIndicator.progressProperty().bind(copyTask.progressProperty());

		copyTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, //
				new EventHandler<WorkerStateEvent>() {
					@Override
					public void handle(WorkerStateEvent t) {
						System.out.println("Finished");
						progressIndicator.setVisible(false);
					}
				});

		FlowPane root = new FlowPane();
		root.setPadding(new Insets(10));
		root.setHgap(10);

		root.getChildren().addAll(progressIndicator);

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();

		new Thread(copyTask).start();

	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	public static final String PROCESS_TRANSACTION_URL = "https://ecopark-system-api.herokuapp.com/api/card/processTransaction";
	public static final String POST_DATA = "{}";

	public static String post(String url, String data
//			, String token
	) throws IOException {
		allowMethods("PATCH");
		URL line_api_url = new URL(url);
		String payload = data;
		System.out.println("Request Info:\nRequest URL: " + url + "\n" + "Payload Data: " + payload + "\n");
		HttpURLConnection conn = (HttpURLConnection) line_api_url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("PATCH");
		conn.setRequestProperty("Content-Type", "application/json");
//		conn.setRequestProperty("Authorization", "Bearer " + token);
		Writer writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		writer.write(payload);
		writer.close();
		BufferedReader in;
		String inputLine;
		if (conn.getResponseCode() / 100 == 2) {
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder response = new StringBuilder();
		while ((inputLine = in.readLine()) != null)
			response.append(inputLine);
		in.close();
		System.out.println("Respone Info: " + response.toString());
		return response.toString();
	}

	private static void allowMethods(String... methods) {
		try {
			Field methodsField = HttpURLConnection.class.getDeclaredField("methods");
			methodsField.setAccessible(true);

			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

			String[] oldMethods = (String[]) methodsField.get(null);
			Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
			methodsSet.addAll(Arrays.asList(methods));
			String[] newMethods = methodsSet.toArray(new String[0]);

			methodsField.set(null/* static field */, newMethods);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

}