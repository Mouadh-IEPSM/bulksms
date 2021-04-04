package com.example.bulksms.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import com.example.bulksms.entity.AuthentificationEntity;
import com.example.bulksms.entity.ClientEntity;

import java.util.List;


public interface ClientServiceInterface {

    @GET("client/detail/{idClient}?")
    Call<List<ClientEntity>> getListOfClient(@Path("idClient") int idClient, @Query("api_token") String apiKey);

    @POST("login?")
    Call<AuthentificationEntity> authentification(@Query("email") String login, @Query("password") String password);


}
