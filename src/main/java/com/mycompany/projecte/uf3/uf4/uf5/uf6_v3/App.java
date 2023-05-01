package com.mycompany.projecte.uf3.uf4.uf5.uf6_v3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

/**
 * Aquesta classe representa l'aplicació principal que extén la classe Application de JavaFX. Aquesta classe
 * carrega l'arxiu FXML de la finestra de connexió (login.fxml) i crea una nova escena per a aquesta finestra.
 * També conté un mètode estàtic que permet canviar la finestra actual per una altra en funció del fitxer FXML
 * especificat.
 */
public class App extends Application {

    private static Scene scene;

    /**
     * Inicialitza l'aplicació i mostra la finestra de connexió.
     *
     * @param stage El Stage principal de l'aplicació.
     * @throws IOException Si hi ha un error en carregar l'arxiu FXML de la finestra de connexió.
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 490, 490);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Canvia la finestra actual per una nova a partir de l'arxiu FXML especificat.
     *
     * @param fxml L'arxiu FXML de la nova finestra.
     * @throws IOException Si hi ha un error en carregar l'arxiu FXML de la nova finestra.
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Carrega l'arxiu FXML especificat i el converteix en un objecte de tipus Parent que conté l'estructura
     * de la finestra.
     *
     * @param fxml L'arxiu FXML a carregar.
     * @return Un objecte Parent que representa l'estructura de la finestra.
     * @throws IOException Si hi ha un error en carregar l'arxiu FXML.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Mètode principal de l'aplicació.
     *
     * @param args Arguments de la línia de comandes.
     */
    public static void main(String[] args) {
        launch();
    }
}