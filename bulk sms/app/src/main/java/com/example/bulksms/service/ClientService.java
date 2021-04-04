package com.example.bulksms.service;

import retrofit2.Call;

import com.example.bulksms.entity.AuthentificationEntity;
import com.example.bulksms.entity.ClientEntity;

import java.util.List;

public class ClientService {

    ClientWebService mClientWebService;

    public void initializeWebService(){
        mClientWebService = new ClientWebService();
    }

    public Call<List<ClientEntity>> loadClientFromAPI(int id, String apiKey){
        if(mClientWebService == null)
        {
            initializeWebService();
        }
        return mClientWebService.getClientfromAPI(id, apiKey);
    }

    public Call<AuthentificationEntity> connectionUserFromAPI(String login, String password){
        if(mClientWebService == null)
        {
            initializeWebService();
        }
        return mClientWebService.connectionFromAPI(login, password);
    }

}
