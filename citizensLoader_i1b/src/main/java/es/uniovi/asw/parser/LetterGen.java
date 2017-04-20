package es.uniovi.asw.parser;

import java.io.IOException;
import java.util.List;

import es.uniovi.asw.model.Citizen;

public interface LetterGen {
	public void generateLetters(List<Citizen> citizens) throws IOException;
}
