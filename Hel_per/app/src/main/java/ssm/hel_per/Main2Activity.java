package ssm.hel_per;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent it1 = getIntent();
        int alert = (int)it1.getSerializableExtra("alert");

        if(alert==1234) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("안녕하세요 저희 앱의 신규회원이 되신 것을 축하드립니다. 저희 앱의 서비스를 받으시려면 설문조사를 통해 고객의 신체유형을 파악하는 과정을 거쳐야합니다.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AlertDialog.Builder builder2 = new AlertDialog.Builder(Main2Activity.this);
                            builder2.setTitle("설문조사를 하시겠습니까?");
                            builder2.setPositiveButton("아니오", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            builder2.setNegativeButton("예", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent it = new Intent(Main2Activity.this,Survey.class);
                                    startActivity(it);
                                }
                            });
                            builder2.show();
                        }
                    }).setCancelable(false).show();
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager manager = getFragmentManager();
        if (id == R.id.nav_diary) {
            manager.beginTransaction().replace(R.id.content_main,new writeDiary()).commit();
        } else if (id == R.id.nav_my_state) {
            manager.beginTransaction().replace(R.id.content_main,new myState()).commit();
        } else if (id == R.id.nav_exercise) {
            manager.beginTransaction().replace(R.id.content_main,new exercise()).commit();
        } else if (id == R.id.nav_food_manage) {
            manager.beginTransaction().replace(R.id.content_main,new foodManage()).commit();
        } else if (id == R.id.nav_add_user) {
            manager.beginTransaction().replace(R.id.content_main,new addUser()).commit();
        } else if (id == R.id.nav_body_check) {
            manager.beginTransaction().replace(R.id.content_main,new bodyCheck()).commit();

        } else if (id == R.id.nav_qna) {
            manager.beginTransaction().replace(R.id.content_main,new QnA()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
