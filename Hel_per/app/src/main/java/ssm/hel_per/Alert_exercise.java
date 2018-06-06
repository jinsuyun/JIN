package ssm.hel_per;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import static android.support.constraint.Constraints.TAG;

public class Alert_exercise extends AppCompatActivity {

    public CharSequence[] items ={"1.유산소", "2.웨이트", "3.무작위"};
    public  CharSequence[] weighttrain={"1.가슴","2.등","3.어깨","4.하체","5.팔","6.복근"};
    public AlertDialog.Builder builder;
    public FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_exercise);
        manager = getFragmentManager();

        builder = new AlertDialog.Builder(this);

        builder.setTitle("오늘은 어떤 운동을 하시겠습니까?")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which)
                        {
                            case 0: // 유산소
                                manager.beginTransaction().replace(R.id.activity_alert,new exercise()).commit();
                                //finish();
                                break;
                            case 1: // 웨이트
                                builder = new AlertDialog.Builder(getApplicationContext());
                                builder.setTitle("운동부위를 선택하세요")
                                        .setItems(weighttrain, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                switch(which) {
                                                    case 0: // 가슴
                                                        manager.beginTransaction().replace(R.id.activity_alert,new exercise()).commit();
                                                        break;
                                                    case 1: // 등
                                                        manager.beginTransaction().replace(R.id.activity_alert,new exercise()).commit();
                                                        break;
                                                    case 2: // 어깨
                                                        manager.beginTransaction().replace(R.id.activity_alert,new exercise()).commit();
                                                        break;
                                                    case 3: // 하체
                                                        manager.beginTransaction().replace(R.id.activity_alert,new exercise()).commit();
                                                        break;
                                                    case 4: // 팔
                                                        manager.beginTransaction().replace(R.id.activity_alert,new exercise()).commit();
                                                        break;
                                                    case 5: // 복근
                                                        manager.beginTransaction().replace(R.id.activity_alert,new exercise()).commit();
                                                        break;
                                                }

                                            }
                                        }).show();
                                break;
                            case 2: // 무작위
                                manager.beginTransaction().replace(R.id.activity_alert,new exercise()).commit();
                                break;
                        }

                    }

                }).show();

    }
}
