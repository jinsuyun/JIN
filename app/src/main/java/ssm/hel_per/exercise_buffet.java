package ssm.hel_per;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class exercise_buffet extends AppCompatActivity {
    TextView mText;
    Button mButton;
    int value=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_buffet);

        mText=(TextView)findViewById(R.id.text);
        mButton=(Button)findViewById(R.id.nextbtn);

        new CountDownTimer(10*1000, 1000){

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


