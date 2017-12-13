import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

public class UpdateJson {

	private final Map<String, String> renameFiles = new HashMap<String, String>();
	private final Map<String, String> renameTextures = new HashMap<String, String>();
	private final Map<String, String> renameModels = new HashMap<String, String>();
	private final List<String> splits = new ArrayList<String>();
	private final List<String> remove = new ArrayList<String>();

	public int renames = 0;
	public int changes = 0;
	public int moves = 0;

	private static final String RENAME = "rename";
	private static final String REMOVE = "remove";
	private static final String TEXTURES = "textures";
	private static final String MODELS = "models";
	private static final String SPLIT = "split";

	private static final Charset charset = StandardCharsets.UTF_8;

	public UpdateJson(String path) {
		JsonObject obj = getJson(path);
		if (obj == null) {
			return;
		} else {
			if (obj.has(RENAME)) {
				JsonObject rename = obj.get(RENAME).getAsJsonObject();
				for (String key : rename.keySet()) {
					addToFileRename("", key, rename.get(key));
					if (key.equals(TEXTURES)) {
						addToTextures("", "", rename.get(key));
					} else if (key.equals(MODELS)) {
						addToModels("", "", rename.get(key));
					}
				}
			}
			if (obj.has(REMOVE)) {
				JsonObject remove = obj.get(REMOVE).getAsJsonObject();

			}
			if (obj.has(SPLIT)) {
				JsonObject split = obj.get(SPLIT).getAsJsonObject();
				for (String key : split.keySet()) {
					addToSplit("", key, split.get(key));
				}
			}
		}
		System.out.println(renameTextures);
	}

	private void addToTextures(String parent, String parentKey, JsonElement elm) {
		if (elm.isJsonObject()) {
			JsonObject rename = elm.getAsJsonObject();
			for (String key : rename.keySet()) {
				addToTextures(parent + "/" + parentKey, key, rename.get(key));
			}
		} else {
			renameTextures.put((parent + "/" + parentKey).replaceFirst("//", "").replace(".png", ""),
					(parent + "/" + elm.getAsString()).replaceFirst("//", "").replace(".png", ""));
		}
	}

	private void addToModels(String parent, String parentKey, JsonElement elm) {
		if (elm.isJsonObject()) {
			JsonObject rename = elm.getAsJsonObject();
			for (String key : rename.keySet()) {
				addToModels(parent + "/" + parentKey, key, rename.get(key));
			}
		} else {
			renameModels.put((parent + "/" + parentKey).replaceFirst("/", ""),
					(parent + "/" + elm.getAsString()).replaceFirst("/", ""));
		}
	}

	private void addToFileRename(String parent, String parentKey, JsonElement elm) {
		if (elm.isJsonObject()) {
			JsonObject rename = elm.getAsJsonObject();
			for (String key : rename.keySet()) {
				addToFileRename(parent + "/" + parentKey, key, rename.get(key));
			}
		} else {
			renameFiles.put(parent + "/" + parentKey, parent + "/" + elm.getAsString());
		}
	}
	
	private void addToSplit(String parent, String parentKey, JsonElement elm){
		if (elm.isJsonObject()) {
			JsonObject rename = elm.getAsJsonObject();
			for (String key : rename.keySet()) {
				addToFileRename(parent + "/" + parentKey, key, rename.get(key));
			}
		} else {
			JsonArray arr = elm.getAsJsonArray();
			for(int i = 0; i < arr.size(); i++){
				splits.add(parent + "/" + parentKey + "/" + arr.get(i).getAsString());
			}
		}
	}

	public void renameFiles(String pathAssets) {
		for (String key : renameFiles.keySet()) {
			File rename = new File(pathAssets + key);
			if (rename.exists()) {
				File newName = new File(pathAssets + renameFiles.get(key));
				if (!newName.exists()) {
					rename.renameTo(newName);
					renames++;
				}
			}
		}
	}

	public void renameContent(String path2) {
		File file = new File(path2);
		if (file.exists()) {
			if (file.isDirectory()) {
				for (File file2 : file.listFiles()) {
					renameContent(file2.getPath());
				}
			} else {
				if (file.getPath().endsWith(".json")) {
					System.out.println("Scanning: " + file.getPath());
					Path path = Paths.get(file.getPath());
					try {
						String content = new String(Files.readAllBytes(path), charset);
						for (String key : renameTextures.keySet()) {
							if(content.contains(renameTextures.get(key)))
								changes++;
							content = content.replaceAll(key, renameTextures.get(key));
						}
						for (String key : renameModels.keySet()) {
							if(content.contains(renameModels.get(key)))
								changes++;
							content = content.replaceAll(key, renameModels.get(key));
						}
						Files.write(path, content.getBytes(charset));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void moveBlockStates(String path){
		for (String key : splits) {
			File rename = new File(path + key);
			if (rename.exists()) {
				File newName = new File(path + "/MANUEL_EDIT/" + key);
				newName.getParentFile().mkdirs();
				if (!newName.exists()) {
					try {
						Files.move(rename.toPath(), newName.toPath());
						moves++;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private JsonObject getJson(String path) {
		try {
			Gson gson = new Gson();
			JsonReader reader = new JsonReader(new FileReader(path));
			JsonObject obj = gson.fromJson(reader, JsonObject.class);
			return obj;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
