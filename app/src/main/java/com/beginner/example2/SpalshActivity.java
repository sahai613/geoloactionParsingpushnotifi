package com.beginner.example2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.parse.Parse;
import com.parse.ParseInstallation;

public class SpalshActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        Parse.initialize(new Parse.Configuration.Builder(this).applicationId("5nLj3KQdAanexDS8v6qxLLucQmSq6ZjgLwDnBm3p").clientKey("p1RoEPpRUqUHeI3OyPFAkW2gPoKcZedvNkxrnlNw").server("http://parseapi.back4app.com/").build());
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("5nLj3KQdAanexDS8v6qxLLucQmSq6ZjgLwDnBm3p")
                .clientKey("p1RoEPpRUqUHeI3OyPFAkW2gPoKcZedvNkxrnlNw")
                .server("https://parseapi.back4app.com/").build()
        );


        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.put("GCMSenderId", "964946825678");
        installation.saveInBackground();

//       Runnable runnable3sec=new Runnable() {
//            @Override
//           public void run() {
//               nextActivity();
//               finish();
//
//           }
//       };
//        Handler myHandler=new Handler();
//         myHandler.postDelayed(runnable3sec,3000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                nextActivity();
                finish();
            }
        },3000);

          }
    public void nextActivity(){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }


}
