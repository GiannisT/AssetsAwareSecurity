package assetawaresecurity;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;


/**
 * This is the server main method. it initiates the gui and opens a socket 
 * 
 * @author Giannis Tziakouris, Marios Zinonos
 */
public class Auctioneer implements Runnable {

	   Socket csocket;
	   DataOutputStream outToClient;
	   ObjectInputStream  inFromClient; 
	   static String matchResult=" ";
	   
	   
	   public Auctioneer(Socket csocket) {
	      this.csocket = csocket;
	   }

	
	   public void run() {
		   synchronized(csocket){
		   CreateAsk asks=new CreateAsk();
           MatchingCalculator match=new MatchingCalculator();

	         
           
	         try{
			    inFromClient = new ObjectInputStream(csocket.getInputStream());
	            outToClient = new DataOutputStream(csocket.getOutputStream());
	            Object clientbid;
	            
	            while (!inFromClient.equals(null)) {
	            	clientbid=inFromClient.readObject();
	            	CreateBid userBid =(CreateBid) clientbid;
	            	
	            	asks.retrieveOffers(userBid.getAvailableSPs());
	            	match.MatchBid(userBid, userBid.getFileName());
	            	outToClient.writeBytes(matchResult);
	            	outToClient.flush();
	            	
	        }
		
		   }catch (IOException | ClassNotFoundException e){}
	         
			try {
				outToClient.close();
				csocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		   }
		   
	   }
	   
	   
	   
	   public static void main(String args[]) throws Exception {
		   	  ServerGui gui=new ServerGui();	
		      ServerSocket ssock = new ServerSocket(1234);
		      System.out.println("Listening");
		     
		      while (true) {
		    	  
		         Socket sock = ssock.accept();
		         
		         System.out.println("Connected");
		         new Thread(new Auctioneer(sock)).start();
		         
		      }
	   }	
}
	

	