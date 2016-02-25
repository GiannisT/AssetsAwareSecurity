package assetawaresecurity;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * This class is used to read the Available SP offers for storage service and form an ask for auctioning
 * 
 * @author Giannis Tziakouris, Marios Zinonos
 */
public class CreateAsk {

	String SPname=" ",RestEncryption=" ", TransitEncryption=" ",PassProtecFiles=" ",FileVersioning=" ", ConcealedKeys=" ", PermanentDeletion=" ", AutoSynch=" ",FileSizeLimit=" ",
			SecKeyManagement=" ", PassRecovery =" ", ShareData=" ", AuditLogs=" ", ProxySupport=" ", DifferentKeyPerFile=" ", SpLocation=" ", Certification=" ", Cost=" ", StorageSize=" "; 

	static CreateAsk ask;

	// will hold all Sp offers from SPs
	static ArrayList<CreateAsk> SPoffers;


	//reads the SP xml offers for secure storage and initializes ask parameters
	public static void retrieveOffers(ArrayList<String> Asp){			
		//needs to find which SPs are available for a user and then obtain their offers
		//User us=new User();
		SPoffers=new ArrayList<CreateAsk>();
		
		for (int j=0; j<Asp.size(); j++){
			ask=new CreateAsk();

			try {
				File secReq = new File("MainSPStorage/"+Asp.get(j).toString()+".xml");
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(secReq);

				doc.getDocumentElement().normalize();	
				NodeList nList = doc.getElementsByTagName("SP");

				for (int i=0; i < nList.getLength(); i++) {

					Node nNode = nList.item(i);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) nNode;
						ask.setSpName(eElement.getElementsByTagName("SpName").item(0).getTextContent());
						ask.setEncryptionAtRest(eElement.getElementsByTagName("EncryptionAtRest").item(0).getTextContent());
						ask.setEncryptionAtTransit(eElement.getElementsByTagName("EncryptionAtTransit").item(0).getTextContent());
						ask.setPassProtected(eElement.getElementsByTagName("PasswordProtectedFiles").item(0).getTextContent());
						ask.setFileVersioning(eElement.getElementsByTagName("FileVersioning").item(0).getTextContent());
						ask.setConcealedKeys(eElement.getElementsByTagName("EncryptionKeysConcealedFromSP").item(0).getTextContent());
						ask.setAutoSynch( eElement.getElementsByTagName("AutoSynch").item(0).getTextContent());
						ask.setSPLocation(eElement.getElementsByTagName("DatacenterLocation").item(0).getTextContent());
						ask.setCost(eElement.getElementsByTagName("Cost").item(0).getTextContent());
						ask.setStorageSize(eElement.getElementsByTagName("StorageSize").item(0).getTextContent());
						ask.setFileSizeLimit(eElement.getElementsByTagName("FileSizeLimit").item(0).getTextContent());
						ask.setSecKeyManagement(eElement.getElementsByTagName("SecureKeyManagement").item(0).getTextContent());
						ask.setPassRecovery(eElement.getElementsByTagName("CredentialRecovery").item(0).getTextContent());
						ask.setShareData(eElement.getElementsByTagName("ShareData").item(0).getTextContent());
						ask.setAuditLogs(eElement.getElementsByTagName("AuditLogs").item(0).getTextContent());
						ask.setProxySupport(eElement.getElementsByTagName("ProxySupport").item(0).getTextContent());
						ask.setDifferentKeyPerFile(eElement.getElementsByTagName("DifferentKeyPerFile").item(0).getTextContent());
						ask.setCertification(eElement.getElementsByTagName("Certification").item(0).getTextContent());
						ask.setPermanentDeletion(eElement.getElementsByTagName("PermanentFileDeletion").item(0).getTextContent());
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println(e.getCause());
			}

			SPoffers.add(ask);//adds the offer of each SP to the list of offers
		}
	}



	//setters
	public void setSpName(String name){
		SPname=name;
	}
	public void setFileSizeLimit(String limit){
		FileSizeLimit=limit;
	}
	public void setEncryptionAtRest(String enc){
		RestEncryption=enc;
	}
	public void setEncryptionAtTransit(String encT){
		TransitEncryption=encT;
	}
	public void setPassProtected(String pass){
		PassProtecFiles=pass;
	}
	public void setShareData(String data){
		ShareData=data;
	}
	public void setAutoSynch(String synch){
		AutoSynch=synch;
	}
	public void setConcealedKeys(String conceal){
		ConcealedKeys=conceal;
	}
	public void setPermanentDeletion(String delete){
		PermanentDeletion=delete;
	}
	public void setSecKeyManagement(String secKey){
		SecKeyManagement=secKey;
	}
	public void setPassRecovery(String rec){
		PassRecovery=rec;
	}
	public void setFileVersioning(String ver){
		FileVersioning=ver;
	}
	public void setAuditLogs(String logs){
		AuditLogs=logs;
	}
	public void setProxySupport(String proxy){
		ProxySupport=proxy;
	}
	public void setDifferentKeyPerFile(String keyPerFile){
		DifferentKeyPerFile=keyPerFile;
	}
	public void setSPLocation(String location){
		SpLocation=location;
	}
	public void setCertification(String cert){
		Certification=cert;
	}
	public void setCost(String cost){
		this.Cost=cost;
	}
	public void setStorageSize(String size){
		StorageSize=size;
	}


	//getters

	//this will send all SP storage offers to the auctioneer for auctioning
	public ArrayList<CreateAsk> getAsks(){
		return SPoffers;
	}
	public String getSpName(){
		return SPname;
	}
	public String getFileSizeLimit(){
		return FileSizeLimit;
	}
	public String getEncryptionAtRest(){
		return RestEncryption;
	}
	public String getEncryptionAtTransit(){
		return TransitEncryption;
	}
	public String getPassProtected(){
		return PassProtecFiles;
	}
	public String getDataShare(){
		return ShareData;
	}
	public String getAutoSynch(){
		return AutoSynch;
	}
	public String getConcealedKeys(){
		return ConcealedKeys;
	}
	public String getPermanentDeletion(){
		return PermanentDeletion;
	}
	public String getSecKeyManagement(){
		return SecKeyManagement;
	}
	public String getPassRecovery(){
		return PassRecovery;
	}
	public String getFileVersioning(){
		return FileVersioning;
	}
	public String getAuditLogs(){
		return AuditLogs;
	}
	public String getProxySupport(){
		return ProxySupport;
	}
	public String getDifferentKeyPerFile(){
		return DifferentKeyPerFile;
	}
	public String getSPLocation(){
		return SpLocation;
	}
	public String getCertification(){
		return Certification;
	}
	public String getCost(){
		return this.Cost;
	}
	public String getStorageSize(){
		return StorageSize;
	}

}