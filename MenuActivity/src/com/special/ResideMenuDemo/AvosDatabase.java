package com.special.ResideMenuDemo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import android.R.integer;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import com.avos.avoscloud.LogUtil.log;
import com.avos.avoscloud.*;

public class AvosDatabase {
	
	void countDatabase(final int flag){
		AVQuery<AVObject> query = AVQuery.getQuery("PackageList"+flag);
		query.whereEqualTo("UserID", TurnControl.user_ID);
		query.countInBackground(new CountCallback() {
			  public void done(int count, AVException e) {
			    if (e == null) {
			      if(flag==1)
			    	  Packages.Num1=count;
			      else {
			    	  Packages.Num2=count;
				}
			    } else {
			      // The request failed
			    }
			  }
			});
	}
	void getDatabase(final int flag){
		
		Packages.CategoryPerent.add("0%");
		Packages.CategoryPerent.add("0%");
		Packages.CategoryPerent.add("0%");
        if (flag == 1){
        	Packages.List1.clear();
        } else{
        	Packages.List2.clear();
        }
		AVQuery<AVObject> query = new AVQuery<AVObject>("PackageList2");
		/*
		Date rtn = null;  
	    GregorianCalendar cal = new GregorianCalendar();  
	    Date date = new Date();  
	    cal.setTime(date);
	    cal.add(2,-1); 
	    Date date1=cal.getTime();
	    AVQuery<AVObject> query1 = new AVQuery<AVObject>("PackageList2");
	    query1.whereEqualTo("UserID",TurnControl.user_ID);
	    query1.whereGreaterThan("createdAt", date1);
        query1.findInBackground(new FindCallback<AVObject>() {
			public void done(List<AVObject> packages, AVException e) {
					TurnControl.number=packages.size();
				}
			});
		*/
        if (flag==1)
        	query.whereEqualTo("UserID",TurnControl.user_ID);
        else
        	query.whereEqualTo("category", "we");
        query.findInBackground(new FindCallback<AVObject>() {
			public void done(List<AVObject> packages, AVException e) {
				
				log.e("ERROR", "flag = " + flag);
				log.e("ERROR", String.valueOf(packages.size()));
				log.e("ERROR", TurnControl.user_ID);

				
				if (packages.size() > 0){
			        if (flag == 1){
			        	Packages.Num1 = packages.size();
			        	
			        }else{
			        	Packages.Num2 = packages.size();
			        }
					for (int i = 0; i < packages.size(); i++)
					{
			        	 PackageInfo good = new PackageInfo();
			        	 good.objectID = packages.get(i).getObjectId();
			             good.company = packages.get(i).getString("company");
			        	 good.category = packages.get(i).getString("category");
			             good.name = packages.get(i).getString("name");
			             good.userID = packages.get(i).getString("UserID");
			        	 good.price = packages.get(i).getString("price");
			        	 good.picture = packages.get(i).getAVFile("picture_1");
//			        	 good.imageLoc = packages.get(i).getString("image");
//			        	 good.image = BitmapFactory.decodeFile(good.imageLoc);
			             if (flag == 1){
			            	 Packages.List1.add(good);	 
			             }else{
			            	 Packages.List2.add(good);

			             } 
					}
				}else{
					//Toast.makeText(context, "No package", Toast.LENGTH_SHORT).show();
				}
			}
		});
        
        AVQuery<AVObject> query_plan = new AVQuery<AVObject>(TurnControl.user_ID+"Info");
		query_plan.whereNotEqualTo("plan", "default");
		query_plan.include("number");
		query_plan.findInBackground(new FindCallback<AVObject>() {
		    public void done(List<AVObject> avObjects, AVException e) {
		        if (e == null) {
		        		//Toast.makeText(getActivity(), "成功查询到:"+ avObjects.size() + " 条符合条件的数据", Toast.LENGTH_SHORT).show();
		            TurnControl.PlanNumber = avObjects.size();
		            for (int i = 0; i <TurnControl.PlanNumber; i++) {
		            		final Plans myplan = new Plans();
						myplan.name = avObjects.get(i).getString("plan"); 
			            AVQuery<AVObject> query_number = new AVQuery<AVObject>("PackageList2");
			            
		    				query_number.whereEqualTo("plan", myplan.name); //计划的名字
		    				//final String string = TurnControl.PlanName.get(i);
		        			query_number.whereEqualTo("UserID", TurnControl.user_ID); //用户名
		        			query_number.findInBackground(new FindCallback<AVObject>() {
		        				public void done(List<AVObject> av, AVException e) {
		        			        if (e == null) {
		        			        		Log.e("get error", myplan.name+"+"+ av.size());
		        						myplan.number = av.size();
		        						TurnControl.Plan.add(myplan);
		        			        } else {
		        			            Log.e("get error", "get error " + e.getMessage());
		        			        }
		        			    }

		        			});

					}
		        } else {
		            Log.d("失败", "查询错误: " + e.getMessage());
		        }
		    }

		});
		
   	 	//Log.e("get error", "get started");
	     for (int i = 0; i < 11; i++) { //找前10天的数据
	    	 //Log.e("get error", "get error " + i);
//			 calendar.add(Calendar.DATE, -1); //得到前一天
//			 Date thisdate = calendar.getTime();
//			 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    	 	 GregorianCalendar cal_today = new GregorianCalendar();  
	 	     Date todays = new Date();  
	 	     cal_today.setTime(todays);
	 	     cal_today.add(5,-i); 
	 	     Date thisdate=cal_today.getTime();
	 	     Log.e("not found", thisdate.toString());
			 AVQuery<AVObject> query_date = new AVQuery<AVObject>("PackageList2");
			 final int index = i;
			 
			 query_date.whereGreaterThan("createdAt", thisdate);
 			 query_date.whereEqualTo("UserID", TurnControl.user_ID); //用户名
 			 
 			 query_date.countInBackground(new CountCallback() {
				public void done(int count, AVException e) {
			        if (e == null) {
			        		Log.e("get error", "data + " + count);
						TurnControl.PunchPerDay[index] = count;
			        } else {
			            Log.e("not found", "get error " + e.getMessage());
			        }
			    }
 			});
 			
		 }
		 
    }
	
	boolean update(final Context context, final int num){
		PackageInfo tmpList1Package = Packages.List1.get(num);
		
		AVObject tmpPackage = new AVObject("PackageList2");
		tmpPackage.put("name", tmpList1Package.name);
		//tmpPackage.put("objectId", tmpList1Package.objectID);
		tmpPackage.put("category", tmpList1Package.category);
		tmpPackage.put("price", tmpList1Package.price);
		tmpPackage.put("company", tmpList1Package.company);
		tmpPackage.put("image", tmpList1Package.imageLoc);
		tmpPackage.put("picture_1", tmpList1Package.picture);
		tmpPackage.put("UserID", TurnControl.user_ID);
		tmpPackage.saveInBackground();
		
		AVQuery<AVObject> query = new AVQuery<AVObject>("PackageList1");
 		query.whereEqualTo("name", tmpList1Package.name);
		
 		query.deleteAllInBackground(new DeleteCallback() {
			@Override
			public void done(AVException e) {
				if (e == null){
					Toast.makeText(context, "Delete Success", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(context, "Delete Failed", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
 		log.e("ERROR",String.valueOf(Packages.List1.size()));
 		Packages.SumPrice += Double.parseDouble(tmpList1Package.price.substring(1));
 		switch (tmpList1Package.category) {
		case "life-sytle":
//			Packages.CategorySum.set(1, Packages.CategorySum.get(1) + Integer.parseInt(tmpList1Package.price));
			Packages.CategoryLife += Double.parseDouble(tmpList1Package.price.substring(1));
			break;
		case "education":
//			Packages.CategorySum.set(2, Packages.CategorySum.get(2) + Integer.parseInt(tmpList1Package.price));
			Packages.CategoryEdu += Double.parseDouble(tmpList1Package.price.substring(1));
			break;
		case "3C":
//			Packages.CategorySum.set(3, Packages.CategorySum.get(3) + Integer.parseInt(tmpList1Package.price));
			Packages.Category3C += Double.parseDouble(tmpList1Package.price.substring(1));
			break;
		}

 		Packages.updatePercent();
 		
 		Packages.List2.add(tmpList1Package);
 		Packages.Num2++;
 		Packages.List1.remove(num);
 		Packages.Num1--;
		return true;
	}


	
}
