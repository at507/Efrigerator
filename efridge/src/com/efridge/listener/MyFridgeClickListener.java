// rat issue #6 : 9/23/2012
// Just created this activity
package com.efridge.listener;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.efridge.activity.AddFoodActivity;
import com.efridge.activity.R;

public class MyFridgeClickListener implements OnClickListener {

	@Override
	public void onClick(View v) {
		
		if (v.getId() == R.id.addFoodBtn) {
			
			Intent intent = new Intent(v.getContext(), AddFoodActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			v.getContext().startActivity(intent);
		}
	}
}