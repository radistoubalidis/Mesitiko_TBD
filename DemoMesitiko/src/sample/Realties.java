package sample;


import javafx.beans.property.*;
import javafx.beans.value.ObservableDoubleValue;

public class Realties {

    private StringProperty idProperty,addressProperty,r_typeProperty;
    private FloatProperty m2Property;

    public Realties(){
        this.idProperty = new SimpleStringProperty();
        this.addressProperty = new SimpleStringProperty();
        this.m2Property = new SimpleFloatProperty();
        this.r_typeProperty = new SimpleStringProperty();
    }



    public StringProperty getIdProperty() {
        return idProperty;
    }

    public void setIdProperty(String idProperty) {
        this.idProperty.set(idProperty);
    }

    public StringProperty getAddressProperty() {
        return addressProperty;
    }

    public void setAddressProperty(String addressProperty) {
        this.addressProperty.set(addressProperty);
    }

    public FloatProperty getM2Property() {
        return m2Property;
    }

    public void setM2Property(float m2Property) {
        this.m2Property.set(m2Property);
    }

    public StringProperty getR_typeProperty() {
        return r_typeProperty;
    }

    public void setR_typeProperty(String r_typeProperty) {
        this.r_typeProperty.set(r_typeProperty);
    }


}
