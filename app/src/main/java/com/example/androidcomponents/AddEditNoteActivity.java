package com.example.androidcomponents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "com.example.androidcomponents.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.androidcomponents.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.example.androidcomponents.EXTRA_PRIORITY";
    public static final String EXTRA_ID = "com.example.androidcomponents.EXTRA_ID";

    private EditText editTextTitle, editTextDescription;
    private NumberPicker numberPickerPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        numberPickerPriority = findViewById(R.id.number_picker_priority);

        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Added Note");
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            numberPickerPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY , 0));
        }
        else
            setTitle("Add Note");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void saveNote() {
        String title  = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        int priority = numberPickerPriority.getValue();

        if(title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(this , "Please Insert a title" , Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();

        data.putExtra(EXTRA_TITLE , title);
        data.putExtra(EXTRA_DESCRIPTION , description);
        data.putExtra(EXTRA_PRIORITY , priority);

        int id = getIntent().getIntExtra(EXTRA_ID , -1);

        if(id!=-1){
            data.putExtra(EXTRA_ID,id);
        }
        setResult(RESULT_OK , data);
        finish();
    }
}