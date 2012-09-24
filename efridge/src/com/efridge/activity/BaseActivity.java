// rat issue #2 : 9/23/2012
// Add tab for navigation with option
// All other activities will inherit this activity

package com.efridge.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.efridge.listener.TabListener;

public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
        Button calendarTab = (Button) findViewById(R.id.calendarTab);
        Button myFridgeTab = (Button) findViewById(R.id.myFridgeTab);
        Button settingTab = (Button) findViewById(R.id.settingTab);
        
        TabListener tabListener = new TabListener();
        
        calendarTab.setOnClickListener(tabListener);
        myFridgeTab.setOnClickListener(tabListener);
        settingTab.setOnClickListener(tabListener);
	}
}
