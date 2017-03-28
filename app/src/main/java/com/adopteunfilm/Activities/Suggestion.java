package com.adopteunfilm.Activities;

import com.adopteunfilm.R;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.os.Handler;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.net.*;
import java.io.*;
import java.lang.*;
import org.json.JSONArray;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.suggestions);
		 
		affiche = (ImageView) findViewById(R.id.buttonAffiche);
		description = (TextView) findViewById(R.id.textDescription);
		titre = (TextView) findViewById(R.id.textTitre);
	        
			Thread t = new Thread(){
				public void run() {
					try {
						URL url = new URL("http://www.omdbapi.com/?t=1-900&plot=full");
	                	HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
	                	InputStream in = new BufferedInputStream(urlConnection.getInputStream());
	                	java.util.Scanner s = new java.util.Scanner(in).useDelimiter("\\A");
	                    x =  s.hasNext() ? s.next() : "";
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
			t.start();
		 
	 }
	        
}
