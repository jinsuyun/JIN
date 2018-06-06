package ssm.hel_per;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomfoodActivity extends AppCompatActivity {

    private RecyclerView mCustomFoodView;
    private FoodSelectAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    Button regButton;
    ArrayList<CustomFoodContent> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customfood);

        regButton = findViewById(R.id.foodregButton);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 등록 버튼
            }
        });

        // RecyclerView binding
        mCustomFoodView = findViewById(R.id.mList);
        // init LayoutManager
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // 기본값이 VERTICAL
        // setLayoutManager
        mCustomFoodView.setLayoutManager(mLayoutManager);

        for(int i = 0; i < 10; i++) {
            data.add(new CustomFoodContent("음식이름 " + i, "음식 양 " + i, i));
        }
        // init Adapter
        mAdapter = new FoodSelectAdapter();
        // set Data
        mAdapter.setData(data);
        // set Adapter
        mCustomFoodView.setAdapter(mAdapter);
    }

}

class FoodSelectAdapter extends RecyclerView.Adapter<CustomFoodViewHolder> {

    private ArrayList<CustomFoodContent> foodData;

    public void setData(ArrayList<CustomFoodContent> list){
        foodData = list;
    }

    @Override
    public CustomFoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // 사용할 아이템의 뷰를 생성해준다.
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_content, parent, false);

        CustomFoodViewHolder holder = new CustomFoodViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(CustomFoodViewHolder holder, final int position) {
        CustomFoodContent data = foodData.get(position);

        holder.name.setText(data.getFoodName());
        holder.amount.setText(data.getAmount());
        holder.consumeCal.setText(data.getConsumeCal() + " kcal");

    }

    @Override
    public int getItemCount() {
        return foodData.size();
    }

}

class CustomFoodViewHolder extends RecyclerView.ViewHolder {

    public TextView name;
    public TextView amount;
    public TextView consumeCal;

    public CustomFoodViewHolder(View foodView) {
        super(foodView);

        name = foodView.findViewById(R.id.foodName);
        amount = foodView.findViewById(R.id.foodAmount);
        consumeCal = foodView.findViewById(R.id.foodCalories);
    }

}