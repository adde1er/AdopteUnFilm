package com.adopteunfilm.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

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

public class ProfilUser extends AppCompatActivity{
    ListView wf;
    ArrayList wfdata;
    int id_profile;
    String x;
    String pseudo = "MissAccro";
    String movie;
    EditText user;
    Handler handler = new Handler();

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        SharedPreferences shp = getSharedPreferences("AdopteUnFilm", MODE_PRIVATE);
        //id_profile = shp.getInt("idUser",1);
        id_profile =4;
        user = (EditText) findViewById(R.id.Username);
        wf = (ListView)findViewById(R.id.WatchFollow);

        /* set name to display
        /Thread t = new Thread(){
            public void run() {
                try {
                    URL url = new URL("http://109.209.5.142:8860/adopteunfilmserver/user/get/"+ id_profile);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    java.util.Scanner s = new java.util.Scanner(in).useDelimiter("\\A");
                    x =  s.hasNext() ? s.next() : "";
                    JSONObject jsnObject = new JSONObject(x);
                    pseudo = jsnObject.getString("pseudo");
                }catch(Exception e){
                }
                handler.post(new Runnable() {
                    public void run() {
                        user.setText(pseudo);
                    }
                });
            }
        };
        t.start();*/

    }

    public void modif(View view){
        pseudo = user.getText().toString();
        user.setText(pseudo);
        /*Thread t = new Thread(){
            public void run() {
                try {
                    URL url = new URL("http://109.209.5.142:8860/adopteunfilmserver/user/update"+id_profile+"/"+pseudo);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    java.util.Scanner s = new java.util.Scanner(in).useDelimiter("\\A");
                    x =  s.hasNext() ? s.next() : "";
                    JSONObject jsnObject = new JSONObject(x);
                    pseudo = jsnObject.getString("pseudo");
                }catch(Exception e){
                }
                handler.post(new Runnable() {
                    public void run() {
                        user.setText(pseudo);
                    }
                });
            }
        };
        t.start();*/
    }

    public void watchP(View view){
        /*Thread t = new Thread(){
            public void run() {
                try {
                    URL url = new URL("http://109.209.5.142:8860/adopteunfilmserver/wihslist/list/"+id_profile);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    java.util.Scanner s = new java.util.Scanner(in).useDelimiter("\\A");
                    x = s.hasNext() ? s.next() : "";
                    JSONObject jsnObject = new JSONObject(x);
                    movie = jsnObject.getString("Title");
                    wfdata.add(movie);
                }catch(Exception e){
                }
            }
        };
        t.start();*/
        wfdata = new ArrayList();
        wfdata.add("film1");
        wfdata.add("film2");
        wfdata.add("film3");
        wfdata.add("film4");
        wfdata.add("film5");
        ArrayAdapter wfadaptater = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, wfdata);
        wf.setAdapter(wfadaptater);
        wf.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //doit lancer vers la page de film avec son id
                Intent it = new Intent(view.getContext(),MovieInfo.class);
                startActivity(it);
            }
        });
    }

    public void followP(View view){
        /*Thread t2 = new Thread(){
            public void run() {
                try {
                    URL url = new URL("http://109.209.5.142:8860/adopteunfilmserver/user/follow/list/"+id_profile);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    java.util.Scanner s = new java.util.Scanner(in).useDelimiter("\\A");
                    x =  s.hasNext() ? s.next() : "";
                    JSONArray jsnArray = new JSONArray(x);
                    for (int i = 0; i < jsnArray.length(); i++) {

                    }
                    JSONObject jsnObject = new JSONObject(x);
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
        t2.start();*/
        wfdata = new ArrayList();
        wfdata.add("user1");
        wfdata.add("user2");
        wfdata.add("user3");
        wfdata.add("user4");
        wfdata.add("user5");
        ArrayAdapter wfadaptater2 = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, wfdata);
        wf.setAdapter(wfadaptater2);
        wf.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //doit get l'id du user puis lancer sur sa page de profile
                Intent it = new Intent(view.getContext(),ProfilUser.class);
                startActivity(it);
            }
        });
    }

}
