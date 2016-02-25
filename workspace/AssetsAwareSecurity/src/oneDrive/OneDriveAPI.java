/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oneDrive;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * The One Drive API
 * 
 * @author Marios Zinonos
 */
public class OneDriveAPI {
    static String CLIENT_ID = "0000000040170501";	//get them from file???
    static String CLIENT_SECRET = "LeY9w0N7aiMnA15lFvOuTlgbtRoksfeR";
    static String AUTH_CODE = ""; // to be stored later
    
    static String GET = "GET";
    static String DELETE = "DELETE";
    static String PUT = "PUT";
    
    static String AUTH_TOKEN_URL =""; 
    static String ACCESS_TOKEN = "";
    static String REFRESH_TOKEN = "";
    
    static String API_URL = "https://apis.live.net/v5.0/";
    static String NewPath = "https://apis.live.net/v5.0/me/skydrive/files/";
    static String DRIVE_PATH = "https://api.onedrive.com/v1.0/drive/root:/";
    static String AUTH_PATH = "https://login.live.com/oauth20_authorize.srf?client_id="+CLIENT_ID+"&scope=wl.signin%20wl.basic%20wl.offline_access%20wl.skydrive_update&response_type=code&redirect_uri=https://login.live.com/oauth20_desktop.srf";
    static String GET_REFRESH_PATH;
    static boolean getSize = false;
    
    private static Client client;

    public static String UPLOAD_PATH;
    static String FILE_PATH = "img.jpg";

	public static void createNewLogin(String url) throws IOException, ProtocolException, MalformedURLException {
		String accessURL;
		String jsonURL;
		String[] tokens;
		accessURL = url;
      
		AUTH_CODE = getCodefromURL(accessURL);
      
		//Build the Auth_token path
		AUTH_TOKEN_URL = "https://login.live.com/oauth20_token.srf?client_id="+CLIENT_ID+"&client_secret="+CLIENT_SECRET+"&code="+AUTH_CODE+"&grant_type=authorization_code&redirect_uri=https://login.live.com/oauth20_desktop.srf";
		
		// Get the tockens in json format
		jsonURL = connectWithREST(AUTH_TOKEN_URL,GET);

		try {
		    tokens = jsonParse(jsonURL);
		    ACCESS_TOKEN = tokens[0];
		    REFRESH_TOKEN = tokens[1];
		    //Save Refresh token to the file
		    createNewTokenFile(REFRESH_TOKEN);
		} catch (JSONException ex) {
		    Logger.getLogger(OneDriveAPI.class.getName()).log(Level.SEVERE, null, ex);
		    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}
	}

    public static void initialiseClient() {
        //initalise client
        DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        defaultClientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        client = Client.create(defaultClientConfig);
    }
    
    public void  login(String fileName) throws IOException{
    	File f = new File(fileName);
    	String fileToken;
        // Authentication loaded
        BufferedReader br = new BufferedReader(new FileReader(f));
        fileToken = br.readLine(); // do refresh token
        refreshTokens(fileToken);
    	
    }
    
        public static String connectWithREST(String url, String method) throws IOException, ProtocolException {
        String newURL="";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // Connect with a REST Method: GET, DELETE, PUT
        con.setRequestMethod(method);
        //add request header
        con.setReadTimeout(20000);
        con.setConnectTimeout(20000);
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        if (method.equals(DELETE) || method.equals(PUT) || getSize)
            con.addRequestProperty("Authorization","Bearer "+ ACCESS_TOKEN);
        
        int responseCode = con.getResponseCode();
        // Read response
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
		}
        in.close();
        newURL = response.toString();

        return newURL;
    }

    private static String getCodefromURL(String accessURL) {
        String code = "";
        
        code = accessURL.substring(accessURL.indexOf("=") + 1, accessURL.indexOf("&"));
        return code;
    }

    private static String[] jsonParse(String jsonURL) throws JSONException {
        String [] tokens = new String[2];
        JSONObject obj = new JSONObject(jsonURL);
        tokens[0] = (String) obj.get("access_token");
        tokens[1] = (String) obj.get("refresh_token");
        
        return tokens;
    }
    
    public static void refreshTokens(String refToken) throws IOException{
        String jsonURL;
        String[] tokens = new String[2];
        
        REFRESH_TOKEN = refToken;
        initRefreshURL();
        jsonURL = connectWithREST(GET_REFRESH_PATH,GET);
        try {
            tokens = jsonParse(jsonURL);
            ACCESS_TOKEN = tokens[0];
            REFRESH_TOKEN = tokens[1];
            } catch (JSONException ex) {
            Logger.getLogger(OneDriveAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void initRefreshURL(){
        GET_REFRESH_PATH = "https://login.live.com/oauth20_token.srf?client_id="+CLIENT_ID+"&refresh_token="+REFRESH_TOKEN+"&grant_type=refresh_token&redirect_uri=https://login.live.com/oauth20_desktop.srf";                            
    
    }
        public static void createNewTokenFile(String accessToken){
        BufferedWriter output = null;
        try {
            File file = new File("SPsCredentials/OneDriveLogin.txt");
            output = new BufferedWriter(new FileWriter(file));
            output.write(accessToken);
            output.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
        
        public static double getOneDriveSize() throws IOException, JSONException {
            getSize = true;
            String drive = "https://api.onedrive.com/v1.0/drive/";
            String response = connectWithREST(drive, GET);

            //Get size
            JSONObject object = new JSONObject(response);
            JSONObject quota = object.getJSONObject("quota");
            double size = quota.getLong("remaining");
            
            return (size / 1024 /1024);
            
        }

    public static void deleteFile(String path) throws IOException {
        String deleteURL;
        
        deleteURL = DRIVE_PATH+path;
        connectWithREST(deleteURL,DELETE);
    }
    public String getURL(){
    	return AUTH_PATH;
    }
  
    
 public static void uploadFile(String filePath){
   URLConnection urlconnection=null;
   try{
    File file = new File(UPLOAD_PATH+filePath);
    URL url = new URL(DRIVE_PATH + file.getName() +":/content");
    urlconnection = url.openConnection();
    urlconnection.setDoOutput(true);
    urlconnection.setDoInput(true);

    if (urlconnection instanceof HttpURLConnection) {
        try {
            ((HttpURLConnection)urlconnection).setRequestMethod("PUT");
            ((HttpURLConnection)urlconnection).setRequestProperty("Content-type", "application/octet-stream");
            ((HttpURLConnection)urlconnection).addRequestProperty("Authorization", "Bearer "+ACCESS_TOKEN);
            ((HttpURLConnection)urlconnection).connect();


        } catch (ProtocolException e) {
            e.printStackTrace();
        }
   }


    BufferedOutputStream bos = new BufferedOutputStream(urlconnection.getOutputStream());
    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
    int i;
    // read byte by byte until end of stream
    while ((i = bis.read()) >= 0) {
        bos.write(i);
    }
    bos.flush();
    bos.close();
    }
  catch(Exception e)
  {
   e.printStackTrace();
  }
 
    }
}
