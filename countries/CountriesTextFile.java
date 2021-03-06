package countries;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountriesTextFile extends Country {

	

	
	public CountriesTextFile(String name, double popluation) {
		super(name, popluation);
	}

	public static Path filePath = Paths.get("countries.txt");


	public static void fileExist(String item) throws IOException {
		if (Files.notExists(filePath)) {
			Files.createFile(filePath);
		}
	}


	public static List<Country> readFile() {

		try {
			List<String> lines = Files.readAllLines(filePath);
			List<Country> countries = new ArrayList<>();
			for (String line : lines) {
				String[] parts = line.split("\t");
				Country c = new CountriesTextFile(line, 0);
				c.setName(parts[0]);
				c.setPopulation(Double.parseDouble(parts[1]));
				countries.add(c);
			}
			return countries;

		} catch (NoSuchFileException ex) {
			return new ArrayList<>();
		} catch (IOException ex) {
			ex.printStackTrace();
			return new ArrayList<>();
		}
	}

	

	public static void addToEndOfFile(Country countries) throws IOException {
		if (Files.notExists(filePath)) {
			Files.createFile(filePath);
		}

		String line = countries.getName() + "\t" + countries.getPopulation();

		List<String> linesToAdd = Arrays.asList(line);

		Files.write(filePath, linesToAdd, StandardOpenOption.APPEND);
	}

}