/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetawaresecurity;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.dropbox.core.DbxException;
import com.sun.org.apache.bcel.internal.classfile.Field;

import Cloudme.CloudmeException;

/**
 *
 * @author methis
 */
public class Gui {
	// Variables declaration - do not modify//GEN-BEGIN:variables
		private JCheckBox AuditLogsCkBtn;
		private JRadioButton AuditLogsDont;
		private ButtonGroup AuditLogsGroup;
		private JRadioButton AuditLogsNo;
		private JComboBox AuditLogsWeight;
		private JRadioButton AuditLogsYes;
		private JCheckBox AutoSynchCkBtn;
		private JRadioButton AutoSynchDont;
		private ButtonGroup AutoSynchGroup;
		private JRadioButton AutoSynchNo;
		private JComboBox AutoSynchWeight;
		private JRadioButton AutoSynchYes;
		private JLabel CertificationLbl, redLbl, greenLbl, blueLbl, jLabel31;
		private JTextField CertificationTxt;
		private JComboBox CertificationWeight;
		static JFrame myframe;
		private JCheckBox ConcealedKeysCkBtn;
		private JRadioButton ConcealedKeysDont;
		private ButtonGroup ConcealedKeysGroup;
		private JRadioButton ConcealedKeysNo;
		private JComboBox ConcealedKeysWeight;
		private JRadioButton ConcealedKeysYes;
		private JTextField CostTxtCus, CostTxtFix;
		private JCheckBox DifferentKeyPerFileCkBtn, spiderOakCkb, spiderOakCkbRtn;
		private JRadioButton DifferentKeyPerFileDont;
		private ButtonGroup DifferentKeyPerFileGroup;
		private JRadioButton DifferentKeyPerFileNo;
		private JComboBox DifferentKeyPerFileWeight;
		private JRadioButton DifferentKeyPerFileYes;
		private JCheckBox FileVersioningCkBtn;
		private JRadioButton FileVersioningDont;
		private ButtonGroup FileVersioningGroup;
		private JRadioButton FileVersioningNo;
		private JComboBox FileVersioningWeight;
		private JRadioButton FileVersioningYes;
		private JCheckBox PassProtecFiles;
		private JRadioButton PassProtecFilesDont;
		private ButtonGroup PassProtecFilesGroup;
		private JRadioButton PassProtecFilesNo;
		private JComboBox PassProtecFilesWeight;
		private JRadioButton PassProtecFilesYes;
		private JCheckBox PassRecoveryCkBtn, cubbyCkbRtn, cubbyCkb, megaCkb, megaCkbRtn;
		private JRadioButton PassRecoveryDont;
		private ButtonGroup PassRecoveryGroup;
		private JRadioButton PassRecoveryNo;
		private JComboBox PassRecoveryWeight;
		private JRadioButton PassRecoveryYes;
		private JCheckBox PermanentDeletionCkBtn;
		private JRadioButton PermanentDeletionDont;
		private ButtonGroup PermanentDeletionGroup;
		private JRadioButton PermanentDeletionNo;
		private JComboBox PermanentDeletionWeight;
		private JRadioButton PermanentDeletionYes;
		private JCheckBox ProxySupportCkBtn;
		private JRadioButton ProxySupportDont;
		private ButtonGroup ProxySupportGroup;
		private JRadioButton ProxySupportNo;
		private JComboBox ProxySupportWeight;
		private JRadioButton ProxySupportYes;
		private JCheckBox RestEncryptionCkBtn;
		private JCheckBox bearShareCkb, bearShareRtn;
		private JRadioButton RestEncryptionDont;
		private ButtonGroup RestEncryptionGroup;
		private JRadioButton RestEncryptionNo;
		private JComboBox RestEncryptionWeight;
		private JRadioButton RestEncryptionYes;
		private JRadioButton SecKeyManagemenDont;
		private JCheckBox SecKeyManagementCkBtn;
		private ButtonGroup SecKeyManagementGroup;
		private JRadioButton SecKeyManagementNo;
		private JComboBox SecKeyManagementWeight;
		private JRadioButton SecKeyManagementYes;
		private JCheckBox ShareDataCkBtn;
		private JRadioButton ShareDataDont;
		private ButtonGroup ShareDataGroup;
		private JRadioButton ShareDataNo;
		private JComboBox ShareDataWeight;
		private JRadioButton ShareDataYes;
		private JComboBox SpLocationCombo, SpLocationComboFix,SpLocationComboWeight;
		private static JComboBox sortCBox;
		private JPanel TestPanel;
		private JCheckBox TransitEncryptionCkBtn;
		private JRadioButton TransitEncryptionDont;
		private ButtonGroup TransitEncryptionGroup;
		private JRadioButton TransitEncryptionNo;
		private JComboBox TransitEncryptionWeight;
		private JRadioButton TransitEncryptionYes;
		private JPanel addUserPane, amendUserPanel, assetsTap;
		private JLabel assets_user, costLbl, currentUsrLb_inputName, currentUsrLb_inputSurname, currentUsrLb_name,currentUsrLb_surname;
		private JButton customSecBtn;
		private JPanel customSecurityPanel;
		private JCheckBox dropBoxCkb;
		private JLabel existUsr_name;
		private JLabel expFileSizelbl;
		private JRadioButton explicitRadBtn;
		private JPanel filesPanel, fixedSecurityPanel, spLegendPanel;
		private JCheckBox googleDriveCkb;
		private JRadioButton implicitRadBtn;
		private JButton selectPolicyBtn;
		private JLabel jLabel1, jLabel10,jLabel11, jLabel12,jLabel13,jLabel14,jLabel15,jLabel16,jLabel17,jLabel18,jLabel19;
		private JLabel jLabel2,jLabel20,jLabel21,jLabel22,jLabel23,jLabel24,jLabel25,jLabel27,jLabel28,jLabel29,jLabel26;
		private JLabel jLabel3,jLabel4,jLabel5,jLabel6,jLabel7,jLabel78,jLabel8,jLabel9,jLabel32;
		private JPanel jPanel1; // check what it is
		private JScrollPane jScrollPane2;
		private static JTabbedPane jTabbs;
		private static JTable table;
		private JButton lowSecBtn, maxSecBtn, medSecBtn,delRecBtn;
		private JPanel monitorUserTap;
		private JLabel newUsrLb_name, newUsrLb_surname, loadGifLbl;
		private JTextField newUsr_nameTxt;
		private JButton addUserBtn;
		private JTextField newUsr_surnameTxt;
		private JCheckBox oneDriveCkb, cloudMeCkb;
		private JButton removeUserBtn;
		private ButtonGroup requirmentTypeGroup;
		private JPanel secLevelPanel;
		private JCheckBox yandexCkb, googleDriveCkbRtn, dropBoxCkbRtn, oneDriveCkbRtn, yandexCkbRtn, cloudMeCkbRtn;
		
		// End of variables declaration//GEN-END:variables
		ImageIcon imageIcon = new ImageIcon("img/loading.gif");
		public ArrayList<JCheckBox> ckBoxList;
		public ArrayList<JComboBox> comboBoxList;
		public ArrayList<ButtonGroup> radioBtnList;
		static public ArrayList<Object[]> assetsList=new ArrayList<Object[]>();
		public ArrayList<Double> weightsList = new ArrayList<Double>();
		ArrayList<String>working=null;
		ArrayList<String>nonWorking=null;
		public ArrayList<JCheckBox> boxListRtn=null;
		static MonitorAssets mon = new MonitorAssets();
		User user ;
		InitializeSystem sys;
		static boolean isCustomPanel = false;
		static CreateBid bid = null;
	    static	boolean isMax = false, isMed = false, isLow = false, isCust = false;
	    static String selectedPolicy=" ";
		static CloudAPIs api;
		ArrayList<String> availSPsListString = new ArrayList<String>();
		static String FileName, FileSize, SecReq, StorageProvider, Price;
		//static DefaultTableModel model;
		static DefaultTableModel model;
		static DefaultTableCellRenderer centerRenderer;
		ArrayList <JButton> buttonList;
	
		/**
	 * Creates new form MainGui
	 * @throws CloudmeException 
	 */
	public Gui() throws IOException, CloudmeException {
		myframe = new JFrame("Asset Aware Security");
		myframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		myframe.setLocation(new java.awt.Point(0, 0));
		//On exit event
		myframe.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				
				File conf = new File("UserDatabase/user.xml");
				
				if( bid!=null && conf.exists()){
				  SaveOnExit();
				}
			}
		});
		initComponents();
		initVariables();

	}

	 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        RestEncryptionGroup = new javax.swing.ButtonGroup();
        TransitEncryptionGroup = new javax.swing.ButtonGroup();
        PassProtecFilesGroup = new javax.swing.ButtonGroup();
        FileVersioningGroup = new javax.swing.ButtonGroup();
        ConcealedKeysGroup = new javax.swing.ButtonGroup();
        AutoSynchGroup = new javax.swing.ButtonGroup();
        SecKeyManagementGroup = new javax.swing.ButtonGroup();
        PassRecoveryGroup = new javax.swing.ButtonGroup();
        ShareDataGroup = new javax.swing.ButtonGroup();
        AuditLogsGroup = new javax.swing.ButtonGroup();
        ProxySupportGroup = new javax.swing.ButtonGroup();
        DifferentKeyPerFileGroup = new javax.swing.ButtonGroup();
        PermanentDeletionGroup = new javax.swing.ButtonGroup();
        requirmentTypeGroup = new javax.swing.ButtonGroup();
        jTabbs = new javax.swing.JTabbedPane();
        assetsTap = new javax.swing.JPanel();
        filesPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        sortCBox = new javax.swing.JComboBox();
        delRecBtn = new javax.swing.JButton();
        existUsr_name = new javax.swing.JLabel();
        assets_user = new javax.swing.JLabel();
        monitorUserTap = new javax.swing.JPanel();
        addUserPane = new javax.swing.JPanel();
        newUsrLb_name = new javax.swing.JLabel();
        newUsr_nameTxt = new javax.swing.JTextField();
        newUsr_surnameTxt = new javax.swing.JTextField();
        addUserBtn = new javax.swing.JButton();
        newUsrLb_surname = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        dropBoxCkb = new javax.swing.JCheckBox();
        googleDriveCkb = new javax.swing.JCheckBox();
        oneDriveCkb = new javax.swing.JCheckBox();
        yandexCkb = new javax.swing.JCheckBox();
        cloudMeCkb = new javax.swing.JCheckBox();
        bearShareCkb = new javax.swing.JCheckBox();
        cubbyCkb = new javax.swing.JCheckBox();
        spiderOakCkb = new javax.swing.JCheckBox();
        megaCkb = new javax.swing.JCheckBox();
        amendUserPanel = new javax.swing.JPanel();
        currentUsrLb_name = new javax.swing.JLabel();
        currentUsrLb_inputName = new javax.swing.JLabel();
        currentUsrLb_inputSurname = new javax.swing.JLabel();
        currentUsrLb_surname = new javax.swing.JLabel();
        removeUserBtn = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        dropBoxCkbRtn = new javax.swing.JCheckBox();
        oneDriveCkbRtn = new javax.swing.JCheckBox();
        cloudMeCkbRtn = new javax.swing.JCheckBox();
        spiderOakCkbRtn = new javax.swing.JCheckBox();
        megaCkbRtn = new javax.swing.JCheckBox();
        cubbyCkbRtn = new javax.swing.JCheckBox();
        bearShareRtn = new javax.swing.JCheckBox();
        yandexCkbRtn = new javax.swing.JCheckBox();
        googleDriveCkbRtn = new javax.swing.JCheckBox();
        spLegendPanel = new javax.swing.JPanel();
        redLbl = new javax.swing.JLabel();
        greenLbl = new javax.swing.JLabel();
        blueLbl = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        TestPanel = new javax.swing.JPanel();
        fixedSecurityPanel = new javax.swing.JPanel();
        RestEncryptionCkBtn = new javax.swing.JCheckBox();
        TransitEncryptionCkBtn = new javax.swing.JCheckBox();
        PassProtecFiles = new javax.swing.JCheckBox();
        FileVersioningCkBtn = new javax.swing.JCheckBox();
        ConcealedKeysCkBtn = new javax.swing.JCheckBox();
        AutoSynchCkBtn = new javax.swing.JCheckBox();
        SpLocationComboFix = new javax.swing.JComboBox();
        SecKeyManagementCkBtn = new javax.swing.JCheckBox();
        PassRecoveryCkBtn = new javax.swing.JCheckBox();
        ShareDataCkBtn = new javax.swing.JCheckBox();
        AuditLogsCkBtn = new javax.swing.JCheckBox();
        ProxySupportCkBtn = new javax.swing.JCheckBox();
        DifferentKeyPerFileCkBtn = new javax.swing.JCheckBox();
        jLabel78 = new javax.swing.JLabel();
        CertificationLbl = new javax.swing.JLabel();
        PermanentDeletionCkBtn = new javax.swing.JCheckBox();
        costLbl = new javax.swing.JLabel();
        CostTxtFix = new javax.swing.JTextField();
        secLevelPanel = new javax.swing.JPanel();
        medSecBtn = new javax.swing.JButton();
        lowSecBtn = new javax.swing.JButton();
        customSecBtn = new javax.swing.JButton();
        maxSecBtn = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        implicitRadBtn = new javax.swing.JRadioButton();
        explicitRadBtn = new javax.swing.JRadioButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        selectPolicyBtn = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        loadGifLbl = new javax.swing.JLabel();
        customSecurityPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        RestEncryptionYes = new javax.swing.JRadioButton();
        RestEncryptionNo = new javax.swing.JRadioButton();
        RestEncryptionDont = new javax.swing.JRadioButton();
        RestEncryptionWeight = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TransitEncryptionYes = new javax.swing.JRadioButton();
        TransitEncryptionNo = new javax.swing.JRadioButton();
        TransitEncryptionDont = new javax.swing.JRadioButton();
        TransitEncryptionWeight = new javax.swing.JComboBox();
        PassProtecFilesYes = new javax.swing.JRadioButton();
        PassProtecFilesNo = new javax.swing.JRadioButton();
        PassProtecFilesDont = new javax.swing.JRadioButton();
        PassProtecFilesWeight = new javax.swing.JComboBox();
        FileVersioningYes = new javax.swing.JRadioButton();
        FileVersioningNo = new javax.swing.JRadioButton();
        FileVersioningDont = new javax.swing.JRadioButton();
        FileVersioningWeight = new javax.swing.JComboBox();
        ConcealedKeysYes = new javax.swing.JRadioButton();
        ConcealedKeysNo = new javax.swing.JRadioButton();
        ConcealedKeysDont = new javax.swing.JRadioButton();
        ConcealedKeysWeight = new javax.swing.JComboBox();
        AutoSynchYes = new javax.swing.JRadioButton();
        AutoSynchNo = new javax.swing.JRadioButton();
        AutoSynchDont = new javax.swing.JRadioButton();
        AutoSynchWeight = new javax.swing.JComboBox();
        SecKeyManagementYes = new javax.swing.JRadioButton();
        SecKeyManagementNo = new javax.swing.JRadioButton();
        SecKeyManagemenDont = new javax.swing.JRadioButton();
        SecKeyManagementWeight = new javax.swing.JComboBox();
        PassRecoveryYes = new javax.swing.JRadioButton();
        PassRecoveryNo = new javax.swing.JRadioButton();
        PassRecoveryDont = new javax.swing.JRadioButton();
        PassRecoveryWeight = new javax.swing.JComboBox();
        ShareDataYes = new javax.swing.JRadioButton();
        ShareDataNo = new javax.swing.JRadioButton();
        ShareDataDont = new javax.swing.JRadioButton();
        ShareDataWeight = new javax.swing.JComboBox();
        AuditLogsYes = new javax.swing.JRadioButton();
        AuditLogsNo = new javax.swing.JRadioButton();
        AuditLogsDont = new javax.swing.JRadioButton();
        AuditLogsWeight = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        ProxySupportYes = new javax.swing.JRadioButton();
        ProxySupportNo = new javax.swing.JRadioButton();
        ProxySupportDont = new javax.swing.JRadioButton();
        ProxySupportWeight = new javax.swing.JComboBox();
        DifferentKeyPerFileYes = new javax.swing.JRadioButton();
        DifferentKeyPerFileNo = new javax.swing.JRadioButton();
        DifferentKeyPerFileDont = new javax.swing.JRadioButton();
        DifferentKeyPerFileWeight = new javax.swing.JComboBox();
        PermanentDeletionYes = new javax.swing.JRadioButton();
        PermanentDeletionNo = new javax.swing.JRadioButton();
        PermanentDeletionDont = new javax.swing.JRadioButton();
        PermanentDeletionWeight = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        SpLocationCombo = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        SpLocationComboWeight = new javax.swing.JComboBox();
        CostTxtCus = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        CertificationTxt = new javax.swing.JTextField();
        CertificationWeight = new javax.swing.JComboBox();

        jTabbs.setName("jTabbs"); // NOI18N

        filesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Files", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "File Name", "File Size (MB)", "Asset Sec. Requirments", "Storage Provider", "Price", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(table);

        jLabel21.setText("Sort By:");

        sortCBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Date", "File Name" }));
        sortCBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortCBoxActionPerformed(evt);
            }
        });
        
        javax.swing.GroupLayout filesPanelLayout = new javax.swing.GroupLayout(filesPanel);
        filesPanel.setLayout(filesPanelLayout);
        filesPanelLayout.setHorizontalGroup(
            filesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(filesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1032, Short.MAX_VALUE)
                    .addGroup(filesPanelLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sortCBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        filesPanelLayout.setVerticalGroup(
            filesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filesPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(filesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(sortCBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                .addContainerGap())
        );

        delRecBtn.setText("Delete (?)");
        delRecBtn.setToolTipText("Select the files you want to delete and click the button");
        delRecBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					delRecBtnActionPerformed(evt);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        existUsr_name.setText("Name Surname");

        assets_user.setText("User:");

        javax.swing.GroupLayout assetsTapLayout = new javax.swing.GroupLayout(assetsTap);
        assetsTap.setLayout(assetsTapLayout);
        assetsTapLayout.setHorizontalGroup(
            assetsTapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assetsTapLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(assetsTapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(assetsTapLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(delRecBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(assetsTapLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(assets_user)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(existUsr_name)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        assetsTapLayout.setVerticalGroup(
            assetsTapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assetsTapLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(assetsTapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(assets_user)
                    .addComponent(existUsr_name))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(delRecBtn)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        jTabbs.addTab("Assets", assetsTap);

        addUserPane.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Add New User", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        newUsrLb_name.setText("Name:");

        newUsr_nameTxt.setName("mmm"); // NOI18N

        addUserBtn.setText("Create");
        addUserBtn.setActionCommand("SubmitBtn");
        addUserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					addUserBtnActionPerformed(evt);
				} catch (IOException | CloudmeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        newUsrLb_surname.setText("Surname:");

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setText("Please select service providers:");

        dropBoxCkb.setText("DropBox");

        googleDriveCkb.setText("GoogleDrive");

        oneDriveCkb.setText("OneDrive");

        yandexCkb.setText("YandexDisk");

        cloudMeCkb.setText("CloudMe");

        bearShareCkb.setText("BearDataShare (?)");

        cubbyCkb.setText("Cubby (?)");
        cubbyCkb.setToolTipText("Installation of SP needed");

        spiderOakCkb.setText("SpiderOak (?)");
        spiderOakCkb.setToolTipText("Installation of SP needed");

        megaCkb.setText("MEGA (?)");
        megaCkb.setToolTipText("Installation of SP needed");

        javax.swing.GroupLayout addUserPaneLayout = new javax.swing.GroupLayout(addUserPane);
        addUserPane.setLayout(addUserPaneLayout);
        addUserPaneLayout.setHorizontalGroup(
            addUserPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addUserPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addUserPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addUserPaneLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(addUserPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(addUserPaneLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(194, 194, 194))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, addUserPaneLayout.createSequentialGroup()
                                .addGroup(addUserPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(addUserPaneLayout.createSequentialGroup()
                                        .addComponent(newUsrLb_surname)
                                        .addGap(18, 18, 18))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addUserPaneLayout.createSequentialGroup()
                                        .addComponent(newUsrLb_name)
                                        .addGap(39, 39, 39)))
                                .addGroup(addUserPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(newUsr_surnameTxt)
                                    .addComponent(newUsr_nameTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                .addContainerGap())))
                    .addGroup(addUserPaneLayout.createSequentialGroup()
                        .addGroup(addUserPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(megaCkb)
                            .addComponent(spiderOakCkb)
                            .addGroup(addUserPaneLayout.createSequentialGroup()
                                .addGroup(addUserPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cloudMeCkb)
                                    .addComponent(dropBoxCkb)
                                    .addComponent(oneDriveCkb))
                                .addGap(58, 58, 58)
                                .addGroup(addUserPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(yandexCkb)
                                    .addComponent(googleDriveCkb)
                                    .addComponent(bearShareCkb)
                                    .addComponent(cubbyCkb))))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(addUserPaneLayout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addComponent(addUserBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        addUserPaneLayout.setVerticalGroup(
            addUserPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addUserPaneLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(addUserPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(addUserPaneLayout.createSequentialGroup()
                        .addComponent(newUsrLb_name)
                        .addGap(44, 44, 44))
                    .addGroup(addUserPaneLayout.createSequentialGroup()
                        .addComponent(newUsr_nameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(addUserPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(newUsrLb_surname)
                            .addComponent(newUsr_surnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addUserPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dropBoxCkb)
                    .addComponent(googleDriveCkb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addUserPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(oneDriveCkb)
                    .addComponent(yandexCkb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addUserPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cloudMeCkb)
                    .addComponent(bearShareCkb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addUserPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spiderOakCkb)
                    .addComponent(cubbyCkb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(megaCkb)
                .addGap(77, 77, 77)
                .addComponent(addUserBtn)
                .addContainerGap(156, Short.MAX_VALUE))
        );

        newUsrLb_surname.getAccessibleContext().setAccessibleDescription("");

        amendUserPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Current User", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        currentUsrLb_name.setText("Name:");

        currentUsrLb_inputName.setText("Unknown");

        currentUsrLb_inputSurname.setText("Unknown");

        currentUsrLb_surname.setText("Surname:");

        removeUserBtn.setText("Remove");
        removeUserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeUserBtnActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel19.setText("SPs Providers:");

        dropBoxCkbRtn.setText("DropBox");

        oneDriveCkbRtn.setText("OneDrive");

        cloudMeCkbRtn.setText("CloudMe");

        spiderOakCkbRtn.setText("Spider Oak (?)");
        spiderOakCkbRtn.setToolTipText("Installation of SP needed");

        megaCkbRtn.setText("MEGA (?)");
        megaCkbRtn.setToolTipText("Installation of SP needed");

        cubbyCkbRtn.setText("Cubby (?)");
        cubbyCkbRtn.setToolTipText("Installation of SP needed");

        bearShareRtn.setText("BearDataShare (?)");
        bearShareRtn.setToolTipText("Installation of SP needed");

        yandexCkbRtn.setText("YandexDisk");

        googleDriveCkbRtn.setText("GoogleDrive");

        javax.swing.GroupLayout amendUserPanelLayout = new javax.swing.GroupLayout(amendUserPanel);
        amendUserPanel.setLayout(amendUserPanelLayout);
        amendUserPanelLayout.setHorizontalGroup(
            amendUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(amendUserPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(amendUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, amendUserPanelLayout.createSequentialGroup()
                        .addGroup(amendUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(oneDriveCkbRtn)
                            .addComponent(dropBoxCkbRtn)
                            .addComponent(cloudMeCkbRtn)
                            .addComponent(megaCkbRtn)
                            .addComponent(spiderOakCkbRtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addGroup(amendUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cubbyCkbRtn)
                            .addComponent(bearShareRtn)
                            .addComponent(googleDriveCkbRtn)
                            .addComponent(yandexCkbRtn)))
                    .addGroup(amendUserPanelLayout.createSequentialGroup()
                        .addGroup(amendUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(amendUserPanelLayout.createSequentialGroup()
                                .addGroup(amendUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(currentUsrLb_name)
                                    .addComponent(currentUsrLb_surname, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(amendUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(currentUsrLb_inputSurname)
                                    .addComponent(currentUsrLb_inputName)))
                            .addComponent(jLabel19))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(amendUserPanelLayout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addComponent(removeUserBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        amendUserPanelLayout.setVerticalGroup(
            amendUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(amendUserPanelLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(amendUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentUsrLb_name)
                    .addComponent(currentUsrLb_inputName))
                .addGap(18, 18, 18)
                .addGroup(amendUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentUsrLb_surname)
                    .addComponent(currentUsrLb_inputSurname))
                .addGap(52, 52, 52)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(amendUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dropBoxCkbRtn)
                    .addComponent(googleDriveCkbRtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(amendUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(oneDriveCkbRtn)
                    .addComponent(yandexCkbRtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(amendUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bearShareRtn, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cloudMeCkbRtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(amendUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spiderOakCkbRtn)
                    .addComponent(cubbyCkbRtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(megaCkbRtn)
                .addGap(74, 74, 74)
                .addComponent(removeUserBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        spLegendPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("SPs legend"));

        redLbl.setBackground(new java.awt.Color(253, 11, 38));
        redLbl.setToolTipText("");
        redLbl.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        redLbl.setOpaque(true);

        greenLbl.setBackground(new java.awt.Color(47, 223, 40));
        greenLbl.setOpaque(true);

        blueLbl.setBackground(new java.awt.Color(66, 0, 255));
        blueLbl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel20.setText("Unsynchronized");

        jLabel32.setText("Synchronized");

        jLabel31.setText("Not Connected");

        javax.swing.GroupLayout spLegendPanelLayout = new javax.swing.GroupLayout(spLegendPanel);
        spLegendPanel.setLayout(spLegendPanelLayout);
        spLegendPanelLayout.setHorizontalGroup(
            spLegendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(spLegendPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(spLegendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(redLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(greenLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(blueLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addGroup(spLegendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel32)
                    .addComponent(jLabel31))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        spLegendPanelLayout.setVerticalGroup(
            spLegendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(spLegendPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(spLegendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(spLegendPanelLayout.createSequentialGroup()
                        .addGroup(spLegendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(redLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addGap(18, 18, 18)
                        .addComponent(greenLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel32))
                .addGap(18, 18, 18)
                .addGroup(spLegendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(blueLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout monitorUserTapLayout = new javax.swing.GroupLayout(monitorUserTap);
        monitorUserTap.setLayout(monitorUserTapLayout);
        monitorUserTapLayout.setHorizontalGroup(
            monitorUserTapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(monitorUserTapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addUserPane, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(amendUserPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(spLegendPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        monitorUserTapLayout.setVerticalGroup(
            monitorUserTapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, monitorUserTapLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(monitorUserTapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(monitorUserTapLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(spLegendPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(amendUserPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addUserPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbs.addTab("Monitor Users", monitorUserTap);

        fixedSecurityPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Maximum Security"));

        RestEncryptionCkBtn.setText("Ecryption at rest");

        TransitEncryptionCkBtn.setText("Encryption at Transit");

        PassProtecFiles.setText("Password Protected files");

        FileVersioningCkBtn.setText("File Versioning");

        ConcealedKeysCkBtn.setText("Enc. Keys Concealed");

        AutoSynchCkBtn.setText("Auto Synch");

        SpLocationComboFix.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Datacenter Location", "Asia", "EU", "USA", "North America", "Russia", "Oceania", " " }));

        SecKeyManagementCkBtn.setText("Secure Key Managment");

        PassRecoveryCkBtn.setText("Credential Recovery");

        ShareDataCkBtn.setText("Share Data");

        AuditLogsCkBtn.setText("Audit Logs");

        ProxySupportCkBtn.setText("Proxy Support");

        DifferentKeyPerFileCkBtn.setText("Diff. Key Per File");

        jLabel78.setText("Certification:");

        CertificationLbl.setText("-------------");

        PermanentDeletionCkBtn.setText("Permanent Deletion");

        costLbl.setBackground(new java.awt.Color(249, 242, 10));
        costLbl.setText("*Cost (in $):");
        costLbl.setOpaque(true);

        javax.swing.GroupLayout fixedSecurityPanelLayout = new javax.swing.GroupLayout(fixedSecurityPanel);
        fixedSecurityPanel.setLayout(fixedSecurityPanelLayout);
        fixedSecurityPanelLayout.setHorizontalGroup(
            fixedSecurityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fixedSecurityPanelLayout.createSequentialGroup()
                .addGroup(fixedSecurityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PermanentDeletionCkBtn)
                    .addComponent(DifferentKeyPerFileCkBtn)
                    .addComponent(ProxySupportCkBtn)
                    .addComponent(AuditLogsCkBtn)
                    .addComponent(ShareDataCkBtn)
                    .addComponent(SpLocationComboFix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AutoSynchCkBtn)
                    .addComponent(ConcealedKeysCkBtn)
                    .addComponent(RestEncryptionCkBtn)
                    .addComponent(TransitEncryptionCkBtn)
                    .addComponent(PassProtecFiles)
                    .addComponent(FileVersioningCkBtn)
                    .addComponent(PassRecoveryCkBtn)
                    .addComponent(SecKeyManagementCkBtn)
                    .addGroup(fixedSecurityPanelLayout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(CostTxtFix, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(fixedSecurityPanelLayout.createSequentialGroup()
                .addGroup(fixedSecurityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel78)
                    .addComponent(costLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CertificationLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        fixedSecurityPanelLayout.setVerticalGroup(
            fixedSecurityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fixedSecurityPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RestEncryptionCkBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TransitEncryptionCkBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PassProtecFiles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FileVersioningCkBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ConcealedKeysCkBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AutoSynchCkBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SpLocationComboFix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SecKeyManagementCkBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PassRecoveryCkBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ShareDataCkBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AuditLogsCkBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ProxySupportCkBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DifferentKeyPerFileCkBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PermanentDeletionCkBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(fixedSecurityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel78)
                    .addComponent(CertificationLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(fixedSecurityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CostTxtFix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costLbl))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        secLevelPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        medSecBtn.setText("Medium Security");
        medSecBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medSecBtnActionPerformed(evt);
            }
        });

        lowSecBtn.setText("Low Security");
        lowSecBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lowSecBtnActionPerformed(evt);
            }
        });

        customSecBtn.setText("Custom Security");
        customSecBtn.setToolTipText("This is a test");
        customSecBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customSecBtnActionPerformed(evt);
            }
        });

        maxSecBtn.setText("High Security");
        maxSecBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxSecBtnActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel24.setText("Step 2:");

        jLabel25.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel25.setText("Select Type of Requirement");
        jLabel25.setToolTipText("");

        requirmentTypeGroup.add(implicitRadBtn);
        implicitRadBtn.setSelected(true);
        implicitRadBtn.setText("Implicit requirements (?)");
        implicitRadBtn.setToolTipText("Indicates that if a perfect match is not found the system will be able to choose the solution closes to the optimal one");

        requirmentTypeGroup.add(explicitRadBtn);
        explicitRadBtn.setText("Explicit requirements (?)");
        explicitRadBtn.setToolTipText("Indicates that if a perfect match is not found the system will not be able to choose any other solution and it will have to store the file locally");

        jLabel26.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel26.setText("Step 3:");

        jLabel27.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel27.setText("Click submit buttnon ");

        jLabel28.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel28.setText("to proceed");

        selectPolicyBtn.setText("Submit Security Policy");
        selectPolicyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectPolicyBtnActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel23.setText("Step 1:");

        jLabel29.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel29.setText("Choose Security Policy");

        javax.swing.GroupLayout secLevelPanelLayout = new javax.swing.GroupLayout(secLevelPanel);
        secLevelPanel.setLayout(secLevelPanelLayout);
        secLevelPanelLayout.setHorizontalGroup(
            secLevelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(secLevelPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(secLevelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(medSecBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lowSecBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(customSecBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(maxSecBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(secLevelPanelLayout.createSequentialGroup()
                        .addGroup(secLevelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28)
                            .addComponent(jLabel29)
                            .addComponent(implicitRadBtn)
                            .addComponent(explicitRadBtn)
                            .addComponent(selectPolicyBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(loadGifLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        secLevelPanelLayout.setVerticalGroup(
            secLevelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(secLevelPanelLayout.createSequentialGroup()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lowSecBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(medSecBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(maxSecBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(customSecBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(implicitRadBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(explicitRadBtn)
                .addGap(18, 18, 18)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel28)
                .addGap(18, 18, 18)
                .addComponent(selectPolicyBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(loadGifLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        customSecurityPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Custom Security"));

        jLabel2.setText("Encryption at Rest:");

        RestEncryptionGroup.add(RestEncryptionYes);
        RestEncryptionYes.setText("Yes");

        RestEncryptionGroup.add(RestEncryptionNo);
        RestEncryptionNo.setText("No");

        RestEncryptionGroup.add(RestEncryptionDont);
        RestEncryptionDont.setSelected(true);
        RestEncryptionDont.setText("Irrelevant");

        RestEncryptionWeight.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 - Low", "2 - Medium", "3 - High" }));

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel3.setText("Attributes:");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setText("Weights (?):");
        jLabel4.setToolTipText("States the importance of each security attribute for the user. Where 1 indicates low importance and 3 high importance");

        TransitEncryptionGroup.add(TransitEncryptionYes);
        TransitEncryptionYes.setText("Yes");

        TransitEncryptionGroup.add(TransitEncryptionNo);
        TransitEncryptionNo.setText("No");

        TransitEncryptionGroup.add(TransitEncryptionDont);
        TransitEncryptionDont.setSelected(true);
        TransitEncryptionDont.setText("Irrelevant");

        TransitEncryptionWeight.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 - Low", "2 - Medium", "3 - High" }));

        PassProtecFilesGroup.add(PassProtecFilesYes);
        PassProtecFilesYes.setText("Yes");

        PassProtecFilesGroup.add(PassProtecFilesNo);
        PassProtecFilesNo.setText("No");

        PassProtecFilesGroup.add(PassProtecFilesDont);
        PassProtecFilesDont.setSelected(true);
        PassProtecFilesDont.setText("Irrelevant");

        PassProtecFilesWeight.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 - Low", "2 - Medium", "3 - High" }));

        FileVersioningGroup.add(FileVersioningYes);
        FileVersioningYes.setText("Yes");

        FileVersioningGroup.add(FileVersioningNo);
        FileVersioningNo.setText("No");

        FileVersioningGroup.add(FileVersioningDont);
        FileVersioningDont.setSelected(true);
        FileVersioningDont.setText("Irrelevant");

        FileVersioningWeight.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 - Low", "2 - Medium", "3 - High" }));

        ConcealedKeysGroup.add(ConcealedKeysYes);
        ConcealedKeysYes.setText("Yes");

        ConcealedKeysGroup.add(ConcealedKeysNo);
        ConcealedKeysNo.setText("No");

        ConcealedKeysGroup.add(ConcealedKeysDont);
        ConcealedKeysDont.setSelected(true);
        ConcealedKeysDont.setText("Irrelevant");

        ConcealedKeysWeight.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 - Low", "2 - Medium", "3 - High" }));

        AutoSynchGroup.add(AutoSynchYes);
        AutoSynchYes.setText("Yes");

        AutoSynchGroup.add(AutoSynchNo);
        AutoSynchNo.setText("No");

        AutoSynchGroup.add(AutoSynchDont);
        AutoSynchDont.setSelected(true);
        AutoSynchDont.setText("Irrelevant");

        AutoSynchWeight.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 - Low", "2 - Medium", "3 - High" }));

        SecKeyManagementGroup.add(SecKeyManagementYes);
        SecKeyManagementYes.setText("Yes");

        SecKeyManagementGroup.add(SecKeyManagementNo);
        SecKeyManagementNo.setText("No");

        SecKeyManagementGroup.add(SecKeyManagemenDont);
        SecKeyManagemenDont.setSelected(true);
        SecKeyManagemenDont.setText("Irrelevant");

        SecKeyManagementWeight.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 - Low", "2 - Medium", "3 - High" }));

        PassRecoveryGroup.add(PassRecoveryYes);
        PassRecoveryYes.setText("Yes");

        PassRecoveryGroup.add(PassRecoveryNo);
        PassRecoveryNo.setText("No");

        PassRecoveryGroup.add(PassRecoveryDont);
        PassRecoveryDont.setSelected(true);
        PassRecoveryDont.setText("Irrelevant");

        PassRecoveryWeight.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 - Low", "2 - Medium", "3 - High" }));

        ShareDataGroup.add(ShareDataYes);
        ShareDataYes.setText("Yes");

        ShareDataGroup.add(ShareDataNo);
        ShareDataNo.setText("No");

        ShareDataGroup.add(ShareDataDont);
        ShareDataDont.setSelected(true);
        ShareDataDont.setText("Irrelevant");

        ShareDataWeight.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 - Low", "2 - Medium", "3 - High" }));

        AuditLogsGroup.add(AuditLogsYes);
        AuditLogsYes.setText("Yes");

        AuditLogsGroup.add(AuditLogsNo);
        AuditLogsNo.setText("No");

        AuditLogsGroup.add(AuditLogsDont);
        AuditLogsDont.setSelected(true);
        AuditLogsDont.setText("Irrelevant");

        AuditLogsWeight.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 - Low", "2 - Medium", "3 - High" }));

        jLabel5.setText("Encryption at Transit:");

        jLabel6.setText("Password Potected Files:");

        jLabel7.setText("File Versioning:");

        jLabel8.setText("Enc Keys Concealed:");

        jLabel9.setText("Auto Synch:");

        jLabel10.setText("Secure Key Managment:");

        jLabel11.setText("Credential Recovery:");

        jLabel12.setText("Share Data:");

        jLabel13.setText("Audit Logs:");

        ProxySupportGroup.add(ProxySupportYes);
        ProxySupportYes.setText("Yes");

        ProxySupportGroup.add(ProxySupportNo);
        ProxySupportNo.setText("No");

        ProxySupportGroup.add(ProxySupportDont);
        ProxySupportDont.setSelected(true);
        ProxySupportDont.setText("Irrelevant");

        ProxySupportWeight.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 - Low", "2 - Medium", "3 - High" }));

        DifferentKeyPerFileGroup.add(DifferentKeyPerFileYes);
        DifferentKeyPerFileYes.setText("Yes");

        DifferentKeyPerFileGroup.add(DifferentKeyPerFileNo);
        DifferentKeyPerFileNo.setText("No");

        DifferentKeyPerFileGroup.add(DifferentKeyPerFileDont);
        DifferentKeyPerFileDont.setSelected(true);
        DifferentKeyPerFileDont.setText("Irrelevant");

        DifferentKeyPerFileWeight.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 - Low", "2 - Medium", "3 - High" }));

        PermanentDeletionGroup.add(PermanentDeletionYes);
        PermanentDeletionYes.setText("Yes");

        PermanentDeletionGroup.add(PermanentDeletionNo);
        PermanentDeletionNo.setText("No");

        PermanentDeletionGroup.add(PermanentDeletionDont);
        PermanentDeletionDont.setSelected(true);
        PermanentDeletionDont.setText("Irrelevant");

        PermanentDeletionWeight.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 - Low", "2 - Medium", "3 - High" }));

        jLabel14.setText("Proxy Support:");

        jLabel15.setText("Diff. Key Per File:");

        jLabel16.setText("Permanent Deletion:");

        SpLocationCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Irrelevant", "Asia", "EU", "USA", "North America", "Russia", "Oceania", " " }));

        jLabel17.setText("Data Center Location:");

        SpLocationComboWeight.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 - Low", "2 - Medium", "3 - High" }));

        jLabel18.setBackground(new java.awt.Color(249, 242, 10));
        jLabel18.setText("* Cost (in $):");

        jLabel22.setText("Certification (?):");
        jLabel22.setToolTipText("Including: ISO 27001, Safe Harbor, FERPA, HIPAA");

        CertificationWeight.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 - Low", "2 - Medium", "3 - High" }));

        javax.swing.GroupLayout customSecurityPanelLayout = new javax.swing.GroupLayout(customSecurityPanel);
        customSecurityPanel.setLayout(customSecurityPanelLayout);
        customSecurityPanelLayout.setHorizontalGroup(
            customSecurityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customSecurityPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(customSecurityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(customSecurityPanelLayout.createSequentialGroup()
                        .addGroup(customSecurityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(RestEncryptionYes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(RestEncryptionNo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(RestEncryptionDont)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(customSecurityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RestEncryptionWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customSecurityPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TransitEncryptionYes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TransitEncryptionNo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TransitEncryptionDont)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TransitEncryptionWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customSecurityPanelLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PassProtecFilesYes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PassProtecFilesNo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PassProtecFilesDont)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PassProtecFilesWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customSecurityPanelLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FileVersioningYes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FileVersioningNo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FileVersioningDont)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FileVersioningWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customSecurityPanelLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ConcealedKeysYes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ConcealedKeysNo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ConcealedKeysDont)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ConcealedKeysWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customSecurityPanelLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AutoSynchYes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AutoSynchNo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AutoSynchDont)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AutoSynchWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customSecurityPanelLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(SecKeyManagementYes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SecKeyManagementNo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SecKeyManagemenDont)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SecKeyManagementWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customSecurityPanelLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PassRecoveryYes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PassRecoveryNo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PassRecoveryDont)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PassRecoveryWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customSecurityPanelLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ShareDataYes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ShareDataNo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ShareDataDont)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ShareDataWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customSecurityPanelLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AuditLogsYes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AuditLogsNo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AuditLogsDont)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AuditLogsWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customSecurityPanelLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ProxySupportYes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ProxySupportNo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ProxySupportDont)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ProxySupportWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customSecurityPanelLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DifferentKeyPerFileYes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DifferentKeyPerFileNo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DifferentKeyPerFileDont)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DifferentKeyPerFileWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customSecurityPanelLayout.createSequentialGroup()
                        .addGroup(customSecurityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(customSecurityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customSecurityPanelLayout.createSequentialGroup()
                                .addComponent(SpLocationCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(SpLocationComboWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customSecurityPanelLayout.createSequentialGroup()
                                .addComponent(PermanentDeletionYes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(PermanentDeletionNo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(PermanentDeletionDont)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(PermanentDeletionWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(customSecurityPanelLayout.createSequentialGroup()
                        .addGroup(customSecurityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel22))
                        .addGap(68, 68, 68)
                        .addGroup(customSecurityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CertificationTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                            .addComponent(CostTxtCus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CertificationWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        customSecurityPanelLayout.setVerticalGroup(
            customSecurityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customSecurityPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(customSecurityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(7, 7, 7)
                .addGroup(customSecurityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(RestEncryptionYes)
                    .addComponent(RestEncryptionNo)
                    .addComponent(RestEncryptionDont)
                    .addComponent(RestEncryptionWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customSecurityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TransitEncryptionYes)
                    .addComponent(TransitEncryptionNo)
                    .addComponent(TransitEncryptionDont)
                    .addComponent(TransitEncryptionWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customSecurityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PassProtecFilesYes)
                    .addComponent(PassProtecFilesNo)
                    .addComponent(PassProtecFilesDont)
                    .addComponent(PassProtecFilesWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customSecurityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FileVersioningYes)
                    .addComponent(FileVersioningNo)
                    .addComponent(FileVersioningDont)
                    .addComponent(FileVersioningWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customSecurityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ConcealedKeysYes)
                    .addComponent(ConcealedKeysNo)
                    .addComponent(ConcealedKeysDont)
                    .addComponent(ConcealedKeysWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customSecurityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AutoSynchYes)
                    .addComponent(AutoSynchNo)
                    .addComponent(AutoSynchDont)
                    .addComponent(AutoSynchWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customSecurityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SecKeyManagementYes)
                    .addComponent(SecKeyManagementNo)
                    .addComponent(SecKeyManagemenDont)
                    .addComponent(SecKeyManagementWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customSecurityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PassRecoveryYes)
                    .addComponent(PassRecoveryNo)
                    .addComponent(PassRecoveryDont)
                    .addComponent(PassRecoveryWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customSecurityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ShareDataYes)
                    .addComponent(ShareDataNo)
                    .addComponent(ShareDataDont)
                    .addComponent(ShareDataWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customSecurityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AuditLogsYes)
                    .addComponent(AuditLogsNo)
                    .addComponent(AuditLogsDont)
                    .addComponent(AuditLogsWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customSecurityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProxySupportYes)
                    .addComponent(ProxySupportNo)
                    .addComponent(ProxySupportDont)
                    .addComponent(ProxySupportWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customSecurityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DifferentKeyPerFileYes)
                    .addComponent(DifferentKeyPerFileNo)
                    .addComponent(DifferentKeyPerFileDont)
                    .addComponent(DifferentKeyPerFileWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customSecurityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PermanentDeletionYes)
                    .addComponent(PermanentDeletionNo)
                    .addComponent(PermanentDeletionDont)
                    .addComponent(PermanentDeletionWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customSecurityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SpLocationCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(SpLocationComboWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customSecurityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CertificationWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CertificationTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(customSecurityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CostTxtCus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout TestPanelLayout = new javax.swing.GroupLayout(TestPanel);
        TestPanel.setLayout(TestPanelLayout);
        TestPanelLayout.setHorizontalGroup(
            TestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TestPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(secLevelPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fixedSecurityPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(customSecurityPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        TestPanelLayout.setVerticalGroup(
            TestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TestPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(secLevelPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customSecurityPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(TestPanelLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(fixedSecurityPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbs.addTab("Security Policy Panel", TestPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(myframe.getContentPane());
        myframe.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbs)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbs)
        );

        jTabbs.getAccessibleContext().setAccessibleName("jTabbs");

        myframe.pack();
    }// </editor-fold>                        

	private void initVariables() throws IOException, CloudmeException {

		myframe.setLocationRelativeTo(null);
		// Initialise Variables
		loadGifLbl.setIcon(imageIcon);
		loadGifLbl.setVisible(false);
		fixedSecurityPanel.setVisible(false);
		customSecurityPanel.setVisible(isCustomPanel);

		boxListRtn = new ArrayList<JCheckBox>();
		boxListRtn.add(yandexCkbRtn);
		boxListRtn.add(oneDriveCkbRtn);
		boxListRtn.add(googleDriveCkbRtn);
		boxListRtn.add(dropBoxCkbRtn);
		boxListRtn.add(cloudMeCkbRtn);
		//Local SPs
		boxListRtn.add(spiderOakCkbRtn);
		boxListRtn.add(bearShareRtn);
		boxListRtn.add(megaCkbRtn);
		boxListRtn.add(cubbyCkbRtn);

		// Create and load arrayLists

		// CheckBox List
		ckBoxList = new ArrayList<JCheckBox>();

		ckBoxList.add(RestEncryptionCkBtn);
		ckBoxList.add(TransitEncryptionCkBtn);
		ckBoxList.add(PassProtecFiles);
		ckBoxList.add(FileVersioningCkBtn);
		ckBoxList.add(ConcealedKeysCkBtn);
		ckBoxList.add(AutoSynchCkBtn);
		ckBoxList.add(SecKeyManagementCkBtn);
		ckBoxList.add(PassRecoveryCkBtn);
		ckBoxList.add(ShareDataCkBtn);
		ckBoxList.add(AuditLogsCkBtn);
		ckBoxList.add(ProxySupportCkBtn);
		ckBoxList.add(DifferentKeyPerFileCkBtn);
		ckBoxList.add(PermanentDeletionCkBtn);

		// RadioGoup List
		radioBtnList = new ArrayList<ButtonGroup>();

		radioBtnList.add(RestEncryptionGroup);
		radioBtnList.add(TransitEncryptionGroup);
		radioBtnList.add(PassProtecFilesGroup);
		radioBtnList.add(FileVersioningGroup);
		radioBtnList.add(ConcealedKeysGroup);
		radioBtnList.add(AutoSynchGroup);
		radioBtnList.add(SecKeyManagementGroup);
		radioBtnList.add(PassRecoveryGroup);
		radioBtnList.add(ShareDataGroup);
		radioBtnList.add(AuditLogsGroup);
		radioBtnList.add(ProxySupportGroup);
		radioBtnList.add(DifferentKeyPerFileGroup);
		radioBtnList.add(PermanentDeletionGroup);

		// ComboBox List
		comboBoxList = new ArrayList<JComboBox>();

		comboBoxList.add(RestEncryptionWeight);
		comboBoxList.add(TransitEncryptionWeight);
		comboBoxList.add(PassProtecFilesWeight);
		comboBoxList.add(FileVersioningWeight);
		comboBoxList.add(ConcealedKeysWeight);
		comboBoxList.add(AutoSynchWeight);
		comboBoxList.add(SecKeyManagementWeight);
		comboBoxList.add(PassRecoveryWeight);
		comboBoxList.add(ShareDataWeight);
		comboBoxList.add(AuditLogsWeight);
		comboBoxList.add(ProxySupportWeight);
		comboBoxList.add(DifferentKeyPerFileWeight);
		comboBoxList.add(PermanentDeletionWeight);

		
		//JButton list
		buttonList = new ArrayList<JButton>();
		
		buttonList.add(lowSecBtn);
		buttonList.add(medSecBtn);
		buttonList.add(maxSecBtn);
		buttonList.add(customSecBtn);
		buttonList.add(selectPolicyBtn);
		// mon.monitorFileChanges();
		// Initialise Methods

		
		myframe.setVisible(true);
		centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		model = (DefaultTableModel) table.getModel();
		
		loadSystem();
		 //loadCheckUserEncXML();
	}

	private void addUserBtnActionPerformed(java.awt.event.ActionEvent evt) throws IOException, CloudmeException {// GEN-FIRST:event_newUsr_submitBtnActionPerformed
		String name, surname;
		ArrayList<String> spListStrings = new ArrayList<String>();
		ArrayList<JCheckBox> boxList = new ArrayList<JCheckBox>();
		Map<String, Boolean> spList = new HashMap<String, Boolean>();
		name = newUsr_nameTxt.getText();
		surname = newUsr_surnameTxt.getText();

		boxList.add(yandexCkb);
		boxList.add(oneDriveCkb);
		boxList.add(googleDriveCkb);
		boxList.add(dropBoxCkb);
		boxList.add(cloudMeCkb);
		//Local SPs
		boxList.add(spiderOakCkb);
		boxList.add(bearShareCkb);
		boxList.add(megaCkb);
		boxList.add(cubbyCkb);

		// Populate SPs list
		for (JCheckBox box : boxList) {
			if (box.isSelected()) {
				spList.put(box.getText(), box.isSelected());
				spListStrings.add(box.getText());
				
				// unTick for next time
				box.setSelected(false);
			}
		}

		// Create user folders
		sys = new InitializeSystem(); 
				
		// create new user importing data from the form and saves to XML file
		user = new User(name, surname, spList);
		availSPsListString = spListStrings; // New temp fix
		
		//Create asks for the user
		System.out.println("User SPs: "+user.getAvailableSP().keySet());
		// Populate the amendUser Panel
		getCurrentUser(user);
		this.addUserPane.setVisible(false);
		this.amendUserPanel.setVisible(true);

		//Enable buttons
		for(int i = 0;i<buttonList.size();i++){
			buttonList.get(i).setEnabled(true);
		}
		// Clear text
		newUsr_nameTxt.setText("");
		newUsr_surnameTxt.setText("");
		
		//Test dialog

		api = new CloudAPIs(spListStrings);
		//testDialog(spListString);
		working = api.getWorkingSPsList();
		nonWorking = api.getNonWorkingSPsList();
		doSPsync(working,nonWorking,boxListRtn);

		// Save User data to XML
		//sys.saveUserData(user); // Not neaded atm
	}

    private void sortCBoxActionPerformed(java.awt.event.ActionEvent evt) {                                         
        sortList();
        updateTable();
    }
	private void removeUserBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_removeUserBtnActionPerformed
		//Remove user file
		user.removeUser();
		assetsList.clear();
		model.setRowCount(0);
		api.clearList();
		availSPsListString.clear();	
		
		// Reset form placeholders
		currentUsrLb_inputName.setText("Unknown");
		currentUsrLb_inputSurname.setText("Unknown");
		existUsr_name.setText("Name Surname");
		
		//Reset checkBoxes to false and
		//back to normal color
		for(int i=0;i<boxListRtn.size();i++){
			boxListRtn.get(i).setSelected(false);
			boxListRtn.get(i).setForeground(Color.BLACK);
		}
		//Disable buttons
		for(int i = 0;i<buttonList.size();i++){
			buttonList.get(i).setEnabled(false);
		}
		
//		yandexCkbRtn.setSelected(false);
//		oneDriveCkbRtn.setSelected(false);
//		googleDriveCkbRtn.setSelected(false);
//		dropBoxCkbRtn.setSelected(false);
//		cloudMeCkbRtn.setSelected(false);
//		// resetLocal SPs
//		spiderOakCkbRtn.setSelected(false);
//		bearShareRtn.setSelected(false);
//		megaCkbRtn.setSelected(false);
//		cubbyCkbRtn.setSelected(false);

		this.amendUserPanel.setVisible(false);
		this.addUserPane.setVisible(true);
		
		
//		if(new File("AssetAwareSecurity.jar").exists()){
//			try {
//				Runtime.getRuntime().exec("java -jar AssetAwareSecurity.jar");
//				System.exit(0);
//			} catch (IOException e) {
//				System.out.println("Error on restarting the system");
//				e.printStackTrace();
//			}	
//		}
		
	}// GEN-LAST:event_removeUserBtnActionPerformed

	private void maxSecBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_maxSecBtnActionPerformed
		String fileAtrrName = "highAtrrValues.txt";
		String fileWeightName = "highAtrrValuesWeights.txt";

		displayFixedCkBoxList(fileAtrrName, fileWeightName);
		CertificationLbl.setText("ISO 27001, Directive 95/46/EC");
		SpLocationComboFix.setSelectedItem("EU");

		resetSelectors();
		isMax = true;
		selectedPolicy="High Security Policy";
		checkPoliciesPanels();
		resetColors();
		
		fillTxtFields();

		fixedSecurityPanel.setBorder(BorderFactory
				.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)), "Maximum Security"));
		maxSecBtn.setForeground(Color.red);
	}// GEN-LAST:event_maxSecBtnActionPerformed


	private void fillTxtFields() {
		if(bid != null){
			CostTxtFix.setText(bid.getCost());
		}
	}

	private void customSecBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_customSecBtnActionPerformed
		resetSelectors();
		isCustomPanel = true;
		selectedPolicy="Custom Security Policy";
		customSecurityPanel.setVisible(true);
		fixedSecurityPanel.setVisible(false);
		resetColors();

		customSecBtn.setForeground(Color.red);
		
		// Populate the form if custom file exist
		File f = new File("customPolicy.xml");
		if (f.exists() && bid != null){
			System.out.println("Populate form:");
			populateForm();
		}
	}// GEN-LAST:event_customSecBtnActionPerformed


	private void lowSecBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_lowSecBtnActionPerformed
		String fileName = "lowAtrrValues.txt";
		String fileWeightName = "lowAtrrValuesWeights.txt";

		ArrayList<Double> weightList = new ArrayList<Double>();
		displayFixedCkBoxList(fileName, fileWeightName);
		CertificationLbl.setText("----------");
		SpLocationComboFix.setSelectedIndex(0);

		resetSelectors();
		isLow = true;
		selectedPolicy="Low Security Policy";
		checkPoliciesPanels();
		resetColors();

		fillTxtFields();
		fixedSecurityPanel.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)), "Low Security"));
		lowSecBtn.setForeground(Color.red);
	}// GEN-LAST:event_lowSecBtnActionPerformed

	public void createObject() {}

	public void displayFixedCkBoxList(String fileNameAtrr, String fileNameWeights) {
		String[] valuesAtrr;
		String[] valuesWeights;

		// get values from files;
		valuesAtrr = getAtrrValues(fileNameAtrr);
		valuesWeights = getAtrrValues(fileNameWeights);

		for (int i = 0; i < ckBoxList.size(); i++) {
			if (valuesAtrr[i].equalsIgnoreCase("yes")) {
				ckBoxList.get(i).setSelected(true);
			} else {
				ckBoxList.get(i).setSelected(false);
			}
		}

		for (int i = 0; i < valuesWeights.length; i++) {

			if (valuesWeights[i].equals("#")) {
				weightsList.add(0.0);
			} else {
				double temp = Double.valueOf(valuesWeights[i]);
				weightsList.add(temp);
			}

			System.out.println(weightsList.get(i));
		}

		for (JCheckBox checkBox : ckBoxList) {
			checkBox.setEnabled(false);
		}
	}

	public String[] getAtrrValues(String fileName) {
		String[] parts = null;
		try {
			BufferedReader in = new BufferedReader(new FileReader("FixedAtrrValues/" + fileName));
			String str;
			while ((str = in.readLine()) != null) {
				parts = str.split(",");
				// System.out.println(parts.length);
			}
			in.close();
		} catch (IOException e) {
		}
		return parts;
	}

	private void medSecBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_medSecBtnActionPerformed
		String fileName = "medAtrrValues.txt";
		String fileWeightName = "medAtrrValuesWeights.txt";

		displayFixedCkBoxList(fileName, fileWeightName);
		CertificationLbl.setText("ISO 27001");
		SpLocationComboFix.setSelectedIndex(0);

		resetSelectors();
		isMed = true;
		selectedPolicy="Medium Security Policy";
		checkPoliciesPanels();
		resetColors();

		fillTxtFields();
		fixedSecurityPanel.setBorder(BorderFactory
				.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)), "Medium Security"));
		medSecBtn.setForeground(Color.red);

	}// GEN-LAST:event_medSecBtnActionPerformed

    private void delRecBtnActionPerformed(java.awt.event.ActionEvent evt) throws Exception {                                          
    	removeSelectedRows(table);
    	
    } 
	private void selectPolicyBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
		doPolicySelection();

	}// GEN-LAST:event_jButton1ActionPerformed

	public void doPolicySelection() {
		Map<String, Double> spStorageSize = new HashMap <String, Double>();
		ArrayList<String> ansList = new ArrayList<String>();
		ArrayList<Double> ansWeightList = new ArrayList<Double>();
		// Temporary fix -- use ArrayList instead of the user HashMap
		for(int i=0;i<availSPsListString.size();i++)// test
			System.out.println("Prin Allo"+availSPsListString.get(i));//test
		boolean pass;
		bid = null;

		pass = validation();
		if (pass) {
			// get Requirment type
			String req;

			if (implicitRadBtn.isSelected()) {
				req = "yes";
			} else {
				req = "no";
			}

			//Get available SPs storage sizes
			double size;
			for (int i=0; i<availSPsListString.size();i++){
				try {
					size = api.getStorageSize(availSPsListString.get(i));
					spStorageSize.put(availSPsListString.get(i), size);
					//System.out.println("Size of "+availSPsListString.get(i)+": "+spStorageSize.entrySet());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// Select Panel
			if (isCustomPanel) {
				// get answers
				ansList = getCustomAnswers();
				ansWeightList = getCustomWeightsAnswers();
				
				try {
					// Create Bid Object
					bid = new CreateBid(req, ansList.get(0), ansList.get(1), ansList.get(2), ansList.get(3),
							ansList.get(4), ansList.get(5), ansList.get(6), ansList.get(7), ansList.get(8),
							ansList.get(9), ansList.get(10), ansList.get(11), ansList.get(12), ansList.get(13),
							ansList.get(14), ansList.get(15),"0", ansWeightList.get(0),
							ansWeightList.get(1), ansWeightList.get(2), ansWeightList.get(3), ansWeightList.get(4),
							ansWeightList.get(5), ansWeightList.get(6), ansWeightList.get(7), ansWeightList.get(8),
							ansWeightList.get(9), ansWeightList.get(10), ansWeightList.get(11), ansWeightList.get(12),
							ansWeightList.get(13), ansWeightList.get(14),availSPsListString,"",selectedPolicy,spStorageSize);
					// need to send bid from here
				} catch (FileNotFoundException ex) {
					Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
					System.out.println(ex.getMessage());
				}
				// if there is a new custom policy save it in an xml file
				sys.saveCustomPolicy(bid); 
			} else {
				
				// check which policy
				if (isMax) {
					for (int i = 0; i < ckBoxList.size(); i++) {
						ansList.add(String.valueOf(ckBoxList.get(i).isSelected()));
					}
					ansList.add(SpLocationComboFix.getSelectedItem().toString());
					ansList.add(CertificationLbl.getText());
					ansList.add(CostTxtFix.getText());

					// Weights imported from file (fixed)
					for (int i = 0; i < weightsList.size(); i++) {
						ansWeightList.add(weightsList.get(i));
					}
				} else if (isMed) {
					for (int i = 0; i < ckBoxList.size(); i++) {
						ansList.add(String.valueOf(ckBoxList.get(i).isSelected()));
					}
					ansList.add(SpLocationComboFix.getSelectedItem().toString());
					ansList.add(CertificationLbl.getText());
					ansList.add(CostTxtFix.getText());

					// Weights
					for (int i = 0; i < weightsList.size(); i++) {
						ansWeightList.add(weightsList.get(i));
					}
				} else if (isLow) {
					for (int i = 0; i < ckBoxList.size(); i++) {
						ansList.add(String.valueOf(ckBoxList.get(i).isSelected()));
					}
					ansList.add(SpLocationComboFix.getSelectedItem().toString());
					ansList.add(CertificationLbl.getText());
					ansList.add(CostTxtFix.getText());

					// Weights
					for (int i = 0; i < weightsList.size(); i++) {
						ansWeightList.add(weightsList.get(i));
					}
				} // End IF
				
				try {
					// Create Bid Object

					bid = new CreateBid(req, ansList.get(0), ansList.get(1), ansList.get(2), ansList.get(3),
							ansList.get(4), ansList.get(5), ansList.get(6), ansList.get(7), ansList.get(8),
							ansList.get(9), ansList.get(10), ansList.get(11), ansList.get(12), ansList.get(13),
							ansList.get(14), ansList.get(15), "0", ansWeightList.get(0),
							ansWeightList.get(1), ansWeightList.get(2), ansWeightList.get(3), ansWeightList.get(4),
							ansWeightList.get(5), ansWeightList.get(6), ansWeightList.get(7), ansWeightList.get(8),
							ansWeightList.get(9), ansWeightList.get(10), ansWeightList.get(11), ansWeightList.get(12),
							ansWeightList.get(13), ansWeightList.get(14),availSPsListString,"",selectedPolicy,spStorageSize);
					// MonitorAssets mon=new MonitorAssets();

				} catch (FileNotFoundException ex) {
					Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
					System.out.println(ex.getMessage());
				}
				
			}
			
			selectPolicyBtn.setText("Loading...");
			//TEST
			for (int i=0; i<availSPsListString.size();i++){
				try {
					System.out.println("Size of "+availSPsListString.get(i)+" 2nd time: "+bid.getSPsSize().entrySet());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			loadGifLbl.setVisible(true);
			Timer timer = new Timer(1000, new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent ev) {
			        // Whatever action you want to happen every 1000 milliseconds
			        loadGifLbl.setVisible(false);
			    }
			});
			timer.start();
			timer.setRepeats(false);
			//test api -- gona need method for that
//			try {
//				api.getStorageSize("CloudMe");
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
			
			selectPolicyBtn.setText("Submit Security Policy");
		} else {
			JOptionPane.showMessageDialog(myframe, "Please fill in the mandatory fields");
		}
	}

	public int runJoptionPane() {
		int result = JOptionPane.showConfirmDialog(this.myframe, "This is the pannel");

		System.out.println("The int is: " + result);

		return result;

	}

	private ArrayList<Double> getCustomWeightsAnswers() {
		ArrayList<Double> weightList = new ArrayList<Double>();
		Double temp;
		for (int i = 0; i < comboBoxList.size(); i++) {
			temp = (double) comboBoxList.get(i).getSelectedIndex();
			weightList.add(temp + 1);
		}
		weightList.add(((double) SpLocationComboWeight.getSelectedIndex()) + 1);
		weightList.add(((double) CertificationWeight.getSelectedIndex()) + 1);
		// System.out.println(weightList.size());
		return weightList;
	}

	public ArrayList<String> getCustomAnswers() {
		ArrayList<String> strList = new ArrayList<String>();
		// Get answers
		String temp;
		for (int i = 0; i < radioBtnList.size(); i++) {
			for (Enumeration<AbstractButton> buttons = radioBtnList.get(i).getElements(); buttons.hasMoreElements();) {
				AbstractButton button = buttons.nextElement();

				if (button.isSelected()) {
					temp = button.getText();
					if (temp.equalsIgnoreCase("Irrelevant")) {
						temp = "/";
					}
					strList.add(temp);

					System.out.println(temp);
				}
			}
		}
		temp = SpLocationCombo.getSelectedItem().toString();
		if (temp.equalsIgnoreCase("Irrelevant")) {
			temp = "/";
		}
		strList.add(temp);

		temp = CertificationTxt.getText();
		if (temp.equalsIgnoreCase("")) {
			temp = "/";
		}
		strList.add(temp);
		strList.add(CostTxtCus.getText());

		return strList;
	}

	public Boolean validation() throws HeadlessException {
		boolean testPass = true;
		// Validation
		if (isCustomPanel) {
			if (CostTxtCus.getText().equals("")) {
				testPass = false;
			}
		} else {
			if (CostTxtFix.getText().equals("")) {
				testPass = false;
			}

		}

		return testPass;
	}

	public void checkPoliciesPanels() {
		if (isCustomPanel) {
			customSecurityPanel.setVisible(false);
			fixedSecurityPanel.setVisible(true);
			isCustomPanel = false;
		} else {
			fixedSecurityPanel.setVisible(true);
		}
	}

	public void resetColors() {
		maxSecBtn.setForeground(Color.BLACK);
		lowSecBtn.setForeground(Color.BLACK);
		medSecBtn.setForeground(Color.BLACK);
		customSecBtn.setForeground(Color.BLACK);
	}

	private void getCurrentUser(User user) {
		Map<String, Boolean> spList = new HashMap<String, Boolean>();
		ArrayList<JCheckBox> loadList = new ArrayList<JCheckBox>();

		
		spList = user.getAvailableSP();

		String temp;
		Iterator it = spList.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			temp = pair.getKey().toString();
			for (int j = 0; j < boxListRtn.size(); j++) {
				if (temp.toLowerCase().contains(boxListRtn.get(j).getText().toLowerCase())) {
//					System.out.println("The tempis : "+temp);
					loadList.add(boxListRtn.get(j));
				}
			}
			it.remove(); // avoids a ConcurrentModificationException
		}

		// Populate form
		currentUsrLb_inputName.setText(user.getName());
		currentUsrLb_inputSurname.setText(user.getSurname());
		
		//Populate assets name+surname
		existUsr_name.setText(user.getName()+ " "+user.getSurname());

		// Tick the selected providers
		for (int i = 0; i < loadList.size(); i++) {
			loadList.get(i).setSelected(true);
		}
	}

	private void resetSelectors() {
		isMax = false;
		isMed = false;
		isLow = false;
		isCust = false;
	}


	
	//load User from XML
	//load custom policy from XML
	private void loadSystem() throws IOException, CloudmeException {
		user = new User();
		sys = new InitializeSystem();
		
		File userFile = new File("UserDatabase/user.xml");
		File custom = new File("customPolicy.xml");
		File customWeights = new File("customPolicyWeights.xml");
		File last = new File("lastPolicy.xml");
		File lastWeights = new File("lastPolicyWeights.xml");
		File assetsListFile = new File("assetsList.xml");
		File spSizes = new File("spSizes.xml");
		if (userFile.exists()) {
			
			this.addUserPane.setVisible(false);
			this.amendUserPanel.setVisible(true);
			user.InitialiseUser();
			
			//Get list of availble SPs
			availSPsListString = getSPstringList();			
			
			//If custom or last policy exist load it
			if (custom.exists() && customWeights.exists() && spSizes.exists()){
				bid = new CreateBid();
				bid = sys.loadPolicyData(custom, customWeights,spSizes);
				
				//Test
			//	System.out.println("Loaded: "+bid.getAuditLogs()+bid.getCost()+bid.getEncryptionAtTransit());
			//	System.out.println("Hash: "+ bid.getSignificance().entrySet());
			//	System.out.println("Latest custom policy loaded");
			} else if (last.exists() && lastWeights.exists() && spSizes.exists()){
				bid = new CreateBid();
				bid = sys.loadPolicyData(last, lastWeights,spSizes);
			
			}
//			//TEST
//			System.out.println("LOADING: "+bid.getSPsSize().entrySet());
			if(assetsListFile.exists()){
				assetsList=sys.loadAssetTab(assetsListFile);			
				sortList();
		        updateTable();
			}
			
			//Load SPs API
			api = new CloudAPIs(availSPsListString);
			working = api.getWorkingSPsList();
			nonWorking = api.getNonWorkingSPsList();
			doSPsync(working,nonWorking,boxListRtn);

			//System.out.println("After Cloud"+user.getAvailableSP().entrySet());
			getCurrentUser(user); // populate panels
		} else {
			this.addUserPane.setVisible(true);
			this.amendUserPanel.setVisible(false);
			for(int i = 0;i<buttonList.size();i++){
				buttonList.get(i).setEnabled(false);
			}
		}
		
	}


	private static void updateTable() {
		model.setRowCount(0);
		for(int x=0; x<table.getColumnModel().getColumnCount(); x++){
			 table.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
		 }
		 
		 for(int i=0; i<assetsList.size(); i++){
				model.addRow(assetsList.get(i));
		 }
	}

	private ArrayList<String> getSPstringList() {
		ArrayList<String> temp = new ArrayList<String>();
		Set set = user.getAvailableSP().entrySet(); //AvailableSp.entrySet();
		Iterator i = set.iterator();
		while (i.hasNext()) {
		    Map.Entry me = (Map.Entry) i.next();
		    System.out.println(me.getKey().toString());
		    temp.add(me.getKey().toString());
		}
		
		
		return temp;
	}
	
	//saves the last policy selected by the user before exiting the system
	public void SaveOnExit(){
			sys.saveLastPolicy(bid);

			if (assetsList!=null){
				//System.out.println("Dame  "+assetsList.get(0));
				sys.saveAssetTab(assetsList);
			}
	}
	
	//sort the assetsList for the table
	public static void sortList() {
		int k = sortCBox.getSelectedIndex();
		if (k == 0) { // by Date
			Collections.sort(assetsList, new Comparator<Object[]>() {
				public int compare(Object[] strings, Object[] otherStrings) {
					return strings[5].toString().compareTo(otherStrings[5].toString());
				}
			});
			Collections.reverse(assetsList); // newest entry to oldest
		}else{ // by File name
			Collections.sort(assetsList, new Comparator<Object[]>() {
				public int compare(Object[] strings, Object[] otherStrings) {
					return strings[0].toString().compareTo(otherStrings[0].toString());
				}
			});
		}
		//
		// for(int i =0; i<assetsList.size();i++){
		// System.out.println("Name: "+assetsList.get(i)[0]);
		// }
		// System.out.println("---");
	}
	
	//updates the asset Tab in GUI with newly matched assets and SPs
	public static void UpdateAssetTab(String Name, String Size, String Req, String Provider, String value){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy  HH:mm");//SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		FileName=Name;
		FileSize=Size;
		SecReq=Req;
		StorageProvider=Provider;
		Price=value;
		
		System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48

		Object[] entry = { FileName, FileSize, SecReq, StorageProvider, Price, dateFormat.format(date)};
		assetsList.add(entry);

		sortList();
		updateTable();
//		for(int x=0; x<table.getColumnModel().getColumnCount(); x++){
//			table.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
//		}
//
//		model.addRow(entry);

		/*
		 * Upload method
		 */
		if(StorageProvider.contains("Local")){
			//Upload locally
			try {
				api.upload_deleteLocalFolder(FileName,true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			//Upload on service provider
			try {
				api.doUpload_Delete(FileName, StorageProvider, true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}			
	}
	
	private void doSPsync(ArrayList<String> working, ArrayList<String> nonWorking, ArrayList<JCheckBox> boxList) {
		String temp;
		for(int i =0; i<working.size();i++){
			temp = working.get(i);
			for(int j = 0; j<boxList.size();j++){
				if(boxList.get(j).getText().equals(temp)){
					boxList.get(j).setForeground(new Color(21, 127, 5));
				}
			}
		}	
		
		for(int i =0; i<nonWorking.size();i++){
			temp = nonWorking.get(i);
			for(int j = 0; j<boxList.size();j++){
				if(boxList.get(j).getText().equals(temp)){
					boxList.get(j).setForeground(new Color(196, 3, 3));
				}
			}
		}
		
		for(int i =0; i<working.size();i++){
			System.out.println("Working SPs: "+working.get(i));
		}
		
		for(int i =0; i<nonWorking.size();i++){
			System.out.println("Non Working SPs: "+nonWorking.get(i));
		}
		
	}
	
	
	//Populate custom form based on bid object
	private void populateForm(){	 
		    System.out.println(bid.getSignificance());
		    if(bid.getAutoSynch().contains("Yes")){
		    	AutoSynchYes.setSelected(true);
		    }else if(bid.getAutoSynch().contains("No")){
		    	AutoSynchNo.setSelected(true);
		    }else{
		    	AutoSynchDont.setSelected(true);
		    }
		    AutoSynchWeight.setSelectedIndex((bid.getSignificance().get("AutoSynch").intValue())-1);
		
		    if(bid.getAuditLogs().contains("Yes")){
		    	AuditLogsYes.setSelected(true);
		    }else if(bid.getAuditLogs().contains("No")){
		    	AuditLogsNo.setSelected(true);
		    }else{
		    	AuditLogsDont.setSelected(true);
		    }
		    AuditLogsWeight.setSelectedIndex((bid.getSignificance().get("AuditLogs").intValue())-1);
			
		    if(bid.getConcealedKeys().contains("Yes")){
		    	ConcealedKeysYes.setSelected(true);
		    }else if(bid.getConcealedKeys().contains("No")){
		    	ConcealedKeysNo.setSelected(true);
		    }else{
		    	ConcealedKeysDont.setSelected(true);
		    }
		    ConcealedKeysWeight.setSelectedIndex((bid.getSignificance().get("EncryptionKeysConcealedFromSP").intValue())-1);
			
		    if(bid.getDifferentKeyPerFile().contains("Yes")){
		    	DifferentKeyPerFileYes.setSelected(true);
		    }else if(bid.getDifferentKeyPerFile().contains("No")){
		    	DifferentKeyPerFileNo.setSelected(true);
		    }else{
		    	DifferentKeyPerFileDont.setSelected(true);
		    }
		    DifferentKeyPerFileWeight.setSelectedIndex((bid.getSignificance().get("DifferentKeyPerFile").intValue())-1);
			
		    if(bid.getEncryptionAtRest().contains("Yes")){
		    	RestEncryptionYes.setSelected(true);
		    }else if(bid.getEncryptionAtRest().contains("No")){
		    	RestEncryptionNo.setSelected(true);
		    }else{
		    	RestEncryptionDont.setSelected(true);
		    }
		    RestEncryptionWeight.setSelectedIndex((bid.getSignificance().get("EncryptionAtRest").intValue())-1);
			
		    if(bid.getEncryptionAtTransit().contains("Yes")){
		    	TransitEncryptionYes.setSelected(true);
		    }else if(bid.getEncryptionAtTransit().contains("No")){
		    	TransitEncryptionNo.setSelected(true);
		    }else{
		    	TransitEncryptionDont.setSelected(true);
		    }
		    TransitEncryptionWeight.setSelectedIndex((bid.getSignificance().get("EncryptionAtTransit").intValue())-1);
			
		    if(bid.getFileVersioning().contains("Yes")){
		    	FileVersioningYes.setSelected(true);
		    }else if(bid.getFileVersioning().contains("No")){
		    	FileVersioningNo.setSelected(true);
		    }else{
		    	FileVersioningDont.setSelected(true);
		    }
		    FileVersioningWeight.setSelectedIndex((bid.getSignificance().get("FileVersioning").intValue())-1);
			
		    if(bid.getPassProtected().contains("Yes")){
		    	PassProtecFilesYes.setSelected(true);
		    }else if(bid.getPassProtected().contains("No")){
		    	PassProtecFilesNo.setSelected(true);
		    }else{
		    	PassProtecFilesDont.setSelected(true);
		    }
		    PassProtecFilesWeight.setSelectedIndex((bid.getSignificance().get("PasswordProtectedFiles").intValue())-1);
			
		    if(bid.getPassRecovery().contains("Yes")){
		    	PassRecoveryYes.setSelected(true);
		    }else if(bid.getPassRecovery().contains("No")){
		    	PassRecoveryNo.setSelected(true);
		    }else{
		    	PassRecoveryDont.setSelected(true);
		    }
		    PassRecoveryWeight.setSelectedIndex((bid.getSignificance().get("CredentialRecovery").intValue())-1);
			
		    if(bid.getPermanentDeletion().contains("Yes")){
		    	PermanentDeletionYes.setSelected(true);
		    }else if(bid.getPermanentDeletion().contains("No")){
		    	PermanentDeletionNo.setSelected(true);
		    }else{
		    	PermanentDeletionDont.setSelected(true);
		    }
		    PermanentDeletionWeight.setSelectedIndex((bid.getSignificance().get("PermanentFileDeletion").intValue())-1);
			
		    if(bid.getProxySupport().contains("Yes")){
		    	ProxySupportYes.setSelected(true);
		    }else if(bid.getProxySupport().contains("No")){
		    	ProxySupportNo.setSelected(true);
		    }else{
		    	ProxySupportDont.setSelected(true);
		    }
		    ProxySupportWeight.setSelectedIndex((bid.getSignificance().get("ProxySupport").intValue())-1);
			
		    if(bid.getSecKeyManagement().contains("Yes")){
		    	SecKeyManagementYes.setSelected(true);
		    }else if(bid.getSecKeyManagement().contains("No")){
		    	SecKeyManagementNo.setSelected(true);
		    }else{
		    	SecKeyManagemenDont.setSelected(true);
		    }
		    SecKeyManagementWeight.setSelectedIndex((bid.getSignificance().get("SecureKeyManagement").intValue())-1);
			
		    if(bid.getShareData().contains("Yes")){
		    	ShareDataYes.setSelected(true);
		    }else if(bid.getShareData().contains("No")){
		    	ShareDataNo.setSelected(true);
		    }else{
		    	ShareDataDont.setSelected(true);
		    }
		    ShareDataWeight.setSelectedIndex((bid.getSignificance().get("ShareData").intValue())-1);
			
		    SpLocationCombo.setSelectedItem(bid.getSPLocation());
		    SpLocationComboWeight.setSelectedIndex((bid.getSignificance().get("DatacenterLocation").intValue())-1);
		    
		    CertificationTxt.setText(bid.getCertification());
		    CertificationWeight.setSelectedIndex((bid.getSignificance().get("Certification").intValue())-1);
		    
		    CostTxtCus.setText(bid.getCost());
		 
	}
	
	public void removeSelectedRows(JTable theTable) throws Exception{
		   DefaultTableModel model = (DefaultTableModel) table.getModel();
		   int[] rows = theTable.getSelectedRows();
		   	System.out.println(rows.length+":"+assetsList.size());
		   	
		   for(int i=0;i<rows.length;i++){
			   
			 Object [] temp=assetsList.get(rows[i]-i);
			 System.out.println(temp[0].toString());
			  assetsList.remove(temp); //remove entry from list
			 			   
			   if(temp[3].toString().toLowerCase().contains("local")){ 
				  api.upload_deleteLocalFolder(temp[0].toString(),false); //remove actual file from local storage
			   }else{
				   api.doUpload_Delete(temp[0].toString(), temp[3].toString(), false); //remove actual file from online cloud storage
			   }
			   
		     model.removeRow(rows[i]-i); //remove entry from gui
		     
		   }
		   
		}
	

	//	private void loadCheckUserEncXML() {
//		File file = new File("user.xml");
//		if(file.exists()) {
//			user = sys.loadUserData();
//			this.addUserPane.setVisible(false);
//			this.amendUserPanel.setVisible(true);
//			
//			// Test
//			System.out.println(user.getName());
//			System.out.println(user.getEmail());
//			System.out.println(user.getSurname());
//			System.out.println(user.getUserId());
//			getCurrentUser(user);
//		}else {
//			this.addUserPane.setVisible(true);
//			this.amendUserPanel.setVisible(false);
//		}
//	}

}