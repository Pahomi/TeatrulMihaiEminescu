package md.convertit.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import md.convertit.services.impl.XmlFileService;
import md.convertit.spectacol.domain.Spectacole;
import md.convertit.spectacol.services.FileService;
import md.convertit.util.DemoData;

public class XmlServiceTest {

	FileService fs = new XmlFileService();
	
	final String PATH = "spectacole.xml";
	final int TOTAL_DEMO_SPECTACOLE = 50;
	@Before
	public void prepare() throws Exception {
		List<Spectacole> spectacolesList = DemoData.getDemoSpectacole(TOTAL_DEMO_SPECTACOLE);
		fs.saveAll(spectacolesList, PATH);
	}

	@Test
	public void readAllTest() throws Exception {
		List<Spectacole> spectacolesList = fs.readAll(PATH);
		assertNotNull(spectacolesList);
		assertFalse(spectacolesList.isEmpty());
		assertEquals(TOTAL_DEMO_SPECTACOLE, spectacolesList.size());
	}
	
	@Test
	public void dateTest(){
		System.out.println(new Date());
		System.out.println(new Date().getTime());
	}
}