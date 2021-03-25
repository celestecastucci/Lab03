/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Dictionary model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxLanguage"
    private ComboBox<String> boxLanguage; // Value injected by FXMLLoader

    @FXML // fx:id="txtInserisci"
    private TextArea txtInserisci; // Value injected by FXMLLoader

    @FXML // fx:id="btnSpellCheck"
    private Button btnSpellCheck; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML // fx:id="txtErrori"
    private TextField txtErrori; // Value injected by FXMLLoader

    @FXML // fx:id="btnClearText"
    private Button btnClearText; // Value injected by FXMLLoader

    @FXML // fx:id="txtTime"
    private TextField txtTime; // Value injected by FXMLLoader

    @FXML
    void handleClearText(ActionEvent event) {
    	
    	txtRisultato.clear();
    	txtErrori.clear();
    	txtTime.clear();
    	txtInserisci.clear();
    }
    
    /**
     * RICORDATI DI INOCARE QUI TUTTI I METODI CHE CREI IN MODEL!!! ALTRIEMENTI NON SONO COLLEGATI
     * @param event
     */

    @FXML
    void handleSpellCheck(ActionEvent event) {
    	
    	this.model.loadDictionary(boxLanguage.getValue());
    	
    	long startTime= System.nanoTime();
    	List<RichWord>paroleR;
    	
    	String inserisci= txtInserisci.getText().toLowerCase();
    	if(inserisci==null) {
    		txtRisultato.setText("ERRORE. DEVI INSERIRE UNA PAROLA");
    	}
    	inserisci=inserisci.replaceAll("[.,\\/#$%!?\\^&\\*;:{}=\\-_'()\\[\\]\"]", "");
    	
    	List<String>listaInputText= new LinkedList<String>();  //creo una lista vuota
    	String array[]=inserisci.split(" ");
    	for(String s: array) {
    		listaInputText.add(s);   //aggiungo alla lista, ogni parola spezzettata dopo aver fatto split
    	}
    	
    //collego il metodo controlloParoleSbagliate del model con questa lista e metto come parametro
    /**
     * IL CONTROLLER NON PUO' SAPERE 	
     */
   paroleR= this.model.spellCheckText(listaInputText);
   paroleR=this.model.spellCheckTextDichotomic(listaInputText);
  List<String>listaFinale=new LinkedList<String>();
  listaFinale = this.model.controlloParoleSbagliate(paroleR);
  
  String risultato="";
    for(String s: listaFinale) {
    	risultato+=s+"\n";
    	}
    
    long endTime= System.nanoTime();
    txtRisultato.setText(risultato);
    txtErrori.setText("The text contains"+listaFinale.size()+"errors");
    txtTime.setText("Spell check completed in"+(endTime-startTime)+"seconds");

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxLanguage != null : "fx:id=\"boxLanguage\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInserisci != null : "fx:id=\"txtInserisci\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrori != null : "fx:id=\"txtErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTime != null : "fx:id=\"txtTime\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Dictionary model) {
    	this.model=model;
    	boxLanguage.getItems().addAll("English", "Italian");
    }
}
