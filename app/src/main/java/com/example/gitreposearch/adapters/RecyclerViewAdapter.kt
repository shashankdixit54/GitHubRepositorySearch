package com.example.gitreposearch.adapters


import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gitreposearch.R
import com.example.gitreposearch.model.Item
import com.example.gitreposearch.utility.MyClickListener


class RecyclerViewAdapter(
    private var modelList: List<Item>,
    private val width: Int,
    private val height: Int,
    private var clickListener: MyClickListener
) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.titleTv.text = modelList[position].name

        holder.recycleItem.setOnClickListener { clickListener.onRecycleViewItemClick(position) }


        Glide.with(holder.imageView.context)
            .load(modelList[position].owner.avatarUrl)
            .into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        internal var titleTv: TextView
        internal var recycleItem: LinearLayout
        internal var imageView: ImageView

        init {
            titleTv = v.findViewById(R.id.titleTv) as TextView
            titleTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, (width / 70).toFloat())
            recycleItem = v.findViewById(R.id.mainRecycleItem)
            imageView = v.findViewById(R.id.image) as ImageView
            imageView.layoutParams.width = width / 5
            imageView.layoutParams.height = width / 5
            imageView.setPadding(width / 30, width / 30, width / 30, width / 30)
        }
    }

}