package com.agilesumo.bodyscan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.Time;

public class MainActivity extends AppCompatActivity  {

    public final static String EXTRA_GENDER = "com.agilesumo.bodyscan.gender";
    public final static String EXTRA_DURATION = "com.agilesumo.bodyscan.duration";


    private Spinner genderSpinner;

    private Spinner durationSpinner;

    private TextView totalDuration;

    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings = PreferenceManager.getDefaultSharedPreferences(this);


        String genderSpinnerPos = settings.getString("gender", "0");
        String durationSpinnerPos = settings.getString("duration", "0");

        genderSpinner = findViewById(R.id.sex_spinner);

        totalDuration = findViewById(R.id.total_duration);


        durationSpinner = findViewById(R.id.duration_spinner);

        durationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedDurationOption = parent.getItemAtPosition(position).toString();

                TimeDuration timeDuration = new TimeDuration();

                int durationInSecs = 0;

                if(selectedDurationOption.length() == 6)
                    durationInSecs = Integer.parseInt(selectedDurationOption.substring(0,1));
                else
                    durationInSecs = Integer.parseInt(selectedDurationOption.substring(0,2));

                timeDuration.addSeconds(durationInSecs*30);


                totalDuration.setText(timeDuration.toStringLong());
            }

            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        genderSpinner.setSelection(Integer.parseInt(genderSpinnerPos));
        durationSpinner.setSelection(Integer.parseInt(durationSpinnerPos));


    }

    protected void onStop(){
        super.onStop();
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("gender", ""+genderSpinner.getSelectedItemPosition() );
        editor.putString("duration", ""+durationSpinner.getSelectedItemPosition() );
        editor.commit();



    }


    public void startBodyScan(View view){
        Intent intent = new Intent(this, BodyScan.class);

        String gender = genderSpinner.getSelectedItem().toString();
        String duration = durationSpinner.getSelectedItem().toString();
        intent.putExtra(EXTRA_GENDER, gender);
        intent.putExtra(EXTRA_DURATION, duration);
        startActivity(intent);

    }



}
