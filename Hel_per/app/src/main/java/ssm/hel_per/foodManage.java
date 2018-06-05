package ssm.hel_per;

import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import static android.support.constraint.Constraints.TAG;

public class foodManage  extends Fragment {
    View v,v2;
    FrameLayout food;
    String bt;
    ImageView graph;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.food_manage, container, false);
        v2 = inflater.inflate(R.layout.food_calori, container, false);
        food = (FrameLayout)v.findViewById(R.id.food);
        graph =(ImageView)v2.findViewById(R.id.graph);
        bt = getActivity().getIntent().getStringExtra("bodytype");

        Log.d(TAG,"SSIBAL"+bt);

        if(bt !=null) {
            if (bt.equals("LW") || bt.equals("LF") || bt.equals("LB") || bt.equals("SB") || bt.equals("SW")) {
                food.setBackgroundResource(R.drawable.thin_food);
                graph.setImageResource(R.drawable.thin_recommned);
                food.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialog = new Dialog(getActivity());

                        dialog.setContentView(R.layout.food_calori);
                        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        dialog.show();
                    }
                });
            }  else {


                food.setBackgroundResource(R.drawable.food_recommend);
                graph.setImageResource(R.drawable.food_graph);
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