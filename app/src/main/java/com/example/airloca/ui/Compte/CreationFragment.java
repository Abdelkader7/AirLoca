package com.example.airloca.ui.Compte;


import android.app.DownloadManager;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.airloca.R;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreationFragment extends Fragment {
        ProgressBar progressBar;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public CreationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreationFragment newInstance(String param1, String param2) {
        CreationFragment fragment = new CreationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_creation, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        final EditText txtNom = view.findViewById(R.id.txtNom);
        final   EditText txtPrenom = view.findViewById(R.id.txtPrenom);
        final  EditText txtLogin = view.findViewById(R.id.txtLogin);
        final  EditText txtMobile = view.findViewById(R.id.txtMobile);
        final EditText txtPassword = view.findViewById(R.id.txtPassword);
        final  EditText txtEmail = view.findViewById(R.id.txtEmail);


        Button btnValider = view.findViewById(R.id.btnValider);

        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = txtNom.getText().toString();
                String prenom = txtPrenom.getText().toString();
                String login = txtLogin.getText().toString();
                String password = txtPassword.getText().toString();
                String email = txtEmail.getText().toString();
                String mobile = txtMobile.getText().toString();

                if(!nom.isEmpty() && !prenom.isEmpty() && !login.isEmpty() && !password.isEmpty() && !mobile.isEmpty() && !email.isEmpty()) {

                    String url = "http://172.16.64.12/airloca/insertpersonne.php";
                    OkHttpAsync okHttpAsync = new OkHttpAsync();
                    okHttpAsync.execute(url,nom,prenom,login,password,email,mobile);

                }else{

                    Toast.makeText(getContext(),"Veuillez saisir vos informations", Toast.LENGTH_LONG).show();
                }

            }
        });


        return view;
    }


    public class OkHttpAsync extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected String doInBackground(String... params) {

            OkHttpClient client = new OkHttpClient();

            String nom = params[1];
            String prenom = params[2];
            String login = params[3];
            String password = params[4];
            String email = params[5];
            String mobile = params[6];


            RequestBody body = new FormEncodingBuilder()
                    .add("nom",nom)
                    .add("prenom",prenom)
                    .add("login",login)
                    .add("password",password)
                    .add("email",email)
                    .add("mobile",mobile)
                    .build();

            Request request = new Request.Builder()
                    .post(body)
                    .url(params[0])
                    .build();
            try {
                Response response = client.newCall(request ).execute();
                return response.body().string();
            } catch (IOException e) {
                String s = e.getMessage();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s ) {
            //Handle result here
            super.onPostExecute(s);
            progressBar.setVisibility(View.INVISIBLE);



        }
    }

}
