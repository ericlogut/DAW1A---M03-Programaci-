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

public class IngresarController {

    @FXML
    ChoiceBox<String> compteIngresar;

    @FXML
    TextField quantitatIngresar;

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
        compteIngresar.setItems(opcionesObservable);
    }

    public void ingresarDiners() throws SQLException {
        
        String opcioSeleccionada = compteIngresar.getValue();
        
        // Utiliza una expresión regular para encontrar el valor numérico entre "Compte " y ":"
        String regex = "Compte\\s(\\d+):\\s\\d+\\.\\d+"; // Patrón regular
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(opcioSeleccionada);
        
        int valorInt = 0;
        // Verifica si se encuentra el patrón y obtén el valor de la "X"
        if (matcher.find()) {
            String valorX = matcher.group(1);
            valorInt = Integer.parseInt(valorX);
            System.out.println("Valor de X: " + valorInt);
            // Realiza las acciones necesarias con el valor de X
        } else {
            System.out.println("No se encontró el valor de X");
        }
        
        String textFieldContent = quantitatIngresar.getText();
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
        
        String updateSql = "UPDATE compte SET saldo = saldo + "+valorDouble+" WHERE id = "+valorInt;
        Statement updateStatement = connection.prepareStatement(updateSql);
        updateStatement.executeUpdate(updateSql);
        initialize();
    }

    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }
}


