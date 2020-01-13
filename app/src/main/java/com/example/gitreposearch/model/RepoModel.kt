package com.example.gitreposearch.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.io.Serializable

data class RepoModel(

    @SerializedName("total_count")
    @Expose
    var totalCount: Int,
    @SerializedName("incomplete_results")
    @Expose
    var incompleteResults: Boolean,
    @SerializedName("items")
    @Expose
    var items: List<Item>

) : Serializable
