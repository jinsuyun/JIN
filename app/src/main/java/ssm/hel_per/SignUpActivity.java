package ssm.hel_per;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SignUpActivity extends AppCompatActivity {

    public static String url = "http://13.209.40.50:3000/signup"; // 웹
    //public static String url = "http://192.168.0.58:3000/signup"; // 로컬
    boolean signupCheck = false;

    ImageView sback;
    TextView createbutton;
    EditText etid;
    EditText etpw;
    EditText etn;
    EditText etem;
    Handler handler = new Handler();

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

        etid = (EditText) findViewById(R.id.regid);
        etpw = (EditText) findViewById(R.id.regpw);
        etn = (EditText) findViewById(R.id.regname);
        etem = (EditText) findViewById(R.id.regemail);

        createbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String urlStr = url;
                String id = etid.getText().toString();
                String pw = etpw.getText().toString();
                String name = etn.getText().toString();
                String email = etem.getText().toString();

                ConnectThread thread = new ConnectThread(urlStr, id, pw, name, email);
                thread.start();
            }
        });
    }

    class ConnectThread extends Thread {
        String urlStr;
        String id;
        String password;
        String name;
        String email;

        public ConnectThread(String inStr, String id, String password, String name, String email) {
            this.urlStr = inStr;
            this.id = id;
            this.password = password;
            this.name = name;
            this.email = email;
        }

        public void run() {
            try {
                final String output = request(urlStr, id, password, name, email);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject obj = new JSONObject(output);
                            signupCheck = obj.getBoolean("success");
                            if(signupCheck) {
                                Toast.makeText(getApplicationContext(), "회원가입 성공!.", Toast.LENGTH_LONG).show();
                                Intent it = new Intent(SignUpActivity.this, SignInActivity.class);
                                startActivity(it);
                                finish();
                            } else {
                                if(obj.getBoolean("valid")) {
                                    Toast.makeText(getApplicationContext(), "중복된 ID입니다.", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "빈 칸을 채워주세요.", Toast.LENGTH_LONG).show();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        private String request(String urlStr, String id, String password, String name, String email) throws IOException {
            StringBuilder output = new StringBuilder();
            try {
                URL url = new URL(urlStr);

                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                if(conn != null) {
                    String json = "";

                    // build jsonObject
                    JSONObject jsonObject = new JSONObject();

                    jsonObject.put("id", id);
                    jsonObject.put("password", password);
                    jsonObject.put("name", name);
                    jsonObject.put("email", email);

                    // convert JSONObject to JSON to String
                    json = jsonObject.toString();
                    conn.setConnectTimeout(1000);
                    conn.setRequestMethod("POST");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    OutputStream os = conn.getOutputStream();
                    os.write(json.getBytes("euc-kr")); // 출력 스트림에 출력.
                    os.flush(); // 출력 스트림을 플러시(비운다)하고 버퍼링 된 모든 출력 바이트를 강제 실행.
                    os.close();

                    int resCode = conn.getResponseCode();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    String line = null;
                    while(true) {
                        line = reader.readLine();
                        if (line == null) {
                            break;
                        }
                        output.append(line + "\n");
                    }
                    reader.close();
                    conn.disconnect();
                }
            } catch (Exception e) {
                Log.e("SampleHTTP", "Exception in processing response.", e);
            }
            return output.toString();
        }
    }
}
