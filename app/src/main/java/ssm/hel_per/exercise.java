package ssm.hel_per;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
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
import java.util.Random;


import static android.support.constraint.Constraints.TAG;
import static com.facebook.FacebookSdk.getApplicationContext;

public class exercise extends Fragment implements Main2Activity.OnBackPressedListener{
    String id;
    View v;
    home mainFragment;
    Button button1,re_select;
    TextView countTxt;
    ImageView exer1,exer2,exer3,exer4,exer5,url1,url2,url3,url4,url5,iv;
    VideoView video;
    String bt;
//    static final String VIDEO_URL1 = "https://ko.gl/youtube.php?download=aHR0cHM6Ly9yMi0tLXNuLWE1bWVrbmVsLmdvb2dsZXZpZGVvLmNvbS92aWRlb3BsYXliYWNrP2R1cj0zNC44MjkmZXhwaXJlPTE1MjgwNjA2Njkmc291cmNlPXlvdXR1YmUmbG10PTE0NzEwNTI1NTM5NDY3MzkmZWk9blFZVVctejBHTV9vLVFPV3VLckFCQSZpZD1vLUFIWUpuUWUxNUJXQjVnR1dtT19wVlFZYkFST2tacDVlelNnT1lKNkRFWjE0Jm1zPWF1JTJDcmR1Jm10PTE1MjgwMzg5NDkmbXY9bSZyYXRlYnlwYXNzPXllcyZpcGJpdHM9MCZmdmlwPTQmbW09MzElMkMyOSZzaWduYXR1cmU9NUJDQ0RBQkNERTc0NTU1Mzc1MkVFQjAyOUQ5QjM3MDZEMEI2MkYzOC4xMUJEQzhCRDYwOTcyQUE1NTIzRDA0QjQ1N0UwNDE3NjQwQkRDQ0JFJm1uPXNuLWE1bWVrbmVsJTJDc24tYTVtN2xubHomcmVxdWlyZXNzbD15ZXMma2V5PXl0NiZpcD0yMDkuMTQxLjM0LjIzOSZwbD0yMyZtaW1lPXZpZGVvJTJGbXA0JmluaXRjd25kYnBzPTY5NjI1MCZpdGFnPTIyJmM9V0VCJnNwYXJhbXM9ZHVyJTJDZWklMkNpZCUyQ2luaXRjd25kYnBzJTJDaXAlMkNpcGJpdHMlMkNpdGFnJTJDbG10JTJDbWltZSUyQ21tJTJDbW4lMkNtcyUyQ212JTJDcGwlMkNyYXRlYnlwYXNzJTJDcmVxdWlyZXNzbCUyQ3NvdXJjZSUyQ2V4cGlyZSZ0aXRsZT0lRUQlOTQlOEMlRUIlOUUlQUQlRUQlODElQUMrJUVDJTlBJUI0JUVCJThGJTk5KyZrZWVwYWxpdmU9eWVz&title=%ED%94%8C%EB%9E%AD%ED%81%AC%20%EC%9A%B4%EB%8F%99";
//    static final String VIDEO_URL2 = "https://ko.gl/youtube.php?download=aHR0cHM6Ly9yMi0tLXNuLWE1bWVrbmVsLmdvb2dsZXZpZGVvLmNvbS92aWRlb3BsYXliYWNrP2R1cj0zNC44MjkmZXhwaXJlPTE1MjgwNjA2Njkmc291cmNlPXlvdXR1YmUmbG10PTE0NzEwNTI1NTM5NDY3MzkmZWk9blFZVVctejBHTV9vLVFPV3VLckFCQSZpZD1vLUFIWUpuUWUxNUJXQjVnR1dtT19wVlFZYkFST2tacDVlelNnT1lKNkRFWjE0Jm1zPWF1JTJDcmR1Jm10PTE1MjgwMzg5NDkmbXY9bSZyYXRlYnlwYXNzPXllcyZpcGJpdHM9MCZmdmlwPTQmbW09MzElMkMyOSZzaWduYXR1cmU9NUJDQ0RBQkNERTc0NTU1Mzc1MkVFQjAyOUQ5QjM3MDZEMEI2MkYzOC4xMUJEQzhCRDYwOTcyQUE1NTIzRDA0QjQ1N0UwNDE3NjQwQkRDQ0JFJm1uPXNuLWE1bWVrbmVsJTJDc24tYTVtN2xubHomcmVxdWlyZXNzbD15ZXMma2V5PXl0NiZpcD0yMDkuMTQxLjM0LjIzOSZwbD0yMyZtaW1lPXZpZGVvJTJGbXA0JmluaXRjd25kYnBzPTY5NjI1MCZpdGFnPTIyJmM9V0VCJnNwYXJhbXM9ZHVyJTJDZWklMkNpZCUyQ2luaXRjd25kYnBzJTJDaXAlMkNpcGJpdHMlMkNpdGFnJTJDbG10JTJDbWltZSUyQ21tJTJDbW4lMkNtcyUyQ212JTJDcGwlMkNyYXRlYnlwYXNzJTJDcmVxdWlyZXNzbCUyQ3NvdXJjZSUyQ2V4cGlyZSZ0aXRsZT0lRUQlOTQlOEMlRUIlOUUlQUQlRUQlODElQUMrJUVDJTlBJUI0JUVCJThGJTk5KyZrZWVwYWxpdmU9eWVz&title=%ED%94%8C%EB%9E%AD%ED%81%AC%20%EC%9A%B4%EB%8F%99";
//    static final String VIDEO_URL3 = "https://ko.gl/youtube.php?download=aHR0cHM6Ly9yMi0tLXNuLWE1bWVrbmVsLmdvb2dsZXZpZGVvLmNvbS92aWRlb3BsYXliYWNrP2R1cj0zNC44MjkmZXhwaXJlPTE1MjgwNjA2Njkmc291cmNlPXlvdXR1YmUmbG10PTE0NzEwNTI1NTM5NDY3MzkmZWk9blFZVVctejBHTV9vLVFPV3VLckFCQSZpZD1vLUFIWUpuUWUxNUJXQjVnR1dtT19wVlFZYkFST2tacDVlelNnT1lKNkRFWjE0Jm1zPWF1JTJDcmR1Jm10PTE1MjgwMzg5NDkmbXY9bSZyYXRlYnlwYXNzPXllcyZpcGJpdHM9MCZmdmlwPTQmbW09MzElMkMyOSZzaWduYXR1cmU9NUJDQ0RBQkNERTc0NTU1Mzc1MkVFQjAyOUQ5QjM3MDZEMEI2MkYzOC4xMUJEQzhCRDYwOTcyQUE1NTIzRDA0QjQ1N0UwNDE3NjQwQkRDQ0JFJm1uPXNuLWE1bWVrbmVsJTJDc24tYTVtN2xubHomcmVxdWlyZXNzbD15ZXMma2V5PXl0NiZpcD0yMDkuMTQxLjM0LjIzOSZwbD0yMyZtaW1lPXZpZGVvJTJGbXA0JmluaXRjd25kYnBzPTY5NjI1MCZpdGFnPTIyJmM9V0VCJnNwYXJhbXM9ZHVyJTJDZWklMkNpZCUyQ2luaXRjd25kYnBzJTJDaXAlMkNpcGJpdHMlMkNpdGFnJTJDbG10JTJDbWltZSUyQ21tJTJDbW4lMkNtcyUyQ212JTJDcGwlMkNyYXRlYnlwYXNzJTJDcmVxdWlyZXNzbCUyQ3NvdXJjZSUyQ2V4cGlyZSZ0aXRsZT0lRUQlOTQlOEMlRUIlOUUlQUQlRUQlODElQUMrJUVDJTlBJUI0JUVCJThGJTk5KyZrZWVwYWxpdmU9eWVz&title=%ED%94%8C%EB%9E%AD%ED%81%AC%20%EC%9A%B4%EB%8F%99";
//    static final String VIDEO_URL4 = "https://ko.gl/youtube.php?download=aHR0cHM6Ly9yMi0tLXNuLWE1bWVrbmVsLmdvb2dsZXZpZGVvLmNvbS92aWRlb3BsYXliYWNrP2R1cj0zNC44MjkmZXhwaXJlPTE1MjgwNjA2Njkmc291cmNlPXlvdXR1YmUmbG10PTE0NzEwNTI1NTM5NDY3MzkmZWk9blFZVVctejBHTV9vLVFPV3VLckFCQSZpZD1vLUFIWUpuUWUxNUJXQjVnR1dtT19wVlFZYkFST2tacDVlelNnT1lKNkRFWjE0Jm1zPWF1JTJDcmR1Jm10PTE1MjgwMzg5NDkmbXY9bSZyYXRlYnlwYXNzPXllcyZpcGJpdHM9MCZmdmlwPTQmbW09MzElMkMyOSZzaWduYXR1cmU9NUJDQ0RBQkNERTc0NTU1Mzc1MkVFQjAyOUQ5QjM3MDZEMEI2MkYzOC4xMUJEQzhCRDYwOTcyQUE1NTIzRDA0QjQ1N0UwNDE3NjQwQkRDQ0JFJm1uPXNuLWE1bWVrbmVsJTJDc24tYTVtN2xubHomcmVxdWlyZXNzbD15ZXMma2V5PXl0NiZpcD0yMDkuMTQxLjM0LjIzOSZwbD0yMyZtaW1lPXZpZGVvJTJGbXA0JmluaXRjd25kYnBzPTY5NjI1MCZpdGFnPTIyJmM9V0VCJnNwYXJhbXM9ZHVyJTJDZWklMkNpZCUyQ2luaXRjd25kYnBzJTJDaXAlMkNpcGJpdHMlMkNpdGFnJTJDbG10JTJDbWltZSUyQ21tJTJDbW4lMkNtcyUyQ212JTJDcGwlMkNyYXRlYnlwYXNzJTJDcmVxdWlyZXNzbCUyQ3NvdXJjZSUyQ2V4cGlyZSZ0aXRsZT0lRUQlOTQlOEMlRUIlOUUlQUQlRUQlODElQUMrJUVDJTlBJUI0JUVCJThGJTk5KyZrZWVwYWxpdmU9eWVz&title=%ED%94%8C%EB%9E%AD%ED%81%AC%20%EC%9A%B4%EB%8F%99";
//    static final String VIDEO_URL5 = "https://ko.gl/youtube.php?download=aHR0cHM6Ly9yMi0tLXNuLWE1bWVrbmVsLmdvb2dsZXZpZGVvLmNvbS92aWRlb3BsYXliYWNrP2R1cj0zNC44MjkmZXhwaXJlPTE1MjgwNjA2Njkmc291cmNlPXlvdXR1YmUmbG10PTE0NzEwNTI1NTM5NDY3MzkmZWk9blFZVVctejBHTV9vLVFPV3VLckFCQSZpZD1vLUFIWUpuUWUxNUJXQjVnR1dtT19wVlFZYkFST2tacDVlelNnT1lKNkRFWjE0Jm1zPWF1JTJDcmR1Jm10PTE1MjgwMzg5NDkmbXY9bSZyYXRlYnlwYXNzPXllcyZpcGJpdHM9MCZmdmlwPTQmbW09MzElMkMyOSZzaWduYXR1cmU9NUJDQ0RBQkNERTc0NTU1Mzc1MkVFQjAyOUQ5QjM3MDZEMEI2MkYzOC4xMUJEQzhCRDYwOTcyQUE1NTIzRDA0QjQ1N0UwNDE3NjQwQkRDQ0JFJm1uPXNuLWE1bWVrbmVsJTJDc24tYTVtN2xubHomcmVxdWlyZXNzbD15ZXMma2V5PXl0NiZpcD0yMDkuMTQxLjM0LjIzOSZwbD0yMyZtaW1lPXZpZGVvJTJGbXA0JmluaXRjd25kYnBzPTY5NjI1MCZpdGFnPTIyJmM9V0VCJnNwYXJhbXM9ZHVyJTJDZWklMkNpZCUyQ2luaXRjd25kYnBzJTJDaXAlMkNpcGJpdHMlMkNpdGFnJTJDbG10JTJDbWltZSUyQ21tJTJDbW4lMkNtcyUyQ212JTJDcGwlMkNyYXRlYnlwYXNzJTJDcmVxdWlyZXNzbCUyQ3NvdXJjZSUyQ2V4cGlyZSZ0aXRsZT0lRUQlOTQlOEMlRUIlOUUlQUQlRUQlODElQUMrJUVDJTlBJUI0JUVCJThGJTk5KyZrZWVwYWxpdmU9eWVz&title=%ED%94%8C%EB%9E%AD%ED%81%AC%20%EC%9A%B4%EB%8F%99";

    public static String urlStr = "http://13.209.40.50:3000/daily"; // 웹
    RelativeLayout imagestack;
    public FragmentManager manager;
    public RelativeLayout R_image,Rela_exer5;
    public CharSequence[] items ={"1.유산소", "2.웨이트", "3.무작위"};
    public  CharSequence[] weighttrain={"1.가슴","2.등","3.어깨","4.하체","5.팔","6.복근"};
    public AlertDialog.Builder builder;

    RelativeLayout Layout;
    int test;
    int workperiod=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.exercise, container, false);
        exer1 = v.findViewById(R.id.exer_opt1);exer2 = v.findViewById(R.id.exer_opt2);exer3 = v.findViewById(R.id.exer_opt3);exer4 = v.findViewById(R.id.exer_opt4);exer5 = v.findViewById(R.id.exer_opt5);
        Rela_exer5= (RelativeLayout)v.findViewById(R.id.exercise_5);
        Rela_exer5.setVisibility(View.VISIBLE);

        mainFragment = new home();

        workperiod = getActivity().getIntent().getIntExtra("workperiod",0);
        id = getActivity().getIntent().getStringExtra("id");
        bt = getActivity().getIntent().getStringExtra("bodytype");
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

        iv = v.findViewById(R.id.exercise_image);

        builder = new AlertDialog.Builder(getActivity());
        countTxt = (TextView)v.findViewById(R.id.count);
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



    public RelativeLayout aerobicRestore(int level){ // 유산소
        RelativeLayout imagestack = (RelativeLayout)v.findViewById(R.id.imagestack);
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


    public RelativeLayout lwRestore(int level){
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
//            url1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Dialog dialog = new Dialog(getActivity());
//
//                    dialog.setContentView(R.layout.exercise_dialog);
//                    dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//                    video=(VideoView)dialog.findViewById(R.id.test_video);
//                    video.seekTo(0);
//                    video.start();
//                    MediaController mc = new MediaController(getActivity());
//                    video.setMediaController(mc);
//                    video.setVideoURI(Uri.parse(VIDEO_URL1));
//                    video.requestFocus();
//                    dialog.show();
//                }
//            });

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
//            url2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Dialog dialog = new Dialog(getActivity());
//
//                    dialog.setContentView(R.layout.exercise_dialog);
//                    dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//                    video=(VideoView)dialog.findViewById(R.id.test_video);
//                    video.seekTo(0);
//                    video.start();
//                    MediaController mc = new MediaController(getActivity());
//                    video.setMediaController(mc);
//                    video.setVideoURI(Uri.parse(VIDEO_URL2));
//                    video.requestFocus();
//                    dialog.show();
//                }
//            });

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
//            url3.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Dialog dialog = new Dialog(getActivity());
//
//                    dialog.setContentView(R.layout.exercise_dialog);
//                    dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//                    video=(VideoView)dialog.findViewById(R.id.test_video);
//                    video.seekTo(0);
//                    video.start();
//                    MediaController mc = new MediaController(getActivity());
//                    video.setMediaController(mc);
//                    video.setVideoURI(Uri.parse(VIDEO_URL3));
//                    video.requestFocus();
//                    dialog.show();
//                }
//            });

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
//            url4.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Dialog dialog = new Dialog(getActivity());
//
//                    dialog.setContentView(R.layout.exercise_dialog);
//                    dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//                    video=(VideoView)dialog.findViewById(R.id.test_video);
//                    video.seekTo(0);
//                    video.start();
//                    MediaController mc = new MediaController(getActivity());
//                    video.setMediaController(mc);
//                    video.setVideoURI(Uri.parse(VIDEO_URL4));
//                    video.requestFocus();
//                    dialog.show();
//                }
//            });
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
//            url5.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Dialog dialog = new Dialog(getActivity());
//
//                    dialog.setContentView(R.layout.exercise_dialog);
//                    dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//                    video=(VideoView)dialog.findViewById(R.id.test_video);
//                    video.seekTo(0);
//                    video.start();
//                    MediaController mc = new MediaController(getActivity());
//                    video.setMediaController(mc);
//                    video.setVideoURI(Uri.parse(VIDEO_URL5));
//                    video.requestFocus();
//                    dialog.show();
//                }
//            });
        }
        return imagestack;
    }

    public RelativeLayout ssRestore(int level){
        RelativeLayout imagestack = (RelativeLayout)v.findViewById(R.id.imagestack);
        url1=v.findViewById(R.id.exer_url1);url2=v.findViewById(R.id.exer_url2);url3=v.findViewById(R.id.exer_url3);url4=v.findViewById(R.id.exer_url4);url5=v.findViewById(R.id.exer_url5);
        exer1 = v.findViewById(R.id.exer_opt1);exer2 = v.findViewById(R.id.exer_opt2);exer3 = v.findViewById(R.id.exer_opt3);exer4 = v.findViewById(R.id.exer_opt4);exer5 = v.findViewById(R.id.exer_opt5);

        if(level==1){
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