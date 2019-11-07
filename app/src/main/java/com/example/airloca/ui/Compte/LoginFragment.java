package com.example.airloca.ui.Compte;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.airloca.Entities.Personne;
import com.example.airloca.R;
import com.example.airloca.ServiceWebAsync;
import com.example.airloca.Session;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment implements ServiceWebAsync.OnFragmentInteractionListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();


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

        View view = inflater.inflate(R.layout.fragment_login, container, false);


        final  EditText txtLogin = view.findViewById(R.id.txtLogin);
        final EditText txtPassword = view.findViewById(R.id.txtPassword);
        final ImageView logo = view.findViewById(R.id.logo);
        final TextView txtTitre = view.findViewById(R.id.txtTitre);


        Button btnLogin = view.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = txtLogin.getText().toString();
                String password = txtPassword.getText().toString();

                if(!login.isEmpty() && !password.isEmpty()) {

                    String url = "http://172.16.64.12/airloca/loginpersonne.php";

                    HashMap<String,String> params = new HashMap<>();

                    params.put("login",login);
                    params.put("password",password);

                    ServiceWebAsync serviceWebAsync = new ServiceWebAsync(url,null,getContext());
                    serviceWebAsync.execute();


                }else{

                    Toast.makeText(getContext(),"Veuillez saisir vos informations", Toast.LENGTH_LONG).show();
                }

            }
        });


        return view;
    }


    @Override
    public void onFragmentInteraction(String value) {

        String rr = value;
        Toast.makeText(getContext(),"value=" + value, Toast.LENGTH_LONG).show());

    }
}
