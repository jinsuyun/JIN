package ssm.hel_per;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class writeDiary extends Fragment{
    View v;
    private HorizontalBarChart horizontalBarChart;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.diary, container, false);
        horizontalBarChart = (HorizontalBarChart) v.findViewById(R.id.bmi_chart);

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(10, 100));


        BarDataSet barDataSet = new BarDataSet(entries, "BMI");

        barDataSet.setColor(Color.parseColor("#FFA1B4DC"));

        barDataSet.setDrawValues(false);

        BarData lineData = new BarData(barDataSet);
        horizontalBarChart.setData(lineData);

        XAxis xAxis = horizontalBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);
        xAxis.enableGridDashedLine(8, 24, 0);

        YAxis yLAxis = horizontalBarChart.getAxisLeft();
        yLAxis.setTextColor(Color.BLACK);

        YAxis yRAxis = horizontalBarChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        Description description = new Description();
        description.setText("");

        horizontalBarChart.setDoubleTapToZoomEnabled(false);
        horizontalBarChart.setDrawGridBackground(false);
        horizontalBarChart.setDescription(description);
        horizontalBarChart.animateY(2000, Easing.EasingOption.EaseInCubic);
        horizontalBarChart.invalidate();

        return v;
    }
}
