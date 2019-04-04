package com.example.qqw;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;
import android.widget.VideoView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private VideoView videoview;
    private ArrayList<String> array = new ArrayList<String>();
    private int count;
    private String users;
    LinearLayout baseLayout;

    private final Integer ImgID[] = {R.drawable.a, R.drawable.b,
            R.drawable.c, R.drawable.e, R.drawable.f, R.drawable.g,
            R.drawable.h, R.drawable.k, R.drawable.l, R.drawable.m,
            R.drawable.d, R.drawable.i, R.drawable.n, R.drawable.j,
            R.drawable.o, R.drawable.p, R.drawable.q, R.drawable.r,
            R.drawable.s, R.drawable.t, R.drawable.u, R.drawable.v, R.drawable.x};

    private final static String ImgName[] = {"국가 부도의 날", "마약왕", "후드",
            "출국", "동네 사람들", "PMC:더벙커", "스윙 키즈", "거미줄에 걸린 소녀", "그란델왈드의 범죄",
            "성난황소", "보헤미안 랩소디", "스타 이즈 본", "완벽한 타인", "도어락", "모털 엔진", "데스티네이션 웨딩",
            "아쿠아맨", "범블비", "부탁 하나만 들어줘", "안개 속 소녀", "악령의 캠핑카", "글래스", "헬 페스트"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button music_play = (Button) findViewById(R.id.music_play);
        Button music_pause = (Button) findViewById(R.id.music_pause);

        //서비스 시작(음악 재생)
        music_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test", "액티비티-서비스 시작버튼클릭");
                Intent intent = new Intent(
                        getApplicationContext(),//현재제어권자
                        MyService.class); // 이동할 컴포넌트
                startService(intent); // 서비스 시작
            }
        });

        //서비스 중지(음악 종료)
        music_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test", "액티비티-서비스 종료버튼클릭");
                Intent intent = new Intent(
                        getApplicationContext(),//현재제어권자
                        MyService.class); // 이동할 컴포넌트
                stopService(intent); // 서비스 종료
            }
        });

        // 메인 배경인 LinearLayout을 baseLayout이라는 변수로 받아주고 배경화면 사진과 투명도를 설정해줌.
        baseLayout = (LinearLayout) findViewById(R.id.lin);
        //baseLayout.setBackgroundResource(R.drawable.sss);
        Drawable alpha = baseLayout.getBackground();
        alpha.setAlpha(70);


        //버튼 매칭
        final Button next = (Button) findViewById(R.id.next);

        //버튼이 클릭됬을 때 이벤트 처리 부분.
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼이 클리되면 새 화면이 시작되도록 새로운 Intent생성, 실행 선언 부분.
                Intent mintent = new Intent(getApplicationContext(), Main.class);
                startActivity(mintent);
            }
        });

        //LoginActivity로부터 아이디를 putExtra()로 넘기고 getStringExtra()로 받아 MainActiviy에서 활용하기 위한 부분.
        Intent intent = getIntent();
        //로그인 한 아이디를 받는 부분.
        users = intent.getStringExtra("id_name");
        //메인화면의 맨 윗줄에 있는 TextView에 회원의 아이디를 출력시키는 부분.
        TextView user = (TextView) findViewById(R.id.user);
        user.setText(users + "님 환영합니다.");

        //멤버로 생성해준 array 리스트에 bohemian 동영상을 추가해줌.
        array.add("android.resource://" + getPackageName() + "/raw/glass");
        count = 0;

        //비디오 뷰 매칭.
        videoview = (VideoView) findViewById(R.id.videoview);

        //연속 재생시킬 2개의 동영상의 경로를 저장해줌.
        try {
            final Uri videofile1 = Uri.parse("android.resource://"
                    + getPackageName() + "/raw/korea");
            final Uri videofile2 = Uri.parse("android.resource://"
                    + getPackageName() + "/raw/glass");

            //비디오뷰에 동영상 세팅
            videoview.setVideoURI(videofile1);
            videoview.requestFocus();

            // 미디어 컨트롤러 추가(동영상 컨트롤러를 만들어준 것-> 재생, 중지, 구간선택 가능)
            MediaController controller = new MediaController(this);
            // 생성해준 비디오뷰에 미디어 컨트롤러를 세팅해줌.
            videoview.setMediaController(controller);

            // 동영상 재생이 완료 됬을 때의 이벤트 처리(베놈과 보헤미안 랩소디 예고편을 순차적으로 반복 재생시키기 위한 메서드)
            MediaPlayer.OnCompletionListener mComplete = new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    //만들어 줬던 URL array이 클 경우(2개의 동영상 중 재생 안된 것이 있는 경우).
                    if (array.size() > count) {
                        count++;
                        videoview.setVideoURI(videofile2);
                        videoview.start();
                    } else {
                        //2개의 동영상 재생이 완료 되면 다시 1번 영상 재생하기 위한 부분.
                        count--;
                        videoview.setVideoURI(videofile1);
                        videoview.start();
                    }
                }
            };

            // 선언해준 완료 시 이벤트 처리문을 비디오뷰에 적용.
            videoview.setOnCompletionListener(mComplete);
            // 비디오뷰 시작.
            videoview.start();
        } catch (Exception ex) {
            Log.d(getClass().getName(), "Video failed: '" + ex + "'");
            ex.printStackTrace();
        }
        // 미디어 컨트롤러 추가
        MediaController controller = new MediaController(this);
        videoview.setMediaController(controller);

        // 비디오 뷰 포커스 요청 부분.
        videoview.requestFocus();

        //갤러리 뷰 매칭 부분.
        Gallery gallery = (Gallery) findViewById(R.id.gallery);
        //갤러리 뷰에 추가한 어뎁터 설정해주기.
        gallery.setAdapter(new MyAdapter(this));
        //gallery.setBackgroundColor(getResources().getColor(R.color.gals));

        //뷰플리퍼 매칭 부분.
        ViewFlipper vf = (ViewFlipper) findViewById(R.id.vf);
        //뷰가 넘어가는 시간초 지정
        vf.setFlipInterval(700);
        //뷰 플리퍼 시작
        vf.startFlipping();

        //TextView 매칭 부분.
        TextView tv3 = (TextView) findViewById(R.id.tv3);
        final SpannableStringBuilder sp3 = new SpannableStringBuilder(
                "I ♥ MOVIE");
        //xml에서 텍스트의 색상을 부분 지정할 수 없어서 직접 문구 부분 색상 세팅.
        sp3.setSpan(new ForegroundColorSpan(Color.RED), 2, 4,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv3.append(sp3);

        TextView tv4 = (TextView) findViewById(R.id.tv4);
        final SpannableStringBuilder sp4 = new SpannableStringBuilder(
                "LOCATE & THEATER");
        sp4.setSpan(new ForegroundColorSpan(Color.RED), 7, 9,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv4.append(sp4);

        //Web Search로 이동하는 버튼 매칭
        Button bt_web = (Button) findViewById(R.id.bt_web);
        //버튼이 눌렸을 때 실행 될 이벤트
        bt_web.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //버튼을 누를 경우 WebView 클래스 실행(새 화면에서).
                Intent intent = new Intent(getApplicationContext(),
                        Webview.class);
                startActivity(intent);
            }
        });

        //버튼 매칭 부분.
        Button end = (Button) findViewById(R.id.end);
        //버튼이 클릭되었을 경우 이벤트 처리 부분,
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //프로그램 종료 실행.
                finish();
            }
        });

    }

    //Adapter 생성 & 설정.
    public class MyAdapter extends BaseAdapter {
        Context context;

        public MyAdapter(Context c) {
            context = c;
        }

        @Override
        public int getCount() {
            return ImgID.length;
        }

        @Override
        public Object getItem(int arg0) {
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //영화 포스터 개수 23개에 맞게 각 사진별 투표 수가 저장 될 수있는 23크기의 배열 생성.
            final int voteCount[] = new int[23];
            //23개의 사진이 클릭 될 때마다 각 위치의 배열에 count 수가 저장된다.
            for (int i = 0; i < 23; i++)
                voteCount[i] = 0;

            //ImageView 매칭 부분.
            ImageView imageview = new ImageView(context);
            imageview.setScaleType(ImageView.ScaleType.FIT_XY);
            //갤러리 뷰에 삽입될 이미지들의 크기를 지정해주는 부분.
            imageview.setLayoutParams(new Gallery.LayoutParams(390, 430));
            //맴머 변수로 설정해준 이미지(R.drawable.-)들을 순차적으로 이미지 뷰에 세팅해주는 부분.
            imageview.setImageResource(ImgID[position]);
            //position값을 pos라는 변수에 상수 값으로 지정해줌.
            final int pos = position;
            for (int i = 0; i < ImgID.length; i++) {
                final int index;
                index = i;
                //갤러리 뷰에 세팅된 각 이미지들이 클릭되었을 경우 이벤트 처리 부분.
                imageview.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        //투표수가 클릭 될 때마다 증가.
                        voteCount[index]++;
                        //토스트 메세지로 총 몇 표인지 출력.
                        Toast.makeText(
                                getApplicationContext(),
                                ImgName[pos] + " : 총 " + voteCount[index]
                                        + " 표", Toast.LENGTH_SHORT).show();

                    }
                });
            }

            //이미지가 길게 클릭되었을 경우 이벤트 처리 부분.
            imageview.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View arg0) {
                    //pictures라는 이름으로 만들어준 xml 디자인을 dialog형태로 띄우기 위해 현재 intent에 inflate 시켜주는 부분.
                    View dialogView = (View) View.inflate(MainActivity.this,
                            R.layout.pictures, null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(
                            MainActivity.this);
                    //만들어준 dialog의 배경색등을 설정하기 위해 Layout을 매칭 시켜줌.
                    LinearLayout dial = (LinearLayout) dialogView
                            .findViewById(R.id.dial);
                    //배경색을 color라는 value file에 지정해준 gal이라는 색으로 바꿔주는 부분.
                    dial.setBackgroundColor(getResources().getColor(R.color.gal));
                    //투명도 지정을 위해 alpha 생성.
                    Drawable alpha = dial.getBackground();
                    dial.setAlpha(1);
                    //dialog에 삽입된 ImageView 매칭 부분.
                    ImageView ivPoster = (ImageView) dialogView
                            .findViewById(R.id.iv1);
                    //dialog에 삽입된 Button 매칭 부분.
                    Button rating_bt = (Button) dialogView
                            .findViewById(R.id.rating_bt);
                    //각 이미지가 LongClick되었을 때 해당 사진(pos에 위치가 저장)이 매칭한 ImageView에 출력되는 부분.
                    ivPoster.setImageResource(ImgID[pos]);
                    //마찬가지로 해당 포스터의 제목이 dialog에 Title로 출력됨
                    dlg.setTitle(ImgName[pos]);
                    //dialog의 아이콘은 sss라는 이미지로 획일적 출력되도록 세팅.
                    dlg.setIcon(R.drawable.sss);
                    //위의 설정들로 dialog를 세팅.
                    dlg.setView(dialogView);
                    //dialog에 닫기 버튼 생성.
                    dlg.setPositiveButton("닫기", null);
                    rating_bt.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View arg0) {
                            Toast.makeText(getApplicationContext(),
                                    "영화를 선택하시면 리뷰 작성을 할 수 있습니다",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                    dlg.show();
                    return false;
                }
            });
            return imageview;
        }
    }
}
