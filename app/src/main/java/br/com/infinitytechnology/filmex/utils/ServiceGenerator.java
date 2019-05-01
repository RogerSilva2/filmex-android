package br.com.infinitytechnology.filmex.utils;

import android.content.Context;
import android.text.TextUtils;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    public static <S> S createService(Context context, Class<S> serviceClass) {
        String apiBaseUrl = PropertyUtil.property(context, "api.base.url");

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(apiBaseUrl)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        return retrofit.create(serviceClass);
    }
}