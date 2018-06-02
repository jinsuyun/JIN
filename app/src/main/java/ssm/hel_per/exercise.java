package ssm.hel_per;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import static android.os.SystemClock.sleep;
import static com.facebook.FacebookSdk.getApplicationContext;

public class exercise extends Fragment {
    String id;
    View v,v2;
    Button button;
    CountDownTimer countDownTimer;
    public int MILLISINFUTURE=11*1000;
    public int COUNT_DOWN_INTERVAL=1000;
    TextView countTxt;
    public int count=10;
    ImageView urltest,cycle;
    VideoView video;
    static final String VIDEO_URL = "https://ko.gl/youtube.php?download=aHR0cHM6Ly9yMy0tLXNuLWE1bWVrbnN5Lmdvb2dsZXZpZGVvLmNvbS92aWRlb3BsYXliYWNrP3JhdGVieXBhc3M9eWVzJmVpPU9JUVNXNWJ4SFlqRS1BT3J3NFRJREEmaXBiaXRzPTAmZHVyPTI1Ny41NTUmc291cmNlPXlvdXR1YmUmc3BhcmFtcz1kdXIlMkNlaSUyQ2lkJTJDaW5pdGN3bmRicHMlMkNpcCUyQ2lwYml0cyUyQ2l0YWclMkNsbXQlMkNtaW1lJTJDbW0lMkNtbiUyQ21zJTJDbXYlMkNwbCUyQ3JhdGVieXBhc3MlMkNyZXF1aXJlc3NsJTJDc291cmNlJTJDZXhwaXJlJnJlcXVpcmVzc2w9eWVzJmxtdD0xNTA4MzA2MzY0NTQ0NDM2Jm10PTE1Mjc5NDAwNjAmaWQ9by1BT0VrYUVVaE0zdWhkVmg4T1V3d1lNODRNMHJVVmtNLXBvTWJkWXpQMGZhZiZtcz1hdSUyQ29uciZmdmlwPTMmcGw9MjMmaW5pdGN3bmRicHM9MTY1Mzc1MCZtdj1tJmV4cGlyZT0xNTI3OTYxNzUyJnNpZ25hdHVyZT1FMEM1RTJGNEE0MUFGMjQ2QjU1RDY5NUNFQjZFODFDODQ5MjlGNTc4LjJDRjZENjhCN0Y5NkE3RjI5Njc0MDE3NTMzQ0RDRUJENEUyRDZFMDcmYz1XRUImbWltZT12aWRlbyUyRm1wNCZpcD0yMDkuMTQxLjM0LjIzOSZrZXk9eXQ2Jml0YWc9MjImbW09MzElMkMyNiZtbj1zbi1hNW1la25zeSUyQ3NuLW40djdrbmxsJnRpdGxlPSVFRCU4QyU5NCVFQSVCNSVCRCVFRCU5OCU4MCVFRCU4RSVCNCVFQSVCOCVCMCslRUMlOUQlOTgrJUVDJUEwJTk1JUVDJTg0JTlELislRUElQjAlODAlRUMlOUUlQTUrXyslRUMlOTklODQlRUIlQjIlQkQlRUQlOTUlOUMrJUVEJTkxJUI4JUVDJTg5JUFDJUVDJTk3JTg1K18rJUVDJTlFJTkwJUVDJTg0JUI4KyVFQiVCMCVCMCVFQyU5QSVCMCVFQSVCOCVCMCslRUElQjUlOTAlRUIlQjMlQjgrJmtlZXBhbGl2ZT15ZXM=&title=%ED%8C%94%EA%B5%BD%ED%98%80%ED%8E%B4%EA%B8%B0%20%EC%9D%98%20%EC%A0%95%EC%84%9D.%20%EA%B0%80%EC%9E%A5%20%22%20%EC%99%84%EB%B2%BD%ED%95%9C%20%ED%91%B8%EC%89%AC%EC%97%85%20%22%20%EC%9E%90%EC%84%B8%20%EB%B0%B0%EC%9A%B0%EA%B8%B0%20%EA%B5%90%EB%B3%B8";

    public static String urlStr = "http://13.209.40.50:3000/daily"; // 웹


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.exercise, container, false);
        v2 = inflater.inflate(R.layout.exercise_timer, container, false);

        id = getActivity().getIntent().getStringExtra("id");
        button = (Button)v.findViewById(R.id.exercise);

        countTxt = (TextView)v.findViewById(R.id.count);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),exercise_cycle.class);
                startActivity(intent);
            }
        });

        cycle = (ImageView)v.findViewById(R.id.cycle);
        cycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());

                dialog.setContentView(R.layout.cycleinformation);
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
                ConnectThread thread = new exercise.ConnectThread(urlStr, id);
                thread.start();
            }
        });

        return v;
    }

    class ConnectThread extends Thread {
        String urlStr;
        String id;
        Date workoutday;
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
                        Toast.makeText(getApplicationContext(), "등록 성공!", Toast.LENGTH_LONG).show();

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
                workoutday=new Date(now);

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