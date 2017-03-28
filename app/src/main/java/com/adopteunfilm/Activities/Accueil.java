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
        Button rechercheFilms=(Button) findViewById(R.id.boutonFilms);
        rechercheFilms.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent accesrechercheFilms = new Intent(Accueil.this, RechercheFilms.class);
                startActivity(accesrechercheFilms);
            }
        });

        Button rechercheUser=(Button) findViewById(R.id.boutonUtilisateurs);
        rechercheUser.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent accesrechercheusers = new Intent(Accueil.this, RechercheUser.class);
                startActivity(accesrechercheusers);
            }
        });

        Button profil =(Button) findViewById(R.id.boutonProfil);
        profil.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent accesProfil = new Intent(Accueil.this, ProfilUser.class);
                startActivity(accesProfil);
            }
        });

        Button suggestions =(Button) findViewById(R.id.boutonTrouverFilm);
        suggestions.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent accesSuggestions = new Intent(Accueil.this, Suggestion.class);
                startActivity(accesSuggestions);
            }
        });
    }

    public void suggestions(View view) {
    	Intent intent = new Intent(this, Suggestion.class);
    	startActivity(intent);
    }
    
}

