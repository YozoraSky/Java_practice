package Test;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpPortMethod {
	public static void main(String[] args) {
		String url = "http://localhost:10080/ivr-repo-gateway/config/query";
		String output = "sql=select * from Atrip_Mock_ESB where OID='8'";
		Thread[] arr = new Thread[150];
		for(int i=0;i<150;i++) {
			arr[i] = new Thread() {
				@Override
				public void run() {
					post(url,output);
				}
			};
		}
		for(int i=0;i<150;i++) {
			arr[i].start();
		}
	}
	
	public static void post(String url, String output) {
		try {
			URL endpoint = new URL(url);
			HttpURLConnection httpConnection = (HttpURLConnection) endpoint.openConnection();
			httpConnection.setRequestMethod("POST");
			httpConnection.setDoOutput(true);
			httpConnection.setDoInput(true);
//			httpConnection.setRequestProperty("Content-Type", "application/json");
			httpConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			DataOutputStream outputStream = new DataOutputStream(httpConnection.getOutputStream());
			outputStream.write(output.getBytes("UTF-8"));
			outputStream.flush();
			outputStream.close();
			DataInputStream inputStream = new DataInputStream(httpConnection.getInputStream());
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferReader = new BufferedReader(inputStreamReader);
			String line;
			while((line = bufferReader.readLine())!=null) {
				System.out.println(line);
			}
			bufferReader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
