package md.convertit.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import md.convertit.services.impl.ExcelFileService;
import md.convertit.spectacol.domain.Spectacole;
import md.convertit.spectacol.services.FileService;
import md.convertit.util.DemoData;

public class ExcelServiceTest {
FileService fs = new ExcelFileService();
final String PATH = "spectacole.xls";
final int TOTAL_DEMO_SPECTACOLE = 50;
@Before
public void prepare() throws Exception {
	List<Spectacole> spectacolesList = DemoData.getDemoSpectacole(TOTAL_DEMO_SPECTACOLE);
	fs.saveAll(spectacolesList, PATH);

}
@Test
public void saveAllTest() throws Exception {
	List<Spectacole> userSpectacole = DemoData.getDemoSpectacole(TOTAL_DEMO_SPECTACOLE);
	assertNotNull(userSpectacole);
	fs.saveAll(userSpectacole, PATH);
}
@Test
public void readAllTest() throws Exception {
	List<Spectacole> userSpectacole = fs.readAll(PATH);
	assertNotNull(userSpectacole);
	assertFalse(userSpectacole.isEmpty()); 
	assertEquals(TOTAL_DEMO_SPECTACOLE, userSpectacole.size());
}
}