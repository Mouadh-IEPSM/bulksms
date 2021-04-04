package com.example.bulksms.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bulksms.R;
import com.example.bulksms.model.Client;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private List<Client> mListOfClient;


    private Context mContext;

    public UserAdapter(List<Client> listOfClientEntity,  Context context){
        mListOfClient = listOfClientEntity;
        mContext = context;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //init the view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_for_user, null);
        //init the viewHolder of the cell
        UserViewHolder viewHolder = new UserViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        //get the client
        final Client client = mListOfClient.get(position);

        //Set the information of client
        holder.mNameOfClient.setText(client.getName());
        holder.mPhoneOfClient.setText(client.getTelephone());
        holder.mDateOfSMS.setText(client.getDate());

    }

    /**
     * get the number of books
     * @return size of the list
     */
    @Override
    public int getItemCount() {
        if ( mListOfClient != null){
            return mListOfClient.size();
        } else {
            return 0;
        }
    }
}
