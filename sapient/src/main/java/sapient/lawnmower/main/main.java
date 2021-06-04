package sapient.lawnmower.main;

import java.util.logging.Logger;

import sapient.lawnmower.entity.Lawnmower;
import sapient.lawnmower.service.LawnmowerServiceImpl;

public class main {
	
	public static Logger logger = Logger.getLogger(main.class.getName());

	public static void main(String[] args) {
		LawnmowerServiceImpl service = new LawnmowerServiceImpl();
		
		//Extrait les données du fichier
		service.extractFile();
		
		//Fait parcourir le chemin de chacune des tondeuses
		for (Lawnmower lawnmower : service.getLawnmowerList()) {
			lawnmower = service.travel(lawnmower);
			System.out.println(lawnmower.getCoorX()+" "+lawnmower.getCoorY()+" "+lawnmower.getOrientation());
		}
	}

}
