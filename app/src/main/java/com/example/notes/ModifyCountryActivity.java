package com.example.notes;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;


public class ModifyCountryActivity extends Activity implements View.OnClickListener {

    private EditText TitleText ;
    private EditText Desc ;
    private Button btnUpdate , btnDelet ;
    private  long _id ;
    private  DBManager dbManager ;

    Preference col = new Preference() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Modify Record");

        dbManager = new DBManager( this );
        dbManager.open() ;

        Preference.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        if( Preference.sharedPreferences.getBoolean("theme_preference", false) == true ){

            setContentView(R.layout.activity_modify_country2);
            TitleText = findViewById( R.id.Subject_EditText2 ) ;
            Desc = findViewById( R.id.DescriptionEditText2 ) ;
            btnUpdate = findViewById(R.id.btn_Update2) ;
            btnDelet = findViewById( R.id.btn_delet2 ) ;
        }else{
            setContentView(R.layout.activity_modify_country);
            TitleText = findViewById( R.id.Subject_EditText ) ;
            Desc = findViewById( R.id.DescriptionEditText ) ;
            btnUpdate = findViewById(R.id.btn_Update) ;
            btnDelet = findViewById( R.id.btn_delet ) ;

        }


        Intent intent = getIntent() ;
        String id = intent.getStringExtra("id") ;
        String name = intent.getStringExtra( "title" ) ;
        String desc = intent.getStringExtra( "desc" ) ;
        _id = Long.parseLong( id ) ;
        TitleText.setText( name );
        Desc.setText( desc );
        btnUpdate.setOnClickListener( this );
        btnDelet.setOnClickListener( this );
    }

    String title , desc ;

    @Override
    public void onClick(View view) {
        switch (view.getId() ){
            case R.id.btn_Update:
                 title = TitleText.getText().toString() ;
                 desc = Desc.getText().toString() ;
                dbManager.update( _id , title , desc );
                this.returnHome() ;
                break;

            case R.id.btn_Update2:
                 title = TitleText.getText().toString() ;
                 desc = Desc.getText().toString() ;
                dbManager.update( _id , title , desc );
                this.returnHome() ;
                break;


            case R.id.btn_delet:
                dbManager.delet( _id );
                this.returnHome() ;
                break;

            case R.id.btn_delet2:
                dbManager.delet( _id );
                this.returnHome() ;
                break;
        }
    }

    public void returnHome(  ){
        Intent home_intent = new Intent( getApplicationContext() ,
                CountryListActivity.class).setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP ) ;

        startActivity( home_intent );
    }


}