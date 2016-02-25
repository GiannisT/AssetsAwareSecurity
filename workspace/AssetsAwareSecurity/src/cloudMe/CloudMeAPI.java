/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudMe;
import Cloudme.*;
import jdk.internal.org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileDeleteStrategy;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HttpContext;

/**
 * The Cloud Me API
 * 
 * @author Marios Zinonos
 */
public class CloudMeAPI {
    static String userName, pass;
    CloudmeUser user;
	final private static String host = "www.cloudme.com";
	final private static String USER_AGENT_INFO = "Nothing special";
	final private static String SOAP_HEADER ="<SOAP-ENV:Envelope xmlns:SOAPENV=\"http://schemas.xmlsoap.org/soap/envelope/\"SOAP-ENV:encodingStyle=\"\" xmlns:xsi=\"http://www.w3.org/1999/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/1999/XMLSchema\"><SOAP-ENV:Body>";
	final private static String SOAP_FOOTER = "</SOAP-ENV:Body></SOAP-ENV:Envelope>";
	private static AuthScheme scheme = null;
	private static Credentials creds = null;
    public static String UPLOAD_PATH;
    

    
    public void closeConnection(CloudmeUser user){
    	user.killUser();
    }

    public CloudmeUser login(String loginPath) throws IOException, CloudmeException {
        String[] credentials;
        File f = new File(loginPath);
        if(f.exists() && !f.isDirectory()) {
            // Authentication loaded
            BufferedReader br = new BufferedReader(new FileReader(f));
            credentials = br.readLine().split(",");
            userName = credentials[0];
            pass = credentials[1];
            user = new CloudmeUser(userName,pass);
            
        }
        return user;
    }
    
    private synchronized String callServerStringResponse() {
    	String soapAction = "login";
    	String body = "";
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
	}
    
	private double getXMLvalue(String xml) throws ParserConfigurationException, SAXException, IOException {
		double freeSpace;
		DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = newDocumentBuilder.parse(new ByteArrayInputStream(xml.getBytes()));
		
		String usedSize = doc.getElementsByTagName("currentSize").item(0).getTextContent();
		String maxSize = doc.getElementsByTagName("quotaLimit").item(0).getTextContent();
		
		freeSpace = Double.valueOf(maxSize) - Double.valueOf(usedSize);
		
		return freeSpace;
	}

	// Creates the connection to the server
	private DefaultHttpClient getConnection() {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpProtocolParams.setUseExpectContinue(httpClient.getParams(), false);
		HttpProtocolParams.setUserAgent(httpClient.getParams(), USER_AGENT_INFO);
		httpClient.getCredentialsProvider().setCredentials(
				new AuthScope(host, 80, "os@xcerion.com", "Digest"),
				new UsernamePasswordCredentials(userName, pass)); // Encrypts password & username
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
    
    public CloudmeUser createNewLogin(String credentials) throws CloudmeException{
      String [] parts = credentials.split(",");
      userName = parts[0];
      pass = parts[1];
      
      createNewLoginFile(userName,pass);
      user = new CloudmeUser(userName,pass);
      return user;
    }
    
    public void uploadFile( String fileName) throws CloudmeException { //CloudmeUser user,
    	 user.getFileManager().uploadFile(UPLOAD_PATH+fileName,"/");
        
    }
    
    //Not available through API
    public double getCloudMeSize()throws CloudmeException, ParserConfigurationException, SAXException, IOException{
    	String xml ="";
    	double storage;
    	
    	xml= callServerStringResponse();

    	storage = getXMLvalue(xml);
    	storage = storage/1024/1024; //MB
		return storage;
    }

    public void deleteFile( String fileName) {
        try {
            CloudmeFile file;
            file = user.getFileManager().getFile("D:/"+fileName);
            file.deleteFile();
        } catch (CloudmeException ex) {
            System.out.println("Error ocucred: " + ex.getMessage());
        }
        
    }
    
    public void createNewLoginFile(String userName, String pass){
        String line = "";
        BufferedWriter output = null;
        try {
            File file = new File("SPsCredentials/CloudMeLogin.txt"); // replace with aforementioned loginPath
            output = new BufferedWriter(new FileWriter(file));
            line = (userName + "," +pass);
            output.write(line);
            output.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
    
}
