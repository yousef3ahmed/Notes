package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
        setContentView(R.layout.activity_add_country);
        subjectEditText = findViewById( R.id.subject_edittext ) ;
        DescEditText = findViewById( R.id.description_edittext ) ;
        addTodoBtn = findViewById( R.id.add_record ) ;
        dbManager = new DBManager( this ) ;
        dbManager.open() ;
        addTodoBtn.setOnClickListener( this );
    }

    @Override
    public void onClick(View view) {
        switch (view.getId() ){
            case R.id.add_record:
                final String name = subjectEditText.getText().toString();
                final  String DESC = DescEditText.getText().toString() ;
                dbManager.insert( name , DESC );
                Intent main = new Intent(AddCountryActivity.this , CountryListActivity.class).
                        setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) ;
            startActivity( main );
            break;

        }
    }
}