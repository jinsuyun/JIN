package org.techtown.media.video.player;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

/**
 * 동영상 재생 방법에 대해 알 수 있습니다.
 *
 * @author Mike
 *
 */
public class MainActivity extends AppCompatActivity {

    static final String VIDEO_URL = "https://ko.gl/youtube.php?download=aHR0cHM6Ly9yMi0tLXNuLWE1bWVrbnNkLmdvb2dsZXZpZGVvLmNvbS92aWRlb3BsYXliYWNrP3JlcXVpcmVzc2w9eWVzJmR1cj0zNzguNDM4JmlkPW8tQUowT1NodThMaV90UDVVUFY2bU9aU21rdXFqLVJFLXoyd24xbWE5TEtnNXEmbW49c24tYTVtZWtuc2QlMkNzbi1hNW03bG5seiZwbD0yMyZtbT0zMSUyQzI5Jm1zPWF1JTJDcmR1Jm12PW0mbXQ9MTUyNzc3MDE5NSZrZXk9eXQ2JmlwPTIwOS4xNDEuMzQuMjM5Jm1pbWU9dmlkZW8lMkZtcDQmZWk9MWV3UFc4ZnVDb21aLWdQcW02WFFCQSZjPVdFQiZpbml0Y3duZGJwcz0yNDAwMDAmaXRhZz0yMiZmdmlwPTImbG10PTE1Mjc2OTEyNDg4MDE0OTAmZXhwaXJlPTE1Mjc3OTE5MjUmc291cmNlPXlvdXR1YmUmaXBiaXRzPTAmcmF0ZWJ5cGFzcz15ZXMmc3BhcmFtcz1kdXIlMkNlaSUyQ2lkJTJDaW5pdGN3bmRicHMlMkNpcCUyQ2lwYml0cyUyQ2l0YWclMkNsbXQlMkNtaW1lJTJDbW0lMkNtbiUyQ21zJTJDbXYlMkNwbCUyQ3JhdGVieXBhc3MlMkNyZXF1aXJlc3NsJTJDc291cmNlJTJDZXhwaXJlJnRpdGxlPSVFQiVBMCU4OCVFQiU5MyU5QyVFQiVCMiVBOCVFQiVCMiVCMyslRUIlQTAlODglRUMlQTAlODQlRUIlOTMlOUMrbXIlRUMlQTAlOUMlRUElQjElQjAlMkIlRUIlOUQlQkMlRUMlOUQlQjQlRUIlQjglOEMrJUVCJUFBJUE4JUVDJTlEJThDKyUyOCVFMyU4NSU4RSVFMyU4NCVCNyVFMyU4NCVCNy4uLi4uLiUyOSsma2VlcGFsaXZlPXllcyZzaWduYXR1cmU9OTc1N0Y2NUNBQTcxOTNGOUU0REM3OTczRDI2MzVDQTk2QUI0MENCMS45NEM4MDEyRUU3QkMxNUJCRUY4QkMzRjI1MUY0RjQzRDg2RTA1MTkx&title=%EB%A0%88%EB%93%9C%EB%B2%A8%EB%B2%B3%20%EB%A0%88%EC%A0%84%EB%93%9C%20mr%EC%A0%9C%EA%B1%B0+%EB%9D%BC%EC%9D%B4%EB%B8%8C%20%EB%AA%A8%EC%9D%8C%20(%E3%85%8E%E3%84%B7%E3%84%B7......)";
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn = (Button) findViewById(R.id.startBtn);
        Button volumeBtn = (Button) findViewById(R.id.volumeBtn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                videoView.seekTo(0);
                videoView.start();
            }
        });

        volumeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AudioManager mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
                int maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, AudioManager.FLAG_SHOW_UI);
            }
        });

        videoView = (VideoView) findViewById(R.id.videoView);

        MediaController mc = new MediaController(this);
        videoView.setMediaController(mc);
        videoView.setVideoURI(Uri.parse(VIDEO_URL));
        videoView.requestFocus();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer player) {
                Toast.makeText(getApplicationContext(), "동영상이 준비되었습니다.\n'재생' 버튼을 누르세요.", Toast.LENGTH_LONG).show();
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer player) {
                Toast.makeText(getApplicationContext(), "동영상 재생이 완료되었습니다.", Toast.LENGTH_LONG).show();
            }
        });

    }

    protected void onResume() {
        Toast.makeText(getApplicationContext(), "동영상 준비중입니다.\n잠시 기다려주세요.", Toast.LENGTH_LONG).show();

        super.onResume();
    }

}
