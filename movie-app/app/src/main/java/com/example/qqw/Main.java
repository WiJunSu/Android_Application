package com.example.qqw;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main extends AppCompatActivity {

    private ListView listView;
    private NoticeListAdapter adapter;
    private List<Notice> noticeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        listView = (ListView) findViewById(R.id.listView);
        noticeList = new ArrayList<Notice>();
        adapter = new NoticeListAdapter(getApplicationContext(),noticeList);
        listView.setAdapter(adapter);


        final Button action = (Button) findViewById(R.id.action);
        final Button romance = (Button) findViewById(R.id.romance);
        final Button sf = (Button) findViewById(R.id.sf);
        final Button horror = (Button) findViewById(R.id.horror);
        final Button notify = (Button) findViewById(R.id.notify);
        final LinearLayout notice = (LinearLayout) findViewById(R.id.notice);


        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notice.setVisibility(View.GONE);
                action.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                romance.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                sf.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                horror.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                notify.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new Action());
                fragmentTransaction.commit();
            }
        });

        romance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notice.setVisibility(View.GONE);
                action.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                romance.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                sf.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));;
                notify.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new Romance());
                fragmentTransaction.commit();
            }
        });

        sf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notice.setVisibility(View.GONE);
                action.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                romance.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                sf.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                horror.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                notify.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new SF());
                fragmentTransaction.commit();
            }
        });

        horror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notice.setVisibility(View.GONE);
                action.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                romance.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                sf.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                horror.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                notify.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new Horror());
                fragmentTransaction.commit();
            }
        });

        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notice.setVisibility(View.GONE);
                action.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                romance.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                sf.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                horror.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                notify.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new Notify());
                fragmentTransaction.commit();
            }
        });

        new BackgroundTask().execute();
    }

    class BackgroundTask extends AsyncTask<Void, Void, String>
    {
        String target;

        @Override
        protected void onPreExecute() {
            target = "http://bbagazy.cafe24.com/NoticeList.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
           try{
               URL url = new URL(target);
               HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
               InputStream inputStream = httpURLConnection.getInputStream();
               BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
               String temp;
               StringBuilder stringBuilder = new StringBuilder();
               while((temp = bufferedReader.readLine()) != null)
               {
                   stringBuilder.append(temp + "\n");
               }
               bufferedReader.close();
               inputStream.close();
               httpURLConnection.disconnect();
               return stringBuilder.toString().trim();
           } catch(Exception e){
               e.printStackTrace();
           }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate();
        }

        @Override
        protected void onPostExecute(String result) {
            try{
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                String noticeContent, noticeName, noticeDate;
                while(count < jsonArray.length()){
                    JSONObject object = jsonArray.getJSONObject(count);
                    noticeContent = object.getString("noticeContent");
                    noticeName = object.getString("noticeName");
                    noticeDate = object.getString("noticeDate");
                    Notice notice = new Notice(noticeContent, noticeName, noticeDate);
                    noticeList.add(notice);
                    count++;
                }
                adapter.notifyDataSetChanged();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
