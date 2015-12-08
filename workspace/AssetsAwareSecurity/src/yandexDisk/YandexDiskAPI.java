/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yandexDisk;

/**
 *
 * @author methis
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.vadel.yandexdisk.authorization.Authorization;
import org.vadel.yandexdisk.authorization.BasicAuthorization;
import org.vadel.yandexdisk.authorization.OAuthAuthorization;
import org.vadel.yandexdisk.authorization.WebDavFile;
import org.vadel.yandexdisk.authorization.WebDavRequest;

public class YandexDiskAPI {
	
	public static boolean DEBUG = false;
	
    //static String cliendID = "ef6dc79d9f8a448f821acf7a95789f41";
    protected static String user = "cloud.sec.aware.test@yandex.com"; // need to read them from file
    protected static String password = "!Y4ndex235";
    public String UPLOAD_PATH;
	
	private static final String PARSE_TOKEN = "#access_token=";
	
	public static final String BASE_URI = "https://webdav.yandex.ru";
	
	public static final String BASE_URL = "https://cloud-api.yandex.net/v1/disk/";
	
	public static final String GET_UPLOAD_URL = "https://cloud-api.yandex.net/v1/disk/resources/upload?path=";
        
	public static final String GET_DOWNLOAD_URL = "https://cloud-api.yandex.net/v1/disk/resources/download?path=";
	
        public static final String GET_DELETE_URL= "https://cloud-api.yandex.net/v1/disk/resources?path=";
	
        protected static final String BASE_OAUTH_AUTHORIZE_URL = 
		"https://oauth.yandex.ru/authorize?response_type=token&client_id=";
	
	static final String PATH_USER_LOGIN = "/?userinfo";
	
	static final String PUT       = "PUT"; 
	static final String GET       = "GET";
	static final String MKCOL     = "MKCOL";
	static final String COPY      = "COPY";
	static final String MOVE      = "MOVE";
	static final String DELETE    = "DELETE";
	static final String PROPFIND  = "PROPFIND";
	static final String PROPPATCH = "PROPPATCH";
	
	static boolean getSize = false;
	
	static int BUFFER = 1024;
	
	public static final int ONE_MB = 1024*1024;
	public static final int TEN_MB = 10*ONE_MB;
	public static final int FIFTY_MB = 50*ONE_MB;
	
	private long chunkSize = TEN_MB;
	
	protected final String clientId;

	protected static Authorization auth;
	
	public YandexDiskAPI() {
		this.clientId = "ef6dc79d9f8a448f821acf7a95789f41";
	}
	
	public String getCredentialsLogin() {
		if (auth instanceof BasicAuthorization)
			return ((BasicAuthorization) auth).login;
		else
			return null;
	}
	
	public static void createNewLogin(String authURL) throws IOException {
		String accessToken;
		System.out.println("Den iparxooo");
		accessToken = authURL;
		System.out.println(accessToken);
    //------------------------------
		setToken(accessToken);
		
		// Function to write a new txt file with token
		createNewTokenFile(accessToken);
	}
	
	public static void login(String loginPath) throws FileNotFoundException, IOException { //Change to string
		File f = new File(loginPath);
		String accessToken;
		// Authentication loaded
		System.out.println("iparxooooo");
		BufferedReader br = new BufferedReader(new FileReader(f));
		accessToken = br.readLine();
		setToken(accessToken);
	}

	public String getCredentialsPassword() {
		if (auth instanceof BasicAuthorization)
			return ((BasicAuthorization) auth).pass;
		else
			return null;
	}
	
	public void setCredentials() {
		auth = new BasicAuthorization(user, password);
	}
	
	public String getOAthRequestUrl() {
		return BASE_OAUTH_AUTHORIZE_URL + clientId;
	}
	
	public String getToken() {
		if (auth instanceof OAuthAuthorization)
			return ((OAuthAuthorization) auth).token;
		else
			return null;
	}
	
	public static void setToken(String value) {
		auth = new OAuthAuthorization(value);
	}
	
	/**
	 * Response example: http://aaaaa.aaaaa.com/callback#access_token=00a11b22c3333c44a7e7d6db623bd5e0&token_type=bearer&state=
	 * @param value - Response uri
	 */
	public void setTokenFromCallBackURI(String uri) {
		int i1 = uri.indexOf(PARSE_TOKEN);
		if (i1 < 0)
			return;
		i1 += PARSE_TOKEN.length();
		int i2 = uri.indexOf("&", i1);
		if (i2 < 0)
			return;
		setToken(uri.substring(i1, i2));
	}

	public boolean isAuthorization() {
		return auth != null && auth.isValid();
	}
	
	public String getUserLogin() {
		if (auth instanceof BasicAuthorization) {
			return ((BasicAuthorization) auth).login;
		} else if (auth instanceof OAuthAuthorization) {
			return getUserLogin(getAuthorization());
		} 
		return null;
	}
	
	public boolean createFolder(String path) {
		return executeWithoutResult(path, MKCOL);
	}
	
	public boolean delete(String path) {
		return executeWithoutResult(path, DELETE);
	}

	public boolean copy(String src, String dst) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("Destination", dst);
		return executeWithoutResult(src, COPY);
	}

	private boolean move(String src, String dst) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("Destination", dst);
		return executeWithoutResult(src, MOVE);
	}

	private boolean uploadFile(String path, InputStream dataStream, long fileLength) {
		InputStreamEntity entity = new InputStreamEntity(dataStream, fileLength);
		return executeWithoutResult(path, PUT, null, entity);
	}

	private boolean uploadFile(String path, String content) throws UnsupportedEncodingException {
		StringEntity entity = new StringEntity(content);
		return executeWithoutResult(path, PUT, null, entity);
	}

	public synchronized ArrayList<WebDavFile> getFiles(String path) {
		return getFiles(getAuthorization(), path);		
	}
	
	public long getChunkSize() {
		return chunkSize;
	}
	
	public void setChunkSize(long value) {
		if (value <= 0)
			return;
		chunkSize = value;
	}
	
	public InputStream getFileInputStream(String path, long start) {
		return getFileInputStream(getAuthorization(), path, start);
	}
	
	public synchronized long downloadFile(String path, FileOutputStream fos, long start,
			OnLoadProgressListener listener) {
		HashMap<String, String> params = null;
		if (start > 0) {
			params = new HashMap<String, String>();
			params.put("Range", "bytes=" + String.valueOf(start) + "-");
		}
		InputStream in = null;
		long lastProgress = -1;
		try {
			in = execute(path, GET, params);
			if (in == null)
				return 0;
			int n = 0;
			byte[] buffs = new byte[BUFFER];
			
			
        	if (listener != null) {
				while((n = in.read(buffs)) > 0) {
					fos.write(buffs, 0, n);
					start += n;
					
	        		long progress = start / chunkSize;
	        		try {
	        			if (lastProgress != progress) {
	        				listener.onProgress(start);
	        				lastProgress = progress;
	        			}
	        			Thread.sleep(1);
        			} catch (InterruptedException e) {
        				break;
        			}
				}
        	} else {
				while((n = in.read(buffs)) > 0) {
					fos.write(buffs, 0, n);
					start += n;
					try { 
	        			Thread.sleep(1);
        			} catch (InterruptedException e) {
        				break;
        			}
        		}
        	}
        
		} catch (IOException e) {
			e.printStackTrace();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
		} finally {
			closeQuietly(in);
		}
		return start;
	}
	
	public String getFileString(String path) {
		return getFileString(path, 0);
	}

	public synchronized String getFileString(String path, long start) {
		HashMap<String, String> params = null;
		if (start > 0) {
			params = new HashMap<String, String>();
			params.put("Range", "bytes=" + String.valueOf(start) + "-");
		}
		InputStream in = null;
		try {
			in = execute(path, GET, params);
			if (in == null)
				return null;
			return getStringFromStream(in);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeQuietly(in);
		}
		return null;
	}

	protected synchronized boolean executeWithoutResult(String path, String method) {
		return executeWithoutResult(path, method, null, null);
	}

	protected synchronized boolean executeWithoutResult(String path, String method, Map<String, String> params) {
		return executeWithoutResult(path, method, params, null);
	}

	protected synchronized boolean executeWithoutResult(String path, String method, Map<String, String> params,
			HttpEntity entity) {
		InputStream in = null;
		try {
			in = execute(getAuthorization(), path, method, params, entity);
			if (in == null)
				return false;
			return in != null;
		} finally {
			closeQuietly(in);
		}
	}
	
	protected InputStream execute(String path, String method) {
		return execute(path, method, null);
	}

	protected InputStream execute(String path, String method, Map<String, String> params) {
		return execute(getAuthorization(), path, method, params, null);
	}

	public static String getUserLogin(String authorization) {
		InputStream in = null;
		try {
			in = execute(authorization, PATH_USER_LOGIN, GET, null, null);
			String s = getStringFromStream(in);
			if (s == null)
				return null;
			return s.replace("login:", "").trim(); 			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeQuietly(in);
		}
		return null;
	}
	
	public static ArrayList<WebDavFile> getFiles(String authorization, String path) {
		InputStream in = null;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("Depth", "1");
		try {
			in = execute(authorization, path, PROPFIND, params, null);
			if (in == null)
				return null;
			return XmlResponseReader.getFilesFromStream(in);
		} finally {
			closeQuietly(in);
		}
	}
	
	public static InputStream getFileInputStream(String authorization, String path, long start) {
		HashMap<String, String> params = null;
		if (start > 0) {
			params = new HashMap<String, String>();
			params.put("Range", "bytes=" + String.valueOf(start) + "-");
		}
		return execute(authorization, path, GET, params, null);
	}

	
	public static InputStream execute(String authorization, String path, String method, Map<String, String> params,
			HttpEntity entity) {
		if (authorization == null || authorization.length() == 0)
			return null;
		if (path == null || path.trim().length() == 0)
			path = "/";
		try {
			if (DEBUG) {
				System.out.println("***" + method + " " + path + "***");
			}
			WebDavRequest req = new WebDavRequest( path, method);
//			WebDavRequest req = new WebDavRequest("https" , "webdav.yandex.ru", path, method);
			
			req.addHeader("Accept", "*/*");
			req.addHeader("Authorization", authorization);
			req.addHeader("Origin", BASE_URI);
			if (params != null)
				for (String key : params.keySet()) 
					req.addHeader(key, params.get(key));
			if (entity != null) {
				req.setEntity(entity);
			}
			if (DEBUG) {
				System.out.println("Request Headers:");
				System.out.println(req.getRequestLine());
				for (Header h : req.getAllHeaders()) 
					System.out.println("   " + h.getName() + ":" + h.getValue());
			}
			HttpClient client = new DefaultHttpClient();
			HttpResponse resp = client.execute(req);
                       
			if (resp == null)
				return null;
			
			int code = resp.getStatusLine().getStatusCode();
			if (code != 201 && code != 200 && code != 206 && code != 207) {
				if (resp.getEntity() != null)
					closeQuietly(resp.getEntity().getContent());
				return null;
//				throw new HttpResponseException(code, resp.getStatusLine().getReasonPhrase());
			}
			if (DEBUG) {
				System.out.println("Response Headers");
				System.out.println(resp.getStatusLine());
				for (Header h : resp.getAllHeaders()) 
					System.out.println("   " + h.getName() + ":" + h.getValue());
				System.out.println();
			}
			return resp.getEntity().getContent();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getAuthorization() {
		if (auth != null && auth.isValid())
			return auth.getAuthorizationHeader();
		else
			return null;
	}
	
	/** 
	 * Only OAuth authorization
	 * @param path - path to file in yandex disk
	 * @return Download url or null
	 */
	public String getDownloadUrl(String path) {
		if (auth == null || !(auth instanceof OAuthAuthorization))
			return null;
		return getDownloadUrl(auth.getAuthorizationHeader(), path);
	}
	
	public static String getDownloadUrl(String auth, String path) {
		if (auth == null || auth.length() == 0)
			return null;
		try {
	  		URL Url = new URL(GET_DOWNLOAD_URL + URLEncoder.encode(path, "UTF-8"));
	  		HttpURLConnection conn = (HttpURLConnection) Url.openConnection();
	  		if (conn == null)
	  			return null;
	  		HttpURLConnection.setFollowRedirects(true);	
	  		conn.setReadTimeout(20000);
	  		conn.setConnectTimeout(20000);
	  		conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
	  		conn.addRequestProperty("Authorization", auth);
	  		InputStream in = getInputEncoding(conn);
	  		if (in == null)
	  			return null;
	  		String s = getStringFromStream(in);
	  		if (s == null)
	  			return null;
	  		return new JSONObject(s).getString("href");
	  	} catch (MalformedURLException e) {
	  		e.printStackTrace();
	  	} catch (IOException e) {
	  		e.printStackTrace();
	  	} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static InputStream getInputEncoding(URLConnection connection) throws IOException {
		InputStream in;
		String encoding = connection.getContentEncoding();
		if (encoding != null && encoding.equalsIgnoreCase("gzip")) {
			in = new GZIPInputStream(connection.getInputStream());
		} else if (encoding != null && encoding.equalsIgnoreCase("deflate")) {
			in = new InflaterInputStream(connection.getInputStream(), new Inflater(true));
		} else {
			in = connection.getInputStream();
		}
		return in;
	}	
	
	public static String getStringFromStream(InputStream in) throws IOException {
		InputStreamReader isr = new InputStreamReader(in);
		BufferedReader reader = new BufferedReader(isr);
		StringBuilder str = new StringBuilder();
		String line;
		boolean notFirst = false;
		while ((line = reader.readLine()) != null) {
			if (notFirst)
				str.append('\n');
			str.append(line);
			notFirst = true;
		}
		return str.toString();
	}
	
	public static void closeQuietly(Closeable in) {
		if (in != null)
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public static interface OnLoadProgressListener {
		
		void onProgress(long progress) throws InterruptedException;
		
	}
        
        public String splitURL(String url){
            String finalURL = "";
            //String url = "{\"href\":\"https://uploader4j.disk.yandex.net:443/upload-target/20151016T232152.229.utd.cjwfmldlj3kva54pttgx7f0st-k4j.268418\",\"method\":\"PUT\",\"templated\":false}";
            String[] parts = url.split(",");
           // System.out.println(url);
            //System.out.println(parts[0]);
            parts = parts[0].split(":",2);
           // System.out.println(parts[1]);
            finalURL = parts[1].replace("\"", "");
           // System.out.println(finalURL);
            
            return finalURL;
        }
        
        private void uploadFileByPUT(String theUrl ,File file) throws Exception{
           // File file = new File("img.jpg");
                URL obj = new URL(theUrl);
           
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is PUT
		con.setRequestMethod("PUT");
                con.setDoOutput(true);

		//add request header
		con.setReadTimeout(20000);
                con.setConnectTimeout(20000);
	  	con.setRequestProperty("User-Agent", "Mozilla/5.0");
	  	con.addRequestProperty("Authorization", auth.getAuthorizationHeader());
                System.out.println("\nSending 'PUT' request to URL : " + theUrl);
		
                long size = file.length();
                FileInputStream inputStream = new FileInputStream(file);
                
                boolean isTrue = uploadFile(theUrl,inputStream, size);
                System.out.println(isTrue);
  
                
                int responseCode = con.getResponseCode();
		System.out.println("Response Code : " + responseCode);
                System.out.println(((HttpURLConnection)con).getResponseMessage());
        }
        
        public void deleteMethod(String delPath) throws Exception {
                String url = GET_DELETE_URL+delPath+"&permanently=true";
		String newURL = "";
                String method = DELETE;
                
                newURL = connectWithREST(url,method);

		
        }
        
        public void uploadMethod(String filePath) throws Exception {
                File file =new File(UPLOAD_PATH+filePath);
		String url = GET_UPLOAD_URL+filePath;
		String newURL = "";
                String method = GET;
                
		newURL = connectWithREST(url,method);
                uploadFileByPUT(newURL,file);
                

	}
        
       // https://cloud-api.yandex.net/v1/disk/

    public String connectWithREST(String url, String method) throws IOException, ProtocolException, MalformedURLException {
        String newURL="";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // Connect with a REST Method: GET, DELETE
        con.setRequestMethod(method);
        //add request header
        con.setReadTimeout(20000);
        con.setConnectTimeout(20000);
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.addRequestProperty("Authorization", auth.getAuthorizationHeader());
        int responseCode = con.getResponseCode();
        System.out.println("\nSending '" + method + "' request to URL: " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
		}
        in.close();
        //print result
        System.out.println(response.toString());
        System.out.println();
        if (method.equals(GET)){
            newURL = splitURL(response.toString());
        }
        if (getSize){
            newURL = response.toString();
            getSize = false;
        }
        System.out.println("The new url is: " + newURL);
        System.out.println("---");
        return newURL;
    }
    
    public static void createNewTokenFile(String accessToken){
        //String text = "Hello world";
        BufferedWriter output = null;
        try {
            File file = new File("SPsCredentials/YandexDiskLogin.txt");
            output = new BufferedWriter(new FileWriter(file));
            System.out.println("The token is: " + accessToken);
            output.write(accessToken);
            output.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
    
    public double getYandexDiskSize() throws IOException, JSONException {
        getSize = true;
        System.out.println("--");
        double[] tokens = new double[3];
        String response = connectWithREST(BASE_URL, GET);
        System.out.print("the json is: " + response);
        //Get size
        JSONObject object = new JSONObject(response);
        tokens[0] = (long) object.get("total_space");
        tokens[1] = (long) object.getDouble("used_space");
        tokens[2] = tokens[0] - tokens[1];

        //Size in GB
        System.out.println("\nThe total is: " + (tokens[0]/1024 / 1024/1024));
        System.out.println("The used is: " + (tokens[1]/1024 / 1024/1024));
        System.out.println("The free is: " + (tokens[2]/1024 / 1024/1024));

        return (tokens[2]/1024 / 1024); // MB
//        JSONObject quota = object.getJSONObject("quota");
//        double size = quota.getLong("remaining");
//        System.out.println("Remining storage is: "+(size / 1024 /1024)); //In MB
    }
}