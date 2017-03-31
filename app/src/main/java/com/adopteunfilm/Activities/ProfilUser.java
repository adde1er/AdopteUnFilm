package com.adopteunfilm.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

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

public class ProfilUser extends NavBar {
    ListView wf;
    ArrayAdapter<String> wfadaptater;
    int id_profile;
    String x;
    String[] array;
    String pseudo;
    EditText user;
    Handler handler = new Handler();

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState, R.layout.profile);
        SharedPreferences shp = getSharedPreferences("AdopteUnFilm", MODE_PRIVATE);
        id_profile = shp.getInt("idUser",1);
        user = (EditText) findViewById(R.id.Username);
        wf = (ListView)findViewById(R.id.WatchFollowu);

        Thread t = new Thread(){
            public void run() {
                try {
                    URL url = new URL("http://109.209.5.142:8860/adopteunfilmserver/user/get/"+ id_profile);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    java.util.Scanner s = new java.util.Scanner(in).useDelimiter("\\A");
                    x =  s.hasNext() ? s.next() : "";
                    JSONObject jsnObject = new JSONObject(x);
                    pseudo = jsnObject.getString("pseudo");
                    urlConnection.disconnect();
                }catch(Exception e){
                }
                handler.post(new Runnable() {
                    public void run() {
                        user.setText(pseudo);
                    }
                });
            }
        };
        t.start();

    }

    public void modif(View view){
        pseudo = user.getText().toString();
        user.setText(pseudo);
        Thread t = new Thread(){
            public void run() {
                try {
                    URL url = new URL("http://109.209.5.142:8860/adopteunfilmserver/user/update/"+id_profile+"/"+pseudo);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.disconnect();

                }catch(Exception e){
                }
            }
        };
        t.start();
    }

    public void watchP(View view){
        Thread t = new Thread(){
            public void run() {
                try {
                    URL url = new URL("http://109.209.5.142:8860/adopteunfilmserver/wishlist/list/" + id_profile);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    java.util.Scanner s = new java.util.Scanner(in).useDelimiter("\\A");
                    x =  s.hasNext() ? s.next() : "";
                    JSONArray jsnArray = new JSONArray(x);
                    array = new String[jsnArray.length()];
                    for(int i = 0; i<jsnArray.length(); i++){
                        array[i] = jsnArray.getJSONObject(i).getString("title");
                    }
                    urlConnection.disconnect();
                } catch (Exception e) {
                }
                handler.post(new Runnable() {
                    public void run() {
                        adapt(array);
                    }
                });

            }
        };
        t.start();


    }
    public void adapt(String[] s){
        wfadaptater = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, s);
        wf.setAdapter(wfadaptater);

        /*wf.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), MovieInfo.class);
                Bundle b = new Bundle();
                b.putInt(wf.getItemAtPosition(), 1);
                intent.putExtras(b);
                startActivity(intent);
                finish();
            }
        });*/
    }

    public void followP(View view){
        Thread t2 = new Thread(){
            public void run() {
                try {
                    URL url = new URL("http://109.209.5.142:8860/adopteunfilmserver/user/follow/list/" + id_profile);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    java.util.Scanner s = new java.util.Scanner(in).useDelimiter("\\A");
                    x =  s.hasNext() ? s.next() : "";
                    JSONArray jsnArray = new JSONArray(x);
                    array = new String[jsnArray.length()];
                    for(int i = 0; i<jsnArray.length(); i++){
                        array[i] = jsnArray.getJSONObject(i).getString("pseudo");
                    }
                    urlConnection.disconnect();
                } catch (Exception e) {
                }
                handler.post(new Runnable() {
                    public void run() {
                        adapt2(array);
                    }
                });
            }
        };
        t2.start();

    }

    public void adapt2(String[] s){
        wfadaptater = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, s);
        wf.setAdapter(wfadaptater);

        /*wf.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), UserPage.class);
                startActivity(intent);
                finish();
            }
        });*/
    }

}
