/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetawaresecurity;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.*;

import Cloudme.CloudmeException;

/**
 *
 * @author methis
 */
public class AssetAwareSecurity {

	static Gui gui = null;

	public static void setGui(Gui gui) {
		AssetAwareSecurity.gui = gui;
	}

	public Gui getGui() {
		return gui;
	}

	public static void main(String[] args) throws IOException, CloudmeException {

//		CreateBid bid = new CreateBid();
//
//		if (new File("customPolicy.xml").isFile() && new File("customPolicyWeights.xml").isFile()) {
//			bid = init.InitializeCustomPolicy(); // this loads the custom policy
//													// if it exists
//		}
		
		//this thread discovers the OS of the system and the upload speed
		new Thread()
		{
		    public void run() {
		    	InitializeSystem.SystemOSbandwidth();
		    }
		}.start();
		

		// needed for new Java Graphics
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, fall back to cross-platform
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (Exception ex) {
				// not worth my time
			}
		}

		gui = new Gui();
		setGui(gui);
//
//		 AssetAwareSecurity ass=new AssetAwareSecurity();
//		ass.setGui(gui);

		Thread t1 = new Thread(new Runnable() {
			public void run() {
				MonitorAssets mon = new MonitorAssets();

				try {
					mon.monitorFileChanges();
				} catch (IOException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
		});
		t1.start();

		// m.testDialog("DropBox");
	}

}