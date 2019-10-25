package dev.hyuwah.dicoding.muvilog.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.hyuwah.dicoding.muvilog.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object TheMovieDbServicesFactory {

    private var INSTANCE: ITheMovieDbServices? = null

    const val BASE_URL = "https://api.themoviedb.org/3/"

    fun create(): ITheMovieDbServices {
        return if (INSTANCE == null) {
            val client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .addInterceptor {
                    val original = it.request()
                    val originalHttpUrl = original.url
                    val url = originalHttpUrl.newBuilder()
                        .addQueryParameter("api_key", BuildConfig.MOVIE_DB_KEY)
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
            retrofit.create(ITheMovieDbServices::class.java)
        } else INSTANCE!!
    }
}