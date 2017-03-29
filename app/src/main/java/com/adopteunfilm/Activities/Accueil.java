package com.adopteunfilm.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.adopteunfilm.R;

public class Accueil extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
    }

    public void suggestion(View view){
    	Intent intent = new Intent(this, Suggestion.class);
    	startActivity(intent);
    }
    
    public void profilUser(View view){
    	Intent intent = new Intent(this, ProfilUser.class);
    	startActivity(intent);
    }
    
    public void rechercheUser(View view){
    	Intent intent = new Intent(this, RechercheUser.class);
    	startActivity(intent);
    }
    
    public void rechercheFilms(View view){
    	Intent intent = new Intent(this, RechercheFilms.class);
    	startActivity(intent);
    }
    
}

