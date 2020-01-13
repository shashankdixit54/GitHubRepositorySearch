package com.example.gitreposearch.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitreposearch.R
import com.example.gitreposearch.RetrofitFectory
import com.example.gitreposearch.adapters.RecyclerViewAdapter
import com.example.gitreposearch.model.RepoModel
import com.example.gitreposearch.utility.AppUtility
import com.example.gitreposearch.utility.MyClickListener
import com.example.gitreposearch.viewmodel.RepoViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MyClickListener {

    private lateinit var context: Context
    private var width: Int = 0
    private var height: Int = 0
    private lateinit var repositoryViewModel: RepoViewModel
    private lateinit var repoData: RepoModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this
        width = resources.displayMetrics.widthPixels
        height = resources.displayMetrics.heightPixels

        initViews()
        initViewModel()
    }

    private fun initViewModel() {
        repositoryViewModel = ViewModelProviders.of(this).get(RepoViewModel::class.java)
        repositoryViewModel.init(RetrofitFectory.retrofitObject)
    }

    private fun initViews() {

        etLanguage.layoutParams.height = height / 14
        btnSearch.layoutParams.height = height / 18
        btnSearch.layoutParams.width = height / 18

        btnSearch.setOnClickListener {
            if (AppUtility.isInternetConnected(context)) {
                if (etLanguage.text.toString().isEmpty()) {
                    Toast.makeText(
                        context,
                        getString(R.string.enter_language),
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    progressBarLayout.visibility = View.VISIBLE
                    searchData(etLanguage.text.toString())
                    AppUtility.hideKeyboard(this@MainActivity, etLanguage.rootView)
                }

            } else {
                Toast.makeText(context, getString(R.string.no_internet), Toast.LENGTH_LONG).show()
            }

        }

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    fun searchData(language: String) {


        repositoryViewModel.getLanguageData(language)
        repositoryViewModel.repositoryData?.observe(this, Observer {

            if (it == null) {
                Toast.makeText(
                    this@MainActivity,
                    getString(R.string.no_result_try_again),
                    Toast.LENGTH_SHORT
                ).show()
                progressBarLayout.visibility = View.GONE
            } else {
                progressBarLayout.visibility = View.GONE
                val articles = it.items
                repoData = it
                recyclerView.adapter = RecyclerViewAdapter(articles, width, height, this)


            }
        })

    }

    override fun onRecycleViewItemClick(position: Int) {
        if (repoData != null && repoData.items != null) {

            val articles = repoData.items
            val item = articles.get(position)
            val i = Intent(this@MainActivity, DetailActivity::class.java)
            i.putExtra("item", item)
            startActivity(i)
        }


    }


}
