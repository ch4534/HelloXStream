package printer.start.itep.com.cn.helloxstream.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.ParcelUuid;

/**
 * Created by 辉 on 2016/5/24.
 */
public class SPBMDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SPBM.db";
    public static final String BMXX_TABLE_NAME = "bmxx";
    public static final String YHZC_TABLE_NAME = "yhzc";
    public static final String CUS_BMXX_TABLE_NAME = "curbmxx";
    private static final String CREATE_BMXX_SQL =
            "CREATE TABLE IF NOT EXISTS bmxx (_id INTEGER PRIMARY KEY AUTOINCREMENT, BB VARCHAR(20)" +
            ", QYSJ CHAR(8), GDQJZSJ CHAR(8), SPBM VARCHAR(20), SPMC VARCHAR(200), SM VARCHAR(4000), ZZSSL VARCHAR(60)," +
            " GJZ VARCHAR(1024), HZX CHAR(1), KYZT CHAR(1), ZZSTSGL VARCHAR(500), ZZSZCYJ VARCHAR(1000)," +
            " ZZSTSNRDM VARCHAR(100), XFSGL VARCHAR(500), XFSZCYJ VARCHAR(1000), XFSTSNRDM VARCHAR(100)," +
            " TJJBM VARCHAR(4000), HGJCKSPPM VARCHAR, PID VARCHAR(20), GXSJ CHAR(8))";
    private static final String CREATE_CUS_BMXX_SQL =
            "CREATE TABLE IF NOT EXISTS bmxx (_id INTEGER PRIMARY KEY AUTOINCREMENT, BB VARCHAR(20)" +
                    ", QYSJ CHAR(8), GDQJZSJ CHAR(8), SPBM VARCHAR(20), ZXBM VARCHAR(20), SPMC VARCHAR(200), SM VARCHAR(4000), ZZSSL VARCHAR(60)," +
                    " GJZ VARCHAR(1024), HZX CHAR(1), KYZT CHAR(1), ZZSTSGL VARCHAR(500), ZZSZCYJ VARCHAR(1000)," +
                    " ZZSTSNRDM VARCHAR(100), XFSGL VARCHAR(500), XFSZCYJ VARCHAR(1000), XFSTSNRDM VARCHAR(100)," +
                    " TJJBM VARCHAR(4000), HGJCKSPPM VARCHAR, PID VARCHAR(20), GXSJ CHAR(8))";
    private static final String CREATE_SL_SQL = "CREATE TABLE IF NOT EXISTS yhzc (_id INTEGER PRIMARY KEY AUTOINCREMENT, YHZCMC VARCHAR(120), SL VARCHAR(60))";
    private static final int DATABASE_VERSION = 1;

    public SPBMDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BMXX_SQL);
        db.execSQL(CREATE_SL_SQL);
        db.execSQL(CREATE_CUS_BMXX_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
