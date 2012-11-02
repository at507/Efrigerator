package com.efridge.activity;

import com.efridge.model.FoodModel;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateFoodDetailsActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) { // TODO
		
		setContentView(R.layout.addfood_layout);
		super.onCreate(savedInstanceState);
		
		Bundle extras = getIntent().getExtras();
		long foodId = extras.getLong("foodId");
		
		final FoodModel foodDetails = FoodModel.getFoodById(foodId);
		showFoodDetails(foodDetails);
		
		Button saveFoodBtn = (Button)findViewById(R.id.saveBtn);
		saveFoodBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				EditText foodName = (EditText)findViewById(R.id.foodNameEdit);
				EditText foodDescription = (EditText)findViewById(R.id.foodDescriptionEdit);
				
//				FoodModel foodModel = new FoodModel();
				foodDetails.setFoodName(foodName.getText().toString());
				foodDetails.setFoodDescription(foodDescription.getText().toString());
				foodDetails.setExpiryDate(1);
				
				foodDetails.updateFoodDetails();
				setMessage();
				
			}
		});
	}
	
	
	private void showFoodDetails(FoodModel foodDetails){
		
		TextView foodExpTextView = (TextView)findViewById(R.id.expDate);
		foodExpTextView.setText("10th Oct, 2012");
		
		TextView foodNameTextView = (TextView)findViewById(R.id.foodNameEdit);
		foodNameTextView.setText(foodDetails.getFoodName());
		
		TextView foodDescriptionTextView = (TextView)findViewById(R.id.foodDescriptionEdit);
		foodDescriptionTextView.setText(foodDetails.getFoodDescription());
	}
	
	private void setMessage(){
		
		EditText foodName = (EditText)findViewById(R.id.foodNameEdit);
		TextView msgTextView = (TextView)findViewById(R.id.msgStr);
		
		msgTextView.setText("Modified " + foodName.getText() + " stored in eFridge");
	}
}
