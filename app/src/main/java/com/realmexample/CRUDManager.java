package com.realmexample;


import com.realmexample.model.Contact;
import com.realmexample.model.Email;
import com.realmexample.model.Person;

import java.util.Calendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;


/**
 * Created by ajea on 08/05/17.
 */

public class CRUDManager {

    private Realm realm;

    public CRUDManager(Realm realm) {
        this.realm = realm;
    }

    /**
     * Obtener Datos
     * **/
    public List<Contact> obtainDataFromDB() {
        RealmResults<Contact> realmResults = realm.where(Contact.class).findAll();
        return realmResults;
    }

    public Contact obtainContact(String id){
        Contact realmResult = realm.where(Contact.class).equalTo("id", id).findFirst();
        return realmResult;
    }

    /**
     * Guardar Datos
     * **/
    public void saveData(String name, int age){
        realm.beginTransaction();

        Person person = realm.createObject(Person.class);
        person.setName(name);
        person.setAge(age);

        Email email1 = realm.createObject(Email.class);
        email1.setActive(true);
        email1.setAddress("example@gmail.com");

        Email email2 = realm.createObject(Email.class);
        email2.setActive(false);
        email2.setAddress("test@gmail.com");

        RealmList<Email> emails = new RealmList<>();
        emails.add(email1);
        emails.add(email2);

        String id = ""+Calendar.getInstance().getTimeInMillis();
        Contact contact = realm.createObject(Contact.class, id);
        contact.setPerson(person);
        contact.setEmails(emails);

        realm.commitTransaction();
    }

    /**
     * Actualizar
     * **/
    public void updateName(Contact contact, String name){
        realm.beginTransaction();
        contact.getPerson().setName(name);
        realm.commitTransaction();
    }

    /**
     * Borrar Datos
     * **/
    public void deleteData(String id){
        Contact contact = obtainContact(id);
        realm.beginTransaction();
        contact.deleteFromRealm();
        realm.commitTransaction();
    }

}
