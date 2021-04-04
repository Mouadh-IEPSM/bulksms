package com.example.bulksms.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bulksms.R;
import com.example.bulksms.entity.ClientEntity;
import com.example.bulksms.model.Client;
import com.example.bulksms.realm.RealmController;

import java.util.Calendar;
import java.util.List;

/**
 * Adapter to display information of client
 */
public class ClientAdapter extends RecyclerView.Adapter<ClientViewHolder> {

    /**
     * list of client
     */
    private List<ClientEntity> mListOfClientEntity;

    private RealmController mRealmController;

    private Context mContext;

    public ClientAdapter(List<ClientEntity> listOfClientEntity, RealmController realmController, Context context){
        mListOfClientEntity = listOfClientEntity;
        mRealmController = realmController;
        mContext = context;
    }

    @Override
    public ClientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //init the view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_for_client, null);
        //init the viewHolder of the cell
        ClientViewHolder viewHolder = new ClientViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ClientViewHolder holder, int position) {
        //get the client
        final ClientEntity clientEntity = mListOfClientEntity.get(position);

        //Set the information of client
        holder.mNameOfClient.setText(clientEntity.getNom()+" "+ clientEntity.getPrenom());
        holder.mPhoneOfClient.setText(clientEntity.getTelephone());
        holder.mSendSMSButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            Client client = mRealmController.getClient(clientEntity.getId());
            if(client == null)
            {
                String message = clientEntity.getNom()
                        +" "+ clientEntity.getPrenom()+
                        " , merci d'avoir choisi AlloBoucherie. Auriez-vous la gentilesse de nous faire part de votre expérience en cliquant sur le lien ci-dessous? https://www.alloboucherie.be";
                SmsManager sms = SmsManager.getDefault(); // using android SmsManager
                sms.sendTextMessage(clientEntity.getTelephone(), null, message, null, null); // adding number and text
                mRealmController.saveClient(clientEntity.getId(), clientEntity.getNom(), clientEntity.getTelephone(), Calendar.getInstance().getTime().toString());
                Toast.makeText(mContext, "Le sms a été envoyé",
                        Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(mContext, "Ce client a déjà reçu un sms",
                        Toast.LENGTH_LONG).show();
            }
            }
        });
    }

    /**
     * get the number of books
     * @return size of the list
     */
    @Override
    public int getItemCount() {
        if ( mListOfClientEntity != null){
            return mListOfClientEntity.size();
        } else {
            return 0;
        }
    }
}
