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
    ChoiceBox<String> targetaAss;
    
    @FXML
public void cargarTargetes() throws SQLException {
    String cuentaSeleccionada = compteEscollit.getValue();
    if (cuentaSeleccionada == null) {
        String textSelecciona = "Selecciona un compte vàlid";
        targetaAss.setValue(textSelecciona);
    } else {
        int inicio = cuentaSeleccionada.indexOf("Compte ") + 7;
        int fin = cuentaSeleccionada.lastIndexOf(":");
        String numeroCuenta = cuentaSeleccionada.substring(inicio, fin);
        int numeroCuentaInt = Integer.parseInt(numeroCuenta);

        String selectSql = "SELECT * FROM targeta WHERE compte_id = " + numeroCuentaInt;
        Statement selectStatement = connection.createStatement();
        ResultSet resultSet = selectStatement.executeQuery(selectSql);

        List<String> opciones = new ArrayList<>();

        while (resultSet.next()) {
            String opcion = "Targeta " + resultSet.getString("num_targeta") 
                           + ", caducitat " + resultSet.getString("data_caducitat") 
                           + " i codi de seguretat " + resultSet.getString("codi_seguretat");

            opciones.add(opcion);
        }
        
        ObservableList<String> opcionesObservable = FXCollections.observableArrayList(opciones);
        targetaAss.setItems(opcionesObservable);
        
        if (!opciones.isEmpty()) {
            targetaAss.setValue(opciones.get(0));
        } else {
            String valorPorDefecto = "No hi ha opcions disponibles";
            targetaAss.setItems(FXCollections.observableArrayList(valorPorDefecto));
            targetaAss.setValue(valorPorDefecto);
        }
    }
}


        
    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }
}