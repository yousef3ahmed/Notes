package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCountryActivity extends Activity implements View.OnClickListener {


    private Button addTodoBtn ;
    private EditText subjectEditText;
    private EditText DescEditText ;
    private  DBManager dbManager ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add Record");

        Preference.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if( Preference.sharedPreferences.getBoolean("theme_preference", false) == true ){
            setContentView(R.layout.activity_add_country2);
            subjectEditText = findViewById( R.id.subject_edittext2 ) ;
            DescEditText = findViewById( R.id.description_edittext2 ) ;
            addTodoBtn = findViewById( R.id.add_record2 ) ;
        }else{
            setContentView(R.layout.activity_add_country);
            subjectEditText = findViewById( R.id.subject_edittext ) ;
            DescEditText = findViewById( R.id.description_edittext ) ;
            addTodoBtn = findViewById( R.id.add_record ) ;
        }

        dbManager = new DBManager( this ) ;
        dbManager.open() ;
        addTodoBtn.setOnClickListener( this );
    }


    String name , DESC ;

    @Override
    public void onClick(View view) {
        switch (view.getId() ){
            case R.id.add_record:
                 name = subjectEditText.getText().toString();
                 DESC = DescEditText.getText().toString() ;

                dbManager.insert( name , DESC , LoginActivity.idd );
                Intent main = new Intent(AddCountryActivity.this , CountryListActivity.class).
                        setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) ;
            startActivity( main );
            break;

            case R.id.add_record2:
                 name = subjectEditText.getText().toString();
                 DESC = DescEditText.getText().toString() ;
                dbManager.insert( name , DESC , LoginActivity.idd  );
                Intent main1 = new Intent(AddCountryActivity.this , CountryListActivity.class).
                        setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) ;
                startActivity( main1 );
                break;

        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.my_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        switch (item.getItemId()) {
//            case R.id.settings:
//                Intent i = new Intent(this, Preference.class);
//                startActivity(i);
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

}