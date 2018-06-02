package ssm.hel_per;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import static android.os.SystemClock.sleep;

public class exercise extends Fragment {
    View v,v2;
    Button button;
    CountDownTimer countDownTimer;
    public int MILLISINFUTURE=11*1000;
    public int COUNT_DOWN_INTERVAL=1000;
    TextView countTxt;
    public int count=10;
    ImageView urltest,cycle;
    VideoView video;
    static final String VIDEO_URL = "https://ko.gl/youtube.php?download=aHR0cHM6Ly9yMy0tLXNuLWE1bWVrbnN5Lmdvb2dsZXZpZGVvLmNvbS92aWRlb3BsYXliYWNrP3JhdGVieXBhc3M9eWVzJmVpPU9JUVNXNWJ4SFlqRS1BT3J3NFRJREEmaXBiaXRzPTAmZHVyPTI1Ny41NTUmc291cmNlPXlvdXR1YmUmc3BhcmFtcz1kdXIlMkNlaSUyQ2lkJTJDaW5pdGN3bmRicHMlMkNpcCUyQ2lwYml0cyUyQ2l0YWclMkNsbXQlMkNtaW1lJTJDbW0lMkNtbiUyQ21zJTJDbXYlMkNwbCUyQ3JhdGVieXBhc3MlMkNyZXF1aXJlc3NsJTJDc291cmNlJTJDZXhwaXJlJnJlcXVpcmVzc2w9eWVzJmxtdD0xNTA4MzA2MzY0NTQ0NDM2Jm10PTE1Mjc5NDAwNjAmaWQ9by1BT0VrYUVVaE0zdWhkVmg4T1V3d1lNODRNMHJVVmtNLXBvTWJkWXpQMGZhZiZtcz1hdSUyQ29uciZmdmlwPTMmcGw9MjMmaW5pdGN3bmRicHM9MTY1Mzc1MCZtdj1tJmV4cGlyZT0xNTI3OTYxNzUyJnNpZ25hdHVyZT1FMEM1RTJGNEE0MUFGMjQ2QjU1RDY5NUNFQjZFODFDODQ5MjlGNTc4LjJDRjZENjhCN0Y5NkE3RjI5Njc0MDE3NTMzQ0RDRUJENEUyRDZFMDcmYz1XRUImbWltZT12aWRlbyUyRm1wNCZpcD0yMDkuMTQxLjM0LjIzOSZrZXk9eXQ2Jml0YWc9MjImbW09MzElMkMyNiZtbj1zbi1hNW1la25zeSUyQ3NuLW40djdrbmxsJnRpdGxlPSVFRCU4QyU5NCVFQSVCNSVCRCVFRCU5OCU4MCVFRCU4RSVCNCVFQSVCOCVCMCslRUMlOUQlOTgrJUVDJUEwJTk1JUVDJTg0JTlELislRUElQjAlODAlRUMlOUUlQTUrXyslRUMlOTklODQlRUIlQjIlQkQlRUQlOTUlOUMrJUVEJTkxJUI4JUVDJTg5JUFDJUVDJTk3JTg1K18rJUVDJTlFJTkwJUVDJTg0JUI4KyVFQiVCMCVCMCVFQyU5QSVCMCVFQSVCOCVCMCslRUElQjUlOTAlRUIlQjMlQjgrJmtlZXBhbGl2ZT15ZXM=&title=%ED%8C%94%EA%B5%BD%ED%98%80%ED%8E%B4%EA%B8%B0%20%EC%9D%98%20%EC%A0%95%EC%84%9D.%20%EA%B0%80%EC%9E%A5%20%22%20%EC%99%84%EB%B2%BD%ED%95%9C%20%ED%91%B8%EC%89%AC%EC%97%85%20%22%20%EC%9E%90%EC%84%B8%20%EB%B0%B0%EC%9A%B0%EA%B8%B0%20%EA%B5%90%EB%B3%B8";
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.exercise, container, false);
        v2 = inflater.inflate(R.layout.exercise_timer, container, false);

        button = (Button)v.findViewById(R.id.exercise);

        countTxt = (TextView)v.findViewById(R.id.count);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.exercise_timer);
                dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                dialog.show();
                sleep(3);
                countDownTimer();
                countDownTimer.start();
            }
        });

        cycle = (ImageView)v.findViewById(R.id.cycle);
        cycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());

                dialog.setContentView(R.layout.cycleinformation);
                dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                dialog.show();
            }
        });

        urltest = (ImageView)v.findViewById(R.id.test1);
        urltest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());

                dialog.setContentView(R.layout.exercise_dialog);
                dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                video=(VideoView)dialog.findViewById(R.id.test_video);
                video.seekTo(0);
                video.start();
                MediaController mc = new MediaController(getActivity());
                video.setMediaController(mc);
                video.setVideoURI(Uri.parse(VIDEO_URL));
                video.requestFocus();
                dialog.show();
            }
        });

        return v;
    }
    public void countDownTimer(){
        countDownTimer=new CountDownTimer(MILLISINFUTURE,COUNT_DOWN_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                countTxt.setText(String.valueOf(count));
                count--;
            }

            @Override
            public void onFinish() {
                countTxt.setText(String.valueOf(count));
            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try{
            countDownTimer.cancel();
        }catch (Exception e){
            countDownTimer=null;
        }
    }
}
