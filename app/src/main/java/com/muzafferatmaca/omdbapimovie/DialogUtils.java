package com.muzafferatmaca.omdbapimovie;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.muzafferatmaca.omdbapimovie.activities.DetailActivity;
import com.squareup.picasso.Picasso;


/**
 * Created by Muzaffer Atmaca on 19.05.2022.
 */
public class DialogUtils {

    public static ProgressDialog createProgressDialog(Context pContext) {

        ProgressDialog dialog = new ProgressDialog(pContext);
        try {
            dialog.show();
        } catch (WindowManager.BadTokenException e) {

        }
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_layout);
        return dialog;
    }

    public static AlertDialog createAlertDialog(Context aContext, String title, String type, String year, String poster,String imdbID) {

        TextView alertTitleTextview;
        TextView alertYearTextView;
        TextView alertTypeTextView;
        ImageView alertPosterImageView;
        Button detailsButton;

        View view = LayoutInflater.from(aContext).inflate(R.layout.alert_dialog_layout, null);

        alertTitleTextview = view.findViewById(R.id.alertTitleTextView);
        alertYearTextView = view.findViewById(R.id.alertYearTextView);
        alertTypeTextView = view.findViewById(R.id.alertTypeTextView);
        alertPosterImageView = view.findViewById(R.id.alertPosterImageView);
        detailsButton = view.findViewById(R.id.detailsButton);

        AlertDialog.Builder alert = new AlertDialog.Builder(aContext);
        alert.setView(view);
        alert.setCancelable(true);
        AlertDialog alertDialog = alert.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

        alertTitleTextview.setText(title);
        alertYearTextView.setText(year);
        alertTypeTextView.setText(type);
        Picasso.get().load(poster).fit().centerCrop().into(alertPosterImageView);
        detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(aContext, DetailActivity.class);
                intent.putExtra("imdbID",imdbID);
                alertDialog.dismiss();
                aContext.startActivity(intent);
            }
        });

        return alertDialog;
    }
}
