package com.samuel.posts.presentation.postlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.samuel.presentation.Resource
import br.com.samuel.presentation.ResourceState
import com.samuel.posts.domain.model.Post
import com.samuel.posts.domain.model.User
import com.samuel.posts.domain.usecase.CombinedUserPost
import com.samuel.posts.domain.usecase.UsersPostsUseCase
import com.samuel.posts.presentation.model.PostItem
import com.samuel.posts.presentation.model.mapToPresentation
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PostListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val repository = mockk<UsersPostsUseCase>()

    private val onPostsLoadedObserver = mockk<Observer<Resource<List<PostItem>>>>()

    val list = listOf(
        CombinedUserPost(
            User("1", "teste", "teste", "teste"),
            Post("teste", "teste", "teste", "teste")
        )
    )

    @Before
    fun beforeTest() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `when view model fetches data than it should call the repository`() {
        val viewModel = instantiateViewModel()

        val resourceSuccess = Resource(ResourceState.SUCCESS, list.mapToPresentation())

        every { repository.get(false) } returns Single.just(list)

        viewModel.get()

        verify { repository.get(false) }
        verify { onPostsLoadedObserver.onChanged(resourceSuccess) }
    }

    @Test
    fun `when view model fetches data than it should call the repository loading`() {
        val viewModel = instantiateViewModel()

        val resourceLoading = Resource(ResourceState.LOADING, null)

        every { repository.get(false) } returns Single.just(list)

        viewModel.get()

        verify { repository.get(false) }
        verify { onPostsLoadedObserver.onChanged(resourceLoading) }
    }

    private fun instantiateViewModel(): PostListViewModel {
        val viewModel = PostListViewModel(repository)
        viewModel.posts.observeForever(onPostsLoadedObserver)
        return viewModel
    }
}
