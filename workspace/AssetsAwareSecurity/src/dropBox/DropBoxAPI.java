/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dropBox;


/**
 * The DropBox API
 * 
 * @author Marios Zinonos
 */
import com.dropbox.core.*;
import java.awt.Component;
import java.io.*;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class DropBoxAPI { 
    public static String fileName; 
    public static String UPLOAD_PATH;
    static String accessToken = "";
    final static String APP_KEY = "njiyfgkul7gukcn";
    final static String APP_SECRET = "6avrsd2oja67mlr";
    static DbxClient client;
    static DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
    static DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0",
            Locale.getDefault().toString());
    static DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
    


    public String getURL(){
    	return webAuth.start();
    }
    
    public static void uploadFile( String fileName) throws DbxException, FileNotFoundException, IOException {
    	
        //Upload
    	String newFile = UPLOAD_PATH+fileName;
        File inputFile = new File(newFile);
        FileInputStream inputStream = new FileInputStream(inputFile);
        try {
            DbxEntry.File uploadedFile = client.uploadFile("/"+fileName,
                    DbxWriteMode.add(), inputFile.length(), inputStream);
            } finally {
            inputStream.close();
        }
    }

    private static void createNewTokenFile(String accessToken){
        BufferedWriter output = null;
        try {
            File file = new File("SPsCredentials/DropBoxLogin.txt");
            output = new BufferedWriter(new FileWriter(file));
            output.write(accessToken);
            output.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
    
    public static void login(String loginPath) throws IOException{
            // Authentication loaded
            BufferedReader br = new BufferedReader(new FileReader(loginPath));
            accessToken = br.readLine();

            client = new DbxClient(config, accessToken);
        
    }
    
    public static void createNewLogin(String code) throws IOException, DbxException{
       
        // This will fail if the user enters an invalid authorization code.
        DbxAuthFinish authFinish = webAuth.finish(code);
        accessToken = authFinish.accessToken;
        
        // Function to write a new txt file with token
        createNewTokenFile(accessToken);
        client = new DbxClient(config, accessToken);
    	
    }
    
    public static void deleteFile( String file) throws DbxException{
        client.delete("/"+file);
    }
    
	public static double getDropboxSize() throws DbxException {
		long dropboxSize = 0, avail=0;
		double totalAvail = 0;
		DbxAccountInfo dbxAccountInfo = client.getAccountInfo();
		// in MB :)
		dropboxSize = dbxAccountInfo.quota.total ;
        avail = dbxAccountInfo.quota.normal ;
        totalAvail = dropboxSize - avail;
		return totalAvail / 1024 / 1024;
	}

        
}
