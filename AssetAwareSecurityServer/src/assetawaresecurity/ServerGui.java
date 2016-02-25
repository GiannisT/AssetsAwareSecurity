package assetawaresecurity;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This is the server's main view
 * 
 * @author Giannis Tziakouris
 *
 */
public class ServerGui {
	
	public ServerGui(){
		JFrame frame = new JFrame("Auctioneer");
	    frame.setSize(325, 100);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JPanel panel = new JPanel();
	    frame.add(panel);

	    JLabel label = new JLabel("Auctioneer is live and waiting for bids....");
	    panel.add(label);
	    frame.setVisible(true);
	}

}