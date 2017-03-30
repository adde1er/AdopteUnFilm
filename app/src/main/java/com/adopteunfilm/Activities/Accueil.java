package com.adopteunfilm.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import com.adopteunfilm.R;

public class Accueil extends NavBar{

	SharedPreferences settings;
    SharedPreferences.Editor editor;
    Handler handler = new Handler();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.homepage);
        
        settings = getSharedPreferences("AdopteUnFilm",MODE_PRIVATE);
        editor = settings.edit();
        
        
        if(settings.getInt("idUser", -1) == -1){
        	Thread t = new Thread(){
				public void run() {
					try {
						/*URL url = new URL("http://109.209.5.142:8860/adopteunfilmserver/user/add/default"); // Vous touchez à ça je revert.
	                	HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
	                	InputStream in = new BufferedInputStream(urlConnection.getInputStream());
	                	java.util.Scanner s = new java.util.Scanner(in).useDelimiter("\\A");
	                    String x =  s.hasNext() ? s.next() : "";
	                    editor.putInt("idUser", Integer.parseInt(x));*/
						editor.putInt("idUser", 4); //temp
						editor.commit();					
	                }catch(Exception e){
	                }
					handler.post(new Runnable() {
		                public void run() {
		                }
		            });
		        }
			};
			t.start();
            
        }
        
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

