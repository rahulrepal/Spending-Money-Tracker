package com.spendingmoneytracker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.MainThread;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.spendingmoneytracker.Add_MOney.DIALOG_ID;

public class Main2Activity extends AppCompatActivity {


    public ImageView submitincome,cback;
    public EditText add_money_text;
    selectiontospinner MyDb;
    int year_x1, month_x1, day_x1;
    int nmonth_x1;
    static String date1;
    TextView setdate;
    int day_builder;
    int month_builder;
    ImageView setdateimage;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final Calendar calendar=Calendar.getInstance();
        year_x1=calendar.get(Calendar.YEAR);
        month_x1=calendar.get(Calendar.MONTH);
        day_x1=calendar.get(Calendar.DAY_OF_MONTH);
        day_builder=calendar.get(Calendar.DAY_OF_MONTH);

        month_builder=calendar.get(Calendar.MONTH)+1;

        MyDb=new selectiontospinner(this);
        setdateimage=(ImageView)findViewById(R.id.setdateimage);
        cback=(ImageView)findViewById(R.id.acback);
        submitincome=(ImageView) findViewById(R.id.submiticon);
        add_money_text=(EditText)findViewById(R.id.add_money_text);
        final Spinner in_spinner=(Spinner)findViewById(R.id.in_spinner);
        final TextView source=(TextView)findViewById(R.id.source);

        setdate = (TextView)findViewById(R.id.setdate);
        ArrayList<String> in_spinner_array=new ArrayList<>();
        in_spinner_array.add("POCKET_MONEY");
        ListAdapter in_spinner_list=new ArrayAdapter<>(Main2Activity.this,R.layout.support_simple_spinner_dropdown_item,in_spinner_array);

        in_spinner.setAdapter((SpinnerAdapter) in_spinner_list);
        setdate.setText(date1);

     cback.setOnClickListener(
             new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Intent refresh_intent1 = new Intent(Main2Activity.this, MainActivity.class);
                     refresh_intent1.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                     startActivity(refresh_intent1);
                     finish();
                 }
             }
     );

        String str_income=add_money_text.getText().toString();
    submitincome.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    /*if(add_money_text.getText().toString().equals(null)&&source.getText().toString().equals(null)){
                        Toast.makeText(Main2Activity.this, "Please Insert Data", Toast.LENGTH_LONG).show();
                    }
                   else{
                    boolean ifincomeinserted=MyDb.insertdetails(in_spinner.getSelectedItem().toString(),int_income,date1,source.getText().toString(),month_x1);
                    if (ifincomeinserted) {
                           //Toast.makeText(Main2Activity.this,str_income,Toast.LENGTH_LONG).show();

                        Intent refresh_intent1=new Intent(Main2Activity.this,MainActivity.class);
                        refresh_intent1.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        startActivity(refresh_intent1);
                        finish();
                    } else {

                        Toast.makeText(Main2Activity.this, "Data not inserted", Toast.LENGTH_LONG).show();
                    }}*/

                    if(add_money_text.getText().toString()!=null&&source.getText().toString()!=null&&date1!=null) {
                        String str_income=add_money_text.getText().toString();
                        int int_income=Integer.parseInt(str_income);
                        boolean ifincomeinserted = MyDb.insertdetails(in_spinner.getSelectedItem().toString(), int_income, date1, source.getText().toString(), month_x1);
                        if (ifincomeinserted) {
                            //Toast.makeText(Main2Activity.this,str_income,Toast.LENGTH_LONG).show();

                            Intent refresh_intent1 = new Intent(Main2Activity.this, MainActivity.class);
                            refresh_intent1.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            startActivity(refresh_intent1);
                            finish();
                        } else {

                            Toast.makeText(Main2Activity.this, "Data not inserted", Toast.LENGTH_LONG).show();
                        }

                    }
                    else {
                        Toast.makeText(Main2Activity.this, "Please Insert Data", Toast.LENGTH_LONG).show();
                    }


                }
            }
    );

        showondialog();
        setdate.setText(date1);


        Toast.makeText(Main2Activity.this,date1,Toast.LENGTH_SHORT).show();
}

    public void showondialog(){
        setdateimage.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDialog(DIALOG_ID);
                    }
                }
        );}

    @Override
    protected Dialog onCreateDialog(int id){
        if (id == DIALOG_ID) {
            return new DatePickerDialog(this, datepickerlistner, year_x1, month_x1, day_x1);
        }
        return null;
    }


    private DatePickerDialog.OnDateSetListener datepickerlistner =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                    year_x1 = year;
                    month_x1 = month+1;
                    day_x1 = day;
                    date1=day_x1+"/"+month_x1+"/"+year_x1;
                    setdate.setText(date1);

                    if(day_x1>day_builder||month_x1>month_builder){
                        View view = null;
                        AlertDialog.Builder rLbuilder=new AlertDialog.Builder(Main2Activity.this);
                        final View finalView = view;
                        rLbuilder.setMessage("Please select a valid date")
                                .setPositiveButton("OK",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {


                                                Intent refresh_intent1=new Intent(Main2Activity.this,Main2Activity.class);


                                                refresh_intent1.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                startActivity(refresh_intent1);
                                                Main2Activity.this.finish();
                                            }
                                        })

                                .setIcon(R.drawable.ic_action_alert);
                        AlertDialog dialog=rLbuilder.create();
                        dialog.show();
                    }
                    Toast.makeText(Main2Activity.this, year_x1 + "/" + month_x1 + "/" + day_x1, Toast.LENGTH_LONG).show();

                }

            };

    public static String getdate() {
        return date1;
    }

}
/*
* <?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.spendingmoneytracker.Add_MOney">

<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical">

    <ImageView
        android:id="@+id/add_background"
        android:layout_width="match_parent"
        android:layout_height="200dp"
        android:scaleType="fitXY"
        android:src="@drawable/entertainmentbackground"/>
    <TextView
        android:id="@+id/textView"
        android:layout_width="170dp"
        android:layout_height="34dp"
        android:layout_marginLeft="16dp"
        android:layout_marginStart="16dp"
        android:text="Spent On"
        android:textSize="20sp"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        android:layout_marginTop="16dp" />

    <Spinner
        android:id="@+id/my"
        android:layout_width="368dp"
        android:layout_height="wrap_content"
        android:layout_marginTop="8dp"
        app:layout_constraintTop_toBottomOf="@+id/textView"
        tools:layout_editor_absoluteX="8dp" />

    <TextView
        android:id="@+id/textView2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft="16dp"
        android:layout_marginStart="16dp"
        android:layout_marginTop="8dp"
        android:text="Amount"
        android:textSize="20sp"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/my" />


    <EditText
        android:id="@+id/editText"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="8dp"
        android:ems="10"
        android:inputType="number"
        app:layout_constraintTop_toBottomOf="@+id/textView2"
        tools:layout_editor_absoluteX="0dp" />

    <TextView
        android:id="@+id/textView4"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft="16dp"
        android:text="Date"
        android:textSize="20sp"
        app:layout_constraintLeft_toLeftOf="parent"
        android:layout_marginTop="8dp"
        app:layout_constraintTop_toBottomOf="@+id/editText"
        android:layout_marginStart="16dp" />

    <Button
        android:id="@+id/datepicker"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft="8dp"
        android:text="Date4"
        app:layout_constraintLeft_toRightOf="@+id/textView4"
        android:layout_marginTop="8dp"
        app:layout_constraintTop_toBottomOf="@+id/editText"
        android:layout_marginStart="8dp" />
</LinearLayout>

    <Button
        android:id="@+id/Submit"
        android:layout_width="106dp"
        android:layout_height="48dp"
        android:layout_marginBottom="27dp"
        android:layout_marginRight="16dp"
        android:background="@drawable/button"
        android:text="Submit"
        android:textAllCaps="false"
        android:textColor="@android:color/background_light"
        android:textSize="15sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        android:layout_marginEnd="17dp"
        android:layout_alignParentBottom="true"
        android:layout_alignParentEnd="true" />

</RelativeLayout>

*
* */