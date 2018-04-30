package application;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NewAuditoriumController {
	@FXML
	private TextField liczbaMiejsc;
	@FXML
	private TextField typSali;

	@FXML
	private Button dodajButton;

	@FXML
	public void dodaj() throws ClassNotFoundException, SQLException {
		SampleController.dodajSale(Integer.parseInt(liczbaMiejsc.getText()), typSali.getText());
	}

}
