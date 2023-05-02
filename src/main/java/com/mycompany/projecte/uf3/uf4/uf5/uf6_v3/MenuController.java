package com.mycompany.projecte.uf3.uf4.uf5.uf6_v3;

import java.io.IOException;
import javafx.fxml.FXML;

/**
*
* Controlador de la vista del menú principal.
*/
public class MenuController {

    /**
    * 
    * Navega a la vista de transferència de diners.
    * @throws IOException si hi ha problemes per carregar la vista.
    */
    public void switchToTransferencia() throws IOException {
    App.setRoot("transferirVista");
    }

    /**
    *
    * Navega a la vista de retirada de diners.
    * @throws IOException si hi ha problemes per carregar la vista.
    */
    public void switchToRetirar() throws IOException {
    App.setRoot("retirarVista");
    }

    /**
    *
    * Navega a la vista de login per a desconnectar l'usuari actual.
    * @throws IOException si hi ha problemes per carregar la vista.
    */
    public void switchToLogout() throws IOException {
    App.setRoot("login");
    }

    /**
    *
    * Navega a la vista d'ingrés de diners.
    * @throws IOException si hi ha problemes per carregar la vista.
    */
    public void switchToIngresar() throws IOException {
    App.setRoot("ingresarVista");
    }

    /**
    * 
    * Navega a la vista de consulta de moviments i saldo.
    * @throws IOException si hi ha problemes per carregar la vista.
    */
    public void switchToConsultar() throws IOException {
    App.setRoot("consultarVista");
    }

    /**
    *
    * Navega a la vista per canviar la clau d'accés.
    * @throws IOException si hi ha problemes per carregar la vista.
    */
    public void switchToClau() throws IOException {
    App.setRoot("clauVista");
    }

    /**
    * 
    * Navega a la vista de pagament de factures.
    * @throws IOException si hi ha problemes per carregar la vista.
    */
    public void switchToBizum() throws IOException {
    App.setRoot("bizumVista");
    }

    /**
    * 
    * Navega a la vista de pagament de factures.
    * @throws IOException si hi ha problemes per carregar la vista.
    */
    public void switchToPagarFactures() throws IOException {
    App.setRoot("facturesVista");
    }

    /**
    * 
    * Navega a la vista per a crear un compte nou.
    * @throws IOException si hi ha problemes per carregar la vista.
    */
    public void switchToCrearCompte() throws IOException {
    App.setRoot("nouCompteVista");
    }
}