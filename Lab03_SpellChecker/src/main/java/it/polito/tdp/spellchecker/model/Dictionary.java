package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {

	List<String>dizionario= new LinkedList<String>();
	
	public void loadDictionary(String language) {
		
		if(language.equals("Italian")) {
			try {
				FileReader fr= new FileReader("src/main/resources/Italian.txt");
				BufferedReader br= new BufferedReader(fr);
				String word;
				while( (word= br.readLine()) !=null) {
					dizionario.add(word);
				}
				br.close();
			} catch(IOException e) {
				System.out.println("ERRORE NELLA LETTURA DA FILE");
			}
		}
			
			else if(language.equals("English")) {
				try {
					FileReader fr= new FileReader("src/main/resources/English.txt");
					BufferedReader br= new BufferedReader(fr);
					String word;
					while( (word= br.readLine()) !=null) {
						dizionario.add(word);
					}
					br.close();
				} catch(IOException e) {
					System.out.println("ERRORE NELLA LETTURA DA FILE");
				}
		}
	}
	
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
	
		List<RichWord>paroleScritte= new LinkedList<RichWord>();
		
		for(String s: inputTextList) {
			if(dizionario.contains(s)) {
			RichWord r= new RichWord(s,true);
			paroleScritte.add(r);
			}
			else {
				RichWord r= new RichWord(s,false);
				paroleScritte.add(r);
			}
		}
		
		return paroleScritte;
	}
	
	
	
	public List<String> controlloParoleSbagliate(List<RichWord>paroleScritte){
		
		//creo una list di tipo string in cui metto solo le parole sbagliate
		//faccio r.getParola() --> perchè cosi posso fare add essendo di tipo String!!!
		List<String>listaParoleSbagliate= new LinkedList<>();
		for(RichWord r: paroleScritte) {
			if(r.isCorretta==false) {
				listaParoleSbagliate.add(r.getParola());
				
			}
		}
		return listaParoleSbagliate ;
		
	}
	
	
	
	
	
	
	
}
