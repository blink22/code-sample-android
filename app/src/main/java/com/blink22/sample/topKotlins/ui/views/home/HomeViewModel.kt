package com.blink22.sample.topKotlins.ui.views.home

import androidx.lifecycle.viewModelScope
import com.blink22.sample.topKotlins.data.models.GithubRepo
import com.blink22.sample.topKotlins.domain.GetTopRepositoriesUseCase
import com.blink22.sample.topKotlins.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private var getTopRepositoriesUseCase: GetTopRepositoriesUseCase
) : BaseViewModel() {

    /**
     * Declare channel of type [HomeEvent] so the view (fragment)
     * observes different events triggered by view model
     * */
    private val _event = Channel<HomeEvent>(Channel.BUFFERED)
    val event = _event.receiveAsFlow()

    var repositories: List<GithubRepo>? = null
    var errorMessage: String? = null

    /**
     * Fetch the repositories list via calling GetTopRepositoriesUseCase.
     */
    fun getTopRepositories() {
        viewModelScope.launch {

            /** [getTopRepositoriesUseCase] Triggers GithubRepoApi call in the GithubRepoRepository
             *  and returns the response to update the UI accordingly (on success and failure) .
             *  */
            getTopRepositoriesUseCase()
                .flowOn(Dispatchers.IO)
                .catch { exception ->
                    /** On Failure, save the [exception].localizedMessage in [errorMessage],
                     * then trigger [HomeEvent.OnError] to notify the HomeFragment with
                     * the error update.
                     * */
                    errorMessage = exception.localizedMessage
                    sendEvent(_event, HomeEvent.OnError)
                }
                .collect {
                    /** On success, save the repositories list in [repositories],
                     * then trigger [HomeEvent.OnRepositoriesLoaded] to notify the [HomeFragment]
                     * with the data updates.
                     * */
                    repositories = it.items
                    sendEvent(_event, HomeEvent.OnRepositoriesLoaded)
                }

        }
    }

}
