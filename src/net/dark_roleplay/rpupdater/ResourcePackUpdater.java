package net.dark_roleplay.rpupdater;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import javax.swing.UIManager;

import com.google.gson.JsonObject;

import net.dark_roleplay.rpupdater.gui.MainScreen;
import net.dark_roleplay.rpupdater.gui.StatsFrame;

public class ResourcePackUpdater {
	
	JsonObject updateJson;
	public static PrintStream OUT;
	
	
	//G:\Coding\Resource Pack Updater\TestPack\assets\minecraft
	//G:\Coding\Resource Pack Updater\ResourcePackUpdater\changes.json
	public static void main(String[] args) throws FileNotFoundException{
		try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    }catch(Exception ex) {
	        ex.printStackTrace();
	    }
		MainScreen screen = new MainScreen();
		return;
	}
	
	public static void update(String pathUpdateFile, String pathAssets) {
		OUT = System.out;
		
		UpdateJson uJson = new UpdateJson(pathUpdateFile);
		uJson.renameContent(pathAssets);
		uJson.moveBlockStates(pathAssets);
		uJson.renameFiles(pathAssets);
		uJson.renameFolders(pathAssets);
		System.out.println("Update Stats:");
		System.out.println(String.format("Renamed %d Files", uJson.renames));
		System.out.println(String.format("Changed %d Files", uJson.changes));
		System.out.println(String.format("Moved %d Files", uJson.moves));
		new StatsFrame(uJson.renames, uJson.changes, uJson.moves).setVisible(true);
	}
	
	private static String inputString(){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}
