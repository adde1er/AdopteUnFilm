package com.adopteunfilm.Activities;

import com.adopteunfilm.R;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;

import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.SharedPreferences;
import java.net.*;
import java.io.*;
import java.lang.*;
import org.json.JSONObject;
import org.json.JSONArray;


/**
 * Created by Adrien on 02/03/2017.
 */

public class Suggestion  extends NavBar {
	ImageView affiche;
	TextView description;
	TextView titre;
	String x;
	String toDesc;
	String toTitle;
	Bitmap icon;
	SharedPreferences settings;
	int idUser;
	int idFilm;
	RatingBar bar;
	Handler handler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.suggestions);
		
		affiche = (ImageView) findViewById(R.id.buttonAffiche);
		description = (TextView) findViewById(R.id.textDescription);
		titre = (TextView) findViewById(R.id.textTitre);
		settings = getSharedPreferences("AdopteUnFilm",MODE_PRIVATE);
		idUser = settings.getInt("idUser",1);
		
		bar = (RatingBar) findViewById(R.id.ratingBar);
	        
			Thread t = new Thread(){
				public void run() {
					try {
						URL url = new URL("http://109.209.5.142:8860/adopteunfilmserver/user/recommend/" + idUser);
	                	HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
	                	InputStream in = new BufferedInputStream(urlConnection.getInputStream());
	                	java.util.Scanner s = new java.util.Scanner(in).useDelimiter("\\A");
	                    x =  s.hasNext() ? s.next() : "";
	                    JSONObject jsnObject = new JSONObject(x);
	                    x = jsnObject.getString("title");
	                    idFilm = jsnObject.getInt("id");
	                    x = x.replaceAll(" ", "%20");
	                    x = x.replaceAll("\'", "%27");
	                    
						url = new URL("http://www.omdbapi.com/?s="+x);
	                	urlConnection = (HttpURLConnection) url.openConnection();
	                	in = new BufferedInputStream(urlConnection.getInputStream());
	                	s = new java.util.Scanner(in).useDelimiter("\\A");
	                    x =  s.hasNext() ? s.next() : "";
	                    jsnObject = new JSONObject(x);
	                    JSONArray jsnArray = jsnObject.getJSONArray("Search");
	                    jsnObject = jsnArray.getJSONObject(0);
	                    toTitle = jsnObject.getString("Title");
	                    toTitle = toTitle.replaceAll(" ", "%20");
	                    toTitle = toTitle.replaceAll("\'", "%27");
	                    
	                    url = new URL("http://www.omdbapi.com/?t="+toTitle+"&plot=full");
	                	urlConnection = (HttpURLConnection) url.openConnection();
	                	in = new BufferedInputStream(urlConnection.getInputStream());
	                	s = new java.util.Scanner(in).useDelimiter("\\A");
	                    x =  s.hasNext() ? s.next() : "";
	                    jsnObject = new JSONObject(x);
	                    toTitle = jsnObject.getString("Title");
	                    toDesc = jsnObject.getString("Plot");
	                    x = jsnObject.getString("Poster");
	                    
	                    icon = null;
	                    in = new URL(x).openStream();
	                    icon = BitmapFactory.decodeStream(in);
	                }catch(Exception e){
	                }
		            handler.post(new Runnable() {
		                public void run() {
		                	affiche.setImageBitmap(icon);
		                	description.setText(toDesc);
		                	titre.setText(toTitle);
		                }
		            });
		        }
			};
			t.start();
		 
	 }
	
	
	public void nextVote(View view){
		Thread t = new Thread(){
			public void run() {
				try {
					URL url = new URL("http://109.209.5.142:8860/adopteunfilmserver/vote/" + idUser + "/" + (int) bar.getRating());
                	HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                	InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                	java.util.Scanner s = new java.util.Scanner(in).useDelimiter("\\A");
                    x =  s.hasNext() ? s.next() : "";
                    JSONObject jsnObject = new JSONObject(x);
                    x = jsnObject.getString("title");
                    idFilm = jsnObject.getInt("id");
                    x = x.replaceAll(" ", "%20");
                    x = x.replaceAll("\'", "%27");
                    
					url = new URL("http://www.omdbapi.com/?s="+x);
                	urlConnection = (HttpURLConnection) url.openConnection();
                	in = new BufferedInputStream(urlConnection.getInputStream());
                	s = new java.util.Scanner(in).useDelimiter("\\A");
                    x =  s.hasNext() ? s.next() : "";
                    jsnObject = new JSONObject(x);
                    JSONArray jsnArray = jsnObject.getJSONArray("Search");
                    jsnObject = jsnArray.getJSONObject(0);
                    toTitle = jsnObject.getString("Title");
                    toTitle = toTitle.replaceAll(" ", "%20");
                    toTitle = toTitle.replaceAll("\'", "%27");
                    
                    url = new URL("http://www.omdbapi.com/?t="+toTitle+"&plot=full");
                	urlConnection = (HttpURLConnection) url.openConnection();
                	in = new BufferedInputStream(urlConnection.getInputStream());
                	s = new java.util.Scanner(in).useDelimiter("\\A");
                    x =  s.hasNext() ? s.next() : "";
                    jsnObject = new JSONObject(x);
                    toTitle = jsnObject.getString("Title");
                    toDesc = jsnObject.getString("Plot");
                    x = jsnObject.getString("Poster");
                    
                    icon = null;
                    in = new URL(x).openStream();
                    icon = BitmapFactory.decodeStream(in);
                }catch(Exception e){
                }
	            handler.post(new Runnable() {
	                public void run() {
	                	affiche.setImageBitmap(icon);
	                	description.setText(toDesc);
	                	titre.setText(toTitle);
	                	bar.setRating(3);
	                }
	            });
	        }
		};
		t.start();
	}
	
	public void wishlist(View view){
		Thread t = new Thread(){
			public void run() {
				try {
					URL url = new URL("http://109.209.5.142:8860/adopteunfilmserver/wishlist/update/" + idUser + "/" + idFilm);
                	HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                	InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                }catch(Exception e){
                }
	        }
		};
		t.start();
	}
	
	public void share(View view){
		Thread t = new Thread(){
			public void run() {
				try {
					URL url = new URL("http://109.209.5.142:8860/adopteunfilmserver/user/share/" + idUser + "/" + idFilm);
                	HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                	InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                }catch(Exception e){
                }
	        }
		};
		t.start();
	}
}

