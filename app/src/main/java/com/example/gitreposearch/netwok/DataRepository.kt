package com.example.gitreposearch.netwok

import androidx.lifecycle.MutableLiveData

import com.example.gitreposearch.model.Contributor
import com.example.gitreposearch.model.Issue
import com.example.gitreposearch.model.RepoModel

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class DataRepository(retrofit: Retrofit) {

    private val api: Api


    init {
        api = retrofit.create(Api::class.java)
    }

    fun getLanguageData(language: String): MutableLiveData<RepoModel> {
        val RepoModel = MutableLiveData<RepoModel>()
        api.getLanguageData(language).enqueue(object : Callback<RepoModel> {
            override fun onResponse(
                call: Call<RepoModel>,
                response: Response<RepoModel>
            ) {
                if (response.isSuccessful) {
                    RepoModel.setValue(response.body())
                } else {
                    RepoModel.setValue(null)
                }
            }

            override fun onFailure(call: Call<RepoModel>, t: Throwable) {
                RepoModel.value = null
            }
        })
        return RepoModel
    }

    fun getTopIssue(userName: String, projectName: String): MutableLiveData<List<Issue>> {
        val RepoModel = MutableLiveData<List<Issue>>()
        api.getTopIssue(userName, projectName).enqueue(object : Callback<List<Issue>> {
            override fun onResponse(
                call: Call<List<Issue>>,
                response: Response<List<Issue>>
            ) {
                if (response.isSuccessful) {
                    RepoModel.setValue(response.body())
                } else {
                    RepoModel.setValue(null)
                }
            }

            override fun onFailure(call: Call<List<Issue>>, t: Throwable) {
                RepoModel.value = null
            }
        })
        return RepoModel
    }


    fun getTopContributor(
        userName: String,
        projectName: String
    ): MutableLiveData<List<Contributor>> {
        val RepoModel = MutableLiveData<List<Contributor>>()
        api.getTopContributors(userName, projectName).enqueue(object : Callback<List<Contributor>> {

            override fun onResponse(
                call: Call<List<Contributor>>,
                response: Response<List<Contributor>>
            ) {
                if (response.isSuccessful) {
                    RepoModel.setValue(response.body())
                } else {
                    RepoModel.setValue(null)
                }
            }

            override fun onFailure(call: Call<List<Contributor>>, t: Throwable) {
                RepoModel.value = null
            }
        })
        return RepoModel
    }

    companion object {

        private var dataRepository: DataRepository? = null


        fun getInstance(retrofit: Retrofit): DataRepository {
            if (dataRepository == null) {

                dataRepository = DataRepository(retrofit)
            }
            return dataRepository as DataRepository
        }
    }

}
