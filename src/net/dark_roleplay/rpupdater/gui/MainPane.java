package net.dark_roleplay.rpupdater.gui;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import net.dark_roleplay.rpupdater.gui.panels.ChangesSelectionPanel;
import net.dark_roleplay.rpupdater.gui.panels.ResourcePackSelectionPanel;

public class MainPane extends JTabbedPane{

	private static final long serialVersionUID = 1L;

	public MainPane(){
		this.setTabPlacement(TOP);
		this.addTab("Resource Pack", null, new ResourcePackSelectionPanel(), "Select the location of your resourcepack");
		this.addTab("Changes Json", null, new ChangesSelectionPanel(), "Select the location of changes.json");
	}
	
}
