package net.dark_roleplay.rpupdater.gui;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JSeparator;

import net.dark_roleplay.rpupdater.gui.components.JMultilineLabel;

public class StatsFrame extends JFrame{
	
	public StatsFrame(int renamed, int changed, int moved) {
		getContentPane().setLayout(null);
		
		JLabel lblS = new JLabel("Stats:");
		lblS.setBounds(10, 11, 280, 14);
		getContentPane().add(lblS);
		
		JLabel lblRenamedFilesd = new JLabel(String.format("Renamed files: %d", renamed));
		lblRenamedFilesd.setBounds(20, 28, 243, 14);
		getContentPane().add(lblRenamedFilesd);
		
		JLabel lblChangedFilesd = new JLabel(String.format("Changed files: %d", changed));
		lblChangedFilesd.setBounds(20, 46, 201, 14);
		getContentPane().add(lblChangedFilesd);
		
		JLabel lblMovedFilesd = new JLabel(String.format("Moved files: %d", moved));
		lblMovedFilesd.setBounds(20, 66, 168, 14);
		getContentPane().add(lblMovedFilesd);
		
		JMultilineLabel lblNewLabel = new JMultilineLabel("New label");
		lblNewLabel.setText("Disclamer:\r\nThis tool by far isn't perfect yet. You will probably need to still fix a lot of things. \r\nHowever if your resource pack just changes textures most stuff should work. But test everything before you publish.\r\n\r\nAlso you are not allowed to publish resource packs that aren't yours just because you updated them with this tool!");
		lblNewLabel.setBounds(10, 91, 395, 138);
		getContentPane().add(lblNewLabel);
		this.setLocationRelativeTo(null);
		this.setSize(new Dimension(431, 279));
	}
}
