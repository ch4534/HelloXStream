package printer.start.itep.com.cn.helloxstream.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.List;

import printer.start.itep.com.cn.helloxstream.Business.BMXX;
import printer.start.itep.com.cn.helloxstream.Business.ZZSYHZC;

/**
 * Created by 辉 on 2016/5/24.
 */
public class DBManager {

    private SPBMDBHelper mHelper;
    private SQLiteDatabase mDatabase;

    public DBManager(Context context){
        mHelper = new SPBMDBHelper(context);
        mDatabase = mHelper.getWritableDatabase();
    }

    public void AddBMXX(BMXX bmxx){
        mDatabase.beginTransaction();
        try{
            String sql = "INSERT INTO " + SPBMDBHelper.BMXX_TABLE_NAME + " VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Object[] params = new Object[]{bmxx.BB, bmxx.QYSJ, bmxx.GDQJZSJ, bmxx.SPBM, bmxx.SPMC,
                    bmxx.SM, bmxx.ZZSSL, bmxx.GJZ, bmxx.HZX, bmxx.KYZT, bmxx.ZZSTSGL, bmxx.ZZSZCYJ,
                    bmxx.ZZSTSNRDM, bmxx.XFSGL, bmxx.XFSZCYJ, bmxx.XFSTSNRDM, bmxx.TJJBM, bmxx.HGJCKSPPM,
                    bmxx.PID, bmxx.GXSJ};
            mDatabase.execSQL(sql, params);
            mDatabase.setTransactionSuccessful();
        }
        finally {
            mDatabase.endTransaction();
        }
    }

    public void AddBMXX(List<BMXX> bmxxList){
        mDatabase.beginTransaction();
        try{
            String sql = "INSERT INTO " + SPBMDBHelper.BMXX_TABLE_NAME + " VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            for (BMXX bmxx : bmxxList){
                Object[] params = new Object[]{bmxx.BB, bmxx.QYSJ, bmxx.GDQJZSJ, bmxx.SPBM, bmxx.SPMC, bmxx.SM, bmxx.ZZSSL, bmxx.GJZ, bmxx.HZX, bmxx.KYZT, bmxx.ZZSTSGL, bmxx.ZZSZCYJ, bmxx.ZZSTSNRDM, bmxx.XFSGL, bmxx.XFSZCYJ, bmxx.XFSTSNRDM, bmxx.TJJBM, bmxx.HGJCKSPPM, bmxx.PID, bmxx.GXSJ};
                mDatabase.execSQL(sql, params);
            }
            mDatabase.setTransactionSuccessful();
        }
        finally {
            mDatabase.endTransaction();
        }
    }

    public void AddCusBMXX(BMXX bmxx){
        mDatabase.beginTransaction();
        try{
            String sql = "INSERT INTO " + SPBMDBHelper.CUS_BMXX_TABLE_NAME + " VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Object[] params = new Object[]{bmxx.BB, bmxx.QYSJ, bmxx.GDQJZSJ, bmxx.SPBM, bmxx.SPMC,
                    bmxx.SM, bmxx.ZZSSL, bmxx.GJZ, bmxx.HZX, bmxx.KYZT, bmxx.ZZSTSGL, bmxx.ZZSZCYJ,
                    bmxx.ZZSTSNRDM, bmxx.XFSGL, bmxx.XFSZCYJ, bmxx.XFSTSNRDM, bmxx.TJJBM, bmxx.HGJCKSPPM,
                    bmxx.PID, bmxx.GXSJ};
            mDatabase.execSQL(sql, params);
            mDatabase.setTransactionSuccessful();
        }
        finally {
            mDatabase.endTransaction();
        }
    }

    public void AddCusBMXX(List<BMXX> bmxxList){
        mDatabase.beginTransaction();
        try{
            String sql = "INSERT INTO " + SPBMDBHelper.CUS_BMXX_TABLE_NAME + " VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            for (BMXX bmxx : bmxxList){
                Object[] params = new Object[]{bmxx.BB, bmxx.QYSJ, bmxx.GDQJZSJ, bmxx.SPBM, bmxx.SPMC, bmxx.SM, bmxx.ZZSSL, bmxx.GJZ, bmxx.HZX, bmxx.KYZT, bmxx.ZZSTSGL, bmxx.ZZSZCYJ, bmxx.ZZSTSNRDM, bmxx.XFSGL, bmxx.XFSZCYJ, bmxx.XFSTSNRDM, bmxx.TJJBM, bmxx.HGJCKSPPM, bmxx.PID, bmxx.GXSJ};
                mDatabase.execSQL(sql, params);
            }
            mDatabase.setTransactionSuccessful();
        }
        finally {
            mDatabase.endTransaction();
        }
    }

    public void AddYHZC(ZZSYHZC zzsyhz){
        mDatabase.beginTransaction();
        try{
            String sql = "INSERT INTO " + SPBMDBHelper.YHZC_TABLE_NAME + " VALUES(null, ?, ?)";
            Object[] params = new Object[]{zzsyhz.YHZCMC, zzsyhz.SL};
            mDatabase.execSQL(sql, params);
            mDatabase.setTransactionSuccessful();
        }
        finally {
            mDatabase.endTransaction();
        }
    }

    public void AddYHZC(List<ZZSYHZC> zzsyzclist){
        mDatabase.beginTransaction();
        try{
            String sql = "INSERT INTO " + SPBMDBHelper.YHZC_TABLE_NAME + " VALUES(null, ?, ?)";

            for (ZZSYHZC zzsyhz : zzsyzclist){
                Object[] params = new Object[]{zzsyhz.YHZCMC, zzsyhz.SL};
                mDatabase.execSQL(sql, params);
            }
            mDatabase.setTransactionSuccessful();
        }
        finally {
            mDatabase.endTransaction();
        }
    }

    public void UpdateBMXX(BMXX bmxx){
        ContentValues cv = new ContentValues();
        cv.put("BB", bmxx.BB);
        cv.put("QYSJ", bmxx.QYSJ);
        cv.put("GDQJZSJ", bmxx.GDQJZSJ);
        cv.put("SPBM", bmxx.SPBM);
        cv.put("SPMC", bmxx.SPMC);
        cv.put("SM", bmxx.SM);
        cv.put("ZZSSL", bmxx.ZZSSL);
        cv.put("GJZ", bmxx.GJZ);
        cv.put("HZX", bmxx.HZX);
        cv.put("KYZT", bmxx.KYZT);
        cv.put("ZZSTSGL", bmxx.ZZSTSGL);
        cv.put("ZZSZCYJ", bmxx.ZZSZCYJ);
        cv.put("ZZSTSNRDM", bmxx.ZZSTSNRDM);
        cv.put("XFSGL", bmxx.XFSGL);
        cv.put("XFSZCYJ", bmxx.XFSZCYJ);
        cv.put("XFSTSNRDM", bmxx.XFSTSNRDM);
        cv.put("TJJBM", bmxx.TJJBM);
        cv.put("HGJCKSPPM", bmxx.HGJCKSPPM);
        cv.put("PID", bmxx.PID);
        cv.put("GXSJ", bmxx.GXSJ);

        mDatabase.update(SPBMDBHelper.BMXX_TABLE_NAME, cv, "SPBM = ?", new String[]{bmxx.SPBM});
    }

    public void UpdateCusBMXXList(List<BMXX> bmxxList){
        for (BMXX bmxx : bmxxList){
            UpdateCusBMXX(bmxx);
        }
    }

    public void UpdateCusBMXX(BMXX bmxx){
        ContentValues cv = new ContentValues();
        cv.put("BB", bmxx.BB);
        cv.put("QYSJ", bmxx.QYSJ);
        cv.put("GDQJZSJ", bmxx.GDQJZSJ);
        cv.put("SPBM", bmxx.SPBM);
        cv.put("SPMC", bmxx.SPMC);
        cv.put("SM", bmxx.SM);
        cv.put("ZZSSL", bmxx.ZZSSL);
        cv.put("GJZ", bmxx.GJZ);
        cv.put("HZX", bmxx.HZX);
        cv.put("KYZT", bmxx.KYZT);
        cv.put("ZZSTSGL", bmxx.ZZSTSGL);
        cv.put("ZZSZCYJ", bmxx.ZZSZCYJ);
        cv.put("ZZSTSNRDM", bmxx.ZZSTSNRDM);
        cv.put("XFSGL", bmxx.XFSGL);
        cv.put("XFSZCYJ", bmxx.XFSZCYJ);
        cv.put("XFSTSNRDM", bmxx.XFSTSNRDM);
        cv.put("TJJBM", bmxx.TJJBM);
        cv.put("HGJCKSPPM", bmxx.HGJCKSPPM);
        cv.put("PID", bmxx.PID);
        cv.put("GXSJ", bmxx.GXSJ);

        mDatabase.update(SPBMDBHelper.CUS_BMXX_TABLE_NAME, cv, "SPBM = ?", new String[]{bmxx.SPBM});
    }

    public void UpdateBMXXList(List<BMXX> bmxxList){
        for (BMXX bmxx : bmxxList){
            UpdateBMXX(bmxx);
        }
    }

    public void RmBMXXList(List<BMXX> bmxxList){
        for (BMXX bmxx : bmxxList){
            DelBMXX(bmxx.SPBM);
        }
    }

    public void DelBMXX(String spbm){
        mDatabase.delete(mHelper.BMXX_TABLE_NAME, "SPBM = ?", new String[]{spbm});
        if (this.IsExist(SPBMDBHelper.BMXX_TABLE_NAME, "PID", spbm)){
            List<BMXX> bmxxList = this.queryBMXX(new String[]{"PID"}, new String[]{spbm});
            RmBMXXList(bmxxList);
        }
    }

    public void DelBMXXList(List<String> spbmlist){
        for (String spbm : spbmlist){
            DelBMXX(spbm);
        }
    }

    public void DelCusBMXX(String spbm){
        mDatabase.delete(mHelper.CUS_BMXX_TABLE_NAME, "SPBM = ?", new String[]{spbm});
    }

    public void DelCusBMXXList(List<String> spbmlist){
        for (String spbm : spbmlist){
            DelCusBMXX(spbm);
        }
    }

    public List<BMXX> queryBMXX(){
        List<BMXX> bmxxList = new ArrayList<>();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + SPBMDBHelper.BMXX_TABLE_NAME + " ORDER BY SPBM ASC", null);
        while (cursor.moveToNext()){
            BMXX bmxx = new BMXX();
            bmxx.BB = cursor.getString(cursor.getColumnIndex("BB"));
            bmxx.QYSJ = cursor.getString(cursor.getColumnIndex("QYSJ"));
            bmxx.GDQJZSJ = cursor.getString(cursor.getColumnIndex("GDQJZSJ"));
            bmxx.SPBM = cursor.getString(cursor.getColumnIndex("SPBM"));
            bmxx.SPMC = cursor.getString(cursor.getColumnIndex("SPMC"));
            bmxx.SM = cursor.getString(cursor.getColumnIndex("SM"));
            bmxx.ZZSSL = cursor.getString(cursor.getColumnIndex("ZZSSL"));
            bmxx.GJZ = cursor.getString(cursor.getColumnIndex("GJZ"));
            bmxx.HZX = cursor.getString(cursor.getColumnIndex("HZX"));
            bmxx.KYZT = cursor.getString(cursor.getColumnIndex("KYZT"));
            bmxx.ZZSTSGL = cursor.getString(cursor.getColumnIndex("ZZSTSGL"));
            bmxx.ZZSZCYJ = cursor.getString(cursor.getColumnIndex("ZZSZCYJ"));
            bmxx.ZZSTSNRDM = cursor.getString(cursor.getColumnIndex("ZZSTSNRDM"));
            bmxx.XFSGL = cursor.getString(cursor.getColumnIndex("XFSGL"));
            bmxx.XFSZCYJ = cursor.getString(cursor.getColumnIndex("XFSZCYJ"));
            bmxx.XFSTSNRDM = cursor.getString(cursor.getColumnIndex("XFSTSNRDM"));
            bmxx.TJJBM = cursor.getString(cursor.getColumnIndex("TJJBM"));
            bmxx.HGJCKSPPM = cursor.getString(cursor.getColumnIndex("HGJCKSPPM"));
            bmxx.PID = cursor.getString(cursor.getColumnIndex("PID"));
            bmxx.GXSJ = cursor.getString(cursor.getColumnIndex("GXSJ"));
            bmxxList.add(bmxx);
        }

        cursor.close();
        return bmxxList;
    }

    public List<BMXX> queryBMXX(String[] keys, String[] values){
        return queryBMXX(keys, values, false);
    }

    public List<BMXX> queryBMXX(String[] keys, String[] values, boolean bASC){
        List<BMXX> bmxxList = new ArrayList<>();
        String sql = "SELECT * FROM " + SPBMDBHelper.BMXX_TABLE_NAME;
        if (keys.length > 0 && values.length > 0){
            int count = keys.length > values.length ? values.length : keys.length;

            sql += " WHERE ";
            for (int i = 0; i < count; ++i){
                if (i == 0) {
                    sql += keys[i] + "=" + values[i];
                }
                else{
                    sql += " AND " + keys[i] + "=" + values[i];
                }
            }
        }

        if (bASC){
            sql += " ORDER BY SPBM ASC";
        }
        else {
            sql += " ORDER BY SPBM DESC";
        }

        Cursor cursor = mDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()){
            BMXX bmxx = new BMXX();
            bmxx.BB = cursor.getString(cursor.getColumnIndex("BB"));
            bmxx.QYSJ = cursor.getString(cursor.getColumnIndex("QYSJ"));
            bmxx.GDQJZSJ = cursor.getString(cursor.getColumnIndex("GDQJZSJ"));
            bmxx.SPBM = cursor.getString(cursor.getColumnIndex("SPBM"));
            bmxx.SPMC = cursor.getString(cursor.getColumnIndex("SPMC"));
            bmxx.SM = cursor.getString(cursor.getColumnIndex("SM"));
            bmxx.ZZSSL = cursor.getString(cursor.getColumnIndex("ZZSSL"));
            bmxx.GJZ = cursor.getString(cursor.getColumnIndex("GJZ"));
            bmxx.HZX = cursor.getString(cursor.getColumnIndex("HZX"));
            bmxx.KYZT = cursor.getString(cursor.getColumnIndex("KYZT"));
            bmxx.ZZSTSGL = cursor.getString(cursor.getColumnIndex("ZZSTSGL"));
            bmxx.ZZSZCYJ = cursor.getString(cursor.getColumnIndex("ZZSZCYJ"));
            bmxx.ZZSTSNRDM = cursor.getString(cursor.getColumnIndex("ZZSTSNRDM"));
            bmxx.XFSGL = cursor.getString(cursor.getColumnIndex("XFSGL"));
            bmxx.XFSZCYJ = cursor.getString(cursor.getColumnIndex("XFSZCYJ"));
            bmxx.XFSTSNRDM = cursor.getString(cursor.getColumnIndex("XFSTSNRDM"));
            bmxx.TJJBM = cursor.getString(cursor.getColumnIndex("TJJBM"));
            bmxx.HGJCKSPPM = cursor.getString(cursor.getColumnIndex("HGJCKSPPM"));
            bmxx.PID = cursor.getString(cursor.getColumnIndex("PID"));
            bmxx.GXSJ = cursor.getString(cursor.getColumnIndex("GXSJ"));
            bmxxList.add(bmxx);
        }

        cursor.close();
        return bmxxList;
    }

    public List<BMXX> queryBMXX(String likeSPBM){
        List<BMXX> bmxxList = new ArrayList<>();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + SPBMDBHelper.BMXX_TABLE_NAME + " WHERE SPBM like '" + likeSPBM + "%' ORDER BY SPBM ASC", null);
        while (cursor.moveToNext()){
            BMXX bmxx = new BMXX();
            bmxx.BB = cursor.getString(cursor.getColumnIndex("BB"));
            bmxx.QYSJ = cursor.getString(cursor.getColumnIndex("QYSJ"));
            bmxx.GDQJZSJ = cursor.getString(cursor.getColumnIndex("GDQJZSJ"));
            bmxx.SPBM = cursor.getString(cursor.getColumnIndex("SPBM"));
            bmxx.SPMC = cursor.getString(cursor.getColumnIndex("SPMC"));
            bmxx.SM = cursor.getString(cursor.getColumnIndex("SM"));
            bmxx.ZZSSL = cursor.getString(cursor.getColumnIndex("ZZSSL"));
            bmxx.GJZ = cursor.getString(cursor.getColumnIndex("GJZ"));
            bmxx.HZX = cursor.getString(cursor.getColumnIndex("HZX"));
            bmxx.KYZT = cursor.getString(cursor.getColumnIndex("KYZT"));
            bmxx.ZZSTSGL = cursor.getString(cursor.getColumnIndex("ZZSTSGL"));
            bmxx.ZZSZCYJ = cursor.getString(cursor.getColumnIndex("ZZSZCYJ"));
            bmxx.ZZSTSNRDM = cursor.getString(cursor.getColumnIndex("ZZSTSNRDM"));
            bmxx.XFSGL = cursor.getString(cursor.getColumnIndex("XFSGL"));
            bmxx.XFSZCYJ = cursor.getString(cursor.getColumnIndex("XFSZCYJ"));
            bmxx.XFSTSNRDM = cursor.getString(cursor.getColumnIndex("XFSTSNRDM"));
            bmxx.TJJBM = cursor.getString(cursor.getColumnIndex("TJJBM"));
            bmxx.HGJCKSPPM = cursor.getString(cursor.getColumnIndex("HGJCKSPPM"));
            bmxx.PID = cursor.getString(cursor.getColumnIndex("PID"));
            bmxx.GXSJ = cursor.getString(cursor.getColumnIndex("GXSJ"));
            bmxxList.add(bmxx);
        }

        cursor.close();
        return bmxxList;
    }

    public List<ZZSYHZC> queryZZSYHZC(){
        List<ZZSYHZC> zzsyList = new ArrayList<>();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + SPBMDBHelper.YHZC_TABLE_NAME, null);
        while (cursor.moveToNext()){
            ZZSYHZC zzsyzc = new ZZSYHZC();
            zzsyzc.YHZCMC = cursor.getString(cursor.getColumnIndex("YHZCMC"));
            zzsyzc.SL = cursor.getString(cursor.getColumnIndex("SL"));
            zzsyList.add(zzsyzc);
        }

        cursor.close();
        return zzsyList;
    }

    public void Clear(String TableName){
        mDatabase.delete(TableName, null, null);
    }

    public boolean IsExist(String TableName, String key, String value){
        String sql = "SELECT * FROM " + TableName + " WHERE " + key + "= " + value + " limit 1";
        Cursor cursor = mDatabase.rawQuery(sql, null);
        if (cursor.getCount() > 0){
            return true;
        }
        else {
            return false;
        }
    }
}
