package josim74.github.com.sqlitepractice.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JosimUddin on 29/04/2018.
 */

public class EmployeeDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "employee_db";
    public static final int DATABASE_VERSION = 1;

    public static final String EMPLOYEE_TABLE = "tbl_employee";
    public static final String EMP_COL_ID = "emp_id";
    public static final String EMP_COL_NAME = "emp_name";
    public static final String EMP_COL_DESIGNATION = "emp_designation";

    public static final String CREATE_EMPLOYEE_TABLE = "CREATE TABLE "+EMPLOYEE_TABLE+" ("+
            EMP_COL_ID+" INTEGER PRIMARY KEY, "+
            EMP_COL_NAME+" TEXT, "+
            EMP_COL_DESIGNATION+" TEXT);";

    public EmployeeDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EMPLOYEE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
