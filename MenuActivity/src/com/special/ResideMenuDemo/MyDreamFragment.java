package com.special.ResideMenuDemo;

import com.avos.avoscloud.LogUtil.log;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MyDreamFragment extends Fragment {
	private ListView listview;
	Bundle saveBundle;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		saveBundle=savedInstanceState;
		View rootView = inflater.inflate(R.layout.pakage, container, false);
		 listview   = (ListView) rootView.findViewById(R.id.listView);	 
		setupViews(rootView);
		TurnControl.flagGet = 1;
		return rootView;
	}
	
	@SuppressLint("NewApi")
	private void setupViews(View rootView) {
        
        final AnimalListAdapter arrayAdapter = new AnimalListAdapter (getActivity());
		listview.setAdapter(arrayAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            		TurnControl.curFragment = 10;
            		TurnControl.selectPackage = i;
            		changeFragment(new PictureFrament());
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