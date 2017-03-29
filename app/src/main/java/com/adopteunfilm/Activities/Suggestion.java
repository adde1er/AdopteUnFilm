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
import android.widget.ImageView;
import android.view.View;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.net.*;
import java.io.*;
import java.lang.*;
import org.json.JSONObject;


/**
 * Created by Adrien on 02/03/2017.
 */

public class Suggestion  extends AppCompatActivity {
	public ImageView affiche;
	public TextView description;
	public TextView titre;
	public Handler handler = new Handler();
	public String x;
	public String toDesc;
	public String toTitle;
	public Bitmap icon;

	/*	//wishlist
	private ListView mDrawerList;
	private DrawerLayout mDrawerLayout;
	private ArrayAdapter<String> mAdapter;
	private ActionBarDrawerToggle mDrawerToggle;
	private String mActivityTitle; */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.suggestions);

		affiche = (ImageView) findViewById(R.id.buttonAffiche);
		description = (TextView) findViewById(R.id.textDescription);
		titre = (TextView) findViewById(R.id.textTitre);

		Thread t = new Thread() {
			public void run() {
				try {
					URL url = new URL("http://www.omdbapi.com/?t=Jumanji&plot=full");
					HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
					InputStream in = new BufferedInputStream(urlConnection.getInputStream());
					java.util.Scanner s = new java.util.Scanner(in).useDelimiter("\\A");
					x = s.hasNext() ? s.next() : "";
					JSONObject jsnObject = new JSONObject(x);
					toTitle = jsnObject.getString("Title");
					toDesc = jsnObject.getString("Plot");
					x = jsnObject.getString("Poster");

					icon = null;
					in = new URL(x).openStream();
					icon = BitmapFactory.decodeStream(in);
				} catch (Exception e) {
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

/*
		mDrawerList = (ListView)findViewById(R.id.navList);mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
		mActivityTitle = getTitle().toString();

		addDrawerItems();
		setupDrawer();

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
	}

	private void addDrawerItems() {
		String[] osArray = { "Android", "iOS", "Windows", "OS X", "Linux" };
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
				getSupportActionBar().setTitle("Navigation!");
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
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
		*/
}
}
