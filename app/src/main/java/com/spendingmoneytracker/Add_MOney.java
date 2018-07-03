package com.spendingmoneytracker;

        import android.app.DatePickerDialog;
        import android.app.Dialog;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.database.Cursor;
        import android.icu.util.Calendar;
        import android.os.Build;
        import android.support.annotation.RequiresApi;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.DatePicker;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.Toast;

public class Add_MOney extends AppCompatActivity {
    //______________________________________________________________________________________________________________________________________________


    ImageView submitbtn,datebtn,aback;
    Spinner spinner;
    String Name_;
    int Amount_;
    int day_builder;
    int month_builder;



    static String date;
    selectiontospinner myDb;
    int year_x, month_x, day_x;
    int nmonth_x;
    String daten;
    static final int DIALOG_ID = 0;

    //__________________________________________________________________________________________________________________________________________________

    TextView set_date1;
    public static final String SELECTED_STRING = "spinner";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__money);

        //========================================================================
         LinearLayout add_background= (LinearLayout) findViewById(R.id.linear);
        datebtn = (ImageView) findViewById(R.id.datepicker);
        aback = (ImageView) findViewById(R.id.amback);
        submitbtn = (ImageView) findViewById(R.id.Submit);
        spinner = (Spinner) findViewById(R.id.my);
        final EditText amount = (EditText) findViewById(R.id.editText);
        final EditText source1=(EditText)findViewById(R.id.source1);

        set_date1 = (TextView)findViewById(R.id.setdate1);
        amount.setText(null);
        //===============================================================


        //==========================================================================
        String selected_string = (String) getIntent().getExtras().get(SELECTED_STRING);
        myDb = new selectiontospinner(this);
        final String Name;
        final Calendar calendar=Calendar.getInstance();
        year_x=calendar.get(Calendar.YEAR);
        month_x=calendar.get(Calendar.MONTH);
        day_x=calendar.get(Calendar.DAY_OF_MONTH);
        day_builder=calendar.get(Calendar.DAY_OF_MONTH);

        month_builder=calendar.get(Calendar.MONTH)+1;


        //=====================================================================

        //========================================Array Declaration===================================================================

        String[] Other = {"Hostel", "Entertainment", "Mess","Dinner", "Recharges", "Wifi", "Transportation", "Party", "Shopping", "Utilities","Petrol", "Other"};
        String[] Hostel = {"Hostel","Lodging"};
        String[] Entertainment = {"Entertainment","Movie"};
        String[] Mess = {"Mess","Dinner"};
        String[] Recharges = {"Recharges"};
        String[] Wifi = {"Wifi"};
        String[] Transportation = {"Transportation"};
        String[] Party = {"Party"};
        String[] Shopping = {"Shopping"};
        String[] Utilities = {"Utilities","Petrol"};
        //==========================================End of Array Declaration===================================================


        //==========================================Adapter Declaration=========================================================

        final ArrayAdapter<String> Hostel1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Hostel);
        final ArrayAdapter<String> Entertainment1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Entertainment);
        final ArrayAdapter<String> Mess1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Mess);
        final ArrayAdapter<String> Recharges1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Recharges);
        final ArrayAdapter<String> Wifi1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Wifi);
        final ArrayAdapter<String> Transportation1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Transportation);
        final ArrayAdapter<String> Party1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Party);
        final ArrayAdapter<String> Shopping1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Shopping);
        final ArrayAdapter<String> Utilities1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Utilities);
        final ArrayAdapter<String> Other1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Other);
        //========================================End Of Adapter Declaration====================================================


        //==================================Adapter Selector======================================================
        Add_Money_Image_Holder add_money_image_holder;
        if (selected_string.equals("Hostel")) {
            spinner.setAdapter(Hostel1);
            Name = "Hostel";
          //  add_money_image_holder=Add_Money_Image_Holder.image_list[0];
          //  add_background.setBackgroundResource(add_money_image_holder.getImageId());

        } else if (selected_string.equals("Entertainment")) {
            spinner.setAdapter(Entertainment1);
            Name = "Entertainment";
          //  add_money_image_holder=Add_Money_Image_Holder.image_list[1];
          //  add_background.setBackgroundResource(add_money_image_holder.getImageId());
        }else if (selected_string.equals("Mess")) {
            spinner.setAdapter(Mess1);
           // add_money_image_holder=Add_Money_Image_Holder.image_list[2];
          //  add_background.setBackgroundResource(add_money_image_holder.getImageId());

            Name = "Mess";
        }  else if (selected_string.equals("Recharges")) {
            spinner.setAdapter(Recharges1);
           // add_money_image_holder=Add_Money_Image_Holder.image_list[3];
           // add_background.setBackgroundResource(add_money_image_holder.getImageId());

            Name = "Recharges";
        } else if (selected_string.equals("Wifi")) {
            //add_money_image_holder=Add_Money_Image_Holder.image_list[4];
            //add_background.setBackgroundResource(add_money_image_holder.getImageId());
            spinner.setAdapter(Wifi1);
            Name = "Wifi";
        } else if (selected_string.equals("Transportation")) {
            spinner.setAdapter(Transportation1);
            Name = "Transportation";
          //  add_money_image_holder=Add_Money_Image_Holder.image_list[5];
          //  add_background.setBackgroundResource(add_money_image_holder.getImageId());
        } else if (selected_string.equals("Party")) {
            spinner.setAdapter(Party1);
            Name = "Party";
          // add_money_image_holder=Add_Money_Image_Holder.image_list[6];
          //  add_background.setBackgroundResource(add_money_image_holder.getImageId());
        } else if (selected_string.equals("Shopping")) {
            spinner.setAdapter(Shopping1);
            Name = "Shopping";
           // add_money_image_holder=Add_Money_Image_Holder.image_list[7];
           // add_background.setBackgroundResource(add_money_image_holder.getImageId());
        }
        else if (selected_string.equals("Utilities")) {
            spinner.setAdapter(Utilities1);
            Name = "Utilities";
            // add_money_image_holder=Add_Money_Image_Holder.image_list[7];
            // add_background.setBackgroundResource(add_money_image_holder.getImageId());
        }else {
            spinner.setAdapter(Other1);
            Name = "Other";
            //add_money_image_holder=Add_Money_Image_Holder.image_list[8];
          //  add_background.setBackgroundResource(add_money_image_holder.getImageId());
        }
        //==================================End of adapter Selector=======================================================


        //=============================================Events=====================================================

        aback.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        
                        Intent Lintent=new Intent(Add_MOney.this,MainActivity.class);
                        startActivity(Lintent);
                        finish();
                    }
                }
        );
        submitbtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String amt=amount.getText().toString();


                       /* if (amount.getText().toString().equals(null)||source1.getText().toString().equals(null)||daten.equals(null)) {
                            Toast.makeText(Add_MOney.this, "Please Insert Data", Toast.LENGTH_SHORT).show();
                        } else {
                            String amt1= amount.getText().toString();
                            int amt2=Integer.parseInt(amt1);
                            Toast.makeText(Add_MOney.this, amt, Toast.LENGTH_SHORT).show();
                            nmonth_x=month_x++;

                            boolean ifinserted = myDb.insertdetails(spinner.getSelectedItem().toString(), amt2,daten,source1.getText().toString(),month_x-1);
                            if (ifinserted) {
                                Intent refresh_intent1=new Intent(Add_MOney.this,MainActivity.class);
                                refresh_intent1.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                startActivity(refresh_intent1);
                                finish();
                            } else {

                                Toast.makeText(Add_MOney.this, "Data not inserted", Toast.LENGTH_LONG).show();
                            }


                        }*/
                        if (amount.getText().toString()!=null&&source1.getText().toString()!=null&&daten!=null){
                            String amt1= amount.getText().toString();
                            int amt2=Integer.parseInt(amt1);
                            Toast.makeText(Add_MOney.this, amt, Toast.LENGTH_SHORT).show();
                            nmonth_x=month_x++;

                            boolean ifinserted = myDb.insertdetails(spinner.getSelectedItem().toString(), amt2,daten,source1.getText().toString(),month_x-1);
                            if (ifinserted) {
                                Intent refresh_intent1=new Intent(Add_MOney.this,MainActivity.class);
                                refresh_intent1.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                startActivity(refresh_intent1);
                                finish();
                                set_date1.setText(date);
                            } else {

                                Toast.makeText(Add_MOney.this, "Data not inserted", Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            Toast.makeText(Add_MOney.this, "Please Insert Data", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        //======================================End of events=================================================


        showondialog();

set_date1.setText(daten);

    }




    public void showondialog(){
        datebtn.setOnClickListener(
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
            return new DatePickerDialog(this, datepickerlistner, year_x, month_x, day_x);
        }
        return null;
    }


    private DatePickerDialog.OnDateSetListener datepickerlistner =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                    year_x = year;
                    month_x = month+1;
                    day_x = day;
                    date=day_x+"/"+month_x+"/"+year_x;
                     daten = day_x + "/" + month_x + "/" + year_x;
                    set_date1.setText(date);
       if(day_x>day_builder||month_x>month_builder){
           View view = null;
           AlertDialog.Builder rLbuilder=new AlertDialog.Builder(Add_MOney.this);
           final View finalView = view;
           rLbuilder.setMessage("Please select a valid date")
                   .setPositiveButton("OK",
                           new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialogInterface, int i) {


                                   Intent refresh_intent1=new Intent(Add_MOney.this,Add_MOney.class);
                                   refresh_intent1.putExtra(Add_MOney.SELECTED_STRING,(String) "Other");

                                   refresh_intent1.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                   startActivity(refresh_intent1);
                                   Add_MOney.this.finish();
                               }
                           })

                   .setIcon(R.drawable.ic_action_alert);
           AlertDialog dialog=rLbuilder.create();
           dialog.show();
       }

                    Toast.makeText(Add_MOney.this, year_x + "/" + month_x + "/" + day_x, Toast.LENGTH_LONG).show();

                }

            };

    public static String getdate() {
        return date;
    }


//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}










