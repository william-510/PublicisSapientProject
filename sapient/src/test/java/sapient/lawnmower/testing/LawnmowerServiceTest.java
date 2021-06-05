package sapient.lawnmower.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import sapient.lawnmower.entity.Lawnmower;

class LawnmowerServiceTest {

	private static ArrayList<String> lines = new ArrayList<String>();
	private static ArrayList<Lawnmower> lawnmowerList = new ArrayList<Lawnmower>();
	private static int heightField;
	private static int weightField;

	@BeforeAll
	public static void readFile() {
		FileReader file = null;
		BufferedReader bfr = null;
		try {
			file = new FileReader("./src/test/resources/input.txt");
			bfr = new BufferedReader(file);
			String line;
			while ((line = bfr.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bfr.close();
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void ExtractFile() {
		String[] values;
		Lawnmower tempLawnmower = null;

		for (int i = 0; i < lines.size(); i++) {
			values = lines.get(i).split(" ");
			// get field size
			if (values.length == 2) {
				heightField = Integer.parseInt(values[0]);
				weightField = Integer.parseInt(values[1]);

				// get each Lawnmower
			} else if (values.length == 3) {
				tempLawnmower = new Lawnmower(Integer.parseInt(values[0]), Integer.parseInt(values[1]),
						values[2].charAt(0));
			} else if (!values.toString().contains(" ")) {
				tempLawnmower.setWay(lines.get(i));
				lawnmowerList.add(tempLawnmower);
			} else {
				System.out.println("Erreur : problème détecté dans le formatage du fichier");
			}
		}
	}

	@Test
	void testExtraction() {

		Lawnmower testLawnmower = new Lawnmower(1, 2, 'N');
		testLawnmower.setWay("GAGAGAGAA");

		ExtractFile();

		assertEquals(5, heightField);
		assertEquals(5, weightField);
		assertEquals(testLawnmower.getCoorX(), lawnmowerList.get(0).getCoorX());
		assertEquals(testLawnmower.getCoorY(), lawnmowerList.get(0).getCoorY());
		assertEquals(testLawnmower.getOrientation(), lawnmowerList.get(0).getOrientation());
		assertEquals(testLawnmower.getWay(), lawnmowerList.get(0).getWay());

	}

	public Lawnmower avance(Lawnmower lawnmower) {
		// Dimension champ
		int heightField = 5;
		int weightField = 5;

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
			System.out.println("Erreur : Orientation inconnue");
			break;
		}

		return lawnmower;
	}

	@Test
	void testDeplacementAvance() {
		List<Lawnmower> lawnmowerList = new ArrayList<Lawnmower>();
		lawnmowerList.add(new Lawnmower(2, 2, 'N'));
		lawnmowerList.add(new Lawnmower(2, 2, 'S'));
		lawnmowerList.add(new Lawnmower(2, 2, 'W'));
		lawnmowerList.add(new Lawnmower(2, 2, 'E'));
		lawnmowerList.add(new Lawnmower(5, 0, 'E'));
		lawnmowerList.add(new Lawnmower(0, 5, 'N'));

		for (Lawnmower lawnmower : lawnmowerList) {
			lawnmower.setWay("A");
			avance(lawnmower);
		}
		// 1
		assertEquals(2, lawnmowerList.get(0).getCoorX()); // X location
		assertEquals(3, lawnmowerList.get(0).getCoorY()); // Y location
		// 2
		assertEquals(2, lawnmowerList.get(1).getCoorX()); // X location
		assertEquals(1, lawnmowerList.get(1).getCoorY()); // Y location
		// 3
		assertEquals(1, lawnmowerList.get(2).getCoorX()); // X location
		assertEquals(2, lawnmowerList.get(2).getCoorY()); // Y location
		// 4
		assertEquals(3, lawnmowerList.get(3).getCoorX()); // X location
		assertEquals(2, lawnmowerList.get(3).getCoorY()); // Y location
		// 5
		assertEquals(5, lawnmowerList.get(4).getCoorX()); // X location
		assertEquals(0, lawnmowerList.get(4).getCoorY()); // Y location
		// 6
		assertEquals(0, lawnmowerList.get(5).getCoorX()); // X location
		assertEquals(5, lawnmowerList.get(5).getCoorY()); // Y location

	}
	
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
			System.out.println("Erreur : Orientation inconnue");
			break;
		}
		return lawnmower;
	}
	
	@Test
	void testChangementOrientation() {
		List<Character> directionList = new ArrayList<Character>();
		directionList.add('D');
		directionList.add('D');
		directionList.add('G');
		Lawnmower lawnmower = new Lawnmower(1,1,'N');
		for (int i=0; i>2 ; i++)
		{
			rotate(lawnmower,directionList.get(i));
			if (i == 0) {
				assertEquals('E', lawnmower.getOrientation());
			} else if (i == 1) {
				assertEquals('S', lawnmower.getOrientation());
			} else if (i == 2) {
				assertEquals('E', lawnmower.getOrientation());
			}
		}
	}
	
	public Lawnmower travel(Lawnmower lawnmower) {
		for (char command : lawnmower.getWay().toCharArray()) {
			if (command == 'A') {
				System.out.println("Avance");
				lawnmower = avance(lawnmower);
			} else if (command == 'D' || command == 'G') {
				System.out.println("Rotate - "+command);
				lawnmower = rotate(lawnmower, command);
			}
		}
		return lawnmower;
	}
	
	@Test
	void testParcourirChemin() {
		Lawnmower lawnmower = new Lawnmower(1, 2, 'N');
		lawnmower.setWay("GAGAGAGAA");
		
		travel(lawnmower);
		
		assertEquals(1, lawnmower.getCoorX());
		assertEquals(3, lawnmower.getCoorY());
		assertEquals('N', lawnmower.getOrientation());
	}

}
