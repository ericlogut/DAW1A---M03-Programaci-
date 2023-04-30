package com.mycompany.projecte.uf3.uf4.uf5.uf6_v3;

import java.io.IOException;
import javafx.fxml.FXML;

public class MenuController {
    
    public void switchToTransferencia() throws IOException {
        App.setRoot("transferirVista");
    }
    
    public void switchToRetirar() throws IOException {
        App.setRoot("retirarVista");
    }
    
    public void switchToLogout() throws IOException {
        App.setRoot("login");
    }
    
    public void switchToIngresar() throws IOException {
        App.setRoot("ingresarVista");
    }
    
    public void switchToConsultar() throws IOException {
        App.setRoot("consultarVista");
    }
    
    public void switchToClau() throws IOException {
        App.setRoot("clauVista");
    }
    
    public void switchToBizum() throws IOException {
        App.setRoot("bizumVista");
    }
    
    public void switchToPagarFactures() throws IOException {
        App.setRoot("facturesVista");
    }
    
    public void switchToCrearCompte() throws IOException {
        App.setRoot("nouCompteVista");
    }
}