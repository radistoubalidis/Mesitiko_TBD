package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Owners {

    private StringProperty id;
    private StringProperty fullName;
    private StringProperty phone;
    private IntegerProperty numOfRealties;

    public Owners(){
        this.id = new SimpleStringProperty();
        this.fullName = new SimpleStringProperty();
        this.phone = new SimpleStringProperty();
        this.numOfRealties = new SimpleIntegerProperty();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public StringProperty fullNameProperty() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public IntegerProperty numOfRealtiesProperty() {
        return numOfRealties;
    }

    public void setNumOfRealties(int numOfRealties) {
        this.numOfRealties.set(numOfRealties);
    }
}