package com.ruby.finalwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ShowActivity extends AppCompatActivity {

    EditText title,context;
    Button add,cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        title = (EditText) findViewById(R.id.et_title);
        context = (EditText) findViewById(R.id.et_content);

        add=(Button)findViewById(R.id.btn_save);
        cancel=(Button)findViewById(R.id.btn_cancel);

        Intent intent =getIntent();
        String first = intent.getStringExtra("record");
        String conteixt = intent.getStringExtra("context");
        title.setText(first);
        context.setText(conteixt);

    }
     public void add(View btn){
        if(btn==add){
            if (btn == add) {
                EditText title=(EditText)findViewById(R.id.et_title);
                EditText context=(EditText)findViewById(R.id.et_content);
                String s1=title.getText().toString();
                String s2=context.getText().toString();
                if(s1.length()>0&&s2.length()>0) {
                    NoteManager manager = new NoteManager(this);
                    manager.add(new NoteItem(s1, s2));

                    Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "请输入内容", Toast.LENGTH_LONG).show();
                }
            }else if (btn==cancel){
                Intent today = new Intent(this, RecordActivity.class);
                startActivity(today);
            }
        }
     }


}
