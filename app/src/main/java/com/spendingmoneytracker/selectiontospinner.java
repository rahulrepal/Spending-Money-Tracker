package com.spendingmoneytracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class selectiontospinner extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "EXPENSES";
    public static final String DB_NAME="EXPENSEMANAGER";
    public static final String COL_1="ID";
    public static final String COL_2="NAME";
    public static final String COL_3="AMOUNT";
    public static final String COL_4="DATE";
    public static final String COL_5="DESCRIPTION";
    public static final String COL_6="MONTH";

    public static final String TABLE_REMINDERS="REMINDERS";

    public static final String COL_TITLE="TITLE";
    public static final String COL_DATE="DATE";
    public static final String COL_CATEGORY="CATEGORY";

    public static final String COL_DAINT="DATE_INT";








    selectiontospinner (Context context){
        super(context,DB_NAME,null,1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT NOT NULL,AMOUNT INTEGER NOT NULL,DATE TEXT,DESCRIPTION TEXT ,MONTH INTEGER)");

        db.execSQL("CREATE TABLE "+TABLE_REMINDERS+"(_ID INTEGER PRIMARY KEY AUTOINCREMENT,"+COL_TITLE+" TEXT,"+COL_CATEGORY+" TEXT,"+COL_DATE+" TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_REMINDERS);

        onCreate(db);
    }


    public boolean insertReminders(String title,String cat,String dat){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put(COL_TITLE,title);
        contentValues.put(COL_CATEGORY,cat);
        contentValues.put(COL_DATE,dat);


        long result_rem =db.insert(TABLE_REMINDERS,null,contentValues);
        if(result_rem==-1){
            return false;
        }
        else {
            return true;
        }

    }




    public boolean insertdetails(String Name, int Amount,String Date,String Desc,int mon){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put(COL_2,Name);
        contentValues.put(COL_3,Amount);
        contentValues.put(COL_4,Date);
        contentValues.put(COL_5,Desc);
        contentValues.put(COL_6,mon);

        long result =db.insert(TABLE_NAME,null,contentValues);
        if(result==-1){
            return false;
        }
        else {
            return true;
        }


    }


    public Cursor getData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public Cursor getData(int mont){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM EXPENSES WHERE MONTH="+mont,null);
        return res;
    }
    public void dropdatabase(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
    }

    public Cursor getSum()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT SUM(AMOUNT) As myTotal FROM EXPENSES where NAME<>'POCKET_MONEY';",null);
        return cursor;
    }
    public Cursor getSum(String Name)
    { String nam="'"+Name+"'";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select sum(AMOUNT) AS myNameTotal from EXPENSES where NAME='"+Name+"';",null);
        return cursor;
    }

    public Cursor getremaining(){

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cur_remaining=db.rawQuery("select sum(AMOUNT) AS myExpense from EXPENSES where NAME='POCKET_MONEY';",null);
        return cur_remaining;

    }

    public Cursor getByDate(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cur_date=db.rawQuery("SELECT DATE,sum(AMOUNT) from EXPENSES GROUP BY DATE",null);
        return cur_date;
    }

    public Cursor maxSpend(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT NAME,max(AMOUNT),DATE from EXPENSES WHERE NAME<>'POCKET_MONEY'",null);
        return cursor;
    }
    public Cursor maxSpend(int mont){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT NAME,max(AMOUNT),DATE from EXPENSES WHERE NAME<>'POCKET_MONEY' AND MONTH="+mont,null);
        return cursor;
    }
    public Cursor getByName(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cur_date=db.rawQuery("SELECT NAME,sum(AMOUNT) from EXPENSES WHERE NAME<>'POCKET_MONEY' GROUP BY NAME ",null);
        return cur_date;
    }
    public Cursor getByName(int mon){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cur_date=db.rawQuery("SELECT NAME,sum(AMOUNT) from EXPENSES WHERE NAME<>'POCKET_MONEY' AND MONTH="+mon+" GROUP BY NAME ",null);
        return cur_date;
    }

    public Cursor getReminders(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cur_rem=db.rawQuery("SELECT "+COL_TITLE+", "+COL_CATEGORY+","+COL_DATE+"  from "+TABLE_REMINDERS,null);
        return cur_rem;
    }
    public Cursor setSearchName(String category){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor Cursor_Search=db.rawQuery("SELECT NAME,"+COL_3+","+COL_4+","+COL_5+" FROM EXPENSES WHERE DESCRIPTION='"+category+"'",null);
        return Cursor_Search;
    }

    public Cursor delbydesc(String des,String d){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor Cursor_Search=db.rawQuery("DELETE FROM EXPENSES WHERE DESCRIPTION='"+des+"' AND DATE='"+d+"'",null);

        return Cursor_Search;
    }
    public void delby(String des){
        String whereClause = "DESCRIPTION=?";
        String[] whereArgs = new String[] { des };
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,whereClause,whereArgs);
    }

    public void delrem(String des){
        String whereClause = "TITLE=?";
        String[] whereArgs = new String[] { des };
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_REMINDERS,whereClause,whereArgs);
    }




}
