package com.mycompany.projecte.uf3.uf4.uf5.uf6_v3;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    TextField nomLogin;
    
    @FXML
    TextField pswdLogin;
    
    @FXML
    Label errorLabel;
    
    int contErrors = 0;
    
    public void button(ActionEvent actionEvent) throws SQLException, IOException {
        if (contErrors < 3) {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();

            String nom = nomLogin.getText();
            String contrasenya = pswdLogin.getText();


            String selectSql = "SELECT * FROM usuaris";
            Statement selectStatement = connection.createStatement();
            ResultSet resultSet = selectStatement.executeQuery(selectSql);

            StringBuilder result = new StringBuilder();

            boolean loginCorrecte = false;

            while (resultSet.next()) {

                if (resultSet.getString("nom").equals(nom) && resultSet.getString("contrasenya").equals(contrasenya)) {
                    loginCorrecte = true;

                    AlmacenarUsuario.usuari = Integer.parseInt(resultSet.getString("id"));
                    System.out.println( AlmacenarUsuario.usuari );
                    switchToMenu();
                }
            }
            if (!(loginCorrecte)) {
                errorLabel.setText("El teu compte no existeix");
                contErrors++;
            }

            resultSet.close();
            selectStatement.close();
            connection.close();
        } else {
            errorLabel.setText("Has superat el màxim nombre d'intents");
        }
    }   
    
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }
}


