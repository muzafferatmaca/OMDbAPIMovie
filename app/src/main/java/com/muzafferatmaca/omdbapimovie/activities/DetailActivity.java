package com.muzafferatmaca.omdbapimovie.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.Toast;

import com.muzafferatmaca.omdbapimovie.models.DetailModel;
import com.muzafferatmaca.omdbapimovie.R;
import com.muzafferatmaca.omdbapimovie.restApi.ApiUtils;
import com.muzafferatmaca.omdbapimovie.restApi.MovieDao;
import com.muzafferatmaca.omdbapimovie.databinding.ActivityDetailBinding;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding detailBinding;
    MovieDao movieDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailBinding = DataBindingUtil.setContentView(this,R.layout.activity_detail);
        detailBinding.setDetailActivityObject(this);

        movieDao = ApiUtils.movieDao();
        getDetails();
    }

    public void getDetails() {

        String imdbID = getIntent().getExtras().getString("imdbID");
        movieDao.allDetail(ApiUtils.API_KEY, imdbID).enqueue(new Callback<DetailModel>() {
            @Override
            public void onResponse(Call<DetailModel> call, Response<DetailModel> response) {

                String responseCase = response.body().getResponse();


                if (responseCase.equals("False")) {

                    Toast.makeText(getApplicationContext(),R.string.errorMessage,Toast.LENGTH_LONG).show();

                } else {

                    Picasso.get().load(response.body().getPoster()).fit().into(detailBinding.posterDetailImageView);
                    detailBinding.detailTitleTextView.setText(response.body().getTitle());
                    detailBinding.detailReleasedTextView.setText(response.body().getReleased());
                    detailBinding.detailGenreTextView.setText(response.body().getGenre());
                    detailBinding.detailRatingTextView.setText("IMDB : "+response.body().getImdbRating());
                    detailBinding.detailActorTextView.setText(response.body().getActors());
                    detailBinding.detailCountryTextView.setText(response.body().getCountry());
                    detailBinding.detailRunTimeTextView.setText(response.body().getRuntime());
                    detailBinding.detailPlotTextView.setText(response.body().getPlot());

                }
            }

            @Override
            public void onFailure(Call<DetailModel> call, Throwable t) {

            }
        });
    }

    public void setImageButton(){

        onBackPressed();

    }
}