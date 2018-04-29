package josim74.github.com.sqlitepractice.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by JosimUddin on 29/04/2018.
 */

public class EmployeeDataSource {
    private EmployeeDatabaseHelper helper;
    private SQLiteDatabase db;

    public EmployeeDataSource(Context context) {
        this.helper = new EmployeeDatabaseHelper(context);
    }

    public void open() {
        db = helper.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public boolean insertEmployee(Employee employee) {
        this.open();
        ContentValues values = new ContentValues();
        values.put(EmployeeDatabaseHelper.EMP_COL_NAME, employee.getName());
        values.put(EmployeeDatabaseHelper.EMP_COL_DESIGNATION, employee.getDesignation());
        long insertedRow = db.insert(EmployeeDatabaseHelper.EMPLOYEE_TABLE, null, values);
        this.close();
        if (insertedRow > 0) {
            return  true;
        }else {
            return false;
        }
    }

    public ArrayList<Employee> getAllEmployees() {
        this.open();
        ArrayList<Employee> employees = new ArrayList<>();
        //db.rawQuery("select * from "+EmployeeDatabaseHelper.EMPLOYEE_TABLE, null);

       Cursor cursor = db.query(EmployeeDatabaseHelper.EMPLOYEE_TABLE, null, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do{
                int empID = cursor.getInt(cursor.getColumnIndex(EmployeeDatabaseHelper.EMP_COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseHelper.EMP_COL_NAME));
                String designation = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseHelper.EMP_COL_DESIGNATION));
                employees.add(new Employee(empID, name, designation));
            }while (cursor.moveToNext());
        }
        cursor.close();
        this.close();

        return employees;
    }

    public boolean deleteEmployee(int empId) {
        this.open();
        int deletedRow = db.delete(EmployeeDatabaseHelper.EMPLOYEE_TABLE, EmployeeDatabaseHelper.EMP_COL_ID + "=" + empId, null);
        if (deletedRow > 0) {
            return  true;
        }else {
            return false;
        }
    }

    public Employee getEmployeeById(int empId) {
        this.open();
        Employee employee = null;
        Cursor cursor = db.query(EmployeeDatabaseHelper.EMPLOYEE_TABLE, null, EmployeeDatabaseHelper.EMP_COL_ID + "=" + empId, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int empID = cursor.getInt(cursor.getColumnIndex(EmployeeDatabaseHelper.EMP_COL_ID));
            String name = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseHelper.EMP_COL_NAME));
            String designation = cursor.getString(cursor.getColumnIndex(EmployeeDatabaseHelper.EMP_COL_DESIGNATION));
            employee = new Employee(empID, name, designation);
        }
        this.close();
        return employee;
    }

    public boolean updateEmployee(Employee employee) {
        this.open();
        ContentValues values = new ContentValues();
        values.put(EmployeeDatabaseHelper.EMP_COL_NAME, employee.getName());
        values.put(EmployeeDatabaseHelper.EMP_COL_DESIGNATION, employee.getDesignation());
        int updatedRow = db.update(EmployeeDatabaseHelper.EMPLOYEE_TABLE, values, EmployeeDatabaseHelper.EMP_COL_ID + "=" + employee.getEmpId(), null);
        if (updatedRow > 0) {
            return  true;
        }else {
            return false;
        }
    }


}
