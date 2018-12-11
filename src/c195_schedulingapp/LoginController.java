/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp;

import static c195_schedulingapp.C195_SchedulingApp.state;
import static c195_schedulingapp.C195_SchedulingApp.appStage;
import c195_schedulingapp.utils.DB;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cfonseca
 */
public class LoginController implements Initializable {
    
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Button loginBtn;
    
    @FXML public void login() throws ClassNotFoundException, SQLException, IOException{
        if(DB.loginAuth(username.getText(), password.getText())==true){
            Parent root = FXMLLoader.load(getClass().getResource("Customers.fxml"));
            Scene scene = new Scene(root);
            appStage.setScene(scene);
            appStage.show();
        } else {
            System.out.println("Login failed!");
        }
    };
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(state.toString());
    }    
    
}
