package ssm.hel_per;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Re_Survey extends AppCompatActivity {

    public static String url = "http://13.209.40.50:3000/userinput"; // 웹
    //public static String url = "http://192.168.0.58:3000/userinput"; // 로컬
    boolean signupCheck = false;

    TextView createbutton;
    ImageView sback;
    EditText etage;
    EditText etheight;
    EditText etweight;

    EditText ettw;
    EditText ettp;
    EditText etsm;
    EditText etbf;
    RadioGroup rgwp;RadioButton rgwp1;RadioButton rgwp2;RadioButton rgwp3;
    RadioGroup rgsex;RadioButton rgm;RadioButton rgw;
    RadioGroup rgwl;RadioButton rgwl1;RadioButton rgwl2;RadioButton rgwl3;RadioButton rgwl4;RadioButton rgwl5;
    Handler handler = new Handler();
    String tag="TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_survey);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        final String name = intent.getStringExtra("name");

        etage = (EditText) findViewById(R.id.age_re);
        etheight = (EditText) findViewById(R.id.height_re);
        etweight = (EditText) findViewById(R.id.weight_re);
        ettw = (EditText) findViewById(R.id.weighttarget_re);
        ettp = (EditText) findViewById(R.id.timetarget_re);
        etsm = (EditText) findViewById(R.id.musclemass_re);
        etbf = (EditText) findViewById(R.id.fat_re);
        rgsex =(RadioGroup) findViewById(R.id.sex_re);
        rgm=(RadioButton)findViewById(R.id.sex_man_re);
        rgw=(RadioButton)findViewById(R.id.sex_woman_re);
        rgwp = (RadioGroup)  findViewById(R.id.exer_state);
        rgwp1 = (RadioButton) findViewById(R.id.ca_one_re);
        rgwp2 = (RadioButton) findViewById(R.id.ca_one_three_re);
        rgwp3 = (RadioButton) findViewById(R.id.ca_three_re);
        rgwl = (RadioGroup)  findViewById(R.id.exer_level_re);
        rgwl1 = (RadioButton) findViewById(R.id.no_exer_re);
        rgwl2 = (RadioButton) findViewById(R.id.usual_exer_re);
        rgwl3 = (RadioButton) findViewById(R.id.normal_exer_re);
        rgwl4 = (RadioButton) findViewById(R.id.many_exer_re);
        rgwl5 = (RadioButton) findViewById(R.id.heavy_exer_re);

        createbutton = (TextView) findViewById(R.id.create_re);
        createbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etage.getText().toString().equals("") || etheight.getText().toString().equals("") || etweight.getText().toString().equals("")||
                        ettp.getText().toString().equals("") || ettw.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "빈 칸을 채워주세요.", Toast.LENGTH_LONG).show();
                }
                else {
                    String urlStr = url;
                    int age = Integer.parseInt(etage.getText().toString());
                    double height = Float.parseFloat(etheight.getText().toString());
                    double weight = Float.parseFloat(etweight.getText().toString());
                    String select_sex = "";
                    double targetweight = Float.parseFloat(ettw.getText().toString());
                    int targetperiod = Integer.parseInt(ettp.getText().toString());
                    int workperiod;
                    int worklevel;


                    if (rgm.isChecked()) {
                        select_sex = "M";
                    } else if (rgw.isChecked())
                        select_sex = "W";

                    if (rgwp3.isChecked()) {
                        workperiod = 3;
                    } else if (rgwp2.isChecked()) {
                        workperiod = 2;
                    } else {
                        workperiod = 1;
                    }

                    if (rgwl5.isChecked()) {
                        worklevel = 5;
                    } else if (rgwl4.isChecked()) {
                        worklevel = 4;
                    } else if (rgwl3.isChecked()) {
                        worklevel = 3;
                    } else if (rgwl2.isChecked()) {
                        worklevel = 2;
                    } else {
                        worklevel = 1;
                    }

                    ConnectThread thread = new ConnectThread(urlStr, id, name, select_sex, age, weight, height, targetweight, targetperiod, workperiod, worklevel);
                    thread.start();

                    //Intent it = new Intent(Survey.this, Main2Activity.class);
                    //startActivity(it);
                }
            }
        });

    }


    class ConnectThread extends Thread {
        String urlStr;
        String id;
        String name;
        String sex;
        int age;
        double weight;
        double height;
        double targetweight;
        int targetperiod;
        int workperiod;
        int worklevel;

        public ConnectThread(String inStr, String id, String name, String sex, int age, double weight, double height,
                             double targetweight, int targetperiod, int workperiod, int worklevel) {
            this.urlStr = inStr;
            this.id = id;
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.weight = weight;
            this.height = height;
            this.targetweight = targetweight;
            this.targetperiod = targetperiod;
            this.workperiod = workperiod;
            this.worklevel = worklevel;

        }

        public void run() {
            try {
                final String output = request(urlStr, id, sex, age, weight, height, targetweight, targetperiod, workperiod, worklevel);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject obj = new JSONObject(output);
                            //signupCheck = obj.getBoolean("success");
                            String bodytype = obj.getString("bodytype");
                           // String bodytype = getIntent().getStringExtra("bodytype");
                             //if(signupCheck) {
                                Toast.makeText(getApplicationContext(), "정보수정 완료!.", Toast.LENGTH_LONG).show();
                                Intent it = new Intent(Re_Survey.this, Main2Activity.class);
                                it.putExtra("alert", 1);
                                it.putExtra("id", id);
                                it.putExtra("name", name);
                                it.putExtra("sex", sex);
                                it.putExtra("age", age);
                                it.putExtra("targetperiod", targetperiod);
                                it.putExtra("targetweight", targetweight);
                                it.putExtra("worklevel", worklevel);
                                it.putExtra("workperiod", workperiod);
                                it.putExtra("bodytype", bodytype);
                                it.putExtra("weight",weight);
                                it.putExtra("height",height);


                                // obj를 string으로 만든 후 putExtra를 이용하여 데이터를 다음 Activity에 전송
                                startActivity(it);

                                finish();
                            /*} else {
                                Toast.makeText(getApplicationContext(), "빈 칸을 채워주세요.", Toast.LENGTH_LONG).show();
                            }*/
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        private String request(String urlStr, String id, String sex, int age, double weight, double height,
                               double targetweight, int targetperiod, int workperiod, int worklevel) throws IOException {
            StringBuilder output = new StringBuilder();
            try {
                URL url = new URL(urlStr);

                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                if(conn != null) {
                    String json = "";

                    // build jsonObject
                    JSONObject jsonObject = new JSONObject();

                    jsonObject.put("id", id);
                    jsonObject.put("sex", sex);
                    jsonObject.put("age", age);
                    jsonObject.put("weight", weight);
                    jsonObject.put("height", height);
                    jsonObject.put("targetweight", targetweight);
                    jsonObject.put("targetperiod", targetperiod);
                    jsonObject.put("workperiod", workperiod);
                    jsonObject.put("worklevel", worklevel);

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