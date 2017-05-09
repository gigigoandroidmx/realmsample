package com.realmexample.model;

import io.realm.RealmObject;

/**
 * Created by ajea on 18/04/17.
 */

public class Email extends RealmObject {

    private String address;
    private boolean active;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Email{" +
                "address='" + address + '\'' +
                ", active=" + active +
                '}'+ "\n";
    }
}
