package ssm.hel_per;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.ArcProgress;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import static android.os.SystemClock.sleep;
import static android.support.constraint.Constraints.TAG;


public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    Handler handler = new Handler();
    int alert=0;
    String id2="";
    int age=0;
    double weight=0;
    double height=0;
    String sex="";
    String name;
    double targetweight;
    int targetperiod;
    int worklevel;
    int workperiod;
    String bodytype;
    String bt;

    //   private Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent it1 = getIntent();
        alert = (int)it1.getSerializableExtra("alert");
        id2=it1.getStringExtra("id");


        if(alert==1234) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("안녕하세요 저희 앱의 신규회원이 되신 것을 축하드립니다. 저희 앱의 서비스를 받으시려면 설문조사를 통해 고객의 신체유형을 파악하는 과정을 거쳐야합니다.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AlertDialog.Builder builder2 = new AlertDialog.Builder(Main2Activity.this);
                            builder2.setTitle("설문조사를 하시겠습니까?");
                            builder2.setPositiveButton("아니오", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            builder2.setNegativeButton("예", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent it = new Intent(Main2Activity.this,Survey.class);
                                    it.putExtra("id", id2);
                                    startActivity(it);
                                }
                            });
                            builder2.show();
                        }
                    }).setCancelable(false).show();
        }else if(alert ==1){
            bt=getIntent().getStringExtra("bodytype");
            age = (int)it1.getIntExtra("age", 0);

            weight =it1.getDoubleExtra("weight",0);
            height =it1.getDoubleExtra("height",0);
            sex =it1.getStringExtra("sex");
            String human="";

            Log.d(TAG,"SEX"+sex+age);

            if (sex.equals("M"))
                human = "남자";
            else
                human = "여자";


            TextView textView_age=findViewById(R.id.age);
            TextView textView_weight=findViewById(R.id.weight);
            TextView textView_height=findViewById(R.id.height);
            TextView textView_sex=findViewById(R.id.sex);

            textView_age.setText(String.valueOf(age));
            textView_weight.setText(String.valueOf(weight));
            textView_height.setText(""+height);
            textView_sex.setText(human);


            ImageView iv = findViewById(R.id.information);

            if(bt!= null) {
                if (bt.equals("LW"))
                    iv.setImageResource(R.drawable.lw_1);
                else if (bt.equals("SW"))
                    iv.setImageResource(R.drawable.sw_2);
                else if (bt.equals("OB"))
                    iv.setImageResource(R.drawable.ob_3);
                else if (bt.equals("SF"))
                    iv.setImageResource(R.drawable.sf_5);
                else if (bt.equals("OF"))
                    iv.setImageResource(R.drawable.of_6);
                else if (bt.equals("LB"))
                    iv.setImageResource(R.drawable.lb_7);
                else if (bt.equals("SB"))
                    iv.setImageResource(R.drawable.sb_8);
                else if (bt.equals("SS"))
                    iv.setImageResource(R.drawable.ss_9);
                else if (bt.equals("OS"))
                    iv.setImageResource(R.drawable.os_10);
            }

            final ArcProgress arcProgress = findViewById(R.id.arc_progress);
            arcProgress.setProgress(100);

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            boolean a = false;
                            if (a) {
                                ObjectAnimator anim = ObjectAnimator.ofInt(arcProgress, "progress", 0, 10);
                                anim.setInterpolator(new DecelerateInterpolator());
                                anim.setDuration(500);
                                anim.start();
                            } else {
                                AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(Main2Activity.this, R.animator.progress_anim);
                                set.setInterpolator(new DecelerateInterpolator());
                                set.setTarget(arcProgress);
                                set.start();
                            }
                        }
                    });
                }
            }, 0, 2000);

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
        }

    }
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager manager = getFragmentManager();
        if (id == R.id.nav_diary) {
            manager.beginTransaction().replace(R.id.content_main,new writeDiary()).commit();
        } else if (id == R.id.nav_my_state) {
            weight=getIntent().getDoubleExtra("weight",0);
            targetweight=getIntent().getDoubleExtra("targetweight",0);
            targetperiod=getIntent().getIntExtra("targetperiod",0);
            worklevel=getIntent().getIntExtra("worklevel",0);
            workperiod=getIntent().getIntExtra("workperiod",0);
            height=getIntent().getDoubleExtra("height",0);

            Intent it_myState = new Intent(Main2Activity.this,myState.class);

            it_myState.putExtra("weight",weight);
            it_myState.putExtra("targetweight",targetweight);
            it_myState.putExtra("targetperiod",targetperiod);
            it_myState.putExtra("worklevel",worklevel);
            it_myState.putExtra("workperiod",workperiod);
            it_myState.putExtra("height",height);


            manager.beginTransaction().replace(R.id.content_main,new myState()).commit();
        } else if (id == R.id.nav_exercise) {
            manager.beginTransaction().replace(R.id.content_main,new exercise()).commit();
        } else if (id == R.id.nav_food_manage) {
            Intent it3_myState = new Intent(Main2Activity.this,foodManage.class);
            it3_myState.putExtra("state",bt);
            manager.beginTransaction().replace(R.id.content_main,new foodManage()).commit();
        } else if (id == R.id.nav_body_check) {
            bodyAlgo bodyAlgo = new bodyAlgo();
            Intent it_bodytype = new Intent(Main2Activity.this,bodyCheck.class);
            age=getIntent().getIntExtra("age",0);
            weight=getIntent().getDoubleExtra("weight",0);
            height=getIntent().getDoubleExtra("height",0);
            sex=getIntent().getStringExtra("sex");
            name=getIntent().getStringExtra("name");
            targetweight=getIntent().getDoubleExtra("targetweight",0);
            targetperiod=getIntent().getIntExtra("targetperiod",0);
            worklevel=getIntent().getIntExtra("worklevel",0);
            workperiod=getIntent().getIntExtra("workperiod",0);
            bodytype=getIntent().getStringExtra("bodytype");

            bodyAlgo.bmiCal(height,weight);
            bodyAlgo.bmrCal(height,weight,age,sex);

            it_bodytype.putExtra("age",age);
            it_bodytype.putExtra("weight",weight);
            it_bodytype.putExtra("height",height);
            it_bodytype.putExtra("sex",sex);
            it_bodytype.putExtra("name",name);
            it_bodytype.putExtra("targetweight",targetweight);
            it_bodytype.putExtra("targetperiod",targetperiod);
            it_bodytype.putExtra("worklevel",worklevel);
            it_bodytype.putExtra("workperiod",workperiod);
            it_bodytype.putExtra("bodytype",bodytype);

            manager.beginTransaction().replace(R.id.content_main,new bodyCheck()).commit();
        } else if (id == R.id.nav_re_survey) {
            Intent it = new Intent(Main2Activity.this,Re_Survey.class);
           // bodytype=getIntent().getStringExtra("bodytype");
            //it.putExtra("bodytype",bodytype);
            it.putExtra("id", id2);
            /*it.putExtra("id",id);
            it.putExtra("sex",sex);
            it.putExtra("age",age);*/
            //Log.d(TAG,"asdf"+id+sex+age);
            startActivity(it);
        } else if (id == R.id.nav_my_home) {
            manager.beginTransaction().replace(R.id.content_main,new home()).commit(); // 홈화면
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showAddWeightDialog(Context c) {
        final EditText weightEditText = new EditText(c);
        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("Add a new Weight")
                //.setMessage("What do you want to do next?")
                .setView(weightEditText)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String currentWeight = String.valueOf(weightEditText.getText());
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }

    private long pressedTime = 0;

    // 리스너 생성
    public interface OnBackPressedListener {
        public void onBack();
    }

    // 리스너 객체 생성
    private OnBackPressedListener mBackListener;

    // 리스너 설정 메소드
    public void setOnBackPressedListener(OnBackPressedListener listener) {
        mBackListener = listener;
    }

    // 뒤로가기 버튼을 눌렀을 때의 오버라이드 메소드
    @Override
    public void onBackPressed() {

        // 다른 Fragment 에서 리스너를 설정했을 때 처리됩니다.
        if(mBackListener != null) {
            mBackListener.onBack();
            Log.e("!!!", "Listener is not null");
            // 리스너가 설정되지 않은 상태(예를들어 메인Fragment)라면
            // 뒤로가기 버튼을 연속적으로 두번 눌렀을 때 앱이 종료됩니다.
        } else {
            Log.e("!!!", "Listener is null");
            if ( pressedTime == 0 ) {
                Snackbar.make(findViewById(R.id.content_main),
                        " 한 번 더 누르면 종료됩니다." , Snackbar.LENGTH_LONG).show();
                pressedTime = System.currentTimeMillis();
            }
            else {
                int seconds = (int) (System.currentTimeMillis() - pressedTime);

                if ( seconds > 2000 ) {
                    Snackbar.make(findViewById(R.id.content_main),
                            " 한 번 더 누르면 종료됩니다." , Snackbar.LENGTH_LONG).show();
                    pressedTime = 0 ;
                }
                else {
                    super.onBackPressed();
                    Log.e("!!!", "onBackPressed : finish, killProcess");
                    finish();
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            }
        }
    }
}
