package ssm.hel_per;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class myState extends Fragment {
    View v;
    double weight=0;
    double height=0;
    double targetweight;
    double bmi=0;
    int targetperiod;
    int worklevel;
    int workperiod;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.my_state, container, false);

        weight=getActivity().getIntent().getDoubleExtra("weight",0);
        targetweight=getActivity().getIntent().getDoubleExtra("targetweight",0);
        targetperiod=getActivity().getIntent().getIntExtra("targetperiod",0);
        worklevel=getActivity().getIntent().getIntExtra("worklevel",0);
        workperiod=getActivity().getIntent().getIntExtra("workperiod",0);
        height=getActivity().getIntent().getDoubleExtra("height",0);


        bmi=bodyAlgo.bmiCal(height,weight);
        float f_weight=Float.parseFloat(String.valueOf(weight));

        float f_targetweight=Float.parseFloat(String.valueOf(targetweight));
        float f_targetperiod=Float.parseFloat(String.valueOf(targetperiod));
        float f_worklevel=Float.parseFloat(String.valueOf(worklevel));
        float f_workperiod=Float.parseFloat(String.valueOf(workperiod));
        float f_bmi=Float.parseFloat(String.valueOf(bmi));

        HorizontalBarChart horizontalBarChart = (HorizontalBarChart) v.findViewById(R.id.chart);

        List<BarEntry> BarEntry_weight = new ArrayList<>();
        BarEntry_weight.add(new BarEntry(0f, f_weight,"체중"));
        List<BarEntry> BarEntry_muscle = new ArrayList<>();
        BarEntry_muscle.add(new BarEntry(1f, 51.2f));
        List<BarEntry> BarEntry_fat = new ArrayList<>();
        BarEntry_fat.add(new BarEntry(2f, 10.5f));
        List<BarEntry> BarEntry_percentage_fat = new ArrayList<>();
        BarEntry_percentage_fat.add(new BarEntry(3f, 16.1f));
        List<BarEntry> BarEntry_bmi = new ArrayList<>();
        BarEntry_bmi.add(new BarEntry(4f, f_bmi));

        BarDataSet barDataSet_weight = new BarDataSet(BarEntry_weight, "체중");
        BarDataSet barDataSet_muscle = new BarDataSet(BarEntry_muscle, "근육량");
        BarDataSet barDataSet_fat = new BarDataSet(BarEntry_fat, "체지방");
        BarDataSet barDataSet_percentage_fat = new BarDataSet(BarEntry_percentage_fat, "체지방율");
        BarDataSet barDataSet_bmi = new BarDataSet(BarEntry_bmi, "BMI");

        barDataSet_weight.setColor(Color.parseColor("#FF0000"));
        barDataSet_muscle.setColor(Color.parseColor("#FF8000"));
        barDataSet_fat.setColor(Color.parseColor("#00FF00"));
        barDataSet_percentage_fat.setColor(Color.parseColor("#0000FF"));
        barDataSet_bmi.setColor(Color.parseColor("#DF01A5"));



        BarData barData = new BarData(barDataSet_weight,barDataSet_muscle,barDataSet_fat,barDataSet_percentage_fat,barDataSet_bmi);
        horizontalBarChart.setData(barData);

        barData.setBarWidth(0.5f);

        XAxis xAxis = horizontalBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //xAxis.setTextColor(Color.YELLOW);

       // xAxis.enableGridDashedLine(8, 24, 0);


        YAxis yLAxis = horizontalBarChart.getAxisLeft();
        //yLAxis.setTextColor(Color.RED);

        YAxis yRAxis = horizontalBarChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        horizontalBarChart.setDoubleTapToZoomEnabled(false);
        horizontalBarChart.setDrawGridBackground(false);
        horizontalBarChart.animateY(2000, Easing.EasingOption.EaseInCubic);
        horizontalBarChart.invalidate();




        LineChart lineChart =v.findViewById(R.id.weight_chart);

        List<Entry> weight_change = new ArrayList<>();
        weight_change.add(new Entry(1f, 65.0f,"체중"));
        weight_change.add(new Entry(2f, 55.0f,"체중"));
        weight_change.add(new Entry(3f, 45.0f,"체중"));
        weight_change.add(new Entry(4f, 35.0f,"체중"));
        weight_change.add(new Entry(5f, 25.0f,"체중"));
        weight_change.add(new Entry(6f, 15.0f,"체중"));


        LineDataSet lineDataSet_weight_change = new LineDataSet(weight_change, "체중");
        lineDataSet_weight_change.setColor(Color.parseColor("#0000FF"));

        LineData lineData = new LineData(lineDataSet_weight_change);
        lineChart.setData(lineData);

        XAxis line_xAxis = lineChart.getXAxis();
        line_xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //xAxis.setTextColor(Color.YELLOW);

        // xAxis.enableGridDashedLine(8, 24, 0);


        YAxis line_yLAxis = lineChart.getAxisLeft();
        //yLAxis.setTextColor(Color.RED);

        YAxis line_yRAxis = lineChart.getAxisRight();
        line_yRAxis.setDrawLabels(false);
        line_yRAxis.setDrawAxisLine(false);
        line_yRAxis.setDrawGridLines(false);

        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.animateY(2000, Easing.EasingOption.EaseInCubic);
        lineChart.invalidate();


        return v;
    }
}
