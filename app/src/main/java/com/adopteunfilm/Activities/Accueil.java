package com.adopteunfilm.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.adopteunfilm.R;

public class Accueil extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

    }

    public void suggestions(View view) {
    	Intent intent = new Intent(this, Suggestion.class);
    	startActivity(intent);
    }
    
}
