package com.example.wtank3;

import com.example.wtank3.model.apiSet;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apicontroler {
    static final String url = "https://watertankproject.000webhostapp.com/";
    private static apicontroler clientobject;
    private static Retrofit retrofit;

    apicontroler(){
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static synchronized apicontroler getInstance(){
        if(clientobject==null)
            clientobject = new apicontroler();
        return clientobject;
    }

    public apiSet getapi(){
        return retrofit.create(apiSet.class);
    }
}
