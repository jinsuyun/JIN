package ssm.hel_per.exercise_all.exercise_weak.weak_lv2;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import ssm.hel_per.R;

public class exercise_plank extends AppCompatActivity {
    TextView mText;
    Button mButton;
    int value=120;
    String id;
    Double weight;
    ImageView swingImage;
    ImageView skyImage;

    Animation shakeAnimation;
    Animation dropAnimation;
    Animation flowAnimation;

    public static String urlStr = "http://13.209.40.50:3000/daily";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_plank);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mText=(TextView)findViewById(R.id.text_plank);
        mButton=(Button)findViewById(R.id.nextbtn_plank);

        new CountDownTimer(120*1000, 1000){

            @Override
            public void onTick(long millisUntilFinished) { // 총 시간과 주기
                value--;
                mText.setText("남은 시간  " + value + "초");
//                if(value==0){
//                    Intent intent = new Intent(exercise_buffet.this,exercise_jumpingrope.class);
//                    startActivity(intent);
//                    finish();
//                }
            }

            @Override
            public void onFinish() {

            }
        }.start();  // 타이머 시작
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = getIntent().getStringExtra("id");
                weight=getIntent().getDoubleExtra("weight",0);


                ConnectThread thread = new ConnectThread(urlStr, id,String.valueOf(weight));
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                finish();
            }
        });
        // swing 이미지에 애니메이션 객체 설정
        swingImage = (ImageView) findViewById(R.id.swingImage);
        shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);
        swingImage.setAnimation(shakeAnimation);

        // water 이미지에 애니메이션 객체 설정
        // waterImage = (ImageView) findViewById(R.id.waterImage);
        dropAnimation = AnimationUtils.loadAnimation(this, R.anim.drop);
        //waterImage.setAnimation(dropAnimation);

        // sky 이미지에 애니메이션 객체 설정
        skyImage = (ImageView) findViewById(R.id.skyImage);
        flowAnimation = AnimationUtils.loadAnimation(this, R.anim.flow);
        skyImage.setAnimation(flowAnimation);
        flowAnimation.setAnimationListener(new AnimationAdapter());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);


        if (hasFocus) {
            shakeAnimation.start();
            dropAnimation.start();
            flowAnimation.start();
        } else {
            shakeAnimation.reset();
            dropAnimation.reset();
            flowAnimation.reset();
        }

    }



    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

    }



    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();

    }


    /**
     * 애니메이션의 시작과 종료 시점을 알기 위한 리스너
     */
    private final class AnimationAdapter implements Animation.AnimationListener {

        public void onAnimationStart(Animation animation) {

        }

        public void onAnimationEnd(Animation animation) {

        }

        public void onAnimationRepeat(Animation animation) {

        }

    }

 class ConnectThread extends Thread {
    String urlStr;
    String id;
    String workoutday;
    String running_time = "0";
    String weight_time = "25";
    String arm = "1";
    String back = "0";
    String shoulder = "1";
    String chest = "1";
    String leg = "1";
    String sixpack = "1";
    String eat_calories = "0";
    String all_eat_calories = "0";
    String spent_calories = "191";
    String all_spent_calories = "0";
    String weight;
    String objective="0";


    public ConnectThread(String inStr, String id,String weight) {
        this.urlStr = inStr;
        this.id = id;
        this.weight=weight;
    }

    public void run() {
        try {
            final String output = request(urlStr, id,weight);

        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    private String request(String urlStr, String id,String weight) throws IOException {
        StringBuilder output = new StringBuilder();
        long now = System.currentTimeMillis();

        try {
            URL url = new URL(urlStr);
            //workoutday=new Date(now);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            workoutday=simpleDateFormat.format(new Date(System.currentTimeMillis()));

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            if(conn != null) {
                String json = "";

                // build jsonObject
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("id", id);
                jsonObject.put("workoutday", workoutday);
                jsonObject.put("running_time", running_time);
                jsonObject.put("weight_time", weight_time);
                jsonObject.put("arm", arm);
                jsonObject.put("back", back);
                jsonObject.put("shoulder", shoulder);
                jsonObject.put("chest", chest);
                jsonObject.put("leg", leg);
                jsonObject.put("sixpack", sixpack);
                jsonObject.put("eat_calories", eat_calories);
                jsonObject.put("all_eat_calories", all_eat_calories);
                jsonObject.put("spent_calories", spent_calories);
                jsonObject.put("all_spent_calories", all_spent_calories);
                jsonObject.put("weight", weight);
                jsonObject.put("objective",objective);
                // convert JSONObject to JSON to String
                json = jsonObject.toString();
                conn.setConnectTimeout(1000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();
                os.write(json.getBytes("euc-kr")); // 출력 스트림에 출력.
                os.flush(); // 출력 스트림을 플러시(비운다)하고 버퍼링 된 모든 출력 바이트를 강제 실행.
                os.close();

                int resCode = conn.getResponseCode();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String line = null;
                while(true) {
                    line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                    output.append(line + "\n");
                }
                reader.close();
                conn.disconnect();
            }
        } catch (Exception e) {
            Log.e("SampleHTTP", "Exception in processing response.", e);
        }
        return output.toString();
    }
}
}

