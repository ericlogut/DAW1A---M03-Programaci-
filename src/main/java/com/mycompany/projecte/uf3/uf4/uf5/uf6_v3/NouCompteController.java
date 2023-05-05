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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controlador per a la vista de creació de nous comptes.
 * Permet als usuaris crear nous comptes amb un tipus de compte i una quantitat inicial.
 */
public class NouCompteController {
    
    @FXML 
    ChoiceBox<String> tipusCompte;
    
    @FXML
    TextField quantitatIngresar;  
    
    @FXML
    Label error;
    
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();
    
    /**
     * Configura el ChoiceBox tipusCompte amb els valors "Estalvi" i "Corrent" i el valor predeterminat com "Estalvi".
     */
    @FXML
    public void initialize() {
        tipusCompte.getItems().addAll("Estalvi", "Corrent");
        tipusCompte.setValue("Estalvi"); // Valor predeterminado
    }
    
    /**
     * Afegeix un nou compte a la base de dades amb les dades especificades pel usuari.
     * Es crida quan l'usuari fa clic al botó "Afegir Compte".
     * @throws SQLException Si hi ha algun error en la comunicació amb la base de dades.
     */
    @FXML
    public void afegirCompte() throws SQLException {
        // Obtenir l'ID màxim actual de la taula compte
        String selectSql = "SELECT MAX(id) FROM compte";
        PreparedStatement stmt = connection.prepareStatement(selectSql);
        ResultSet rs = stmt.executeQuery();

        int idMaximo = 0;

        if (rs.next()) {
            idMaximo = rs.getInt(1);
            // Hacer algo con el valor obtenido
        }

        try {
            // Inserir un nou compte amb les dades especificades per l'usuari
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
            error.setText("S'ha creat el compte");
        } catch (SQLException e) {
            // Manejar l'excepció
        }
    }
    
    /**
     * Canvia la vista actual per la vista del menú principal de l'aplicació.
     * Es crida quan l'usuari fa clic al botó "Menu".
     * @throws IOException Si hi ha algun error en la carrega de la vista del menú.
     */
    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }
}
