package com.adopteunfilm.Activities;

import com.adopteunfilm.R;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class NavBar extends AppCompatActivity {
	private ListView mDrawerList;
	private DrawerLayout mDrawerLayout;
	private ArrayAdapter<String> mAdapter;
	private ActionBarDrawerToggle mDrawerToggle;
	private String mActivityTitle;
	
	private int activity;
	
	
	protected void onCreate(Bundle savedInstanceState, int activity) {
		super.onCreate(savedInstanceState);
		setContentView(activity);
		
		mDrawerList = (ListView) findViewById(R.id.navList);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mActivityTitle = getTitle().toString();
		
		addDrawerItems();
		setupDrawer();
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);		
	}
	
	private void addDrawerItems() {
		String[] osArray = {"Film 1", "Film 2", "Film 3", "Film 4", "Film 5"};
		mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
		mDrawerList.setAdapter(mAdapter);

		mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//Toast.makeText(NavBar.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
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

