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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodselectActivity extends AppCompatActivity {

    private RecyclerView mFoodListView;
    private FoodListAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    Button calButton;
    Button addButton;
    Button registButton;
    TextView sumCalText;

    ArrayList<CustomFoodContent> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodselect);

        calButton = (Button) findViewById(R.id.calFix);
        addButton = (Button) findViewById(R.id.addFood);
        registButton = (Button) findViewById(R.id.registerFood);
//        ReviewData task = new ReviewData();
//        task.execute(R_number);


        calButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sumCalText = findViewById(R.id.sumCalories);
                sumCalText.setText(sumCalories(data) + " kcal"); // 실시간 갱신 필요
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodselectActivity.this,CustomfoodActivity.class);
                startActivityForResult(intent, 3000);
            }
        });

        registButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sumCalText = findViewById(R.id.sumCalories);
                sumCalText.setText(sumCalories(data) + " kcal"); // 실시간 갱신 필요
                //서버에 칼로리 저장
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

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
        sumCalText.setText(sumCalories(data) + " kcal"); // 실시간 갱신 필요

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

    public int sumCalories(ArrayList<CustomFoodContent> al){
        int sum = 0;

        for(int i = 0; i < al.size(); i++) {
            sum = sum + al.get(i).getConsumeCal();
        }

        return sum;
    }
}

class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodListViewHolder>{

    private ArrayList<CustomFoodContent> foodData;

    public void setData(ArrayList<CustomFoodContent> list){
        foodData = list;
    }

    @Override
    public FoodListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // 사용할 아이템의 뷰를 생성해준다.
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_list, parent, false);

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

    private void removeItemView(int position) {
        foodData.remove(position);
        notifyItemChanged(position);
        notifyItemRangeChanged(position, foodData.size());
    }

    class FoodListViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView amount;
        public TextView consumeCal;
        public ImageView delbtn;

        public FoodListViewHolder(View foodView) {
            super(foodView);

            name = foodView.findViewById(R.id.regFoodName);
            amount = foodView.findViewById(R.id.regFoodAmount);
            consumeCal = foodView.findViewById(R.id.regFoodCalories);
            delbtn = foodView.findViewById(R.id.delBtn);

            delbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeItemView(getAdapterPosition());

                }
            });
        }
    }
}
