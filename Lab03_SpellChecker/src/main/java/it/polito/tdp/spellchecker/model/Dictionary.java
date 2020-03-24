package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Dictionary {

	private List<String> paroleDigitate = new ArrayList<>();
	private List<String> paroleDizionario = new ArrayList<>();

	public List<String> suddividoTestoInParoleEdEffettuoControllo(String testoDigitato) {
		testoDigitato = testoDigitato.replaceAll("[.,\\/#!?$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
		testoDigitato = testoDigitato.toLowerCase();
		String[] digitate;
		digitate = testoDigitato.split(" ");
		List<String> errate = new ArrayList<>();
		for (String s : digitate) {
			paroleDigitate.add(s);
			boolean corretta = false;
			for (String diz : paroleDizionario) {
				if (diz.equals(s)) {
					corretta = true;
				}
			}
			if (corretta == false) {
				errate.add(s);
			}
		}
		return errate;

	}

	public void scaricoIlDizionario(String linguaggio) {
		try {
			FileReader fr;
			if (linguaggio.equals("English")) {
				fr = new FileReader("src/main/resources/English.txt");
			} else {
				fr = new FileReader("src/main/resources/Italian.txt");
			}
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null) {
				// Aggiungere parola alla struttura dati
				paroleDizionario.add(word);
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Errore nella lettura del file");
		}
	}

	public void eliminaDizionarioScaricatoETestoPrecedente() {
		paroleDizionario.clear();
		paroleDigitate.clear();
	}

}
