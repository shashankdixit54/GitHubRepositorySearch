package com.example.gitreposearch.netwok


import com.example.gitreposearch.model.Contributor
import com.example.gitreposearch.model.Issue
import com.example.gitreposearch.model.RepoModel

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {


    @Headers("Content-Type:application/json")
    @GET("search/repositories")
    fun getLanguageData(@Query("q") countryName: String): Call<RepoModel>

    @Headers("Content-Type:application/json")
    @GET("repos/{user}/{reponame}/issues")
    fun getTopIssue(@Path("user") user: String, @Path("reponame") projectName: String): Call<List<Issue>>

    @Headers("Content-Type:application/json")
    @GET("repos/{user}/{reponame}/contributors")
    fun getTopContributors(@Path("user") user: String, @Path("reponame") projectName: String): Call<List<Contributor>>

}
