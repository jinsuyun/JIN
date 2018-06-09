package ssm.hel_per;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FoodselectActivity extends AppCompatActivity {

    public static String urlStr = "http://13.209.40.50:3000/calorie"; // 웹

    private RecyclerView mFoodListView;
    private FoodListAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    Button calButton;
    Button addButton;
    Button registButton;
    TextView sumCalText;
    String id;
    Handler handler = new Handler();

    ArrayList<CustomFoodContent> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodselect);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

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
                ConnectThread thread = new ConnectThread(urlStr, id, sumCalories(data));
                thread.start();
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


    class ConnectThread extends Thread {
        String urlStr;
        String id;
        String workoutday;
        int eat_calories;

        public ConnectThread(String inStr, String id, int eat_calories) {
            this.urlStr = inStr;
            this.id = id;
            this.eat_calories = eat_calories;

        }

        public void run() {
            try {
                final String output = request(urlStr, id, eat_calories);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject obj = new JSONObject(output);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        private String request(String urlStr, String id, int eat_calories) throws IOException {
            StringBuilder output = new StringBuilder();
            try {
                URL url = new URL(urlStr);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                workoutday = simpleDateFormat.format(new Date(System.currentTimeMillis()));

                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                if(conn != null) {
                    String json = "";

                    // build jsonObject
                    JSONObject jsonObject = new JSONObject();

                    jsonObject.put("id", id);
                    jsonObject.put("workoutday", workoutday);
                    jsonObject.put("eat_calories", eat_calories);

                    // convert JSONObject to JSON to String
                    json = jsonObject.toString();
                    conn.setConnectTimeout(1000);
                    conn.setRequestMethod("POST");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    OutputStream os = conn.getOutputStream();
                    os.write(json.getBytes("euc-kr")); // 출력 스트림에 출력.
                    os.flush(); // 출력 스트림을 플러시(비운다)하고 버퍼링 된 모든 출력 바이트를 강제 실행.
                    os.close();

                    int resCode = conn.getResponseCode();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    String line = null;
                    while(true) {
                        line = reader.readLine();
                        if (line == null) {
                            break;
                        }
                        output.append(line + "\n");
                    }
                    reader.close();
                    conn.disconnect();
                }
            } catch (Exception e) {
                Log.e("SampleHTTP", "Exception in processing response.", e);
            }
            return output.toString();
        }
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
