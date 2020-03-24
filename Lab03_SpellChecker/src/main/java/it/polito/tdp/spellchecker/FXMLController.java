/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class FXMLController {
	
	private Dictionary dizionario;

	public void setDictionary(Dictionary dizionario) {
		this.dizionario=dizionario;
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextArea txtTesto;

    @FXML
    private Button btnSpellCheck;

    @FXML
    private TextArea txtParoleSbagliate;

    @FXML
    private Text txtNumeroParoleSbaglaite;

    @FXML
    private Button btnClearText;

    @FXML
    private Text txtTempo;

    @FXML
    void doClearText(ActionEvent event) {
    	txtTesto.clear();
    	txtParoleSbagliate.clear();
    	dizionario.eliminaDizionarioScaricatoETestoPrecedente();
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	List<String> sbagliate;
    	dizionario.scaricoIlDizionario(choiceBox.getValue());
    	sbagliate=dizionario.suddividoTestoInParoleEdEffettuoControllo(txtTesto.getText());
    	for(String s:sbagliate) {
    		txtParoleSbagliate.appendText(s+"\n");
    	}
    }

    @FXML
    void initialize() {
        assert choiceBox != null : "fx:id=\"choiceBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTesto != null : "fx:id=\"txtTesto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtParoleSbagliate != null : "fx:id=\"txtParoleSbagliate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumeroParoleSbaglaite != null : "fx:id=\"txtNumeroParoleSbaglaite\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTempo != null : "fx:id=\"txtTempo\" was not injected: check your FXML file 'Scene.fxml'.";
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("English", "Italian");
        //populate the Choicebox;  
        choiceBox.setItems(list);
    }
}



