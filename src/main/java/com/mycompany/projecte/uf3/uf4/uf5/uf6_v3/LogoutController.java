package com.mycompany.projecte.uf3.uf4.uf5.uf6_v3;

import java.io.IOException;
import javafx.fxml.FXML;

public class LogoutController {

    @FXML
    
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
        AlmacenarUsuario.usuari = 0;
    }
}