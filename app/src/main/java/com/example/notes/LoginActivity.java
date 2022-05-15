package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText  username = findViewById( R.id.username1 ) ;
        EditText  password = findViewById( R.id.password1 ) ;

        Button  GO = ( Button ) findViewById( R.id.sginin1 ) ;
        DBHelper DB = new DBHelper( this );

        GO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString() ;
                String pass = password.getText().toString() ;

                if(TextUtils.isEmpty( user ) || TextUtils.isEmpty( pass ))
                    Toast.makeText(LoginActivity.this , "All fields Required" , Toast.LENGTH_SHORT).show();
                else{
                    Boolean chkUserandPass = DB.checkusernameANDpassword( user , pass ) ;
                    if( chkUserandPass == true ){
                        Toast.makeText(LoginActivity.this , "Login Done" , Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext() , CountryListActivity.class) ;
                        startActivity( intent );
                    }else
                        Toast.makeText(LoginActivity.this , "enter correct password and username" , Toast.LENGTH_SHORT).show();

                }


            }
        });

    }
}