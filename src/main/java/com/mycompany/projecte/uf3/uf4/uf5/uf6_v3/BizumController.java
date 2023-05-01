/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 *
 * @author ericl
 */
public class BizumController {
    
    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }
    
    @FXML
    ChoiceBox<String> compteTrans;
            
    @FXML
    ChoiceBox<String> usuariTrans;
    
    @FXML
    TextField quantitatTransferir;
    
    @FXML
    Label Error;
    
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();
    
    /**
    * Inicialitza les opcions de transferència per als usuaris i comptes disponibles.
    * @throws SQLException si hi ha un error en la connexió amb la base de dades.
    */
    @FXML
    public void initialize() throws SQLException {
    String selectSql = "SELECT * FROM usuaris";
    Statement selectStatement = connection.createStatement();
    ResultSet resultSet = selectStatement.executeQuery(selectSql);

    List<String> opcions = new ArrayList<>();

    while (resultSet.next()) {
    opcions.add(resultSet.getString("nom"));
    }

    ObservableList<String> opcionsObservable = FXCollections.observableArrayList(opcions);
    usuariTrans.setItems(opcionsObservable);

    String selectSql2 = "SELECT * FROM compte WHERE usuari_id = " + AlmacenarUsuario.usuari;
    Statement selectStatement2 = connection.createStatement();
    ResultSet resultSet2 = selectStatement2.executeQuery(selectSql2);

    List<String> opcions2 = new ArrayList<>();

    while (resultSet2.next()) {
    opcions2.add("Compte " + resultSet2.getString("id") + ": " + resultSet2.getString("saldo"));
    }

    ObservableList<String> opcionsObservable2 = FXCollections.observableArrayList(opcions2);
    compteTrans.setItems(opcionsObservable2);
    }
    
    public void moureDiners() throws SQLException {
        
        String opcioSeleccionada1 = compteTrans.getValue(); 
        
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
        
        String updateSql2 = "UPDATE compte c JOIN usuaris u ON c.usuari_id = u.id SET c.saldo = c.saldo + " + Double.parseDouble(quantitatTransferir.getText()) + " WHERE u.nom = '" + usuariTrans.getValue() + "' LIMIT 1";
        Statement updateStatement2 = connection.prepareStatement(updateSql2);
        updateStatement2.executeUpdate(updateSql2);
        
        String selectSql = "SELECT id FROM usuaris WHERE nom = '"+usuariTrans.getValue()+"'";
        Statement selectStatement = connection.createStatement();
        ResultSet resultSet = selectStatement.executeQuery(selectSql);

        if (resultSet.next()) {
            int usuariId = resultSet.getInt("id");

            String compteSql = "SELECT * FROM compte WHERE usuari_id = "+usuariId+" LIMIT 1";
            Statement compteStatement = connection.createStatement();
            ResultSet compteResultSet = compteStatement.executeQuery(compteSql);

            if (compteResultSet.next()) {
                // aquí puedes obtener los valores de la primera cuenta encontrada
                int compteId = compteResultSet.getInt("id");
                // otros valores de la cuenta que te interesen
                registraMoviment(valorInt1,compteId);
            } else {
                System.out.println("El usuario no tiene ninguna cuenta asociada");
            }
        } else {
            System.out.println("No se encontró ningún usuario con el nombre proporcionado");
        }

        
        initialize();
        

    }

    public void registraMoviment(int compteIdOri, int compteIdDes) throws SQLException {
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Crear un formateador de fecha con el patrón "yyyy-MM-dd"
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Formatear la fecha actual usando el formateador
        String fechaFormateada = fechaActual.format(formateador);

        String selectSql = "INSERT INTO moviment (tipusDeMoviment, data, quantitat, compteOrigen_id, compteDesti_id) \n" +
            "VALUES ('Bizum', '"+fechaFormateada+"', "+Double.parseDouble(quantitatTransferir.getText())+", "+compteIdOri+","+compteIdDes+");";

        PreparedStatement stmt = connection.prepareStatement(selectSql);
        int rowsAffected = stmt.executeUpdate();

        if (rowsAffected > 0) {
            Error.setText("El bizum s'ha realitzar correctament.");
        } else {
            Error.setText("El bizum no s'ha realitzar correctament.");
        }
    }

    
    
            
}
