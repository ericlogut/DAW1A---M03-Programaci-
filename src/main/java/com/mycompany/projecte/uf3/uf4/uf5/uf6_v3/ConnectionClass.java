package com.mycompany.projecte.uf3.uf4.uf5.uf6_v3;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Aquesta classe gestiona la connexió a la base de dades MySQL del sistema de caixers automàtics.
 */
public class ConnectionClass {

    /** L'objecte Connection que representa la connexió a la base de dades. */
    public Connection connection;

    /**
     * Retorna l'objecte Connection que representa la connexió a la base de dades.
     * 
     * @return L'objecte Connection que representa la connexió a la base de dades.
     */
    public Connection getConnection () {

        /** Nom de la base de dades MySQL */
        String dbName="caixerautomatic";
        /** Nom d'usuari per connectar-se a MySQL */
        String userName="root";
        /** Contrasenya per connectar-se a MySQL */
        String password="";
        try {
            /** Registra el controlador JDBC per a MySQL */
            Class.forName("com.mysql.jdbc.Driver"); 
            
            /** Estableix la connexió a la base de dades MySQL */
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName,userName,password);
        } catch (Exception e) {
            /** Si hi ha hagut un error, imprimeix el missatge d'error a la consola. */
            e.printStackTrace();
        }

        return connection;
    }
}
