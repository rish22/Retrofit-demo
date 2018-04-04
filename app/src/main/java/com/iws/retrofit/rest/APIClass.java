package com.iws.retrofit.rest;

import com.iws.retrofit.model.CountryList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by iws-037 on 2/4/18.
 */

public class APIClass {
    private static final String baseUrl = "http://cmsbox.in/app/empower/api/webservices/";

    public static RetrofitInterface retrofitInterface = null;

    public static RetrofitInterface getRetrofitInterface() {
        if (retrofitInterface == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            retrofitInterface = retrofit.create(RetrofitInterface.class);
        }
        return retrofitInterface;
    }

    public interface RetrofitInterface {

        @GET("countrylist.json")
        Call<CountryList> getCountrylist();
    }
}
