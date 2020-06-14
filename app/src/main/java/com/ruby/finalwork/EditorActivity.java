package com.ruby.finalwork;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditorActivity extends AppCompatActivity {

Button save,cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        save=findViewById(R.id.btn_save);
        cancel=findViewById(R.id.btn_cancel);

    }

    public void OpenEdit(View btn) {
        if (btn == save) {
            EditText title=(EditText)findViewById(R.id.et_title);
            EditText context=(EditText)findViewById(R.id.et_content);
            String s1=title.getText().toString();
            String s2=context.getText().toString();
            NoteManager manager =new NoteManager(this);
            manager.add(new NoteItem(s1,s2));
            Toast.makeText(getApplicationContext(),"保存成功",Toast.LENGTH_LONG).show();
        }else if (btn==cancel){
            Intent today = new Intent(this, RecordActivity.class);
            startActivity(today);
        }
    }
}
