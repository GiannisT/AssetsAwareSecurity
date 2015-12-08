/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localSPs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;


/**
 *
 * @author methis
 */
public class SpiderOakAPI {
    static String USER_NAME = "u_spideroak_auto_124455", USER_PASS = "M4rio235Oak";
    static String ENCODED_PASS = "M5XWW5JNONZWUMSANBXXI3LBNFWC4Y3PNU";
    static String GET = "GET", DELETE = "DELETE", PUT = "PUT", POST = "POST";
    
    static String ROOT_URL = "https://spideroak.com/storage/";
    static String DEL_URL = "https://spideroak.com/storage/OVPXG4DJMRSXE33BNNPWC5LUN5PTCMRUGQ2TK/ubuntuLap/home/methis/SpiderOak%20Hive/";
    
    public static String UPLOAD_PATH;
    static String ROOT_PATH;
    

    public void login(String fileName) throws IOException{
    	File f = new File(fileName);
    	String fileToken;
        // Authentication loaded
        System.out.println("iparxooooo");
        BufferedReader br = new BufferedReader(new FileReader(f));
        fileToken = br.readLine(); // do refresh token
        ROOT_PATH = fileToken;
        System.out.println("Root folder is: "+ROOT_PATH);
    	
    }
   
    public static void uploadFile(String fileName) throws IOException{
        java.io.File file = new java.io.File(UPLOAD_PATH+fileName);
        System.out.println(file.getAbsolutePath());
        
        
        String newDir = ROOT_PATH;
        
        java.io.File newFile = new java.io.File(newDir + fileName);
        FileUtils.copyFile(file, newFile);
        System.out.println("Copied at: "+ROOT_PATH);
        
    }
    
    public double getStorageSize() throws IOException {
        int num = 1024;
        long totalMemory = 2*num*num*num; // in GB need to be declared constant in all calasses --To bytes
        File dir = new File(ROOT_PATH);
        long totalUsed = 0; // total bytes

        System.out.println("Getting all files in " + dir.getCanonicalPath() + " including those in subdirectories");
        List<File> files = (List<File>) FileUtils.listFiles(dir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
        for (File file : files) {
            System.out.println("file: " + file.getCanonicalPath());
            //System.out.println(file.length());
            totalUsed += file.length();
        }
        System.out.println("Total bytes in memory: "+totalMemory); // bytes
        System.out.println("Total bytes in use: "+totalUsed);
        System.out.println("Total free bytes: "+(totalMemory-totalUsed));
        
        return ((totalMemory-totalUsed) /num/num); //MB
    }
    
    public void createNewLogin(String folder){
    	String accessToken = folder;
    	BufferedWriter output = null;
        try {
            File file = new File("SPsCredentials/SpiderOakLogin.txt");
            output = new BufferedWriter(new FileWriter(file));
            System.out.println("The token is: " + accessToken);
            output.write(accessToken);
            output.close();
            ROOT_PATH = accessToken;
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    	
    }
    
    public static String connectWithREST(String url, String method) throws IOException, ProtocolException, MalformedURLException {
        String newURL="";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // Connect with a REST Method: GET, DELETE, PUT
        con.setRequestMethod(method);
        //add request header
        con.setReadTimeout(20000);
        con.setConnectTimeout(20000);
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        if (method.equals(DELETE) || method.equals(PUT))
            con.addRequestProperty("Authorization","Base M5XWW5JNONZWUMSANBXXI3LBNFWC4Y3PNU5E2NDSNFXTEMZVJ5QWW");
        
        int responseCode = con.getResponseCode();
        System.out.println("\nSending '" + method + "' request to URL: " + url);
        System.out.println("Response Code : " + responseCode);
        
        // Read response
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
        newURL = response.toString();

        System.out.println("The new url is: " + newURL);
        System.out.println("---");
        return newURL;
    }

    public static void deleteFile(String fileName) {    
        String newDir = ROOT_PATH;
        
        java.io.File file = new java.io.File(newDir + fileName);
        file.delete();
        
    }
    
}
