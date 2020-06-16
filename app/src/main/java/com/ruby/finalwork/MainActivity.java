package com.ruby.finalwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ListActivity implements  Runnable{

    private String TAG = "Oncreate";
    String title[]={"《远和近》","《偶然》","《错误》","《小巷》"};
    String author[]={"顾城","徐志摩","郑愁予","顾城"};
    String detail[]={"你，\n" +
            "　　一会看我，\n" +
            "　　一会看云。\n" +
            "　　我觉得，\n" +
            "　　你看我时很远，\n" +
            "　　你看云时很近。","我是天空里的一片云，\n" +
            "　　偶然投影在你的波心\n" +
            "　　你不必讶异，\n" +
            "　　更无须欢喜，\n" +
            "　　在转瞬间消灭了踪影。\n" +
            "　　你我相逢在黑夜的海上，\n" +
            "　　你有你的，我有我的，方向；\n" +
            "　　你记得也好，\n" +
            "　　最好你忘掉，\n" +
            "　　在这交会时互放的光亮！\n","" +
            "我打江南走过\n" +
            "　　那等在季节里的容颜如莲花的开落\n" +
            "　　东风不来，\n" +
            "　　三月的柳絮不飞\n" +
            "　　你的心如小小的寂寞的城\n" +
            "　　恰若青石的街道向晚\n" +
            "　　跫音不响，\n" +
            "　　三月的春帏不揭\n" +
            "　　你的心是小小的窗扉紧掩\n" +
            "　　我达达的马蹄是美丽的错误\n" +
            "　　我不是归人，\n" +
            "　　是过客","小巷\n" +
            "　　又弯又长\n" +
            "　　没有门\n" +
            "　　没有窗\n" +
            "　　我拿把旧钥匙\n" +
            "　　敲着厚厚的墙"};

    Handler handler;
    private ArrayList<HashMap<String, String>> listItems;
    private SimpleAdapter listItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        initListView();
        this.setListAdapter(listItemAdapter);

        Thread t = new Thread(this);
        t.start();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == 5){
                    List<HashMap<String, String>> poemList = (List<HashMap<String, String>>) msg.obj;
                    SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, poemList, // listItems数据源
                            R.layout.listview, // ListItem的XML布局实现
                            new String[] { "Title", "Author" ,"Detail"},
                            new int[] { R.id.title, R.id.author,R.id.detail });
                    setListAdapter(adapter);
                    Log.i("handler","reset list...");
                }
                super.handleMessage(msg);
            }

        };


    }   @Override
    public void run() {
        Log.i("thread","run.....");
        boolean marker = false;
        List<HashMap<String, String>> poemList = new ArrayList<HashMap<String, String>>();
//
//        Bundle bundle = new Bundle();
//        File input = new File("C:/Users/Dell/Desktop");
//        try {
//            Document doc = Jsoup.parse(input, "UTF-8", "http://www.oschina.net/");
//            Elements links = doc.getElementsByTag("p");
//           // StringBuffer buffer = new StringBuffer();
//            for (Element link : links) {
//               String  head = link.getElementsByTag("h1").toString();
//               String author = link.getElementsByTag("h2").toString();
//               String detail = link.getElementsByTag("br").toString();
//
//                HashMap<String, String> map = new HashMap<String, String>();
//                map.put("Title", head);
//                map.put("Author", author);
//                map.put("Detail", detail);
//
//                poemList.add(map);
//                Log.i("td",head + "=>" + author);
//
//                //  buffer.append(link.text().trim());
//            }
//           marker=true;
//        } catch (MalformedURLException e) {
//            Log.e("www", e.toString());
//            e.printStackTrace();
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Message msg = handler.obtainMessage();
//        msg.what = 5;
//        if(marker){
//            msg.arg1 = 1;
//        }else{
//            msg.arg1 = 0;
//        }
//
//        msg.obj = poemList;
//        handler.sendMessage(msg);
//
//        Log.i("thread","sendMessage.....");
    }

    private void initListView() {
        listItems = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < 4; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("Title",  title[i]); // 标题文字
            map.put("Author", author[i]); // 详情描述
            map.put("Detail", detail[i]);
            listItems.add(map);
        }
        // 生成适配器的Item和动态数组对应的元素
        listItemAdapter = new SimpleAdapter(this, listItems, // listItems数据源
                R.layout.listview, // ListItem的XML布局实现
                new String[] { "Title", "Author" ,"Detail"},
                new int[] { R.id.title, R.id.author,R.id.detail }
        );
    }
}
