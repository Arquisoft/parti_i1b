package es.uniovi.asw;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.parser.LetterGen;
import es.uniovi.asw.parser.LetterGenDocx;
import es.uniovi.asw.parser.LetterGenPdf;
import es.uniovi.asw.parser.LetterGenTxt;

public class LetterGenTest {
	private List<Citizen> citizenList;
	
	@Before
    public void setUp() throws Exception {
		Citizen citizen = new Citizen("Diego","Perez",new Date(),"email@test.com",
				"11332233A","aa","aa", 1);
		citizen.setUnhashedPassword("test");
		citizenList = new ArrayList<Citizen>();
		citizenList.add(citizen);
    }


	@Test
	public void letterGenPdfTest() {
		LetterGen letterGen = new LetterGenPdf();
		try {
			letterGen.generateLetters(citizenList);
			File file = new File("letters/email@test.com.pdf");
			PDDocument document= PDDocument.load(file);
			PDFTextStripper pdfStripper = new PDFTextStripper();
			
			String pdfText = pdfStripper.getText(document);
			assertEquals(pdfText,"email@test.com\r\ntest\r\n");
		} catch (IOException e) {
		}
	}
	
	@Test
	public void letterGenTxtTest() {
		LetterGen letterGen = new LetterGenTxt();
		try {
			letterGen.generateLetters(citizenList);
			assertEquals("todo","todo");
			// TODO
		} catch (IOException e) {
		}
	}
	
	@Test
	public void letterGenDocxTest() {
		LetterGen letterGen = new LetterGenDocx();
		try {
			letterGen.generateLetters(citizenList);
			assertEquals("todo","todo");
			// TODO
		} catch (IOException e) {
		}
		
	}

}
