package es.uniovi.asw.parser;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.transaction.NotSupportedException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.persistence.CitizenRepository;
import es.uniovi.asw.persistence.Insert;

public class Parser {
	private final static String PDF_COMMAND = "pdf";
	private final static String TXT_COMMAND = "txt";
	private final static String DOCX_COMMAND = "docx";

	public static CitizenRepository citizenRepository;
	private static ReadCitizens reader;
	private static LetterGen letterGen;

	// We pass here the inputs in the command line in order to generate
	// different writeformats
	@Autowired
	public static void run(String[] args) {

		String[] filePath = null;
		String letterFormat = null;
		CommandLineParser parser = null;
		CommandLine cmdLine = null;

		// CLI program options
		Options options = new Options();
		Option inputFileOption = new Option("f", true, "Input files location. Multiple allowed");
		inputFileOption.setArgs(Option.UNLIMITED_VALUES);
		options.addOption(inputFileOption);
		options.addOption("l", true, "Letter generation format. pdf, txt, and docx.");
		options.addOption("h", "help", false, "Display this help message.");

		// Text
		String helpExample = "citizensLoader.jar -f excel1.xlsx -f excel2.xlsx -l pdf";

		// Parsing the arguments and checking that they're valid
		try {
			parser = new DefaultParser();
			cmdLine = parser.parse(options, args);
			HelpFormatter formatter = new HelpFormatter();

			// If there is the help flag then show the help and return.
			if (cmdLine.hasOption("h")) {
				formatter.printHelp(helpExample, options);
				return;
			}

			// Check that the file has been specified.
			if (cmdLine.hasOption("f")) {
				filePath = cmdLine.getOptionValues("f");
			} else {
				System.out.println("You must specify a file.");
				formatter.printHelp(helpExample, options);
				return;
			}
			// Check that the letter format has been specified.
			if (cmdLine.hasOption("l")) {
				letterFormat = cmdLine.getOptionValue("l").trim();
			} else {
				System.out.println("You must specify a letter generation format.");
				formatter.printHelp(helpExample, options);
				return;
			}
		} catch (ParseException e1) {
			System.out.println("The parameters were incorrectly formatted.");
			return;
		}

		// We start executing the program.
		System.out.println("Letter generation format: " + letterFormat);
		try {
			for (String sFilePath : filePath) {
				sFilePath = sFilePath.trim();
				System.out.println("File location: " + sFilePath);
				createReader(sFilePath);
				// the command line executing syntax is mode path
				List<CitizenInfo> citizenInfo = reader.readCitizens(sFilePath);
				Insert inserter = new InsertR();
				List<Citizen> letCit = inserter.insert(citizenInfo);
				// Generate the letters
				generateLetter(letCit, letterFormat);
			}
		} catch (NotSupportedException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Undefined error");
		}
	}

	/**
	 * selects a type of reader depending on the extension of the file (using
	 * the path)
	 * 
	 * @param path
	 * @throws Exception
	 */
	private static void createReader(String path) throws Exception {
		if (path.endsWith(".xlsx")) {
			reader = new ReadExcel();
		} else {
			throw new NotSupportedException("Not supported file");
		}
	}

	/**
	 * Generates a letter per citizen in the chosen format.
	 * 
	 * @param letCit
	 * @param letterFormat
	 * @throws IOException
	 */
	private static void generateLetter(List<Citizen> letCit, String letterFormat) throws IOException {
		File letterDir = new File("letters");
		if (!letterDir.exists()) {
			letterDir.mkdir();
		}
		switch (letterFormat) {
		case PDF_COMMAND:
			letterGen = new LetterGenPdf();
			letterGen.generateLetters(letCit);
			break;
		case TXT_COMMAND:
			letterGen = new LetterGenTxt();
			letterGen.generateLetters(letCit);
			break;
		case DOCX_COMMAND:
			letterGen = new LetterGenDocx();
			letterGen.generateLetters(letCit);
			break;
		default:
			System.out.println("The chosen letter format doesn't exist.");
			break;
		}

	}
}
