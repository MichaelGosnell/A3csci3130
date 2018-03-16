package com.acme.a3csci3130;

/**
 * Displays list of stored businesses, allows adding news one and editing/erasing old ones
 * @author michael
 * @see com.acme.a3csci3130.CreateBuisnessAcitivity to create new buisnesses
 * @see com.acme.a3csci3130.DetailViewActivity to edit current buisnesses
 * All button names directly reflect name in UI and function
 * All text fields directly reflect name in UI and function
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends Activity {


    private ListView contactListView;
    private FirebaseListAdapter<Buisness> firebaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the app wide shared variables
        MyApplicationData appData = (MyApplicationData)getApplication();

        //Set-up Firebase
        appData.firebaseDBInstance = FirebaseDatabase.getInstance();
        appData.firebaseReference = appData.firebaseDBInstance.getReference("buisnesses");

        //Get the reference to the UI contents
        contactListView = (ListView) findViewById(R.id.listView);

        //Set up the List View
       firebaseAdapter = new FirebaseListAdapter<Buisness>(this, Buisness.class,
                android.R.layout.simple_list_item_1, appData.firebaseReference) {
            @Override
            protected void populateView(View v, Buisness model, int position) {
                TextView contactName = (TextView)v.findViewById(android.R.id.text1);
                contactName.setText(model.name);
            }
        };
        contactListView.setAdapter(firebaseAdapter);
        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // onItemClick method is called everytime a user clicks an item on the list
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Buisness person = (Buisness) firebaseAdapter.getItem(position);
                showDetailView(person);
            }
        });
    }

    public void createContactButton(View v)
    {
        Intent intent=new Intent(this, CreateBuisnessAcitivity.class);
        startActivity(intent);
    }

    private void showDetailView(Buisness person)
    {
        Intent intent = new Intent(this, DetailViewActivity.class);
        intent.putExtra("Contact", person);
        startActivity(intent);
    }

}
