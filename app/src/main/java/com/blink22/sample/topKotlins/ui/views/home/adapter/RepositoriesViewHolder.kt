package com.blink22.sample.topKotlins.ui.views.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.blink22.sample.topKotlins.data.models.GithubRepo
import com.blink22.sample.topKotlins.databinding.ItemRepositoryBinding

class RepositoriesViewHolder(
    private val binding: ItemRepositoryBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: GithubRepo) {
        binding.apply {

            tvRepoName.text = item.name
            tvRepoLanguage.text = item.language
            tvRepoDescription.text = item.description
            tvRepoOwner.text = item.ownerName
            tvRepoForks.text = item.forksCount.toString()
            tvRepoStars.text = item.starsCount.toString()
        }
    }

}