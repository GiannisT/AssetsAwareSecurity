package assetawaresecurity;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JOptionPane;

/**
 * This class is responsible for calculating and matching the most optimal service based
 * on user's requirements.
 * 
 * @author Giannis Tziakouris, Marios Zinonos
 *
 */
public class MatchingCalculator {

	double SecurityUtility=0, cost=0;
	String SPname="";
	//holds the security and cost utility calculated for each SP along with all the mismatched requirements
	ArrayList <MatchingCalculator> SPcalculations;
	ArrayList<CreateAsk> asks;


	//getters
	public String getSPname(){
		return SPname;
	}
	public double getSecurityUtility(){
		return SecurityUtility; 
	}
	
	public double getCost(){
		return cost;
	}


	//setters
	public void setSPname(String name){
		SPname=name;
	}
	public void setSecurityUtility(double sec){
		SecurityUtility=sec; 
	}

	public void setCost (double cost){
		this.cost = cost;
	}


	//this function is called when a change in files take place and new requirements are formed
	public void MatchBid(CreateBid bid, String file){
		//the returned arraylist contains the set of SPs that are suitable for the certain user (performed tradeoff analysis)
		ArrayList<CreateAsk> SanitizedSPs=this.PreliminaryElimination(bid);  

		this.CalculateSecurity(bid, SanitizedSPs, file); //this function calculates/ranks the order of offers to final an optimal solution for the user based on security preferences (SAW algorithm)
	}



	//checks to determine if there are any unfit offers for the user to remove them. this enables higher efficiency and tradeoff mitigation between
	//costs (monetary-file size) and security
	public ArrayList<CreateAsk> PreliminaryElimination(CreateBid bid){

		CreateAsk obj=new CreateAsk();
		ArrayList<CreateAsk> suitableAsks = new ArrayList<CreateAsk>();

		asks=obj.getAsks(); //retrieves all available SP offers
		double SPLimitSize=0;

		for (int i=0; i<asks.size();i++){//check the offers of all available SPs to determine if they are suitable for a users bid  

			boolean condition=false;

			if(bid.getCost().equals("/")){ //indicates that a user does not care about the cost
			}else if(Double.valueOf(bid.getCost().trim()) >= Double.valueOf(asks.get(i).getCost().trim())){ //in case that the cost that a user is willing to pay, is violated we remove the certain Sp
				condition=true;
			}else if (Double.valueOf(bid.getCost().trim()) < Double.valueOf(asks.get(i).getCost().trim())){
				continue;
			}

			//this functions checks if the expected file size will be smaller than the upload file size limit and storage size, hence to make decisions that can hold over long periods of time
			Double AvailStorageSize=0.0;

			//returns the size of available storage for each SP in question
			switch(asks.get(i).getSpName()){
			case "BearDataShare": 
				AvailStorageSize=bid.getSPsSize().get("BearDataShare (?)");
				break;
			case "CloudMe":
				AvailStorageSize=bid.getSPsSize().get("CloudMe");
				break;
			case "Cubby":
				AvailStorageSize=bid.getSPsSize().get("Cubby (?)");
				break;
			case "DropBox":
				AvailStorageSize=bid.getSPsSize().get("DropBox");
				break;
			case "GoogleDrive":
				AvailStorageSize=bid.getSPsSize().get("GoogleDrive");
				break;
			case "MEGA":
				AvailStorageSize=bid.getSPsSize().get("MEGA (?)");
				break;
			case "OneDrive":
				AvailStorageSize=bid.getSPsSize().get("OneDrive");
				break;
			case "SpiderOak":
				AvailStorageSize=bid.getSPsSize().get("SpiderOak (?)");
				break;
			case "YandexDisk":
				AvailStorageSize=bid.getSPsSize().get("YandexDisk");
				break;
			default:
				JOptionPane.showMessageDialog(null, "Error Server side: " + asks.get(i).getSpName() +" is not a provider");;
			}


			if(asks.get(i).getFileSizeLimit().equals(" ")){ //if upload file size limit is unlimited give a huge number so the test will pass
				SPLimitSize=100000000000000000000000000.0;
			}else{
				SPLimitSize=Double.valueOf(asks.get(i).getFileSizeLimit());
			}

			if( (condition==true) && (Double.valueOf(bid.getExpectedFileSize())<= AvailStorageSize) && (Double.valueOf(bid.getExpectedFileSize()) <= SPLimitSize)){
				suitableAsks.add(asks.get(i)); //in case that the storage provided is less than the current file size, remove SP offer. No need to check for current file size as we considering a bigger size already
				}else{
				}
			}

		return suitableAsks;
	}



	//need to check: contains, re-initialization of values and continues...print all data after the for loop ends to see what is happening
	public void CalculateSecurity(CreateBid bid,  ArrayList<CreateAsk> SuitableSPs, String file){  


		SPcalculations=new ArrayList<MatchingCalculator>(); //re-instantiated in order to be empty the next time we need to calculate the utility for a new bid/request
		//CreateAsk obj=new CreateAsk();
		MatchingCalculator util;
		double high=10, low=5;

		for (int i=0; i<SuitableSPs.size();i++){//for all available SPs for a user, calculate security and cost utility
			//initialize variables for calculating the utility values of each SP
			SecurityUtility=0; 
			util=new MatchingCalculator();
			int count = 0;

			double weight1=0;
			if (bid.getEncryptionAtRest().equals("/")){ 
				count++;
			}else if(SuitableSPs.get(i).getEncryptionAtRest().toLowerCase().contains(bid.getEncryptionAtRest().toLowerCase())){ //if it satisfies the requirement assign a big value if not a small one
				weight1=Double.valueOf(bid.getSignificance().get("EncryptionAtRest"));
				SecurityUtility= SecurityUtility + (high*weight1);
				count++;
			}else{
				weight1=Double.valueOf(bid.getSignificance().get("EncryptionAtRest"));
				SecurityUtility= SecurityUtility + (low*weight1);	 
			}

			double weight2=0;
			if(bid.getEncryptionAtTransit().equals("/")){
				count++;
			}else if(SuitableSPs.get(i).getEncryptionAtTransit().toLowerCase().contains(bid.getEncryptionAtTransit().toLowerCase())){
				weight2= Double.valueOf(bid.getSignificance().get("EncryptionAtTransit")); 
				SecurityUtility= SecurityUtility + (high*weight2);
				count++;
			}else{
				weight2= Double.valueOf(bid.getSignificance().get("EncryptionAtTransit")); 
				SecurityUtility= SecurityUtility + (low*weight2); 
			}
			
			double weight3=0;
			if(bid.getPassProtected().equals("/")){
				count++;
			}else if(SuitableSPs.get(i).getPassProtected().toLowerCase().contains(bid.getPassProtected().toLowerCase())){
				weight3=Double.valueOf(bid.getSignificance().get("PasswordProtectedFiles"));  
				SecurityUtility= SecurityUtility + (high*weight3);
				count++;

			}else{
				weight3=Double.valueOf(bid.getSignificance().get("PasswordProtectedFiles"));  
				SecurityUtility= SecurityUtility + (low*weight3);
			}

			double weight4=0;
			if(bid.getFileVersioning().equals("/")){
				count++;
			}else if(SuitableSPs.get(i).getFileVersioning().toLowerCase().contains(bid.getFileVersioning().toLowerCase())){
				weight4=Double.valueOf(bid.getSignificance().get("FileVersioning"));
				SecurityUtility= SecurityUtility + (high*weight4);
				count++;
			}else{
				weight4=Double.valueOf(bid.getSignificance().get("FileVersioning"));
				SecurityUtility= SecurityUtility + (low*weight4);
			}

			double weight5=0;
			if(bid.getConcealedKeys().equals("/")){
				count++;
			}else if(SuitableSPs.get(i).getConcealedKeys().toLowerCase().contains(bid.getConcealedKeys().toLowerCase())){
				weight5=Double.valueOf(bid.getSignificance().get("EncryptionKeysConcealedFromSP"));
				SecurityUtility= SecurityUtility + (high*weight5);
				count++;
			}else{
				weight5=Double.valueOf(bid.getSignificance().get("EncryptionKeysConcealedFromSP"));
				SecurityUtility= SecurityUtility + (low*weight5);
			}

			double weight6=0;
			if(bid.getAutoSynch().equals("/")){
				count++;
			}else if(SuitableSPs.get(i).getAutoSynch().toLowerCase().contains(bid.getAutoSynch().toLowerCase())){
				weight6=Double.valueOf(bid.getSignificance().get("AutoSynch"));
				SecurityUtility= SecurityUtility + (high*weight6);
				count++;
			}else{
				weight6=Double.valueOf(bid.getSignificance().get("AutoSynch"));
				SecurityUtility= SecurityUtility + (low*weight6);
			}

			double weight7=0;
			if( bid.getSPLocation().equals("/") || bid.getSPLocation().equals("Datacenter Location") ){
				count++;
			}else if(SuitableSPs.get(i).getSPLocation().toLowerCase().contains(bid.getSPLocation().toLowerCase())){
				weight7=Double.valueOf(bid.getSignificance().get("DatacenterLocation"));
				SecurityUtility=SecurityUtility + (high*weight7);
				count++;
			}else{
				weight7=Double.valueOf(bid.getSignificance().get("DatacenterLocation"));
				SecurityUtility=SecurityUtility + (low*weight7);
			}

			double weight8=0;
			if(bid.getSecKeyManagement().equals("/")){
				count++;
			}else if(SuitableSPs.get(i).getSecKeyManagement().toLowerCase().contains(bid.getSecKeyManagement().toLowerCase())){
				weight8=Double.valueOf(bid.getSignificance().get("SecureKeyManagement"));
				SecurityUtility=SecurityUtility + (high*weight8);
				count++;
			}else{
				weight8=Double.valueOf(bid.getSignificance().get("SecureKeyManagement"));
				SecurityUtility=SecurityUtility + (low*weight8);
			}

			double weight9=0;
			if(bid.getPassRecovery().equals("/")){
				count++;
			}else if(SuitableSPs.get(i).getPassRecovery().toLowerCase().contains(bid.getPassRecovery().toLowerCase())){
				weight9=Double.valueOf(bid.getSignificance().get("CredentialRecovery"));
				SecurityUtility=SecurityUtility + (high*weight9);
				count++;
			}else{
				weight9=Double.valueOf(bid.getSignificance().get("CredentialRecovery"));
				SecurityUtility=SecurityUtility + (low*weight9);
			}

			double weight10=0;
			if(bid.getShareData().equals("/")){
				count++;
			}else if(SuitableSPs.get(i).getDataShare().toLowerCase().contains(bid.getShareData().toLowerCase())){
				weight10=Double.valueOf(bid.getSignificance().get("ShareData"));
				SecurityUtility=SecurityUtility + (high*weight10);
				count++;
			}else{
				weight10=Double.valueOf(bid.getSignificance().get("ShareData"));
				SecurityUtility=SecurityUtility + (low*weight10);
			}

			double weight11=0;
			if(bid.getAuditLogs().equals("/")){
				count++;
			}else if(SuitableSPs.get(i).getAuditLogs().toLowerCase().contains(bid.getAuditLogs().toLowerCase())){
				weight11=Double.valueOf(bid.getSignificance().get("AuditLogs"));
				SecurityUtility=SecurityUtility + (high*weight11);
				count++;
			}else{
				weight11=Double.valueOf(bid.getSignificance().get("AuditLogs"));
				SecurityUtility=SecurityUtility + (low*weight11);
			}

			double weight12=0;
			if(bid.getProxySupport().equals("/")){
				count++;
			}else if(SuitableSPs.get(i).getProxySupport().toLowerCase().contains(bid.getProxySupport().toLowerCase())){
				weight12=Double.valueOf(bid.getSignificance().get("ProxySupport"));
				SecurityUtility=SecurityUtility + (high*weight12);
				count++;
			}else{
				weight12=Double.valueOf(bid.getSignificance().get("ProxySupport"));
				SecurityUtility=SecurityUtility + (low*weight12);
			}

			double weight13=0;
			if(bid.getDifferentKeyPerFile().equals("/")){
				count++;
			}else if(SuitableSPs.get(i).getDifferentKeyPerFile().toLowerCase().contains(bid.getDifferentKeyPerFile().toLowerCase())){
				weight13=Double.valueOf(bid.getSignificance().get("DifferentKeyPerFile"));
				SecurityUtility=SecurityUtility + (high*weight13);
				count++;
			}else{
				weight13=Double.valueOf(bid.getSignificance().get("DifferentKeyPerFile"));
				SecurityUtility=SecurityUtility + (low*weight13);
			}

			double weight14=0;
			if(bid.getCertification().equals("/")){
				count++;
			}else{ // Check for perfect match of the certification provided by user
				String [] parts;
				boolean perfMatch = false;
				parts = bid.getCertification().split(",");
				for (int k = 0; k<parts.length;k++){
					if(SuitableSPs.get(i).getCertification().toLowerCase().trim().contains(parts[k].toLowerCase().trim())){
						perfMatch = true;
					} else{
						perfMatch = false;
						break;
					}

				}
				if(perfMatch==true){
					weight14=Double.valueOf(bid.getSignificance().get("Certification"));
					SecurityUtility=SecurityUtility + (high*weight14);
					count++;
				}else{
					weight14=Double.valueOf(bid.getSignificance().get("Certification"));
					SecurityUtility=SecurityUtility + (low*weight14);
				}
			}

			double weight15=0;
			if(bid.getPermanentDeletion().equals("/")){
				count++;
			}else if(SuitableSPs.get(i).getPermanentDeletion().toLowerCase().contains(bid.getPermanentDeletion().toLowerCase())){
				weight15=Double.valueOf(bid.getSignificance().get("PermanentFileDeletion"));
				SecurityUtility=SecurityUtility + (high*weight15);
				count++;
			}else{
				weight15=Double.valueOf(bid.getSignificance().get("PermanentFileDeletion"));
				SecurityUtility=SecurityUtility + (low*weight15);
			}

			//storing the calculated values and mismatched occurrences for the certain SP

			util.setSecurityUtility(SecurityUtility);
			util.setSPname(SuitableSPs.get(i).getSpName());
			util.setCost(Double.valueOf(SuitableSPs.get(i).getCost()));

			//First if statement is for explicit sec type else if the requirement type is yes it means its implicit   
			//all data for the current SP are stored and prepared for auctioning
			
			if(bid.getRequirementsType().equalsIgnoreCase("no") && count >=15){ //TO LATHOS INE DAME TO 15 PREPI NA ALLASI ANALOGA ME TI INE SELECTED
				SPcalculations.add(util);
			}else if(bid.getRequirementsType().equalsIgnoreCase("yes")){
				SPcalculations.add(util);
			}
		}

		//test
		double higher=0, base =0;
		String winnerSP=" ";
		
		// If it is explicit then get the cheepest one
		if(bid.getRequirementsType().equalsIgnoreCase("no")){ // no means it is explicit
			if(!SPcalculations.isEmpty()){ // Check if list has explicit entries
				base = SPcalculations.get(0).getCost();
				winnerSP = SPcalculations.get(0).getSPname();
				higher=SPcalculations.get(0).getSecurityUtility();
				for (int i=0; i<SPcalculations.size();i++){
					if (SPcalculations.get(i).getCost() < base){ // move to cheapest option
						winnerSP = SPcalculations.get(i).getSPname();	
						higher=SPcalculations.get(i).getSecurityUtility();
					}
				}
			}
		}else { // bid is implicit
			double costThres = 0;
			for (int i=0; i<SPcalculations.size();i++){ // Get the winner with the most Utility points
				if (higher<SPcalculations.get(i).getSecurityUtility()){
					higher=SPcalculations.get(i).getSecurityUtility();
					winnerSP=SPcalculations.get(i).getSPname();
					costThres = SPcalculations.get(i).getCost();
				}
			}
			for(int i=0;i<SPcalculations.size();i++){// Check if there is a cheaper option within the threshold
				if(SPcalculations.get(i).getSecurityUtility() >= (higher-5) && !SPcalculations.get(i).getSPname().equals(winnerSP)){
					if(SPcalculations.get(i).getCost() < costThres){
						winnerSP = SPcalculations.get(i).getSPname(); // set new winner and cost threshold
						costThres = SPcalculations.get(i).getCost();
					}
				}
			}
		}
		
		Auctioneer.matchResult=" ";
		String cost="0";
		if (higher!=0){ //if an available optimal SP was found

			for(int i=0; i<asks.size(); i++){
				if(asks.get(i).getSpName().equals(winnerSP)){
					cost=asks.get(i).getCost();
					break;
				}
			}
			Auctioneer.matchResult=winnerSP+","+cost+ '\n';
		}else{
			Auctioneer.matchResult="Store Locally"+","+ "N/A"+'\n';
		}

	}


}