package com.special.ResideMenuDemo;

import java.util.ArrayList;
import java.util.List;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class AnalysisBarFragment extends Fragment {
	private PieChart pieChart;
	View pieView;
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        
        catchData();
		pieView = inflater.inflate(R.layout.analysis_pie, container, false);
        initView();
        pieChart.setHoleColorTransparent(true);   
        
        pieChart.setHoleRadius(60f);  //半径   
        pieChart.setTransparentCircleRadius(64f); // 半透明圈   
        //pieChart.setHoleRadius(0)  //实心圆   
     
        pieChart.setDescription("");   
     
        // mChart.setDrawYValues(true);   
        pieChart.setDrawCenterText(true);  //饼状图中间可以添加文字   
     
        pieChart.setDrawHoleEnabled(true);   
     
        pieChart.setRotationAngle(90); // 初始旋转角度   
     
        // draws the corresponding description value into the slice   
        // mChart.setDrawXValues(true);   
     
        // enable rotation of the chart by touch   
        pieChart.setRotationEnabled(true); // 可以手动旋转   
     
        // display percentage values   
        pieChart.setUsePercentValues(true);  //显示成百分比  
        pieChart.setCenterTextSize(20);
        
        pieChart.setTextAlignment(10);
        // mChart.setUnit(" €");   
        // mChart.setDrawUnitsInChart(true);   
     
        // add a selection listener   
//      mChart.setOnChartValueSelectedListener(this);   
        // mChart.setTouchEnabled(false);   
     
//      mChart.setOnAnimationListener(this);   
     
        pieChart.setCenterText("各计划打卡比例");  //饼状图中间的文字   
        
        //设置数据   
        PieData pieData = getPieData(TurnControl.PlanNumber, 100);
        pieChart.setData(pieData);    
             
        // undo all highlights   
//      pieChart.highlightValues(null);   
//      pieChart.invalidate();   
     
        Legend mLegend = pieChart.getLegend();  //设置比例图   
        mLegend.setPosition(LegendPosition.RIGHT_OF_CHART);  //最右边显示   
//      mLegend.setForm(LegendForm.LINE);  //设置比例图的形状，默认是方形   
        mLegend.setXEntrySpace(20f);   
        mLegend.setYEntrySpace(5f);   
             
        pieChart.animateXY(1000, 1000);  //设置动画   
        // mChart.spin(2000, 0, 360); 
        
        return pieView;
	}
	public void initView(){
    		pieChart = (PieChart)pieView.findViewById(R.id.chart1);
	}
	
	public void catchData(){
		Toast.makeText(getActivity(), "分别是:"+ TurnControl.Plan.get(0).name, Toast.LENGTH_SHORT).show();
		Toast.makeText(getActivity(), "分别是:"+ TurnControl.Plan.get(1).name, Toast.LENGTH_SHORT).show();
		Toast.makeText(getActivity(), "分别是:"+ TurnControl.Plan.get(2).name, Toast.LENGTH_SHORT).show();
		
		Toast.makeText(getActivity(), "分别是:"+ TurnControl.Plan.get(0).name, Toast.LENGTH_SHORT).show();
		Toast.makeText(getActivity(), "分别是:"+ TurnControl.Plan.get(1).name, Toast.LENGTH_SHORT).show();
		Toast.makeText(getActivity(), "分别是:"+ TurnControl.Plan.get(2).name, Toast.LENGTH_SHORT).show();

		
//        TurnControl.PlanName.add("toefl");
//        TurnControl.PlanName.add("GRE");
//        TurnControl.PlanName.add("ETS");
//        TurnControl.PlanName.add("GPA");
//        TurnControl.PlanStatic.add(10);
//        TurnControl.PlanStatic.add(10);
//        TurnControl.PlanStatic.add(10);
//        TurnControl.PlanStatic.add(10);
	}
	private PieData getPieData(int count, float range) {   
        
        ArrayList<String> xValues = new ArrayList<String>();  //xVals用来表示每个饼块上的内容   
     
        for (int i = 0; i < count; i++) {   
            xValues.add(TurnControl.Plan.get(i).name);  //饼块上显示成各计划的名字  
        }   
     
        ArrayList<Entry> yValues = new ArrayList<Entry>();  //yVals用来表示封装每个饼块的实际数据   
     
        // 饼图数据   
        /** 
         * 将一个饼形图分成四部分， 四部分的数值比例为14:14:34:38 
         * 所以 14代表的百分比就是14%  
         */   
         
        
        for (int i = 0; i < count; i++) {   
            yValues.add(new Entry(TurnControl.Plan.get(i).number, i));    
        }   
     
     
        //y轴的集合   
        PieDataSet pieDataSet = new PieDataSet(yValues, "所有计划"/*显示在比例图上*/);   
        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离   
     
        ArrayList<Integer> colors = new ArrayList<Integer>();   
     
        // 饼图颜色 
        for (int i = 0; i < count; i++) {
        		
		}
        
        /* colors.add(Color.rgb(205, 205, 205));   
        colors.add(Color.rgb(114, 188, 223));   
        colors.add(Color.rgb(255, 123, 124));   
        colors.add(Color.rgb(57, 135, 200)); 
     	*/
        pieDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
    //    pieDataSet.setColors(colors);   
        pieDataSet.setValueTextSize(12f);
        DisplayMetrics metrics = getResources().getDisplayMetrics();   
        float px = 5 * (metrics.densityDpi / 160f);   
        pieDataSet.setSelectionShift(px); // 选中态多出的长度   
     
        PieData pieData = new PieData(xValues, pieDataSet);   
             
        return pieData;   
    }
}

