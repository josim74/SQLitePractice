package josim74.github.com.sqlitepractice;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import josim74.github.com.sqlitepractice.database.Employee;

/**
 * Created by JosimUddin on 25/04/2018.
 */

public class EmployeeAdapter extends ArrayAdapter<Employee> {

    private Context context;
    private ArrayList<Employee> employees;

    public EmployeeAdapter(@NonNull Context context, ArrayList<Employee> employees) {
        super(context, R.layout.employee_row ,employees);
        this.context = context;
        this.employees = employees;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.employee_row, parent, false);

        TextView tvName = convertView.findViewById(R.id.tv_name);
        TextView tvDesignation = convertView.findViewById(R.id.tv_designation);


        tvName.setText(employees.get(position).getName());
        tvDesignation.setText(employees.get(position).getDesignation());
        return convertView;
    }

// add new movie... to the list....
//    public void addNewMove(Movie movie) {
//        movies.add(movie);
//        notifyDataSetChanged();
//    }
}
