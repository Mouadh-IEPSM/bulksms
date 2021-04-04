package com.example.bulksms.realm;

import com.example.bulksms.model.Client;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmController {

    private final Realm realm;

    public RealmController() {
        realm = Realm.getDefaultInstance();
    }

    //Refresh the realm istance
    public void refresh() {

        realm.refresh();
    }

    //clear all objects from Client.class
    public void clearAll() {

        realm.beginTransaction();
        realm.delete(Client.class);
        realm.commitTransaction();
    }

    //find all objects in the Client.class
    public RealmResults<Client> getClients() {

        return realm.where(Client.class).findAll();
    }

    //query a single item with the given id
    public Client getClient(int id) {

        return realm.where(Client.class).equalTo("id", id).findFirst();
    }

    public void saveClient(int id, String name, String telephone, String date)
    {
        // Create the object
        Client client = new Client();
        client.setId(id);
        client.setName(name);
        client.setTelephone(telephone);
        client.setDate(date);

        realm.beginTransaction();
        Client copyOfClient = realm.copyToRealm(client);
        realm.commitTransaction();
    }
}
