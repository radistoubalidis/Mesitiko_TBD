/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPageController {


    @FXML
    private Button authButton;

    @FXML
    private TextField userTextField;

    @FXML
    private PasswordField passWordTextField;

    @FXML
    private Text outputText;

    public Button getAuthButton(){return authButton;}

    public PasswordField getPassWordTextField() { return passWordTextField; }

    public TextField getUserTextField() { return userTextField; }

    public Text getOutputText() { return outputText; }



}