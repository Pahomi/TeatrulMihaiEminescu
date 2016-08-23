package md.convertit.dao.impl;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import md.convertit.dao.SpectacoleDao;
import md.convertit.spectacol.domain.Spectacole;
import md.convertit.util.DemoData;

public class SpectacoleDaoImplTest {

	private SpectacoleDao dao = new SpectacoleDaoImpl();
	
	@Test
	public void testSave(){
		List<Spectacole> data = DemoData.getDemoSpectacole(1);
		Spectacole spectacole = data.get(0);
		dao.save(spectacole);
	}
	@Test
	public void testFindAll(){
		List<Spectacole> allSpect = dao.findAll();
		Assert.assertNotNull(allSpect);
		Assert.assertFalse(allSpect.isEmpty());
		Assert.assertTrue(allSpect.size()>0);
	}
	@Test
	public void testUpdate(){
		final Long Spectacole_ID = 1L;
		Spectacole newSpectacole = new Spectacole();
		newSpectacole.setName("Ultima noapte la Madrid");
		newSpectacole.setSeatsAvailable(89);
		newSpectacole.setId(Spectacole_ID);
		newSpectacole.setPremiere(true);
		newSpectacole.setData(new Date());
		boolean result = dao.update(newSpectacole, Spectacole_ID);
		Assert.assertTrue(result);
	}
	@Test
	public void testDelete(){
		final Long Spectacole_ID = 3L;
		boolean result = dao.delete(Spectacole_ID);
		Assert.assertTrue(result);
	}
	
}
