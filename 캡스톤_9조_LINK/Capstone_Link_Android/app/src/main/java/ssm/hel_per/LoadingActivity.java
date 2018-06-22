package ssm.hel_per;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by super on 2018-05-01.
 */

public class LoadingActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startLoading();
    }

    private void startLoading(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 3000);
    }
}
