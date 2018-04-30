package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SampleController {
	private static int i;

	private static ObservableList<Film> filmyData = FXCollections.observableArrayList();
	private static ObservableList<Sala> screeningRooms = FXCollections.observableArrayList();
	private static ObservableList<Seans> seansy = FXCollections.observableArrayList();

	@FXML
	private Button dodajFilmStage;
	@FXML
	private Button wczytajFilmyButton;
	@FXML
	private Button dodajSaleStage;
	@FXML
	private Button wczytajSaleButton;
	@FXML
	private Button dodajSeansStage;
	@FXML
	private Button wczytajSeanseButton;

	@FXML
	private TableView<Film> filmyTableView;
	@FXML
	private TableView<Sala> screeningRoomsTableView;
	@FXML
	private TableView<Seans> seansyTableView;

	@FXML
	private TableColumn<Film, Integer> idColumn;
	@FXML
	private TableColumn<Film, String> nazwaColumn;
	@FXML
	private TableColumn<Film, String> opisColumn;
	@FXML
	private TableColumn<Film, String> czasTrwaniaColumn;
	@FXML
	private TableColumn<Film, String> limitWiekowyColumn;

	@FXML
	private TableColumn<Sala, Integer> screeningRoomNumbercolumn;
	@FXML
	private TableColumn<Sala, Integer> liczbaMiejscScreeningRoomColumn;
	@FXML
	private TableColumn<Sala, String> typSaliScreeningRoomColumn;

	@FXML
	private TableColumn<Seans, Integer> seansIDColumn;
	@FXML
	private TableColumn<Seans, Integer> seansFilmIDColumn;
	@FXML
	private TableColumn<Seans, Integer> seansNumberSaliColumn;
	@FXML
	private TableColumn<Seans, String> seansDataSeansuColumn;

	@FXML
	public void dodajFilmStage() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("NewFilm.fxml"));
		Parent addNewEmployee = loader.load();

		Stage newFilmStage = new Stage();
		newFilmStage.initModality(Modality.WINDOW_MODAL);
		newFilmStage.initOwner(Main.primaryStage);
		Scene scene = new Scene(addNewEmployee);
		newFilmStage.setScene(scene);
		newFilmStage.showAndWait();
	}

	@FXML
	public void dodajSaleStage() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("NewAuditorium.fxml"));
		Parent addNewScreeningRoom = loader.load();

		Stage newFilmStage = new Stage();
		newFilmStage.initModality(Modality.WINDOW_MODAL);
		newFilmStage.initOwner(Main.primaryStage);
		Scene scene = new Scene(addNewScreeningRoom);
		newFilmStage.setScene(scene);
		newFilmStage.showAndWait();
	}

	@FXML
	public void dodajSeansStage() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("NewSeans.fxml"));
		Parent addNewScreeningRoom = loader.load();

		Stage newFilmStage = new Stage();
		newFilmStage.initModality(Modality.WINDOW_MODAL);
		newFilmStage.initOwner(Main.primaryStage);
		Scene scene = new Scene(addNewScreeningRoom);
		newFilmStage.setScene(scene);
		newFilmStage.showAndWait();
	}

	// DODAJ FILM
	public static void dodaj(String nazwa, String opis, String czasTrwania, String limitWiekowy)
			throws SQLException, ClassNotFoundException {
		jakieID("THEATER");
		try {
			Connection c = null;
			Statement stmt = null;
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Theater.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();

			String sql = "INSERT INTO THEATER (ID,NAZWA,OPIS,CZASTRWANIA,LIMITWIEKOWY) " + "VALUES ("
					+ Integer.toString(i + 1) + ", '" + nazwa + "', '" + opis + "', '" + czasTrwania + "', '"
					+ limitWiekowy + "' );";
			System.out.println(sql);
			stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Film zosta³ dodany do bazy");
	}

	public static void dodajSale(int liczbaMiejsc, String typ) throws SQLException, ClassNotFoundException {
		jakieID("SALE");
		try {
			Connection c = null;
			Statement stmt = null;
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Theater.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();

			String sql = "INSERT INTO SALE (NUMER, LICZBAMIEJSCE, TYP) " + "VALUES (" + Integer.toString(i + 1) + ", "
					+ Integer.toString(liczbaMiejsc) + ", '" + typ + "');";
			System.out.println(sql);
			stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Sala zosta³a dodana do bazy");
	}

	public static void dodajSeans(int filmID, int numberSali, String czasSeansu)
			throws SQLException, ClassNotFoundException {
		jakieID("SEANSY");
		try {
			Connection c = null;
			Statement stmt = null;
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Theater.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();

			String sql = "INSERT INTO SEANSY (IDSEANS, FILMID, NUMERSALI, CZASSEANSU) " + "VALUES ("
					+ Integer.toString(i + 1) + ", " + filmID + ", " + numberSali + ", '" + czasSeansu + "');";
			System.out.println(sql);
			stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Seans zosta³ dodany do bazy");
	}

	@FXML
	public void wczytajFilmy() {
		filmyTableView.getItems().clear();
		idColumn.setCellValueFactory(new PropertyValueFactory<Film, Integer>("id"));
		nazwaColumn.setCellValueFactory(new PropertyValueFactory<Film, String>("nazwa"));
		opisColumn.setCellValueFactory(new PropertyValueFactory<Film, String>("opis"));
		czasTrwaniaColumn.setCellValueFactory(new PropertyValueFactory<Film, String>("czasTrwania"));
		limitWiekowyColumn.setCellValueFactory(new PropertyValueFactory<Film, String>("limitWiekowy"));

		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Theater.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM THEATER;");

			while (rs.next()) {
				int id = rs.getInt("id");
				String nazwa = rs.getString("NAZWA");
				String opis = rs.getString("OPIS");
				String czasTrwania = rs.getString("CZASTRWANIA");
				String limitWiekowy = rs.getString("LIMITWIEKOWY");

				System.out.println("ID = " + id);
				System.out.println("NAZWA = " + nazwa);
				System.out.println("OPIS = " + opis);
				System.out.println("CZASTRWANIA = " + czasTrwania);
				System.out.println("LIMITWIEKOWY = " + limitWiekowy);

				filmyData.add(new Film(id, nazwa, opis, czasTrwania, limitWiekowy));
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		filmyTableView.setItems(filmyData);
	}

	@FXML
	public void wczytajSale() {
		screeningRoomsTableView.getItems().clear();
		screeningRoomNumbercolumn.setCellValueFactory(new PropertyValueFactory<Sala, Integer>("number"));
		liczbaMiejscScreeningRoomColumn.setCellValueFactory(new PropertyValueFactory<Sala, Integer>("liczbaMiejsc"));
		typSaliScreeningRoomColumn.setCellValueFactory(new PropertyValueFactory<Sala, String>("typSali"));

		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Theater.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM SALE;");

			while (rs.next()) {
				int number = rs.getInt("NUMER");
				int liczbaMiejsc = rs.getInt("LICZBAMIEJSCE");
				String typSali = rs.getString("TYP");

				System.out.println("NUMER = " + number);
				System.out.println("LICZBAMIEJSCE = " + liczbaMiejsc);
				System.out.println("TYP = " + typSali);

				screeningRooms.add(new Sala(number, liczbaMiejsc, typSali));
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		screeningRoomsTableView.setItems(screeningRooms);
	}

	@FXML
	public void wczytajSeansy() {
		seansyTableView.getItems().clear();
		seansIDColumn.setCellValueFactory(new PropertyValueFactory<Seans, Integer>("id"));
		seansFilmIDColumn.setCellValueFactory(new PropertyValueFactory<Seans, Integer>("filmID"));
		seansNumberSaliColumn.setCellValueFactory(new PropertyValueFactory<Seans, Integer>("numberSali"));
		seansDataSeansuColumn.setCellValueFactory(new PropertyValueFactory<Seans, String>("czasSeansu"));

		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Theater.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM SEANSY;");

			while (rs.next()) {
				int id = rs.getInt("IDSEANS");
				int filmID = rs.getInt("FILMID");
				int numberSali = rs.getInt("NUMERSALI");
				String czasSeansu = rs.getString("CZASSEANSU");

				System.out.println("NUMER = " + id);
				System.out.println("LICZBAMIEJSCE = " + filmID);
				System.out.println("TYP = " + numberSali);
				System.out.println("TYP = " + czasSeansu);

				seansy.add(new Seans(id, filmID, numberSali, czasSeansu));
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		seansyTableView.setItems(seansy);
	}

	public static void jakieID(String table) throws ClassNotFoundException, SQLException {
		Connection c = null;
		Statement stmt = null;
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:Theater.db");
		c.setAutoCommit(false);

		stmt = c.createStatement();
		if (table == "THEATER") {
			ResultSet rs = stmt.executeQuery("SELECT MAX(ID) FROM " + table + ";");
			i = rs.getInt(1);
			rs.close();
		} else if (table == "SALE") {
			ResultSet rs = stmt.executeQuery("SELECT MAX(NUMER) FROM " + table + ";");
			i = rs.getInt(1);
			rs.close();
		} else if (table == "SEANSY") {
			ResultSet rs = stmt.executeQuery("SELECT MAX(IDSEANS) FROM " + table + ";");
			i = rs.getInt(1);
			rs.close();
		} else {
			System.out.println("Podano z³¹ tabelê");
		}
		stmt.close();
		c.close();
	}

}
