package com.muzafferatmaca.omdbapimovie.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.muzafferatmaca.omdbapimovie.DialogUtils;
import com.muzafferatmaca.omdbapimovie.models.Search;
import com.muzafferatmaca.omdbapimovie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Muzaffer Atmaca on 18.05.2022.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    List<Search> searchModelList;
    Activity activity;
    Context context;
    AlertDialog alertDialog;

    public SearchAdapter(List<Search> searchModelList, Activity activity, Context context) {
        this.searchModelList = searchModelList;
        this.activity = activity;
        this.context = context;
    }


    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.movie_list_layout, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {

        Picasso.get().load(searchModelList.get(position).getPoster()).fit().into(holder.moviePosterImageView);

        holder.moviePosterImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog = DialogUtils.createAlertDialog(activity,
                        searchModelList.get(position).getTitle(),
                        searchModelList.get(position).getType(),
                        searchModelList.get(position).getYear(),
                        searchModelList.get(position).getPoster(),
                        searchModelList.get(position).getImdbID());

            }
        });

    }

    @Override
    public int getItemCount() {
        return searchModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView moviePosterImageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            moviePosterImageView = itemView.findViewById(R.id.moviePosterImageView);
        }
    }
}
