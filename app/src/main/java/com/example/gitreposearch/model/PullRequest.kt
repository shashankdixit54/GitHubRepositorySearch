package com.example.gitreposearch.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PullRequest(

    @SerializedName("url")
    @Expose
    var url: String,
    @SerializedName("html_url")
    @Expose
    var htmlUrl: String,
    @SerializedName("diff_url")
    @Expose
    var diffUrl: String,
    @SerializedName("patch_url")
    @Expose
    var patchUrl: String

)
