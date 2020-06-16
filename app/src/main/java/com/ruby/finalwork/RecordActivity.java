package com.ruby.finalwork;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.UserHandle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {

    Button add;
    Handler handler;
    private List<HashMap<String,String>> noteList;
    private ListAdapter listItemAdapter;
    ListView  lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        add = findViewById(R.id.btn_add);
        initListView();
        lv=(ListView) findViewById(R.id.lv_note);
        listItemAdapter =new SimpleAdapter(RecordActivity.this,noteList, R.layout.notelist,
                new String[]{"Title","Context"},
                new int[] { R.id.notetitle, R.id.notecontext})
        ;
        lv.setAdapter(listItemAdapter);

        lv.setOnItemLongClickListener(this);
        lv.setOnItemClickListener(this);
    }


    public void OpenAdd(View btn) {
        if (btn == add) {
            Intent today = new Intent(this, EditorActivity.class);
            startActivity(today);
        }

    }

    private void initListView() {
       // noteList = new ArrayList<HashMap<String,String>>();
        noteList =new ArrayList<HashMap<String, String>>();
        NoteManager manager = new NoteManager(this);
        for (NoteItem item : manager.listAll()) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("Title",  item.getCurName()); // 标题文字
            map.put("Context", item.getCurRate()); // 详情描述
            noteList.add(map);
     }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("提示").setMessage("请确认是否删除该记录").setPositiveButton("确认", null).setNegativeButton("取消",null);
        builder.create().show();
        return true;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HashMap<String,String> map=(HashMap<String, String>)parent.getItemAtPosition(position);
        String title=map.get("Title");
        String context=map.get("Context");

        Intent rateCalc = new Intent(this,ShowActivity.class);
        rateCalc.putExtra("record",title);
        rateCalc.putExtra("context",context);
        startActivity(rateCalc);


    }
}