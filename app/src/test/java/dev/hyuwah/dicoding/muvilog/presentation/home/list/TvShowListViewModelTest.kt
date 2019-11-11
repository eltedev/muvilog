package dev.hyuwah.dicoding.muvilog.presentation.home.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import dev.hyuwah.dicoding.muvilog.data.Repository
import dev.hyuwah.dicoding.muvilog.data.helper.DummyData
import dev.hyuwah.dicoding.muvilog.data.local.SharedPrefSource
import dev.hyuwah.dicoding.muvilog.presentation.model.MovieItem
import dev.hyuwah.dicoding.muvilog.presentation.model.base.Resource
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TvShowListViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var repository: Repository

    @MockK
    private lateinit var sharedPrefSource: SharedPrefSource

    private lateinit var viewModel: TvShowListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        every { sharedPrefSource.getCurrentLangId() }.answers { "en" }
        viewModel = TvShowListViewModel(repository, sharedPrefSource)
    }

    @After
    fun tearDown() {
    }

    private fun createMockObserver() : Observer<Resource<List<MovieItem>>> = spyk(Observer {  })

    @Test
    fun `fetch tv show happy flow empty`(){
        val mockObserver = createMockObserver()
        viewModel.state.observeForever(mockObserver)
        coEvery { repository.fetchDiscoverTvShow(any()) } returns listOf()

        // When
        viewModel.load()

        // Then
        verify { mockObserver.onChanged(Resource.Loading) }
        verify { mockObserver.onChanged(Resource.Success(emptyList())) }

    }

    @Test
    fun `fetch tv show happy flow`(){
        val mockObserver = createMockObserver()
        viewModel.state.observeForever(mockObserver)
        coEvery { repository.fetchDiscoverTvShow(any()) } returns DummyData.MovieList.normal()

        // When
        viewModel.load()

        // Then
        verify { mockObserver.onChanged(Resource.Loading) }
        verify { mockObserver.onChanged(Resource.Success(DummyData.MovieList.normal())) }

    }
}