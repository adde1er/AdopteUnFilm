package com.adopteunfilm.Activities;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import com.adopteunfilm.R;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Handler;

public class NavBar extends AppCompatActivity {
	private ListView mDrawerList;
	private DrawerLayout mDrawerLayout;
	private ArrayAdapter<String> mAdapter;
	private ActionBarDrawerToggle mDrawerToggle;
	private String mActivityTitle;
	
	Handler handlerNav = new Handler();
	SharedPreferences settings;
	SharedPreferences.Editor editor;
	int idUserNav;
	String xNav;
	String[] array;
	NavBar navbar = this;
	
	private int activity;
	
	
	protected void onCreate(Bundle savedInstanceState, int activity) {
		super.onCreate(savedInstanceState);
		setContentView(activity);
		
		mDrawerList = (ListView) findViewById(R.id.navList);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mActivityTitle = getTitle().toString();
		
		settings = getSharedPreferences("AdopteUnFilm",MODE_PRIVATE);
        editor = settings.edit();

		setupDrawer();
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);	
		Thread t = new Thread(){
			public void run() {
				try {
					if(settings.getInt("idUser", -1) == -1){
						URL url = new URL("http://109.209.5.142:8860/adopteunfilmserver/user/add/default");
	                	HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
	                	InputStream in = new BufferedInputStream(urlConnection.getInputStream());
	                	java.util.Scanner s = new java.util.Scanner(in).useDelimiter("\\A");
	                    String x =  s.hasNext() ? s.next() : "";
	                    editor.putInt("idUser", Integer.parseInt(x));
						editor.commit();
					}
					
					idUserNav = settings.getInt("idUser",-1);
					
					URL url = new URL("http://109.209.5.142:8860/adopteunfilmserver/wishlist/list/" + idUserNav);
					HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
					InputStream in = new BufferedInputStream(urlConnection.getInputStream());
					java.util.Scanner s = new java.util.Scanner(in).useDelimiter("\\A");
					xNav =  s.hasNext() ? s.next() : "";
					JSONArray jsnArray = new JSONArray(xNav);
					array = new String[jsnArray.length()];
					for(int i = 0; i<jsnArray.length(); i++){
						array[i] = jsnArray.getJSONObject(i).getString("title");
					}
				}catch(Exception e){
				}
				handlerNav.post(new Runnable() {
					public void run() {
						addDrawerItems(array);
					}
				});
			}
		};
		t.start();
	}
        
	
	private void addDrawerItems(String[] osArray) {
	
		mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
		mDrawerList.setAdapter(mAdapter);
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

