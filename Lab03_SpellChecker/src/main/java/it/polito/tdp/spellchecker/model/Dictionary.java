package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Dictionary {

	private List<String> paroleDigitate = new ArrayList<>();
	private List<String> paroleDizionario = new ArrayList<>();

	public List<String> suddividoTestoInParoleEdEffettuoControlloLineare(String testoDigitato) {
		testoDigitato = testoDigitato.replaceAll("[.,\\/#!?$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
		testoDigitato = testoDigitato.toLowerCase();
		String[] digitate;
		digitate = testoDigitato.split(" ");
		List<String> errate = new ArrayList<>();
		for (String s : digitate) {
			paroleDigitate.add(s);
			boolean corretta = false;
			for (String str : paroleDizionario) {
				if (str.equals(s)) {
					corretta = true;
					break;
				}
			}
			if (corretta == false) {
				errate.add(s);
			}
		}
		return errate;

	}

	public List<String> suddividoTestoInParoleEdEffettuoControlloDicotomico(String testoDigitato) {
		testoDigitato = testoDigitato.replaceAll("[.,\\/#!?$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
		testoDigitato = testoDigitato.toLowerCase();
		String[] digitate;
		digitate = testoDigitato.split(" ");
		List<String> errate = new ArrayList<>();
		for (String s : digitate) {
			/*int indice = ricercaDicotomica(paroleDizionario, s);
			if (indice == -1) {
				errate.add(s);
			}*/
			int indice=Collections.binarySearch(paroleDizionario, s);
			if(indice<0)
				errate.add(s);
		}
		return errate;
	}

	public int ricercaDicotomica(List<String> lista, String parola) {
		int l = 0, r = lista.size() - 1;
		while (l <= r) {
			int m = (l + r )/ 2;
            
			// Check if x is present at mid
			if (lista.get(m).equals(parola))
				return m;

			// If x greater, ignore left half
			if (parola.compareTo(lista.get(m))>0)
				l = m + 1;

			// If x is smaller, ignore right half
			else
				r = m - 1;
		}

		// if we reach here, then element was
		// not present
		return -1;
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
