package com.adopteunfilm.Activities;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.adopteunfilm.R;

/**
 * Created by Adrien on 02/03/2017.
 */
public class WishList extends AppCompatActivity {

    private DrawerLayout menuLayout; //Layout Principal
    private ListView menuElementsList; //Menu
    private ActionBarDrawerToggle menuToggle; //Gère l'ouverture et la fermeture du menu


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.wishlistlayout);
    }


}