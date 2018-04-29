package josim74.github.com.sqlitepractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import josim74.github.com.sqlitepractice.database.Employee;
import josim74.github.com.sqlitepractice.database.EmployeeDataSource;

public class ShowEmployeeListActivity extends AppCompatActivity {
    private ListView listView;
    private EmployeeDataSource source;
    private EmployeeAdapter employeeAdapter;
    private ArrayList<Employee> employees = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_employee_list);
        listView = findViewById(R.id.emp_list);

        source = new EmployeeDataSource(this);
        employees = source.getAllEmployees();

        employeeAdapter = new EmployeeAdapter(this, employees);
        listView.setAdapter(employeeAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int empId = employees.get(position).getEmpId();
                boolean status = source.deleteEmployee(empId);

                if (status) {
                    Toast.makeText(ShowEmployeeListActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                    reloadActivity();
                }else {
                    Toast.makeText(ShowEmployeeListActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int empId = employees.get(position).getEmpId();
                startActivity(new Intent(ShowEmployeeListActivity.this, MainActivity.class).putExtra("id", empId));
            }
        });
    }

    private void reloadActivity() {
        startActivity(new Intent(this, ShowEmployeeListActivity.class));
    }
}
