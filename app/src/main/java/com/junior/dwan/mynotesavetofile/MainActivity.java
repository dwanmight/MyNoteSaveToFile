package com.junior.dwan.mynotesavetofile;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String FILENAME = "Note.txt";
    EditText etNote;
    Button btnSave, btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNote = (EditText) findViewById(R.id.etNote);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnLoad = (Button) findViewById(R.id.btnLoad);

        btnSave.setOnClickListener(this);
        btnLoad.setOnClickListener(this);

    }

    private void saveToFile() {
        String textNote = etNote.getText().toString();

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = this.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fileOutputStream.write(textNote.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void loadFromFile() {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = this.openFileInput(FILENAME);
            int available = fileInputStream.available();
            byte[] buffer = new byte[available];
            fileInputStream.read(buffer);
            String textNote = new String(buffer);

            etNote.setText(textNote);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                saveToFile();
                break;
            case R.id.btnLoad:
                loadFromFile();
                break;
        }
    }
}
