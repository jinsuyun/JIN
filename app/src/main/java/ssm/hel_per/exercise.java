package ssm.hel_per;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.airbnb.lottie.L;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.support.constraint.Constraints.TAG;
import static com.facebook.FacebookSdk.getApplicationContext;

public class exercise extends Fragment {
    String id;
    View v;
    Button button;
    CountDownTimer countDownTimer;
    public int MILLISINFUTURE=11*1000;
    public int COUNT_DOWN_INTERVAL=1000;
    TextView countTxt;
    public int count=10;
    ImageView urltest,exer1,exer2,exer3,exer4,exer5;
    VideoView video;
    String bt;
    static final String VIDEO_URL = "https://ko.gl/youtube.php?download=aHR0cHM6Ly9yMi0tLXNuLWE1bWVrbmVsLmdvb2dsZXZpZGVvLmNvbS92aWRlb3BsYXliYWNrP2R1cj0zNC44MjkmZXhwaXJlPTE1MjgwNjA2Njkmc291cmNlPXlvdXR1YmUmbG10PTE0NzEwNTI1NTM5NDY3MzkmZWk9blFZVVctejBHTV9vLVFPV3VLckFCQSZpZD1vLUFIWUpuUWUxNUJXQjVnR1dtT19wVlFZYkFST2tacDVlelNnT1lKNkRFWjE0Jm1zPWF1JTJDcmR1Jm10PTE1MjgwMzg5NDkmbXY9bSZyYXRlYnlwYXNzPXllcyZpcGJpdHM9MCZmdmlwPTQmbW09MzElMkMyOSZzaWduYXR1cmU9NUJDQ0RBQkNERTc0NTU1Mzc1MkVFQjAyOUQ5QjM3MDZEMEI2MkYzOC4xMUJEQzhCRDYwOTcyQUE1NTIzRDA0QjQ1N0UwNDE3NjQwQkRDQ0JFJm1uPXNuLWE1bWVrbmVsJTJDc24tYTVtN2xubHomcmVxdWlyZXNzbD15ZXMma2V5PXl0NiZpcD0yMDkuMTQxLjM0LjIzOSZwbD0yMyZtaW1lPXZpZGVvJTJGbXA0JmluaXRjd25kYnBzPTY5NjI1MCZpdGFnPTIyJmM9V0VCJnNwYXJhbXM9ZHVyJTJDZWklMkNpZCUyQ2luaXRjd25kYnBzJTJDaXAlMkNpcGJpdHMlMkNpdGFnJTJDbG10JTJDbWltZSUyQ21tJTJDbW4lMkNtcyUyQ212JTJDcGwlMkNyYXRlYnlwYXNzJTJDcmVxdWlyZXNzbCUyQ3NvdXJjZSUyQ2V4cGlyZSZ0aXRsZT0lRUQlOTQlOEMlRUIlOUUlQUQlRUQlODElQUMrJUVDJTlBJUI0JUVCJThGJTk5KyZrZWVwYWxpdmU9eWVz&title=%ED%94%8C%EB%9E%AD%ED%81%AC%20%EC%9A%B4%EB%8F%99";
    public static String urlStr = "http://13.209.40.50:3000/daily"; // 웹

    //Date workoutday=null;
    int running_time = 0;
    int weight_time = 0;
    int arm = 0;
    int back = 0;
    int shoulder = 0;
    int chest = 0;
    int leg = 0;
    int sixpack = 0;
    int eat_calories = 0;
    int all_eat_calories = 0;
    int spent_calories = 0;
    int all_spent_calories = 0;
    Double weight = 0d;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.exercise, container, false);

        bt = getActivity().getIntent().getStringExtra("bodytype");
        Log.d(TAG,"FUCKING"+bt);

        exer1 = (ImageView)v.findViewById(R.id.exer_opt1);
        exer2 = (ImageView)v.findViewById(R.id.exer_opt2);
        exer3 = (ImageView)v.findViewById(R.id.exer_opt3);
        exer4 = (ImageView)v.findViewById(R.id.exer_opt4);
        exer5 = (ImageView)v.findViewById(R.id.exer_opt5);

        id = getActivity().getIntent().getStringExtra("id");

        button = (Button)v.findViewById(R.id.exercise);

        if(bt!=null) {
            if (bt.equals("LW") || bt.equals("LF") || bt.equals("LB") || bt.equals("SB") || bt.equals("SW")) {
                exer1.setImageResource(R.drawable.pushup);
                exer2.setImageResource(R.drawable.pullup);
                exer3.setImageResource(R.drawable.squat);
                exer4.setImageResource(R.drawable.dumbel);
                exer5.setImageResource(R.drawable.cycle);
            } else {
                exer1.setImageResource(R.drawable.buffet);
                exer2.setImageResource(R.drawable.running3km);
                exer3.setImageResource(R.drawable.cycle);
                exer4.setImageResource(R.drawable.jumprope);
                exer5.setImageResource(R.drawable.plank);
            }
        }

        countTxt = (TextView)v.findViewById(R.id.count);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),exercise_buffet.class);
                ConnectThread thread = new exercise.ConnectThread(urlStr, id);
                thread.start();
                startActivity(intent);
            }
        });

        exer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());

                dialog.setContentView(R.layout.plankinformation);
                dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                dialog.show();
            }
        });

        urltest = (ImageView)v.findViewById(R.id.test1);
        urltest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());

                dialog.setContentView(R.layout.exercise_dialog);
                dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                video=(VideoView)dialog.findViewById(R.id.test_video);
                video.seekTo(0);
                video.start();
                MediaController mc = new MediaController(getActivity());
                video.setMediaController(mc);
                video.setVideoURI(Uri.parse(VIDEO_URL));
                video.requestFocus();
                dialog.show();

            }
        });



        return v;
    }

    class ConnectThread extends Thread {
        String urlStr;
        String id;
        String workoutday;
        String running_time = "68";
        String weight_time = "40";
        String arm = "3";
        String back = "5";
        String shoulder = "2";
        String chest = "8";
        String leg = "5";
        String sixpack = "4";
        String eat_calories = "493";
        String all_eat_calories = "1200";
        String spent_calories = "395";
        String all_spent_calories = "694";
        String weight = "74";

        Handler handler = new Handler();

        public ConnectThread(String inStr, String id) {
            this.urlStr = inStr;
            this.id = id;
        }

        public void run() {
            try {
                final String output = request(urlStr, id);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(getApplicationContext(), "등록 성공!", Toast.LENGTH_LONG).show();

                    }
                });
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        private String request(String urlStr, String id) throws IOException {
            StringBuilder output = new StringBuilder();
            long now = System.currentTimeMillis();

            try {
                URL url = new URL(urlStr);
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