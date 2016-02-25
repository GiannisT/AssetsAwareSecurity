/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localSPs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;



/**
 * The Bear Data Share API
 * 
 * @author Marios Zinonos
 */
public class BearDataShareAPI {
    
    public static String UPLOAD_PATH;
    static String ROOT_PATH = System.getProperty("user.home")+"/BearDataShare/";
    

    public static void uploadFile(String fileName) throws IOException{
        java.io.File file = new java.io.File(UPLOAD_PATH+fileName);
        
        
        String newDir = ROOT_PATH;
        
        java.io.File newFile = new java.io.File(newDir + fileName);
        FileUtils.copyFile(file, newFile);        
    }
    
    public void login(String fileName) throws IOException{
    	File f = new File(fileName);
    	String fileToken;
        // Authentication loaded
        BufferedReader br = new BufferedReader(new FileReader(f));
        fileToken = br.readLine(); // do refresh token
        ROOT_PATH = fileToken;
    	
    }
    
    public void createNewLogin(String folder){
    	String accessToken = folder;
    	BufferedWriter output = null;
        try {
            File file = new File("SPsCredentials/BearDataShareLogin.txt");
            output = new BufferedWriter(new FileWriter(file));
            output.write(accessToken);
            output.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    	
    }
    
    public double getStorageSize() throws IOException {
    	BigInteger num = new BigInteger("1024");
        String ans ="";
        BigInteger totalMemory = new BigInteger("21474836480");
        BigInteger used;
        File dir = new File(ROOT_PATH);
        long totalUsed = 0; // total bytes
        List<File> files = (List<File>) FileUtils.listFiles(dir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
        for (File file : files) {
            totalUsed += file.length();
        }
        used = new BigInteger(String.valueOf(totalUsed));
        ans = totalMemory.subtract(used).divide(num).divide(num).toString();//MB
        return (Double.parseDouble(ans)); 
    }
    

    public static void deleteFile(String fileName) {    
        String newDir = ROOT_PATH;
        
        java.io.File file = new java.io.File(newDir + fileName);
        file.delete();
        
    }
    
}
