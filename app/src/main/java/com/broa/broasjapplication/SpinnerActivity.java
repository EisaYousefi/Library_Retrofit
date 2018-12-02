package com.broa.broasjapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SpinnerActivity extends AppCompatActivity {
    EditText addvalues_Spinner;
    Button btn_add,btn_remove;
    Spinner spinner,spinner_custom;
    List<String> list,listCustom;
    ArrayAdapter adapter;
    SpinnerAdaptor adapterCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);



        addvalues_Spinner=findViewById(R.id.input_add_spinner);
        spinner          =findViewById(R.id.spinner_SpinnerActivity);
        btn_add          =findViewById(R.id.btn_addSpinner);
        btn_remove       =findViewById(R.id.btn_remoe_spinner);
        spinner_custom   =findViewById(R.id.spinner_custom);

        list=new ArrayList<>();
        list.add("EisA");
        list.add("Broa");
        list.add("Mosa");
        list.add("Salam");
        list.add("Ali");
        adapter=new ArrayAdapter(this,android.R.layout
                .simple_expandable_list_item_1,list);
        spinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addvalues_Spinner.getText().toString().equals(""))
                {
                    Toast.makeText(SpinnerActivity.this, "فیلد خالی است", Toast.LENGTH_SHORT).show();
                }else
                {
                    list.add(addvalues_Spinner.getText().toString().trim());
                    addvalues_Spinner.setText("");
                    adapter.notifyDataSetChanged();
                }
            }
        });
        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int position = spinner.getSelectedItemPosition();
                    list.remove(position);
                    adapter.notifyDataSetChanged();
                }catch (Exception e){
                    Toast.makeText(SpinnerActivity.this, "آیتمی برای حذف وجود ندارد", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //----------Spinner Custom--
        listCustom=new ArrayList <>();
        listCustom.add("ali");
        listCustom.add("majid");
        listCustom.add("Mohammad");
        listCustom.add("nima");
        listCustom.add("Reza");
        listCustom.add("mosa");
        adapterCustom=new SpinnerAdaptor(this,R.layout.spinner_customer,R.id.tv_cantact_customerspinner,listCustom);
        spinner_custom.setAdapter(adapterCustom);



    }

}
