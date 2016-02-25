/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package googleDrive;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.*;
import com.google.api.services.drive.Drive;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * The Google Drive API
 * 
 * @author Marios Zinonos
 */
public class GoogleDriveAPI {
    static String path = "img.jpg";
    static String callBack = "http://localhost:37198/Callback";
    public static String UPLOAD_PATH;
    private static final java.io.File UPLOAD_FILE = new java.io.File(path);
    private static final java.io.File DELETE_FILE = new java.io.File(path);
    
    /** Application name. */
    private static final String APPLICATION_NAME = "AssetSecurityAware";

    /** Directory to store user credentials for this application. */
    private static final java.io.File DATA_STORE_DIR = new java.io.File("SPsCredentials"); // chage to our storage

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY =
        JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the scopes required by this quickstart. */
    private static final List<String> SCOPES =
        Arrays.asList(DriveScopes.DRIVE);

    public static Drive service;
    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize() throws IOException {
        // Load client secrets.
    	
        InputStream in = GoogleDriveAPI.class.getResourceAsStream("/client_secret.json"); // /ConfigFiles/googleConfig.json"
        GoogleClientSecrets clientSecrets =
            GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
       // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline")
                .build();       
        Credential credential = new AuthorizationCodeInstalledApp(
            flow, new LocalServerReceiver()).authorize("user");
        System.out.println(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        
        
        return credential;
    }

    /**
     * Build and return an authorized Drive client service.
     * @return an authorized Drive client service
     * @throws IOException
     */
    public static void login() throws IOException {
        Credential credential = authorize();
        
        service = new Drive.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
    
    private static File insertFile(Drive service, String title, String description,
      String parentId, String mimeType, String filename) {
       
        return null;
        
    }
    
    
    public double getGoogleDriveSize(){
    	double freeMem=0;
        try {
          About about = service.about().get().execute();
          freeMem = (about.getQuotaBytesTotal() - about.getQuotaBytesUsed());
          
          
        } catch (IOException e) {
          System.out.println("An error occurred: " + e);
        }
		return freeMem /1024/1024;
    }
    
      /** Uploads a file using either resumable or direct media upload. */
  
  public static void uploadFile(String fileName) throws IOException{ // should add file
	  java.io.File uploadFile = new java.io.File(UPLOAD_PATH+fileName);
      File fileMetadata = new File();
      fileMetadata.setTitle(uploadFile.getName());
      //fileMetadata.setId(UPLOAD_FILE.getName());
      InputStreamContent mediaContent = new InputStreamContent("", new BufferedInputStream(new FileInputStream(uploadFile)));
        mediaContent.setLength(uploadFile.length());

        Drive.Files.Insert request = service.files().insert(fileMetadata, mediaContent);
        request.execute();
  }
  
  public static void deleteFile (String fileName) throws IOException { // should add string
      File file = getTheFile(service, path);
      service.files().delete(file.getId()).execute();
  }
  
  private static File getTheFile(Drive drive, String fileName) throws IOException{
       File theFile = new File();
       FileList result = drive.files().list().execute();
       
       List<File> files = result.getItems();
        if (files == null || files.size() == 0) {
        }else{
            for(int i=0;i<files.size();i++){
                theFile = files.get(i);
                if (theFile.getTitle().equals(fileName)){ 
                    break;
                }
            }
        }
       
       return theFile;
      
  }

}