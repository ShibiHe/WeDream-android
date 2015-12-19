package com.special.ResideMenuDemo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.LogUtil.log;
import com.avos.avoscloud.ProgressCallback;
import com.avos.avoscloud.SaveCallback;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PostFragment extends Fragment {
	Bundle saveBundle;
	private static final int PHOTO_PICKED_WITH_DATA = 3021;  
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		saveBundle=savedInstanceState;
		View rootView = inflater.inflate(R.layout.post, container, false);
		//rootView.setBackgroundResource(R.drawable.post_bg1);
		final EditText eText  = (EditText) rootView.findViewById(R.id.post_EditText);
	//	Button postButton = (Button)rootView.findViewById(R.id.post_ImageButton);
	//	Button selectButton = (Button)rootView.findViewById(R.id.post_SelectButton);
		ImageView postButton = (ImageView) rootView.findViewById(R.id.post_post);
        ImageView selectButton = (ImageView) rootView.findViewById(R.id.post_photo);
        ImageView backButton = (ImageView) rootView.findViewById(R.id.post_back);
		   postButton.setOnClickListener(new View.OnClickListener(){
				@Override
				
				public void onClick(View v) {
					AvosDatabase packages = new AvosDatabase();
					Toast.makeText(getActivity(), "successfully", Toast.LENGTH_SHORT).show();
					/*if (packages.update(getActivity(), TurnControl.selectPackage)){
				        TurnControl.curFragment = 2;
				        TurnControl.number++;
			        		changeFragment(new PackageFragment());

					}*/
					AVObject post = new AVObject("Post");
					post.put("content", eText.getText().toString());
					post.put("pubUser", TurnControl.user_ID);
					//if (!TurnControl.photoPath.equals("")) {
					AVFile file;
					//log.e("photo","ok!!");
					try {
						
						file = AVFile.withAbsoluteLocalPath(TurnControl.user_ID+TurnControl.photoNumber+".jpg", TurnControl.photoPath);
						file.saveInBackground(new SaveCallback() {
							
							@Override
							public void done(AVException arg0) {
								// TODO Auto-generated method stub
								if (arg0!= null) {
									log.e("post error", "can not post the photo");
								}
								else {
									log.e("post error", "can post the photo");
								}
							}
						}, new ProgressCallback() {
							
							@Override
							public void done(Integer arg0) {
								// TODO Auto-generated method stub
								log.e("post progress", "uploading" + arg0);
							}
						});
						post.put("picture", file);
						TurnControl.photoNumber++;
						log.e("photo","ok!");
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
						log.e("post error", "not found the photo!"+" " + TurnControl.photoPath);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
						log.e("post error", "ioexception!");
					}
					TurnControl.photoPath = "";
					//}
					//else {
					//	log.e("post error", "not a photo!");
					//}
					post.saveInBackground();
					//
				}
		    	
		    });
		   
		   selectButton.setOnClickListener(new View.OnClickListener(){
				@Override
				
				public void onClick(View v) {
					Intent i = new Intent(
	                        Intent.ACTION_PICK,
	                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	  
	                startActivityForResult(i, TurnControl.RESULT_LOAD_IMAGE);
				}
		    	
		    });
		   
		   backButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				TurnControl.curFragment = 0;
        			changeFragment(new HomeFragment());
			}
		});
		return rootView;
	}
	
	
	
	 private void changeFragment(Fragment targetFragment){
	        getActivity().getSupportFragmentManager()
	                .beginTransaction()
	                .replace(R.id.main_fragment, targetFragment, "fragment")
	                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
	                .commit();
	    }
	 

}
