package net.dark_roleplay.rpupdater.gui;

import javax.swing.JFrame;

public class MainScreen extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public MainScreen (){
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.add(new MainPane());
		this.setVisible(true);
		this.setResizable(false);
	}

}
