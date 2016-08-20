package md.convertit.dao;

import java.util.List;


import md.convertit.spectacol.domain.Spectacole;

public interface SpectacoleDao {

	boolean save(Spectacole spectacole);

	List<Spectacole> findAll();
	boolean update(Spectacole newSpectacole, Long id);
	
	boolean delete(Long id);
}
