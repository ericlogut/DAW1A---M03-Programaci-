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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.util.Pair;

/**
 *
 * @author ericl
 */
public class FacturesController {
    int i = 0;

    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }
    
    @FXML
    ChoiceBox<String> facturaSeleccionada;
    
    @FXML
    ChoiceBox<String> compteSeleccionat;
    
    
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();
    
    @FXML
    public void initialize() throws SQLException {
        String selectSql = "SELECT * FROM compte WHERE usuari_id = "+AlmacenarUsuario.usuari;
        Statement selectStatement = connection.createStatement();
        ResultSet resultSet = selectStatement.executeQuery(selectSql);

        List<String> opciones = new ArrayList<>();

        while (resultSet.next()) {
            opciones.add("Compte "+resultSet.getString("id")+": "+resultSet.getString("saldo")+"€");
            // reemplaza 'nombre_de_la_columna' por el nombre real de la columna
            // que contiene los valores que deseas mostrar en el ChoiceBox
        }

        ObservableList<String> opcionesObservable = FXCollections.observableArrayList(opciones);
        compteSeleccionat.setItems(opcionesObservable);
 
        
        String selectSql2 = "SELECT * FROM factures WHERE usuari_id = "+AlmacenarUsuario.usuari;
        Statement selectStatement2 = connection.createStatement();
        ResultSet resultSet2 = selectStatement2.executeQuery(selectSql2);

        List<String> opciones2 = new ArrayList<>();
        
        while (resultSet2.next()) {
            i++;
            opciones2.add(i+". "+resultSet2.getString("descripcio")+": "+resultSet2.getString("total")+"€");
            // reemplaza 'nombre_de_la_columna' por el nombre real de la columna
            // que contiene los valores que deseas mostrar en el ChoiceBox
        }
        i = 0;
        ObservableList<String> opcionesObservable2 = FXCollections.observableArrayList(opciones2);
        facturaSeleccionada.setItems(opcionesObservable2);

    }
    
    public static Pair<Integer, Double> obtenerValoresCompte(String texto) {
        Pattern pattern = Pattern.compile("Compte (\\d+):\\s*(\\d+(\\.\\d+)?)€");
        Matcher matcher = pattern.matcher(texto);

        if (matcher.find()) {
            int valueOfX = Integer.parseInt(matcher.group(1));
            double valueOfY = Double.parseDouble(matcher.group(2));
            return new Pair<>(valueOfX, valueOfY);
        } else {
            return null;
        }
    }
    
        
    public double[] extraerValoresDeFactura() {
        Pattern pattern = Pattern.compile("\\s*(\\d+(\\.\\d+)?)\\.\\s*([^:]+):\\s*(\\d+(\\.\\d+)?)€\\s*");
        Matcher matcher = pattern.matcher(facturaSeleccionada.getValue());

        if (matcher.find()) {
            String valorDeZ = matcher.group(1);
            double z = Double.parseDouble(valorDeZ);
            System.out.println("Valor de Z: " + z);

            String valorDeY = matcher.group(4);
            double y = Double.parseDouble(valorDeY);
            System.out.println("Valor de factura: " + y);

            return new double[] {z, y};
        } else {
            return new double[] {0.0, 0.0}; // O cualquier valor predeterminado que desees devolver si no se encuentra una coincidencia
        }
    }

    
    @FXML
    public void pagarFactura() throws SQLException{
            
        Pair<Integer, Double> valores = obtenerValoresCompte(compteSeleccionat.getValue());
        if (valores != null) {
            int x = valores.getKey();
            double y = valores.getValue();
            System.out.println("Valor de id: " + x);
            System.out.println("Valor de saldo: " + y);
        }
        
        double[] valoresFactura = extraerValoresDeFactura();
        
        int idFactura = (int) valoresFactura[0];
        double preuFactura = valoresFactura[1];
        
        int idCompte = valores.getKey();
        Double valorFactura = preuFactura;
        Double Resta = valores.getValue() - valorFactura;
        
        if (Resta >= 0 ) {
            // Que funcione
            String updateSql1 = "UPDATE compte SET saldo = saldo - "+valorFactura+" WHERE id = "+idCompte;
            Statement updateStatement1 = connection.prepareStatement(updateSql1);
            updateStatement1.executeUpdate(updateSql1);
            
            String updateSql2= "UPDATE factures SET total = 0.0 WHERE usuari_id = "+AlmacenarUsuario.usuari+" AND id = ( SELECT id FROM factures WHERE usuari_id = "+AlmacenarUsuario.usuari+" AND id = "+idFactura+" GROUP BY usuari_id );";
            Statement updateStatement2 = connection.prepareStatement(updateSql2);
            updateStatement2.executeUpdate(updateSql2);
            
            registraMoviment(idCompte,valorFactura);
            initialize();
        } else {
            // Error por negativo
        }
        System.out.println(Resta);
    }
    
    public void registraMoviment(int compteId, double preuFactura) throws SQLException {
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Crear un formateador de fecha con el patrón "yyyy-MM-dd"
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Formatear la fecha actual usando el formateador
        String fechaFormateada = fechaActual.format(formateador);

        String selectSql = "INSERT INTO moviment (tipusDeMoviment, data, quantitat, compteOrigen_id) \n" +
            "VALUES ('Factura', '"+fechaFormateada+"', "+preuFactura+", "+compteId+");";

        PreparedStatement stmt = connection.prepareStatement(selectSql);
        int rowsAffected = stmt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("El registro se insertó correctamente.");
        } else {
            System.out.println("El registro no se insertó correctamente.");
        }
    }
}
