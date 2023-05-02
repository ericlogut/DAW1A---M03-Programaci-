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
    
    /**
     * aquest mètode es crida quan l'usuari fa clic al botó de connexió. 
     * Comprova el nom d'usuari i la contrasenya introduïts, busca a la base de dades si existeix un usuari amb aquestes credencials, 
     * i si ho fa, redirigeix l'usuari a la finestra del menú principal de l'aplicació. Si l'usuari introdueix credencials incorrectes, 
     * la variable contErrors s'incrementa i es mostra un missatge d'error a la etiqueta errorLabel.
     * @param actionEvent
     * @throws SQLException
     * @throws IOException 
     */
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
    
    /**
     * Canvia la finestra actual per la finestra del menú principal.
     * @throws IOException Si hi ha hagut un error al carregar la finestra del men
     */
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }
}


