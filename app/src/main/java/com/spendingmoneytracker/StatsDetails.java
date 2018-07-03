package com.spendingmoneytracker;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StatsDetails extends AppCompatActivity {

    selectiontospinner myDb;
    public static final String EXPENSE_NO="expnsno";
    private ArrayList<String> sNAME,sDATE,sDESC;
    private ArrayList<Integer> sAMOUNT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_details);
        myDb=new selectiontospinner(this);

        TextView snam=(TextView)findViewById(R.id.sname);
        TextView scate=(TextView)findViewById(R.id.scat);
        TextView samount=(TextView)findViewById(R.id.samt);
        TextView sdate=(TextView)findViewById(R.id.sdat);
        Button sdelete=(Button)findViewById(R.id.sdel);

        String orderNo=getIntent().getExtras().getString(EXPENSE_NO);

        sAMOUNT=new ArrayList<>();
        sNAME=new ArrayList<>();
        sDESC=new ArrayList<>();
        sDATE=new ArrayList<>();


        Cursor cursor=myDb.setSearchName(orderNo);
        while (cursor.moveToNext()){
            sNAME.add(cursor.getString(0));
            sAMOUNT.add(cursor.getInt(1));
            sDATE.add(cursor.getString(2));
            sDESC.add(cursor.getString(3));

        }


        snam.setText(sNAME.get(0));
        scate.setText(sDESC.get(0));
        sdate.setText(sDATE.get(0));
        samount.setText(String.valueOf(sAMOUNT.get(0)));



      sdelete.setOnClickListener(
              new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      myDb.delby(sDESC.get(0));
                      Toast.makeText(StatsDetails.this,sDESC.get(0)+"deleted successfully",Toast.LENGTH_SHORT).show();
                      Intent refresh_intent1=new Intent(StatsDetails.this,MainActivity.class);
                      refresh_intent1.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                      startActivity(refresh_intent1);
                      finish();

                  }
              }
      );






    }
}
