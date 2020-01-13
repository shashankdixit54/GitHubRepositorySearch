package com.example.gitreposearch.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.gitreposearch.R
import com.example.gitreposearch.RetrofitFectory
import com.example.gitreposearch.model.Item
import com.example.gitreposearch.viewmodel.RepoViewModel
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.item_layout_details.view.*

class DetailActivity : AppCompatActivity() {

    private lateinit var repositoryViewModel: RepoViewModel
    private var width: Int = 0
    private var height: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        var model: Item? = null
        if (intent.getSerializableExtra("item") != null) {
            model = intent.getSerializableExtra("item") as Item
        }

        width = resources.displayMetrics.widthPixels
        height = resources.displayMetrics.heightPixels

        initViewModel()
        initViews(model)
    }

    fun initViewModel() {
        repositoryViewModel = ViewModelProviders.of(this).get(RepoViewModel::class.java)
        repositoryViewModel.init(RetrofitFectory.retrofitObject)
    }

    fun initViews(model: Item?) {

        nameLayout.headingTv.text = getString(R.string.name)
        descriptionLayout.headingTv.text = getString(R.string.descripton)
        newIssueLayout.headingTv.text = getString(R.string.newest_issue)
        topContributorLayout.headingTv.text = getString(R.string.top_contributor)
        newIssueLayout.descriptionTv.text = ""
        topContributorLayout.descriptionTv.text = ""

        ivRepoIcon.layoutParams.width = width / 3
        ivRepoIcon.layoutParams.height = width / 3
        ivRepoIcon.setPadding(width / 40, width / 40, width / 40, 0)


        ivBackDetails.setOnClickListener { finish() }

        if (model != null) {
            nameLayout.descriptionTv.text = model.name
            nameTv.text = model.name
            descriptionLayout.descriptionTv.text = model.description
            getLatestIssueData(model.owner.login, model.name)
            getTopContributorData(model.owner.login, model.name)

            Glide.with(this)
                .load(model.owner.avatarUrl)
                .into(ivRepoIcon)


            detailsProgressBarLayout.visibility = View.VISIBLE
        }

    }

    fun getLatestIssueData(repoName: String, userName: String) {

        repositoryViewModel.getIssueData(repoName, userName)
        detailsProgressBarLayout.visibility = View.VISIBLE
        repositoryViewModel.issueData?.observe(this, Observer {

            if (it == null) {
                Toast.makeText(
                    this@DetailActivity,
                    getString(R.string.no_latest_issue),
                    Toast.LENGTH_SHORT
                ).show()
                detailsProgressBarLayout.visibility = View.GONE
            } else {

                for (i in 0 until it.size) {
                    val text = newIssueLayout.descriptionTv.text.toString()
                    if (text.isEmpty()) {
                        newIssueLayout.descriptionTv.text =
                            (i + 1).toString() + ". " + it.get(i).title
                    } else {
                        newIssueLayout.descriptionTv.text =
                            text + "\n\n" + (i + 1) + ". " + it.get(i).title
                    }
                    if (i == 2) {
                        break
                    }
                }
                detailsProgressBarLayout.visibility = View.GONE
            }

        })
    }

    fun getTopContributorData(repoName: String, userName: String) {

        repositoryViewModel.getContributorData(repoName, userName)
        repositoryViewModel.contributionData?.observe(this, Observer { contributionData ->
            if (contributionData == null) {
                Toast.makeText(
                    this@DetailActivity,
                    getString(R.string.no_contributor),
                    Toast.LENGTH_SHORT
                ).show()
                detailsProgressBarLayout.visibility = View.GONE
            } else {

                for (i in 0 until contributionData.size) {
                    val text = topContributorLayout.descriptionTv.text.toString()
                    if (text.isEmpty()) {
                        topContributorLayout.descriptionTv.text =
                            (i + 1).toString() + ". " + contributionData.get(i).login

                    } else {
                        topContributorLayout.descriptionTv.text =
                            text + "\n\n" + (i + 1) + ". " + contributionData.get(i).login
                    }

                    if (i == 2) {
                        break
                    }
                }
                detailsProgressBarLayout.visibility = View.GONE

            }
        })
    }

}