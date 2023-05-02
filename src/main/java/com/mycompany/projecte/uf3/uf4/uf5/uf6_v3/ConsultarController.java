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

/**
 * Aquesta classe és el controlador per a la finestra Consultar del sistema de caixers automàtics. 
 * Es mostra el saldo total dels comptes de l'usuari, es permet escollir un compte i es mostren les targetes 
 * associades a aquest compte si n'hi ha alguna.
 */
public class ConsultarController {

     /** L'etiqueta on es mostra el saldo total dels comptes de l'usuari. */
    @FXML
    Label saldoTotal;

    /** El desplegable on es pot seleccionar un compte. */
    @FXML
    ChoiceBox<String> compteEscollit;

    /** La connexió a la base de dades. */
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();

    /** Inicialitza la finestra Consultar. Es mostra el saldo total dels comptes de l'usuari i es carreguen els comptes disponibles. */
    @FXML
    public void initialize() throws SQLException {

        /** Consulta SQL per obtenir els comptes de l'usuari. */
        String selectSql = "SELECT * FROM compte WHERE usuari_id = "+AlmacenarUsuario.usuari;
        Statement selectStatement = connection.createStatement();
        ResultSet resultSet = selectStatement.executeQuery(selectSql);

        /** Llista de comptes disponibles. */
        List<String> opciones = new ArrayList<>();

        /** Suma dels saldos de tots els comptes de l'usuari. */
        double saldoTotalSuma = 0;

        /** Recorre els resultats de la consulta SQL per afegir els comptes disponibles a la llista "opciones" i actualitzar la suma dels saldos. */
        while (resultSet.next()) {
            opciones.add("Compte "+resultSet.getString("id")+": "+resultSet.getString("saldo")+"€");
            saldoTotalSuma = saldoTotalSuma + Double.parseDouble(resultSet.getString("saldo"));
        }

        /** Actualitza la visualització del saldo total. */
        saldoTotal.setText(String.valueOf(saldoTotalSuma));

        /** Crea una llista observable a partir de la llista "opciones" i la passa al desplegable "compteEscollit". */
        ObservableList<String> opcionesObservable = FXCollections.observableArrayList(opciones);
        compteEscollit.setItems(opcionesObservable);
    }

    /** El desplegable on es pot seleccionar una targeta. */
    @FXML
    ChoiceBox<String> targetaAss;
    
/**
     * Carrega les targetes associades a un compte seleccionat.
     * Si no s'ha seleccionat cap compte, mostra un missatge d'error.
     * 
     * @throws SQLException Si hi ha hagut un error durant l'execució de la consulta SQL.
     */
    @FXML
    public void cargarTargetes() throws SQLException {
        /** Obté el compte seleccionat al desplegable "compteEscollit". */
        String cuentaSeleccionada = compteEscollit.getValue();

        /** Si no s'ha seleccionat cap compte, es mostra un missatge d'error. */
        if (cuentaSeleccionada == null) {
            String textSelecciona = "Selecciona un compte vàlid";
            targetaAss.setValue(textSelecciona);
        } else {
            /** Obté l'identificador del compte seleccionat. */
            int inicio = cuentaSeleccionada.indexOf("Compte ") + 7;
            int fin = cuentaSeleccionada.lastIndexOf(":");
            String numeroCuenta = cuentaSeleccionada.substring(inicio, fin);
            int numeroCuentaInt = Integer.parseInt(numeroCuenta);

            /** Consulta SQL per obtenir les targetes associades al compte seleccionat. */
            String selectSql = "SELECT * FROM targeta WHERE compte_id = " + numeroCuentaInt;
            Statement selectStatement = connection.createStatement();
            ResultSet resultSet = selectStatement.executeQuery(selectSql);

            /** Llista de targetes disponibles. */
            List<String> opciones = new ArrayList<>();

            /** Recorre els resultats de la consulta SQL per afegir les targetes disponibles a la llista "opciones". */
            while (resultSet.next()) {
                String opcion = "Targeta " + resultSet.getString("num_targeta") 
                               + ", caducitat " + resultSet.getString("data_caducitat") 
                               + " i codi de seguretat " + resultSet.getString("codi_seguretat");

                opciones.add(opcion);
            }
            
            /** Crea una llista observable a partir de la llista "opciones" i la passa al desplegable "targetaAss". */
            ObservableList<String> opcionesObservable = FXCollections.observableArrayList(opciones);
            targetaAss.setItems(opcionesObservable);
            
            /** Si hi ha alguna targeta disponible, selecciona la primera per defecte. */
            if (!opciones.isEmpty()) {
                targetaAss.setValue(opciones.get(0));
            } else {
                /** Si no hi ha cap targeta disponible, mostra un missatge d'error. */
                String valorPorDefecto = "No hi ha opcions disponibles";
                targetaAss.setItems(FXCollections.observableArrayList(valorPorDefecto));
                targetaAss.setValue(valorPorDefecto);
            }
        }
    }


    /**
     * Canvia la finestra actual per la finestra del menú principal.
     * @throws IOException Si hi ha hagut un error al carregar la finestra del men
     */
    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }
}