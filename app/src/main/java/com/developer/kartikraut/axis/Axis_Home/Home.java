package com.developer.kartikraut.axis.Axis_Home;


import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.developer.kartikraut.axis.About.About_fragment;
import com.developer.kartikraut.axis.Constants;
import com.developer.kartikraut.axis.Contact.ContactUs;
import com.developer.kartikraut.axis.Events.Categories_Fragment;
import com.developer.kartikraut.axis.Exhibitions.ExhibFragment;
import com.developer.kartikraut.axis.Going_To.GoingTo;
import com.developer.kartikraut.axis.GuestLectures.GuestFragment;
import com.developer.kartikraut.axis.Inaugration.InaugFragment;
import com.developer.kartikraut.axis.Initiatives.Socialragment;
import com.developer.kartikraut.axis.LoginSignup.Login;
import com.developer.kartikraut.axis.Map.MapsActivity;
import com.developer.kartikraut.axis.MySingleton;
import com.developer.kartikraut.axis.R;
import com.developer.kartikraut.axis.Schedule.ScheduleFragment;
import com.developer.kartikraut.axis.Summit.SummitFragment;
import com.developer.kartikraut.axis.Talks.TalkFragment;
import com.developer.kartikraut.axis.Techinite.TechiniteFragment;
import com.developer.kartikraut.axis.Workshops.WSFragment;
import com.google.firebase.FirebaseApp;

import java.util.Calendar;


public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected DrawerLayout drawer;
    boolean doubleBackToExitPressedOnce;
    Boolean login;
    Menu menu;
    MenuItem login_item;
    Dialog profile_dialog;
    TextView NAME,EMAIL;
    ImageView iv_navHeader;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        Constants.instance(this.getApplicationContext());


        NAME = (TextView)navigationView.getHeaderView(0).findViewById(R.id.nav_header_name);
        EMAIL = (TextView)navigationView.getHeaderView(0).findViewById(R.id.nav_header_email);
        iv_navHeader = (ImageView)navigationView.getHeaderView(0).findViewById(R.id.ivNavHeader);

        Glide.with(getApplicationContext()).load(R.drawable.axislogo2)
                .into(iv_navHeader);

      /*  ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Black))); */

        menu = navigationView.getMenu();
        login_item = menu.findItem(R.id.nav_logout);

        profile_dialog = new Dialog(new ContextThemeWrapper(this,R.style.DialogSlideAnim));

        login = (Constants.instance().fetchValueBool("LOGIN"));

        if(login==false)
        {
            NAME.setText("AXIS");
            EMAIL.setText("guest@axisvnit.org");
            login_item.setTitle("Login/Register");
        }
        else
        {
            login_item.setTitle("Profile");
            NAME.setText(Constants.instance().fetchValueString("USER_NAME"));
            EMAIL.setText(Constants.instance().fetchValueString("EMAIL"));
        }

        showFragment0(Home_Fragment.class,"HOME");
        doubleBackToExitPressedOnce = false;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            Fragment f = getSupportFragmentManager().findFragmentById(R.id.flContent);
            if (getSupportFragmentManager().getBackStackEntryCount()==0)
            {
                if(doubleBackToExitPressedOnce){
                    super.onBackPressed();
                    return;
                }

                this.doubleBackToExitPressedOnce = true;
                Toast.makeText(this, "Please press BACK again to exit", Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce=false;
                    }
                }, 2000);
            }
            else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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

        Class fragment = null;

        if(id == R.id.nav_home){
            fragment = Home_Fragment.class;
            showFragment(fragment,"HOME");
        }
        else if (id == R.id.nav_about){
            fragment = About_fragment.class;
            showFragment(fragment,"ABOUT");
        }
        else if (id == R.id.nav_inaug){
            fragment = InaugFragment.class;
            showFragment(fragment,"INAUGURATION");
        }
        else if (id == R.id.nav_techi){
            fragment = TechiniteFragment.class;
            showFragment(fragment,"TECH-I-NITE");
        }
        else if (id == R.id.nav_events) {
            fragment = Categories_Fragment.class;
            showFragment(fragment,"EVENTS");
        }
        else if (id == R.id.nav_workshops) {
            fragment = WSFragment.class;
            showFragment(fragment,"WORKSHOPS");
        }
        else if (id == R.id.nav_exhib){
            fragment = ExhibFragment.class;
            showFragment(fragment,"EXHIBITIONS");
        }
        else if(id == R.id.nav_summit){
            fragment = SummitFragment.class;
            showFragment(fragment,"TNS");
        }
        else if (id == R.id.nav_social_initiatives) {
            fragment = Socialragment.class;
            showFragment(fragment,"SOCIAL INITIATIVES");
        }
        else if (id == R.id.nav_talks){
            fragment = TalkFragment.class;
            showFragment(fragment,"TALKS");
        }
        else if (id == R.id.nav_guest){
            fragment = GuestFragment.class;
            showFragment(fragment,"GUEST LECTURES");
        }
        else if (id == R.id.nav_schedule) {
            fragment = ScheduleFragment.class;
            showFragment(fragment,"SCHEDULE");
        }
        else if (id == R.id.nav_going){
            fragment = GoingTo.class;
            showFragment(fragment,"GOING TO");
        }
        else if (id == R.id.nav_map) {
             Intent mapIntent = new Intent(Home.this, com.developer.kartikraut.axis.MapsActivity.class);
             startActivity(mapIntent);
        }
        else if (id == R.id.nav_sponsors) {
            String url = "http://axisvnit.org/sponsors/";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }
        else if (id == R.id.nav_contact_us) {
            fragment = ContactUs.class;
            showFragment(fragment,"CONTACT US");
        }
        else if (id == R.id.nav_logout) {
            if(login==true)
            {
               login_item.setTitle("Profile");
               profile_popup();

            }
            else
            {
                Intent intent = new Intent(Home.this,Login.class);
                startActivity(intent);
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void profile_popup() {
        TextView txtclose1,profile_name,profile_email,profile_axidId;
        profile_dialog.setContentView(R.layout.profile_popup);
        txtclose1 = (TextView)profile_dialog.findViewById(R.id.profile_txtclose);
        profile_name = (TextView)profile_dialog.findViewById(R.id.profile_name);
        profile_email = (TextView)profile_dialog.findViewById(R.id.profile_email);
        profile_axidId = (TextView)profile_dialog.findViewById(R.id.profile_axisid);

        profile_name.setText(Constants.instance().fetchValueString("USER_NAME"));
        profile_email.setText(Constants.instance().fetchValueString("EMAIL"));
        profile_axidId.setText(Constants.instance().fetchValueString("AXISID"));

        txtclose1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_dialog.dismiss();
            }
        });

        profile_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        profile_dialog.show();
    }

    private void showFragment(Class fragmentClass, String fragment_title) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
     /*   int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        String backpos = Integer.toString(backStackEntryCount);
        Toast.makeText(this,backpos,Toast.LENGTH_SHORT).show();  */
        FragmentManager fragmentManager = getSupportFragmentManager();
    /*    if(backStackEntryCount==0)
        {
            fragmentManager.beginTransaction()
                    .replace(R.id.flContent,fragment)
                    .setCustomAnimations(R.anim.slide_in,R.anim.slide_out)
                    .commit();
        }
        else
        {
            fragmentManager.beginTransaction()
                    .replace(R.id.flContent, fragment)
                    .addToBackStack(null)
                    .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                    .commit();
        }  */

        fragmentManager.beginTransaction()
                .replace(R.id.flContent, fragment)
                .addToBackStack(null)
                .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                .commit();
        setTitle(fragment_title);
    }

    private void showFragment0(Class fragmentClass, String fragment_title) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.flContent, fragment)
                .commit();
        setTitle(fragment_title);
    }

    public void switchContent(int id, Fragment fragment, String fragment_title) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(id, fragment, fragment.toString());
        ft.addToBackStack(null);
        ft.commit();
        setTitle(fragment_title);
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
