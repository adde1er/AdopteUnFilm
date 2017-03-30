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

public class Suggestion  extends AppCompatActivity {
	ImageView affiche;
	TextView description;
	TextView titre;
	Handler handler = new Handler();
	String x;
	String toDesc;
	String toTitle;
	Bitmap icon;
	SharedPreferences settings;
	int idUser;
	RatingBar bar;

	private ListView mDrawerList;
	private DrawerLayout mDrawerLayout;
	private ArrayAdapter<String> mAdapter;
	private ActionBarDrawerToggle mDrawerToggle;
	private String mActivityTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.suggestions);
		
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
			
			mDrawerList = (ListView) findViewById(R.id.navList);
			mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
			mActivityTitle = getTitle().toString();

			addDrawerItems();
			setupDrawer();

			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			getSupportActionBar().setHomeButtonEnabled(true);
		 
	 }
	
	
	public void nextVote(View view){
		Thread t = new Thread(){
			public void run() {
				try {
					URL url = new URL("http://109.209.5.142:8860/adopteunfilmserver/vote/" + idUser + "/" + bar.getNumStars());
                	HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                	InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                	java.util.Scanner s = new java.util.Scanner(in).useDelimiter("\\A");
                    x =  s.hasNext() ? s.next() : "";
                    JSONObject jsnObject = new JSONObject(x);
                    x = jsnObject.getString("title");
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
		bar.setRating(3);
	}
	
	private void addDrawerItems() {
		String[] osArray = {"Film 1", "Film 2", "Film 3", "Film 4", "Film 5"};
		mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
		mDrawerList.setAdapter(mAdapter);

		mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(Suggestion.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void setupDrawer() {
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

			// Called when a drawer has settled in a completely open state.
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getSupportActionBar().setTitle("WatchList");
				invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
			}

			// Called when a drawer has settled in a completely closed state. //
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				getSupportActionBar().setTitle(mActivityTitle);
				invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
			}
		};

		mDrawerToggle.setDrawerIndicatorEnabled(true);
		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();


		// Activate the navigation drawer toggle
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
	        
}

