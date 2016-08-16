package md.convertit.services.impl;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import md.convertit.spectacol.domain.Spectacole;
import md.convertit.spectacol.domain.SpectacoleXmlHelper;
import md.convertit.spectacol.services.FileService;

public class XmlFileService implements FileService {

	private static final Logger log = Logger.getLogger(XmlFileService.class.getName());
	private File file;

	@Override
	public void saveAll(List<Spectacole> spectacole, String path) throws Exception {
		file = new File(path);
		JAXBContext jaxbcontext = JAXBContext.newInstance(SpectacoleXmlHelper.class);
		Marshaller marshaller = jaxbcontext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		SpectacoleXmlHelper helper = new SpectacoleXmlHelper();
		helper.setSpectacoleList(spectacole);
		marshaller.marshal(helper, file);
		log.log(Level.INFO, String.format("saved objects in file: %s", file.getAbsolutePath()));

	}

	@Override
	public List<Spectacole> readAll(String path) throws Exception {
		file = new File(path);
		JAXBContext jaxbcontext = JAXBContext.newInstance(SpectacoleXmlHelper.class);
		Unmarshaller unmarshaller = jaxbcontext.createUnmarshaller();
		SpectacoleXmlHelper helper =(SpectacoleXmlHelper) unmarshaller.unmarshal(file);
		log.log(Level.INFO, String.format("Return total of %d objects from %s", helper.getSpectacoleList().size(),
				file.getAbsolutePath()));
		return helper.getSpectacoleList();
		
	}

}
