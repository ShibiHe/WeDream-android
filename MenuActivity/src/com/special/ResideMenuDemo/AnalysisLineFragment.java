package com.special.ResideMenuDemo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.avos.avoscloud.LogUtil.log;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.special.ResideMenuDemo.R;

import android.support.v4.app.Fragment;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AnalysisLineFragment extends Fragment{
	private LineChart mChart; 
	View LineView;
    private Typeface tf;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
		LineView = inflater.inflate(R.layout.analysis_bar, container, false);
        initView();
        
     // no description text
        mChart.setDescription("");

        // enable value highlighting
        mChart.setHighlightEnabled(true);

        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawGridBackground(true);
        
      //  tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        
        XAxis x = mChart.getXAxis();
      //  x.setTypeface(tf);
        x.setEnabled(true);
        
        YAxis y = mChart.getAxisLeft();
      //  y.setTypeface(tf);
        y.setLabelCount(5);
        y.setEnabled(true);
        
        mChart.getAxisRight().setEnabled(true);

        // add data
        catchData();
        
        
        Date dt=new Date();
	    SimpleDateFormat matter1=new SimpleDateFormat("dd");
	    int days = Integer.parseInt(matter1.format(dt));
        log.e("days = "+days);
        setData(days - 10, 10, 100);
        
        mChart.getLegend().setEnabled(true);
        
        mChart.animateXY(2000, 2000);

        // dont forget to refresh the drawing
        /*ArrayList<LineDataSet> sets = (ArrayList<LineDataSet>) mChart.getData()
                .getDataSets();
        
        for (LineDataSet set : sets) {
            if (set.isDrawCubicEnabled())
                set.setDrawCubic(false);
            else
                set.setDrawCubic(true);
        }*/
        mChart.invalidate();
           
        return LineView;
	}
	
	public void initView(){
		mChart = (LineChart)LineView.findViewById(R.id.chart2);
	}
	
	public void catchData(){
		TurnControl.PunchPerDay.add(10);
        TurnControl.PunchPerDay.add(10);
        TurnControl.PunchPerDay.add(10);
        TurnControl.PunchPerDay.add(10);
        TurnControl.PunchPerDay.add(10);
        TurnControl.PunchPerDay.add(10);
        TurnControl.PunchPerDay.add(10);
        TurnControl.PunchPerDay.add(10);
        TurnControl.PunchPerDay.add(10);
        TurnControl.PunchPerDay.add(10);
        TurnControl.PunchPerDay.add(10);
        TurnControl.PunchPerDay.add(10);
        TurnControl.PunchPerDay.add(10);
        TurnControl.PunchPerDay.add(10);
        TurnControl.PunchPerDay.add(10);
        TurnControl.PunchPerDay.add(10);
        TurnControl.PunchPerDay.add(10);
        TurnControl.PunchPerDay.add(10);
        TurnControl.PunchPerDay.add(10);
        TurnControl.PunchPerDay.add(10);
        TurnControl.PunchPerDay.add(10);
        TurnControl.PunchPerDay.add(10);
        TurnControl.PunchPerDay.add(10);
        TurnControl.PunchPerDay.add(10);
	}
	private void setData(int start, int count, float range) {

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            xVals.add((start + i) + "日");
        }

        ArrayList<Entry> vals1 = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {
            vals1.add(new Entry(TurnControl.PunchPerDay.get(start + i), i));
        }
        
        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(vals1, "近期打卡情况");
        set1.setDrawCubic(true);
        set1.setCubicIntensity(0.2f);
        set1.setDrawFilled(true);
        set1.setDrawCircles(true); 
        set1.setLineWidth(2f);
        set1.setCircleSize(5f);
        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setColor(Color.rgb(104, 241, 175));
        set1.setFillColor(ColorTemplate.getHoloBlue());
        
        // create a data object with the datasets
        LineData data = new LineData(xVals, set1);
        data.setValueTypeface(tf);
        data.setValueTextSize(9f);
        data.setDrawValues(false);

        // set data
        mChart.setData(data);
    }
}
