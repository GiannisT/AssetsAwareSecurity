package cloudMe;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HttpContext;

public class Test {
	final private static String host = "www.cloudme.com";
	final private static String USER_AGENT_INFO = "Nothing special";
	final private static String SOAP_HEADER ="<SOAP-ENV:Envelope xmlns:SOAPENV=\"http://schemas.xmlsoap.org/soap/envelope/\"SOAP-ENV:encodingStyle=\"\" xmlns:xsi=\"http://www.w3.org/1999/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/1999/XMLSchema\"><SOAP-ENV:Body>";
	final private static String SOAP_FOOTER = "</SOAP-ENV:Body></SOAP-ENV:Envelope>";
	private static AuthScheme scheme = null;
	private static Credentials creds = null;
	private String username = "cloud.sec";
	private String password = "letmein";

	public Test() {
		// We call the login call on CloudMe
		String action = "login";
		String body = "";
		String something = callServerStringResponse(action, body);
		System.out.println(something);
	}

	// Creates the connection to the server
	private DefaultHttpClient getConnection() {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpProtocolParams.setUseExpectContinue(httpClient.getParams(), false);
		HttpProtocolParams.setUserAgent(httpClient.getParams(), USER_AGENT_INFO);
		httpClient.getCredentialsProvider().setCredentials(
				new AuthScope(host, 80, "os@xcerion.com", "Digest"),
				new UsernamePasswordCredentials(username, password)); // Encrypts password & username
		HttpRequestInterceptor preemptiveAuth = new HttpRequestInterceptor() {
			public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
				AuthState authState = (AuthState) context.getAttribute(ClientContext.TARGET_AUTH_STATE);
				if ( authState.getAuthScheme() == null ) {
					if ( creds != null && scheme != null ) {
						authState.setAuthScheme(scheme);
						authState.setCredentials(creds);
					} else {
						scheme = authState.getAuthScheme();
					}
				}
			}
		};
		httpClient.addRequestInterceptor(preemptiveAuth,0);
		return httpClient;

	}

	// Converts the HTTPResponse into a huge string
	private String getResponse(HttpResponse response) {
		try {
			DataInputStream stream = new DataInputStream(response.getEntity().getContent());
			StringBuffer buf = new StringBuffer();
			String tmp;
			while ((tmp = stream.readLine()) != null) {
				buf.append(tmp);
			}
			stream.close();
			return buf.toString();
		} catch (IOException e) {
			return "IOException: " + e.getMessage();
		}
	}

	// Does the actuall SOAP-call to the server, returns the response as a
	// string for printing

	private synchronized String callServerStringResponse(String soapAction, String body) {
		DefaultHttpClient httpClient = getConnection();
		byte[] b = null;
		HttpPost httpPost = new HttpPost("http://" + host + "/v1/");
		httpPost.setHeader("soapaction", soapAction);
		httpPost.setHeader("Content-Type", "text/xml; charset=utf-8");
		final StringBuffer soap = new StringBuffer();
		soap.append(SOAP_HEADER);
		soap.append("<" + soapAction + ">");
		soap.append(body);
		soap.append("</" + soapAction + ">");
		soap.append(SOAP_FOOTER);
		System.out.println("\n\n" + soap.toString() + "\n\n"); // Prints the call so that the SOAP-call can be checked manually for errors
		try {
			HttpEntity entity = new StringEntity(soap.toString());
			httpPost.setEntity(entity);
			HttpResponse response = httpClient.execute(httpPost);
			// Call to getResponse needs to be done before httpClient is shut down
			// otherwise the response disappears and null is returned
			String stringResponse = getResponse(response);
			httpClient.getConnectionManager().shutdown();
			return stringResponse;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		httpClient.getConnectionManager().shutdown();
		return null;
		//33799  -- 3221225472 -- 10574767
	}

	/*
	 * All of these REST calls return string for readability, hence no parsing
	 * is done
	 */
	// Used for PUT REST calls
	private synchronized String putServerREST(String putCall, String xmlString, String type) {
		DefaultHttpClient httpClient = getConnection();
		HttpPut httpPut = new HttpPut(putCall);
		try {
			StringEntity entity = new StringEntity(xmlString, "UTF-8");
			entity.setContentType("application/xml");
			httpPut.setEntity(entity);
			System.out.println("\n\n" + httpPut.getRequestLine().toString() + "\n\n");
			HttpResponse response = httpClient.execute(httpPut);
			// Call to getResponse needs to be done before httpClient is shut
			// down
			// otherwise the response disapears and null is returned
			String stringResponse = getResponse(response);
			httpClient.getConnectionManager().shutdown();
			return stringResponse;
		} catch (Exception e) {
			System.out.println("excep");
			System.out.println(e.getMessage());
		}
		httpClient.getConnectionManager().shutdown();
		return null;
	}

	// Used for DELETE REST calls
	private synchronized String deleteServerREST(String deleteCall) {
		DefaultHttpClient httpClient = getConnection();
		HttpDelete httpDelete = new HttpDelete(deleteCall);
		try {
			System.out.println("\n\n" + httpDelete.getRequestLine().toString() + "\n\n");
			HttpResponse response = httpClient.execute(httpDelete);
			// Call to getResponse needs to be done before httpClient is shut
			// down
			// otherwise the response disapears and null is returned
			String stringResponse = getResponse(response);
			httpClient.getConnectionManager().shutdown();
			return stringResponse;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		httpClient.getConnectionManager().shutdown();
		return null;
	}
	// Used for POST REST calls
	// Not complet since e.g. uploading a file would need the body to contain
	// the file, not an xml string

	private synchronized String postServerREST(String postCall, String xmlString) {
		DefaultHttpClient httpClient = getConnection();
		HttpPost httpPost = new HttpPost(postCall);
		try {
			// StringEntity entity = new StringEntity(xmlString, "UTF-8");
			// entity.setContentType("text/plain");
			FileBody file = new FileBody(new File("bird.jpg"));
			StringBody comment = new StringBody("A nice bird");
			MultipartEntity reqEntity = new MultipartEntity();
			reqEntity.addPart("bin", file);
			reqEntity.addPart("comment", comment);
			httpPost.setEntity(reqEntity);
			System.out.println("\n\n" + httpPost.getRequestLine().toString() + "\n\n");
			HttpResponse response = httpClient.execute(httpPost);
			// Call to getResponse needs to be done before httpClient is shut
			// down
			// otherwise the response disapears and null is returned
			String stringResponse = getResponse(response);
			httpClient.getConnectionManager().shutdown();
			return stringResponse;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		httpClient.getConnectionManager().shutdown();
		return null;
	}

	private synchronized String postServerREST2(String postCall, String xmlString) {
		DefaultHttpClient httpClient = getConnection();
		HttpPost httpPost = new HttpPost(postCall);
		try {
			StringEntity entity = new StringEntity(xmlString, "UTF-8");
			entity.setContentType("text/plain");
			// FileBody file = new FileBody(new File("bird.jpg"));
			// StringBody comment = new StringBody("A nice bird");
			// MultipartEntity reqEntity = new MultipartEntity();
			// reqEntity.addPart("bin", file);
			// reqEntity.addPart("comment", comment);
			httpPost.setEntity(entity);
			System.out.println("\n\n" + httpPost.getRequestLine().toString() + "\n\n");
			HttpResponse response = httpClient.execute(httpPost);
			// Call to getResponse needs to be done before httpClient is shut
			// down
			// otherwise the response disapears and null is returned
			String stringResponse = getResponse(response);
			httpClient.getConnectionManager().shutdown();
			return stringResponse;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		httpClient.getConnectionManager().shutdown();
		return null;
	}

	// Used for GET REST calls
	private synchronized String getServerREST(String getCall) {
		DefaultHttpClient httpClient = getConnection();
		HttpGet httpGet = new HttpGet(getCall);
		try {
			System.out.println("\n\n" + httpGet.getRequestLine().toString() + "\n\n");
			HttpResponse response = httpClient.execute(httpGet);
			// Call to getResponse needs to be done before httpClient is shut
			// down
			// otherwise the response disapears and null is returned
			String stringResponse = getResponse(response);
			httpClient.getConnectionManager().shutdown();
			return stringResponse;
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		httpClient.getConnectionManager().shutdown();
		return null;
	}
	
	public static void main(String[] args){
		Test test = new Test();
	}
}