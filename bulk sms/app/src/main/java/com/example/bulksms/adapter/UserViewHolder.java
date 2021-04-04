package com.example.bulksms.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bulksms.R;

public class UserViewHolder extends RecyclerView.ViewHolder {

    /**
     * Name of the client
     */
    protected TextView mNameOfClient;

    /**
     * Phone of the client
     */
    protected TextView mPhoneOfClient;


    protected TextView mDateOfSMS;

    /**
     * @param view : the view
     */
    public UserViewHolder(View view) {
        super(view);
        this.mNameOfClient = (TextView) view.findViewById(R.id.name_of_user);
        this.mPhoneOfClient = (TextView) view.findViewById(R.id.phone_of_user);
        mDateOfSMS = (TextView) view.findViewById(R.id.date_of_sms);
    }
}
