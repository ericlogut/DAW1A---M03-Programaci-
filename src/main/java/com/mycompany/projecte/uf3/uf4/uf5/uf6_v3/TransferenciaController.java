package com.mycompany.projecte.uf3.uf4.uf5.uf6_v3;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class TransferenciaController {

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

    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();
    
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
    }
}