package com.example.airloca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.airloca.Entities.Personne;
import com.example.airloca.Utils.Functions;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AsyncSleep asyncSleep = new AsyncSleep();
        asyncSleep.execute();
    }

    public class AsyncSleep extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            try {

                String permanentPersonne = Functions.LoadSharedPreferences(MainActivity.this,Functions.LabelPermanentPersonne);

                if(!permanentPersonne.isEmpty()){
                    Gson gson = new Gson();
                    Personne personne = gson.fromJson(permanentPersonne,Personne.class);

                    Session.setPersonneConnected(personne);
                }
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s ) {
            //Handle result here
            super.onPostExecute(s);

            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(intent);

        }
    }
}
