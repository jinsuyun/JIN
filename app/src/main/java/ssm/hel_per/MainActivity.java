package ssm.hel_per;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.kakao.auth.ErrorCode;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;

import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;


import org.json.JSONObject;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity{

    TextView sin;
    LinearLayout circle;
    private CallbackManager callbackManager; // facebook callback
    SessionCallback callback; // Kakao callback

    OAuthLogin mOAuthLoginModule;
    OAuthLoginButton mOAuthLoginButton;
    Context context;

    private OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
        @Override
        public void run(boolean success) {
            if (success) {
                final String accessToken = mOAuthLoginModule.getAccessToken(context);
                String refreshToken = mOAuthLoginModule.getRefreshToken(context);
                long expiresAt = mOAuthLoginModule.getExpiresAt(context);
                String tokenType = mOAuthLoginModule.getTokenType(context);
            } else {
                String errorCode = mOAuthLoginModule.getLastErrorCode(context).getCode();
                String errorDesc = mOAuthLoginModule.getLastErrorDesc(context);
                Toast.makeText(context, "errorCode:" + errorCode
                        + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
            }
        };
    };

    //--------------------------Kakao Callback 세션 구현 시작---------------------
    private class SessionCallback implements ISessionCallback{

        @Override
        public void onSessionOpened() {

            UserManagement.requestMe(new MeResponseCallback() {

                @Override
                public void onFailure(ErrorResult errorResult) {
                    String message = "failed to get user info. msg=" + errorResult;

                    ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                    if (result == ErrorCode.CLIENT_ERROR_CODE) {
//                        finish();
                    } else {
                        //redirectMainActivity();
                    }
                }

                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                }

                @Override
                public void onNotSignedUp() {

                }

                @Override
                public void onSuccess(UserProfile userProfile) {
                    //로그인에 성공하면 로그인한 사용자의 일련번호, 닉네임, 이미지url등을 리턴합니다.
                    //사용자 ID는 보안상의 문제로 제공하지 않고 일련번호는 제공합니다.
//                    Log.e("UserProfile", userProfile.toString());
//                    Log.e("UserProfile", userProfile.getId() + "");
                    long number = userProfile.getId();
                }
            });
        }
        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            // 세션 연결이 실패했을때
            // 어쩔때 실패되는지는 테스트를 안해보았음 ㅜㅜ

        }
    }
// --------------------------- KakaoCallback 세션 구현 종료------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_main);

        callback = new SessionCallback(); // kakao
        callbackManager = CallbackManager.Factory.create(); // facebook
        mOAuthLoginButton = (OAuthLoginButton)findViewById(R.id.buttonOAuthLoginImg);
        mOAuthLoginModule = OAuthLogin.getInstance();
        mOAuthLoginModule.init(
                MainActivity.this
                ,"nek0P4eWN_YUNJ8Jabqz"
                ,"lshlhUMHho"
                ,"superb422"
        );

        if(mOAuthLoginModule.getAccessToken(this) != null){
            startActivity(new Intent(this,MainActivity.class));
        } else {
            mOAuthLoginButton.setOAuthLoginHandler(mOAuthLoginHandler);
        }
        Session.getCurrentSession().addCallback(callback);

        LoginButton face_loginButton = (LoginButton)findViewById(R.id.face_login_button);
        face_loginButton.setReadPermissions(Arrays.asList("public_profile","email"));
        face_loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.v("result",object.toString());
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("field","id,name,email,gender,birthday");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Log.e("LoginErr",error.toString());
            }
        });

        circle = (LinearLayout)findViewById(R.id.circle);
        sin = (TextView)findViewById(R.id.create_2);

        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(it);

            }
        });
        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,SignInActivity.class);
                startActivity(it);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
