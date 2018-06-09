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

import static android.support.constraint.Constraints.TAG;

public class foodManage  extends Fragment implements Main2Activity.OnBackPressedListener{
    home mainFragment;
    View v,v2;
    FrameLayout food;
    String bt;
    ImageView graph;
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
        graph =(ImageView)v2.findViewById(R.id.graph);
        bt = getActivity().getIntent().getStringExtra("bodytype");

        Log.d(TAG,"SSIBAL"+bt);

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
            if (bt.equals("LW") || bt.equals("LF") || bt.equals("LB")) {
                food.setBackgroundResource(R.drawable.menu_2100);
                graph.setImageResource(R.drawable.nut_2100);
                food.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialog = new Dialog(getActivity());

                        dialog.setContentView(R.layout.food_calori);
                        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        dialog.show();
                    }
                });
            } else if (bt.equals("SB") || bt.equals("SW")) {
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