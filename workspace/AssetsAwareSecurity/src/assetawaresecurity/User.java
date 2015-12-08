package assetawaresecurity;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileDeleteStrategy;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class User {

    String name = "", surname = "";
    int userId = 0;
        //ArrayList<String> SPlist = new ArrayList<String>();

    //will hold all available SPs for a user...order DropBox, GoogleDrive, YandexDisk, CloudMe, OneDrive
    Map<String, Boolean> AvailableSp = new HashMap<String, Boolean>();

//    User(String name, String surname, String email, Map<String, Boolean> spList) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    public void setName(String userName) {
        name = userName;
    }

    public void setSurname(String userSurname) {
        surname = userSurname;
    }

    public void setUserId(int id) {
        userId = id;
    }

    //holds all the SPs that a user has account in
    public void setAvailableSP(Map<String, Boolean> Sp) {
        this.AvailableSp = Sp;
    }
//        
//        public void setSPList(ArrayList<String> list){
//            this.SPlist = list;
//        }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }


    public int getUserId() {
        return userId;
    }

//        public ArrayList<String> getSPList(){
//            return this.SPlist;
//        }
    public Map<String, Boolean> getAvailableSP() {
        return AvailableSp;
    }

	//creates the main directory for the user to locally keep his files
    //creates the xml config file for the user, if it uses it for the first time
    public User() {

    }

    public User(String usrName, String usrSurname, Map<String, Boolean> list) {

        //creates a user s config file!!
        this.userId++;
        this.name = usrName;
        this.surname = usrSurname;
        this.AvailableSp = list;

        File f = new File("UserDatabase/user.xml");

        if (!(f.exists())) {
            try {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.newDocument();

                // root element
                Element rootElement = doc.createElement("userdata");
                doc.appendChild(rootElement);

                Element user = doc.createElement("user");
                rootElement.appendChild(user);

                // setting attribute to element
                Attr attr = doc.createAttribute("id");
                attr.setValue(Integer.toString(userId));
                user.setAttributeNode(attr);

                Element nameElm = doc.createElement("name");
                nameElm.appendChild(doc.createTextNode(name));
                user.appendChild(nameElm);

                Element surnameElm = doc.createElement("surname");
                surnameElm.appendChild(doc.createTextNode(surname));
                user.appendChild(surnameElm);


                Set set = AvailableSp.entrySet();
                Iterator i = set.iterator();

                while (i.hasNext()) { //appending available SPs, their names and status
                    Map.Entry me = (Map.Entry) i.next();
                    Element sp = doc.createElement("AvailableSP");
                    Attr attr1 = doc.createAttribute("Name");
                    attr1.setValue(me.getKey().toString());
                    sp.setAttributeNode(attr1);
                    sp.appendChild(doc.createTextNode(me.getValue().toString()));
                    user.appendChild(sp);
                }

                // write the content into xml file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File("UserDatabase/user.xml"));
                transformer.transform(source, result);

                // Output to console for testing
                StreamResult consoleResult = new StreamResult(System.out);
                transformer.transform(source, consoleResult);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    //if the user is already created it reads the config file to initialize the object User
    public void InitialiseUser() {
        File f = new File("UserDatabase/user.xml"); // not needed

        if (f.exists()) {
            try {
                File inputFile = new File("UserDatabase/user.xml"); // need to be send over?
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(inputFile);
                doc.getDocumentElement().normalize();
                NodeList nList = doc.getElementsByTagName("user");

                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    System.out.println("\nCurrent Element :" + nNode.getNodeName());

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;

                        System.out.println("User id : " + eElement.getAttribute("id"));
                        this.setUserId(Integer.valueOf(eElement.getAttribute("id")));

                        System.out.println("Name: " + eElement.getElementsByTagName("name").item(0).getTextContent());
                        this.setName(eElement.getElementsByTagName("name").item(0).getTextContent());

                        System.out.println("Surname: " + eElement.getElementsByTagName("surname").item(0).getTextContent());
                        this.setSurname(eElement.getElementsByTagName("surname").item(0).getTextContent());

                        // test
                        try {//check the availableSPs

                            int k = 0;
                            NodeList listNodes = eElement.getElementsByTagName("AvailableSP");
                            System.out.println(listNodes.getLength());
                            for (int j = 0; j < listNodes.getLength(); j++) {
                                System.out.println("Eimai mesa");
                                AvailableSp.put(eElement.getElementsByTagName("AvailableSP").item(j).getAttributes().item(0).getNodeValue(), Boolean.valueOf(eElement.getElementsByTagName("AvailableSP").item(j).getTextContent()));
                                System.out.println(AvailableSp.keySet());
                            }

                        } catch (IndexOutOfBoundsException e) {
                            e.getMessage();
                        }

                        this.setAvailableSP(AvailableSp);

                        //testCode...starts
                        Set set = AvailableSp.entrySet();
                        Iterator i = set.iterator();
                        while (i.hasNext()) {
                            Map.Entry me = (Map.Entry) i.next();
                            System.out.println(me.getKey().toString() + " : " + me.getValue().toString());
                        }
			         	  //testCode..ends

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

	//this function is deployed when a user clicks on remove existing user. Deletes all folders and config files
    //marios develop gui data removal (reset gui to initial state)?????????
    public void removeUser() {
        //File conf = new File("UserDatabase/Config.xml"); // used for new user
    	File conf = new File("UserDatabase/user.xml");
        File conf2 = new File("MainUserStorage");
        File conf3=new File("customPolicy.xml");
        File conf4=new File("customPolicyWeights.xml");
        File spFolder = new File("SPsCredentials");
        File lastPolicy=new File("lastPolicy.xml");
        File weightLastPolicy=new File("lastPolicyWeights.xml");
        
        try {
            if (conf.exists()) {
            	FileDeleteStrategy.FORCE.delete(conf);
                System.out.println("DeletedUser");
            }
            
            if (lastPolicy.exists()) {
            	FileDeleteStrategy.FORCE.delete(lastPolicy);
                System.out.println("DeletedLastPolicy");
            }
            
            if (weightLastPolicy.exists()) {
            	 FileDeleteStrategy.FORCE.delete(weightLastPolicy);
                System.out.println("DeletedLastPolicyWeights");
            }

            if (conf2.exists()) {
                System.out.println("DeletedMainUserStorage Contents");
                String[] entries = conf2.list();
                for (String s : entries) {
                    File currentFile = new File(conf2.getPath(), s);
                    FileDeleteStrategy.FORCE.delete(currentFile);
                }
            }
            
            if (conf3.exists()) {
                System.out.println("Deleted CustomPolicy");
                FileDeleteStrategy.FORCE.delete(conf3);
            }
            
            if (conf4.exists()) {
                System.out.println("Deleted CustomPolicyWeights");
                FileDeleteStrategy.FORCE.delete(conf4);
            }
            
            if(spFolder.exists()){
            	System.out.println("Deleted SPCredentials");
                String[] entries = spFolder.list();
                for (String s : entries) {
                    File currentFile = new File(spFolder.getPath(), s);
                    FileDeleteStrategy.FORCE.delete(currentFile);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}