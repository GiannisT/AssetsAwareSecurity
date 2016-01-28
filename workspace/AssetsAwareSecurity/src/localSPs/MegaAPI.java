package localSPs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;



public class MegaAPI {
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
    	
    }
	
    public static void uploadFile(String fileName) throws IOException{
        java.io.File file = new java.io.File(UPLOAD_PATH+fileName);
        System.out.println(file.getAbsolutePath());
        
        
        String newDir = ROOT_PATH;
        
        java.io.File newFile = new java.io.File(newDir + fileName);
        System.out.println("NEW FILE: "+newFile.getName());
        FileUtils.copyFile(file, newFile);
        System.out.println("Copied at: "+ROOT_PATH);
        
    }
    
    public static void deleteFile(String fileName) {    
        String newDir = ROOT_PATH;
        
        java.io.File file = new java.io.File(newDir + fileName);
        file.delete();
        
    }
    
    public void createNewLogin(String folder){
    	String accessToken = folder;
    	BufferedWriter output = null;
        try {
            File file = new File("SPsCredentials/MEGALogin.txt");
            output = new BufferedWriter(new FileWriter(file));
            System.out.println("The token is: " + accessToken);
            output.write(accessToken);
            output.close();
            ROOT_PATH = accessToken;
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    	
    }

    public double getStorageSize() throws IOException {
    	BigInteger num = new BigInteger("1024");
        String ans ="";
        BigInteger totalMemory = new BigInteger("53687091200");
        BigInteger used;
        File dir = new File(ROOT_PATH);
        long totalUsed = 0; // total bytes

        System.out.println("Getting all files in " + dir.getCanonicalPath() + " including those in subdirectories");
        List<File> files = (List<File>) FileUtils.listFiles(dir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
        for (File file : files) {
            System.out.println("file: " + file.getCanonicalPath());
            totalUsed += file.length();
        }
        used = new BigInteger(String.valueOf(totalUsed));
        
        System.out.println("Total bytes in memory: "+totalMemory); // bytes
        System.out.println("Total bytes in use: "+used);
        System.out.println("Total free bytes: "+(totalMemory.subtract(used)));
        
        ans = totalMemory.subtract(used).divide(num).divide(num).toString();//MB
        return (Double.parseDouble(ans)); 
    }
    
    
}
