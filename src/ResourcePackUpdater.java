import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import com.google.gson.JsonObject;

public class ResourcePackUpdater {
	
	JsonObject updateJson;
	public static PrintStream OUT;
	
	public static void main(String[] args) throws FileNotFoundException{
		OUT = System.out;
		String pathUpdateFile = "";
		String pathAssets = "";
		if(args.length == 1){
			pathUpdateFile = args[0];
			while(pathAssets.isEmpty()){
				OUT.println("Please enter the path to your assets folder:");
				pathAssets = inputString();
			}
		}else if(args.length == 0){
			while(pathUpdateFile.isEmpty()){
				OUT.println("Please enter the path to a update json:");
				pathUpdateFile = inputString();
			}
			while(pathAssets.isEmpty()){
				OUT.println("Please enter the path to your assets folder:");
				pathAssets = inputString();
			}
		}else{
			pathUpdateFile = args[0];
			pathAssets = args[1];
		}
		
		UpdateJson uJson = new UpdateJson(pathUpdateFile);
		uJson.renameFiles(pathAssets);
		uJson.renameContent(pathAssets);
		uJson.moveBlockStates(pathAssets);
		System.out.println("Renamed " + uJson.renames + " Files!");
		System.out.println("Changed " + uJson.changes + " Lines!");
		System.out.println("Moved " + uJson.moves + " Blockstates!");
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
