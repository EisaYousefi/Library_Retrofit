package com.broa.broasjapplication;

import android.text.GetChars;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BooksServise {
    public static final String    baseUrl ="http://10.0.2.2:8000/";


    // Books
    @GET("library/books")
    Call<List<Books>> getBooks();

    @GET("/library/books/{id}")
    Call<Books> getIdBook(@Path("id") String id);

    @POST("library/books/")
    Call<Books> createBook(@Body Books books);

    @DELETE("/library/books/{id}/")
    Call<ResponseBody> deleteBooks(@Path("id") String id);

    @PUT("/library/books/{id}/")
    Call<Books> updateBooks(@Path("id") String id, @Body Books books);



    //Azo
    @GET("library/azo")
    Call<List<Azo>> getAzo();

    @POST("library/azo/")
    Call<Azo> createAzo(@Body Azo azo);

    @GET("library/azo/{id}")
    Call<Azo> getIdAzo(@Path("id") String id);

    @PUT("library/azo/{id}/")
    Call<Azo> updateAzo(@Path("id") String id ,@Body Azo azo);


    //Amanat
    @GET("library/amanat")
    Call<List<Amanat>> getAmanat();

    @POST("library/amanat/")
    Call<Amanat> createAmanat(@Body Amanat amanat);

    @GET("library/amant/{id}")
    Call<Amanat> getIdAmanat(@Path("id") String id);

    @PUT("library/amanat/{id}")
    Call<Amanat> updateIDAmanat(@Path("id") String id ,@Body  Amanat amanat);

    @DELETE("/library/amanat/{id}/")
    Call<ResponseBody> deleteAmanat(@Path("id") String id);
}
