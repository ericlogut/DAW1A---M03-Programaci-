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
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 *
 * @author ericl
 */
public class NouCompteController {
    
    @FXML 
    ChoiceBox<String> tipusCompte;
    
    @FXML
    TextField quantitatIngresar;
    
    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }
    
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();
    
    @FXML
    public void initialize() {
        tipusCompte.getItems().addAll("Estalvi", "Corrent");
        tipusCompte.setValue("Estalvi"); // Valor predeterminado
    }
    
    @FXML
    public void afegirCompte() throws SQLException {
        String selectSql = "SELECT MAX(id) FROM compte";
        PreparedStatement stmt = connection.prepareStatement(selectSql);
        ResultSet rs = stmt.executeQuery();

        int idMaximo = 0;

        if (rs.next()) {
            idMaximo = rs.getInt(1);
            // Hacer algo con el valor obtenido
        }

        try {
            String insertSql = "INSERT INTO compte (id, tipusDeCompte, saldo, usuari_id) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt2 = connection.prepareStatement(insertSql);
            stmt2.setInt(1, idMaximo+1);
            stmt2.setString(2, tipusCompte.getValue());
            stmt2.setDouble(3, Double.parseDouble(quantitatIngresar.getText()));
            stmt2.setInt(4, AlmacenarUsuario.usuari);

            int filasAfectadas = stmt2.executeUpdate();

            stmt2.close();
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            // Manejar la excepción
        }
    }

}
