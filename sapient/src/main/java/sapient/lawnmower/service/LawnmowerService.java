package sapient.lawnmower.service;

import sapient.lawnmower.entity.Lawnmower;

public interface LawnmowerService {
		
	public void readFile();
	public void extractFile();
	public Lawnmower avance(Lawnmower lawnmower);
	public Lawnmower rotate(Lawnmower lawnmower, char direction);
	public Lawnmower travel(Lawnmower lawnmower);

}
