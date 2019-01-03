package com.example.qqw;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;

public class VActivity extends Activity {
    TabHost tabhost;
    ImageView iv, dir_iv, act1_iv, act2_iv, act3_iv;
    String what;
    TextView tv, story_info, dir_info, act1_info, act2_info, act3_info;
    ListView listview;
    EditText edit;
    Button review_bt;
    String movieID_num, movieTitles, movieDirectors, movieActors, movieStorys, movieKinds;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vactivity);

        iv = (ImageView) findViewById(R.id.iv);
        this.tabhost = (TabHost) findViewById(R.id.tabhost);
        tabhost.setup();

        TabHost.TabSpec ts1 = tabhost.newTabSpec("Poster");
        ts1.setContent(R.id.Poster);
        ts1.setIndicator("포스터");
        tabhost.addTab(ts1);

        TabHost.TabSpec ts2 = tabhost.newTabSpec("Time_info");
        ts2.setContent(R.id.Movie_info);
        ts2.setIndicator("영화 정보");
        tabhost.addTab(ts2);

        TabHost.TabSpec ts3 = tabhost.newTabSpec("Review");
        ts3.setContent(R.id.Review);
        ts3.setIndicator("리뷰");
        tabhost.addTab(ts3);

        final ArrayList<String> list = new ArrayList<String>();
        listview = (ListView) findViewById(R.id.listview);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        edit = (EditText) findViewById(R.id.edit);

        review_bt = (Button) findViewById(R.id.review_bt);

        review_bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                list.add(edit.getText().toString());

                // 화면에 동적할당한 리스트 목록을 띄워줌(새로고침 개념)
                adapter.notifyDataSetChanged();
            }
        });

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                list.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        TextView tv = (TextView) findViewById(R.id.tv);
        TextView tv2 = (TextView) findViewById(R.id.tv2);

        Intent intent = getIntent();
        movieID_num = intent.getStringExtra("movieID");
        id = Integer.parseInt(movieID_num);
        movieTitles = intent.getStringExtra("movieTitle");
        movieDirectors = intent.getStringExtra("movieDirector");
        movieActors = intent.getStringExtra("movieActor");
        movieStorys = intent.getStringExtra("movieStory");
        movieKinds = intent.getStringExtra("movieKind");

        dir_iv = (ImageView) findViewById(R.id.dir_iv);
        act1_iv = (ImageView) findViewById(R.id.act1_iv);
        act2_iv = (ImageView) findViewById(R.id.act2_iv);
        act3_iv = (ImageView) findViewById(R.id.act3_iv);

        dir_info = (TextView) findViewById(R.id.dir_info);
        act1_info = (TextView) findViewById(R.id.act1_info);
        act2_info = (TextView) findViewById(R.id.act2_info);
        act3_info = (TextView) findViewById(R.id.act3_info);
        story_info = (TextView) findViewById(R.id.story_info);


        if (id == 1) {
            BitmapDrawable img_movie = (BitmapDrawable) getResources()
                    .getDrawable(R.drawable.a);
            iv.setImageDrawable(img_movie);
            tv.setText(movieTitles);
            tv2.setText("장르 - " + movieKinds);
            dir_iv.setImageResource(R.drawable.a_1);
            dir_info.setText(movieDirectors);
            act1_iv.setImageResource(R.drawable.a_2);
            act2_iv.setImageResource(R.drawable.a_3);
            act3_iv.setImageResource(R.drawable.a_4);

            act1_info.setText("김혜수");
            act2_info.setText("유아인");
            act3_info.setText("허준호");
            story_info.setText(movieStorys);
        } else if (id == 2) {
            BitmapDrawable img_movie = (BitmapDrawable) getResources()
                    .getDrawable(R.drawable.b);
            iv.setImageDrawable(img_movie);
            tv.setText(movieTitles);
            tv2.setText("장르 - " + movieKinds);
            dir_iv.setImageResource(R.drawable.b_1);
            dir_info.setText(movieTitles);
            act1_iv.setImageResource(R.drawable.b_2);
            act2_iv.setImageResource(R.drawable.b_3);
            act3_iv.setImageResource(R.drawable.b_4);

            act1_info.setText("송강호");
            act2_info.setText("조정석");
            act3_info.setText("배두나");
            story_info
                    .setText(movieStorys);
        } else if (id == 3) {
            BitmapDrawable img_movie = (BitmapDrawable) getResources()
                    .getDrawable(R.drawable.c);
            iv.setImageDrawable(img_movie);
            tv.setText(movieTitles);
            tv2.setText("장르 - " + movieKinds);
            dir_iv.setImageResource(R.drawable.c_1);
            dir_info.setText("오토 바서스트");
            act1_iv.setImageResource(R.drawable.c_2);
            act2_iv.setImageResource(R.drawable.c_3);
            act3_iv.setImageResource(R.drawable.c_4);

            act1_info.setText("태런 에저튼  ");
            act2_info.setText("제이미 폭스  ");
            act3_info.setText("제이미 도넌  ");
            story_info
                    .setText(movieStorys);
        } else if (id == 4) {
            BitmapDrawable img_movie = (BitmapDrawable) getResources()
                    .getDrawable(R.drawable.e);
            iv.setImageDrawable(img_movie);
            tv.setText(movieTitles);
            tv2.setText("장르 - " + movieKinds);
            dir_iv.setImageResource(R.drawable.e_1);
            dir_info.setText("노규엽");
            act1_iv.setImageResource(R.drawable.e_2);
            act2_iv.setImageResource(R.drawable.e_3);
            act3_iv.setImageResource(R.drawable.e_4);

            act1_info.setText("이범수");
            act2_info.setText("연우진");
            act3_info.setText("박혁권");
            story_info
                    .setText(movieStorys);
        } else if (id == 5) {
            BitmapDrawable img_movie = (BitmapDrawable) getResources()
                    .getDrawable(R.drawable.f);
            iv.setImageDrawable(img_movie);
            tv.setText(movieTitles);
            tv2.setText("장르 - " + movieKinds);
            dir_iv.setImageResource(R.drawable.f_1);
            dir_info.setText("임진순");
            act1_iv.setImageResource(R.drawable.f_2);
            act2_iv.setImageResource(R.drawable.f_3);
            act3_iv.setImageResource(R.drawable.f_4);

            act1_info.setText("마동석");
            act2_info.setText("김새론");
            act3_info.setText("이상엽");
            story_info
                    .setText(movieStorys);
        } else if (id == 6) {
            BitmapDrawable img_movie = (BitmapDrawable) getResources()
                    .getDrawable(R.drawable.g);
            iv.setImageDrawable(img_movie);
            tv.setText(movieTitles);
            tv2.setText("장르 - " + movieKinds);
            dir_iv.setImageResource(R.drawable.g_1);
            dir_info.setText("김병우   ");
            act1_iv.setImageResource(R.drawable.g_2);
            act2_iv.setImageResource(R.drawable.g_3);
            act3_iv.setImageResource(R.drawable.g_4);

            act1_info.setText("하정우   ");
            act2_info.setText("이선균   ");
            act3_info.setText("제니퍼 엘");
            story_info
                    .setText(movieStorys);
        } else if (id == 7) {
            BitmapDrawable img_movie = (BitmapDrawable) getResources()
                    .getDrawable(R.drawable.h);
            iv.setImageDrawable(img_movie);
            tv.setText(movieTitles);
            tv2.setText("장르 - " + movieKinds);
            dir_iv.setImageResource(R.drawable.h_1);
            dir_info.setText("강형철   ");
            act1_iv.setImageResource(R.drawable.h_2);
            act2_iv.setImageResource(R.drawable.h_3);
            act3_iv.setImageResource(R.drawable.h_4);

            act1_info.setText("디오     ");
            act2_info.setText("박혜수   ");
            act3_info.setText("오정세   ");
            story_info
                    .setText(movieStorys);
        } else if (id == 8) {
            BitmapDrawable img_movie = (BitmapDrawable) getResources()
                    .getDrawable(R.drawable.k);
            iv.setImageDrawable(img_movie);
            tv.setText(movieTitles);
            tv2.setText("장르 - " + movieKinds);
            dir_iv.setImageResource(R.drawable.k_1);
            dir_info.setText("페데 알바레즈       ");
            act1_iv.setImageResource(R.drawable.k_2);
            act2_iv.setImageResource(R.drawable.k_3);
            act3_iv.setImageResource(R.drawable.k_4);

            act1_info.setText("클레어 포이         ");
            act2_info.setText("실비아 획스         ");
            act3_info.setText("스베리르 구드나손");
            story_info
                    .setText(movieStorys);
        } else if (id == 9) {
            BitmapDrawable img_movie = (BitmapDrawable) getResources()
                    .getDrawable(R.drawable.l);
            iv.setImageDrawable(img_movie);
            tv.setText(movieTitles);
            tv2.setText("장르 - " + movieKinds);
            dir_iv.setImageResource(R.drawable.l_1);
            dir_info.setText("데이빗 예이츠    ");
            act1_iv.setImageResource(R.drawable.l_2);
            act2_iv.setImageResource(R.drawable.l_3);
            act3_iv.setImageResource(R.drawable.l_4);

            act1_info.setText("에디 레드메인   ");
            act2_info.setText("캐서린 워터스턴");
            act3_info.setText("앨리슨 수돌      ");
            story_info
                    .setText(movieStorys);
        } else if (id == 10) {
            BitmapDrawable img_movie = (BitmapDrawable) getResources()
                    .getDrawable(R.drawable.m);
            iv.setImageDrawable(img_movie);
            tv.setText(movieTitles);
            tv2.setText("장르 - " + movieKinds);
            dir_iv.setImageResource(R.drawable.m_1);
            dir_info.setText("김민호");
            act1_iv.setImageResource(R.drawable.f_2);
            act2_iv.setImageResource(R.drawable.m_2);
            act3_iv.setImageResource(R.drawable.m_3);

            act1_info.setText("마동석");
            act2_info.setText("송지효");
            act3_info.setText("김성오");
            story_info
                    .setText(movieStorys);
        } else if (id == 11) {
            BitmapDrawable img_movie = (BitmapDrawable) getResources()
                    .getDrawable(R.drawable.d);
            iv.setImageDrawable(img_movie);
            tv.setText(movieTitles);
            tv2.setText("장르 - " + movieKinds);
            dir_iv.setImageResource(R.drawable.d_1);
            dir_info.setText("브라이언 싱어");
            act1_iv.setImageResource(R.drawable.d_2);
            act2_iv.setImageResource(R.drawable.d_3);
            act3_iv.setImageResource(R.drawable.d_4);

            act1_info.setText("라미 말렉    ");
            act2_info.setText("루시 보인턴 ");
            act3_info.setText("귈림 리       ");
            story_info
                    .setText(movieStorys);
        } else if (id == 12) {
            BitmapDrawable img_movie = (BitmapDrawable) getResources()
                    .getDrawable(R.drawable.i);
            iv.setImageDrawable(img_movie);
            tv.setText(movieTitles);
            tv2.setText("장르 - " + movieKinds);
            dir_iv.setImageResource(R.drawable.i_1);
            dir_info.setText("브래들리 쿠퍼");
            act1_iv.setImageResource(R.drawable.i_1);
            act2_iv.setImageResource(R.drawable.i_2);
            act3_iv.setImageResource(R.drawable.i_3);

            act1_info.setText("브래들리 쿠퍼");
            act2_info.setText("레이디 가가   ");
            act3_info.setText("샘 엘리어트   ");
            story_info
                    .setText(movieStorys);
        } else if (id == 13) {
            BitmapDrawable img_movie = (BitmapDrawable) getResources()
                    .getDrawable(R.drawable.n);
            iv.setImageDrawable(img_movie);
            tv.setText(movieTitles);
            tv2.setText("장르 - " + movieKinds);
            dir_iv.setImageResource(R.drawable.n_1);
            dir_info.setText("이재규");
            act1_iv.setImageResource(R.drawable.n_2);
            act2_iv.setImageResource(R.drawable.n_3);
            act3_iv.setImageResource(R.drawable.n_4);

            act1_info.setText("유해진");
            act2_info.setText("조진웅");
            act3_info.setText("이서진");
            story_info
                    .setText(movieStorys);
        } else if (id == 14) {
            BitmapDrawable img_movie = (BitmapDrawable) getResources()
                    .getDrawable(R.drawable.j);
            iv.setImageDrawable(img_movie);
            tv.setText(movieTitles);
            tv2.setText("장르 - " + movieKinds);
            dir_iv.setImageResource(R.drawable.j_1);
            dir_info.setText("이 권");
            act1_iv.setImageResource(R.drawable.j_2);
            act2_iv.setImageResource(R.drawable.j_3);
            act3_iv.setImageResource(R.drawable.j_4);

            act1_info.setText("공효진");
            act2_info.setText("김예원");
            act3_info.setText("김성오");
            story_info
                    .setText(movieStorys);
        } else if (id == 15) {
            BitmapDrawable img_movie = (BitmapDrawable) getResources()
                    .getDrawable(R.drawable.o);
            iv.setImageDrawable(img_movie);
            tv.setText(movieTitles);
            tv2.setText("장르 - " + movieKinds);
            dir_iv.setImageResource(R.drawable.o_1);
            dir_info.setText("크리스찬 리버스");
            act1_iv.setImageResource(R.drawable.o_2);
            act2_iv.setImageResource(R.drawable.o_3);
            act3_iv.setImageResource(R.drawable.o_4);

            act1_info.setText("헤라 힐마    ");
            act2_info.setText("로버트 시한  ");
            act3_info.setText("휴고 위빙    ");
            story_info
                    .setText(movieStorys);
        } else if (id == 16) {
            BitmapDrawable img_movie = (BitmapDrawable) getResources()
                    .getDrawable(R.drawable.p);
            iv.setImageDrawable(img_movie);
            tv.setText(movieTitles);
            tv2.setText("장르 - " + movieKinds);
            dir_iv.setImageResource(R.drawable.p_1);
            dir_info.setText("빅터 레빈     ");
            act1_iv.setImageResource(R.drawable.p_2);
            act2_iv.setImageResource(R.drawable.p_3);
            act3_iv.setImageResource(R.drawable.p_1);

            act1_info.setText("키아누 리브스 ");
            act2_info.setText("위노나 라이더 ");
            act3_info.setText("빅터 레빈    ");
            story_info
                    .setText(movieStorys);
        } else if (id == 17) {
            BitmapDrawable img_movie = (BitmapDrawable) getResources()
                    .getDrawable(R.drawable.q);
            iv.setImageDrawable(img_movie);
            tv.setText(movieTitles);
            tv2.setText("장르 - " + movieKinds);
            dir_iv.setImageResource(R.drawable.q_1);
            dir_info.setText("제임스 완     ");
            act1_iv.setImageResource(R.drawable.q_2);
            act2_iv.setImageResource(R.drawable.q_3);
            act3_iv.setImageResource(R.drawable.q_1);

            act1_info.setText("제이슨 모모아 ");
            act2_info.setText("엠버 허드    ");
            act3_info.setText("니콜 키드먼   ");
            story_info
                    .setText(movieStorys);
        } else if (id == 18) {
            BitmapDrawable img_movie = (BitmapDrawable) getResources()
                    .getDrawable(R.drawable.r);
            iv.setImageDrawable(img_movie);
            tv.setText(movieTitles);
            tv2.setText("장르 - " + movieKinds);
            dir_iv.setImageResource(R.drawable.r_1);
            dir_info.setText("트래비스 나이트   ");
            act1_iv.setImageResource(R.drawable.r_2);
            act2_iv.setImageResource(R.drawable.r_3);
            act3_iv.setImageResource(R.drawable.r_4);

            act1_info.setText("헤일리 스테인펠드 ");
            act2_info.setText("존 시나         ");
            act3_info.setText("피터 쿨렌        ");
            story_info
                    .setText(movieStorys);
        } else if (id == 19) {
            BitmapDrawable img_movie = (BitmapDrawable) getResources()
                    .getDrawable(R.drawable.s);
            iv.setImageDrawable(img_movie);
            tv.setText(movieTitles);
            tv2.setText("장르 - " + movieKinds);
            dir_iv.setImageResource(R.drawable.s_1);
            dir_info.setText("폴 페이그       ");
            act1_iv.setImageResource(R.drawable.s_2);
            act2_iv.setImageResource(R.drawable.s_3);
            act3_iv.setImageResource(R.drawable.s_4);

            act1_info.setText("블레이크 라이블리");
            act2_info.setText("안나 켄드릭     ");
            act3_info.setText("헨리 골딩       ");
            story_info
                    .setText(movieStorys);
        } else if (id == 20) {
            BitmapDrawable img_movie = (BitmapDrawable) getResources()
                    .getDrawable(R.drawable.t);
            iv.setImageDrawable(img_movie);
            tv.setText(movieTitles);
            tv2.setText("장르 - " + movieKinds);
            dir_iv.setImageResource(R.drawable.t_1);
            dir_info.setText("도나토 카리시    ");
            act1_iv.setImageResource(R.drawable.t_2);
            act2_iv.setImageResource(R.drawable.t_3);
            act3_iv.setImageResource(R.drawable.t_4);

            act1_info.setText("토니 세르빌로   ");
            act2_info.setText("장 르노        ");
            act3_info.setText("아레시오 보니   ");
            story_info
                    .setText(movieStorys);
        } else if (id == 21) {
            BitmapDrawable img_movie = (BitmapDrawable) getResources()
                    .getDrawable(R.drawable.u);
            iv.setImageDrawable(img_movie);
            tv.setText(movieTitles);
            tv2.setText("장르 - " + movieKinds);
            dir_iv.setImageResource(R.drawable.u_1);
            dir_info.setText("톰 네이겔    ");
            act1_iv.setImageResource(R.drawable.u_2);
            act2_iv.setImageResource(R.drawable.u_3);
            act3_iv.setImageResource(R.drawable.u_1);

            act1_info.setText("톰 네이겔   ");
            act2_info.setText("데니스 리차드");
            act3_info.setText("미샤 바튼   ");
            story_info
                    .setText(movieStorys);
        } else if (id == 22) {
            BitmapDrawable img_movie = (BitmapDrawable) getResources()
                    .getDrawable(R.drawable.v);
            iv.setImageDrawable(img_movie);
            tv.setText(movieTitles);
            tv2.setText("장르 - " + movieKinds);
            dir_iv.setImageResource(R.drawable.v_1);
            dir_info.setText("M.나이트 샤말란  ");
            act1_iv.setImageResource(R.drawable.v_2);
            act2_iv.setImageResource(R.drawable.v_3);
            act3_iv.setImageResource(R.drawable.v_1);

            act1_info.setText("제임스 맥어보이  ");
            act2_info.setText("브루스 윌리스    ");
            act3_info.setText("사무엘 L.잭슨    ");
            story_info
                    .setText(movieStorys);
        } else if (id == 23) {
            BitmapDrawable img_movie = (BitmapDrawable) getResources()
                    .getDrawable(R.drawable.x);
            iv.setImageDrawable(img_movie);
            tv.setText(movieTitles);
            tv2.setText("장르 - " + movieKinds);
            dir_iv.setImageResource(R.drawable.x_1);
            dir_info.setText("그레고리 플로킨     ");
            act1_iv.setImageResource(R.drawable.x_2);
            act2_iv.setImageResource(R.drawable.x_3);
            act3_iv.setImageResource(R.drawable.x_4);

            act1_info.setText("에이미 포사이스    ");
            act2_info.setText("벡스 테일러 클라우스");
            act3_info.setText("레인 에드워즈      ");
            story_info
                    .setText(movieStorys);
        }

    }
}
