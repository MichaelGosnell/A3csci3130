package com.acme.a3csci3130;

/**
 * Allows the user to create and store a new business
 * @author michael
 * @see com.acme.a3csci3130.DetailViewActivity to edit current buisnesses
 * @see MainActivity for only user path this this activity
 * All button names directly reflect name in UI and function
 * All text fields directly reflect name in UI and function
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText numberField, nameField, primaryBuisnessField, addressField, provinceField;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        numberField = (EditText) findViewById(R.id.number);
        nameField = (EditText) findViewById(R.id.name);
        primaryBuisnessField = (EditText) findViewById(R.id.primaryBuisness);
        addressField = (EditText) findViewById(R.id.primaryBuisness);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (EditText) findViewById(R.id.province);
    }

    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String personID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        String number = numberField.getText().toString();
        String primaryBuisness = primaryBuisnessField.getText().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getText().toString();
        Contact person = new Contact(personID, name, number, primaryBuisness, address, province);

        appState.firebaseReference.child(personID).setValue(person);

        finish();

    }
}
