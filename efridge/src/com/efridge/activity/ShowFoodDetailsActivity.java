package com.efridge.activity;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.efridge.model.FoodModel;

public class ShowFoodDetailsActivity extends BaseActivity { 

	private List<FoodModel> foodList;
	private List<Button> btnsList;
	private long foodId;
	private int selectedBtnIndex;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) { // TODO
		
		setContentView(R.layout.fooddetails_layout);
		super.onCreate(savedInstanceState);
		
		Bundle extras = getIntent().getExtras();
		foodId = extras.getLong("foodId");
		
		foodList = FoodModel.getFoods(); 
		FoodModel foodDetails = FoodModel.getFoodById(foodId);
		
		showFoodDetails(foodDetails);
		addFoodInScrollView();
	}
	
	private void addFoodInScrollView(){
		
		btnsList = new ArrayList<Button>();
		for(int i = 0; i < foodList.size(); i++) {

			LinearLayout foodScrollView = (LinearLayout)findViewById(R.id.foodScrollView);
			
			final FoodModel foodDetails = foodList.get(i);
			final Button btn = new Button(this);
			final int btnIndex = i;
			
			btnsList.add(btn);
			btn.setText("food " + i);
			btn.setBackgroundColor(Color.RED);
			
			LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(120, 60);
			param.setMargins(20, 0, 0, 0);
			foodScrollView.addView(btn, param);
			
			if(foodDetails.getFoodId() == foodId){
				btn.setBackgroundColor(Color.BLUE);
				selectedBtnIndex = i;
			} else {
				btn.setBackgroundColor(Color.CYAN);
			}
			
			btn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					showFoodDetails(foodDetails);
					Button lastSelectedBtn = btnsList.get(selectedBtnIndex);
					selectedBtnIndex = btnIndex;

					lastSelectedBtn.setBackgroundColor(Color.CYAN);
					btn.setBackgroundColor(Color.BLUE);
				}
			});
		}
	}
	
	private void showFoodDetails(FoodModel foodDetails){
		
		TextView foodExpTextView = (TextView)findViewById(R.id.foodexp_details);
		foodExpTextView.setText("10th Oct, 2012");
		
		TextView foodNameTextView = (TextView)findViewById(R.id.foodName_details);
		foodNameTextView.setText(foodDetails.getFoodName());
		
		TextView foodDescriptionTextView = (TextView)findViewById(R.id.foodDescription_details);
		foodDescriptionTextView.setText(foodDetails.getFoodDescription());
	}
}