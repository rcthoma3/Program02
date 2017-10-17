package com.example.tacosv3.program02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MovieDetailsFragment extends Fragment {
    private ShareActionProvider shareActionProvider;
    private String Movie;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
        {
            Movie = savedInstanceState.getString("Movie");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.movie_details_fragment, container, false);
    }

    public void setMovie(String inMovie) {
        Movie = inMovie;
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view != null && Movie != null) {
            String[] MovieKey = getResources().getStringArray(R.array.MovieMap);
            String[] MovieDetails = getResources().getStringArray(R.array.MoveDetails);
            for (int i = 0; i < MovieKey.length; i++) {
                if (Movie.equals(MovieKey[i]))
                    ((TextView) view.findViewById(R.id.MovieDetails)).setText(MovieDetails[i]);
            }
            ((ImageView) view.findViewById(R.id.MovieImage)).setImageResource(returnImageId(Movie));
        }
    }

    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putString("Movie", Movie);
    }

    int returnImageId(String Movie) {

        if (Movie.equals("Die Hard"))
            return R.drawable.diehard;
        else if (Movie.equals("The Dark Knight"))
            return R.drawable.thedarkknight;
        else if (Movie.equals("Mad Max: Fury Road"))
            return R.drawable.madmax;
        else if (Movie.equals("Airplane!"))
            return R.drawable.airplane;
        else if (Movie.equals("Anchorman: The Legend of Ron Burgundy"))
            return R.drawable.anchorman;
        else if (Movie.equals("Shaun of the Dead"))
            return R.drawable.shaunofthedead;
        else if (Movie.equals("The Shawshank Redemption"))
            return R.drawable.theshawshankredemption;
        else if (Movie.equals("12 Angry Men"))
            return R.drawable.twelveangrymen;
        else if (Movie.equals("The Godfather"))
            return R.drawable.thegodfather;
        else return 0; //PLACEHOLDER
    }
}