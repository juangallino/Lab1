package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.model.CuentaBancaria;
import com.example.myapplication.model.Tarjeta;
import com.example.myapplication.model.Usuario;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {



    EditText ccv, mail,numeroTarjeta,pass2,pass1, nombre,cbu, alias;
    Button registrarBtn;
    Switch cargaInicial;
    EditText mestarjeta, añoTarjeta;
    SeekBar sbMonto;
    CheckBox tyCond;
    RadioGroup rg;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pass1 = findViewById(R.id.contraseñalbl);
        pass2 = findViewById(R.id.repetirContraseñalbl);
        pass2.setText(pass1.getText().toString());
        numeroTarjeta = findViewById(R.id.numTarjetalbl);
        ccv = findViewById(R.id.codSegTarjetalbl);
        cargaInicial = findViewById(R.id.switchCargaInicial);
        mestarjeta =findViewById(R.id.etMesTarjeta);
        añoTarjeta =findViewById(R.id.etAñoTarjeta);
         sbMonto = findViewById(R.id.seekBarCargaInicial);
         tyCond= findViewById(R.id.cbTerminoYCond);
          cargaInicial = findViewById(R.id.switchCargaInicial);
          mail= findViewById(R.id.emaillbl);
          registrarBtn =findViewById(R.id.btnRegistrar);
          rg= findViewById(R.id.rGrpTarjetas);
          nombre= findViewById(R.id.nombreUsrlbl);
          cbu = findViewById(R.id.cbuUsrlbl);
          alias= findViewById(R.id.aliasUsrlbl);




//cuando el numero de tarjeta es de 16 digitos se habilitan los campos de cvv y fecha de vencimientod de la tarjeta
        numeroTarjeta.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                numeroTarjeta = findViewById(R.id.numTarjetalbl);
                ccv = findViewById(R.id.codSegTarjetalbl);
                if (editable.length() == 16) {
                    mestarjeta.setEnabled(true);
                    añoTarjeta.setEnabled(true);

                    ccv.setEnabled(true);
                    Toast.makeText(getApplicationContext(), "atr esa tarjeta bro", Toast.LENGTH_SHORT).show();

                } else {
                    ccv.setText("");
                    ccv.setEnabled(false);
                    mestarjeta.setEnabled(false);
                    añoTarjeta.setEnabled(false);


                }


            }
        });



        //Sellecionador tipo tarjeta habilita la entrada de la tarjeta

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                numeroTarjeta.setEnabled(true);
            }
        });


        //Cuando el Switch de carga inicial esta en true la seeker bar se activa
        cargaInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TextView tvCarga = findViewById(R.id.cargainciallbl);


                if (cargaInicial.isChecked()) {

                     tvCarga.setVisibility(View.VISIBLE);
                    sbMonto.setVisibility(View.VISIBLE);
                } else {
                     tvCarga.setVisibility(View.GONE);
                    sbMonto.setVisibility(View.GONE);
                }


            }
        });




        //Las contraseñas deberian chequearse mientras se estan ingresando
        pass2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void afterTextChanged(Editable editable) {
                if(pass1.getText().toString().equals(editable.toString())){
                    pass1.setBackgroundColor(Color.GREEN);
                    Toast.makeText(MainActivity.this, "pass iguales", Toast.LENGTH_SHORT).show();
                }
                else{
                    pass1.setBackgroundColor(Color.RED);
                   // Toast.makeText(MainActivity.this, "pass distinas", Toast.LENGTH_SHORT).show();

                }

            }
        });

        //cuando se mueve la seekbar se muestra el monto que se está seleccionando
        sbMonto.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int seekBarValue= 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValue= progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "Monto= "+seekBarValue, Toast.LENGTH_SHORT).show();
            }
        });


        //cuando se checkean los terminos y condiciones se habilita el boton de registrar
            tyCond.setOnClickListener(new View.OnClickListener() {
                Button registrar = findViewById(R.id.btnRegistrar);
                @Override
                public void onClick(View v) {
                    if(tyCond.isChecked()){
                        registrar.setEnabled(true);
                    }else{
                        registrar.setEnabled(false);
                    }

                }
            });





            registrarBtn.setOnClickListener(new View.OnClickListener() {
               // @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {

                    //Debemos checkear valores, definimos y seteamos en boleanos en false

                    Boolean notnullCheck=false;
                    Boolean contraseñaCheck=false;
                    Boolean emailCheck=false;
                    Boolean tarjetaFechaCheck=false;
                    Boolean  cargaInicialCheck=false;

                //Valido contraseñas iguales
                    if(!pass1.getText().toString().isEmpty()){
                        if(pass1.getText().toString().equals(pass2.getText().toString())){
                           // pass1.setBackgroundColor(R.color.okPass);
                                contraseñaCheck=true;
                                notnullCheck=true;
                                registrarBtn.setEnabled(true);
                           // Toast.makeText(MainActivity.this, "Contraseñas ok", Toast.LENGTH_SHORT).show();

                        }
                        else{
                            contraseñaCheck=false;
                            Toast.makeText(MainActivity.this, "Contraseñas distintas", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                            notnullCheck=false;
                            Toast.makeText(MainActivity.this, "Escriba una contraseña", Toast.LENGTH_SHORT).show();
                    }





                 //Valido carga inicial != 0
                    if(cargaInicial.isChecked()){
                        if(sbMonto.getProgress()>0){
                            cargaInicialCheck=true;
                           // Toast.makeText(MainActivity.this, "Valor a cargar aceptado", Toast.LENGTH_SHORT).show();
                        }else{      cargaInicialCheck=false;
                                    notnullCheck=false;
                                    Toast.makeText(MainActivity.this, "Monto a cargar = 0. Seleccione un valor", Toast.LENGTH_SHORT).show();}
                    }else{cargaInicialCheck=true; notnullCheck=true;}




                    //Validar correo
                   if(!mail.getText().toString().isEmpty()) {
                       if (android.util.Patterns.EMAIL_ADDRESS.matcher(mail.getText().toString()).matches()) {
                           emailCheck = true; notnullCheck=true;
                       } else {
                           emailCheck = false;
                           Toast.makeText(MainActivity.this, "Formato email incorrecto", Toast.LENGTH_SHORT).show();
                       }
                   }else {notnullCheck=false;}



                    //Validar fecha tarjeta de credio TODO

                    if(!mestarjeta.getText().toString().isEmpty() && !añoTarjeta.getText().toString().isEmpty()){
                        DateFormat formato = new SimpleDateFormat("MM-yy");
                        String fechaIngresadaStr = mestarjeta.getText().toString()+"-"+añoTarjeta.getText().toString();
                        Date fechaIngresada = null;

                        try {
                            fechaIngresada = formato.parse(fechaIngresadaStr);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }


                        Calendar today = Calendar.getInstance();
                        Date fechaActual = new Date();
                        today.setTime(fechaActual);
                        today.add(Calendar.MONTH, 3);

                        Calendar fechaTarjeta = Calendar.getInstance();
                        fechaTarjeta.setTime(fechaIngresada);

                        if (fechaTarjeta.before(today)){
                            Toast.makeText(MainActivity.this, "El Vencimiento de la Tarjeta debe ser mayor a 3 meses", Toast.LENGTH_SHORT).show();
                            tarjetaFechaCheck = false;
                        } else {
                            tarjetaFechaCheck=true; notnullCheck=true;
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Falta Completar Fecha de Vencimiento", Toast.LENGTH_SHORT).show();
                        notnullCheck = false;
                    }




                //chequear todos los boolean



                  if(notnullCheck && contraseñaCheck && emailCheck && tarjetaFechaCheck && cargaInicialCheck) {
                      Toast.makeText(MainActivity.this, "PASO TODAS LAS VALIDACIONES", Toast.LENGTH_LONG).show();

                      //cargamos datos en entidades


                      //CARGAR CUENTA BANCARIA
                      CuentaBancaria cuentaBancaria = new CuentaBancaria();
                      cuentaBancaria.setAlias(alias.getText().toString());
                      cuentaBancaria.setCbu(cbu.getText().toString());


                      //CARGAR TARJETA
                      Tarjeta tarjeta = new Tarjeta();
                      tarjeta.setNumero(numeroTarjeta.getText().toString());
                      tarjeta.setCcv(ccv.getText().toString());
                      RadioButton rd = findViewById(rg.getCheckedRadioButtonId());
                      tarjeta.setEsCredito(rd.getText().toString().equals("Credito"));


                      //CARGAR USER
                      Usuario usr = new Usuario();
                      usr.setNombre(nombre.getText().toString());
                      usr.setClave(pass2.getText().toString());
                      usr.setCredito((double) sbMonto.getProgress());
                      usr.setEmail(mail.getText().toString());
                      usr.setCuentaBancaria(cuentaBancaria);
                      usr.setTarjeta(tarjeta);


                      System.out.println(tarjeta.getNumero() + " es credito" + tarjeta.isEsCredito() + "  ---- " + tarjeta);
                      System.out.println(usr.getCredito() + " --- nombre  ...+" + usr.getNombre() + "  ---  email " + usr.getEmail() + "   " + usr);
                  }
                }
         });




        }


    }






