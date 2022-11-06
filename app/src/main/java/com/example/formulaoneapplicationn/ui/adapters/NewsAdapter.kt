package com.example.formulaone.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.formulaoneapplicationn.databinding.SingleNewsLayoutBinding
import com.example.formulaoneapplicationn.domain.model.ArticleDomain

class NewsAdapter :
    ListAdapter<ArticleDomain, NewsAdapter.NewsViewHolder>(
        NewsDiffCallBack()
    ) {

    private lateinit var itemClickListener: (ArticleDomain, Int) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): NewsViewHolder {
        val binding =
            SingleNewsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindData()
    }

    inner class NewsViewHolder(private val binding: SingleNewsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var model: ArticleDomain? = null

        fun bindData() {
            model = getItem(bindingAdapterPosition)
            binding.apply {
                tvNewsText.text = model?.title
                tvDesription.text = model?.description
                Glide.with(this.ivNewsImage)
                    .load(model?.urlToImage)
                    .into(ivNewsImage)
            }

            binding.ivNewsImage.setOnClickListener {
                itemClickListener.invoke(model!!, absoluteAdapterPosition)
            }
        }
    }

    fun setOnItemClickListener(clickListener: (ArticleDomain, Int) -> Unit) {
        itemClickListener = clickListener
    }

}

class NewsDiffCallBack :
    DiffUtil.ItemCallback<ArticleDomain>() {
    override fun areItemsTheSame(
        oldItem: ArticleDomain,
        newItem: ArticleDomain
    ): Boolean {
        return oldItem.content == newItem.content
    }

    override fun areContentsTheSame(
        oldItem: ArticleDomain,
        newItem: ArticleDomain
    ): Boolean {
        return oldItem == newItem
    }


}