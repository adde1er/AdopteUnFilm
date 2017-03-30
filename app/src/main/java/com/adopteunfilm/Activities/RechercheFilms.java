package com.adopteunfilm.Activities;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.adopteunfilm.R;

/**
 * Created by Adrien on 02/03/2017.
 */

public class RechercheFilms extends AppCompatActivity {

    public ListView lv_film;
    public SearchView sv_film;

    String[] test = {"test1", "test2", "test3", "test4", "test5", "test6", "test7", "test8", "test9", "test10"};
    ArrayAdapter<String> adapter_film;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.recherche_films);
        handleIntent(getIntent());

        lv_film = (ListView) findViewById(R.id.listview_films);
        sv_film = (SearchView) findViewById(R.id.searchview_films);

        adapter_film = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,test);
        lv_film.setAdapter(adapter_film);

        sv_film.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {

                adapter_film.getFilter().filter(text);

                return false;
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
    }

    private void doMySearch(String query) {
    }
}
