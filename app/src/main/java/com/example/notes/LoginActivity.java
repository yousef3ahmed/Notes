package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public  static  int idd ;
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

                         idd = DB.GetId( user ) ;
                        Toast.makeText(LoginActivity.this , "User id" + idd , Toast.LENGTH_SHORT).show();
                        Toast.makeText(LoginActivity.this , "Login Done" , Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext() , CountryListActivity.class) ;
                        startActivity( intent );
                    }else
                        Toast.makeText(LoginActivity.this , "enter correct password and username" , Toast.LENGTH_SHORT).show();

                }


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.my_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.settings:
                Intent i = new Intent(this, Preference.class);
                startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}