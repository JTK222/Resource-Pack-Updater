package net.dark_roleplay.rpupdater.gui;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import net.dark_roleplay.rpupdater.ResourcePackUpdater;
import net.dark_roleplay.rpupdater.gui.components.JMultilineLabel;
import net.dark_roleplay.rpupdater.gui.panels.ChangesSelectionPanel;
import net.dark_roleplay.rpupdater.gui.panels.ResourcePackSelectionPanel;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class MainPane extends JPanel{

	private static final long serialVersionUID = 1L;

	public MainPane(){
		
		Box verticalBox = Box.createVerticalBox();
		ResourcePackSelectionPanel resourcePackSelectionPanel = new ResourcePackSelectionPanel();
		verticalBox.add(resourcePackSelectionPanel);
		ChangesSelectionPanel changesSelectionPanel = new ChangesSelectionPanel();
		verticalBox.add(changesSelectionPanel);
		
		JButton btnNewButton = new JButton("Update Pack");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResourcePackUpdater.update(ChangesSelectionPanel.folderLocation.getText(), ResourcePackSelectionPanel.folderLocation.getText());
			}
		});
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		verticalBox.add(btnNewButton);
		add(verticalBox);
	}
}
