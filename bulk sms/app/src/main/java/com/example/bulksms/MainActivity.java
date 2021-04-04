package com.example.bulksms;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.bulksms.adapter.ClientAdapter;
import com.example.bulksms.entity.ClientEntity;
import com.example.bulksms.model.Client;
import com.example.bulksms.realm.RealmController;
import com.example.bulksms.service.ClientService;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * the recycler view that display the list of recipe
     */
    private RecyclerView mRecyclerView;

    /**
     * the recipe adapter for the view
     */
    private ClientAdapter mClientAdapter;

    private ClientService mClientService;

    private String mApiKey;

    private RealmController realmController;

    private Button mAccesDataBaseButton;

    List<ClientEntity> mListOfClientEntity;

    private LinearLayoutManager mLinearLayoutManager;

    private int mIdClient = 1;
    private boolean loadingData = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mApiKey = sharedPref.getString("API_KEY_SHARED_PREF", null);

        realmController = new RealmController();
        mAccesDataBaseButton = findViewById(R.id.data_base_button);
        mRecyclerView = findViewById(R.id.recycler_view);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {
                Log.d("permission", "permission denied to SEND_SMS - requesting it");
                String[] permissions = {Manifest.permission.SEND_SMS};
                requestPermissions(permissions,  0);
            }
        }

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = mRecyclerView.getChildCount();
                int totalItemCount = mLinearLayoutManager.getItemCount();
                int lastVisibleItem = mLinearLayoutManager
                        .findLastVisibleItemPosition();

                if (!loadingData
                        && totalItemCount <= (lastVisibleItem + visibleItemCount)) {
                    // End has been reached
                    // Do something
                    mIdClient = mListOfClientEntity.get(mListOfClientEntity.size() -1).getId();
                    getRecipeListFromApi();
                    loadingData = true;
                }
            }
        });

        mAccesDataBaseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openDataBaseActivity();
            }
        });
        mClientService = new ClientService();
        //set a null adapter to avoid to have an error
        setAdapterToRecyclerView(null);
        loadingData= true;
        getRecipeListFromApi();
    }

    void openDataBaseActivity()
    {
        Intent myIntent = new Intent(this, DatabaseActivity.class);
        MainActivity.this.startActivity(myIntent);
    }

    private void getRecipeListFromApi()
    {
        Call<List<ClientEntity>> call = mClientService.loadClientFromAPI(mIdClient, mApiKey);
        call.enqueue(new Callback<List<ClientEntity>>() {
            @Override
            public void onResponse(Call<List<ClientEntity>> call, Response<List<ClientEntity>> response) {
                List<ClientEntity> newListOfClientEntity = response.body();
                if(newListOfClientEntity != null)
                {
                    if(mListOfClientEntity == null)
                    {
                        mListOfClientEntity = newListOfClientEntity;
                    }
                    else
                    {
                        mListOfClientEntity.addAll(newListOfClientEntity);
                    }
                    displayClient(mListOfClientEntity);
                }
            }

            @Override
            public void onFailure(Call<List<ClientEntity>> call, Throwable t) {
                call.cancel();
            }
        });

    }
    public void setAdapterToRecyclerView(List<ClientEntity> listOfClientEntity){
        mClientAdapter = new ClientAdapter(listOfClientEntity, realmController, this);
        mRecyclerView.setAdapter(mClientAdapter);
        loadingData = false;
    }

    public void displayClient(List<ClientEntity> listOfClientEntity){
        setAdapterToRecyclerView(listOfClientEntity);
    }
}

