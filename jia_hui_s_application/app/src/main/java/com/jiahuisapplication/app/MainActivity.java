package com.jiahuisapplication.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.view.View;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] character_dropdown = {"Student", "Instructor"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        // Take the instance of Spinner and
        // apply OnItemSelectedListener on it which
        // tells which item of spinner is clicked
        Spinner spinnerGroupTwo = findViewById(R.id.spinnerGroupTwo);
        spinnerGroupTwo.setOnItemSelectedListener(this);

        // Create the instance of ArrayAdapter
        // having the list of courses
        ArrayAdapter<String> adapter
                = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                character_dropdown);

        // set simple layout resource file
        // for each item of spinner
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        // Set the ArrayAdapter (adapter) data on the
        // Spinner which binds data to spinner
        spinnerGroupTwo.setAdapter(adapter);
    }

    // Performing action when ItemSelected
    // from spinner, Overriding onItemSelected method

    @Override
    public void onItemSelected(AdapterView<?> arg0,
                               View arg1,
                               int position,
                               long id) {

        // make toast of the name of course
        // which is selected in spinner
        Toast.makeText(getApplicationContext(),
                        character_dropdown[position],
                        Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // Auto-generated method stub
    }
}
