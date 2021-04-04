package com.example.bulksms.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bulksms.R;

public class ClientViewHolder extends RecyclerView.ViewHolder {

    /**
     * Name of the client
     */
    protected TextView mNameOfClient;

    /**
     * Phone of the client
     */
    protected TextView mPhoneOfClient;


    protected Button mSendSMSButton;

    /**
     * @param view : the view
     */
    public ClientViewHolder(View view) {
        super(view);
        this.mNameOfClient = (TextView) view.findViewById(R.id.name_of_client);
        this.mPhoneOfClient = (TextView) view.findViewById(R.id.phone_of_client);
        mSendSMSButton = (Button) view.findViewById(R.id.connection_button);
    }
}
