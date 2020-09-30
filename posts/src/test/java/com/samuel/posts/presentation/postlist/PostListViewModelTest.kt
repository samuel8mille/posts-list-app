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
import io.mockk.verifyOrder
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PostListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val repository = mockk<UsersPostsUseCase>(relaxed = true)
    private val mThrowable: Throwable = mockk()

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
    fun `when view model fetches data than it should call the repository success`() {
        val viewModel = instantiateViewModel()

        val resourceSuccess = Resource(ResourceState.SUCCESS, list.mapToPresentation())
        val resourceLoading = Resource(ResourceState.LOADING, null)

        every { repository.get(false) } returns Single.just(list)

        every { onPostsLoadedObserver.onChanged(resourceLoading) } answers {
        }

        viewModel.get()

        verify { repository.get(false) }
        verifyOrder {
            onPostsLoadedObserver.onChanged(resourceLoading)
            onPostsLoadedObserver.onChanged(resourceSuccess)
        }
    }

    @Test
    fun `when view model fetches data than it should call the repository error`() {
        val viewModel = instantiateViewModel()

        val resourceLoading = Resource(ResourceState.LOADING, null)
        val resourceError = Resource(ResourceState.ERROR, null)

        every { repository.get(false) } returns Single.error(mThrowable)

        every { onPostsLoadedObserver.onChanged(resourceLoading) } answers {
        }

        viewModel.get()

        verify { repository.get(false) }
        verifyOrder {
            onPostsLoadedObserver.onChanged(resourceLoading)
            onPostsLoadedObserver.onChanged(resourceError)
        }
    }

    private fun instantiateViewModel(): PostListViewModel {
        val viewModel = PostListViewModel(repository)
        viewModel.posts.observeForever(onPostsLoadedObserver)
        return viewModel
    }
}

