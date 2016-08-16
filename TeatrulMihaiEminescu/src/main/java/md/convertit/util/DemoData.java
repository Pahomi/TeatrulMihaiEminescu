package md.convertit.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import md.convertit.spectacol.domain.Spectacole;


public class DemoData {

	public static List<Spectacole> getDemoSpectacole(int tOTAL_DEMO_SPECTACOL) {
		List<Spectacole> list = new ArrayList<Spectacole>();
		
		Random random = new Random();
		
		for (int i = 0; i <tOTAL_DEMO_SPECTACOL; i++) {
			Spectacole spectacole = new Spectacole();
			
			spectacole.setId(random.nextLong());
			spectacole.setName("Iubire la prosti!" + random.nextInt(45));
			spectacole.setData(RandomData.getrandomDate());
			spectacole.setPremiere(random.nextBoolean());
			spectacole.setSeatsAvailable(random.nextInt());
			list.add(spectacole);
		}
		return list;
	}

}
