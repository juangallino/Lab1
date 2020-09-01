package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    EditText pass1;
    EditText pass2;
    Button registrarBtn;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pass1= findViewById(R.id.contraseñalbl);
        pass2 =findViewById(R.id.repetirContraseñalbl);
        pass2.setText(pass1.getText().toString());
      //  pass2.setVisibility(View.GONE);
      //  registrarBtn.setVisibility(View.GONE);
        if (pass1.getText().toString()!=null){
            pass2.setVisibility(View.VISIBLE);
        }

    }



}