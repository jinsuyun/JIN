package ssm.hel_per;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.support.v7.app.AlertDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import ssm.hel_per.exercise_all.exercise_obesity.obesity_lv1.exercise_jumprope;
import ssm.hel_per.exercise_all.exercise_obesity.obesity_lv2.exercise_running3km;
import ssm.hel_per.exercise_all.exercise_standard.standard_lv1.exercise_handstand_pushup;
import ssm.hel_per.exercise_all.exercise_standard.standard_lv2.exercise_pullup;
import ssm.hel_per.exercise_all.exercise_weak.weak_lv1.exercise_knee_pushup;
import ssm.hel_per.exercise_all.exercise_weak.weak_lv2.exercise_wall_pushup;

import static android.support.constraint.Constraints.TAG;
import static ssm.hel_per.bodyAlgo.targetCal;

public class exercise extends Fragment implements Main2Activity.OnBackPressedListener{

    String id;
    View v;
    home mainFragment;
    Button button1,re_select;
    Button exercisebtn;
    TextView countTxt;
    ImageView exer1,exer2,exer3,exer4,exer5,url1,url2,url3,url4,url5,iv;
    VideoView video;
    String bt;
    Double weight;
    static final String VIDEO_URL = "https://ko.gl/youtube.php?download=aHR0cHM6Ly9yMi0tLXNuLWE1bWVrbmVsLmdvb2dsZXZpZGVvLmNvbS92aWRlb3BsYXliYWNrP2R1cj0zNC44MjkmZXhwaXJlPTE1MjgwNjA2Njkmc291cmNlPXlvdXR1YmUmbG10PTE0NzEwNTI1NTM5NDY3MzkmZWk9blFZVVctejBHTV9vLVFPV3VLckFCQSZpZD1vLUFIWUpuUWUxNUJXQjVnR1dtT19wVlFZYkFST2tacDVlelNnT1lKNkRFWjE0Jm1zPWF1JTJDcmR1Jm10PTE1MjgwMzg5NDkmbXY9bSZyYXRlYnlwYXNzPXllcyZpcGJpdHM9MCZmdmlwPTQmbW09MzElMkMyOSZzaWduYXR1cmU9NUJDQ0RBQkNERTc0NTU1Mzc1MkVFQjAyOUQ5QjM3MDZEMEI2MkYzOC4xMUJEQzhCRDYwOTcyQUE1NTIzRDA0QjQ1N0UwNDE3NjQwQkRDQ0JFJm1uPXNuLWE1bWVrbmVsJTJDc24tYTVtN2xubHomcmVxdWlyZXNzbD15ZXMma2V5PXl0NiZpcD0yMDkuMTQxLjM0LjIzOSZwbD0yMyZtaW1lPXZpZGVvJTJGbXA0JmluaXRjd25kYnBzPTY5NjI1MCZpdGFnPTIyJmM9V0VCJnNwYXJhbXM9ZHVyJTJDZWklMkNpZCUyQ2luaXRjd25kYnBzJTJDaXAlMkNpcGJpdHMlMkNpdGFnJTJDbG10JTJDbWltZSUyQ21tJTJDbW4lMkNtcyUyQ212JTJDcGwlMkNyYXRlYnlwYXNzJTJDcmVxdWlyZXNzbCUyQ3NvdXJjZSUyQ2V4cGlyZSZ0aXRsZT0lRUQlOTQlOEMlRUIlOUUlQUQlRUQlODElQUMrJUVDJTlBJUI0JUVCJThGJTk5KyZrZWVwYWxpdmU9eWVz&title=%ED%94%8C%EB%9E%AD%ED%81%AC%20%EC%9A%B4%EB%8F%99";

    public static String urlStr = "http://13.209.40.50:3000/appuser"; // 웹
    RelativeLayout imagestack;
    public RelativeLayout R_image,Rela_exer5;
    public CharSequence[] items ={"1.유산소", "2.웨이트", "3.무작위"};
    public  CharSequence[] weighttrain={"1.가슴","2.등","3.어깨","4.하체","5.팔","6.복근"};
    public AlertDialog.Builder builder;

    RelativeLayout Layout;
    int test;
    int workperiod=0;
    int exerlevel;
    int exercount;
    double targetweight;
    int targetperiod;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.exercise, container, false);
        exer1 = v.findViewById(R.id.exer_opt1);exer2 = v.findViewById(R.id.exer_opt2);exer3 = v.findViewById(R.id.exer_opt3);exer4 = v.findViewById(R.id.exer_opt4);exer5 = v.findViewById(R.id.exer_opt5);
        Rela_exer5= v.findViewById(R.id.exercise_5);
        Rela_exer5.setVisibility(View.VISIBLE);

        mainFragment = new home();

        workperiod = getActivity().getIntent().getIntExtra("workperiod",0);
        id = getActivity().getIntent().getStringExtra("id");
        weight=getActivity().getIntent().getDoubleExtra("weight",0);
        bt = getActivity().getIntent().getStringExtra("bodytype");
        exerlevel=getActivity().getIntent().getIntExtra("exerlevel",0);
        exercount=getActivity().getIntent().getIntExtra("exercount",0);

        targetweight = getActivity().getIntent().getDoubleExtra("targetweight", 0);
        targetperiod = getActivity().getIntent().getIntExtra("targetperiod", 0);

        exercisebtn=v.findViewById(R.id.exercise);
        button1 = v.findViewById(R.id.part);
        R_image = v.findViewById(R.id.imagestack);

       // exerlevel = getArguments().getInt("exerlevel",0);
        ConnectThread thread = new ConnectThread(urlStr, id, exercount);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result=thread.getResult();

        try {
            JSONArray ary = new JSONArray(result);
            JSONObject jsonObject = ary.getJSONObject(0);
            exercount = jsonObject.getInt("exercount");
            exerlevel = jsonObject.getInt("exerlevel");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(bt!=null) {
            if (bt.equals("LW"))
                Layout=lwRestore(exerlevel);
            else if (bt.equals("SW"))
                Layout=swRestore(exerlevel);
            else if (bt.equals("OB"))
                Layout=obRestore(exerlevel);
            else if (bt.equals("SF"))
                Layout=sfRestore(exerlevel);
            else if (bt.equals("OF"))
                Layout=ofRestore(exerlevel);
            else if (bt.equals("LB"))
                Layout=lbRestore(exerlevel);
            else if (bt.equals("SB"))
                Layout=sbRestore(exerlevel);
            else if (bt.equals("SS"))
                Layout=ssRestore(exerlevel);
            else if (bt.equals("OS"))
                Toast.makeText(getActivity(), "Hel_per를 사용할만한 수준이 아닙니다", Toast.LENGTH_LONG).show();
        }

        iv = v.findViewById(R.id.exercise_image);

        builder = new AlertDialog.Builder(getActivity());
        countTxt = v.findViewById(R.id.count);

        exercisebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =null;
                if (bt.equals("LW")) {
                    if (exerlevel == 1) {
                        intent = new Intent(getActivity(), exercise_knee_pushup.class);
                        intent.putExtra("id", id);
                        intent.putExtra("weight",weight);
                    }
                    else {
                        intent = new Intent(getActivity(), exercise_wall_pushup.class);
                        intent.putExtra("id", id);
                        intent.putExtra("weight",weight);
                    }
                }
                else if (bt.equals("SW")) {
                    if (exerlevel == 1) {
                        intent = new Intent(getActivity(), exercise_knee_pushup.class);
                        intent.putExtra("id", id);
                        intent.putExtra("weight",weight);
                    }
                    else {
                        intent = new Intent(getActivity(), exercise_wall_pushup.class);
                        intent.putExtra("id", id);
                        intent.putExtra("weight",weight);
                    }
                }
                else if (bt.equals("OB")) {
                    if (exerlevel == 1) {
                        intent = new Intent(getActivity(), exercise_knee_pushup.class);
                        intent.putExtra("id", id);
                        intent.putExtra("weight",weight);
                    }
                    else {
                        intent = new Intent(getActivity(), exercise_wall_pushup.class);
                        intent.putExtra("id", id);
                        intent.putExtra("weight",weight);
                        }
                    }
                else if (bt.equals("SF")) {
                    if (exerlevel == 1) {
                        intent = new Intent(getActivity(), exercise_knee_pushup.class);
                        intent.putExtra("id", id);
                        intent.putExtra("weight",weight);
                    }
                    else {
                        intent = new Intent(getActivity(), exercise_wall_pushup.class);
                        intent.putExtra("id", id);
                        intent.putExtra("weight",weight);
                    }
                }
                else if (bt.equals("OF")) {
                    if (exerlevel == 1) {
                        intent = new Intent(getActivity(), exercise_jumprope.class);
                        intent.putExtra("id", id);
                        intent.putExtra("weight",weight);
                    }
                    else {
                        intent = new Intent(getActivity(), exercise_running3km.class);
                        intent.putExtra("id", id);
                        intent.putExtra("weight",weight);
                    }
                }
                else if (bt.equals("LB")) {
                    if (exerlevel == 1) {
                        intent = new Intent(getActivity(), exercise_knee_pushup.class);
                        intent.putExtra("id", id);
                        intent.putExtra("weight",weight);
                    }
                    else {
                        intent = new Intent(getActivity(), exercise_wall_pushup.class);
                        intent.putExtra("id", id);
                        intent.putExtra("weight",weight);
                    }
                }
                else if (bt.equals("SB")) {
                    if (exerlevel == 1) {
                        intent = new Intent(getActivity(), exercise_handstand_pushup.class);
                        intent.putExtra("id", id);
                        intent.putExtra("weight",weight);
                    }
                    else {
                        intent = new Intent(getActivity(), exercise_pullup.class);
                        intent.putExtra("id", id);
                        intent.putExtra("weight",weight);
                    }
                }
                else if (bt.equals("SS")) {
                    if (exerlevel == 1) {
                        intent = new Intent(getActivity(), exercise_knee_pushup.class);
                        intent.putExtra("id", id);
                        intent.putExtra("weight",weight);
                    }
                    else {
                        intent = new Intent(getActivity(), exercise_wall_pushup.class);
                        intent.putExtra("id", id);
                        intent.putExtra("weight",weight);
                    }
                }
                else if (bt.equals("OS")) {
                    if (exerlevel == 1) {
                        intent = new Intent(getActivity(), exercise_knee_pushup.class);
                        intent.putExtra("id", id);
                        intent.putExtra("weight",weight);
                    }
                    else {
                        intent = new Intent(getActivity(), exercise_wall_pushup.class);
                        intent.putExtra("id", id);
                        intent.putExtra("weight",weight);
                    }
                }
                startActivity(intent);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("오늘은 어떤 운동을 하시겠습니까?")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //lwRestore(workperiod);swRestore(workperiod);obRestore(workperiod);sfRestore(workperiod);lbRestore(workperiod);sbRestore(workperiod);ssRestore(workperiod);
                                //RelativeLayout[] ran={routine1,routine2,routine3,routine4,routine5,routine6,routine7};
                                //test=(int)(Math.random()*7);

                                switch(which)
                                {
                                    case 0: // 유산소
                                        aerobicRestore(workperiod);
                                        break;
                                    case 1: // 웨이트
                                        builder.setTitle("운동부위를 선택하세요")
                                                .setItems(weighttrain, new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        switch(which) {
                                                            case 0: // 가슴
                                                                Layout = chestRestore(workperiod);
                                                                break;
                                                            case 1: // 등
                                                                Layout = backRestore(workperiod);
                                                                break;
                                                            case 2: // 어깨
                                                                Layout = shoulderRestore(workperiod);
                                                                break;
                                                            case 3: // 하체
                                                                Layout = legRestore(workperiod);
                                                                break;
                                                            case 4: // 팔
                                                                Layout = armRestore(workperiod);
                                                                break;
                                                            case 5: // 복근
                                                                Layout = abdoRestore(workperiod);
                                                                break;
                                                        }
                                                    }
                                                }).show();
                                        break;
                                    case 2: // 무작위
                                        Random rnd = new Random();
                                        rnd.setSeed(System.currentTimeMillis());
                                        test=(int)(Math.random() * 8);
                                        Log.d(TAG,"test"+test);
                                        switch(test){
                                            case 0:
                                                Layout = lwRestore(workperiod);
                                                break;
                                            case 1:
                                                Layout = swRestore(workperiod);
                                                break;
                                            case 2:
                                                Layout = obRestore(workperiod);
                                                break;
                                            case 3:
                                                Layout = sfRestore(workperiod);
                                                break;
                                            case 4:
                                                Layout = lbRestore(workperiod);
                                                break;
                                            case 5:
                                                Layout = sbRestore(workperiod);
                                                break;
                                            case 6:
                                                Layout = ssRestore(workperiod);
                                                break;
                                            case 7:
                                                Layout = ofRestore(workperiod);
                                                break;
                                        }
                                }
                            }
                        }).show();
            }
        });

        return v;
    }

    class ConnectThread extends Thread {
        String urlStr;
        String id;
        int exercount;
        String result;

        public ConnectThread(String inStr, String id,int exercount) {
            this.urlStr = inStr;
            this.id = id;
            this.exercount=exercount;
        }

        public void run() {
            try {
                final String output = request(urlStr, id,exerlevel);
                result=output;
                JSONObject json = new JSONObject(output);
                exercount = json.getInt("exercount");
                exerlevel = json.getInt("exerlevel");

            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        private String request(String urlStr, String id,int exercount) throws IOException {
            StringBuilder output = new StringBuilder();
            long now = System.currentTimeMillis();

            try {
                URL url = new URL(urlStr);

                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                if(conn != null) {
                    String json = "";

                    // build jsonObject
                    JSONObject jsonObject = new JSONObject();

                    jsonObject.put("id", id);
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
        public String getResult() {
            return result;
        }
    }
    TextView tv1 = v.findViewById(R.id.textView5); TextView tv2 = v.findViewById(R.id.textView10); TextView tv3 = v.findViewById(R.id.textView12);  TextView tv4 = v.findViewById(R.id.textView13); TextView tv5 = v.findViewById(R.id.textView14);

    public RelativeLayout aerobicRestore(int level){ // 유산소
        RelativeLayout imagestack = v.findViewById(R.id.imagestack);
        url1=v.findViewById(R.id.exer_url1);url2=v.findViewById(R.id.exer_url2);url3=v.findViewById(R.id.exer_url3);url4=v.findViewById(R.id.exer_url4);url5=v.findViewById(R.id.exer_url5);
        exer1 = v.findViewById(R.id.exer_opt1);exer2 = v.findViewById(R.id.exer_opt2);exer3 = v.findViewById(R.id.exer_opt3);exer4 = v.findViewById(R.id.exer_opt4);exer5 = v.findViewById(R.id.exer_opt5);
        if(level==1) {
            exer1.setImageResource(R.drawable.running3km);
            exer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.running_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=rqH4EpLZf2g"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer2.setImageResource(R.drawable.jumprope);
            exer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.jumprope_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=RjMCLiw8yRQ"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer3.setImageResource(R.drawable.buffet);
            exer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.buffet_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Uukm_JpUcRg"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer4.setImageResource(R.drawable.step);
            exer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.step_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=tkj7N3xT-_U&t=1s"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            Rela_exer5.setVisibility(View.GONE);
        }else {
            exer1.setImageResource(R.drawable.running5km);
            exer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.running_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=rqH4EpLZf2g"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer2.setImageResource(R.drawable.buffet);
            exer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.buffet_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Uukm_JpUcRg"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer3.setImageResource(R.drawable.cycle);
            exer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.cycle_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=OxNXE4jwy0w"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer4.setImageResource(R.drawable.plank);
            exer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.plank_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=iNdDN4GKtHk&t=9s"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            Rela_exer5.setVisibility(View.GONE);
        }
        return imagestack;
    }
    public RelativeLayout chestRestore(int level){
        RelativeLayout imagestack = (RelativeLayout)v.findViewById(R.id.imagestack);
        url1=v.findViewById(R.id.exer_url1);url2=v.findViewById(R.id.exer_url2);url3=v.findViewById(R.id.exer_url3);url4=v.findViewById(R.id.exer_url4);url5=v.findViewById(R.id.exer_url5);
        exer1 = v.findViewById(R.id.exer_opt1);exer2 = v.findViewById(R.id.exer_opt2);exer3 = v.findViewById(R.id.exer_opt3);exer4 = v.findViewById(R.id.exer_opt4);exer5 = v.findViewById(R.id.exer_opt5);
        if(level==1){
            exer1.setImageResource(R.drawable.knee_pushup);
            exer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.pushup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=LiKfLE2K_DI"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer2.setImageResource(R.drawable.wall_pushup);
            exer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.pushup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=JsVKv06CWD4"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer3.setImageResource(R.drawable.wide_pushup);
            exer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.pushup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=rr6eFNNDQdU&t=10s"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer4.setImageResource(R.drawable.fly_3kg);
            exer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.fly_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=VANutvcSOwc"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            Rela_exer5.setVisibility(View.GONE);
        } else {
            exer1.setImageResource(R.drawable.knee_pushup);
            exer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.pushup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=LiKfLE2K_DI"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer2.setImageResource(R.drawable.narrow_pushup);
            exer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.pushup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=40LMvyHkUKw"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer3.setImageResource(R.drawable.incline_pushup);
            exer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.pushup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Z0bRiVhnO8Q"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer4.setImageResource(R.drawable.fly_5kg);
            exer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.fly_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=VANutvcSOwc"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            Rela_exer5.setVisibility(View.GONE);
        }
        return imagestack;
    }
    public RelativeLayout backRestore(int level){
        RelativeLayout imagestack = (RelativeLayout)v.findViewById(R.id.imagestack);
        url1=v.findViewById(R.id.exer_url1);url2=v.findViewById(R.id.exer_url2);url3=v.findViewById(R.id.exer_url3);url4=v.findViewById(R.id.exer_url4);url5=v.findViewById(R.id.exer_url5);
        exer1 = v.findViewById(R.id.exer_opt1);exer2 = v.findViewById(R.id.exer_opt2);exer3 = v.findViewById(R.id.exer_opt3);exer4 = v.findViewById(R.id.exer_opt4);exer5 = v.findViewById(R.id.exer_opt5);
        if(level==1){
            exer1.setImageResource(R.drawable.jump_pullup);
            exer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.pushup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=fKe5S1dd0fw"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer2.setImageResource(R.drawable.bentover_3);
            exer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.bentover_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=LktGPg-AkvY"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer3.setImageResource(R.drawable.onearm_3);
            exer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.onearm_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=ovTHP1MZbZI&t=13s"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer4.setImageResource(R.drawable.backex);
            exer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.backextension_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=ph3pddpKzzw"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            Rela_exer5.setVisibility(View.GONE);
        } else {
            exer1.setImageResource(R.drawable.bentover_5);
            exer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.bentover_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=LktGPg-AkvY"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer2.setImageResource(R.drawable.pullup);
            exer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.pullup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=eGo4IYlbE5g]"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer3.setImageResource(R.drawable.onearm_5);
            exer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.onearm_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=ovTHP1MZbZI&t=13s"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer4.setImageResource(R.drawable.backex);
            exer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.backextension_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(" https://www.youtube.com/watch?v=ph3pddpKzzw"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            Rela_exer5.setVisibility(View.GONE);
        }
        return imagestack;
    }
    public RelativeLayout shoulderRestore(int level){
        RelativeLayout imagestack = (RelativeLayout)v.findViewById(R.id.imagestack);
        url1=v.findViewById(R.id.exer_url1);url2=v.findViewById(R.id.exer_url2);url3=v.findViewById(R.id.exer_url3);url4=v.findViewById(R.id.exer_url4);url5=v.findViewById(R.id.exer_url5);
        exer1 = v.findViewById(R.id.exer_opt1);exer2 = v.findViewById(R.id.exer_opt2);exer3 = v.findViewById(R.id.exer_opt3);exer4 = v.findViewById(R.id.exer_opt4);exer5 = v.findViewById(R.id.exer_opt5);
        if(level==1){
            exer1.setImageResource(R.drawable.pike_pushup);
            exer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.pushup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=sposDXWEB0A&t=37s"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer2.setImageResource(R.drawable.sitdum_3);
            exer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.sitdum_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=lfb3ffbrd4Q"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer3.setImageResource(R.drawable.plank);
            exer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.plank_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=iNdDN4GKtHk&t=9s"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer4.setImageResource(R.drawable.fdum_3);
            exer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.front_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=-t7fuZ0KhDA&t=3s"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            Rela_exer5.setVisibility(View.GONE);
        } else {
            exer1.setImageResource(R.drawable.handstand_pushup);
            exer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.pushup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=iVvEc1hYI3M"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer2.setImageResource(R.drawable.squat);
            exer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.squat_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=mGvzVjuY8SY"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer3.setImageResource(R.drawable.cycle);
            exer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.cycle_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=OxNXE4jwy0w"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer4.setImageResource(R.drawable.lunge);
            exer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.lunge_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=QF0BQS2W80k"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            Rela_exer5.setVisibility(View.GONE);
        }
        return imagestack;
    }
    public RelativeLayout legRestore(int level){
        RelativeLayout imagestack = (RelativeLayout)v.findViewById(R.id.imagestack);
        url1=v.findViewById(R.id.exer_url1);url2=v.findViewById(R.id.exer_url2);url3=v.findViewById(R.id.exer_url3);url4=v.findViewById(R.id.exer_url4);url5=v.findViewById(R.id.exer_url5);
        exer1 = v.findViewById(R.id.exer_opt1);exer2 = v.findViewById(R.id.exer_opt2);exer3 = v.findViewById(R.id.exer_opt3);exer4 = v.findViewById(R.id.exer_opt4);exer5 = v.findViewById(R.id.exer_opt5);
        if(level==1){
            exer1.setImageResource(R.drawable.one_squat);
            exer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.squat_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=9_Ca2YRRdtE"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer2.setImageResource(R.drawable.jump_squat);
            exer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.squat_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=DeTBwEL4m7s&t=14s"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer3.setImageResource(R.drawable.lunge);
            exer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.lunge_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=QF0BQS2W80k"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer4.setImageResource(R.drawable.wide_squat);
            exer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.squat_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=v2ukjHXbXVo"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            Rela_exer5.setVisibility(View.GONE);
        } else {
            exer1.setImageResource(R.drawable.handstand_pushup);
            exer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.pushup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=iVvEc1hYI3M"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer2.setImageResource(R.drawable.sitdum_5);
            exer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.sitdum_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=lfb3ffbrd4Q"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer3.setImageResource(R.drawable.rfum_5);
            exer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.lateral_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=3VcKaXpzqRo"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer4.setImageResource(R.drawable.pike_pushup);
            exer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.pushup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=sposDXWEB0A&t=37s"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            Rela_exer5.setVisibility(View.GONE);
        }
        return imagestack;
    }
    public RelativeLayout armRestore(int level){
        RelativeLayout imagestack = (RelativeLayout)v.findViewById(R.id.imagestack);
        url1=v.findViewById(R.id.exer_url1);url2=v.findViewById(R.id.exer_url2);url3=v.findViewById(R.id.exer_url3);url4=v.findViewById(R.id.exer_url4);url5=v.findViewById(R.id.exer_url5);
        exer1 = v.findViewById(R.id.exer_opt1);exer2 = v.findViewById(R.id.exer_opt2);exer3 = v.findViewById(R.id.exer_opt3);exer4 = v.findViewById(R.id.exer_opt4);exer5 = v.findViewById(R.id.exer_opt5);
        if(level==1){
            exer1.setImageResource(R.drawable.dips);
            exer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.dip_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=2z8JmcrW-As"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer2.setImageResource(R.drawable.extension_1);
            exer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.backextension_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=dFyU0rcUU7U"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer3.setImageResource(R.drawable.dumcurl_1);
            exer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.dumcur_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=sAq_ocpRh_I"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer4.setImageResource(R.drawable.dumkick_1);
            exer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.kickback_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=m9me06UBPKc&t=12s"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            Rela_exer5.setVisibility(View.GONE);
        } else {
            exer1.setImageResource(R.drawable.dips);
            exer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.dip_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=2z8JmcrW-As"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer2.setImageResource(R.drawable.dumcurl_3);
            exer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.dumcur_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=sAq_ocpRh_I"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer3.setImageResource(R.drawable.dumkick_3);
            exer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.kickback_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=m9me06UBPKc&t=12s"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer4.setImageResource(R.drawable.extension_3);
            exer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.backextension_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=dFyU0rcUU7U"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            Rela_exer5.setVisibility(View.GONE);
        }
        return imagestack;
    }
    public RelativeLayout abdoRestore(int level){
        RelativeLayout imagestack = (RelativeLayout)v.findViewById(R.id.imagestack);
        url1=v.findViewById(R.id.exer_url1);url2=v.findViewById(R.id.exer_url2);url3=v.findViewById(R.id.exer_url3);url4=v.findViewById(R.id.exer_url4);url5=v.findViewById(R.id.exer_url5);
        exer1 = v.findViewById(R.id.exer_opt1);exer2 = v.findViewById(R.id.exer_opt2);exer3 = v.findViewById(R.id.exer_opt3);exer4 = v.findViewById(R.id.exer_opt4);exer5 = v.findViewById(R.id.exer_opt5);

        if(level==1){
            exer1.setImageResource(R.drawable.crunch);
            exer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.crunch_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Xyd_fa5zoEU&t=9s"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer2.setImageResource(R.drawable.leg_raise);
            exer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.legraise_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=JB2oyawG9KI"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer3.setImageResource(R.drawable.bicycle);
            exer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.bicycle_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=9FGilxCbdz8"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer4.setImageResource(R.drawable.plank);
            exer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.plank_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=iNdDN4GKtHk&t=9s"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            Rela_exer5.setVisibility(View.GONE);
        } else {
            exer1.setImageResource(R.drawable.vup);
            exer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.vup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=iP2fjvG0g3w"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer2.setImageResource(R.drawable.cross_crunch);
            exer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.crunch_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=bEYv_MMlWhs"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer3.setImageResource(R.drawable.leg_raise);
            exer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.legraise_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=JB2oyawG9KI"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer4.setImageResource(R.drawable.russian_twist);
            exer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.russiantwist_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=wkD8rjkodUI"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            Rela_exer5.setVisibility(View.GONE);
        }
        return imagestack;
    }
    List<Integer> AcountSet = aerobicCountSet(weight,targetweight,targetperiod);
    List<Integer> WcountSet = weightCountSet(bt);
    public RelativeLayout lwRestore(int level){
        RelativeLayout imagestack = (RelativeLayout)v.findViewById(R.id.imagestack);
        url1=v.findViewById(R.id.exer_url1);url2=v.findViewById(R.id.exer_url2);url3=v.findViewById(R.id.exer_url3);url4=v.findViewById(R.id.exer_url4);url5=v.findViewById(R.id.exer_url5);
        exer1 = v.findViewById(R.id.exer_opt1);exer2 = v.findViewById(R.id.exer_opt2);exer3 = v.findViewById(R.id.exer_opt3);exer4 = v.findViewById(R.id.exer_opt4);exer5 = v.findViewById(R.id.exer_opt5);

        if(level==1){
            tv1.setText(WcountSet.get(1)+"회"); tv2.setText(WcountSet.get(1)+"회"); tv3.setText(WcountSet.get(0)+"회");
            tv4.setText(WcountSet.get(3)+"회"); tv5.setText(AcountSet.get(0)+"걸음");
            exer1.setImageResource(R.drawable.knee_pushup);
            exer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.pushup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=LiKfLE2K_DI"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer2.setImageResource(R.drawable.fly_3kg);
            exer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.fly_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=VANutvcSOwc"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer3.setImageResource(R.drawable.jump_pullup);
            exer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.pullup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=fKe5S1dd0fw"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer4.setImageResource(R.drawable.pike_pushup);
            exer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.pushup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=sposDXWEB0A"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer5.setImageResource(R.drawable.step);
            exer5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.step_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=tkj7N3xT-_U"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });

        } else {
            tv1.setText(WcountSet.get(1)+"회"); tv2.setText(WcountSet.get(3)+"회"); tv3.setText(WcountSet.get(4)+"회");
            tv4.setText(WcountSet.get(2)+"회"); tv5.setText(AcountSet.get(3)+"초");
            exer1.setImageResource(R.drawable.wall_pushup);
            exer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.pushup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=JsVKv06CWD4"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer2.setImageResource(R.drawable.bentover_3);
            exer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.bentover_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=LktGPg-AkvY"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer3.setImageResource(R.drawable.dumcurl_1);
            exer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.dumcur_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=zC3nLlEvin4"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer4.setImageResource(R.drawable.squat);
            exer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.squat_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=lnJPcQwRURY"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer5.setImageResource(R.drawable.plank);
            exer5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.plank_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=iNdDN4GKtHk"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });

        }
        return imagestack;
    }
    public RelativeLayout swRestore(int level){
        RelativeLayout imagestack = (RelativeLayout)v.findViewById(R.id.imagestack);
        url1=v.findViewById(R.id.exer_url1);url2=v.findViewById(R.id.exer_url2);url3=v.findViewById(R.id.exer_url3);url4=v.findViewById(R.id.exer_url4);url5=v.findViewById(R.id.exer_url5);
        exer1 = v.findViewById(R.id.exer_opt1);exer2 = v.findViewById(R.id.exer_opt2);exer3 = v.findViewById(R.id.exer_opt3);exer4 = v.findViewById(R.id.exer_opt4);exer5 = v.findViewById(R.id.exer_opt5);

        if(level==1){
            tv1.setText(WcountSet.get(1)+"회"); tv2.setText(WcountSet.get(0)+"회"); tv3.setText(WcountSet.get(0)+"회");
            tv4.setText(WcountSet.get(3)+"회"); tv5.setText(AcountSet.get(4)+"회");
            exer1.setImageResource(R.drawable.wide_pushup);
            exer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.pushup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=rr6eFNNDQdU"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer2.setImageResource(R.drawable.onearm_3);
            exer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.onearm_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=ovTHP1MZbZI"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer3.setImageResource(R.drawable.sitdum_3);
            exer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.sitdum_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=BsULGO70tcU"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer4.setImageResource(R.drawable.squat);
            exer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.squat_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=lnJPcQwRURY"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer5.setImageResource(R.drawable.knee_dips);
            exer5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.dip_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=JO85XZqDgEY"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
        } else {
            tv1.setText(WcountSet.get(1)+"회"); tv2.setText(WcountSet.get(0)+"회"); tv3.setText(WcountSet.get(0)+"회");
            tv4.setText(WcountSet.get(2)+"회"); tv5.setText(AcountSet.get(1)+"회");
            exer1.setImageResource(R.drawable.pushup);
            exer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.pushup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=-_DUjHxgmWk"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer2.setImageResource(R.drawable.fly_5kg);
            exer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.fly_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=VANutvcSOwc"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer3.setImageResource(R.drawable.fdum_3);
            exer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.front_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=-t7fuZ0KhDA"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer4.setImageResource(R.drawable.jump_squat);
            exer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.squat_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=DeTBwEL4m7s"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer5.setImageResource(R.drawable.dumkick_1);
            exer5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.kickback_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=m9me06UBPKc"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
        }
        return imagestack;
    }

    public RelativeLayout lbRestore(int level){
        RelativeLayout imagestack = (RelativeLayout)v.findViewById(R.id.imagestack);
        url1=v.findViewById(R.id.exer_url1);url2=v.findViewById(R.id.exer_url2);url3=v.findViewById(R.id.exer_url3);url4=v.findViewById(R.id.exer_url4);url5=v.findViewById(R.id.exer_url5);
        exer1 = v.findViewById(R.id.exer_opt1);exer2 = v.findViewById(R.id.exer_opt2);exer3 = v.findViewById(R.id.exer_opt3);exer4 = v.findViewById(R.id.exer_opt4);exer5 = v.findViewById(R.id.exer_opt5);

        if(level==1){
            tv1.setText(WcountSet.get(1)+"회"); tv2.setText(WcountSet.get(0)+"회"); tv3.setText(WcountSet.get(0)+"회");
            tv4.setText(WcountSet.get(3)+"회"); tv5.setText(AcountSet.get(4)+"분");
            exer1.setImageResource(R.drawable.narrow_pushup);
            exer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.pushup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=40LMvyHkUKw"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer2.setImageResource(R.drawable.jump_pullup);
            exer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.pullup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=fKe5S1dd0fw"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer3.setImageResource(R.drawable.step);
            exer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.step_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=tkj7N3xT-_U&t=1s"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer4.setImageResource(R.drawable.buffet);
            exer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.buffet_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Uukm_JpUcRg"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer5.setImageResource(R.drawable.running5km);
            exer5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.running_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=rqH4EpLZf2g"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
        } else {
            tv1.setText(WcountSet.get(1)+"회"); tv2.setText(WcountSet.get(1)+"회"); tv3.setText(AcountSet.get(3)+"초");
            tv4.setText(WcountSet.get(3)+"회"); tv5.setText(AcountSet.get(4)+"회");
            exer1.setImageResource(R.drawable.wall_pushup);
            exer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.pushup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=JsVKv06CWD4"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer2.setImageResource(R.drawable.pike_pushup);
            exer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.pushup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=sposDXWEB0A&t=37s"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer3.setImageResource(R.drawable.plank);
            exer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.plank_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=iNdDN4GKtHk&t=9s"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer4.setImageResource(R.drawable.jumprope);
            exer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.jumprope_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=RjMCLiw8yRQ"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer5.setImageResource(R.drawable.cycle);
            exer5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.cycle_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=OxNXE4jwy0w"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
        }
        return imagestack;
    }
    public RelativeLayout obRestore(int level){
        RelativeLayout imagestack = (RelativeLayout)v.findViewById(R.id.imagestack);
        url1=v.findViewById(R.id.exer_url1);url2=v.findViewById(R.id.exer_url2);url3=v.findViewById(R.id.exer_url3);url4=v.findViewById(R.id.exer_url4);url5=v.findViewById(R.id.exer_url5);
        exer1 = v.findViewById(R.id.exer_opt1);exer2 = v.findViewById(R.id.exer_opt2);exer3 = v.findViewById(R.id.exer_opt3);exer4 = v.findViewById(R.id.exer_opt4);exer5 = v.findViewById(R.id.exer_opt5);

        if(level==1){
            tv1.setText(WcountSet.get(1)+"회"); tv2.setText(WcountSet.get(0)+"회"); tv3.setText(WcountSet.get(0)+"회");
            tv4.setText(WcountSet.get(3)+"회"); tv5.setText(AcountSet.get(4)+"회");
            exer1.setImageResource(R.drawable.knee_pushup);
            exer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.pushup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=LiKfLE2K_DI"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer2.setImageResource(R.drawable.jump_pullup);
            exer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.pullup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=fKe5S1dd0fw"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer3.setImageResource(R.drawable.step);
            exer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.step_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=tkj7N3xT-_U&t=1s"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer4.setImageResource(R.drawable.buffet);
            exer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.buffet_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Uukm_JpUcRg"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer5.setImageResource(R.drawable.running5km);
            exer5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.running_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=rqH4EpLZf2g"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
        } else {
            tv1.setText(WcountSet.get(1)+"회"); tv2.setText(WcountSet.get(0)+"회"); tv3.setText(WcountSet.get(0)+"회");
            tv4.setText(WcountSet.get(3)+"회"); tv5.setText(AcountSet.get(4)+"회");
            exer1.setImageResource(R.drawable.wall_pushup);
            exer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.pushup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=JsVKv06CWD4"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer2.setImageResource(R.drawable.pike_pushup);
            exer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.pushup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=sposDXWEB0A&t=37s"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer3.setImageResource(R.drawable.plank);
            exer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.plank_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=iNdDN4GKtHk&t=9s"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer4.setImageResource(R.drawable.jumprope);
            exer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.jumprope_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=RjMCLiw8yRQ"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer5.setImageResource(R.drawable.cycle);
            exer5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.cycle_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=OxNXE4jwy0w"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
        }
        return imagestack;
    }

    public RelativeLayout sbRestore(int level){
        RelativeLayout imagestack = (RelativeLayout)v.findViewById(R.id.imagestack);
         url1=v.findViewById(R.id.exer_url1);url2=v.findViewById(R.id.exer_url2);url3=v.findViewById(R.id.exer_url3);url4=v.findViewById(R.id.exer_url4);url5=v.findViewById(R.id.exer_url5);
        exer1 = v.findViewById(R.id.exer_opt1);exer2 = v.findViewById(R.id.exer_opt2);exer3 = v.findViewById(R.id.exer_opt3);exer4 = v.findViewById(R.id.exer_opt4);exer5 = v.findViewById(R.id.exer_opt5);

        if(level==1){
            tv1.setText(WcountSet.get(1)+"회"); tv2.setText(WcountSet.get(0)+"회"); tv3.setText(WcountSet.get(0)+"회");
            tv4.setText(WcountSet.get(3)+"회"); tv5.setText(AcountSet.get(4)+"분");
            exer1.setImageResource(R.drawable.handstand_pushup);
            exer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.pushup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=iVvEc1hYI3M"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer2.setImageResource(R.drawable.incline_pushup);
            exer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.pushup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Z0bRiVhnO8Q"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer3.setImageResource(R.drawable.one_squat);
            exer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.squat_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=DeTBwEL4m7s&t=14s"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer4.setImageResource(R.drawable.buffet);
            exer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.buffet_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Uukm_JpUcRg"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer5.setImageResource(R.drawable.cycle);
            exer5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.cycle_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=OxNXE4jwy0w"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
        } else {
            /*------------------------------------------------------------------exer1 시나리오----------------------------------------------------------*/
            exer1.setImageResource(R.drawable.pullup);
            exer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.pullup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=eGo4IYlbE5g"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            /*-------------------------------------------------------------exer2 시나리오------------------------------------------------------*/
            exer2.setImageResource(R.drawable.jump_squat);
            exer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.squat_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=DeTBwEL4m7s&t=14s"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            /*-----------------------------------------------exer3 시나리오--------------------------------------------*/
            exer3.setImageResource(R.drawable.narrow_pushup);
            exer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.pushup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=40LMvyHkUKw"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            /*--------------------------------------------------exer4 시나리오------------------------------------------*/
            exer4.setImageResource(R.drawable.plank);
            exer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.plank_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=iNdDN4GKtHk&t=9s"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            /*----------------------------------------------------------exer5 시나리오---------------------------------------------------*/
            exer5.setImageResource(R.drawable.running5km);
            exer5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.running_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=rqH4EpLZf2g"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
        }
        return imagestack;
    }

    public RelativeLayout ssRestore(int level){
        RelativeLayout imagestack = (RelativeLayout)v.findViewById(R.id.imagestack);
        url1=v.findViewById(R.id.exer_url1);url2=v.findViewById(R.id.exer_url2);url3=v.findViewById(R.id.exer_url3);url4=v.findViewById(R.id.exer_url4);url5=v.findViewById(R.id.exer_url5);
        exer1 = v.findViewById(R.id.exer_opt1);exer2 = v.findViewById(R.id.exer_opt2);exer3 = v.findViewById(R.id.exer_opt3);exer4 = v.findViewById(R.id.exer_opt4);exer5 = v.findViewById(R.id.exer_opt5);

        if(level==1){
            tv1.setText(WcountSet.get(1)+"회"); tv2.setText(WcountSet.get(0)+"회"); tv3.setText(WcountSet.get(0)+"회");
            tv4.setText(WcountSet.get(3)+"회"); tv5.setText(AcountSet.get(4)+"회");
            exer1.setImageResource(R.drawable.pushup);
            exer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.pushup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=-_DUjHxgmWk&t=35s"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer2.setImageResource(R.drawable.fly_5kg);
            exer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.fly_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=VANutvcSOwc"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer3.setImageResource(R.drawable.jumprope);
            exer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.jumprope_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=RjMCLiw8yRQ"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer4.setImageResource(R.drawable.buffet);
            exer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.buffet_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Uukm_JpUcRg"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer5.setImageResource(R.drawable.running5km);
            exer5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.running_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=rqH4EpLZf2g"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
        } else {
            exer1.setImageResource(R.drawable.pullup);
            exer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.pullup_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=eGo4IYlbE5g"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer2.setImageResource(R.drawable.onearm_5);
            exer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.onearm_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=ovTHP1MZbZI&t=13s"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer3.setImageResource(R.drawable.plank);
            exer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.plank_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=iNdDN4GKtHk&t=9s"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer4.setImageResource(R.drawable.step);
            exer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.step_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=tkj7N3xT-_U&t=1s"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer5.setImageResource(R.drawable.cycle);
            exer5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.cycle_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=OxNXE4jwy0w"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
        }
        return imagestack;
    }

    public RelativeLayout sfRestore(int level){
        RelativeLayout imagestack = (RelativeLayout)v.findViewById(R.id.imagestack);
        url1=v.findViewById(R.id.exer_url1);url2=v.findViewById(R.id.exer_url2);url3=v.findViewById(R.id.exer_url3);url4=v.findViewById(R.id.exer_url4);url5=v.findViewById(R.id.exer_url5);
        exer1 = v.findViewById(R.id.exer_opt1);exer2 = v.findViewById(R.id.exer_opt2);exer3 = v.findViewById(R.id.exer_opt3);exer4 = v.findViewById(R.id.exer_opt4);exer5 = v.findViewById(R.id.exer_opt5);

        if(level==1){
            tv1.setText(AcountSet.get(1)+"분"); tv2.setText(AcountSet.get(0)+"분"); tv3.setText(AcountSet.get(0)+"회");
            tv4.setText(WcountSet.get(0)+"회"); tv5.setText(AcountSet.get(5)+"회");
            exer1.setImageResource(R.drawable.running5km);
            exer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.running_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=rqH4EpLZf2g"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer2.setImageResource(R.drawable.cycle);
            exer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.cycle_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=OxNXE4jwy0w"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer3.setImageResource(R.drawable.jumprope);
            exer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.jumprope_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=RjMCLiw8yRQ"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer4.setImageResource(R.drawable.backex);
            exer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.backextension_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=ph3pddpKzzw"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer5.setImageResource(R.drawable.bicycle);
            exer5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.bicycle_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=9FGilxCbdz8"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
        } else {
            tv1.setText(AcountSet.get(1)+"걸음"); tv2.setText(WcountSet.get(0)+"회"); tv3.setText(WcountSet.get(0)+"회");
            tv4.setText(WcountSet.get(3)+"회"); tv5.setText(AcountSet.get(4)+"회");
            exer1.setImageResource(R.drawable.step);
            exer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.step_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=tkj7N3xT-_U&t=1s"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer2.setImageResource(R.drawable.buffet);
            exer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.buffet_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Uukm_JpUcRg"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer3.setImageResource(R.drawable.cycle);
            exer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.cycle_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=OxNXE4jwy0w"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer4.setImageResource(R.drawable.cross_crunch);
            exer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.crunch_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=bEYv_MMlWhs"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer5.setImageResource(R.drawable.plank);
            exer5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.plank_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=iNdDN4GKtHk&t=9s"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
        }
        return imagestack;
    }

    public RelativeLayout ofRestore(int level){
        RelativeLayout imagestack = (RelativeLayout)v.findViewById(R.id.imagestack);
        url1=v.findViewById(R.id.exer_url1);url2=v.findViewById(R.id.exer_url2);url3=v.findViewById(R.id.exer_url3);url4=v.findViewById(R.id.exer_url4);url5=v.findViewById(R.id.exer_url5);
        exer1 = v.findViewById(R.id.exer_opt1);exer2 = v.findViewById(R.id.exer_opt2);exer3 = v.findViewById(R.id.exer_opt3);exer4 = v.findViewById(R.id.exer_opt4);exer5 = v.findViewById(R.id.exer_opt5);

        if(level==1){
            tv1.setText(AcountSet.get(1)+"회"); tv2.setText(AcountSet.get(0)+"걸음"); tv3.setText(WcountSet.get(0)+"회");
            tv4.setText(WcountSet.get(3)+"회"); tv5.setText(AcountSet.get(4)+"회");
            exer1.setImageResource(R.drawable.jumprope);
            exer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.jumprope_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=RjMCLiw8yRQ"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer2.setImageResource(R.drawable.step);
            exer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.step_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=tkj7N3xT-_U&t=1s"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer3.setImageResource(R.drawable.buffet);
            exer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.buffet_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Uukm_JpUcRg"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer4.setImageResource(R.drawable.crunch);
            exer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.crunch_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Xyd_fa5zoEU"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer5.setImageResource(R.drawable.leg_raise);
            exer5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.legraise_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=JB2oyawG9KI"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });

        } else {
            tv1.setText(AcountSet.get(2)+"분"); tv2.setText(AcountSet.get(3)+"초"); tv3.setText(AcountSet.get(4)+"분");
            tv4.setText(WcountSet.get(5)+"회"); tv5.setText(AcountSet.get(5)+"회");
            exer1.setImageResource(R.drawable.running3km);
            exer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.running_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=rqH4EpLZf2g"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer2.setImageResource(R.drawable.plank);
            exer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.plank_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=iNdDN4GKtHk&t=9s"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer3.setImageResource(R.drawable.cycle);
            exer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.cycle_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=OxNXE4jwy0w"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer4.setImageResource(R.drawable.bicycle);
            exer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.bicycle_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=9FGilxCbdz8"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
            exer5.setImageResource(R.drawable.russian_twist);
            exer5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.setImageResource(R.drawable.russiantwist_info);
                    if(iv.getVisibility() == View.INVISIBLE)
                        iv.setVisibility(getView().VISIBLE);
                    else
                        iv.setVisibility(getView().INVISIBLE);
                }
            });
            url5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=wkD8rjkodUI"));
                    intent.putExtra("force_fullscreen",true);
                    startActivity(intent);
                }
            });
        }
        return imagestack;
    }
    public static List<Integer> aerobicCountSet(double weight, double targetweight,int targetperiod){
      //  bodyAlgo bodyalgo = new bodyAlgo();
        double targetCal = (Math.round(targetCal(weight, targetweight, targetperiod) * 0.3 * 100d) / 100d);
        int stepcount = 0; int jumpcount = 0; int runningcount = 0;
        int plankcount = 0; int cyclecount = 0;
        if(targetCal <= 300){
            stepcount = 300; jumpcount = 100; runningcount = 40; // 걸음, 회, 분
            plankcount = 120; cyclecount = 25;                   // 초, 분
        }
        else if(targetCal <= 200){
            stepcount = 250; jumpcount = 70; runningcount = 30;
            plankcount = 90; cyclecount = 20;
        }
        else if(targetCal <= 150){
            stepcount = 200; jumpcount = 50; runningcount = 20;
            plankcount = 60; cyclecount = 20;
        }
        return Arrays.asList(stepcount,jumpcount,runningcount,plankcount,cyclecount);
    }
    public static List<Integer> weightCountSet(String bt){
        int backcount = 0; int chestcount = 0; int legcount = 0;
        int shouldercount = 0; int armcount = 0; int stomachcount = 0;
        if(bt.equals("LW") || bt.equals("LB") || bt.equals("SW")){
            backcount = 8; chestcount = 12; legcount = 15;
            shouldercount = 8; armcount = 10; stomachcount = 15;
        }
        else if(bt.equals("OF") || bt.equals("SF")){
            backcount = 15; chestcount = 15; legcount = 20;
            shouldercount = 12; armcount = 15; stomachcount = 15;
        }
        else{
            backcount = 24; chestcount = 24; legcount = 36;
            shouldercount = 20; armcount = 30; stomachcount = 30;
        }
        return Arrays.asList(backcount,chestcount,legcount,shouldercount,armcount,stomachcount);
    }

    @Override
    public void onBack() {
        Log.e("Other", "onBack()");
        // 리스너를 설정하기 위해 Activity 를 받아옵니다.
        Main2Activity activity = (Main2Activity) getActivity();
        // 한번 뒤로가기 버튼을 눌렀다면 Listener 를 null 로 해제해줍니다.
        activity.setOnBackPressedListener(null);
        // MainFragment 로 교체
        getActivity().getFragmentManager().beginTransaction()
                .replace(R.id.content_main, mainFragment).commit();
        // Activity 에서도 뭔가 처리하고 싶은 내용이 있다면 하단 문장처럼 호출해주면 됩니다.
        // activity.onBackPressed();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("Other", "onAttach()");
        ((Main2Activity) context).setOnBackPressedListener(this);
    }
}