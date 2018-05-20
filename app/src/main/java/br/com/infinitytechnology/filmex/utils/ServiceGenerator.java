package br.com.infinitytechnology.filmex.utils;

import android.content.Context;
import android.text.TextUtils;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    public static <S> S createService(Context context, Class<S> serviceClass) {
        return createService(context, serviceClass, null, null);
    }

    public static <S> S createService(Context context, Class<S> serviceClass, String username,
                                      String password) {
        if (!TextUtils.isEmpty(username)
                && !TextUtils.isEmpty(password)) {
            String authToken = Credentials.basic(username, password);
            return createService(context, serviceClass, authToken);
        }

        return createService(context, serviceClass, null);
    }

    public static <S> S createService(Context context, Class<S> serviceClass,
                                      final String authToken) {
        String apiBaseUrl = PropertyUtil.property(context, "api.base.url");

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(apiBaseUrl)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor(authToken);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);

                builder.client(httpClient.build());
                retrofit = builder.build();
            }
        }

        return retrofit.create(serviceClass);
    }
}