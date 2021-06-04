package sapient.lawnmower.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import sapient.lawnmower.entity.Lawnmower;

public class LawnmowerServiceImpl implements LawnmowerService {

	private static Logger logger = Logger.getLogger(LawnmowerServiceImpl.class.getName());

	private List<String> lines = new ArrayList<>();
	private List<Lawnmower> lawnmowerList = new ArrayList<>();
	private int heightField = 0;
	private int weightField = 0;

	public LawnmowerServiceImpl() {
		// constructor
	}

	// Lit chaque ligne du fichier input
	public void readFile() {
		FileReader file = null;
		BufferedReader bfr = null;
		try {
			file = new FileReader("./src/main/resources/input.txt");
			bfr = new BufferedReader(file);
			String line;
			while ((line = bfr.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage());
		} finally {
			try {
				bfr.close();
				file.close();
			} catch (IOException | NullPointerException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
		}
	}

	// Permet l'extration des inputs
	public void extractFile() {
		String[] values;
		Lawnmower tempLawnmower = null;

		readFile();

		for (int i = 0; i < lines.size(); i++) {
			values = lines.get(i).split(" ");
			// get field size
			if (values.length == 2) {
				setHeightField(Integer.parseInt(values[0]));
				setWeightField(Integer.parseInt(values[1]));

				// get each Lawnmower
			} else if (values.length == 3) {
				tempLawnmower = new Lawnmower(Integer.parseInt(values[0]), Integer.parseInt(values[1]),
						values[2].charAt(0));
				i++;
				tempLawnmower.setWay(lines.get(i));
				lawnmowerList.add(tempLawnmower);
			}
		}
	}

	public int getHeightField() {
		return heightField;
	}

	public void setHeightField(int heightField) {
		this.heightField = heightField;
	}

	public int getWeightField() {
		return weightField;
	}

	public void setWeightField(int weightField) {
		this.weightField = weightField;
	}

	public List<Lawnmower> getLawnmowerList() {
		return lawnmowerList;
	}

	public void setLawnmowerList(List<Lawnmower> lawnmowerList) {
		this.lawnmowerList = lawnmowerList;
	}

	// Permet à la tondeuse d'avancer
	public Lawnmower avance(Lawnmower lawnmower) {
		int positionX = lawnmower.getCoorX();
		int positionY = lawnmower.getCoorY();
		switch (lawnmower.getOrientation()) {
		case 'N':
			if (positionY < heightField) {
				positionY++;
				lawnmower.setCoorY(positionY);
			}
			break;
		case 'S':
			if (positionY > 0) {
				positionY--;
				lawnmower.setCoorY(positionY);
			}
			break;
		case 'W':
			if (positionX > 0) {
				positionX--;
				lawnmower.setCoorX(positionX);
			}
			break;
		case 'E':
			if (positionX < weightField) {
				positionX++;
				lawnmower.setCoorX(positionX);
			}
			break;
		default:
			logger.log(Level.SEVERE, "Erreur : Orientation inconnue");
			break;
		}

		return lawnmower;
	}

	// Permet à la tondeuse de changer de direction
	public Lawnmower rotate(Lawnmower lawnmower, char direction) {
		switch (lawnmower.getOrientation()) {
		case 'N':
			if (direction == 'D') {
				lawnmower.setOrientation('E');
			} else if (direction == 'G') {
				lawnmower.setOrientation('W');
			}
			break;
		case 'S':
			if (direction == 'D') {
				lawnmower.setOrientation('W');
			} else if (direction == 'G') {
				lawnmower.setOrientation('E');
			}
			break;
		case 'W':
			if (direction == 'D') {
				lawnmower.setOrientation('N');
			} else if (direction == 'G') {
				lawnmower.setOrientation('S');
			}
			break;
		case 'E':
			if (direction == 'D') {
				lawnmower.setOrientation('S');
			} else if (direction == 'G') {
				lawnmower.setOrientation('N');
			}
			break;
		default:
			logger.log(Level.SEVERE, "Erreur : Orientation inconnue");
			break;
		}
		return lawnmower;
	}

	// Permet à la tondeuse de parcourir un chemin
	public Lawnmower travel(Lawnmower lawnmower) {
		for (char command : lawnmower.getWay().toCharArray()) {
			if (command == 'A') {
				lawnmower = avance(lawnmower);
			} else if (command == 'D' || command == 'G') {
				lawnmower = rotate(lawnmower, command);
			}
		}
		return lawnmower;
	}

}
