package dev.hyuwah.dicoding.muvilog.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object TheMovieDbServicesFactory {

    const val BASE_URL = "https://api.themoviedb.org/3/"

    fun create() : ITheMovieDbServices {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .addInterceptor {
                val original = it.request()
                val originalHttpUrl = original.url
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", "72d6d60dd6706f5c433560d444ae9ba3")
                    .build()
                val requestBuilder = original.newBuilder().url(url)
                val request = requestBuilder.build()
                it.proceed(request)
            }
            .build()

        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        return retrofit.create(ITheMovieDbServices::class.java)
    }
}