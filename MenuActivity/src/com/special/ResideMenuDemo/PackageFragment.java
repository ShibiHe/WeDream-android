package com.special.ResideMenuDemo;


import com.avos.avoscloud.AVFile;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenuDemo.AnimalListAdapter.ViewHolder;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import at.technikum.mti.fancycoverflow.FancyCoverFlow;
import at.technikum.mti.fancycoverflow.FancyCoverFlowAdapter;



	/**
	 * A placeholder fragment containing a simple view.
	 */
	@SuppressLint("NewApi")
	public class PackageFragment extends Fragment {
		private FancyCoverFlow fancyCoverFlow;
		Bundle saveBundle;
	    private ResideMenu resideMenu;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			saveBundle=savedInstanceState;
			View rootView = inflater.inflate(R.layout.pakage, container, false);
			 //listview   = (ListView) rootView.findViewById(R.id.listView);	
			 fancyCoverFlow = (FancyCoverFlow) rootView.findViewById(R.id.fancyCoverFlow);
			 this.fancyCoverFlow.setUnselectedAlpha(1.0f);
		        this.fancyCoverFlow.setUnselectedSaturation(0.0f);
		        this.fancyCoverFlow.setUnselectedScale(0.5f);
		        this.fancyCoverFlow.setSpacing(50);
		        this.fancyCoverFlow.setMaxRotation(0);
		        this.fancyCoverFlow.setScaleDownGravity(0.2f);
		        this.fancyCoverFlow.setActionDistance(FancyCoverFlow.ACTION_DISTANCE_AUTO);
		     //fancyCoverFlow.setAdapter(new ViewGroupExampleAdapter());
			setupViews(rootView);
    		TurnControl.flagGet = 2;
			return rootView;
		}
		
		@SuppressLint("NewApi")
		private void setupViews(View rootView) {
	        MenuActivity parentActivity = (MenuActivity) getActivity();
			resideMenu = parentActivity.getResideMenu();
		    FrameLayout ignored_view = (FrameLayout) rootView.findViewById(R.id.ignored_view);
		    resideMenu.addIgnoredView(ignored_view);
	        final ViewGroupExampleAdapter arrayAdapter = new ViewGroupExampleAdapter (getActivity());

			TurnControl.flagGet = 2;
			fancyCoverFlow.setAdapter(arrayAdapter);
	        
            
			fancyCoverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                		TurnControl.curFragment = 10;
                		TurnControl.selectPackage = i;
                		changeFragment1(new PictureFrament());
                }	
            });
		}
		 private void changeFragment1(Fragment targetFragment){
			 
		        getActivity().getSupportFragmentManager()
		                .beginTransaction()
		                .replace(R.id.main_fragment, targetFragment, "fragment")
		                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
		                .commit();
		 }
		 private static class ViewGroupExampleAdapter extends FancyCoverFlowAdapter {

		        // =============================================================================
		        // Private members
		        // =============================================================================
		        private int[] images = {R.drawable.qr, R.drawable.qr, R.drawable.qr, R.drawable.qr, R.drawable.qr, R.drawable.qr,};
		    	private LayoutInflater mInflater = null;

		        // =============================================================================
		        // Supertype overrides
		        // =============================================================================

		        public ViewGroupExampleAdapter(Context context){
		    		super();
		            mInflater = (LayoutInflater) context
		    		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    	}
		        @Override
		        public int getCount() {
		            return Packages.Num2;
		        }

		        @Override
		        public Integer getItem(int i) {
		            return images[i];
		        }

		        @Override
		        public long getItemId(int i) {
		            return i;
		        }
		        
		        @Override
		        public View getCoverFlowItem(int i, View reuseableView, ViewGroup viewGroup) {
		            CustomViewGroup customViewGroup = null;

		            if (reuseableView != null) {
		                customViewGroup = (CustomViewGroup) reuseableView;
		            } else {
		                customViewGroup = new CustomViewGroup(viewGroup.getContext());
		                customViewGroup.setLayoutParams(new FancyCoverFlow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		            }

		            //customViewGroup.getImageView().setImageResource(this.getItem(i));
		            customViewGroup.getImageView().setAVFile(Packages.List2.get(i).picture);
		            customViewGroup.getImageView().loadInBackground(false);;
		            //customViewGroup.getTextView().setText(String.format("Item %d", i));

		            return customViewGroup;
		        }
		    }

		    private static class CustomViewGroup extends LinearLayout {

		        // =============================================================================
		        // Child views
		        // =============================================================================

		        private TextView textView;

		        private AVImageView imageView;

		        private Button button;

		        // =============================================================================
		        // Constructor
		        // =============================================================================

		        private CustomViewGroup(Context context) {
		            super(context);

		            this.setOrientation(VERTICAL);

		            this.textView = new TextView(context);
		            this.imageView = new AVImageView(context);
		            this.button = new Button(context);

		            LinearLayout.LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		            this.textView.setLayoutParams(layoutParams);
		            this.imageView.setLayoutParams(layoutParams);
		            this.button.setLayoutParams(layoutParams);

		            this.textView.setGravity(Gravity.CENTER);

		            this.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
		           // this.imageView.setAdjustViewBounds(true);
		            this.imageView.setLayoutParams(new LayoutParams(900,1000));
		            this.button.setText("");
		            this.button.setOnClickListener(new OnClickListener() {
		                @Override
		                public void onClick(View view) {
		                    
		                }
		            });

		            this.addView(this.imageView);
		            this.addView(this.textView);

		            //this.addView(this.button);
		        }

		        // =============================================================================
		        // Getters
		        // =============================================================================

		        private TextView getTextView() {
		            return textView;
		        }

		        private AVImageView getImageView() {
		            return imageView;
		        }
		        /*private void setImageView(AVFile file){
		        	imageView.setAVFile(file);
		    		imageView.loadInBackground();
		        }*/
		    }
	}
		

