package com.example.bulksms;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bulksms.adapter.ClientAdapter;
import com.example.bulksms.adapter.UserAdapter;
import com.example.bulksms.entity.ClientEntity;
import com.example.bulksms.model.Client;
import com.example.bulksms.realm.RealmController;
import com.example.bulksms.service.ClientService;

import java.util.List;

public class DatabaseActivity extends AppCompatActivity {

    /**
     * the recycler view that display the list of recipe
     */
    private RecyclerView mRecyclerView;

    /**
     * the recipe adapter for the view
     */
    private UserAdapter mUserAdapter;

    private RealmController realmController;

    List<Client> mListOfClient;

    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_base_layout);

        realmController = new RealmController();
        mRecyclerView = findViewById(R.id.recycler_view);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mListOfClient = realmController.getClients();


        //set a null adapter to avoid to have an error
        mUserAdapter = new UserAdapter(mListOfClient, this);
        mRecyclerView.setAdapter(mUserAdapter);
    }


}
