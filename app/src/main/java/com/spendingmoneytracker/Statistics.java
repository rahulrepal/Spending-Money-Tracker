package com.spendingmoneytracker;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by RahulSR on 8/2/2017.
 */


public class Statistics extends Fragment {
       PieChart myChart1;
    selectiontospinner myDb;
    //Spinner stats;
    //ListView listmy;
    //Button drop,refresh,sum;
    TextView bal,listdate,tcas,tspent,texpand;
     BarChart barChart;
    ListView last_list;
    ProgressBar progressBar;
    Intent balintent;
    NotificationCompat.Builder notification_bal;
    private static final int uniqueIDBAL=4561;
    PendingIntent pendingbalIntent;
    ArrayList<String> myBal=new ArrayList<>();
     int gmonth=0;
    String str_result=null;
    int final_progress= 0;
   Spinner showspineer;
    ArrayList<String> Names=new ArrayList<>();
    ArrayList<Integer> Imageid=new ArrayList<>();
    ArrayList<String> desc=new ArrayList<>();
    ArrayList<String> mdate=new ArrayList<>();
    ArrayList<String> Names_pieChart;

    ArrayList<Integer> Amount_pieChart;









    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     Calendar gcal=Calendar.getInstance();
        gmonth=gcal.get(Calendar.MONTH)+1;
        myDb = new selectiontospinner(getActivity());
        View rootView = inflater.inflate(R.layout.statistics, container, false);
        // stats=(Spinner)rootView.findViewById(R.id.stats);
        //bal = (TextView) rootView.findViewById(R.id.text);
        listdate = (TextView) rootView.findViewById(R.id.ldate);
        tcas = (TextView) rootView.findViewById(R.id.tcash);
        tspent = (TextView) rootView.findViewById(R.id.tspend);
        texpand = (TextView) rootView.findViewById(R.id.twithdrawn);
        //  listmy=(ListView)rootView.findViewById(R.id.listmy);
        last_list=(ListView)rootView.findViewById(R.id.last_list);
        TextView max_name=(TextView)rootView.findViewById(R.id.max_name);
        TextView max_amount=(TextView)rootView.findViewById(R.id.max_amount);
        TextView max_date=(TextView)rootView.findViewById(R.id.max_date);
        progressBar=(ProgressBar)rootView.findViewById(R.id.progressbar);
        showspineer=(Spinner)rootView.findViewById(R.id.espinner);
        myChart1=(PieChart)rootView.findViewById(R.id.myChart1);
        TextView progresspercentage=(TextView)rootView.findViewById(R.id.progresspercentage);
      Integer[] imageid= {R.drawable.hostelsmall, R.drawable.entertainmentsmall, R.drawable.messsmall,R.drawable.rechargesmall, R.drawable.wifismall, R.drawable.transportsmall, R.drawable.partysmall, R.drawable.shoppingsmall, R.drawable.utilitiessmall, R.drawable.othersmall,R.drawable.utilitiessmall};
        Names_pieChart = new ArrayList<>();
        Amount_pieChart = new ArrayList<>();
        ArrayList<String> Es=new ArrayList<>();
        Es.add("by this month");
        Es.add("by last month");
        Es.add("by Year");
        final ArrayAdapter<String> Esdap = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Es);
        showspineer.setAdapter(Esdap);
    showspineer.setOnItemSelectedListener(
            new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(i==0){
                        Names_pieChart.clear();
                        Amount_pieChart.clear();
                        Names.clear();
                        mdate.clear();
                        desc.clear();
                    Cursor cur_piechart=myDb.getByName(gmonth);
                    while (cur_piechart.moveToNext()){

                        Names_pieChart.add(cur_piechart.getString(0));
                        Amount_pieChart.add(cur_piechart.getInt(1));
                    }
                        List<PieEntry> pieEntries = new ArrayList<>();
                        for (int il=0;il<Names_pieChart.size();il++){
                            pieEntries.add(new PieEntry(Amount_pieChart.get(il),Names_pieChart.get(il)));}
                        PieDataSet dataset=new PieDataSet(pieEntries,"EXPENSES");
                        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
                        PieData data=new PieData(dataset);
                        myChart1.setData(data);
                        myChart1.animateY(1000);

                        myChart1.invalidate();

                        Toast.makeText(getActivity(),String.valueOf(gmonth), Toast.LENGTH_SHORT).show();

                        Cursor data1=myDb.getData(gmonth);


                        while (data1.moveToNext()){
                            Names.add(data1.getString(1));
                            mdate.add(data1.getString(3));
                            desc.add(data1.getString(4));
                        }
                        int limiter=mdate.size()-1;
                        if(limiter>-1){
                            listdate.setText(mdate.get(mdate.size()-1));
                        }

                        ListAdapter listadapter=new ArrayAdapter<>(getActivity(),R.layout.support_simple_spinner_dropdown_item,desc);
                        CustomLayout customLayout=new CustomLayout();
                        last_list.setAdapter(customLayout);
                        last_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View vicdew, int i, long l) {

                                TextView v = (TextView) vicdew.findViewById(R.id.custom_text);
                                Toast.makeText(getActivity(),v.getText(),Toast.LENGTH_SHORT).show();
                                Intent intent1=new Intent(getActivity(),StatsDetails.class);
                                intent1.putExtra(StatsDetails.EXPENSE_NO,(String) v.getText().toString());
                                startActivity(intent1);
                            }
                        });
               }
               else if(i==1){

                            Names_pieChart.clear();
                            Amount_pieChart.clear();
                            Names.clear();
                            mdate.clear();
                            desc.clear();
                            Cursor cur_piechart = myDb.getByName(gmonth-1);
                            while (cur_piechart.moveToNext()) {

                                Names_pieChart.add(cur_piechart.getString(0));
                                Amount_pieChart.add(cur_piechart.getInt(1));
                            }
                            List<PieEntry> pieEntries = new ArrayList<>();
                            for (int il = 0; il < Names_pieChart.size(); il++) {
                                pieEntries.add(new PieEntry(Amount_pieChart.get(il), Names_pieChart.get(il)));
                            }
                            PieDataSet dataset = new PieDataSet(pieEntries, "EXPENSES");
                            dataset.setColors(ColorTemplate.COLORFUL_COLORS);
                            PieData data = new PieData(dataset);
                            myChart1.setData(data);
                            myChart1.animateY(1000);

                            myChart1.invalidate();

                            Toast.makeText(getActivity(), String.valueOf(gmonth), Toast.LENGTH_SHORT).show();

                            Cursor data1 = myDb.getData(gmonth-1);


                            while (data1.moveToNext()) {
                                Names.add(data1.getString(1));
                                mdate.add(data1.getString(3));
                                desc.add(data1.getString(4));
                            }
                            int limiter = mdate.size() - 1;
                            if (limiter > -1) {
                                listdate.setText(mdate.get(mdate.size() - 1));
                            }

                            ListAdapter listadapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, desc);
                            CustomLayout customLayout = new CustomLayout();
                            last_list.setAdapter(customLayout);
                            last_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View vicdew, int i, long l) {

                                    TextView v = (TextView) vicdew.findViewById(R.id.custom_text);
                                    Toast.makeText(getActivity(), v.getText(), Toast.LENGTH_SHORT).show();
                                    Intent intent1 = new Intent(getActivity(), StatsDetails.class);
                                    intent1.putExtra(StatsDetails.EXPENSE_NO, (String) v.getText().toString());
                                    startActivity(intent1);
                                }
                            });

                    }
               else {
                        Names_pieChart.clear();
                        Amount_pieChart.clear();
                        Names.clear();
                        mdate.clear();
                        desc.clear();
                        Cursor cur_piechart=myDb.getByName();
                        while (cur_piechart.moveToNext()){
                            Names_pieChart.add(cur_piechart.getString(0));
                            Amount_pieChart.add(cur_piechart.getInt(1));
                        }




                        List<PieEntry> pieEntries = new ArrayList<>();
                        for (int ij=0;ij<Names_pieChart.size();ij++){
                            pieEntries.add(new PieEntry(Amount_pieChart.get(ij),Names_pieChart.get(ij)));}
                        PieDataSet dataset=new PieDataSet(pieEntries,"EXPENSES");
                        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
                        PieData data=new PieData(dataset);
                        myChart1.setData(data);
                        myChart1.animateY(1000);

                        myChart1.invalidate();

                        Cursor data1=myDb.getData();


                        while (data1.moveToNext()){
                            Names.add(data1.getString(1));
                            mdate.add(data1.getString(3));
                            desc.add(data1.getString(4));
                        }
                        int limiter=mdate.size()-1;
                        if(limiter>-1){
                            listdate.setText(mdate.get(mdate.size()-1));
                        }

                        ListAdapter listadapter=new ArrayAdapter<>(getActivity(),R.layout.support_simple_spinner_dropdown_item,desc);
                        CustomLayout customLayout=new CustomLayout();
                        last_list.setAdapter(customLayout);
                        last_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View vicdew, int i, long l) {

                                TextView v = (TextView) vicdew.findViewById(R.id.custom_text);
                                Toast.makeText(getActivity(),v.getText(),Toast.LENGTH_SHORT).show();
                                Intent intent1=new Intent(getActivity(),StatsDetails.class);
                                intent1.putExtra(StatsDetails.EXPENSE_NO,(String) v.getText().toString());
                                startActivity(intent1);
                            }
                        });
                    }
                }


                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            }
    );


       /* Cursor cur_piechart=myDb.getByName();
        while (cur_piechart.moveToNext()){
            Names_pieChart.add(cur_piechart.getString(0));
            Amount_pieChart.add(cur_piechart.getInt(1));
        }


        String[] Months={"Jan","Feb","Mar","May","Jun"};
        int[] rainfall={20,10,30,20,20};

        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i=0;i<Names_pieChart.size();i++){
            pieEntries.add(new PieEntry(Amount_pieChart.get(i),Names_pieChart.get(i)));}
        PieDataSet dataset=new PieDataSet(pieEntries,"EXPENSES");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data=new PieData(dataset);
        myChart1.setData(data);
        myChart1.animateY(1000);

        myChart1.invalidate();*/


        for(int i=0;i<imageid.length;i++){
          Imageid.add(imageid[i]);
      }



        Cursor max_data=myDb.maxSpend();
        String max_Name=null;
        String max_Date=null;
        Integer max_Amount=0;

        while (max_data.moveToNext()){
            max_Name=max_data.getString(0);
            max_Amount=max_data.getInt(1);
            max_Date=max_data.getString(2);
        }
        max_name.setText(max_Name);
        max_amount.setText(String.valueOf(max_Amount));
        max_date.setText(max_Date);

/*

        Cursor data1=myDb.getData();


        while (data1.moveToNext()){
            Names.add(data1.getString(1));
            mdate.add(data1.getString(3));
            desc.add(data1.getString(4));
        }
        int limiter=mdate.size()-1;
       if(limiter>-1){
            listdate.setText(mdate.get(mdate.size()-1));
        }

        ListAdapter listadapter=new ArrayAdapter<>(getActivity(),R.layout.support_simple_spinner_dropdown_item,desc);
        CustomLayout customLayout=new CustomLayout();
        last_list.setAdapter(customLayout);
        last_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View vicdew, int i, long l) {

                TextView v = (TextView) vicdew.findViewById(R.id.custom_text);
                Toast.makeText(getActivity(),v.getText(),Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(getActivity(),StatsDetails.class);
                intent1.putExtra(StatsDetails.EXPENSE_NO,(String) v.getText().toString());
                startActivity(intent1);
            }
        });

        */


        Cursor Expense = myDb.getremaining();
        Integer result_12 = 0;
        if (Expense.moveToNext())
            result_12 = (int) Expense.getDouble(Expense.getColumnIndex("myExpense"));


        Cursor Distance = myDb.getSum();
        Integer result = 0;
        if (Distance.moveToNext())
            result = (int) Distance.getDouble(Distance.getColumnIndex("myTotal"));

        Integer final_balance = result_12 - result;
        texpand.setText(String.valueOf(result_12));
        tspent.setText(String.valueOf(result));
        str_result= final_balance.toString();
        myBal.add(str_result);
        //bal.setText(str_result);
        tcas.setText(str_result);
//        bal.setTextColor(Color.GREEN);
        Integer progress=final_balance*100;

        if(result_12!=0){
        final_progress =progress/result_12;}
        if(final_progress<=20){
            onRem1();
            getActivity().setTheme(R.style.AppThemeError);
        }

        //Toast.makeText(getActivity(),String.valueOf(final_progress),Toast.LENGTH_SHORT).show();
        progresspercentage.setText(String.valueOf(final_progress)+"%");
        progressBar.setProgress(final_progress);

        Cursor cur_getBydate=myDb.getByDate();
        ArrayList<String> cur_Dates=new ArrayList<>();
        ArrayList<Integer> cur_AmountbyDate=new ArrayList<>();
        while (cur_getBydate.moveToNext()){
            cur_Dates.add(cur_getBydate.getString(0));
            cur_AmountbyDate.add(cur_getBydate.getInt(1));
        }




        return rootView;
    }

    public class CustomLayout extends BaseAdapter {
        Statistics sts;
        @Override
        public int getCount() {
            return Names.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {


            view=getActivity().getLayoutInflater().inflate(R.layout.customlayout,null);
            ImageView custom_image=(ImageView)view.findViewById(R.id.custom_image);
            TextView custom_text=(TextView)view.findViewById(R.id.custom_text);
            if(Names.get(i).equals("Hostel")){
                custom_image.setImageResource(Imageid.get(0));
                custom_text.setText(desc.get(i));
            }
            else if (Names.get(i).equals("Entertainment")){
                custom_image.setImageResource(Imageid.get(1));
                custom_text.setText(desc.get(i));
            }
             else if (Names.get(i).equals("Mess")){
                custom_image.setImageResource(Imageid.get(2));
                custom_text.setText(desc.get(i));

            }
             else if (Names.get(i).equals("Recharges")){
                custom_image.setImageResource(Imageid.get(3));
                custom_text.setText(desc.get(i));
            }
             else if (Names.get(i).equals("Wifi")){
                custom_image.setImageResource(Imageid.get(4));
                custom_text.setText(desc.get(i));
            }
             else if (Names.get(i).equals("Transportation")){
                custom_image.setImageResource(Imageid.get(5));
                custom_text.setText(desc.get(i));
            }
             else if (Names.get(i).equals("Party")){
                custom_image.setImageResource(Imageid.get(6));
                custom_text.setText(desc.get(i));
            }
             else if (Names.get(i).equals("Shopping")){
                custom_image.setImageResource(Imageid.get(7));
                custom_text.setText(desc.get(i));
            }
             else if (Names.get(i).equals("Utilities")){
                custom_image.setImageResource(Imageid.get(8));
                custom_text.setText(desc.get(i));
            }
            else {
                custom_image.setImageResource(Imageid.get(9));
                custom_text.setText(desc.get(i));
            }
               return view;
        }


    }
    public  void onRem1(){
        balintent=new Intent(getActivity(),MainActivity.class);
        pendingbalIntent= PendingIntent.getActivity(getActivity(),0,balintent,PendingIntent.FLAG_UPDATE_CURRENT);
        notification_bal= new NotificationCompat.Builder(getActivity());
        notification_bal.setAutoCancel(true);
        notification_bal.setSmallIcon(R.drawable.ic_stat_low);
        notification_bal.setTicker("Ticker");
        notification_bal.setWhen(System.currentTimeMillis());
        notification_bal.setContentTitle("Low Balance");
        notification_bal.setContentText("Your balance is less than 20%");
        notification_bal.setContentIntent(pendingbalIntent);

        NotificationManager notificationManager=(NotificationManager)getActivity().getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(uniqueIDBAL,notification_bal.build());
    }

}























//========================================================================================================
    //  drop=(Button)rootView.findViewById(R.id.deldatabase);
    //   refresh=(Button)rootView.findViewById(R.id.refresh);
    //   sum=(Button)rootView.findViewById(R.id.sum);




//  final ArrayAdapter<String> statsadapter=new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,statsmenu);


//stats.setAdapter(statsadapter);
       /* refresh.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Cursor Distance_12=myDb.getremaining();

                        String result_12="";
                        if (Distance_12.moveToNext())
                            result_12 = String.valueOf(Distance_12.getDouble(Distance_12.getColumnIndex("myExpense")));
                        int int_result_12=Integer.parseInt(result_12);

                        Cursor Distance1=myDb.getSum();

                        String result1="";
                        if (Distance1.moveToNext())
                            result1 = String.valueOf(Distance1.getDouble(Distance1.getColumnIndex("myTotal")));
                        int int_result1=Integer.parseInt(result1);

                        int balance=int_result1-int_result_12;
                        String str_balance=String.valueOf(balance);
                       // Cursor Distance=myDb.getSum();

                       // String result="";
                       // if (Distance.moveToNext())
                        //    result = String.valueOf(Distance.getDouble(Distance.getColumnIndex("myTotal")));
                     //   bal.setText(result);
                      Intent refresh_intent=new Intent(getActivity(),MainActivity.class);
                        startActivity(refresh_intent);
                    }
                }
        );
        drop.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myDb.dropdatabase();
                    }
                }
        );
        populatelistview();
        final String[] expense_name={"Hostel","Entertainment","Mess","Recharges","Wifi","Transportation","Party","Shopping","Utilities","Other"};
        sum.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Cursor Distance=myDb.getSum();

                        String result="";
                        if (Distance.moveToNext())
                            result = String.valueOf(Distance.getDouble(Distance.getColumnIndex("myTotal")));
                        int int_result=Integer.parseInt(result);

                        Toast.makeText(getActivity(),result,Toast.LENGTH_SHORT).show();
                        /*Cursor cursor = myDb.getSum(expense_name[0]);

                        String string_amount = "";
                        int int_amount=0;

                        if (cursor.moveToNext())
                            int_amount = (int) cursor.getDouble(cursor.getColumnIndex("myNameTotal"));

                        string_amount=String.valueOf(int_amount);

                        Toast.makeText(getActivity(),string_amount,Toast.LENGTH_SHORT).show();


                    }
                }
        );*/

      // =================================================================================================

    /*private void populatelistview() {
        Cursor data=myDb.getData();
        ArrayList<String> Names= new ArrayList<>();
        while (data.moveToNext()){
            Names.add(data.getString(1));
        }

        ListAdapter listadapter=new ArrayAdapter<>(getActivity(),R.layout.support_simple_spinner_dropdown_item,Names);
        listmy.setAdapter(listadapter);
    }
}*/

/*
* <ImageView
        android:id="@+id/hostelimage"
        android:layout_width="70dp"
        android:layout_height="70dp"
        android:scaleType="centerCrop"
        app:srcCompat="@drawable/hostel"
        android:layout_marginLeft="12dp"
        app:layout_constraintLeft_toLeftOf="parent"
        android:layout_marginStart="12dp"
        app:layout_constraintTop_toTopOf="parent"
        android:layout_marginTop="32dp" />

    <ImageView
        android:id="@+id/transportationimage"
        android:layout_width="72dp"
        android:layout_height="72dp"
        app:srcCompat="@drawable/transit"
        app:layout_constraintLeft_toRightOf="@+id/wifiimage"
        android:layout_marginLeft="24dp"
        android:layout_marginTop="32dp"
        app:layout_constraintTop_toBottomOf="@+id/entertainmentimage"
        android:layout_marginStart="24dp" />

    <ImageView
        android:id="@+id/wifiimage"
        android:layout_width="72dp"
        android:layout_height="72dp"
        app:srcCompat="@drawable/wifi"
        android:layout_marginLeft="12dp"
        app:layout_constraintLeft_toLeftOf="parent"
        android:layout_marginTop="34dp"
        app:layout_constraintTop_toBottomOf="@+id/hostelimage"
        android:layout_marginStart="12dp" />

    <ImageView
        android:id="@+id/messimage"
        android:layout_width="72dp"
        android:layout_height="72dp"
        app:srcCompat="@drawable/mess"
        app:layout_constraintLeft_toRightOf="@+id/entertainmentimage"
        android:layout_marginLeft="24dp"
        app:layout_constraintTop_toTopOf="parent"
        android:layout_marginTop="32dp"
        android:layout_marginStart="24dp" />

    <ImageView
        android:id="@+id/rechargesimage"
        android:layout_width="0dp"
        android:layout_height="72dp"
        app:srcCompat="@drawable/recharges"
        app:layout_constraintLeft_toRightOf="@+id/messimage"
        android:layout_marginLeft="25dp"
        app:layout_constraintTop_toTopOf="parent"
        android:layout_marginTop="32dp"
        android:layout_marginRight="13dp"
        app:layout_constraintRight_toRightOf="parent"
        android:layout_marginStart="25dp"
        android:layout_marginEnd="13dp"
        tools:layout_constraintRight_creator="1"
        tools:layout_constraintLeft_creator="1" />

    <ImageView
        android:id="@+id/partyimage"
        android:layout_width="72dp"
        android:layout_height="72dp"
        app:srcCompat="@drawable/drinks"
        app:layout_constraintLeft_toRightOf="@+id/transportationimage"
        android:layout_marginLeft="24dp"
        android:layout_marginTop="32dp"
        app:layout_constraintTop_toBottomOf="@+id/messimage"
        android:layout_marginStart="24dp" />

    <ImageView
        android:id="@+id/shoppingimage"
        android:layout_width="0dp"
        android:layout_height="72dp"
        android:layout_marginLeft="24dp"
        android:layout_marginRight="12dp"
        android:layout_marginTop="32dp"
        app:layout_constraintLeft_toRightOf="@+id/partyimage"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/rechargesimage"
        app:srcCompat="@drawable/shopping"
        android:layout_marginStart="24dp"
        android:layout_marginEnd="12dp"
        tools:layout_constraintRight_creator="1"
        tools:layout_constraintLeft_creator="1" />

    <ImageView
        android:id="@+id/utilitiesimage"
        android:layout_width="72dp"
        android:layout_height="72dp"
        app:srcCompat="@drawable/utilities"
        android:layout_marginLeft="12dp"
        app:layout_constraintLeft_toLeftOf="parent"
        android:layout_marginTop="32dp"
        app:layout_constraintTop_toBottomOf="@+id/wifiimage"
        android:layout_marginStart="12dp" />

    <ImageView
        android:id="@+id/otherimage"
        android:layout_width="72dp"
        android:layout_height="72dp"
        app:srcCompat="@drawable/others"
        android:layout_marginTop="30dp"
        app:layout_constraintTop_toBottomOf="@+id/partyimage"
        app:layout_constraintLeft_toRightOf="@+id/utilitiesimage"
        android:layout_marginLeft="24dp"
        android:layout_marginRight="8dp"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        android:layout_marginStart="24dp"
        android:layout_marginEnd="8dp" />

    <ImageView
        android:id="@+id/entertainmentimage"
        android:layout_width="72dp"
        android:layout_height="72dp"
        app:srcCompat="@drawable/enter"
        app:layout_constraintLeft_toRightOf="@+id/hostelimage"
        android:layout_marginLeft="24dp"
        app:layout_constraintTop_toTopOf="parent"
        android:layout_marginTop="32dp"
        android:layout_marginStart="24dp" />

    <Button
        android:id="@+id/addmoneybtn"
        android:layout_width="88dp"
        android:layout_height="70dp"
        android:layout_marginBottom="89dp"
        android:layout_marginEnd="32dp"
        android:layout_marginRight="32dp"
        android:background="@drawable/add"
        android:shadowColor="@android:color/background_dark"
        android:shadowDx="5"
        android:shadowDy="5"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintRight_toRightOf="parent" />

    <Button
        android:id="@+id/addbtn"
        android:layout_width="88dp"
        android:layout_height="72dp"
        android:layout_marginTop="30dp"
        android:text="Button"
        android:background="@drawable/ic_control_point_black_24dp"
        app:layout_constraintLeft_toRightOf="@+id/otherimage"
        app:layout_constraintTop_toBottomOf="@+id/partyimage"
        android:layout_marginLeft="31dp" />
*/
/*================================================================================================================
  <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"

        android:elevation="?attr/actionBarSize"


        android:orientation="horizontal">

        <TextView
            android:id="@+id/textView5"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="5"
            android:padding="5dp"
            android:text="Balance "
            android:textColor="@android:color/white"
            android:textSize="30sp"
            android:textStyle="bold" />


        <TextView
            android:id="@+id/text"
            android:layout_width="0dp"
            android:layout_height="wrap_content"

            android:layout_alignParentEnd="true"
            android:layout_alignParentTop="true"
            android:layout_marginEnd="10dp"
            android:layout_weight="5"
            android:elevation="8dp"
            android:gravity="center"
            android:text="TextView"
            android:textAlignment="center"
            android:textSize="30sp" />

    </LinearLayout>
 */