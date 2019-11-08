package com.example.airloca.ui.share;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.airloca.Entities.Logement;
import com.example.airloca.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class ShareFragment extends Fragment {

    private ShareViewModel shareViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(ShareViewModel.class);

        String logementJson = getArguments().getString("logement");

        Gson gson = new Gson();
        Logement logement = gson.fromJson(logementJson,Logement.class);

        View view = inflater.inflate(R.layout.fragment_share, container, false);

        TextView txtTitre = view.findViewById(R.id.txtTitreDetail);

        TextView txtDescriptif = view.findViewById(R.id.txtDescriptif);
        TextView txtAdresse = view.findViewById(R.id.txtAdresse);
        TextView txtCodePostal = view.findViewById(R.id.txtCodePostal);
        TextView txtVille = view.findViewById(R.id.txtVille);




        txtTitre.setText(logement.getTitre());
        txtAdresse.setText(logement.getAdresse());
        txtCodePostal.setText(logement.getCodePostal());
        txtVille.setText(logement.getVille());


        ImageView imgPhotoDetail = view.findViewById(R.id.imgPhotoDetail);

        String url = "http://172.16.64.12/airloca/photos/"+ logement.getPhoto();
        Picasso.with(getContext())
                .load(url)
                .into(imgPhotoDetail);


        return view;
    }
}