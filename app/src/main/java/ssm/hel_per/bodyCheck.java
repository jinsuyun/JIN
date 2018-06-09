package ssm.hel_per;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
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

public class bodyCheck extends Fragment implements Main2Activity.OnBackPressedListener{
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.body_check, container, false);
        super.onCreate(savedInstanceState);

        mainFragment = new home();

        FloatingActionButton floatingActionButton = ((Main2Activity) getActivity()).getFloatingActionButton();
        if (floatingActionButton != null) {
            floatingActionButton.hide();
        }
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

        return v;
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
