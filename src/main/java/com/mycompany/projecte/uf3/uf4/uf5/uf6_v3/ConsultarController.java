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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class ConsultarController {

    @FXML
    Label saldoTotal;
    
    @FXML
    ChoiceBox<String> compteEscollit;
    
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();
    
    @FXML
    public void initialize() throws SQLException {
        String selectSql = "SELECT * FROM compte WHERE usuari_id = "+AlmacenarUsuario.usuari;
        Statement selectStatement = connection.createStatement();
        ResultSet resultSet = selectStatement.executeQuery(selectSql);

        List<String> opciones = new ArrayList<>();

        double saldoTotalSuma = 0;
        
        while (resultSet.next()) {
            opciones.add("Compte "+resultSet.getString("id")+": "+resultSet.getString("saldo")+"€");
            saldoTotalSuma = saldoTotalSuma + Double.parseDouble(resultSet.getString("saldo"));
        }
        
        saldoTotal.setText(String.valueOf(saldoTotalSuma));
        ObservableList<String> opcionesObservable = FXCollections.observableArrayList(opciones);
        compteEscollit.setItems(opcionesObservable);
    }
    
    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }
}