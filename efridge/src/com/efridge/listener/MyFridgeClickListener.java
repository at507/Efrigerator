// rat issue #6 : 9/23/2012
// Just created this activity
package com.efridge.listener;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.efridge.activity.AddFoodActivity;
import com.efridge.activity.R;

public class MyFridgeClickListener extends Activity implements OnClickListener {

	boolean isDelVisible = false;
/*	ImageButton delBtn;
	
	public ImageButton getDelBtn() {
		return delBtn;
	}

	public void setDelBtn(ImageButton delBtn) {
		this.delBtn = delBtn;
	}
*/
	@Override
	public void onClick(View v) {
		
		
		if (v.getId() == R.id.addFoodBtn) {
			
			Intent intent = new Intent(v.getContext(), AddFoodActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			v.getContext().startActivity(intent);
			
		} /*else if(v.getId() == R.id.editFoodBtn){

			System.out.println("---------------");
			
			ImageButton delBtn = (ImageButton)findViewById(R.id.delBtn);
			isDelVisible = !isDelVisible;
			
			System.out.println("delBtn : " + delBtn);
			if(isDelVisible)
				delBtn.setVisibility(View.VISIBLE);
			else 
				delBtn.setVisibility(View.INVISIBLE);
		}*/
	}
}