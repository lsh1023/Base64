package com.lsh.day38base64;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActionBarDrawerToggle toggle;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        ActionBar bar=getSupportActionBar();
        assert bar != null;
        bar.setDisplayHomeAsUpEnabled(true);
        //关闭抽屉的开关

        toggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.navigation_open,R.string.navigation_close);

        toggle.syncState();
        mDrawerLayout.addDrawerListener(toggle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return toggle.onOptionsItemSelected(item)||super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();

        switch (item.getItemId())
        {
            case R.id.action_base64://Base64编码
                transaction.add(R.id.rl_container, new Base64Fragment());
                break;
            case R.id.action_url_encode:
                transaction.add(R.id.rl_container, new UrlEncodeFragment());
                break;
            case R.id.action_md5:
                transaction.add(R.id.rl_container, new Md5Fragment());
                break;
            case R.id.action_aes:
                transaction.add(R.id.rl_container, new AESFragment());
                break;
            case R.id.action_rsa:
                transaction.add(R.id.rl_container, new RSAFragment());
                break;

        }


        transaction.commit();
        mDrawerLayout.closeDrawer(Gravity.LEFT);
        return false;
    }
}
