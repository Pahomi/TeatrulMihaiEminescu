package md.convertit.services.impl;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import md.convertit.spectacol.domain.Spectacole;
import md.convertit.spectacol.services.FileService;

public class JsonFileService implements FileService {
	private static final Logger log = Logger.getLogger(JsonFileService.class.getName());

	private Gson gson = new Gson();
	private File file;
	
	@Override
	public void saveAll(List<Spectacole> spectacole, String path) throws Exception {
		file = new File(path);
		FileWriter fw = new FileWriter(file);
		gson.toJson(spectacole, fw);
		fw.close();
		log.log(Level.INFO, "objects saved to: " + file.getAbsolutePath());
		
	}

	@Override
	public List<Spectacole> readAll(String path) throws Exception {
		file = new File(path);
		// pregatim file reader
		FileReader fr = new FileReader(file);
		Type type = new TypeToken<List<Spectacole>>() {}.getType();
		List<Spectacole> spectacole = gson.fromJson(fr, type);
		log.log(Level.INFO, String.format("Loaded from file %s total %d objects", file.getAbsolutePath(),spectacole.size()));
		return spectacole;
	}


}
