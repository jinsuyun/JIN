package ssm.hel_per;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomfoodActivity extends AppCompatActivity {

    private RecyclerView mCustomFoodView;
    private FoodSelectAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    ArrayList<CustomFoodContent> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customfood);

        // RecyclerView binding
        mCustomFoodView = findViewById(R.id.mList);
        // init LayoutManager
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // 기본값이 VERTICAL
        // setLayoutManager
        mCustomFoodView.setLayoutManager(mLayoutManager);

        data.add(new CustomFoodContent("밥(공기)", 1," 공기", 310));
        data.add(new CustomFoodContent("삶은계랑(개)", 1, " 개", 80));
        data.add(new CustomFoodContent("잡곡밥(공기)", 1," 공기", 340));
        data.add(new CustomFoodContent("닭가슴살(인분)", 1," 인분", 109));
        data.add(new CustomFoodContent("김밥(줄)", 1," 줄", 318));
        data.add(new CustomFoodContent("양상추(인분)", 1, " 인분", 11));
        data.add(new CustomFoodContent("아보카도(인분)", 1, " 인분", 187));
        data.add(new CustomFoodContent("떡볶이(인분)", 1, " 인분", 280));
        data.add(new CustomFoodContent("찐고구마(개)", 1, " 개", 128));
        data.add(new CustomFoodContent("배추김치(소접시)", 1, " 접시", 25));
        data.add(new CustomFoodContent("바나나(개)", 1, " 개", 88));
        data.add(new CustomFoodContent("구운계란(개)", 1, " 개", 73));
        data.add(new CustomFoodContent("삼겹살(인분)", 1, " 인분", 348));
        data.add(new CustomFoodContent("우유(컵)", 1, " 컵", 122));
        data.add(new CustomFoodContent("계란후라이(개)", 1, " 개", 89));
        data.add(new CustomFoodContent("사과(개)", 1, " 개", 130));
        data.add(new CustomFoodContent("수박", 1, " 조각", 31));
        data.add(new CustomFoodContent("방울토마토(개)", 1, " 개", 2));
        data.add(new CustomFoodContent("참외(개)", 1, " 개", 62));
        data.add(new CustomFoodContent("귤(개)", 1, " 개", 39));
        data.add(new CustomFoodContent("김(장)", 1, " 장", 9));
        data.add(new CustomFoodContent("신라면(봉)", 1, " 봉", 500));
        data.add(new CustomFoodContent("삼양라면(봉)", 1, " 봉", 500));
        data.add(new CustomFoodContent("진라면(봉)", 1, " 봉", 500));
        data.add(new CustomFoodContent("너구리라면(봉)", 1, " 봉", 505));
        data.add(new CustomFoodContent("육개장사발면(컵)", 1, " 컵", 375));
        data.add(new CustomFoodContent("튀김우동큰사발면(컵)", 1, " 컵", 495));
        data.add(new CustomFoodContent("한솥도시락(치킨마요)", 1, " 인분", 453));
        data.add(new CustomFoodContent("한솥도시락(도련님도시락)", 1, " 인분", 748));
        data.add(new CustomFoodContent("한솥도시락(돈치고기고기)", 1, " 인분", 754));
        data.add(new CustomFoodContent("맘스터치(싸이버거세트)", 1, " 개", 956));
        data.add(new CustomFoodContent("맘스터치(화이트갈릭세트)", 1, " 개", 1022));
        data.add(new CustomFoodContent("맘스터치(딥치즈버거세트)", 1, " 개", 859));
        data.add(new CustomFoodContent("맘스터치(디럭스불고기버거세트)", 1, " 개", 932));
        data.add(new CustomFoodContent("GS25(참치마요삼각김밥)", 1, " 개", 174));
        data.add(new CustomFoodContent("GS25(춘천닭갈비삼각김밥)", 1, " 개", 167));
        data.add(new CustomFoodContent("GS25(전주비빔삼각김밥)", 1, " 개", 200));
        data.add(new CustomFoodContent("두부(1/4모)", 1, " 개", 79));
        data.add(new CustomFoodContent("육쌈냉면(물)", 1, " 인분", 569));
        data.add(new CustomFoodContent("육쌈냉면(비빔)", 1, " 인분", 445));
        data.add(new CustomFoodContent("스타벅스(아이스아메리카노 Tall)", 1, " 개", 10));
        data.add(new CustomFoodContent("스타벅스(아이스카페라떼 Tall)", 1, " 개", 110));
        data.add(new CustomFoodContent("스타벅스(아이스바닐라라떼 Tall)", 1, " 개", 160));
        data.add(new CustomFoodContent("김치찌개(1대접)", 1, " 인분", 243));
        data.add(new CustomFoodContent("제육볶음(1인분)", 1, " 인분", 572));
        data.add(new CustomFoodContent("뚝배기불고기(1인분)", 1, " 인분", 317));
        data.add(new CustomFoodContent("자장면", 1, " 인분", 785));
        data.add(new CustomFoodContent("짬뽕", 1, " 인분", 764));
        data.add(new CustomFoodContent("탕수육(1인분)", 1, " 인분", 591));

        // init Adapter
        mAdapter = new FoodSelectAdapter();
        // set Data
        mAdapter.setData(data);
        // set Adapter
        mCustomFoodView.setAdapter(mAdapter);

        mCustomFoodView.addOnItemTouchListener(new RecyclerViewOnItemClickListener(this, mCustomFoodView, new RecyclerViewOnItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

                Intent resultIntent = new Intent();
                resultIntent.putExtra("name", data.get(position).getFoodName());
                resultIntent.putExtra("num", data.get(position).getNum());
                resultIntent.putExtra("amount", data.get(position).getAmount());
                resultIntent.putExtra("consumeCal", data.get(position).getConsumeCal());
                setResult(RESULT_OK, resultIntent);
                finish();

            }
            @Override
            public void onItemLongClick(View v, int position) {

            }
        }
        ));

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
        holder.amount.setText(data.getNum() + data.getAmount());
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