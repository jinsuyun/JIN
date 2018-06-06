package ssm.hel_per;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FoodselectActivity extends AppCompatActivity {

    private RecyclerView mFoodListView;
    private FoodListAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    Button addButton;
    Button registButton;
    TextView sumCalText;

    ArrayList<CustomFoodContent> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodselect);

        addButton = (Button) findViewById(R.id.addFood);
        registButton = (Button) findViewById(R.id.registerFood);
//        ReviewData task = new ReviewData();
//        task.execute(R_number);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(FoodselectActivity.this,CustomfoodActivity.class);
                startActivityForResult(intent, 3000);
                // intent를 이용하여 목록 추가

            }
        });

        registButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        int sum = 0;
        // RecyclerView binding
        mFoodListView = findViewById(R.id.mSelectList);
        // init LayoutManager
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // 기본값이 VERTICAL
        // setLayoutManager
        mFoodListView.setLayoutManager(mLayoutManager);

        // init Adapter
        mAdapter = new FoodListAdapter();
        // set Data
        mAdapter.setData(data);
        // set Adapter
        mFoodListView.setAdapter(mAdapter);

        sumCalText = findViewById(R.id.sumCalories);

        for(int i = 0; i < data.size(); i++) {
            sum = sum + data.get(i).getConsumeCal();
        }
        sumCalText.setText(sum + " kcal");


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resdata) {
        super.onActivityResult(requestCode, resultCode, resdata);
        if(resultCode == RESULT_OK) {
            switch(requestCode) {
                case 3000 :
                    data.add(new CustomFoodContent(resdata.getStringExtra("name"), resdata.getStringExtra("amount"), resdata.getIntExtra("consumeCal", 0)));
                    // 데이터 추가
                    break;
            }
        }
    }
}

class FoodListAdapter extends RecyclerView.Adapter<FoodListViewHolder> {

    private ArrayList<CustomFoodContent> foodData;

    public void setData(ArrayList<CustomFoodContent> list){
        foodData = list;
    }

    @Override
    public FoodListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // 사용할 아이템의 뷰를 생성해준다.
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_content, parent, false);

        FoodListViewHolder holder = new FoodListViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(FoodListViewHolder holder, final int position) {
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

class FoodListViewHolder extends RecyclerView.ViewHolder {

    public TextView name;
    public TextView amount;
    public TextView consumeCal;

    public FoodListViewHolder(View foodView) {
        super(foodView);

        name = foodView.findViewById(R.id.foodName);
        amount = foodView.findViewById(R.id.foodAmount);
        consumeCal = foodView.findViewById(R.id.foodCalories);
    }

}