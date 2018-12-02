package com.broa.broasjapplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private  static  Retrofit retrofit;

    public static Retrofit getInsrtanc(){
        if(retrofit==null){
            retrofit =new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BooksServise.baseUrl)
                    .build();

        }
        return retrofit;
    }

}
