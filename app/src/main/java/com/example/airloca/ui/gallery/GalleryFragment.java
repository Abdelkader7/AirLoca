package com.example.airloca.ui.gallery;

import android.app.Service;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.airloca.Entities.Logement;
import com.example.airloca.Entities.Logements;
import com.example.airloca.R;
import com.example.airloca.ServiceWebAsync;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GalleryFragment extends Fragment implements ServiceWebAsync.OnFragmentInteractionListener{

    private GalleryViewModel galleryViewModel;

    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        String url = "http://172.16.64.12/airloca/loadlogements.php";
        ServiceWebAsync serviceWebAsync = new ServiceWebAsync(url,null,getContext(),GalleryFragment.this);
        serviceWebAsync.execute();



       /* galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);*/
        Logements logementList = null;

        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        recyclerView = view.findViewById(R.id.rcwLogements);


        return view;

    }

    @Override
    public void returnSw(String value) {
        if(!value.isEmpty()){
            try{
                Gson gson = new Gson();
                Logements logements = gson.fromJson(value,Logements.class);

                RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(logements);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(recyclerViewAdapter);

            }catch (Exception ex){

            }

        }
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

        private ArrayList<Logement> _logementList;

        public RecyclerViewAdapter(ArrayList<Logement> logementList) {
            this._logementList = logementList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_logement, parent, false);

            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            try
            {
                final Logement logement = this._logementList.get(position);
                holder.txtTitre.setText(logement.getTitre());
                holder.txtDescriptif.setText(logement.getDescriptif());
                holder.txtVille.setText(logement.getVille());

                String url = "http://172.16.64.12/airloca/photos/"+logement.getPhoto();
                Picasso.with(getContext())
                        .load(url)
                        .into(holder.imgPhoto);
                holder.btnDetail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        NavController navController= Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
                        Bundle bundle = new Bundle();
                        Gson gson = new Gson();
                        String logementJson = gson.toJson(logement);
                        bundle.putString("logement",logementJson);
                        navController.navigate(R.id.nav_share,bundle);

                    }
                });
            }
            catch(Exception ex)
            {
                Log.e("Erreur",ex.getMessage());
            }

        }

        @Override
        public int getItemCount() {
            return this._logementList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView txtTitre;
            public TextView txtDescriptif;
            public TextView txtVille;
            public ImageView imgPhoto;

            public Button btnDetail;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                txtTitre = itemView.findViewById(R.id.txtTitre);
                txtDescriptif = itemView.findViewById(R.id.txtDescriptif);
                txtVille = itemView.findViewById(R.id.txtVille);
                imgPhoto = itemView.findViewById(R.id.imgPhoto);

                btnDetail = itemView.findViewById(R.id.btnDetail);


            }
        }
    }

    }
