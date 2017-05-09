package es.uniovi.asw.parser;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import es.uniovi.asw.model.Citizen;

public class LetterGenPdf implements LetterGen {

	@Override
	public void generateLetters(List<Citizen> citizens) throws IOException {
		for (Citizen citizen : citizens) {
			// Setting up the document
			PDDocument letter = new PDDocument();
			PDPage page = new PDPage();
			letter.addPage(page);
			PDPageContentStream contentStream = new PDPageContentStream(letter, page);
			// Adding the text
			contentStream.beginText();
			contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
			contentStream.setLeading(14f);
			contentStream.newLineAtOffset(25, 720);
			contentStream.showText(citizen.getEmail());
			contentStream.newLine();
			contentStream.showText(citizen.getUnhashedPassword());
			contentStream.endText();
			contentStream.close();
			// Saving the document
			letter.save("letters/" + citizen.getEmail() + ".pdf");
			letter.close();
			// Deleting unhashed password after letter generation
			citizen.setUnhashedPassword("");
		}
	}

}
