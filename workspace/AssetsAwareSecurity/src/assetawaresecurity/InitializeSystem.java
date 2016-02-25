package assetawaresecurity;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
/**
 *  This class is responsible for initializing the system and creating 
 *  nessesary resources. 
 *   
 * @author Giannis Tzakouris, Marios Zinonos
 *
 */
public class InitializeSystem { // Model
	
	static String uploadBandwidth=" ", OS=" ";

    //initializes and configures the system
    public InitializeSystem() {
        this.MakeUserStorageDirectory();
        this.makeSPsCredentials();
        this.makeUserDatabase();
        this.makeLocalStorageFolder();
    }

    public void makeLocalStorageFolder(){
   	 File k = new File("LocalStorage");

        if (!(k.exists() && k.isDirectory())) {
            new File("LocalStorage").mkdir();
        }
   }
    
    //initializes the data of the user
    public void makeUserDatabase(){
    	 File k = new File("UserDatabase");

         if (!(k.exists() && k.isDirectory())) {
             new File("UserDatabase").mkdir();
         }
    }
    
    public void makeSPsCredentials(){
        File k = new File("SPsCredentials");

        if (!(k.exists() && k.isDirectory())) {
            new File("SPsCredentials").mkdir();
        }
    }

	  //creates the main directory that will store the home folder of the user
    public void MakeUserStorageDirectory() {
        File k = new File("MainUserStorage");

        if (!(k.exists() && k.isDirectory())) {
            new File("MainUserStorage").mkdir();
        }
    }

    //creates the main directory for SPs that will store the offers of SPs folder of SPs
    public void MakeSPDirectory() {
        File k = new File("MainSPStorage");

        if (!(k.exists() && k.isDirectory())) {
            new File("MainSPStorage").mkdir();
        }
    }
    
    public User loadUserData(){
        File xml = new File("user.xml");
        User user = new User();
        if (xml.exists()){
            try{
                XMLDecoder dec = new XMLDecoder( new FileInputStream(xml));
                user = (User) dec.readObject();
                dec.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
                
            }
        }
        
        return user;
    }
    
	public CreateBid loadPolicyData(File policy, File policyWeights, File spSizes) {
		CreateBid bid = new CreateBid();
		Map<String, Double> Significance = new HashMap<String, Double>();
		Map<String, Double> SPsSizes = new HashMap<String, Double>();
		
		// Load Policy
		try {
			XMLDecoder dec = new XMLDecoder(new FileInputStream(policy));
			bid = (CreateBid) dec.readObject();
			dec.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		//Load Weights
		try {
			XMLDecoder dec = new XMLDecoder(new FileInputStream(policyWeights));
			Significance = (HashMap<String, Double>) dec.readObject();
			dec.close();
			bid.SetSignificance(Significance);
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		
		//Load SP Sizes
		try {
			XMLDecoder dec = new XMLDecoder(new FileInputStream(spSizes));
			SPsSizes = (HashMap<String, Double>) dec.readObject();
			dec.close();
			bid.SetSPsSize(SPsSizes);
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		
		return bid;
	}
    
	// two saved policies can be merge into one
    public void saveLastPolicy(CreateBid bid){
        try{
            XMLEncoder encoder = new XMLEncoder(new FileOutputStream("lastPolicy.xml"));
            encoder.writeObject(bid);
            encoder.close();
        }catch (Exception e){
           System.out.println(e.getMessage());
        }
        
        //writes the security attributes weights
        try{
            XMLEncoder encoder1 = new XMLEncoder(new FileOutputStream("lastPolicyWeights.xml"));
            encoder1.writeObject(bid.getSignificance());
            encoder1.close();
        }catch (Exception e){
           System.out.println(e.getMessage());  
        }
        
        //writes the AvailSPs storage size
        try{
            XMLEncoder encoder2 = new XMLEncoder(new FileOutputStream("spSizes.xml"));
            encoder2.writeObject(bid.getSPsSize());
            encoder2.close();
        }catch (Exception e){
           System.out.println(e.getMessage());
        }
    }
    
    public void saveCustomPolicy(CreateBid bid){
       //writes the security attributes
        try{
            XMLEncoder encoder = new XMLEncoder(new FileOutputStream("customPolicy.xml"));
            encoder.writeObject(bid);
            encoder.close();
        }catch (Exception e){
           System.out.println(e.getMessage());
        }
        
      //writes the security attributes weights
        try{
            XMLEncoder encoder1 = new XMLEncoder(new FileOutputStream("customPolicyWeights.xml"));
            encoder1.writeObject(bid.getSignificance());
            encoder1.close();
        }catch (Exception e){
           System.out.println(e.getMessage());
        }
        
        //writes the AvailSPs storage size
        try{
            XMLEncoder encoder2 = new XMLEncoder(new FileOutputStream("spSizes.xml"));
            encoder2.writeObject(bid.getSPsSize());
            encoder2.close();
        }catch (Exception e){
           System.out.println(e.getMessage());
        }
    }
    
 // Not to be used at the moment  
    public void saveUserData( User user){
        //boolean isSaved = true; 
        
        try{
            XMLEncoder encoder = new XMLEncoder(new FileOutputStream("user.xml"));
            encoder.writeObject(user);
            encoder.close();
        }catch (Exception e){
           System.out.println(e.getMessage());
        }
        
    }
    
    //save the assetTab information for gui illustration
    public void saveAssetTab(ArrayList<Object[]> assetsList){
    	 try{
             XMLEncoder encoder = new XMLEncoder(new FileOutputStream("assetsList.xml"));
             encoder.writeObject(assetsList);
             encoder.close();
         }catch (Exception e){
            System.out.println(e.getMessage());
         }
    }
    
    //returns the loaded files that were matched and uploaded to SPs (that are contained in the MainUserStorage)
    public ArrayList<Object[]> loadAssetTab(File assets){
    	
    	ArrayList<Object[]> assetsList = new ArrayList<Object[]>();
    		
    		try {
    			XMLDecoder dec = new XMLDecoder(new FileInputStream(assets));
    			assetsList = (ArrayList<Object[]>) dec.readObject();
    			dec.close();
    		} catch (Exception e) {
    			System.out.println(e.getMessage());
    		}
   
    					
    	return assetsList;
    }
    
    
    //this function aims to return the current systems Operating system and upload Speed which will help us estimate time for upload
    public static void SystemOSbandwidth(){
    	OS=System.getProperty("os.name");
    	
    	Runtime rt = Runtime.getRuntime();
    	String command=" ";
    	
    	if (OS.toLowerCase().contains("windows")){
    		command= "speedtest-32-v0.8.4-d.exe -uo";    		
    	}else if (OS.toLowerCase().contains("mac")){
    		command= "sudo chmod ugo+x speedtest-mac-amd64-v0.8.4-d -uo";
    	}else{
    		command= "sudo chmod ugo+x speedtest-linux-386-v0.8.4-d -uo";
    	}
    	
    	uploadBandwidth=null;
		Process proc = null;
		try {
			proc = rt.exec(command);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

		// read the output from the command
		System.out.println(" Operating System:" +OS);
		String s = null;
		try {
			while ((s = stdInput.readLine()) != null) {
			    
				if(s.contains("Upload")){
					s=s.substring(s.length()-11, s.length()-5).replace(" ","");
					
					if(s.contains(":")){
						s=s.replace(":","");
					}
					uploadBandwidth=s;
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }
    
     
}