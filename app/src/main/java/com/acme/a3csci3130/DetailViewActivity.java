package com.acme.a3csci3130;

/**
 * Displays already existing details of selected business
 * @author michael
 * @see CreateContactAcitivity for origin of buisnesses
 * @see MainActivity for only user path this this activity
 * All buttons names directly reflect name in UI and function
 * All text fields directly reflect name in UI and function
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailViewActivity extends Activity {

    private Button updateButton, eraseButton;
    private EditText numberField, nameField, primaryBuisnessField, addressField, provinceField;
    private String personID;
    private MyApplicationData appState;
    Contact receivedPersonInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");

        personID = receivedPersonInfo.uid;

        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        nameField = (EditText) findViewById(R.id.name);
        numberField = (EditText) findViewById(R.id.number);
        primaryBuisnessField = (EditText) findViewById(R.id.primaryBuisness);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (EditText) findViewById(R.id.province);

        updateButton = (Button) findViewById(R.id.updateButton);
        eraseButton = (Button) findViewById(R.id.deleteButton);


        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            numberField.setText(receivedPersonInfo.number);
            primaryBuisnessField.setText(receivedPersonInfo.primaryBuisness);
            addressField.setText(receivedPersonInfo.address);
            primaryBuisnessField.setText(receivedPersonInfo.province);
            provinceField.setText(receivedPersonInfo.province);
        }
    }

    public void updateContact(View v){
        String name = nameField.getText().toString();
        String number = numberField.getText().toString();
        String primaryBuisness = primaryBuisnessField.getText().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getText().toString();

        Contact person = new Contact(personID, name, number, primaryBuisness, address, province);

        appState.firebaseReference.child(personID).setValue(person);

        //leave activity
        finish();
    }

    public void eraseContact(View v) {
        appState.firebaseReference.child(personID).removeValue();

        //leave activity
        finish();
    }
}
