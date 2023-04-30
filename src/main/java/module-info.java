module com.mycompany.projecte.uf3.uf4.uf5.uf6_v3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.mycompany.projecte.uf3.uf4.uf5.uf6_v3 to javafx.fxml;
    exports com.mycompany.projecte.uf3.uf4.uf5.uf6_v3;
}
