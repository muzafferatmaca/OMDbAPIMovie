package com.muzafferatmaca.omdbapimovie.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;
import com.muzafferatmaca.omdbapimovie.adapters.SearchAdapter;
import com.muzafferatmaca.omdbapimovie.DialogUtils;
import com.muzafferatmaca.omdbapimovie.models.Search;
import com.muzafferatmaca.omdbapimovie.models.SearchModel;
import com.muzafferatmaca.omdbapimovie.restApi.ApiUtils;
import com.muzafferatmaca.omdbapimovie.restApi.MovieDao;
import com.muzafferatmaca.omdbapimovie.R;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    MovieDao movieDao;
    TextView infoTextView;
    ProgressDialog dialog;
    SearchAdapter searchAdapter;
    List<Search> searchList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.mainText);
        define();
        searchList = new ArrayList<>();
        RecyclerView.LayoutManager manager = new GridLayoutManager(MainActivity.this, 3);
        recyclerView.setLayoutManager(manager);
        searchAdapter = new SearchAdapter(searchList, this, this);
        movieDao = ApiUtils.movieDao();
        setSearchView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        searchView.clearFocus();
    }

    public void define() {
        searchView = findViewById(R.id.searchView);
        infoTextView = findViewById(R.id.infoTextView);
        recyclerView = findViewById(R.id.recyclerView);
    }

    public void setSearchView() {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                dialog = DialogUtils.createProgressDialog(MainActivity.this);
                getSearchMovie(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return true;
            }
        });

    }

    public void getSearchMovie(String sTest) {

        movieDao.allMovies(ApiUtils.API_KEY, sTest).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {

                String responseCase = response.body().getResponse();

                if (responseCase.equals("False")) {

                    searchList.clear();
                    recyclerView.setVisibility(View.INVISIBLE);
                    infoTextView.setVisibility(View.VISIBLE);
                    infoTextView.setText(R.string.errorMessage);

                } else {

                    recyclerView.setVisibility(View.VISIBLE);
                    infoTextView.setVisibility(View.INVISIBLE);
                    List<Search> searchList = response.body().getSearch();
                    searchAdapter = new SearchAdapter(searchList, MainActivity.this, getApplicationContext());
                    recyclerView.setAdapter(searchAdapter);
                }
                dialog.dismiss();
                //after the call hide keyboard
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputManager.isAcceptingText()) {
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}