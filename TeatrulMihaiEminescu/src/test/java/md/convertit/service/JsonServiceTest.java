package md.convertit.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import md.convertit.services.impl.JsonFileService;
import md.convertit.spectacol.domain.Spectacole;
import md.convertit.spectacol.services.FileService;
import md.convertit.util.DemoData;

public class JsonServiceTest {
	FileService fs = new JsonFileService();
	final String PATH = "spectacole.txt";
	final int TOTAL_DEMO_SPECTACOL = 50;
	
	@Before
	public void prepare() throws Exception{
	//metoda care se executa inaintea testelor 	
		List<Spectacole> spectacoleList = DemoData.getDemoSpectacole(TOTAL_DEMO_SPECTACOL);
		fs.saveAll(spectacoleList, PATH);
	}
	@Test
	public void saveAllTest() throws Exception {
		List<Spectacole> userSpectacole = DemoData.getDemoSpectacole(10);
		assertNotNull(userSpectacole);
		fs.saveAll(userSpectacole, PATH);
	}
	@Test
	public void readAllTest() throws Exception {
		List<Spectacole> userSpectacole = fs.readAll(PATH);
		assertNotNull(userSpectacole);
		assertFalse(userSpectacole.isEmpty()); //sa nu fie goala
		assertEquals(TOTAL_DEMO_SPECTACOL, userSpectacole.size());
	}

}


