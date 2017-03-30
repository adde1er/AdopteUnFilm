package com.adopteunfilm.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.adopteunfilm.R;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Darkxell on 30/03/2017.
 */

public class MovieInfo extends AppCompatActivity {

    public ImageView affiche;
    public TextView description;
    public TextView titre;
    public Handler handler = new Handler();
    public String x;
    public String toDesc;
    public String toTitle;
    public Bitmap icon;

    private String movietodisplay = "";

    //shamelessly C/Ped #freecks's code <3

/*
* To start this activity from an other one with the wanted film, use this code:
*
 Intent intent = new Intent(FirstActivity.this, MovieInfo.class);
Bundle b = new Bundle();
b.putInt("movietodisplay", 1);
intent.putExtras(b);
startActivity(intent);
finish();
* */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movieinfo);

        affiche = (ImageView) findViewById(R.id.buttonAffiche);
        description = (TextView) findViewById(R.id.textDescription);
        titre = (TextView) findViewById(R.id.textTitre);

        Bundle b = getIntent().getExtras();
        if (b != null)
            movietodisplay = b.getString("movietodisplay");

        Thread t = new Thread() {

            public void run() {
                try {
                    URL url = new URL("http://www.omdbapi.com/?t=" + movietodisplay + "&plot=full");
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
    }
}
