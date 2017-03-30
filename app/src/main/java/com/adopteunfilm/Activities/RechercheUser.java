package com.adopteunfilm.Activities;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.adopteunfilm.R;

/**
 * Created by Utilisateur on 28/03/2017.
 */

public class RechercheUser extends AppCompatActivity {

    public ListView lv_user;
    public SearchView sv_user;

    String[] test = {"test1", "test2", "test3", "test4", "test5", "test6", "test7", "test8", "test9", "test10"};
    ArrayAdapter<String> adapter_user;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.recherche_users);
        handleIntent(getIntent());

        lv_user = (ListView) findViewById(R.id.listview_films);
        sv_user = (SearchView) findViewById(R.id.searchview_films);

        adapter_user = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,test);
        lv_user.setAdapter(adapter_user);

        sv_user.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {

                adapter_user.getFilter().filter(text);

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