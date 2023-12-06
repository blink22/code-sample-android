package com.blink22.sample.topKotlins.ui.views.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.blink22.sample.topKotlins.data.models.GithubRepo
import com.blink22.sample.topKotlins.databinding.ItemRepositoryBinding
import com.blink22.sample.topKotlins.ui.base.BaseRecyclerViewAdapter

class RepositoriesListAdapter: BaseRecyclerViewAdapter<GithubRepo, RepositoriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesViewHolder {
        val itemBinding = ItemRepositoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return RepositoriesViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) =
        holder.bind(items[position])

}