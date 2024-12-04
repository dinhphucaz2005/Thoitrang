package com.example.thoitrang.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thoitrang.database.DbHelper;
import com.example.thoitrang.model.ExHoaDon;

import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
    private SQLiteDatabase db;

    public HoaDonDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(ExHoaDon ob){

        ContentValues values = new ContentValues();
        values.put("maNV", ob.maNV);
        values.put("maKH",ob.maKH);
        values.put("maGiay",ob.maGiay);
        values.put("ngay",ob.ngay);
        values.put("giaHD",ob.giaHD);
        values.put("trangThai",ob.trangThai);
        return db.insert("HoaDon",null,values);
    }

    public int update(ExHoaDon ob){
        ContentValues values = new ContentValues();
        values.put("maNV", ob.maNV);
        values.put("maKH",ob.maKH);
        values.put("maGiay",ob.maGiay);
        values.put("ngay",ob.ngay);
        values.put("giaHD",ob.giaHD);
        values.put("trangThai",ob.trangThai);
        return db.update("HoaDon",values,"maHD=?", new String[]{String.valueOf(ob.maHD)});
    }

    public int delete(String id){
        return db.delete("HoaDon","maHD=?", new String[]{id});
    }

    public List<ExHoaDon> getAll(){
        String sql = "SELECT * FROM HoaDon";
        return getData(sql);
    }

    public ExHoaDon getID(String id){
        String sql = "SELECT * FROM HoaDon WHERE maHD=?";
        List<ExHoaDon> list = getData(sql, id);
        return list.get(0);
    }

    private List<ExHoaDon> getData(String sql, String...selectionArgs){
        List<ExHoaDon> list = new ArrayList<ExHoaDon>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            ExHoaDon ob = new ExHoaDon();
            ob.maHD = Integer.parseInt(c.getString(c.getColumnIndex("maHD")));
            ob.maNV = Integer.parseInt(c.getString(c.getColumnIndex("maNV")));
            ob.maKH = Integer.parseInt(c.getString(c.getColumnIndex("maKH")));
            ob.maGiay = Integer.parseInt(c.getString(c.getColumnIndex("maGiay")));
            ob.ngay = c.getString(c.getColumnIndex("ngay"));
            ob.giaHD = c.getString(c.getColumnIndex("giaHD"));
            ob.trangThai = Integer.parseInt(c.getString(c.getColumnIndex("trangThai")));

            list.add(ob);
        }
        return list;
    }
}
