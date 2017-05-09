package com.realmexample.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ajea on 18/04/17.
 */

public class Contact extends RealmObject {

    @PrimaryKey
    private String id;
    private Person person;
    private RealmList<Email> emails;
//    private RealmList<Person> friends;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public RealmList<Email> getEmails() {
        return emails;
    }

    public void setEmails(RealmList<Email> emails) {
        this.emails = emails;
    }
//
//    public RealmList<Person> getFriends() {
//        return friends;
//    }
//
//    public void setFriends(RealmList<Person> friends) {
//        this.friends = friends;
//    }


    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", person=" + person +
                ", emails=" + emails +
                '}'+ "\n\n";
    }
}
