package ssm.hel_per;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.support.v7.app.AlertDialog;

import com.airbnb.lottie.L;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;


import ssm.hel_per.exercise_all.exercise_obesity.obesity_lv1.exercise_jumprope;
import ssm.hel_per.exercise_all.exercise_obesity.obesity_lv2.exercise_running3km;
import ssm.hel_per.exercise_all.exercise_standard.standard_lv1.exercise_handstand_pushup;
import ssm.hel_per.exercise_all.exercise_standard.standard_lv2.exercise_pullup;
import ssm.hel_per.exercise_all.exercise_weak.weak_lv1.exercise_knee_pushup;
import ssm.hel_per.exercise_all.exercise_weak.weak_lv2.exercise_wall_pushup;

import static android.support.constraint.Constraints.TAG;
import static com.facebook.FacebookSdk.getApplicationContext;

public class exercise extends Fragment implements Main2Activity.OnBackPressedListener{
    String id;
    View v;
    home mainFragment;
    Button button1,re_select;
    Button exercisebtn;
    TextView countTxt;
    ImageView urltest,exer1,exer2,exer3,exer4,exer5;
    VideoView video;
    String bt;
    Double weight;
    static final String VIDEO_URL = "https://ko.gl/youtube.php?download=aHR0cHM6Ly9yMi0tLXNuLWE1bWVrbmVsLmdvb2dsZXZpZGVvLmNvbS92aWRlb3BsYXliYWNrP2R1cj0zNC44MjkmZXhwaXJlPTE1MjgwNjA2Njkmc291cmNlPXlvdXR1YmUmbG10PTE0NzEwNTI1NTM5NDY3MzkmZWk9blFZVVctejBHTV9vLVFPV3VLckFCQSZpZD1vLUFIWUpuUWUxNUJXQjVnR1dtT19wVlFZYkFST2tacDVlelNnT1lKNkRFWjE0Jm1zPWF1JTJDcmR1Jm10PTE1MjgwMzg5NDkmbXY9bSZyYXRlYnlwYXNzPXllcyZpcGJpdHM9MCZmdmlwPTQmbW09MzElMkMyOSZzaWduYXR1cmU9NUJDQ0RBQkNERTc0NTU1Mzc1MkVFQjAyOUQ5QjM3MDZEMEI2MkYzOC4xMUJEQzhCRDYwOTcyQUE1NTIzRDA0QjQ1N0UwNDE3NjQwQkRDQ0JFJm1uPXNuLWE1bWVrbmVsJTJDc24tYTVtN2xubHomcmVxdWlyZXNzbD15ZXMma2V5PXl0NiZpcD0yMDkuMTQxLjM0LjIzOSZwbD0yMyZtaW1lPXZpZGVvJTJGbXA0JmluaXRjd25kYnBzPTY5NjI1MCZpdGFnPTIyJmM9V0VCJnNwYXJhbXM9ZHVyJTJDZWklMkNpZCUyQ2luaXRjd25kYnBzJTJDaXAlMkNpcGJpdHMlMkNpdGFnJTJDbG10JTJDbWltZSUyQ21tJTJDbW4lMkNtcyUyQ212JTJDcGwlMkNyYXRlYnlwYXNzJTJDcmVxdWlyZXNzbCUyQ3NvdXJjZSUyQ2V4cGlyZSZ0aXRsZT0lRUQlOTQlOEMlRUIlOUUlQUQlRUQlODElQUMrJUVDJTlBJUI0JUVCJThGJTk5KyZrZWVwYWxpdmU9eWVz&title=%ED%94%8C%EB%9E%AD%ED%81%AC%20%EC%9A%B4%EB%8F%99";
    public static String urlStr = "http://13.209.40.50:3000/daily"; // 웹
    RelativeLayout imagestack;
    public FragmentManager manager;
    public RelativeLayout R_image,Rela_exer5;
    public CharSequence[] items ={"1.유산소", "2.웨이트", "3.무작위"};
    public  CharSequence[] weighttrain={"1.가슴","2.등","3.어깨","4.하체","5.팔","6.복근"};
    public AlertDialog.Builder builder;

    RelativeLayout routine1,routine2,routine3,routine4,routine5,routine6,routine7,Layout;
    int test;

    int workperiod=0,count=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.exercise, container, false);
        exer1 = v.findViewById(R.id.exer_opt1);exer2 = v.findViewById(R.id.exer_opt2);exer3 = v.findViewById(R.id.exer_opt3);exer4 = v.findViewById(R.id.exer_opt4);exer5 = v.findViewById(R.id.exer_opt5);
        Rela_exer5= (RelativeLayout)v.findViewById(R.id.exercise_5);
        Rela_exer5.setVisibility(View.VISIBLE);

        mainFragment = new home();
        routine1=lwRestore(workperiod);routine2=swRestore(workperiod);routine3=obRestore(workperiod);routine4=sfRestore(workperiod);routine5=lbRestore(workperiod);routine6=sbRestore(workperiod);routine7=ssRestore(workperiod);
        final RelativeLayout[] ran={routine1,routine2,routine3,routine4,routine5,routine6,routine7};
        test=(int)(Math.random()*7);
        workperiod = getActivity().getIntent().getIntExtra("workperiod",0);
        id = getActivity().getIntent().getStringExtra("id");
        weight=getActivity().getIntent().getDoubleExtra("weight",0);
        bt = getActivity().getIntent().getStringExtra("bodytype");
        exercisebtn=(Button)v.findViewById(R.id.exercise);
        button1 = (Button)v.findViewById(R.id.part);
        R_image = (RelativeLayout)v.findViewById(R.id.imagestack);

        if(bt!=null) {
            if (bt.equals("LW"))
                Layout=lwRestore(workperiod);
            else if (bt.equals("SW"))
                Layout=swRestore(workperiod);
            else if (bt.equals("OB"))
                Layout=obRestore(workperiod);
            else if (bt.equals("SF"))
                Layout=sfRestore(workperiod);
            else if (bt.equals("OF"))
                Layout=ofRestore(workperiod);
            else if (bt.equals("LB"))
                Layout=lbRestore(workperiod);
            else if (bt.equals("SB"))
                Layout=sbRestore(workperiod);
            else if (bt.equals("SS"))
                Layout=ssRestore(workperiod);
            else if (bt.equals("OS"))
                Toast.makeText(getApplicationContext(), "Hel_per를 사용할만한 수준이 아닙니다", Toast.LENGTH_LONG).show();
        }

        builder = new AlertDialog.Builder(getActivity());
        countTxt = (TextView)v.findViewById(R.id.count);

        exercisebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =null;
                if (bt.equals("LW")) {
                    if (workperiod == 1) {
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
                    if (workperiod == 1) {
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
                    if (workperiod == 1) {
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
                    if (workperiod == 1) {
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
                    if (workperiod == 1) {
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
                    if (workperiod == 1) {
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
                    if (workperiod == 1) {
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
                    if (workperiod == 1) {
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
                    if (workperiod == 1) {
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
                                                                chestRestore(workperiod);
                                                                break;
                                                            case 1: // 등
                                                                backRestore(workperiod);
                                                                break;
                                                            case 2: // 어깨
                                                                shoulderRestore(workperiod);
                                                                break;
                                                            case 3: // 하체
                                                                legRestore(workperiod);
                                                                break;
                                                            case 4: // 팔
                                                                armRestore(workperiod);
                                                                break;
                                                            case 5: // 복근
                                                                abdoRestore(workperiod);
                                                                break;
                                                        }
                                                    }
                                                }).show();
                                        break;
                                    case 2: // 무작위
                                        //Layout=ran[test];
                                        break;
                                }
                            }
                        }).show();
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

    public RelativeLayout aerobicRestore(int level){ // 유산소
        RelativeLayout imagestack = (RelativeLayout)v.findViewById(R.id.imagestack);
        if(level==1) {
            exer1.setImageResource(R.drawable.running3km);
            exer2.setImageResource(R.drawable.jumprope);
            exer3.setImageResource(R.drawable.buffet);
            exer4.setImageResource(R.drawable.step);
            Rela_exer5.setVisibility(View.GONE);
        }else {
            exer1.setImageResource(R.drawable.running5km);
            exer2.setImageResource(R.drawable.buffet);
            exer3.setImageResource(R.drawable.cycle);
            exer4.setImageResource(R.drawable.plank);
            Rela_exer5.setVisibility(View.GONE);
        }
        return imagestack;
    }
    public RelativeLayout chestRestore(int level){
        RelativeLayout imagestack = (RelativeLayout)v.findViewById(R.id.imagestack);
        if(level==1){
            exer1.setImageResource(R.drawable.knee_pushup);
            exer2.setImageResource(R.drawable.wall_pushup);
            exer3.setImageResource(R.drawable.wide_pushup);
            exer4.setImageResource(R.drawable.fly_3kg);
            Rela_exer5.setVisibility(View.GONE);
        } else {
            exer1.setImageResource(R.drawable.knee_pushup);
            exer2.setImageResource(R.drawable.narrow_pushup);
            exer3.setImageResource(R.drawable.incline_pushup);
            exer4.setImageResource(R.drawable.fly_5kg);
            Rela_exer5.setVisibility(View.GONE);
        }
        return imagestack;
    }
    public RelativeLayout backRestore(int level){
        RelativeLayout imagestack = (RelativeLayout)v.findViewById(R.id.imagestack);
        if(level==1){
            exer1.setImageResource(R.drawable.jump_pullup);
            exer2.setImageResource(R.drawable.bentover_3);
            exer3.setImageResource(R.drawable.onearm_3);
            exer4.setImageResource(R.drawable.backex);
            Rela_exer5.setVisibility(View.GONE);
        } else {
            exer1.setImageResource(R.drawable.bentover_5);
            exer2.setImageResource(R.drawable.pullup);
            exer3.setImageResource(R.drawable.onearm_5);
            exer4.setImageResource(R.drawable.backex);
            Rela_exer5.setVisibility(View.GONE);
        }
        return imagestack;
    }
    public RelativeLayout shoulderRestore(int level){
        RelativeLayout imagestack = (RelativeLayout)v.findViewById(R.id.imagestack);
        if(level==1){
            exer1.setImageResource(R.drawable.pike_pushup);
            exer2.setImageResource(R.drawable.sitdum_3);
            exer3.setImageResource(R.drawable.plank);
            exer4.setImageResource(R.drawable.fdum_3);
            Rela_exer5.setVisibility(View.GONE);
        } else {
            exer1.setImageResource(R.drawable.handstand_pushup);
            exer2.setImageResource(R.drawable.squat);
            exer3.setImageResource(R.drawable.cycle);
            exer4.setImageResource(R.drawable.lunge);
            Rela_exer5.setVisibility(View.GONE);
        }
        return imagestack;
    }
    public RelativeLayout legRestore(int level){
        RelativeLayout imagestack = (RelativeLayout)v.findViewById(R.id.imagestack);
        if(level==1){
            exer1.setImageResource(R.drawable.one_squat);
            exer2.setImageResource(R.drawable.jump_squat);
            exer3.setImageResource(R.drawable.lunge);
            exer4.setImageResource(R.drawable.wide_squat);
            Rela_exer5.setVisibility(View.GONE);
        } else {
            exer1.setImageResource(R.drawable.handstand_pushup);
            exer2.setImageResource(R.drawable.sitdum_5);
            exer3.setImageResource(R.drawable.rfum_5);
            exer4.setImageResource(R.drawable.pike_pushup);
            Rela_exer5.setVisibility(View.GONE);
        }
        return imagestack;
    }
    public RelativeLayout armRestore(int level){
        RelativeLayout imagestack = (RelativeLayout)v.findViewById(R.id.imagestack);
        if(level==1){
            exer1.setImageResource(R.drawable.dips);
            exer2.setImageResource(R.drawable.extension_1);
            exer3.setImageResource(R.drawable.dumcurl_1);
            exer4.setImageResource(R.drawable.dumkick_1);
            Rela_exer5.setVisibility(View.GONE);
        } else {
            exer1.setImageResource(R.drawable.dips);
            exer2.setImageResource(R.drawable.dumcurl_3);
            exer3.setImageResource(R.drawable.dumkick_3);
            exer4.setImageResource(R.drawable.extension_3);
            Rela_exer5.setVisibility(View.GONE);
        }
        return imagestack;
    }
    public RelativeLayout abdoRestore(int level){
        RelativeLayout imagestack = (RelativeLayout)v.findViewById(R.id.imagestack);
        if(level==1){
            exer1.setImageResource(R.drawable.crunch);
            exer2.setImageResource(R.drawable.leg_raise);
            exer3.setImageResource(R.drawable.bicycle);
            exer4.setImageResource(R.drawable.plank);
            Rela_exer5.setVisibility(View.GONE);
        } else {
            exer1.setImageResource(R.drawable.vup);
            exer2.setImageResource(R.drawable.cross_crunch);
            exer3.setImageResource(R.drawable.leg_raise);
            exer4.setImageResource(R.drawable.russian_twist);
            Rela_exer5.setVisibility(View.GONE);
        }
        return imagestack;
    }
    public RelativeLayout lwRestore(int level){
        RelativeLayout imagestack = (RelativeLayout)v.findViewById(R.id.imagestack);
        if(level==1){
            exer1.setImageResource(R.drawable.knee_pushup);
            exer2.setImageResource(R.drawable.fly_3kg);
            exer3.setImageResource(R.drawable.jump_pullup);
            exer4.setImageResource(R.drawable.pike_pushup);
            exer5.setImageResource(R.drawable.step);
        } else {
            exer1.setImageResource(R.drawable.wall_pushup);
            exer2.setImageResource(R.drawable.bentover_3);
            exer3.setImageResource(R.drawable.dumcurl_1);
            exer4.setImageResource(R.drawable.squat);
            exer5.setImageResource(R.drawable.plank);
        }
        return imagestack;
    }
    public RelativeLayout swRestore(int level){
        RelativeLayout imagestack = (RelativeLayout)v.findViewById(R.id.imagestack);
        if(level==1){
            exer1.setImageResource(R.drawable.wide_pushup);
            exer2.setImageResource(R.drawable.onearm_3);
            exer3.setImageResource(R.drawable.sitdum_3);
            exer4.setImageResource(R.drawable.squat);
            exer5.setImageResource(R.drawable.knee_dips);
        } else {
            exer1.setImageResource(R.drawable.pushup);
            exer2.setImageResource(R.drawable.fly_5kg);
            exer3.setImageResource(R.drawable.fdum_3);
            exer4.setImageResource(R.drawable.jump_squat);
            exer5.setImageResource(R.drawable.dumkick_1);
        }
        return imagestack;
    }

    public RelativeLayout lbRestore(int level){
        RelativeLayout imagestack = (RelativeLayout)v.findViewById(R.id.imagestack);
        if(level==1){
            exer1.setImageResource(R.drawable.narrow_pushup);
            exer2.setImageResource(R.drawable.jump_pullup);
            exer3.setImageResource(R.drawable.step);
            exer4.setImageResource(R.drawable.buffet_test);
            exer5.setImageResource(R.drawable.running5km);
        } else {
            exer1.setImageResource(R.drawable.wall_pushup);
            exer2.setImageResource(R.drawable.pike_pushup);
            exer3.setImageResource(R.drawable.plank);
            exer4.setImageResource(R.drawable.jumprope);
            exer5.setImageResource(R.drawable.cycle);
        }
        return imagestack;
    }
    public RelativeLayout obRestore(int level){
        RelativeLayout imagestack = (RelativeLayout)v.findViewById(R.id.imagestack);
        if(level==1){
            exer1.setImageResource(R.drawable.knee_pushup);
            exer2.setImageResource(R.drawable.jump_pullup);
            exer3.setImageResource(R.drawable.step);
            exer4.setImageResource(R.drawable.buffet_test);
            exer5.setImageResource(R.drawable.running5km);
        } else {
            exer1.setImageResource(R.drawable.wall_pushup);
            exer2.setImageResource(R.drawable.pike_pushup);
            exer3.setImageResource(R.drawable.plank);
            exer4.setImageResource(R.drawable.jumprope);
            exer5.setImageResource(R.drawable.cycle);
        }
        return imagestack;
    }

    public RelativeLayout sbRestore(int level){
        RelativeLayout imagestack = (RelativeLayout)v.findViewById(R.id.imagestack);
        if(level==1){
            exer1.setImageResource(R.drawable.handstand_pushup);
            exer2.setImageResource(R.drawable.incline_pushup);
            exer3.setImageResource(R.drawable.one_squat);
            exer4.setImageResource(R.drawable.buffet_test);
            exer5.setImageResource(R.drawable.cycle);
        } else {
            exer1.setImageResource(R.drawable.pullup);
            exer2.setImageResource(R.drawable.jump_squat);
            exer3.setImageResource(R.drawable.narrow_pushup);
            exer4.setImageResource(R.drawable.plank);
            exer5.setImageResource(R.drawable.running5km);
        }
        return imagestack;
    }

    public RelativeLayout ssRestore(int level){
        RelativeLayout imagestack = (RelativeLayout)v.findViewById(R.id.imagestack);
        if(level==1){
            exer1.setImageResource(R.drawable.pushup);
            exer2.setImageResource(R.drawable.fly_5kg);
            exer3.setImageResource(R.drawable.jumprope);
            exer4.setImageResource(R.drawable.buffet_test);
            exer5.setImageResource(R.drawable.running5km);
        } else {
            exer1.setImageResource(R.drawable.pullup);
            exer2.setImageResource(R.drawable.onearm_5);
            exer3.setImageResource(R.drawable.plank);
            exer4.setImageResource(R.drawable.step);
            exer5.setImageResource(R.drawable.cycle);
        }
        return imagestack;
    }

    public RelativeLayout sfRestore(int level){
        RelativeLayout imagestack = (RelativeLayout)v.findViewById(R.id.imagestack);
        if(level==1){
            exer1.setImageResource(R.drawable.running5km);
            exer2.setImageResource(R.drawable.cycle);
            exer3.setImageResource(R.drawable.jumprope);
            exer4.setImageResource(R.drawable.backex);
            exer5.setImageResource(R.drawable.bicycle);
        } else {
            exer1.setImageResource(R.drawable.step);
            exer2.setImageResource(R.drawable.buffet_test);
            exer3.setImageResource(R.drawable.cycle);
            exer4.setImageResource(R.drawable.cross_crunch);
            exer5.setImageResource(R.drawable.plank);
        }
        return imagestack;
    }

    public RelativeLayout ofRestore(int level){
        RelativeLayout imagestack = (RelativeLayout)v.findViewById(R.id.imagestack);
        if(level==1){
            exer1.setImageResource(R.drawable.jumprope);
            exer2.setImageResource(R.drawable.step);
            exer3.setImageResource(R.drawable.buffet_test);
            exer4.setImageResource(R.drawable.crunch);
            exer5.setImageResource(R.drawable.leg_raise);
        } else {
            exer1.setImageResource(R.drawable.running3km);
            exer2.setImageResource(R.drawable.plank);
            exer3.setImageResource(R.drawable.cycle);
            exer4.setImageResource(R.drawable.bicycle);
            exer5.setImageResource(R.drawable.russian_twist);
        }
        return imagestack;
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