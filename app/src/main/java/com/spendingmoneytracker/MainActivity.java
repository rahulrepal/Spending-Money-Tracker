package com.spendingmoneytracker;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
   
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
  
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //7588696868


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


       /* myDb=new selectiontospinner(this);


        //==================================================================================
        final Calendar calendar=Calendar.getInstance(TimeZone.getTimeZone("India"));
        year_cur=calendar.get(Calendar.YEAR);
        month_cur=calendar.get(Calendar.MONTH);
        day_cur=calendar.get(Calendar.DAY_OF_MONTH)+1;
        date_cur=day_cur+"/"+month_cur+"/"+year_cur;
        //==========================================

       Cursor cursor_rem=myDb.getReminders();
        TITL=new ArrayList<>();
        CATEG=new ArrayList<>();
        DATE=new ArrayList<>();

        while (cursor_rem.moveToNext()){
            TITL.add(cursor_rem.getString(0));
            CATEG.add(cursor_rem.getString(1));
            DATE.add(cursor_rem.getString(2));
        }


        int k=0;
        while ( k<DATE.size()){
            if(DATE.get(k)==date_cur){
                notintent=new Intent(MainActivity.this,MainActivity.class);
                pendingnotiIntent=PendingIntent.getActivity(MainActivity.this,0,notintent,PendingIntent.FLAG_UPDATE_CURRENT);
                notification= new NotificationCompat.Builder(MainActivity.this);
                notification.setAutoCancel(true);
                notification.setSmallIcon(R.drawable.ic_event_available_black_24dp);
                notification.setTicker("Ticker");
                notification.setWhen(System.currentTimeMillis());
                notification.setContentTitle("Title");
                notification.setContentText("Context");
                notification.setContentIntent(pendingnotiIntent);

                NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(uniqueID,notification.build());

            }
        }*/







    }


   /*  @Override
   public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
           Intent mintent=new Intent(this,settings.class);
            startActivity(mintent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    
  

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    Home tab1= new Home();
                    return tab1;
                case 1:
                    Statistics tab2= new Statistics();
                    return tab2;
                case 2:
                    Reminders tab3= new Reminders();
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Home";
                case 1:
                    return "Statistics";
                case 2:
                    return "Reminders";
            }
            return null;
        }
    }



}
