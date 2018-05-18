package net.dark_roleplay.rpupdater.gui.panels;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import net.dark_roleplay.rpupdater.gui.components.JMultilineLabel;

public class ResourcePackSelectionPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public static JMultilineLabel informationLbl;
	public static JTextField folderLocation;
	public static JButton folderSelection;
	
	public ResourcePackSelectionPanel() {
		this.setLayout(null);

		informationLbl = new JMultilineLabel(
				"Select your Resource Pack Folder, \n" + "the resource pack shouldn't be in a zip file.\n"
						+ "and you should choose the \"/assets/minecraft/\" folder within in.");
		informationLbl.setSize(450, 60);
		informationLbl.setLocation(10, 10);
		this.add(informationLbl);

		folderLocation = new JTextField();
		folderLocation.setSize(350, 20);
		folderLocation.setLocation(10, 80);
		this.add(folderLocation);

		folderSelection = new JButton("Select");
		folderSelection.setSize(100, 20);
		folderSelection.setLocation(370, 80);
		folderSelection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent arg0) {
				final JFileChooser fc = new JFileChooser(){
					public void approveSelection() {
		                if (getSelectedFile().isFile()) {
		                    return;
		                } else
		                    super.approveSelection();
		            }
				};
				fc.setFileSelectionMode( JFileChooser.FILES_AND_DIRECTORIES );
				int returnVal = fc.showOpenDialog(ResourcePackSelectionPanel.this);
				if(returnVal == 0){
					folderLocation.setText(fc.getSelectedFile().getPath());
				}
			}
		});
		this.add(folderSelection);

	}

}
