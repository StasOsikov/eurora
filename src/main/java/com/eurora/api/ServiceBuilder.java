package com.eurora.api;

import com.eurora.api.exceptions.ServicePathException;
import com.eurora.api.interceptors.BasicHeadersInterceptor;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ServiceBuilder {

    private final List<Interceptor> interceptors = new ArrayList<>();

    @SneakyThrows
    public <T> T build(Class<T> clazz) {
        Service service = clazz.isAnnotationPresent(Service.class) ? clazz.getAnnotation(Service.class) : null;

        if (null == service && !isServicePathEndsWithSlash(service.type().toString())) {
            throw new ServicePathException();
        }

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

        for (Interceptor interceptor : this.interceptors) {
            okHttpClientBuilder.addInterceptor(interceptor);
        }

        return getRetrofit(service, okHttpClientBuilder).create(clazz);
    }

    private Retrofit getRetrofit(Service service, OkHttpClient.Builder okHttpClientBuilder) {
        return new Retrofit.Builder()
                .baseUrl(service.type().getBaseUrl())
                .client(okHttpClientBuilder
                        .addInterceptor(new HttpLoggingInterceptor(log::info)
                                .setLevel(Level.BODY)
                                .setLevel(Level.HEADERS)
                                .setLevel(Level.BASIC))
                        .addInterceptor(new BasicHeadersInterceptor())
                        .followRedirects(true)
                        .build())
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .setLenient()
                                .create()))
                .build();
    }

    private static boolean isServicePathEndsWithSlash(String servicePath) {
        return servicePath.endsWith("/");
    }
}
