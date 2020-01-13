package com.example.gitreposearch

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFectory {

    private var retrofit: Retrofit? = null
    private val BASE_URL = "https://api.github.com/"

    val retrofitObject: Retrofit
        @Synchronized get() {
            if (retrofit == null) {

                val client = OkHttpClient.Builder().build()
                val gsonBuilder = GsonBuilder()
                gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                val gson = gsonBuilder.create()

                retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(BASE_URL)
                    .client(client)
                    .build()
            }

            return retrofit!!
        }


}
