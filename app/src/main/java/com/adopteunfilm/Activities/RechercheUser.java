package com.adopteunfilm.Activities;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.adopteunfilm.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Utilisateur on 28/03/2017.
 */

public class RechercheUser extends NavBar {

    public ListView listview;
    public TextView searchbar;
    public Button button;
    ArrayList<String> names;
    String recherchetext;
    Handler handler = new Handler();    
    RechercheUser activity = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.recherche_users);

        listview = (ListView) findViewById(R.id.listViewUsers);
        searchbar = (TextView) findViewById(R.id.searchbar);
        button = (Button) findViewById(R.id.searchbutton);
    }

    public void onResearch(View view) {
        recherchetext = searchbar.getText().toString();
        
        
        Thread t = new Thread(){
        	public void run() {
        		try {
        			URL url = new URL("http://109.209.5.142:8860/adopteunfilmserver/user/search/" + recherchetext);
        			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        			InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        			java.util.Scanner s = new java.util.Scanner(in).useDelimiter("\\A");
        			String x = s.hasNext() ? s.next() : "";
        			
        			JSONObject jsnObject;
        			JSONArray jsnArray = new JSONArray(x);
        			
        			names = new ArrayList<>();
        			
        			for (int i = 0; i < jsnArray.length(); ++i) {
        				jsnObject = jsnArray.getJSONObject(i);
        				names.add(jsnObject.getString("pseudo"));
        			}
        			
        		} catch (Exception e) {
        		}
        		handler.post(new Runnable() {
	                public void run() {
	                	ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, names);
	                	listview.setAdapter(arrayAdapter);                	
	                }
	            });
        	}       	
        };
        t.start();
    }
}