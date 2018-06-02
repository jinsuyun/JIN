package ssm.hel_per;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class exercise_cycle extends AppCompatActivity {
    int value=0;
    TextView mText;
    Button mbutton;

 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_cycle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

     mText=(TextView)findViewById(R.id.text);
     mbutton=(Button)findViewById(R.id.nextbtn);

     new CountDownTimer(10 * 1000, 1000){

         @Override
         public void onTick(long millisUntilFinished) { // 총 시간과 주기
             value++;
             mText.setText("남은 시간=" + value);
         }

         @Override
         public void onFinish() {

         }
     }.start();  // 타이머 시작
     
    }


}
