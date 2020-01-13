package com.example.gitreposearch.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gitreposearch.model.Contributor
import com.example.gitreposearch.model.Issue
import com.example.gitreposearch.model.RepoModel
import com.example.gitreposearch.netwok.DataRepository
import retrofit2.Retrofit


class RepoViewModel : ViewModel() {

    private var mutableLiveDataRepository: MutableLiveData<RepoModel>? = null
    private var mutableLiveDataIssue: MutableLiveData<List<Issue>>? = null
    private var mutableLiveDataContribution: MutableLiveData<List<Contributor>>? = null
    private var dataRepository: DataRepository? = null

    fun init(retrofit: Retrofit) {
        if (dataRepository != null) {
            return
        }
        dataRepository = DataRepository.getInstance(retrofit)
    }

    val repositoryData: LiveData<RepoModel>? get() = mutableLiveDataRepository

    val issueData: LiveData<List<Issue>>? get() = mutableLiveDataIssue

    val contributionData: LiveData<List<Contributor>>? get() = mutableLiveDataContribution


    fun getLanguageData(language: String) {
        mutableLiveDataRepository = dataRepository!!.getLanguageData("language:$language")
        //mutableLiveDataRepository?.postValue(dataRepository!!.getLanguageData("language:$language").value)
    }

    fun getIssueData(user: String, repoName: String) {
        mutableLiveDataIssue = dataRepository!!.getTopIssue(user, repoName)
    }

    fun getContributorData(user: String, repoName: String) {
        mutableLiveDataContribution = dataRepository!!.getTopContributor(user, repoName)
    }

}
