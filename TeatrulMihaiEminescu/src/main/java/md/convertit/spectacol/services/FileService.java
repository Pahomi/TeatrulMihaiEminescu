package md.convertit.spectacol.services;

import java.util.List;
import md.convertit.spectacol.domain.Spectacole;

public interface FileService {
	void saveAll(List<Spectacole> spectacole, String path) throws Exception;

	List<Spectacole> readAll(String path) throws Exception;
}
