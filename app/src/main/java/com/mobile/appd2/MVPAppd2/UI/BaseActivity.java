package com.mobile.appd2.MVPAppd2.UI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;
import com.google.android.gms.common.api.GoogleApiClient;
import com.mobile.appd2.MVPAppd2.Clases.PlanVo;
import com.mobile.appd2.MVPAppd2.R;

/**
 * Created by david on 11/2/16.
 */
public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fm;
    private Profile profile;
    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    private String user_name;
    private String user_id;
    private TextView userName;

    public String getVoID() {
        return VoID;
    }

    public void setVoID(String voID) {
        VoID = voID;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public PlanVo planVo;

    public String getFeeling() {
        return feeling;
    }

    public void setFeeling(String feeling) {
        this.feeling = feeling;
    }

    public String feeling;

    private String VoID, date;
    private int budget, hour, minute;
    private ProfilePictureView userPicture;
    public static LoginManager loginManager;
    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedpreferences = getSharedPreferences(
                "com.mobile.appd2.MVPAppd2", Context.MODE_PRIVATE);

        String userName = "com.mobile.appd2.MVPAppd2.FACEBOOK_NAME";
        user_name =sharedpreferences.getString(userName, "");
        String userId = "com.mobile.appd2.MVPAppd2.FACEBOOK_ID";
        user_id =sharedpreferences.getString(userId, "");
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_plan) {
            // Handle the camera action
        } else if (id == R.id.nav_discover) {

        } else if (id == R.id.nav_activity) {

        } else if (id == R.id.nav_invite) {

        } else if (id == R.id.nav_notifications) {

        } else if (id == R.id.nav_favorites) {

        } else if (id == R.id.nav_logout) {
            loginManager.getInstance().logOut();
            gotoLogin();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

/*
        Set Navigation header by using Layout Inflater.
     */

    public void setNavigationHeader() {

        NavigationView navigation_view = (NavigationView) findViewById(R.id.nav_view);

        View header = LayoutInflater.from(this).inflate(R.layout.nav_header_login, null);
        navigation_view.addHeaderView(header);

        userName = (TextView) header.findViewById(R.id.user_name);
        userPicture = (ProfilePictureView) header.findViewById(R.id.profilePicture);
    }

    /*
       Set User Profile Information in Navigation Bar.
     */

    public void setUserName(String name) {
        userName.setText(name);
    }

    public void setUserId(String id) {
        userPicture.setProfileId(id);
    }


    public void setUserProfile() {

        try {
            setUserName(user_name);
            setUserId(user_id);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gotoLogin() {

        Intent loginIntent = new Intent().setClass(
                BaseActivity.this, FacebookLoginActivity.class);
        startActivity(loginIntent);
        finish();
    }

    public void getExtras(){

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            VoID= null;
            budget=0;
            date = null;
            hour = 0;
            minute =0;
        } else {
            VoID= extras.getString("VoKey");
            budget = extras.getInt("BUDGET");
            date = extras.getString("DATE");
            hour = extras.getInt("HOUR");
            minute =extras.getInt("MINUTE");
        }
    }

    public void putExtras(Intent intent){
        intent.putExtra("VoKey", getVoID());
        intent.putExtra("BUDGET", getBudget());
        intent.putExtra("DATE", getDate());
        intent.putExtra("HOUR", getHour());
        intent.putExtra("MINUTE", getMinute());
    }

    public PlanVo createPlan(String feeling){
        setFeeling(feeling);
        planVo = new PlanVo(getVoID(),getBudget(),getDate(),getHour(),getMinute(),getFeeling());
        return planVo;
    }

}
