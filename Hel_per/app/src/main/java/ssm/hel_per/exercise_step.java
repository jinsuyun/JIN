package ssm.hel_per;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class exercise_step extends AppCompatActivity {
    int value=600;
    TextView mText;
    Button mbutton;

    ImageView swingImage;
    ImageView skyImage;

    Animation shakeAnimation;
    Animation dropAnimation;
    Animation flowAnimation;

 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_cycle);

     mText=(TextView)findViewById(R.id.text_cycle);
     mbutton=(Button)findViewById(R.id.nextbtn_cycle);

     new CountDownTimer(600 * 1000, 1000){

         @Override
         public void onTick(long millisUntilFinished) { // 총 시간과 주기
             value--;
             mText.setText("남은 시간  " + value + "초");
//             if(value==0){
//                 Intent intent = new Intent(exercise_cycle.this,exercise_buffet.class);
//                 startActivity(intent);
//                 finish();
//             }
         }

         @Override
         public void onFinish() {

         }
     }.start();  // 타이머 시작
     mbutton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(exercise_step.this,exercise_buffet.class);
             startActivity(intent);
             finish();
         }
     });

     // swing 이미지에 애니메이션 객체 설정
     swingImage = (ImageView) findViewById(R.id.swingImage);
     shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);
     swingImage.setAnimation(shakeAnimation);

     // water 이미지에 애니메이션 객체 설정
     // waterImage = (ImageView) findViewById(R.id.waterImage);
     dropAnimation = AnimationUtils.loadAnimation(this, R.anim.drop);
     //waterImage.setAnimation(dropAnimation);

     // sky 이미지에 애니메이션 객체 설정
     skyImage = (ImageView) findViewById(R.id.skyImage);
     flowAnimation = AnimationUtils.loadAnimation(this, R.anim.flow);
     skyImage.setAnimation(flowAnimation);
     flowAnimation.setAnimationListener(new AnimationAdapter());

    // Resources res = getResources();
     /*Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.sky_background);

     int bitmapWidth = bitmap.getWidth();
     int bitmapHeight = bitmap.getHeight();

     ViewGroup.LayoutParams params = skyImage.getLayoutParams();
     params.width = bitmapWidth;
     params.height = bitmapHeight;

     skyImage.setImageBitmap(bitmap);*/

     //flowAnimation.setAnimationListener(new AnimationAdapter());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);


        if (hasFocus) {
            shakeAnimation.start();
            dropAnimation.start();
            flowAnimation.start();
        } else {
            shakeAnimation.reset();
            dropAnimation.reset();
            flowAnimation.reset();
        }

    }



    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

    }



    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();

    }


    /**
     * 애니메이션의 시작과 종료 시점을 알기 위한 리스너
     */
    private final class AnimationAdapter implements Animation.AnimationListener {

        public void onAnimationStart(Animation animation) {

        }

        public void onAnimationEnd(Animation animation) {

        }

        public void onAnimationRepeat(Animation animation) {

        }

    }

}
