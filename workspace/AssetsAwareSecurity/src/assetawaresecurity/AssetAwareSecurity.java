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
 * Java Class that contains the main method of the program. It also 
 * evoke a thread for monitoring new file entries.
 * 
 * @author Marios Zinonos, Giannis Tziakouris
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
			}
		}

		gui = new Gui();
		setGui(gui);


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

	}

}