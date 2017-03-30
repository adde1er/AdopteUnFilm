package com.adopteunfilm.Activities;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
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

public class RechercheUser extends AppCompatActivity {

    public ListView listview;
    public TextView searchbar;
    public Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recherche_users);

        listview = (ListView) findViewById(R.id.listViewUsers);
        searchbar = (TextView) findViewById(R.id.searchbar);
        button = (Button) findViewById(R.id.searchbutton);
    }

    public void onResearchUser(View view) {
        String recherchetext = searchbar.getText().toString();

        try {
            URL url = new URL("http://109.209.5.142:8860/adopteunfilmserver/user/search/" + recherchetext);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            java.util.Scanner s = new java.util.Scanner(in).useDelimiter("\\A");
            String x = s.hasNext() ? s.next() : "";

            JSONObject jsnObject;
            JSONArray jsnArray = new JSONArray(x);

            ArrayList<String> names = new ArrayList<>();

            for (int i = 0; i < jsnArray.length(); ++i) {
                jsnObject = jsnArray.getJSONObject(i);
                names.add(jsnObject.toString());
                Log.i("[i]",jsnObject.toString());
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    names);
            listview.setAdapter(arrayAdapter);

        } catch (Exception e) {
            Log.e("ListView","Could not build the listview from server data",e);
        }
    }
}