package com.special.ResideMenuDemo;

import java.security.PublicKey;


import java.util.ArrayList;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.LogUtil.log;
import com.avos.avoscloud.SaveCallback;
import com.special.ResideMenu.ResideMenu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PictureFrament extends Fragment {
		ArrayList<PackageInfo> list = Packages.List2;
		View parentView;
		AVImageView imageView;
		TextView textView;
		TextView textView2;
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		   parentView = inflater.inflate(R.layout.picture, container, false);
		   initview();
	       return parentView;
	         
	    }
	    public void initview(){
	    	if (TurnControl.flagGet == 1) 
	    		list = Packages.List1; else
	    			list = Packages.List2;
	    	imageView= (AVImageView) parentView.findViewById(R.id.picture);
//	    	imageView.setImageResource(R.drawable.qr);
	    	textView = (TextView) parentView.findViewById(R.id.twoDCode);
	    	textView2 = (TextView) parentView.findViewById(R.id.twoDCode2);
	    	AVFile picture_big = list.get(TurnControl.selectPackage).picture; 
	    	String introduction = list.get(TurnControl.selectPackage).name;
	    	String introduction2 = list.get(TurnControl.selectPackage).price;

	    	imageView.setAVFile(picture_big);
	    	imageView.loadInBackground();
	    	textView.setText(introduction);
	    	textView2.setText(introduction2);
	    	
	    	Button btn = (Button)parentView.findViewById(R.id.get_button);
		    btn.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v) {
					String objectID = list.get(TurnControl.selectPackage).objectID;
					String tableName = new String();
					if (TurnControl.flagGet == 1)
						tableName = "PackageList1";
					else
						tableName = "PackageList2";
					AVObject post = AVObject.createWithoutData(tableName, objectID);
					//更新属性
					Integer dianzan = Integer.parseInt(list.get(TurnControl.selectPackage).price) + 1;
					post.put("price", dianzan.toString());
					//保存
					post.saveInBackground();
//					Toast.makeText(getActivity(), "successful", Toast.LENGTH_LONG);
					textView2.setText(dianzan.toString());
				}
		    	
		    });
	    }
		 private void changeFragment(Fragment targetFragment){
		        getActivity().getSupportFragmentManager()
		                .beginTransaction()
		                .replace(R.id.main_fragment, targetFragment, "fragment")
		                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
		                .commit();
		    }
}
