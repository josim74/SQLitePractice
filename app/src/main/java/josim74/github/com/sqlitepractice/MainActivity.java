package josim74.github.com.sqlitepractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import josim74.github.com.sqlitepractice.database.Employee;
import josim74.github.com.sqlitepractice.database.EmployeeDataSource;

public class MainActivity extends AppCompatActivity {
    EditText etName, etDes;
    Button btnSave, btnShow;
    private EmployeeDataSource source;
    private int empId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etDes = findViewById(R.id.etDesignation);
        btnSave = findViewById(R.id.btnSave);

        source = new EmployeeDataSource(this);

        empId = getIntent().getIntExtra("id", 0);
        if (empId > 0) {
            Employee employee = source.getEmployeeById(empId);
            etName.setText(employee.getName());
            etDes.setText(employee.getDesignation());
            btnSave.setText("Update");
        }

    }

    public void saveemp(View view) {
        if (empId > 0) {
            String name = etName.getText().toString();
            String designation = etDes.getText().toString();

            Employee employee = new Employee(empId, name, designation);
            boolean status = source.updateEmployee(employee);
            if (status) {
                Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
            }
        }else{
            String name = etName.getText().toString();
            String designation = etDes.getText().toString();

            Employee employee = new Employee(name, designation);
            boolean status = source.insertEmployee(employee);
            if (status) {
                Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
            }
        }


    }

    public void showemp(View view) {
        startActivity(new Intent(this, ShowEmployeeListActivity.class));
    }
}
