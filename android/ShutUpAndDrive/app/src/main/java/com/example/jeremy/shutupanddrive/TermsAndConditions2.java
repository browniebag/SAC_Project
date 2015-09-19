package com.example.jeremy.shutupanddrive;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class TermsAndConditions2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions2);

        Button acpt_bnt = (Button)findViewById(R.id.accept_button);
        acpt_bnt.setOnClickListener(
                new Button.OnClickListener(){
                public void onClick(View v){
                    setResult(RESULT_OK);
                    finish();
                }
            }
        );

        Button decline_btn = (Button)findViewById(R.id.decline_button);
        decline_btn.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        setResult(RESULT_CANCELED);
                        finish();
                    }
                }
        );


    }




}
