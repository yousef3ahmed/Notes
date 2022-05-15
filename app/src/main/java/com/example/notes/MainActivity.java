package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username , password , repassword ;
    Button signup , signin;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init ;
        username = findViewById(R.id.username) ;
        password = findViewById(R.id.password) ;
        repassword = findViewById( R.id.repassword ) ;
        signin = findViewById( R.id.signin ) ;
        signup = findViewById( R.id.sginup );
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString() ;
                String pass = password.getText().toString() ;
                String repass = repassword.getText().toString() ;

                if(TextUtils.isEmpty( user ) || TextUtils.isEmpty( pass ) || TextUtils.isEmpty( repass )  )
                    Toast.makeText(MainActivity.this , "All fields Required" , Toast.LENGTH_SHORT).show();
                else{
                    if( pass.equals(repass) ) {
                        boolean chk_user = DB.checkusername( user ) ;
                        if( chk_user == false ){
                            Boolean insert = DB.insert( user , pass ) ;
                            if( insert == true ){
                                Toast.makeText(MainActivity.this , "insert Done" , Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext() , CountryListActivity.class) ;
                                startActivity(intent);
                            }else
                                Toast.makeText(MainActivity.this , "insert failed" , Toast.LENGTH_SHORT).show();
                        }else
                            Toast.makeText(MainActivity.this , "User already exists" , Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(MainActivity.this , "passwords not matching" , Toast.LENGTH_SHORT).show();
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext() , LoginActivity.class) ;
                startActivity( intent );
            }
        });

    }
}