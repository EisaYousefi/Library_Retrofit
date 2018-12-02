package com.broa.broasjapplication;

import android.app.AutomaticZenRule;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LibreryHomeActivity extends AppCompatActivity {
    TextInputEditText inputid, inputNameBook, inputauthor, inputPublishers, inputPrice;
    Button btn_insertBook, btn_search;

    TextInputEditText input_name_azo, input_id_azo, input_tel_azo;
    Button btn_azo;

    TextInputEditText input_idazo_tab5, input_nameazo_tab5, inputelazo_tab5;

    Button btn_search_tab5, tab5, btn_edit_tab5;

    TextInputEditText Input_idbook_amanat, input_namebook_amnat, input_writernamebook_amanat, input_numberrbookk_amanat, input_idazo_amanat, input_nameazo_amanat, input_telazo_amanat;
    Button btn_search_save_amanat, btn_bargasht_amanat;

    DatabaseHlper helper = new DatabaseHlper(this);
    Books books = new Books();
    Azo azo = new Azo();
    Amanat amanat = new Amanat();
    List <Books> listbook = new ArrayList <>();
    List <Amanat> amanatList = new ArrayList <>();
    List <Azo> azoList = new ArrayList <>();
    ListView listView_books;
    ArrayAdapter adapter;
    //----
    TextInputEditText inputid_searchitem, inputprice_search, inputwritername_searchitem, inputpulisor_searchitem, inputnamebook_searchitem;
    AutoCompleteTextView completeTextView;
    Button btn__search_searchItem, btn_updatr_searchItem;

    EditText input_idbook_bargashtAmanat;
    Button btn_bargashtamanat;

    //ذخیره اطلاعات ب روی سرور
    Retrofit retrofit;
    BooksServise booksServise;
    //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librery_home);

        TabHost host = findViewById(R.id.tabhost_librery);


        createTabHost(host);







        //ذخیره اطلاعات ب روی سرور

        retrofit = RetrofitClient.getInsrtanc();
        booksServise = retrofit.create(BooksServise.class);
        inseertBooktoTable();
        insertAzo();

        searchallbook();
        searchallAzo();
        allbookAmanat();
        searchAmanatBargasht();

        searchIItemNameBook();
        searchazo();

        update();
        updateAzo();

        deleteIitem();
        bargashtbook();
    }

    public void searchazo() {


        input_idazo_tab5 = findViewById(R.id.input_number_azo);
        input_nameazo_tab5 = findViewById(R.id.input_name_famil_azo);
        inputelazo_tab5 = findViewById(R.id.tel_azo);

        btn_edit_tab5 = findViewById(R.id.btn_edit_az0_tab5);
        btn_search_tab5 = findViewById(R.id.btn_serach_azo_tab5);
        btn_search_tab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Call<Azo> call = booksServise.getIdAzo(input_id_azo.getText().toString().trim());
              call.enqueue(new Callback <Azo>() {
                  @Override
                  public void onResponse(Call <Azo> call, Response <Azo> response) {
                      if (response.isSuccessful()){
                          azo = response.body();
                          input_nameazo_tab5.setText(azo.getNameFamil());
                          inputelazo_tab5.setText(azo.getTel());
                          toastCustomer("پیدا شد");
                      }
                  else {
                      toastCustomer("عضو با این کد موجود نیست");
                      }
                  }


                  @Override
                  public void onFailure(Call <Azo> call, Throwable t) {

                  }
              });

            }
        });
    }

    private void allbookAmanat() {
        final Button btn_searchallamanat = findViewById(R.id.btn_searchtab4_allamanat);
        final ListView listView_searchall = findViewById(R.id.listview_searchall);
        final TextView tv = findViewById(R.id.tv_tab444444);

        btn_searchallamanat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("لیست  " + btn_searchallamanat.getText());

                if (amanatList == null) amanatList = new ArrayList <>();
                Call<List<Amanat>> call = booksServise.getAmanat();
                call.enqueue(new Callback <List <Amanat>>() {
                    @Override
                    public void onResponse(Call <List <Amanat>> call, Response <List <Amanat>> response) {
                        if (response.isSuccessful()){
                            amanatList = response.body();
                            adapter = new ArrayAdapter(getApplication(), android.R.layout.simple_expandable_list_item_1, amanatList);
                            listView_searchall.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(LibreryHomeActivity.this, "تعداد کتاب های به امانت رفته : " + adapter.getCount() + "   جلد", Toast.LENGTH_SHORT).show();
                        }else {
                            toastCustomer("مشکل در نمایش");
                        }

                    }

                    @Override
                    public void onFailure(Call <List <Amanat>> call, Throwable t) {
                        toastCustomer("اتصال به سرور را بررسی کنید");

                    }
                });


            }
            //}
        });
    }

    private void bargashtbook() {
        btn_bargashtamanat = findViewById(R.id.btn_bargasht_amanat);
        btn_bargashtamanat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_numberrbookk_amanat.getText().toString().equals(""))
                    toastCustomer("لطفا کد کتاب را وارد کنید ");
                else{
                    alerdialogBargashtBook();

                  }



            }
        });

    }

    private void searchAmanatBargasht() {

        input_idazo_amanat = findViewById(R.id.input_number_azo_amanat);
        input_nameazo_amanat = findViewById(R.id.input_name_azo_amanat);
        input_telazo_amanat = findViewById(R.id.input_tel_azo_amanat);

        input_namebook_amnat = findViewById(R.id.input_bookName_amanat);
        input_writernamebook_amanat = findViewById(R.id.input_writerNAme_amanat);
        input_numberrbookk_amanat = findViewById(R.id.input_idbook_amanattt);

        btn_search_save_amanat = findViewById(R.id.btn_save_amanat);


        btn_search_save_amanat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Call<Azo> call = booksServise.getIdAzo(input_idazo_amanat.getText().toString().trim());
                call.enqueue(new Callback <Azo>() {
                    @Override
                    public void onResponse(Call <Azo> call, Response <Azo> response) {
                        if (response.isSuccessful()){

                            input_nameazo_amanat.setText(response.body().getNameFamil());
                            input_telazo_amanat.setText(response.body().getTel());
                            toastCustomer("عضو پیدا شد");
                        } else {
                            toastCustomer("عضو با این کد موجود نیست");
                        }
                    }

                    @Override
                    public void onFailure(Call <Azo> call, Throwable t) {

                    }
                });



                //
                Call<Books> callbook = booksServise.getIdBook(input_numberrbookk_amanat.getText().toString().trim());
                callbook.enqueue(new Callback <Books>() {
                    @Override
                    public void onResponse(Call <Books> call, Response <Books> response) {
                        if (response.isSuccessful()){
                            input_namebook_amnat.setText(response.body().getBookName());
                            input_writernamebook_amanat.setText(response.body().getAuthorName());
                            toastCustomer("کتاب پیدا شد");
                            //---------insert data to table amanat---------
                            if (input_numberrbookk_amanat.getText().toString().equals("")
                                    || input_namebook_amnat.getText().toString().equals("")
                                    || input_writernamebook_amanat.getText().toString().equals("")
                                    || input_nameazo_amanat.getText().toString().equals("")
                                    || input_telazo_amanat.getText().toString().equals("")) {
                                toastCustomer("لطفا فیلد های خالی را مقدار دهی کنید ");
                            }

                            else {
                                amanat.setId_book_amanat(input_numberrbookk_amanat.getText().toString().trim());
                                amanat.setName_book_amanat(input_namebook_amnat.getText().toString().trim());
                                amanat.setWritername_book_amanat(input_writernamebook_amanat.getText().toString().trim());
                                amanat.setId_name_amanat(input_idazo_amanat.getText().toString().trim());
                                amanat.setName_amanat(input_nameazo_amanat.getText().toString().trim());
                                amanat.setTel_amanat(input_telazo_amanat.getText().toString().trim());
                                alertdialogAmanat();
                            }

                        } else {
                            toastCustomer("کتاب با این کد موجود نیست");
                        }
                    }


                    @Override
                    public void onFailure(Call <Books> call, Throwable t) {

                    }
                });
                //


            }
        });


    }


    private void searchallbook() {

        final Button btn_searchallbook = findViewById(R.id.btn_searchtab4);
        final ListView listView_searchall = findViewById(R.id.listview_searchall);
        final TextView tv = findViewById(R.id.tv_tab444444);
        btn_searchallbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if(spinner.getAdapter().toString()=="همه کتاب ها"){
                tv.setText("لیست  " + btn_searchallbook.getText());
                if (listbook == null) listbook = new ArrayList <>();
                Call<List<Books>> call = booksServise.getBooks();
                call.enqueue(new Callback<List <Books>>() {
                    @Override
                    public void onResponse(Call <List <Books>> call, Response <List <Books>> response) {

                        List <Books> modelsList = response.body();
                        adapter = new ArrayAdapter(getApplication(), android.R.layout.simple_expandable_list_item_1, modelsList);
                        listView_searchall.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(LibreryHomeActivity.this, "تعداد کتاب موجود : " + adapter.getCount() + "   جلد", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call <List <Books>> call, Throwable t) {
                        Toast.makeText(LibreryHomeActivity.this, t.toString(), Toast.LENGTH_SHORT).show();


                    }

                });


            }
            //}
        });


    }

    private void searchallAzo() {
        final Button btn_Searchallazo = findViewById(R.id.btn_searchtab4_allazo);
        final ListView listView_searchall = findViewById(R.id.listview_searchall);
        final TextView tv = findViewById(R.id.tv_tab444444);
        btn_Searchallazo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if(spinner.getAdapter().toString()=="همه کتاب ها"){
                tv.setText("لیست  " + btn_Searchallazo.getText());
                if (azoList == null) azoList = new ArrayList <>();
                Call<List<Azo>> call = booksServise.getAzo();
                call.enqueue(new Callback <List <Azo>>() {
                    @Override
                    public void onResponse(Call <List <Azo>> call, Response <List <Azo>> response) {
                        if (response.isSuccessful()){
                            azoList = response.body();
                            adapter = new ArrayAdapter(getApplication(), android.R.layout.simple_expandable_list_item_1, azoList);
                            listView_searchall.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(LibreryHomeActivity.this, "تعداد اعضای کتابخانه : " + adapter.getCount() + "   نفر هستند", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call <List <Azo>> call, Throwable t) {

                    }
                });
            }
            //}
        });
    }


    private void deleteIitem() {
        Button btn_delete_searchitem = findViewById(R.id.btn_delete_searchItem);
        btn_delete_searchitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!inputnamebook_searchitem.getText().toString().isEmpty())
                    alerdialogDelete();
                else toastCustomer("فیلد خالی وجود دارد");


            }
        });

    }

    private void update() {
        inputid_searchitem = findViewById(R.id.edittext_codeBook_searchItem);
        inputnamebook_searchitem = findViewById(R.id.edittext_namebook_searchItem);
        inputprice_search = findViewById(R.id.edittext_price_searchItem);
        inputpulisor_searchitem = findViewById(R.id.edittext_publiser_searchItem);
        inputwritername_searchitem = findViewById(R.id.edittext_writname_nsearchItem);
        completeTextView = findViewById(R.id.autocomple_edittext);


        btn_updatr_searchItem = findViewById(R.id.btn_Update_searchItem);

        btn_updatr_searchItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                books.setPublishers(inputpulisor_searchitem.getText().toString().trim());
                books.setPrice(Integer.valueOf(inputprice_search.getText().toString().trim()));
                books.setBookName(inputnamebook_searchitem.getText().toString().trim());
                books.setAuthorName(inputwritername_searchitem.getText().toString().trim());
              //  books.setId(inputid_searchitem.getText().toString().trim());

                Call<Books> call = booksServise.updateBooks(inputid_searchitem.getText().toString().trim(),books );
                call.enqueue(new Callback <Books>() {
                    @Override
                    public void onResponse(Call <Books> call, Response <Books> response) {
                        if (response.isSuccessful())
                            toastCustomer("کتاب ویرایش شد");
                        else
                            toastCustomer("ویرایش نشد");

                    }

                    @Override
                    public void onFailure(Call <Books> call, Throwable t) {

                    }
                });


            }
        });


    }

    //-----update azo
    public void updateAzo() {
        input_idazo_tab5 = findViewById(R.id.input_number_azo);
        inputelazo_tab5 = findViewById(R.id.tel_azo);
        input_nameazo_tab5 = findViewById(R.id.input_name_famil_azo);
        btn_edit_tab5 = findViewById(R.id.btn_edit_az0_tab5);
        btn_edit_tab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                azo.setNameFamil(input_nameazo_tab5.getText().toString().trim());
                azo.setTel(inputelazo_tab5.getText().toString().trim());
                azo.setId_azo(input_idazo_tab5.getText().toString().trim());
                Call<Azo> call = booksServise.updateAzo(input_id_azo.getText().toString().trim(),azo);
                call.enqueue(new Callback <Azo>() {
                    @Override
                    public void onResponse(Call <Azo> call, Response <Azo> response) {
                        if (response.isSuccessful())
                            toastCustomer("ویرایش شد");
                        else
                            toastCustomer("مشکل در ویرایش");

                    }

                    @Override
                    public void onFailure(Call <Azo> call, Throwable t) {

                    }
                });

            }
        });
    }

    private void searchIItemNameBook() {
        inputid_searchitem = findViewById(R.id.edittext_codeBook_searchItem);
        inputnamebook_searchitem = findViewById(R.id.edittext_namebook_searchItem);
        inputprice_search = findViewById(R.id.edittext_price_searchItem);
        inputpulisor_searchitem = findViewById(R.id.edittext_publiser_searchItem);
        inputwritername_searchitem = findViewById(R.id.edittext_writname_nsearchItem);
        completeTextView = findViewById(R.id.autocomple_edittext);

        btn__search_searchItem = findViewById(R.id.btn_search_searchItem);
        btn_updatr_searchItem = findViewById(R.id.btn_Update_searchItem);
        List <String> list = new ArrayList <>();


        list = helper.getAllNameBook();

        adapter = new ArrayAdapter <>(this, android.R.layout.simple_list_item_1, list);
        completeTextView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        completeTextView.setThreshold(1);

        btn__search_searchItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Books> call =booksServise.getIdBook(inputid_searchitem.getText().toString().trim());
                call.enqueue(new Callback <Books>() {
                    @Override
                    public void onResponse(Call <Books> call, Response <Books> response) {
                        if (response.isSuccessful()) {
                             books=response.body();

                                inputnamebook_searchitem.setText(books.getBookName());
                                inputwritername_searchitem.setText(books.getAuthorName());
                                inputprice_search.setText(String.valueOf(books.getPrice()));
                                inputpulisor_searchitem.setText(books.getPublishers());
                                toastCustomer("پیدا شد");

                        } else {
                            toastCustomer("کتاب با این کد موجود نیست");
                        }

                    }

                    @Override
                    public void onFailure(Call <Books> call, Throwable t) {
                        toastCustomer("خطا در اتصال");

                    }
                });

            }

        });


    }

    private void alertdialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // builder.setTitle("محض اطلاع...");
        builder.setMessage("آیا اطلاعات ذخیره شوند؟");
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.alertdialod, null);
        builder.setView(view);

        builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Call<Books> call =booksServise.createBook(books);
                call.enqueue(new Callback <Books>() {
                    @Override
                    public void onResponse(Call <Books> call, Response<Books> response) {
                        if(response.isSuccessful()){
                            toastCustomer("ذخیره شد");
                            inputPrice.setText("");
                            inputPublishers.setText("");
                            inputNameBook.setText("");
                            inputauthor.setText("");
                            inputid.setText("");
                            inputid.requestFocus();

                        }
                        else  toastCustomer(" ارتباط با سرور را برسی کنید");
                    }

                    @Override
                    public void onFailure(Call <Books> call, Throwable t) {
                        toastCustomer(" ارتباط با سرور را برسی کنید");
                    }
                });



            }
        });
        builder.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Toast.makeText(LibreryHomeActivity.this, "ذخیره نشد", Toast.LENGTH_SHORT).show();
                toastCustomer(" --اطلاعات ذخیره نشد--");
            }
        });
        builder.show();

    }

    //--dialog for insert to table Amanat
    private void alertdialogAmanat() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // builder.setTitle("محض اطلاع...");
        builder.setMessage("آیا این کتاب به امانت برود؟");
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.alertdialod, null);
        builder.setView(view);

        builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Call<Amanat> amanatCall = booksServise.createAmanat(amanat);
                amanatCall.enqueue(new Callback <Amanat>() {
                    @Override
                    public void onResponse(Call <Amanat> call, Response <Amanat> response) {
                        if(response.isSuccessful()){

                            toastCustomer("به امانت رفت");
                            input_numberrbookk_amanat.setText("");
                            input_namebook_amnat.setText("");
                            input_writernamebook_amanat.setText("");
                            input_idazo_amanat.setText("");
                            input_nameazo_amanat.setText("");
                            input_telazo_amanat.setText("");
                            inputid.requestFocus();
                        }
                    }

                    @Override
                    public void onFailure(Call <Amanat> call, Throwable t) {

                    }
                });
            }
        });
        builder.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Toast.makeText(LibreryHomeActivity.this, "ذخیره نشد", Toast.LENGTH_SHORT).show();
                toastCustomer(" --به امانت نرفت--");
            }
        });
        builder.show();

    }

    //---alertdialog   for delete---
    public void alerdialogDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("آیا اطلاعات حذف شوند؟");
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.alertdelete, null);
        builder.setView(view);

        builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Call<ResponseBody> call = booksServise.deleteBooks(inputid_searchitem.getText().toString().trim());
                call.enqueue(new Callback <ResponseBody>() {
                    @Override
                    public void onResponse(Call <ResponseBody> call, Response <ResponseBody> response) {
                        if(response.isSuccessful()){
                            toastCustomer("با موغقیت حذف شد");
                            inputprice_search.setText("");
                            inputpulisor_searchitem.setText("");
                            inputnamebook_searchitem.setText("");
                            inputwritername_searchitem.setText("");
                            inputid_searchitem.setText("");
                            inputid.requestFocus();
                        }

                        else{
                            toastCustomer("کتاب با این کد موجود نیست");
                        }

                    }

                    @Override
                    public void onFailure(Call <ResponseBody> call, Throwable t) {

                    }
                });



            }
        });
        builder.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Toast.makeText(LibreryHomeActivity.this, "ذخیره نشد", Toast.LENGTH_SHORT).show();
                toastCustomer(" --اطلاعات حذف نشد--");
            }
        });
        builder.show();
    }

    //---------------dialog for  bargasht books------------------
    public void alerdialogBargashtBook() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // builder.setTitle("محض اطلاع...");
        builder.setMessage("آیا کتاب برگشتی حذف شود؟");
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.alertdelete, null);
        builder.setView(view);

        builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Call<ResponseBody>  bodyCall = booksServise.deleteAmanat(input_numberrbookk_amanat.getText().toString().trim());
                bodyCall.enqueue(new Callback <ResponseBody>() {
                    @Override
                    public void onResponse(Call <ResponseBody> call, Response <ResponseBody> response) {
                        if (response.isSuccessful()){
                            toastCustomer("با موغقیت عملیات برگشت امانت انجام گرفت");
                            inputprice_search.setText("");
                            inputpulisor_searchitem.setText("");
                            inputnamebook_searchitem.setText("");
                            inputwritername_searchitem.setText("");
                            inputid_searchitem.setText("");
                            inputid.requestFocus();
                        }
                        else toastCustomer("کتاب با این کد موجود نیست");

                    }

                    @Override
                    public void onFailure(Call <ResponseBody> call, Throwable t) {

                    }
                });



            }
        });
        builder.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Toast.makeText(LibreryHomeActivity.this, "ذخیره نشد", Toast.LENGTH_SHORT).show();
                toastCustomer(" --کتاب برگشت داده نشد--");
            }
        });
        builder.show();
    }

    private void toastCustomer(String tv) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.toast,
                (ViewGroup) findViewById(R.id.viewGrop));
        TextView textView = view.findViewById(R.id.tv_Toast);
        textView.setText(tv);
        Toast toast = new Toast(LibreryHomeActivity.this);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();

        //---table azo


    }

    private void inseertBooktoTable() {
        inputid = findViewById(R.id.input_codeBook_newbook);
        inputNameBook = findViewById(R.id.input_nameBook_newbook);
        inputauthor = findViewById(R.id.input_writname_newbook);
        inputPrice = findViewById(R.id.input_price_newbook);
        inputPublishers = findViewById(R.id.input_publiser_newwbook);
        btn_insertBook = findViewById(R.id.btn_save_newbook);
        //----insert data to database--
        btn_insertBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputid.getText().toString().equals("")
                        || inputauthor.getText().toString().equals("")
                        || inputNameBook.getText().toString().equals("")
                        || inputPrice.getText().toString().equals("")
                        || inputPublishers.getText().toString().equals("")) {
                    //Toast.makeText(LibreryHomeActivity.this, "فیلد نباید خالی باشد ", Toast.LENGTH_SHORT).show();
                    toastCustomer("لطفا فیلد های خالی را مقدار دهی کنید ");
                } else {
                    books.setPublishers(inputPublishers.getText().toString().trim());
                    books.setPrice(Integer.valueOf(inputPrice.getText().toString().trim()));
                    books.setBookName(inputNameBook.getText().toString().trim());
                    books.setAuthorName(inputauthor.getText().toString().trim());
                    books.setId(inputid.getText().toString().trim());

                    alertdialog();

                   /* helper.insertBook(books);
                    toastCustomer("ذخیره شد");
                    inputPrice.setText("");
                    inputPublishers.setText("");
                    inputNameBook.setText("");
                    inputauthor.setText("");
                    inputid.setText("");
                    inputid.requestFocus();*/

                }
            }
        });
    }

    //------------insrt azo----------
    public void insertAzo() {
        input_id_azo = findViewById(R.id.input_number_azo);
        input_name_azo = findViewById(R.id.input_name_famil_azo);
        input_tel_azo = findViewById(R.id.tel_azo);
        btn_azo = findViewById(R.id.btn_save_azo);
        //----insert data to database--
        btn_azo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_id_azo.getText().toString().equals("")
                        || input_name_azo.getText().toString().equals("")
                        || input_tel_azo.getText().toString().equals("")) {
                    //Toast.makeText(LibreryHomeActivity.this, "فیلد نباید خالی باشد ", Toast.LENGTH_SHORT).show();
                    toastCustomer("لطفا فیلد های خالی را مقدار دهی کنید ");
                } else {
                    azo.setId_azo(input_id_azo.getText().toString().trim());
                    azo.setNameFamil(input_name_azo.getText().toString().trim());
                    azo.setTel(input_tel_azo.getText().toString().trim());

                    //--search for Id
                    Cursor cursor1 = helper.getItemBookIDAzo(azo.getId_azo().toString());
                    if (cursor1.moveToFirst()) {
                        toastCustomer("کد عضو تکراری است ");
                        //Toast.makeText(getApplication(), "کد کتاب تکراری است", Toast.LENGTH_SHORT).show();
                        input_id_azo.requestFocus();
                        input_id_azo.setText("");
                    }//--=========end search

                    else {
                        //  alertdialog();

                       Call<Azo> call = booksServise.createAzo(azo);
                       call.enqueue(new Callback <Azo>() {
                           @Override
                           public void onResponse(Call <Azo> call, Response <Azo> response) {
                               if(response.isSuccessful()){
                                   toastCustomer("ذخیره شد");
                                   input_id_azo.setText("");
                                   input_tel_azo.setText("");
                                   input_name_azo.setText("");
                                   inputid.requestFocus();

                               }

                           }

                           @Override
                           public void onFailure(Call <Azo> call, Throwable t) {
                               toastCustomer("ارتباط با سرور را برسی کنید");

                           }
                       });

                    }
                }
            }
        });
    }

    private void createTabHost(TabHost host) {
        host.setup();

        TabHost.TabSpec tab1 = host.newTabSpec("publice");
        TabHost.TabSpec tab2 = host.newTabSpec("private");
        TabHost.TabSpec tab3 = host.newTabSpec("protected");
        TabHost.TabSpec tab4 = host.newTabSpec("other");
        TabHost.TabSpec tab5 = host.newTabSpec("other1");


        tab1.setContent(R.id.tab1);
        tab2.setContent(R.id.tab2);
        tab3.setContent(R.id.tab3);
        tab4.setContent(R.id.tab4);
        tab5.setContent(R.id.tab5);


        tab1.setIndicator("کتاب جدید");

        tab2.setIndicator("جستجو");
        tab3.setIndicator("امانت وبرگشت");
        tab4.setIndicator("گزارش");
        tab5.setIndicator("عضو");


        host.addTab(tab1);
        host.addTab(tab2);
        host.addTab(tab3);
        host.addTab(tab4);
        host.addTab(tab5);

    }
}
