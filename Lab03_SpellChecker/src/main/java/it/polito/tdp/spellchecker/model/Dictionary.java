package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Dictionary {
	
	List<String>dizionario= new LinkedList<String>();
	
	
	public void loadDictionary(String language) {
		
		this.dizionario.clear();
		
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
	
	public List<RichWord> spellCheckTextLinear(List<String> inputTextList){
		List<RichWord> paroleErrate=new LinkedList<>();
		RichWord parola;
		
		for(String s:inputTextList) {
			parola=new RichWord(s,false);
			
			for(String si:dizionario) {
				if(si.equals(s)) {
					parola.setCorretta(true);
					break;
				}
			}
			
			if(!parola.isCorretta())
				paroleErrate.add(parola);			
		}
		
		return paroleErrate;
	}
	public List<RichWord> spellCheckTextDichotomic(List<String> inputTextList){
		
		List<RichWord> paroleErrate=new LinkedList<>();
		RichWord parola;
		int index;
		for(String s: inputTextList) {
	
			parola=new RichWord(s,false);
			//il metodo restituisce l'indice a cui viene trovato l'elemento nella lista 'dictionary'
			//eseguendo una ricerca dicotomica; se non lo trova l'indice assume valori negativi
			index=Collections.binarySearch(dizionario, s);
			if(index>=0)
				parola.setCorretta(true);
		
			
			if(!parola.isCorretta())
				paroleErrate.add(parola);	
	
		}
		
		return paroleErrate;
	}
		
   
	
	
}
