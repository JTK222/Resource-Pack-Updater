package net.dark_roleplay.rpupdater.gui;

import javax.swing.JFrame;

public class MainScreen extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public MainScreen (){
		setTitle("Dark Roleplay Resource Pack Updater");
		this.setSize(600, 400);
		this.setContentPane(new MainPane());
//		this.pack();
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

}
