package com.mycompany.projecte.uf3.uf4.uf5.uf6_v3;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
* Controlador per a la pantalla de transferències.
*/
public class TransferenciaController {

    /**
    * Funció que redirigeix l'usuari a la pantalla de menú principal.
    * @throws IOException si hi ha problemes amb la càrrega de la pantalla.
    */
    @FXML    
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }
    
    @FXML
    ChoiceBox<String> compteTrans1;
    
    @FXML
    ChoiceBox<String> compteTrans2;

    @FXML
    TextField quantitatTransferir;
    
    @FXML
    Label error;

    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();
    
    /**
    * Funció que s'executa al carregar la pantalla de transferències.
    * Obtinguem els comptes disponibles per a l'usuari i els afegim als ChoiceBox corresponents.
    * @throws SQLException si hi ha problemes amb la connexió a la base de dades.
    */
    @FXML
    public void initialize() throws SQLException {
        String selectSql = "SELECT * FROM compte WHERE usuari_id = "+AlmacenarUsuario.usuari;
        Statement selectStatement = connection.createStatement();
        ResultSet resultSet = selectStatement.executeQuery(selectSql);

        List<String> opciones = new ArrayList<>();

        while (resultSet.next()) {
            opciones.add("Compte "+resultSet.getString("id")+": "+resultSet.getString("saldo"));
            // reemplaza 'nombre_de_la_columna' por el nombre real de la columna
            // que contiene los valores que deseas mostrar en el ChoiceBox
        }

        ObservableList<String> opcionesObservable = FXCollections.observableArrayList(opciones);
        compteTrans1.setItems(opcionesObservable);
        compteTrans2.setItems(opcionesObservable);
    }

    /**
    * Funció que es crida quan l'usuari prem el botó de transferència de diners.
    * Obtenim les dades dels ChoiceBox i del camp de text i actualitzem els saldos dels comptes corresponents.
    * També registrem el moviment a la base de dades.
    * @throws SQLException si hi ha problemes amb la connexió a la base de dades.
    */
    public void moureDiners() throws SQLException {
        
        String opcioSeleccionada1 = compteTrans1.getValue();
        String opcioSeleccionada2 = compteTrans2.getValue();
        
        // Utiliza una expresión regular para encontrar el valor numérico entre "Compte " y ":"
        String regex = "Compte\\s(\\d+):\\s\\d+\\.\\d+"; // Patrón regular
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(opcioSeleccionada1);
        
        int valorInt1 = 0;
        // Verifica si se encuentra el patrón y obtén el valor de la "X"
        if (matcher.find()) {
            String valorX = matcher.group(1);
            valorInt1 = Integer.parseInt(valorX);
            System.out.println("Valor de X: " + valorInt1);
            // Realiza las acciones necesarias con el valor de X
        } else {
            System.out.println("No se encontró el valor de X");
        }
        
        int valorInt2 = 0;
        
        matcher = pattern.matcher(opcioSeleccionada2);
        // Verifica si se encuentra el patrón y obtén el valor de la "X"
        if (matcher.find()) {
            String valorX = matcher.group(1);
            valorInt2 = Integer.parseInt(valorX);
            System.out.println("Valor de X: " + valorInt2);
            // Realiza las acciones necesarias con el valor de X
        } else {
            System.out.println("No se encontró el valor de X");
        }
        
        String textFieldContent = quantitatTransferir.getText();
        double valorDouble = 0;
        
        try {
            valorDouble = Double.parseDouble(textFieldContent);
            System.out.println("El contenido del TextField es un número double: " + valorDouble);
            // Realiza las acciones necesarias con el valor double
        } catch (NumberFormatException e) {
            System.out.println("El contenido del TextField no es un número double válido");
            // Realiza las acciones necesarias en caso de excepción
            valorDouble = 0;
        }
        
        String updateSql1 = "UPDATE compte SET saldo = saldo - "+valorDouble+" WHERE id = "+valorInt1;
        Statement updateStatement1 = connection.prepareStatement(updateSql1);
        updateStatement1.executeUpdate(updateSql1);
        
        String updateSql2 = "UPDATE compte SET saldo = saldo + "+valorDouble+" WHERE id = "+valorInt2;
        Statement updateStatement2 = connection.prepareStatement(updateSql2);
        updateStatement2.executeUpdate(updateSql2);
        initialize();
        registraMoviment(valorInt1,valorInt2);
        error.setText("La transferencia ha funcionat");
    }
    
    /**
    * Registra una nova transferència amb la informació proporcionada.
    * @param compteIdOri l'ID del compte origen de la transferència.
    * @param compteIdDes l'ID del compte destí de la transferència.
    * @throws SQLException si hi ha un error en la connexió a la base de dades.
    */
    public void registraMoviment(int compteIdOri, int compteIdDes) throws SQLException {
        // Obtenir la data actual
        LocalDate fechaActual = LocalDate.now();

        // Crear format per la data"yyyy-MM-dd"
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Donar format a la data actual
        String fechaFormateada = fechaActual.format(formateador);

        String selectSql = "INSERT INTO moviment (tipusDeMoviment, data, quantitat, compteOrigen_id, compteDesti_id) \n" +
            "VALUES ('Transferencia', '"+fechaFormateada+"', "+Double.parseDouble(quantitatTransferir.getText())+", "+compteIdOri+","+compteIdDes+");";

        PreparedStatement stmt = connection.prepareStatement(selectSql);
        int rowsAffected = stmt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("El registre ha funcionat correctament.");
        } else {
            System.out.println("El registre no ha funcionat correctament.");
        }
    }
}