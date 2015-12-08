/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudMe;
import Cloudme.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author methis
 */
public class CloudMeAPI {
    static String userName, pass;
    CloudmeUser user;
    public static String UPLOAD_PATH;
    

    
    public void closeConnection(CloudmeUser user){
    	user.killUser();
    }

    public CloudmeUser login(String loginPath) throws IOException, CloudmeException {
        String[] credentials;
        File f = new File(loginPath);
        if(f.exists() && !f.isDirectory()) {
            // Authentication loaded
            System.out.println("iparxooooo");
            BufferedReader br = new BufferedReader(new FileReader(f));
            credentials = br.readLine().split(",");
            userName = credentials[0];
            pass = credentials[1];
            user = new CloudmeUser(userName,pass);
            
        }
        return user;
    }
    
    public CloudmeUser createNewLogin(String credentials) throws CloudmeException{
      System.out.println("Den iparxooo");
      String [] parts = credentials.split(",");
      userName = parts[0];
      pass = parts[1];
      
      createNewLoginFile(userName,pass);
      user = new CloudmeUser(userName,pass); // needs to be returned
      return user;
    }
    
    public void uploadFile( String fileName) throws CloudmeException { //CloudmeUser user,
        user.getFileManager().uploadFile(UPLOAD_PATH+fileName,"/");
        
    }
    
    //Not available through API
    public double getCloudMeSize()throws CloudmeException{
    	System.out.println("coming soon");
    	return 0;
    }

    public void deleteFile( String fileName) {
        try {
            CloudmeFile file;
            file = user.getFileManager().getFile("D:/"+fileName);
            System.out.println(file.toString());
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
            System.out.println("The token is: " + line);
            output.write(line);
            output.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
    
}
