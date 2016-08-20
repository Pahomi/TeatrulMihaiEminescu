package md.convertit.dao.impl;

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
//		spectacole.setName("Gogoasa");
//		spectacole.setSeatsAvailable(85);
//		spectacole.setPremiere(true);
//		spectacole.setData(new Date());
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
		final Long USER_ID = 1L;
		Spectacole newSpectacole = new Spectacole();
		newSpectacole.setName("");
		//newSpectacole.setSeatsAvailable();
//		User newUser = new User();
//		newUser.setUsername("new_admin");
//		newUser.setEmail("new_amin@mail.com");
//		newUser.setPassword("new_password");
//		boolean result = dao.update(newUser, USER_ID);
//		//verificam result sa fie true
		//Assert.assertTrue(result);
	}
	
}
