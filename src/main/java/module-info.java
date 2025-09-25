module lk.ijse.elitedrivingschoolproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires jbcrypt;

    opens lk.ijse.elitedrivingschoolproject.config to jakarta.persistence;
//    opens lk.ijse.elitedrivingschoolproject.entity to org.hibernate.orm.core;


    opens lk.ijse.elitedrivingschoolproject to javafx.fxml;
    exports lk.ijse.elitedrivingschoolproject;
    exports lk.ijse.elitedrivingschoolproject.controller;
    opens lk.ijse.elitedrivingschoolproject.controller;
}