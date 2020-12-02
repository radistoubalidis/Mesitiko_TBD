package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Customers {

    private StringProperty idProperty,nameProperty,phoneProperty;

    public Customers(){
        this.idProperty = new SimpleStringProperty();
        this.nameProperty = new SimpleStringProperty();
        this.phoneProperty = new SimpleStringProperty();
    }



    // getters - setters
    public StringProperty getIdProperty() {
        return idProperty;
    }

    public void setIdProperty(String  id){
        this.idProperty.set(id);
    }

    public StringProperty getNameProperty(){
        return nameProperty;
    }

    public void setNameProperty(String nameProperty) {
        this.nameProperty.set(nameProperty);
    }

    public StringProperty getPhoneProperty() {
        return phoneProperty;
    }

    public void setPhoneProperty(String phoneProperty) {
        this.phoneProperty.set(phoneProperty);
    }


    public String toString(){
        return this.getIdProperty().get()+","+this.getNameProperty().get()+","+this.getPhoneProperty();
    }
}
