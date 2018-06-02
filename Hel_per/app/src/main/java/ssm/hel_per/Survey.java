package ssm.hel_per;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;

public class Survey extends AppCompatActivity {
    TextView createbutton;
    ImageView sback;
    int career_select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        Intent it1 = getIntent();
        career_select = (int) it1.getSerializableExtra("alert");


        /*---------------운동 경력 선택 --------------------*/
        findViewById(R.id.ca_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                career_select=1;
            }
        });
        findViewById(R.id.ca_one_three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                career_select=2;
            }
        });
        findViewById(R.id.ca_three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                career_select=3;
            }
        });

        sback = (ImageView)findViewById(R.id.sback);
        sback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(Survey.this, SignUpActivity.class);
                startActivity(it);
            }
        });

        createbutton = (TextView) findViewById(R.id.create);
        createbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Survey.this, Main2Activity.class);
                it.putExtra("alert",career_select);
                startActivity(it);
            }
        });
    }
}
