package assetawaresecurity;

import static java.nio.file.StandardWatchEventKinds.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

/**
 * 
 * This class is used to monitor any changes (modification, deletion, creation) on the files of the user.
 * This will allow as to adapt the security of the assets of the user according to the modifications performed and the runtime security requirements associate with the changes
 * if a file is created by a user, the system automatically creates an xml security requirements file for the system
 *
 *@author Giannis Tzakouris, Marios Zinonos
 */

public class MonitorAssets {
	
	//this variables are used to calculate expected file size depending on the size of each file
	private final double UP_TO_150MB=0.5, BETWEEN_150MB_500MB=0.3,  BETWEEN_500MB_1GB=0.2, MORE_THAN_1GB=0.07;


	public void monitorFileDeletion() throws IOException{
		WatchService watcher = FileSystems.getDefault().newWatchService();
		Path dir = Paths.get("LocalStorage");
		try {
		    WatchKey key = dir.register(watcher,ENTRY_DELETE); // Trigger when delete occurs
		} catch (IOException x) {
		    System.err.println(x);
		}
	}


	public void monitorFileChanges() throws IOException{ 
		//create the watcher
		WatchService watcher = FileSystems.getDefault().newWatchService();

		Path dir = Paths.get("MainUserStorage");
		//assign watcher
		try {
			//no need for modify key as every time a file is modified is recreated. Plus no need for deleted files, we do not care about them
			WatchKey key = dir.register(watcher,ENTRY_CREATE);

		} catch (IOException x) {
			System.err.println(x);
		}

		while (true) {
			WatchKey key;
			try {
				// wait for a key to be available
				key = watcher.take();	        
			} catch (InterruptedException ex) {
				return;
			}

			for (WatchEvent<?> event : key.pollEvents()) {
				// get event type
				WatchEvent.Kind<?> kind = event.kind();

				// get file name
				WatchEvent<Path> ev = (WatchEvent<Path>) event;
				Path fileName = ev.context();
				File conf = new File("UserDatabase/user.xml");
				if (kind == OVERFLOW) {
					continue;
				} else if ( (kind == ENTRY_CREATE) && !(fileName.toString().contains("~")) && conf.exists() && Gui.bid!=null) { //we do not want to check for temp files. Thus we omit all files that have ~, which indicates temp file 
					File file = new File("MainUserStorage/"+fileName.toString());
					double filesize=file.length();
					filesize=filesize/1024/1024;//convert it to MB
					double ExpectedFileSize=0;
				
					if (filesize <= 150){ // if the file size is less that 150 MB
						ExpectedFileSize=((filesize)+ (filesize * UP_TO_150MB)); //Will add an extra size of 50%
					}else if (filesize <= 500){ // if the file size is less that 500 MB
						ExpectedFileSize=((filesize)+ (filesize * BETWEEN_150MB_500MB)); //Will add an extra size of 30%
					}else if (filesize <= 1024){ // if the file size is less that 1GB
						ExpectedFileSize=((filesize)+ (filesize * BETWEEN_500MB_1GB)); //Will add an extra size of 20%
					}else if (filesize > 1024){ // if the file size is more that 1GB
						ExpectedFileSize=((filesize)+ (filesize * MORE_THAN_1GB)); //Will add an extra size of 7%
					}
					
					DecimalFormat df = new DecimalFormat("0.0000");
					String TruncExpectFileSize = df.format(ExpectedFileSize); //truncates the expected file size to 4 decimal points
					
					Gui.bid.setFileName(fileName.toString());
					Gui.bid.setExpectedFileSize(String.valueOf(TruncExpectFileSize));
					
					String TruncFileSize = df.format(filesize); //truncates the actual file size to 4 decimal points
					Gui.bid.setFileSize(String.valueOf(TruncFileSize));
						
					Double EstimateTime=0.0;
					int result=10;
					//check file upload time ...if long ask user if wants to proceed
					//this just considers the file size to approximate the upload time in case that the upload speed has not been yet initialized
					if( (InitializeSystem.uploadBandwidth==null) && (Double.valueOf(Gui.bid.getFileSize()) >300)){
						
						result = JOptionPane.showConfirmDialog(null,
								" The file is too big and will take some time to upload. Do you want to procceed?", "alert",
								JOptionPane.OK_CANCEL_OPTION);
					
					}else if(InitializeSystem.uploadBandwidth!=null){ //calculates an approximation of the time (in hours) needed to upload a file to the provider
						EstimateTime=((Double.valueOf(Gui.bid.getFileSize()) / (Double.valueOf(InitializeSystem.uploadBandwidth)/8) ) /360);
						
						if(EstimateTime>17){
							DecimalFormat f = new DecimalFormat("0.0");
							String ExpectTime = f.format(EstimateTime); //truncates the expected file size to 4 decimal points
							result = JOptionPane.showConfirmDialog(null,
									"The file is too big and will approximately take " +ExpectTime+ " hours to upload it. Do you want to procceed?", "alert",
									JOptionPane.OK_CANCEL_OPTION);
						}
					}
					
					
					if (result == JOptionPane.CANCEL_OPTION) {
						JOptionPane.showMessageDialog(null, Gui.bid.getFileName()+" will not be uplaoded");
					} else{
						ForwardBidToServer();
					}

				}else if(!conf.exists()){
					JOptionPane.showMessageDialog(Gui.myframe,"Please create a user account before trying uploading a file");
					key.pollEvents();
					break;
				}else if (Gui.bid == null){
					JOptionPane.showMessageDialog(Gui.myframe,"Please select a security policy before trying uploading a file");
					key.pollEvents();
					break;
				}
			}

			// reset key for next event
			boolean valid = key.reset();
			if (!valid) {
				break;
			}
		}

	}
	
	private String getServer(){
		BufferedReader br = null;
		String sCurrentLine = null;
		try {

			br = new BufferedReader(new FileReader("Network/serverIP.txt"));

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}

		}catch(Exception e){
			System.out.println(e.getMessage());
		} 
		return sCurrentLine;
		
	}

	//client side for forwarding bid to auctioneer
	public void ForwardBidToServer(){

		String hostName = getServer();
		int portNumber = 1234;
		String AuctioneerResponse;

		try{
			Socket clientSocket = new Socket(hostName, portNumber);
			ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			outToServer.writeObject(Gui.bid);
			outToServer.flush();

			AuctioneerResponse = inFromServer.readLine();
			
			//send matched data to be printed on Asset tab in GUI
			List<String> splitdata = Arrays.asList(AuctioneerResponse.split(",")); //splits the answer of auctioneer, contains name of SP and price
			
			//check which security policy was selected by the user
			if (Gui.isMax==true){
				Gui.bid.setSelectedPolicy("High Security Policy");
			}else if (Gui.isMed==true){
				Gui.bid.setSelectedPolicy("Medium Security Policy");
			}else if (Gui.isLow==true){
				Gui.bid.setSelectedPolicy("Low Security Policy");
			}else if (Gui.isCustomPanel==true){
				Gui.bid.setSelectedPolicy("Custom Security Policy");
			}
						
			Gui.UpdateAssetTab(Gui.bid.getFileName(), Gui.bid.getFileSize(), Gui.bid.getSelectedPolicy(), splitdata.get(0), splitdata.get(1));
			clientSocket.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}


}