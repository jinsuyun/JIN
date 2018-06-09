package ssm.hel_per;

import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import static android.support.constraint.Constraints.TAG;

public class foodManage  extends Fragment {
    View v,v2;
    FrameLayout food;
    String bt;
    ImageView graph;
    Button button;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.food_manage, container, false);
        v2 = inflater.inflate(R.layout.food_calori, container, false);
        food = (FrameLayout)v.findViewById(R.id.food);
        graph =(ImageView)v2.findViewById(R.id.graph);
        button = (Button) v.findViewById(R.id.customFood);
        bt = getActivity().getIntent().getStringExtra("bodytype");
        FloatingActionButton floatingActionButton = ((Main2Activity) getActivity()).getFloatingActionButton();
        if (floatingActionButton != null) {
            floatingActionButton.hide();
        }
        Log.d(TAG,"SSIBAL"+bt);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(),FoodselectActivity.class);
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
}