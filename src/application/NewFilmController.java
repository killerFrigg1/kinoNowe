package application;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NewFilmController {
	@FXML
	private TextField nazwa;
	@FXML
	private TextField opis;
	@FXML
	private TextField czasTrwania;
	@FXML
	private TextField limitWiekowy;

	@FXML
	private Button dodajButton;

	@FXML
	public void dodaj() throws ClassNotFoundException, SQLException {
		SampleController.dodaj(nazwa.getText(), opis.getText(), czasTrwania.getText(), limitWiekowy.getText());
	}

}
