package sapient.lawnmower.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class FileReaderTest {

	@Test
	void test() {
		BufferedReader bfr = null;
		try {
			FileReader file = new FileReader("./src/test/resources/input.txt");
			bfr = new BufferedReader(file);
			StringBuilder bld = new StringBuilder();
			String line;
			
			while ((line = bfr.readLine()) != null) {
				bld.append(line+"\n");
			}
			
			assertEquals("5 5\n" + 
					"1 2 N\n" + 
					"GAGAGAGAA\n" + 
					"3 3 E\n" + 
					"AADAADADDA\n", bld.toString());
			
			bfr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bfr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
