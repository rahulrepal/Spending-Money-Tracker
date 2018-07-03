package com.spendingmoneytracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by RahulSR on 8/2/2017.
 */

public class Reminders extends Fragment {


    @Nullable
  /* int[] rainfall={10,12,13,14,15};
    String[] month={"Jan","Feb","Mar","May","Jun"};
    String[] expense_name={"Hostel"};*/

            FloatingActionButton setReminder;
    selectiontospinner myDb;
    ArrayList<String> TIT,CATE,DAT;
    ListView rlist;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        View rootView = inflater.inflate(R.layout.reminders, container, false);

        setReminder=(FloatingActionButton) rootView.findViewById(R.id.setReminder);
        setReminder.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent_reminder=new Intent(getActivity(),Add_Reminder.class);
                        startActivity(intent_reminder);

                    }
                }
        );
        myDb = new selectiontospinner(getActivity());
        Cursor cursor_rm=myDb.getReminders();
        TIT=new ArrayList<>();
        CATE=new ArrayList<>();
        DAT=new ArrayList<>();

        while (cursor_rm.moveToNext()){
            TIT.add(cursor_rm.getString(0));
            CATE.add(cursor_rm.getString(1));
            DAT.add(cursor_rm.getString(2));
        }
        rlist=(ListView) rootView.findViewById(R.id.remList);
        ReminderLayout rl=new ReminderLayout();
        rlist.setAdapter(rl);
        rlist.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, final View view, int i, long l) {

                        AlertDialog.Builder rbuilder=new AlertDialog.Builder(getActivity());
                        rbuilder.setMessage("Do you really want to delete")
                                .setPositiveButton("Yes",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                TextView vt = (TextView) view.findViewById(R.id.rem_list_title);
                                                myDb.delrem(vt.getText().toString());
                                                Toast.makeText(getActivity(),"Successfully Deleted",Toast.LENGTH_SHORT).show();
                                                Intent refresh_intent1=new Intent(getActivity(),MainActivity.class);
                                                refresh_intent1.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                startActivity(refresh_intent1);
                                                getActivity().finish();
                                            }
                                        })
                                .setNegativeButton("No",null)
                                .setIcon(R.drawable.ic_action_alert);
                        AlertDialog dialog=rbuilder.create();
                        dialog.show();

                    }
                }
        );


        return rootView;





    }

    public class ReminderLayout extends BaseAdapter{

        @Override
        public int getCount() {
            return DAT.size();
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
        public View getView(int i, View rview, ViewGroup viewGroup) {
            rview=getActivity().getLayoutInflater().inflate(R.layout.reminer_list,null);
            TextView tit=(TextView)rview.findViewById(R.id.rem_list_title);

            TextView ca=(TextView)rview.findViewById(R.id.rem_list_cat);

            TextView da=(TextView)rview.findViewById(R.id.rem_list_dat);

            tit.setText(TIT.get(i));
            ca.setText(CATE.get(i));
            da.setText(DAT.get(i));

            return rview;

        }
    }
}
