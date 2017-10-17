package com.example.tacosv3.program02;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoryOptions extends AppCompatActivity {
    private ShareActionProvider shareActionProvider;
    private String CategoryString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_options);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        populateList();
    }

    private void populateList() {
        CategoryString = getIntent().getExtras().getString("Category");
        ListView listView = (ListView) findViewById(R.id.CategoryOptionsList);
        List<String> CatOptionsList;
        if (CategoryString.equals("Action"))
            CatOptionsList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.ActionOptions)));
        else if (CategoryString.equals("Comedy"))
            CatOptionsList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.ComedyOptions)));
        else if (CategoryString.equals("Drama"))
            CatOptionsList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.DramaOptions)));
        else
            CatOptionsList = new ArrayList<String>(Arrays.asList(new String[] {"Error - Category Not Found"})); //PLACEHOLDER CASE

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                CatOptionsList);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                if ((findViewById(R.id.fragment_container) == null)){
                    Intent intent = new Intent(CategoryOptions.this, MovieDetails.class);
                    if (CategoryString.equals("Action"))
                        intent.putExtra("MovieChoice", getResources().getStringArray(R.array.ActionOptions)[position]);
                    else if (CategoryString.equals("Comedy"))
                        intent.putExtra("MovieChoice", getResources().getStringArray(R.array.ComedyOptions)[position]);
                    else if (CategoryString.equals("Drama"))
                        intent.putExtra("MovieChoice", getResources().getStringArray(R.array.DramaOptions)[position]);
                    intent.putExtra("Category", CategoryString);
                    startActivity(intent);
                }
                else{
                    MovieDetailsFragment details = new MovieDetailsFragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    if (CategoryString.equals("Action"))
                        details.setMovie(getResources().getStringArray(R.array.ActionOptions)[position]);
                    if (CategoryString.equals("Comedy"))
                        details.setMovie(getResources().getStringArray(R.array.ComedyOptions)[position]);
                    if (CategoryString.equals("Drama"))
                        details.setMovie(getResources().getStringArray(R.array.DramaOptions)[position]);
                    ft.replace(R.id.fragment_container, details);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.addToBackStack(null);
                    ft.commit();
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the app bar.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        shareActionProvider =
                (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        setShareActionIntent("Want to join me for a movie?");
        return super.onCreateOptionsMenu(menu);
    }

    private void setShareActionIntent(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_view_random:
                Intent intent = new Intent(this, RandomMovieRecomendation.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
