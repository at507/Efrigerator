// rat issue #2 : 9/23/2012
// Add tab for navigation with option

package com.efridge.listener;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.efridge.activity.CalendarActivity;
import com.efridge.activity.MyFridgeActivity;
import com.efridge.activity.R;
import com.efridge.activity.SettingActivity;

public class TabListener implements OnClickListener {

	@Override
	public void onClick(View v) {

		if (v.getId() == R.id.calendarTab) {
			
			Intent intent = new Intent(v.getContext(), CalendarActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			v.getContext().startActivity(intent);
			
		} else	if (v.getId() == R.id.myFridgeTab) {
			
			Intent intent = new Intent(v.getContext(), MyFridgeActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			v.getContext().startActivity(intent);
			
		} else	if (v.getId() == R.id.settingTab) {
			
			Intent intent = new Intent(v.getContext(), SettingActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			v.getContext().startActivity(intent);
		}
	}
}
