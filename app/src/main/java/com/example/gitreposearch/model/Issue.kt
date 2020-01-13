package com.example.gitreposearch.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Issue(

    @SerializedName("url")
    @Expose
    var url: String,
    @SerializedName("repository_url")
    @Expose
    var repositoryUrl: String,
    @SerializedName("labels_url")
    @Expose
    var labelsUrl: String,
    @SerializedName("comments_url")
    @Expose
    var commentsUrl: String,
    @SerializedName("events_url")
    @Expose
    var eventsUrl: String,
    @SerializedName("html_url")
    @Expose
    var htmlUrl: String,
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("node_id")
    @Expose
    var nodeId: String,
    @SerializedName("number")
    @Expose
    var number: Int,
    @SerializedName("title")
    @Expose
    var title: String,
    @SerializedName("user")
    @Expose
    var user: User,
    @SerializedName("labels")
    @Expose
    var labels: List<Any>,
    @SerializedName("state")
    @Expose
    var state: String,
    @SerializedName("locked")
    @Expose
    var locked: Boolean,
    @SerializedName("assignee")
    @Expose
    var assignee: Any,
    @SerializedName("assignees")
    @Expose
    var assignees: List<Any>,
    @SerializedName("milestone")
    @Expose
    var milestone: Any,
    @SerializedName("comments")
    @Expose
    var comments: Int,
    @SerializedName("created_at")
    @Expose
    var createdAt: String,
    @SerializedName("updated_at")
    @Expose
    var updatedAt: String,
    @SerializedName("closed_at")
    @Expose
    var closedAt: Any,
    @SerializedName("author_association")
    @Expose
    var authorAssociation: String,
    @SerializedName("pull_request")
    @Expose
    var pullRequest: PullRequest,
    @SerializedName("body")
    @Expose
    var body: String

)
