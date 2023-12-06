package com.blink22.sample.topKotlins.ui.views.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.blink22.sample.topKotlins.R
import com.blink22.sample.topKotlins.databinding.FragmentHomeBinding
import com.blink22.sample.topKotlins.ui.base.BaseFragment
import com.blink22.sample.topKotlins.ui.utils.observe
import com.blink22.sample.topKotlins.ui.views.home.adapter.RepositoriesListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override val vm: HomeViewModel by viewModels()
    private val repositoriesAdapter: RepositoriesListAdapter by lazy {
        RepositoriesListAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Set repositories' recyclerView adapter since the LayoutManager is already set
         * in fragment_home layout.
         * */
        vb.rvRepositories.adapter = repositoriesAdapter

        /**
         * Fetch screen data
         * */
        vm.getTopRepositories()
    }

    override fun setupObservers() {
        super.setupObservers()
        observe(vm.event) {
            when (it) {

                /**
                 * Set [repositoriesAdapter].data when [vm].repositories are loaded successfully
                 * ([HomeEvent.OnRepositoriesLoaded] is sent).
                 * */
                is HomeEvent.OnRepositoriesLoaded -> {
                    vm.repositories?.let { repositories ->
                        repositoriesAdapter.setData(repositories)
                    }
                }

                /**
                 * Show a toast with [vm].errorMessage when [HomeEvent.OnError] is called.
                 * */
                HomeEvent.OnError -> {
                    vm.errorMessage?.let { errorMessage ->
                        Toast.makeText(
                            this@HomeFragment.context,
                            errorMessage,
                            Toast.LENGTH_LONG
                        )
                    }
                }

            }
        }
    }

}