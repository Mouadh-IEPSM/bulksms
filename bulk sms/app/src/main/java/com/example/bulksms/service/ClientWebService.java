package com.example.bulksms.service;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.bulksms.entity.AuthentificationEntity;
import com.example.bulksms.entity.ClientEntity;

import java.util.List;

public class ClientWebService {

    /**
     * Instance of retrofit
     */
    private Retrofit retrofit;

    /**
     * instance of Rest Api Interface
     */
    private ClientServiceInterface clientServiceInterface;
    /**
     * Constructor
     * Initialise retrofit
     */
    public ClientWebService(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.alloboucherie.be/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();

        clientServiceInterface = retrofit.create(ClientServiceInterface.class);
    }

    /**
     * Method that get all clients from the API
     * @return
     */
    public Call<List<ClientEntity>> getClientfromAPI(int id, String apiKey)
    {
        return clientServiceInterface.getListOfClient(id, apiKey);
    }

    /**
     * Method that get all clients from the API
     * @return
     */
    public Call<AuthentificationEntity> connectionFromAPI(String login, String password){
        return clientServiceInterface.authentification(login, password);
    }
}
