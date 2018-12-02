package com.broa.broasjapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {
    EditText text;
    Button btn_add;
    ListView listView;
    ArrayAdapter<String> adapter;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_activity);



        text    =findViewById(R.id.edit_listadd_listActivity);
        btn_add =findViewById(R.id.btn_aalist_listActivity);
        listView=findViewById(R.id.listview);

        list=new ArrayList<>();
        list.add("Eisa");
        list.add("Broa");
        list.add("Fatemeh");
        adapter=new ArrayAdapter <String>(this,android.R.layout.simple_expandable_list_item_1,list);
        listView.setAdapter(adapter);

        //---add item to list
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text.getText().toString().isEmpty())
                    Toast.makeText(ListViewActivity.this, "فیلد خالی است", Toast.LENGTH_SHORT).show();
                else {
                    list.add(text.getText().toString().trim());
                    text.setText("");
                    adapter.notifyDataSetChanged();
                }
            }
        });


        //--delete item list--
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                list.remove(position);
                Toast.makeText(ListViewActivity.this, "  آیتم با اندس"+position+"حذف شد  ", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView <?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),list.get(position), Toast.LENGTH_SHORT).show();
                text.setText(list.get(position));
                return false;
            }
        });

    }



}
