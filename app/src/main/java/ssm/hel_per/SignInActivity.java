package ssm.hel_per;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

public class SignInActivity extends AppCompatActivity {

    public static String url = "http://13.209.40.50:3000/login"; // 웹
    //public static String url = "http://192.168.0.58:3000/login"; // 로컬
    boolean signinCheck = false;
    boolean surveyCheck = false;
    String id;
    String pw;
    String name;
    String sex;
    int age;
    double weight;
    double height;
    double targetweight;
    int targetperiod;
    int worklevel;
    int workperiod;
    String bodytype;
    ImageView sback;
    TextView signinButton;
    Toolbar toolbar;
    EditText etid;
    EditText etpw;
    Handler handler = new Handler();
    public int alert= 1234;  // 설문조사 여부를 위한 토큰
    boolean saveLoginData;
    SharedPreferences appData;
    CheckBox autoLogin;
    String urlStr = url;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id)
        {
            case android.R.id.home:
                Intent it = new Intent(SignInActivity.this,MainActivity.class);
                startActivity(it);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // 설정값 불러오기
        appData = getSharedPreferences("appData", MODE_PRIVATE);
        load();
        signinButton=(TextView)findViewById(R.id.create);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setHomeAsUpIndicator(R.drawable.left);

        etid = (EditText) findViewById(R.id.usrusr);
        etpw = (EditText) findViewById(R.id.pswrd);
        autoLogin = (CheckBox) findViewById(R.id.checkBox);

        // if autoLogin checked, get input

        // 이전에 로그인 정보를 저장시킨 기록이 있다면
        if (saveLoginData) {
            etid.setText(id);
            etpw.setText(pw);
            autoLogin.setChecked(saveLoginData);
        }
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 로그인 성공시 저장 처리
                save();
                ConnectThread thread = new ConnectThread(urlStr, id, pw);
                thread.start();
            }
        });
    }

    class ConnectThread extends Thread {
        String urlStr;
        String id;
        String password;

        public ConnectThread(String inStr, String id, String password) {
            this.urlStr = inStr;
            this.id = id;
            this.password = password;
        }

        public void run() {
            try {
                final String output = request(urlStr, id, password);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject obj = new JSONObject(output);
                            signinCheck = obj.getBoolean("success");
                            try {
                                surveyCheck = obj.getBoolean("survey");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(signinCheck) {
                                name = obj.getString("name");

                                if(!surveyCheck) { // 설문조사x
                                    Intent it = new Intent(SignInActivity.this, Survey.class);
                                    it.putExtra("id", id);
                                    it.putExtra("name", name);
                                    startActivity(it);
                                    finish();

                                } else { // 설문조사o
                                    alert = 1;
                                    sex = obj.getString("sex");
                                    age = obj.getInt("age");
                                    weight = obj.getDouble("weight");
                                    height = obj.getDouble("height");
                                    targetweight = obj.getDouble("targetweight");
                                    targetperiod = obj.getInt("targetperiod");
                                    worklevel = obj.getInt("worklevel");//일주일에 얼마나 운동하는지
                                    workperiod = obj.getInt("workperiod");//그동안 운동을 얼마나 해봤는지
                                    bodytype = obj.getString("bodytype");

                                    Intent it = new Intent(SignInActivity.this, Main2Activity.class);
                                    it.putExtra("id", id);
                                    it.putExtra("alert", alert);
                                    it.putExtra("name",name);
                                    it.putExtra("sex",sex);
                                    it.putExtra("age",age);
                                    it.putExtra("targetperiod",targetperiod);
                                    it.putExtra("targetweight",targetweight);
                                    it.putExtra("worklevel",worklevel);
                                    it.putExtra("workperiod",workperiod);
                                    it.putExtra("bodytype",bodytype);
                                    it.putExtra("weight",weight);
                                    it.putExtra("height",height);

                                    startActivity(it);
                                    finish();
                                }

                            } else {
                                Toast.makeText(getApplicationContext(), "로그인 실패!", Toast.LENGTH_LONG).show();
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
        private String request(String urlStr, String id, String password) throws IOException {
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
    // 설정값을 저장하는 함수
    private void save() {
        // SharedPreferences 객체만으론 저장 불가능 Editor 사용
        SharedPreferences.Editor editor = appData.edit();

        // 에디터객체.put타입( 저장시킬 이름, 저장시킬 값 )
        // 저장시킬 이름이 이미 존재하면 덮어씌움
        editor.putBoolean("SAVE_LOGIN_DATA", autoLogin.isChecked());
        editor.putString("id", etid.getText().toString().trim());
        editor.putString("pw", etpw.getText().toString().trim());

        // apply, commit 을 안하면 변경된 내용이 저장되지 않음
       editor.apply();
    }

    // 설정값을 불러오는 함수
    private void load() {
        // SharedPreferences 객체.get타입( 저장된 이름, 기본값 )
        // 저장된 이름이 존재하지 않을 시 기본값
        saveLoginData = appData.getBoolean("SAVE_LOGIN_DATA", false);
        id = appData.getString("id", "");
        pw = appData.getString("pw", "");
    }
}