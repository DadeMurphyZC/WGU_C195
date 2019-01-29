/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp;

import static c195_schedulingapp.C195_SchedulingApp.appStage;
import static c195_schedulingapp.C195_SchedulingApp.state;
import c195_schedulingapp.utils.DB;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author cfonseca
 */
public class LoginController implements Initializable {
    
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Button loginBtn;
    @FXML private ComboBox location;
    
    private static ResourceBundle messages = null;
    
    public static void setBundle(){
        Locale l = Locale.getDefault();
        messages = ResourceBundle.getBundle("c195_schedulingapp.MessagesBundle_"+l, l);
    }
    
    @FXML public void login() throws ClassNotFoundException, SQLException, IOException{
        setBundle();
        if(DB.loginAuth(username.getText(), password.getText())==true){
            state.setLocation(location.getSelectionModel().getSelectedItem().toString());
            Parent root = FXMLLoader.load(getClass().getResource("Customers.fxml"));
            Scene scene = new Scene(root);
            appStage.setScene(scene);
            appStage.show();
        } else {
            JOptionPane.showMessageDialog(null, messages.getString("loginError"));
        }
    };
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(!location.getItems().isEmpty()){location.getItems().clear();}
        location.getItems().add("America/New_York");
        location.getItems().add("America/Phoenix");
        location.getItems().add("Europe/London");
        location.getItems().add("Online");
    }    
    
}
