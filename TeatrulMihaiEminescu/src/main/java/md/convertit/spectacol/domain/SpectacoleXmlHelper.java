package md.convertit.spectacol.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class SpectacoleXmlHelper {
	private List<Spectacole> spectacoleList;

	public List<Spectacole> getSpectacoleList() {
		return spectacoleList;
	}
	@XmlElement
	public void setSpectacoleList(List<Spectacole> spectacoleList) {
		this.spectacoleList = spectacoleList;
	}

	
}
