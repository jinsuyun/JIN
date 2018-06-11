package ssm.hel_per;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class foodManage  extends Fragment implements Main2Activity.OnBackPressedListener{

    public static String urlStr = "http://13.209.40.50:3000/calorie"; // 웹
    home mainFragment;
    View v,v2;
    FrameLayout food;
    String bt;
    ImageView graph;
    TextView tv;
    Button btn;
    FloatingActionButton fab;

    Handler handler = new Handler();
    public FloatingActionButton getFloatingActionButton() {
        return fab;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.food_manage, container, false);
        //v2 = inflater.inflate(R.layout.food_calori, container, false);
        food = (FrameLayout)v.findViewById(R.id.food);
        graph =(ImageView)v.findViewById(R.id.foodinfograph);
        tv = (TextView) v.findViewById(R.id.reqCalText);
        btn = (Button) v.findViewById(R.id.regCalButton);
        bt = getActivity().getIntent().getStringExtra("bodytype");

        bodyAlgo bodyalgo = new bodyAlgo();

        int age = getActivity().getIntent().getIntExtra("age", 0);
        double height = getActivity().getIntent().getDoubleExtra("height", 0);
        double weight = getActivity().getIntent().getDoubleExtra("weight", 0);
        String sex = getActivity().getIntent().getStringExtra("sex");
        double targetweight = getActivity().getIntent().getDoubleExtra("targetweight", 0);
        int targetperiod = getActivity().getIntent().getIntExtra("targetperiod", 0);
        int worklevel = getActivity().getIntent().getIntExtra("worklevel", 0);

        final int reqCalorie = (int) bodyalgo.eatCal(height, weight, age, sex, worklevel, targetweight, targetperiod);
        int regCalorie;
        tv.setText(String.valueOf(reqCalorie) + "kcal");

        mainFragment = new home();
        fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(),FoodselectActivity.class);
                intent.putExtra("id", getActivity().getIntent().getStringExtra("id"));
                startActivity(intent);
                // intent를 이용하여 목록 추가

            }
        });

        if (reqCalorie < 1150) {
            regCalorie = 1100;
            food.setBackgroundResource(R.drawable.menu_1100);
            graph.setImageResource(R.drawable.nut_1100);
        } else if (reqCalorie < 1250 && reqCalorie >= 1150) {
            regCalorie = 1200;
            food.setBackgroundResource(R.drawable.menu_1200);
            graph.setImageResource(R.drawable.nut_1200);
        } else if (reqCalorie < 1350 && reqCalorie >= 1250) {
            regCalorie = 1300;
            food.setBackgroundResource(R.drawable.menu_1300);
            graph.setImageResource(R.drawable.nut_1300);
        } else if (reqCalorie < 1450 && reqCalorie >= 1350) {
            regCalorie = 1400;
            food.setBackgroundResource(R.drawable.menu_1400);
            graph.setImageResource(R.drawable.nut_1400);
        } else if (reqCalorie < 1550 && reqCalorie >= 1450) {
            regCalorie = 1500;
            food.setBackgroundResource(R.drawable.menu_1500);
            graph.setImageResource(R.drawable.nut_1500);
        } else if (reqCalorie < 1650 && reqCalorie >= 1550) {
            regCalorie = 1600;
            food.setBackgroundResource(R.drawable.menu_1600);
            graph.setImageResource(R.drawable.nut_1600);
        } else if (reqCalorie < 1750 && reqCalorie >= 1650) {
            regCalorie = 1700;
            food.setBackgroundResource(R.drawable.menu_1700);
            graph.setImageResource(R.drawable.nut_1700);
        } else if (reqCalorie < 1850 && reqCalorie >= 1750) {
            regCalorie = 1800;
            food.setBackgroundResource(R.drawable.menu_1800);
            graph.setImageResource(R.drawable.nut_1800);
        } else if (reqCalorie < 1950 && reqCalorie >= 1850) {
            regCalorie = 1900;
            food.setBackgroundResource(R.drawable.menu_1900);
            graph.setImageResource(R.drawable.nut_1900);
        } else {
            regCalorie = 2000;
            food.setBackgroundResource(R.drawable.menu_2000);
            graph.setImageResource(R.drawable.nut_2000);

        }

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(graph.getVisibility() == View.VISIBLE)
                    graph.setVisibility(getView().INVISIBLE);
                else
                    graph.setVisibility(getView().VISIBLE);

            }
        });

        final int finalRegCalorie = regCalorie;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectThread thread = new ConnectThread(urlStr, getActivity().getIntent().getStringExtra("id"), finalRegCalorie);
                thread.start();
            }
        });
        return v;
    }

    class ConnectThread extends Thread {
        String urlStr;
        String id;
        String workoutday;
        int eat_calories;

        public ConnectThread(String inStr, String id, int eat_calories) {
            this.urlStr = inStr;
            this.id = id;
            this.eat_calories = eat_calories;

        }

        public void run() {
            try {
                final String output = request(urlStr, id, eat_calories);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity().getBaseContext(), eat_calories + " kcal가 등록되었습니다..", Toast.LENGTH_LONG).show();
                    }
                });
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        private String request(String urlStr, String id, int eat_calories) throws IOException {
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
                    jsonObject.put("eat_calories", eat_calories);

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