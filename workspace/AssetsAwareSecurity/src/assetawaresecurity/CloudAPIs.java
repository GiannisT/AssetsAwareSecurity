package assetawaresecurity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ProgressMonitor;

import org.apache.commons.io.FileUtils;

import com.dropbox.core.DbxException;

import Cloudme.CloudmeException;
import Cloudme.CloudmeUser;
import cloudMe.CloudMeAPI;
import dropBox.DropBoxAPI;
import googleDrive.GoogleDriveAPI;
import localSPs.BearDataShareAPI;
import localSPs.CubbyAPI;
import localSPs.MegaAPI;
import localSPs.SpiderOakAPI;
import oneDrive.OneDriveAPI;
import yandexDisk.YandexDiskAPI;

/**
 * This class is used for inisalizing the cloud APIs and the selection of appropriate
 * cloud functions(upload- delete etc) based on the cloud provider's name.
 * @author Marios Zinonos
 *
 */
public class CloudAPIs {
	String GLOBALL_PATH = "SPsCredentials/";
	String UPLOAD_PATH = "MainUserStorage/";
	private String spName = "";
	private ArrayList<String> spList = new ArrayList<String>();
	static ProgressMonitor pbar;
	AssetAwareSecurity ass;
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
	CubbyAPI cubbyAPI;

	public CloudAPIs(ArrayList<String> availableSPlist) throws IOException, CloudmeException {
		this.spList = availableSPlist;
		ass = new AssetAwareSecurity();
		gui = ass.getGui();
		this.frame = gui.myframe;
		
		//create monitor bar
		pbar = new ProgressMonitor(frame, "Loading Providers",
		           "Initializing . . .", 0, spList.size());
		int progress = 0;
		pbar.setProgress(progress);
		// Initialise APIs
		for (int i = 0; i < spList.size(); i++) {
			authServiceProvider(spList.get(i));
			progress +=1;
			pbar.setProgress(progress);
			pbar.setNote("Loaded " + progress + " Provider(s)");
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
		case "SpiderOak (?)":
			spiderAPI = new SpiderOakAPI();
			spiderAPI.UPLOAD_PATH = this.UPLOAD_PATH;
			authSpiderOak(name);
			break;
		case "BearDataShare (?)":
			bearAPI = new BearDataShareAPI();
			bearAPI.UPLOAD_PATH = this.UPLOAD_PATH;
			authBearDataShare(name);
			break;
		case "MEGA (?)":
			megaAPI = new MegaAPI();
			megaAPI.UPLOAD_PATH = this.UPLOAD_PATH;
			authMEGA(name);
			break;
		case "Cubby (?)":
			cubbyAPI = new CubbyAPI();
			cubbyAPI.UPLOAD_PATH = this.UPLOAD_PATH;
			authCubby(name);
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
		case "SpiderOak (?)":
			spiderOAkUploadDelete(fileName, isUpload);
			break;
		case "BearDataShare (?)":
			bearDataShareUploadDelete(fileName, isUpload);
			break;
		case "MEGA (?)":
			megaUploadDelete(fileName, isUpload);
			break;
		case "Cubby (?)":
			cubbyUploadDelete(fileName,isUpload);
			break;
		default:
			throw new IllegalArgumentException("Invalid UploadDelete ");
		}
		
	}
	
	public double getStorageSize( String spName) throws Exception{
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
		case "SpiderOak (?)":
			memory=spiderAPI.getStorageSize();
			break;
		case "BearDataShare (?)":
			memory=bearAPI.getStorageSize();
			break;
		case "MEGA (?)":
			memory=megaAPI.getStorageSize();
			break;
		case "Cubby (?)":
			memory = cubbyAPI.getStorageSize();
			break;
		default:
			throw new IllegalArgumentException("Invalid UploadDelete ");
		}
		return memory;
		
	}


	private void authBearDataShare(String name) throws IOException {
		authLocalSPs(name);
		
	}

	private void authSpiderOak(String name) throws IOException {
		authLocalSPs(name);	
	}
	
	private void authCubby(String name) throws IOException {
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
			if (name.equals("SpiderOak (?)")) {
				spiderAPI.login(loginPath);
			} else if (name.equals("BearDataShare (?)")) {
				bearAPI.login(loginPath);
			} else if (name.equals("MEGA (?)")) {
				megaAPI.login(loginPath);
			} else if (name.equals("Cubby (?)")){
				cubbyAPI.login(loginPath);
			}
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
					if (name.equals("SpiderOak (?)")) {
						spiderAPI.createNewLogin(clientFolder);
					} else if (name.equals("BearDataShare (?)")) {
						bearAPI.createNewLogin(clientFolder);
					} else if (name.equals("MEGA (?)")) {
						megaAPI.createNewLogin(clientFolder);
					} else if (name.equals("Cubby (?)")){
						cubbyAPI.createNewLogin(clientFolder);
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
			workingSPs.add(name);
		}else{
			url = dropBoxAPI.getURL();
			authDealog(name, url);
			returnString = obj.returnString;
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
		}
		

	}
	
	private void authGoogleDrive(String name) {
		String temp = "StoredCredential"; 
		File f = new File(GLOBALL_PATH + temp);
		if (f.exists() && !f.isDirectory()) {
			try {
				googleAPI.login();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());;
			}
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
				cloudMeUser = cloudMeAPI.login(GLOBALL_PATH + name + "Login.txt");
				workingSPs.add(name);
			} catch (CloudmeException e) {
				JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
			}
		} else {
			url = "";
			authDealog(name, url);
			returnString = obj.returnString;
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
	
	private void cubbyUploadDelete(String filePath, Boolean isUpload) throws IOException {
		if(isUpload){
			cubbyAPI.uploadFile(filePath);
		}else{
			cubbyAPI.deleteFile(filePath);
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
    	   File newFile = new File(folderTo + fileName);
    	   FileUtils.copyFile(file, newFile);
    	   file.delete(); //delete the file in mainStorage
       }else{//delete folder from local storage
    	   File delfile = new File(folderTo+fileName);
    	 
    	   if (delfile.exists()) {
    		   delfile.delete();
           }
       }
	}
	
	
	// Create the dialog
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