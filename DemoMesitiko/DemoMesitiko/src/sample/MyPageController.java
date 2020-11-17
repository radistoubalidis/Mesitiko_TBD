/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
  
package sample;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MyPageController {

    @FXML
    private Button customers,realties,owners,contracts,closeButton,for_sale_rent;

    public Button getContracts() {
        return contracts;
    }

    public Button getCustomers() {
        return customers;
    }

    public Button getOwners() {
        return owners;
    }

    public Button getRealties() {
        return realties;
    }

    public Button getCloseButton() {
        return closeButton;
    }

    public Button getFor_sale_rent() {
        return for_sale_rent;
    }
}
