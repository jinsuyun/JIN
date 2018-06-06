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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FoodselectActivity extends AppCompatActivity {

    Button addButton;
    Button registButton;

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
                startActivity(intent);
                // intent를 이용하여 목록 추가

            }
        });

        registButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
