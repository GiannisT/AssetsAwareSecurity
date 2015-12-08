package assetawaresecurity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

import com.dropbox.core.DbxException;

import Cloudme.CloudmeException;
import Cloudme.CloudmeUser;
import cloudMe.CloudMeAPI;
import dropBox.DropBoxAPI;
import googleDrive.GoogleDriveAPI;
import localSPs.BearDataShareAPI;
import localSPs.MegaAPI;
import localSPs.SpiderOakAPI;
import oneDrive.OneDriveAPI;
import yandexDisk.YandexDiskAPI;

public class CloudAPIs {
	String GLOBALL_PATH = "SPsCredentials/";
	String UPLOAD_PATH = "MainUserStorage/";
	private String spName = "";
	private ArrayList<String> spList = new ArrayList<String>();
	AssetAwareSecurity ass; // Controller?
	Gui gui;
	JFrame frame;
	String thePass;
	IntroProcessDialog obj;
	String url ="", returnString="0";

	ArrayList<String> workingSPs = new ArrayList<String>();
	ArrayList<String> nonWorkingSPs = new ArrayList<String>();
	// APIs
	CloudMeAPI cloudMeAPI; CloudmeUser cloudMeUser;
	DropBoxAPI dropBoxAPI;
	GoogleDriveAPI googleAPI;
	OneDriveAPI oneDriveAPI;
	YandexDiskAPI yandexAPI;
	SpiderOakAPI spiderAPI;
	BearDataShareAPI bearAPI;
	MegaAPI megaAPI;

	// can go both client and server side.
	public CloudAPIs(ArrayList<String> availableSPlist) throws IOException, CloudmeException {
		// this.spName = chosenSP;
		this.spList = availableSPlist;

		ass = new AssetAwareSecurity();
		gui = ass.getGui();
		this.frame = gui.myframe;

		// Initialise APIs
		for (int i = 0; i < spList.size(); i++) {
			authServiceProvider(spList.get(i));
		}

	}


	public void authServiceProvider(String name) throws IOException, CloudmeException {
		switch (name) {
		case "DropBox":
			dropBoxAPI = new DropBoxAPI();
			dropBoxAPI.UPLOAD_PATH = this.UPLOAD_PATH;
			authDropBox(name);
			break;
		case "GoogleDrive":
			googleAPI = new GoogleDriveAPI();
			googleAPI.UPLOAD_PATH = this.UPLOAD_PATH;
			authGoogleDrive(name);
			break;
		case "OneDrive":
			oneDriveAPI = new OneDriveAPI();
			oneDriveAPI.UPLOAD_PATH = this.UPLOAD_PATH;
			authOneDrive(name);
			break;
		case "YandexDisk":
			yandexAPI = new YandexDiskAPI();
			yandexAPI.UPLOAD_PATH = this.UPLOAD_PATH;
			authYandexDisk(name);
			break;
		case "CloudMe":
			cloudMeAPI = new CloudMeAPI();
			cloudMeAPI.UPLOAD_PATH = this.UPLOAD_PATH;
			authCloudMe(name);
			break;
		case "SpiderOak":
			spiderAPI = new SpiderOakAPI();
			spiderAPI.UPLOAD_PATH = this.UPLOAD_PATH;
			authSpiderOak(name);
			//spiderAPI.getStorageSize(); -- testing storage size
			break;
		case "BearDataShare":
			bearAPI = new BearDataShareAPI();
			bearAPI.UPLOAD_PATH = this.UPLOAD_PATH;
			authBearDataShare(name);
			break;
		case "MEGA":
			megaAPI = new MegaAPI();
			megaAPI.UPLOAD_PATH = this.UPLOAD_PATH;
			authMEGA(name);
			break;
			
		default:
			JOptionPane.showMessageDialog(null, "Error: " + name +" is not a provider");;
		}
	}
	


	public void doUpload_Delete(String fileName, String spName, Boolean isUpload) throws Exception{
		switch (spName) {
		case "DropBox":
			dropBoxUploadDelete(fileName, isUpload);
			break;
		case "GoogleDrive":
			googleDriveUploadDelete(fileName,isUpload);
			break;
		case "OneDrive":
			oneDriveUploadDelete(fileName, isUpload);
			break;
		case "YandexDisk":
			yandexDiskUploadDelete(fileName, isUpload);
			break;
		case "CloudMe":
			cloudMeUploadDeleteFile(fileName, isUpload);
			break;
		case "SpiderOak":
			spiderOAkUploadDelete(fileName, isUpload);
			break;
		case "BearDataShare":
			bearDataShareUploadDelete(fileName, isUpload);
			break;
		case "MEGA":
			megaUploadDelete(fileName, isUpload);
			break;
		default:
			throw new IllegalArgumentException("Invalid UploadDelete ");
		}
		
	}
	
	public void getStorageSize( String spName) throws Exception{
		double memory=0;
		switch (spName) {
		case "DropBox":
			memory=dropBoxAPI.getDropboxSize();
			break;
		case "GoogleDrive":
			memory=googleAPI.getGoogleDriveSize();
			break;
		case "OneDrive":
			memory=oneDriveAPI.getOneDriveSize();
			break;
		case "YandexDisk":
			memory=yandexAPI.getYandexDiskSize();
			break;
		case "CloudMe":
			memory=cloudMeAPI.getCloudMeSize();
			break;
		case "SpiderOak":
			memory=spiderAPI.getStorageSize();
			break;
		case "BearDataShare":
			memory=bearAPI.getStorageSize();
			break;
		case "MEGA":
			memory=megaAPI.getStorageSize();
			break;
		default:
			throw new IllegalArgumentException("Invalid UploadDelete ");
		}
		
	}


	private void authBearDataShare(String name) throws IOException {
		authLocalSPs(name);
		
	}

	private void authSpiderOak(String name) throws IOException {
		authLocalSPs(name);	
	}

	private void authMEGA(String name) throws IOException {
		authLocalSPs(name);
		
	}


	private void authLocalSPs(String name) throws IOException {
		String loginPath;
		loginPath = GLOBALL_PATH + name + "Login.txt";
		File f = new File(loginPath);
		if (f.exists() && !f.isDirectory()) {
			if (name.equals("SpiderOak")) {
				spiderAPI.login(loginPath);
			} else if (name.equals("BearDataShare")) {
				bearAPI.login(loginPath);
			} else if (name.equals("MEGA")) {
				megaAPI.login(loginPath);
			}
			JOptionPane.showMessageDialog(null, name + " Cloud found");
			workingSPs.add(name);
		} else {
			int result = JOptionPane.showConfirmDialog(null,
					name + " client must be first installed on this machine and be at the default location", "alert",
					JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.CANCEL_OPTION) {
				JOptionPane.showMessageDialog(null, name + " Proccess has been canceled");
				nonWorkingSPs.add(name);
			} else {
				String clientFolder;
				clientFolder = JOptionPane.showInputDialog("Please enter the full path of your " + name + " folder:");
				File folder = new File(clientFolder);
				if (folder.exists() && folder.isDirectory()) {
					clientFolder = clientFolder+"/";
					if (name.equals("SpiderOak")) {
						spiderAPI.createNewLogin(clientFolder);
					} else if (name.equals("BearDataShare")) {
						bearAPI.createNewLogin(clientFolder);
					} else if (name.equals("MEGA")) {
						megaAPI.createNewLogin(clientFolder);
					}

					JOptionPane.showMessageDialog(null, name + " Preferences saved");
					workingSPs.add(name);
				} else {
					JOptionPane.showMessageDialog(null, clientFolder + " does not exist.");
				}
			}
		}
	}


	private void authOneDrive(String name) throws IOException {
		String loginPath;
		loginPath = GLOBALL_PATH + name + "Login.txt";
		File f = new File(loginPath);
		if (f.exists() && !f.isDirectory()) {
			oneDriveAPI.login(loginPath);
			JOptionPane.showMessageDialog(null, name + " Succesfully loaded");
			workingSPs.add(name);
		}else{
			url = oneDriveAPI.getURL();
			authDealog(name,url);
			returnString = obj.returnString;
			if (returnString.equals("0")) {
				JOptionPane.showMessageDialog(null, name + " Proccess has been canceled");
				nonWorkingSPs.add(name);
			}else{
				try {
					oneDriveAPI.createNewLogin(returnString);
					JOptionPane.showMessageDialog(null, name + " Succesfully authenticated");
					workingSPs.add(name);
				}  catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
				}
			}
		}
	}

	private void authDropBox(String name) throws IOException {
		String accessToken="", loginPath;
		loginPath = GLOBALL_PATH + name + "Login.txt";
		File f = new File(loginPath);
		if (f.exists() && !f.isDirectory()) {
			dropBoxAPI.login(loginPath);
			JOptionPane.showMessageDialog(null, name + " Succesfully loaded");
			workingSPs.add(name);
		}else{
			url = dropBoxAPI.getURL();
			authDealog(name, url);
			returnString = obj.returnString;
			System.out.println(returnString);
			if (returnString.equals("0")) {
				JOptionPane.showMessageDialog(null, name + " Proccess has been canceled");
				nonWorkingSPs.add(name);
			}else{
				try {
					dropBoxAPI.createNewLogin(returnString);
					JOptionPane.showMessageDialog(null, name + " Succesfully authenticated");
					workingSPs.add(name);
				} catch (DbxException e) {
					JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());;
				}
			}
			//dropBoxAPI.createNewLogin();
		}
		

	}
	
	private void authGoogleDrive(String name) {
		String temp = "StoredCredential"; // change name to the name of file
		File f = new File(GLOBALL_PATH + temp);
		if (f.exists() && !f.isDirectory()) {
			try {
				googleAPI.login();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());;
			}
			JOptionPane.showMessageDialog(null, name + " Succesfully loaded");
			workingSPs.add(name);
		}else {
			 int result = JOptionPane.showConfirmDialog(null, "You must first authendicate your "+name+" accound. Click OK to redirect",
				        "alert", JOptionPane.OK_CANCEL_OPTION);
			 if(result == JOptionPane.CANCEL_OPTION){
				 JOptionPane.showMessageDialog(null, name + " Proccess has been canceled");
				 nonWorkingSPs.add(name);
			 }else{
				 try {
					googleAPI.login();
					JOptionPane.showMessageDialog(null, name + " Succesfully authenticated");
					workingSPs.add(name);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
				}
			 }
		}

	}

	private void authYandexDisk(String name) {
		String loginPath = GLOBALL_PATH + name + "Login.txt";
		File f = new File(loginPath);
		if (f.exists() && !f.isDirectory()) {
			try {
				yandexAPI.login(loginPath);
				JOptionPane.showMessageDialog(null, name + " Succesfully loaded");
				workingSPs.add(name);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
			}
		}else{
			url = yandexAPI.getOAthRequestUrl();
			authDealog(name, url);
			returnString = obj.returnString;
			if (returnString.equals("0")) {
				JOptionPane.showMessageDialog(null, name + " Proccess has been canceled");
				nonWorkingSPs.add(name);
			} else {
				try {
					yandexAPI.createNewLogin(returnString);
					JOptionPane.showMessageDialog(null, name + " Succesfully authenticated");
					workingSPs.add(name);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
				}
			}
		}

	}

	private void authCloudMe(String name) throws IOException {
		File f = new File(GLOBALL_PATH + name + "Login.txt");
		if (f.exists() && !f.isDirectory()) {
			try {
				cloudMeUser = cloudMeAPI.login(GLOBALL_PATH + name + "Login.txt"); // user might not need																					
				JOptionPane.showMessageDialog(null, name + " Succesfully loaded");
				workingSPs.add(name);
			} catch (CloudmeException e) {
				JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
			}
		} else {
			url = "";
			authDealog(name, url);
			returnString = obj.returnString;
			System.out.println(returnString);
			if (returnString.equals("0")) {
				JOptionPane.showMessageDialog(null, name + " Proccess has been canceled");
				nonWorkingSPs.add(name);
			} else {
				try {
					cloudMeUser = cloudMeAPI.createNewLogin(returnString);
					JOptionPane.showMessageDialog(null, name + " Succesfully authenticated");
					workingSPs.add(name);
				} catch (CloudmeException e) {
					JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
				}
			}
		}
	}

	private void cloudMeUploadDeleteFile(String fileName, boolean isUpload) throws CloudmeException{
		if(isUpload){
			cloudMeAPI.uploadFile(fileName); 
		}else{
			cloudMeAPI.deleteFile( fileName);
		}
	}
	
	private void spiderOAkUploadDelete(String fileName, Boolean isUpload) throws IOException {
		if(isUpload){
			spiderAPI.uploadFile(fileName);
		}else{
			spiderAPI.deleteFile(fileName);
		}
		
	}
	
	private void yandexDiskUploadDelete(String filePath, Boolean isUpload) throws Exception {
		if(isUpload){
			yandexAPI.uploadMethod(filePath);
		}else{
			yandexAPI.deleteMethod(filePath);
		}
	}
	
	private void oneDriveUploadDelete(String filePath, Boolean isUpload) throws IOException {
		if(isUpload){
			oneDriveAPI.uploadFile(filePath);
		}else{
			oneDriveAPI.deleteFile(filePath);
		}
		
	}

	private void bearDataShareUploadDelete(String filePath, Boolean isUpload) throws IOException {
		if(isUpload){
			bearAPI.uploadFile(filePath);
		}else{
			bearAPI.deleteFile(filePath);
		}
		
	}

	private void megaUploadDelete(String filePath, Boolean isUpload) throws IOException {
		if(isUpload){
			megaAPI.uploadFile(filePath);
		}else{
			megaAPI.deleteFile(filePath);
		}
		
	}
	
	private void googleDriveUploadDelete(String fileName, boolean isUpload) throws IOException {
		if(isUpload){
			googleAPI.uploadFile(fileName);
		}else{
			googleAPI.deleteFile(fileName);
		}
		
	}
	
	private void dropBoxUploadDelete(String fileName, boolean isUpload) throws FileNotFoundException, DbxException, IOException {
		if (isUpload){
			dropBoxAPI.uploadFile(fileName);
		}else{
			dropBoxAPI.deleteFile(fileName);
		}
		
	}
	
	//deletes a file from MainUserStorageFile in case that is not needed NOT USED FOR NOW!!!!!
	public void delMainUserStorageFile(String filename){
		  File file = new File("MainUserStorage/"+filename);
		  
		  if(file.exists()){
			  file.delete();
		  }
	}
	
	//Upload file to local folder if not found a suitable SP
	public void upload_deleteLocalFolder(String fileName, boolean bool) throws IOException{
		String folderTo ="LocalStorage/";
		String folderFrom = UPLOAD_PATH;
		
       if(bool==true){ //keep folder to local storage
    	   File file = new File(folderFrom+fileName);
    	   System.out.println(file.getAbsolutePath());       
    	   File newFile = new File(folderTo + fileName);
    	   FileUtils.copyFile(file, newFile);
    	   file.delete(); //delete the file in mainStorage after the file 
    	   System.out.println("Copied at: "+folderTo);
       }else{//delete folder from local storage
    	   File delfile = new File(folderTo+fileName);
    	 
    	   if (delfile.exists()) {
    		   delfile.delete();
               System.out.println("DeletedLocalFolder");
           }
       }
	}
	
	
	
	public void authDealog(String name, String url) {
		obj = new IntroProcessDialog(frame, true, url);

		obj.spTitlelbl1.setText(name);
		obj.spTitlelbl2.setText(name);
		obj.setTitle("Authendicating " + name);
		obj.setLocationRelativeTo(frame);
		obj.setVisible(true);
	}

	// ** Setters and Getters **
	public void setSPname(String name) {
		this.spName = name;
	}

	public String getSPname() {
		return this.spName;
	}
	
	public ArrayList<String> getWorkingSPsList(){
		return workingSPs;
	}
	
	public ArrayList<String> getNonWorkingSPsList(){
		return nonWorkingSPs;
	}
	public void clearList(){
		workingSPs.clear();
		nonWorkingSPs.clear();
	}
}