package com.spendingmoneytracker;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by RahulSR on 8/2/2017.
 */

public class Home extends Fragment {

    com.github.clans.fab.FloatingActionButton addmoneybtn,addbtn;;
    TextView bb;
    ImageView hostelimage,
    entertainmentimage,
            messimage,
    rechargesimage,
            wifiimage,
    transportationimage,
            partyimage,
    shoppingimage,
           utilitiesimage,
    otherimage;


    Intent notintent;
    NotificationCompat.Builder notification;
    private static final int uniqueID=45612;
    PendingIntent pendingnotiIntent;
    selectiontospinner myDb;
    int year_xcur,month_xcur,day_xcur;
    String date_xcur=null;
    ArrayList<String> TITL,CATEG,DATE;
    Statistics mystats;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home, container, false);
      final String[] content={"Hostel","Entertainment","Mess","Recharges","Wifi","Transportation","Party","Shopping","Utilities","Other"};
        myDb = new selectiontospinner(getActivity());

        //--------------------------------------------Declaration of image Views---------------------------------------------------------
        bb=(TextView)rootView.findViewById(R.id.belowBalance);
        addbtn=(com.github.clans.fab.FloatingActionButton) rootView.findViewById(R.id.minc);
        addmoneybtn=(com.github.clans.fab.FloatingActionButton) rootView.findViewById(R.id.mexpense);
        hostelimage=(ImageView)rootView.findViewById(R.id.hostelimage);
        entertainmentimage=(ImageView)rootView.findViewById(R.id.entertainmentimage);
        messimage=(ImageView)rootView.findViewById(R.id.messimage);
        rechargesimage=(ImageView)rootView.findViewById(R.id.rechargesimage);
        wifiimage=(ImageView)rootView.findViewById(R.id.wifiimage);
        transportationimage=(ImageView)rootView.findViewById(R.id.transportationimage);
        partyimage=(ImageView)rootView.findViewById(R.id.partyimage);
        shoppingimage=(ImageView)rootView.findViewById(R.id.shoppingimage);
        utilitiesimage=(ImageView)rootView.findViewById(R.id.utilitiesimage);
        otherimage=(ImageView)rootView.findViewById(R.id.otherimage);
       //--------------------------------------------Declaration of image Views---------------------------------------------------------
        Cursor hbal=myDb.getremaining();
        Integer hresult_12=0;

        if (hbal.moveToNext())
            hresult_12 = (int) hbal.getDouble(hbal.getColumnIndex("myExpense"));


        Cursor hrem = myDb.getSum();
        Integer hresult = 0;
        if (hrem.moveToNext())
            hresult = (int) hrem.getDouble(hrem.getColumnIndex("myTotal"));

        Integer hfinal_balance = hresult_12 - hresult;

        String str_result= String.valueOf(hfinal_balance);
        bb.setText(str_result);





        final Intent addintent=new Intent("com.spendingmoneytracker.Add_MOney");

        hostelimage.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addintent.putExtra(Add_MOney.SELECTED_STRING,(String) content[0]);
                        startActivity(addintent);
                    }
                }
        );
        entertainmentimage.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        addintent.putExtra(Add_MOney.SELECTED_STRING,(String) content[1]);
                        startActivity(addintent);
                    }
                }
        );

        messimage.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addintent.putExtra(Add_MOney.SELECTED_STRING,(String) content[2]);
                        startActivity(addintent);
                    }
                }
        );

        rechargesimage.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        addintent.putExtra(Add_MOney.SELECTED_STRING,(String) content[3]);
                        startActivity(addintent);
                    }
                }
        );
        wifiimage.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        addintent.putExtra(Add_MOney.SELECTED_STRING,(String) content[4]);
                        startActivity(addintent);
                    }
                }
        );
        transportationimage.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        addintent.putExtra(Add_MOney.SELECTED_STRING,(String) content[5]);
                        startActivity(addintent);
                    }
                }
        );
        partyimage.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        addintent.putExtra(Add_MOney.SELECTED_STRING,(String) content[6]);
                        startActivity(addintent);
                    }
                }
        );
        shoppingimage.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        addintent.putExtra(Add_MOney.SELECTED_STRING,(String) content[7]);
                        startActivity(addintent);
                    }
                }
        );
       utilitiesimage.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        addintent.putExtra(Add_MOney.SELECTED_STRING,(String) content[8]);
                        startActivity(addintent);
                    }
                }
        );
        otherimage.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        addintent.putExtra(Add_MOney.SELECTED_STRING,(String) content[9]);
                        startActivity(addintent);
                    }
                }
        );




        /*addmoneybtn=(ImageView) rootView.findViewById(R.id.addmoneybtn);*/

           addmoneybtn.setOnClickListener(
                   new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {

                           addintent.putExtra(Add_MOney.SELECTED_STRING,(String) content[9]);
                           startActivity(addintent);
                       }
                   }
           );

        addbtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                      Intent intentadd=new Intent(getActivity(),Main2Activity.class);
                        startActivity(intentadd);
                    }
                }
        );
        myDb=new selectiontospinner(getActivity());


        //==================================================================================
        final android.icu.util.Calendar calendar= android.icu.util.Calendar.getInstance();
        year_xcur=calendar.get(android.icu.util.Calendar.YEAR);
        month_xcur=calendar.get(android.icu.util.Calendar.MONTH);
        day_xcur=calendar.get(android.icu.util.Calendar.DAY_OF_MONTH);
        date_xcur=day_xcur+"/"+month_xcur+"/"+year_xcur;
        final String temp2=String.valueOf(4);
        int i=4,j=9,l=2017;
        final String temp=String.valueOf(4);
        String dayy=String.valueOf(day_xcur);
        String monthh=String.valueOf(month_xcur);
        String year=String.valueOf(year_xcur);

        String da=dayy+monthh+year;
        String ta="492017";
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
for (int k=0;k<DATE.size();k++) {
    if (DATE.get(k).equals(date_xcur)) {
        onRem(k);
    }
}





        return rootView;
    }
    public  void onRem(int pos){
        notintent=new Intent(getActivity(),MainActivity.class);
        pendingnotiIntent=PendingIntent.getActivity(getActivity(),0,notintent,PendingIntent.FLAG_UPDATE_CURRENT);
        notification= new NotificationCompat.Builder(getActivity());
        notification.setAutoCancel(true);
        notification.setSmallIcon(R.drawable.ic_stat_noti);
        notification.setTicker("Ticker");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle(CATEG.get(pos));
        notification.setContentText(TITL.get(pos));
        notification.setContentIntent(pendingnotiIntent);

        NotificationManager notificationManager=(NotificationManager)getActivity().getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(uniqueID,notification.build());
    }


}
