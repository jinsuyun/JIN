package ssm.hel_per;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class foodManage  extends Fragment implements Main2Activity.OnBackPressedListener{
    home mainFragment;
    View v,v2;
    FrameLayout food;
    String bt;
    ImageView graph;
    TextView tv;
    FloatingActionButton fab;

    public FloatingActionButton getFloatingActionButton() {
        return fab;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.food_manage, container, false);
        v2 = inflater.inflate(R.layout.food_calori, container, false);
        food = (FrameLayout)v.findViewById(R.id.food);
        graph =(ImageView)v2.findViewById(R.id.foodinfograph);
        tv = (TextView) v.findViewById(R.id.reqCalText);
        bt = getActivity().getIntent().getStringExtra("bodytype");

        bodyAlgo bodyalgo = new bodyAlgo();

        int age = getActivity().getIntent().getIntExtra("age", 0);
        double height = getActivity().getIntent().getDoubleExtra("height", 0);
        double weight = getActivity().getIntent().getDoubleExtra("weight", 0);
        String sex = getActivity().getIntent().getStringExtra("sex");
        double targetweight = getActivity().getIntent().getDoubleExtra("targetweight", 0);
        int targetperiod = getActivity().getIntent().getIntExtra("targetperiod", 0);
        int worklevel = getActivity().getIntent().getIntExtra("worklevel", 0);

        int reqCalorie = (int) bodyalgo.eatCal(height, weight, age, sex, worklevel, targetweight, targetperiod);

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

        if(bt !=null) {
            if (reqCalorie < 1150) {
                food.setBackgroundResource(R.drawable.menu_1100);
                graph.setImageResource(R.drawable.nut_1100);
                food.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialog = new Dialog(getActivity());

                        dialog.setContentView(R.layout.food_calori);
                        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        dialog.show();
                    }
                });
            } else if (reqCalorie < 1250 && reqCalorie >= 1150) {
                food.setBackgroundResource(R.drawable.menu_1200);
                graph.setImageResource(R.drawable.nut_1200);
                food.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialog = new Dialog(getActivity());

                        dialog.setContentView(R.layout.food_calori);
                        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        dialog.show();
                    }
                });
            } else if (reqCalorie < 1350 && reqCalorie >= 1250) {
                food.setBackgroundResource(R.drawable.menu_1300);
                graph.setImageResource(R.drawable.nut_1300);
                food.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialog = new Dialog(getActivity());

                        dialog.setContentView(R.layout.food_calori);
                        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        dialog.show();
                    }
                });
            } else if (reqCalorie < 1450 && reqCalorie >= 1350) {
                food.setBackgroundResource(R.drawable.menu_1400);
                graph.setImageResource(R.drawable.nut_1400);
                food.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialog = new Dialog(getActivity());

                        dialog.setContentView(R.layout.food_calori);
                        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        dialog.show();
                    }
                });
            } else if (reqCalorie < 1550 && reqCalorie >= 1450) {
                food.setBackgroundResource(R.drawable.menu_1500);
                graph.setImageResource(R.drawable.nut_1500);
                food.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialog = new Dialog(getActivity());

                        dialog.setContentView(R.layout.food_calori);
                        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        dialog.show();
                    }
                });
            } else if (reqCalorie < 1650 && reqCalorie >= 1550) {
                food.setBackgroundResource(R.drawable.menu_1600);
                graph.setImageResource(R.drawable.nut_1600);
                food.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialog = new Dialog(getActivity());

                        dialog.setContentView(R.layout.food_calori);
                        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        dialog.show();
                    }
                });
            } else if (reqCalorie < 1750 && reqCalorie >= 1650) {
                food.setBackgroundResource(R.drawable.menu_1700);
                graph.setImageResource(R.drawable.nut_1700);
                food.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialog = new Dialog(getActivity());

                        dialog.setContentView(R.layout.food_calori);
                        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        dialog.show();
                    }
                });
            } else if (reqCalorie < 1850 && reqCalorie >= 1750) {
                food.setBackgroundResource(R.drawable.menu_1800);
                graph.setImageResource(R.drawable.nut_1800);
                food.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialog = new Dialog(getActivity());

                        dialog.setContentView(R.layout.food_calori);
                        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        dialog.show();
                    }
                });
            } else if (reqCalorie < 1950 && reqCalorie >= 1850) {
                food.setBackgroundResource(R.drawable.menu_1900);
                graph.setImageResource(R.drawable.nut_1900);
                food.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialog = new Dialog(getActivity());

                        dialog.setContentView(R.layout.food_calori);
                        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        dialog.show();
                    }
                });
            } else {
                food.setBackgroundResource(R.drawable.menu_2000);
                graph.setImageResource(R.drawable.nut_2000);
                food.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialog = new Dialog(getActivity());

                        dialog.setContentView(R.layout.food_calori);
                        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        dialog.show();
                    }
                });
            }
        }
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