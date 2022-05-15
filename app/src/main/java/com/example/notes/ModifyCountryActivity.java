package com.example.notes;

import androidx.annotation.IntRange;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModifyCountryActivity extends Activity implements View.OnClickListener {

    private EditText TitleText ;
    private EditText Desc ;
    private Button btnUpdate , btnDelet ;
    private  long _id ;
    private  DBManager dbManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Modify Record");
        setContentView(R.layout.activity_modify_country);

        dbManager = new DBManager( this );
        dbManager.open() ;

        TitleText = findViewById( R.id.Subject_EditText ) ;
        Desc = findViewById( R.id.DescriptionEditText ) ;
        btnUpdate = findViewById(R.id.btn_Update) ;
        btnDelet = findViewById( R.id.btn_Update ) ;

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

    @Override
    public void onClick(View view) {
        switch (view.getId() ){
            case R.id.btn_Update:
                String title = TitleText.getText().toString() ;
                String desc = Desc.getText().toString() ;
                dbManager.update( _id , title , desc );
                this.returnHome() ;
                break;

            case R.id.btn_delet:
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