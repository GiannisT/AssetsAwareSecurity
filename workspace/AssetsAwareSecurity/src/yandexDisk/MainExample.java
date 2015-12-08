/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yandexDisk;

import com.sun.jndi.toolkit.url.Uri;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author methis
 */
public class MainExample {
    static String cliendID = "ef6dc79d9f8a448f821acf7a95789f41";
    static String login = "cloud.sec.aware.test@yandex.com"; // need to read them from file
    static String password = "!Y4ndex235";
    
    static String testPath = "/HelloFolder";
    
//    public static void main(String[] args) throws IOException {
//        String test = "", authURL = "", dnURL = "Bears.jpg", upURL = "img.jpg";
//        String accessToken = "";
//        Boolean auth;
//        YandexDiskApi api = new YandexDiskApi();
//        api.setCredentials();
//        
//        test = api.getCredentialsLogin();
//        System.out.println(test);
//        test = api.getCredentialsPassword();
//        System.out.println(test);
//        
//        authURL = api.getOAthRequestUrl();  
//        File f = new File("yandexAccessToken.txt");
//            if(f.exists() && !f.isDirectory()) { 
//                login(api, f);
//            }else{
//                createNewLogin(authURL, api);
//            }  
//        test = api.getToken();
//        System.out.println("GetToken = " + test);
//        
//        auth = api.isAuthorization();
//        System.out.println(auth);
//        
//        //api.splitURL();
//        
//        System.out.println();
//        
//        try {
//           api.uploadFileByGET(upURL);
//           //api.deleteFileByGET(upURL);
//        } catch (Exception ex) {
//            Logger.getLogger(MainExample.class.getName()).log(Level.SEVERE, null, ex);
//        }
//          
//
//        
//        
//        
//    }

	private static void createNewLogin(String authURL, YandexDiskAPI api) throws IOException {
		String accessToken;
		System.out.println("Den iparxooo");
		System.out.println("1. Go to: " + authURL);
		System.out.println("2. Click \"Allow\" (you might have to log in first)");
		System.out.println("3. Copy the authorization code.");
		accessToken = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();
		System.out.println(accessToken);
    //------------------------------
		api.setToken(accessToken);
		
		// Function to write a new txt file with token
		api.createNewTokenFile(accessToken);
	}

	private static void login(YandexDiskAPI api, File f) throws FileNotFoundException, IOException {
		String accessToken;
		// Authentication loaded
		System.out.println("iparxooooo");
		BufferedReader br = new BufferedReader(new FileReader(f));
		accessToken = br.readLine();
		api.setToken(accessToken);
	}
    
}
