package com.mycompany.projecte.uf3.uf4.uf5.uf6_v3;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ClauController {
    
    /**
    * Funció per poder tornar a la vista de menu
    * @throws IOException si hi ha algún error en la vista
    */
    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }
    
    @FXML
    TextField clauAcces;
    
    @FXML
    TextField confClauAcces;
    
    @FXML 
    TextField novaClau;
        
    @FXML
    Label errorLabel;
    
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();
    
    /**
    * Comprova si les claus d'accés coincideixen i actualitza la contrasenya de l'usuari a la nova clau.
    * @throws SQLException Si hi ha un error en l'execució de la consulta SQL.
    */
    @FXML
    public void comprovarClaus() throws SQLException {
        String selectSql = "SELECT * FROM usuaris WHERE id = "+AlmacenarUsuario.usuari;
        Statement selectStatement = connection.createStatement();
        ResultSet resultSet = selectStatement.executeQuery(selectSql);

        if (clauAcces.getText().equals(confClauAcces.getText())) {
            if (resultSet.next() && resultSet.getString("contrasenya").equals(clauAcces.getText())) {
                String updateSql = "UPDATE usuaris SET contrasenya = '"+novaClau.getText()+"' WHERE id = "+AlmacenarUsuario.usuari;
                Statement updateStatement = connection.prepareStatement(updateSql);
                updateStatement.executeUpdate(updateSql);
                errorLabel.setText("Ha funcionat");
            } else {
                errorLabel.setText("Error clau");
            }
        } else {
            errorLabel.setText("Clau incorrecte");
        }
    }

}