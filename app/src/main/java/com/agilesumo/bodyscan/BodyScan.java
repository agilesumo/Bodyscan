package com.agilesumo.bodyscan;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class BodyScan extends AppCompatActivity {

    private ImageView currentDisplayedImage;
    private TextView currentInstructionText;


    private final int INTERVAL = 5000;

    final int[] FEMALE_IMAGE_IDS = {
            R.drawable.female_right_toes,
            R.drawable.female_right_foot,
            R.drawable.female_right_ankle,
            R.drawable.female_right_lower_leg,
            R.drawable.female_right_knee,
            R.drawable.female_right_thigh,


            R.drawable.female_left_toes,
            R.drawable.female_left_foot,
            R.drawable.female_left_ankle,
            R.drawable.female_left_lower_leg,
            R.drawable.female_left_knee,
            R.drawable.female_left_thigh,

            R.drawable.female_hips,
            R.drawable.female_stomach,
            R.drawable.female_chest,

            R.drawable.female_right_shoulder,
            R.drawable.female_right_upper_arm,
            R.drawable.female_right_elbow,
            R.drawable.female_right_forearm,
            R.drawable.female_right_hand,

            R.drawable.female_left_shoulder,
            R.drawable.female_left_upper_arm,
            R.drawable.female_left_elbow,
            R.drawable.female_left_forearm,
            R.drawable.female_left_hand,

            R.drawable.female_neck,
            R.drawable.female_head,
            R.drawable.female_full_body


    };

    final int[] MALE_IMAGE_IDS = {
            R.drawable.male_right_toes,
            R.drawable.male_right_foot,
            R.drawable.male_right_ankle,
            R.drawable.male_right_lower_leg,
            R.drawable.male_right_knee,
            R.drawable.male_right_thigh,

            R.drawable.male_left_toes,
            R.drawable.male_left_foot,
            R.drawable.male_left_ankle,
            R.drawable.male_left_lower_leg,
            R.drawable.male_left_knee,
            R.drawable.male_left_thigh,

            R.drawable.male_hips,
            R.drawable.male_stomach,
            R.drawable.male_chest,

            R.drawable.male_right_shoulder,
            R.drawable.male_right_upper_arm,
            R.drawable.male_right_elbow,
            R.drawable.male_right_forearm,
            R.drawable.male_right_hand,

            R.drawable.male_left_shoulder,
            R.drawable.male_left_upper_arm,
            R.drawable.male_left_elbow,
            R.drawable.male_left_forearm,
            R.drawable.male_left_hand,

            R.drawable.male_neck,
            R.drawable.male_head,
            R.drawable.male_full_body
    };

    private final String[] INSTRUCTIONS = {

            "Focus on your right toes",
            "Focus on your right foot",
            "Focus on your right anlke",
            "Focus on your right lower leg",
            "Focus on your right knee",
            "Focus on your right thigh",
            "Focus on your left toes",
            "Focus on your left foot",
            "Focus on your left ankle",
            "Focus on your left lower leg",
            "Focus on your left knee",
            "Focus on your left thigh",
            "Focus on your hips",
            "Focus on your stomach",
            "Focus on your chest",
            "Focus on your right shoulder",
            "Focus on your right upper arm",
            "Focus on your right elbow",
            "Focus on your right forearm",
            "Focus on your right hand",
            "Focus on your left shoulder",
            "Focus on your left upper arm",
            "Focus on your left elbow",
            "Focus on your left forearm",
            "Focus on your left hand",
            "Focus on your neck",
            "Focus on your head",
            "Well Done! You Finished"

    };



     private int counter;

     private int[] listImages;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_scan);

        currentDisplayedImage = (ImageView) findViewById(R.id.currentBodyImage);

        currentInstructionText = (TextView) findViewById(R.id.instruction_text) ;

        Intent intent = getIntent();

        String genderStr = intent.getStringExtra(MainActivity.EXTRA_GENDER);

        String durationStr = intent.getStringExtra(MainActivity.EXTRA_DURATION);
        int durationInSecs;

        if(durationStr.length() == 6)
            durationInSecs = Integer.parseInt(durationStr.substring(0,1));
        else
            durationInSecs = Integer.parseInt(durationStr.substring(0,2));

        if(genderStr.equals("Female"))
            listImages = FEMALE_IMAGE_IDS;
        else
            listImages = MALE_IMAGE_IDS;

        counter = 0;

        new CountDownTimer(1000*durationInSecs*30,1000*durationInSecs) {
            public void onTick(long millisUntilFinished) {
                if(counter < listImages.length)
                updateDisplay();
                counter++;
            }

            public void onFinish() {

            }
        }.start();







    }

    private void updateDisplay(){
        currentDisplayedImage.setImageResource(listImages[counter]);

        if (counter < INSTRUCTIONS.length) {
            currentInstructionText.setText(INSTRUCTIONS[counter]);

        }
    }









}
