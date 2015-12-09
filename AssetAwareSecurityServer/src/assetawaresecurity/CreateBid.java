package assetawaresecurity;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * This class is used to read the user request for storage service and form a
 * bid for auctioning
 *
 */
public class CreateBid implements Serializable {

    String RequirementsType = "/", RestEncryption = "/", TransitEncryption = "/", PassProtecFiles = "/", ShareData = "/", ConcealedKeys = "/", PermanentDeletion = "/", AutoSynch = "/", FileExpectedSize = "/",
            SecKeyManagement = "/", PassRecovery = "/", FileVersioning = "/", AuditLogs = "/", ProxySupport = "/", DifferentKeyPerFile = "/", SpLocation = "/", Certification = "/", Cost = "/", FileSize = "/", fileName = "/",
            SelectedPolicy="/";


    Map<String, Double> Significance;  //describes the importance of each attribute, it will help us to calc weights for decision making
    
    ArrayList<String> availableSPs;
    
    public CreateBid(){ }

    public CreateBid(String RequirementsType, String RestEncryption, String TransitEncryption, String PassProtecFiles,String FileVersioning, String ConcealedKeys, String AutoSynch, String SecKeyManagement,
            String PassRecovery, String ShareData, String AuditLogs, String ProxySupport, String DifferentKeyPerFile, String PermanentDeletion, String SpLocation, String Certification, String Cost , String FileExpectedSize, 
            Double RestEncryptionWeight,Double TransitEncryptionWeight, Double PassProtecFilesWeight, Double FileVersioningWeight, Double ConcealedKeysWeight, Double AutoSynchWeight, Double SecKeyManagementWeight, Double PassRecoveryWeight,
            Double ShareDataWeight, Double AuditLogsWeight, Double ProxySupportWeight, Double DifferentKeyPerFileWeight,Double PermanentDeletionWeight, Double SpLocationWeight, Double CertificationWeight, ArrayList <String> availableSPs, String fileName, String SelectedPolicy) throws FileNotFoundException {

        Significance = new HashMap<String, Double>();

        try {

            System.out.println("Requirements Type: " + RequirementsType);
            // we will not consider attributes that the user does not care for them (that are left blank)
            this.setRequirementsType(RequirementsType);

            System.out.println("Encryption at Rest: " + RestEncryption);
            System.out.println("Weight: " + RestEncryptionWeight);
            // we will not consider attributes that the user does not care for them (that are left blank)
            if (!RestEncryption.equals(" ")) {
                this.setEncryptionAtRest(RestEncryption);
                Significance.put("EncryptionAtRest", RestEncryptionWeight);
            }

            System.out.println("Encryption at Transit: " + TransitEncryption);
            System.out.println("Weight: " + TransitEncryptionWeight);
            if (!TransitEncryption.equals(" ")) {
                this.setEncryptionAtTransit(TransitEncryption);
                Significance.put("EncryptionAtTransit", TransitEncryptionWeight);
            }

            System.out.println("Password Protected Files: " + PassProtecFiles);
            System.out.println("Weight: " + PassProtecFilesWeight);
            if (!PassProtecFiles.equals(" ")) {
                this.setPassProtected(PassProtecFiles);
                Significance.put("PasswordProtectedFiles", PassProtecFilesWeight);
            }

            System.out.println("FileVersioning: " + FileVersioning);
            System.out.println("Weight: " + FileVersioningWeight);
            if (!FileVersioning.equals(" ")) {
                this.setFileVersioning(FileVersioning);
                Significance.put("FileVersioning", FileVersioningWeight);
            }

            System.out.println("Encryption Keys Concealed From SP : " + ConcealedKeys);
            System.out.println("Weight: " + ConcealedKeysWeight);
            if (!ConcealedKeys.equals(" ")) {
                this.setConcealedKeys(ConcealedKeys);
                Significance.put("EncryptionKeysConcealedFromSP", ConcealedKeysWeight);
            }

            System.out.println("Auto Synch: " + AutoSynch);
            System.out.println("Weight: " + AutoSynchWeight);
            if (!AutoSynch.equals(" ")) {
                this.setAutoSynch(AutoSynch);
                Significance.put("AutoSynch", AutoSynchWeight);
            }

            System.out.println("Datacenter Location: " + SpLocation);
            System.out.println("Weight: " + SpLocationWeight);
            if (!SpLocation.equals(" ")) {
                this.setSPLocation(SpLocation);
                Significance.put("DatacenterLocation", SpLocationWeight);
            }

            System.out.println("Cost: " + Cost);
            if (!Cost.equals(" ")) {
                this.setCost(Cost);
            }

            System.out.println("Expected File Size: " + FileExpectedSize);
            if (!FileExpectedSize.equals(" ")) {
                this.setExpectedFileSize(FileExpectedSize);
            }

            System.out.println("SecureKeyManagement : " + SecKeyManagement);
            System.out.println("Weight: " + SecKeyManagementWeight);
            if (!SecKeyManagement.equals(" ")) {
                this.setSecKeyManagement(SecKeyManagement);
                Significance.put("SecureKeyManagement", SecKeyManagementWeight);
            }

            System.out.println("Credential Recovery: " + PassRecovery);
            System.out.println("Weight: " + PassRecoveryWeight);
            if (!PassRecovery.equals(" ")) {
                this.setPassRecovery(PassRecovery);
                Significance.put("CredentialRecovery", PassRecoveryWeight);
            }

            System.out.println("Share Data: " + ShareData);
            System.out.println("Weight: " + ShareDataWeight);
            if (!ShareData.equals(" ")) {
                this.setShareData(ShareData);
                Significance.put("ShareData", ShareDataWeight);
            }

            System.out.println("Audit Logs: " + AuditLogs);
            System.out.println("Weight: " + AuditLogsWeight);
            if (!AuditLogs.equals(" ")) {
                this.setAuditLogs(AuditLogs);
                Significance.put("AuditLogs", AuditLogsWeight);
            }

            System.out.println("Proxy Support: " + ProxySupport);
            System.out.println("Weight: " + ProxySupportWeight);
            if (!ProxySupport.equals(" ")) {
                this.setProxySupport(ProxySupport);
                Significance.put("ProxySupport", ProxySupportWeight);
            }

            System.out.println("Different Key Per File: " + DifferentKeyPerFile);
            System.out.println("Weight: " + DifferentKeyPerFileWeight);
            if (!DifferentKeyPerFile.equals(" ")) {
                this.setDifferentKeyPerFile(DifferentKeyPerFile);
                Significance.put("DifferentKeyPerFile", DifferentKeyPerFileWeight);
            }

            System.out.println("Certification: " + Certification);
            System.out.println("Weight: " + CertificationWeight);
            if (!Certification.equals(" ")) {
                this.setCertification(Certification);
                Significance.put("Certification", CertificationWeight);
            }

            System.out.println("Permanent File Deletion: " + PermanentDeletion);
            System.out.println("Weight: " + PermanentDeletionWeight);
            if (!PermanentDeletion.equals(" ")) {
                this.setPermanentDeletion(PermanentDeletion);
                Significance.put("PermanentFileDeletion", PermanentDeletionWeight);
            }
            
            this.setAvailableSPs(availableSPs);
            

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        this.SetSignificance(Significance); //add the significance of each attribute for the user to the bid

//        //store custom policy (bid) into an xml file
//        FileOutputStream fos = new FileOutputStream("customPolicy.xml");
//        BufferedOutputStream bos = new BufferedOutputStream(fos);
//        XMLEncoder xmlEncoder = new XMLEncoder(bos);
//        xmlEncoder.writeObject(this);
//        xmlEncoder.writeObject(this.getSignificance());
//        xmlEncoder.close();

    }



	//send the bid to calculate its expected utility for the user and match it with optimal offer we might need to add a socket to send it instead of a function?????
//	public void ForwardBid(){
//		MatchingCalculator calc = new MatchingCalculator();
//		calc.MatchBid(bid);
//	}
    //setters
    public void setRequirementsType(String type) {
        RequirementsType = type;
    }

    public void SetSignificance(Map<String, Double> weights) {
        Significance = weights;
    }

    public void setEncryptionAtRest(String enc) {
        RestEncryption = enc;
    }

    public void setEncryptionAtTransit(String encT) {
        TransitEncryption = encT;
    }

    public void setPassProtected(String pass) {
        PassProtecFiles = pass;
    }

    public void setShareData(String data) {
        ShareData = data;
    }

    public void setAutoSynch(String synch) {
        AutoSynch = synch;
    }

    public void setConcealedKeys(String conceal) {
        ConcealedKeys = conceal;
    }

    public void setPermanentDeletion(String delete) {
        PermanentDeletion = delete;
    }

    public void setSecKeyManagement(String secKey) {
        SecKeyManagement = secKey;
    }

    public void setPassRecovery(String rec) {
        PassRecovery = rec;
    }

    public void setFileVersioning(String rev) {
        FileVersioning = rev;
    }

    public void setAuditLogs(String logs) {
        AuditLogs = logs;
    }

    public void setProxySupport(String proxy) {
        ProxySupport = proxy;
    }

    public void setDifferentKeyPerFile(String keyPerFile) {
        DifferentKeyPerFile = keyPerFile;
    }

    public void setSPLocation(String location) {
        SpLocation = location;
    }

    public void setCertification(String cert) {
        Certification = cert;
    }

    public void setCost(String cost) {
        this.Cost = cost;
    }

    public void setFileSize(String size) {
        FileSize = size;
    }
    
    public void setFileName(String fileName){
    	this.fileName = fileName;
    }

    public void setExpectedFileSize(String expSize) {
        FileExpectedSize = expSize;
    }
    
    public void setAvailableSPs(ArrayList<String> avail){
    	availableSPs=avail;
    }
    
    public void setSelectedPolicy(String select){
    	SelectedPolicy=select;
    }
    
    //getters
    public String getRequirementsType() {
        return RequirementsType;
    }

    public Map<String, Double> getSignificance() {
        return Significance;
    }

    public String getEncryptionAtRest() {
        return RestEncryption;
    }

    public String getEncryptionAtTransit() {
        return TransitEncryption;
    }

    public String getPassProtected() {
        return PassProtecFiles;
    }

    public String getShareData() {
        return ShareData;
    }
    
    public String getFileName(){
    	return fileName;
    }

    public String getAutoSynch() {
        return AutoSynch;
    }

    public String getConcealedKeys() {
        return ConcealedKeys;
    }

    public String getPermanentDeletion() {
        return PermanentDeletion;
    }

    public String getSecKeyManagement() {
        return SecKeyManagement;
    }

    public String getPassRecovery() {
        return PassRecovery;
    }

    public String getFileVersioning() {
        return FileVersioning;
    }

    public String getAuditLogs() {
        return AuditLogs;
    }

    public String getProxySupport() {
        return ProxySupport;
    }

    public String getDifferentKeyPerFile() {
        return DifferentKeyPerFile;
    }

    public String getSPLocation() {
        return SpLocation;
    }

    public String getCertification() {
        return Certification;
    }

    public String getCost() {
        return this.Cost;
    }

    public String getFileSize() {
        return FileSize;
    }

    public String getExpectedFileSize() {
        return FileExpectedSize;
    }
    
    public ArrayList<String> getAvailableSPs(){
    	return availableSPs;
    }
    
    public String getSelectedPolicy(){
    	return SelectedPolicy;
    }

}