package ssm.hel_per;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.NavigationMenuItemView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.facebook.FacebookSdk.getApplicationContext;

public class bodyCheck extends Fragment implements Main2Activity.OnBackPressedListener{

    public static String urlStr = "http://13.209.40.50:3000/allcalorie"; // 웹

    home mainFragment;
    View v;
    ImageView iv;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;
    TextView tv6;
    TextView tv7;
    TextView tv8;

    Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.body_check, container, false);
        super.onCreate(savedInstanceState);

        mainFragment = new home();


       String bt = getActivity().getIntent().getStringExtra("bodytype");

        iv = (ImageView) v.findViewById(R.id.imageView4);
        tv1 = (TextView) v.findViewById(R.id.textView3);
        tv2 = v.findViewById(R.id.textView6);
        tv3 = v.findViewById(R.id.textView7);
        tv4 = v.findViewById(R.id.textView8);
        tv5 = v.findViewById(R.id.textView9);
        tv6 = v.findViewById(R.id.textView11);
        tv7 = v.findViewById(R.id.textview13);

        Log.i("body",bt.toString());

        if(bt!= null) {
            if (bt.equals("LW"))
                iv.setImageResource(R.drawable.lw_1_);
            else if (bt.equals("SW"))
                iv.setImageResource(R.drawable.sw_2_);
            else if (bt.equals("OB"))
                iv.setImageResource(R.drawable.ob_3_);
            else if (bt.equals("SF"))
                iv.setImageResource(R.drawable.sf_5_);
            else if (bt.equals("OF"))
                iv.setImageResource(R.drawable.of_6_);
            else if (bt.equals("LB"))
                iv.setImageResource(R.drawable.lb_7_);
            else if (bt.equals("SB"))
                iv.setImageResource(R.drawable.sb_8_);
            else if (bt.equals("SS"))
                iv.setImageResource(R.drawable.ss_9_);
            else if (bt.equals("OS"))
                iv.setImageResource(R.drawable.os_10_);
        }

        int age = getActivity().getIntent().getIntExtra("age", 0);
        double height = getActivity().getIntent().getDoubleExtra("height", 0);
        double weight = getActivity().getIntent().getDoubleExtra("weight", 0);
        String sex = getActivity().getIntent().getStringExtra("sex");
        double targetweight = getActivity().getIntent().getDoubleExtra("targetweight", 0);
        int targetperiod = getActivity().getIntent().getIntExtra("targetperiod", 0);
        int worklevel = getActivity().getIntent().getIntExtra("worklevel", 0);

        bodyAlgo bodyalgo = new bodyAlgo();

        tv1.setText("당신의 비만도 지수는 " + (double)(Math.round(bodyalgo.bmiCal(height, weight)) * 100d) / 100d);
        tv2.setText("일일 필요 열량: " + (double)(Math.round(bodyalgo.consumeCal(height, weight, age, sex, worklevel) * 100d) / 100d) + "kcal");
        tv3.setText("기초대사량: " + (double)(Math.round(bodyalgo.bmrCal(height, weight, age, sex) * 100d) / 100d) + "kcal");
        tv4.setText("음식소화흡수열량: " + (double)(Math.round(bodyalgo.tefCal(height, weight, age, sex, worklevel) * 100d) / 100d) + "kcal");
        tv5.setText("활동대사량: " + (double)(Math.round(bodyalgo.actCal(height, weight, age, sex, worklevel) * 100d) / 100d) + "kcal");
        tv6.setText("일일목표소모 열량: " + (double)(Math.round(bodyalgo.targetCal(weight, targetweight, targetperiod) * 100d) / 100d) + "kcal");
        tv7.setText("당신이 지금부터 " + targetperiod + "일간 " + Math.abs(weight - targetweight) + "kg을 조절하기 위해서는 매일 운동으로 "
                + (double)(Math.round(bodyalgo.targetCal(weight, targetweight, targetperiod) * 0.3 * 100d) / 100d) + "kcal를 소모해야 하고, 식사는 하루 "
                + (double)(Math.round(bodyalgo.eatCal(height, weight, age, sex, worklevel, targetweight, targetperiod) * 100d) / 100d) + "kcal 를 섭취해야 합니다..");

        ConnectThread thread = new ConnectThread(urlStr, getActivity().getIntent().getStringExtra("id"), (double)(Math.round(bodyalgo.eatCal(height, weight, age, sex, worklevel, targetweight, targetperiod) * 100d) / 100d));
        thread.start();

        return v;
    }


    class ConnectThread extends Thread {
        String urlStr;
        String id;
        String workoutday;
        double all_eat_calories;

        public ConnectThread(String inStr, String id, double all_eat_calories) {
            this.urlStr = inStr;
            this.id = id;
            this.all_eat_calories = all_eat_calories;

        }

        public void run() {
            try {
                final String output = request(urlStr, id, all_eat_calories);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject obj = new JSONObject(output);
                            //Toast.makeText(getApplicationContext(), all_eat_calories + " kcal가 등록되었습니다..", Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        private String request(String urlStr, String id, double all_eat_calories) throws IOException {
            StringBuilder output = new StringBuilder();
            try {
                URL url = new URL(urlStr);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                workoutday = simpleDateFormat.format(new Date(System.currentTimeMillis()));

                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                if(conn != null) {
                    String json = "";

                    // build jsonObject
                    JSONObject jsonObject = new JSONObject();

                    jsonObject.put("id", id);
                    jsonObject.put("workoutday", workoutday);
                    jsonObject.put("all_eat_calories", all_eat_calories);

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



    @Override
    public void onBack() {
        Log.e("Other", "onBack()");
        // 리스너를 설정하기 위해 Activity 를 받아옵니다.
        Main2Activity activity = (Main2Activity)getActivity();
        // 한번 뒤로가기 버튼을 눌렀다면 Listener 를 null 로 해제해줍니다.
        activity.setOnBackPressedListener(null);
        // MainFragment 로 교체
        getActivity().getFragmentManager().beginTransaction()
                .replace(R.id.content_main, mainFragment).commit();
        // Activity 에서도 뭔가 처리하고 싶은 내용이 있다면 하단 문장처럼 호출해주면 됩니다.
        // activity.onBackPressed();
    }

    // Fragment 호출 시 반드시 호출되는 오버라이드 메소드입니다.
    @Override
    //                     혹시 Context 로 안되시는분은 Activity 로 바꿔보시기 바랍니다.
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("Other", "onAttach()");
        ((Main2Activity)context).setOnBackPressedListener(this);
    }
}
