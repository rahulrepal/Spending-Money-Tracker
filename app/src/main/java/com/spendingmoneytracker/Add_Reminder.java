package com.spendingmoneytracker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

public class Add_Reminder extends AppCompatActivity {

    int day_r,year_r,month_r,hour_r,minute_r;
    static final int DIALOG_ID_TIME=0;
    static final int DIALOG_ID=1;
    ImageView timeget,dateget,submitrem;
    EditText setext;
    TextView arDate;
    String date_r,date_cur,date_x;
    String time_r,time_cur;
    ArrayList<String> date_a;
    ArrayList<String> time_a;
    int day_cur,year_cur,month_cur,hour_cur,minute_cur,month_x;
    Intent mYintent;
    PendingIntent pendingIntent;
    reminderNoti rn=new reminderNoti();
    Spinner spinnerrem;
    int day_builder;
    int month_builder;
    selectiontospinner myDb;
    ArrayList<String> categ;


    @RequiresApi(api = Build.VERSION_CODES.N)
    NotificationCompat.Builder notification;
    private static final int uniqueID=45612;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__reminder);
        myDb=new selectiontospinner(this);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_cash_income);
        View A_view=getSupportActionBar().getCustomView();
        ImageView actionbarbutton=(ImageView) A_view.findViewById(R.id.actionbarbutton);
        TextView actionbar_title=(TextView)A_view.findViewById(R.id.actionbar_title);
        actionbar_title.setText("Set Reminder");
        actionbarbutton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                     finish();

                    }
                }
        );

        submitrem=(ImageView)findViewById(R.id.submitreminder);
        spinnerrem=(Spinner)findViewById(R.id.remspinner);
        setext=(EditText)findViewById(R.id.settitle);
        arDate=(TextView)findViewById(R.id.setdate2);

        final Calendar calendar=Calendar.getInstance(TimeZone.getTimeZone("India"));
        year_r=calendar.get(Calendar.YEAR);
        month_r=calendar.get(Calendar.MONTH);
        day_r=calendar.get(Calendar.DAY_OF_MONTH);
        day_builder=calendar.get(Calendar.DAY_OF_MONTH);

        month_builder=calendar.get(Calendar.MONTH)+1;

        hour_cur=calendar.get(Calendar.HOUR);
        minute_cur=calendar.get(Calendar.MINUTE);
        date_cur=day_cur+"/"+month_cur+"/"+year_cur;
        time_cur=hour_cur+":"+minute_cur;
        Toast.makeText(Add_Reminder.this,date_cur,Toast.LENGTH_LONG).show();

        //===========================================
        categ=new ArrayList<>();
        categ.add("Hostel");
        categ.add("Entertainment");
        categ.add("Mess");
        categ.add("Recharges");
        categ.add("Wifi");
        categ.add("Transportation");
        categ.add("Party");
        categ.add("Shopping");
        categ.add("Utilities");
        categ.add("Other");
        //===========================================
        final ArrayAdapter<String> catego = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categ);
        spinnerrem.setAdapter(catego);

        //Button notigetter=(Button)findViewById(R.id.notigetter);
        /*notigetter.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClick1();
                    }
                }
        );*/

        shoDatePickerDialog();

        //showTimePickerDialog();

       arDate.setText(date_x);
         submitrem.setOnClickListener(
                 new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         String title=setext.getText().toString();
                         boolean ifinserted_rem = myDb.insertReminders(title, spinnerrem.getSelectedItem().toString(),date_r);
                         if (ifinserted_rem) {
                             Intent refresh_intent1=new Intent(Add_Reminder.this,MainActivity.class);
                             refresh_intent1.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                             startActivity(refresh_intent1);
                             finish();
                         } else {

                             Toast.makeText(Add_Reminder.this, "Data not inserted", Toast.LENGTH_LONG).show();
                         }
                     }
                 }
         );




    }

    public  void onClick1(){
        mYintent=new Intent(Add_Reminder.this,MainActivity.class);
        pendingIntent=PendingIntent.getActivity(Add_Reminder.this,0,mYintent,PendingIntent.FLAG_UPDATE_CURRENT);
        notification= new NotificationCompat.Builder(Add_Reminder.this);
        notification.setAutoCancel(true);
        notification.setSmallIcon(R.drawable.ic_event_available_black_24dp);
        notification.setTicker("Ticker");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Title");
        notification.setContentText("Context");
        notification.setContentIntent(pendingIntent);

        NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(uniqueID,notification.build());
    }
    public void shoDatePickerDialog(){
        dateget=(ImageView) findViewById(R.id.dategetter);
        dateget.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDialog(DIALOG_ID);
                    }
                }
        );

    }
    /*public void showTimePickerDialog(){
        timeget=(ImageView) findViewById(R.id.timegetter);
        timeget.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDialog(DIALOG_ID_TIME);
                    }
                }
        );

    }*/

    @Override
    protected Dialog onCreateDialog(int id) {
      /* if (id==DIALOG_ID_TIME){
           return new TimePickerDialog(Add_Reminder.this,timepickerlistner,hour_r,minute_r,false);
       }*/
        if (id == DIALOG_ID) {
            return new DatePickerDialog(this, datepickerlistner, year_r, month_r, day_r);
        }

           return null;
       }


    private DatePickerDialog.OnDateSetListener datepickerlistner =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                    year_r = year;
                    month_r = month;
                    month_x=month+1;
                    day_r = day;
                    date_r=day_r+"/"+month_r+"/"+year_r;
                    date_x=day_r+"/"+month_x+"/"+year_r;
                    arDate.setText(date_x);
                    if(day_r<day_builder||month_x<month_builder){
                        View view = null;
                        AlertDialog.Builder rLbuilder=new AlertDialog.Builder(Add_Reminder.this);
                        final View finalView = view;
                        rLbuilder.setMessage("Please select a valid date")
                                .setPositiveButton("OK",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {


                                                Intent refresh_intent1=new Intent(Add_Reminder.this,Add_Reminder.class);


                                                refresh_intent1.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                startActivity(refresh_intent1);
                                                Add_Reminder.this.finish();
                                            }
                                        })

                                .setIcon(R.drawable.ic_action_alert);
                        AlertDialog dialog=rLbuilder.create();
                        dialog.show();
                    }


                    Toast.makeText(Add_Reminder.this, year_r + "/" + month_x + "/" + day_r, Toast.LENGTH_LONG).show();

                }

            };

    /*protected TimePickerDialog.OnTimeSetListener timepickerlistner=
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        hour_r=hour;
                         minute_r=minute;
                       time_r=hour_r+":"+minute;
                    Toast.makeText(Add_Reminder.this,String.valueOf(hour_r)+":"+String.valueOf(minute_r),Toast.LENGTH_LONG).show();
                }
            };*/
}
