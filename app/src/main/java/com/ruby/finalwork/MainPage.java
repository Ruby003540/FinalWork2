package com.ruby.finalwork;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainPage extends AppCompatActivity {
   Button taday,yesterday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        taday=(Button)findViewById(R.id.taday);
        yesterday=(Button)findViewById(R.id.yestoday);

    }
    public void Open(View btn) {
        if (btn == taday) {
            Intent today = new Intent(this, MainActivity.class);
            startActivity(today);
        }else if(btn==yesterday){
            Intent today = new Intent(this, RecordActivity.class);
            startActivity(today);
        }
    }
}
