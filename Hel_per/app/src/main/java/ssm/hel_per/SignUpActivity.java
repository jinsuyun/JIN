package ssm.hel_per;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import customfonts.MyEditText;

public class SignUpActivity extends AppCompatActivity {

    ImageView sback;
    TextView createbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        sback = (ImageView)findViewById(R.id.sback);
        sback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(it);
            }
        });

        createbutton = (TextView) findViewById(R.id.create);
        createbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(it);
            }
        });

    }
}
