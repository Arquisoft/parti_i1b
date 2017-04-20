package es.uniovi.asw.log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class LogManager {


	public void addToLog(String line)
	{
		BufferedWriter writer = null;
		try {

			// Create directory if it doesn't exist
			File logsDir = new File("logs");
			if (!logsDir.exists()) {
				logsDir.mkdir();
			}
			//path to the file
			File logFile = new File("logs/errors.log");

			// Check message
			//System.out.println("Updating log file");

			//add the data
			writer = new BufferedWriter(new FileWriter(logFile,true));
			writer.write(line+"\n");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the writer
				writer.close();
			} catch (Exception e) {
			}
		}
	}
}
