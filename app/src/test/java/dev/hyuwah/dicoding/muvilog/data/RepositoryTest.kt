package dev.hyuwah.dicoding.muvilog.data

import dev.hyuwah.dicoding.muvilog.data.helper.DummyData
import dev.hyuwah.dicoding.muvilog.data.local.FavoriteMovieDao
import dev.hyuwah.dicoding.muvilog.data.remote.ITheMovieDbServices
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class RepositoryTest {

    private lateinit var repository: Repository
    private var lang = "en"

    @MockK
    private lateinit var dao: FavoriteMovieDao

    @MockK
    private lateinit var services: ITheMovieDbServices

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = Repository(
            services, dao
        )
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `should handle fetch discover movies network failure`(){
        runBlocking {
            coEvery { services.getDiscoverMovies(any(), any(), any()) } throws Exception()
            val movies = repository.fetchDiscoverMovies(lang)
            assertNotNull(movies)
            assert(movies.isEmpty())
        }
    }

    @Test
    fun `should success fetch empty discover movies`(){
        runBlocking {
            coEvery { services.getDiscoverMovies(any(), any(), any()) } returns DummyData.DiscoverMovie.emptyResponse()
            val movies = repository.fetchDiscoverMovies(lang)
            assertNotNull(movies)
            assert(movies.isEmpty())
        }
    }

    @Test
    fun `should success fetch normal discover movies`(){
        runBlocking {
            coEvery { services.getDiscoverMovies(any(), any(), any()) } returns DummyData.DiscoverMovie.normalResponse()
            val tvshow = repository.fetchDiscoverMovies(lang)
            assertNotNull(tvshow)
            assert(tvshow.isNotEmpty())
            assert(tvshow.first().title == "Movie 1")
        }
    }

    @Test
    fun `should handle fetch discover tv show network failure`(){
        runBlocking {
            coEvery { services.getDiscoverTvShow(any(), any()) } throws Exception()
            val tvshow = repository.fetchDiscoverTvShow(lang)
            assertNotNull(tvshow)
            assert(tvshow.isEmpty())
        }
    }

    @Test
    fun `should success fetch empty discover tv show`(){
        runBlocking {
            coEvery { services.getDiscoverTvShow(any(), any()) } returns DummyData.DiscoverTv.emptyResponse()
            val tvshow = repository.fetchDiscoverTvShow(lang)
            assertNotNull(tvshow)
            assert(tvshow.isEmpty())
        }
    }

    @Test
    fun `should success fetch normal discover tv show`(){
        runBlocking {
            coEvery { services.getDiscoverTvShow(any(), any()) } returns DummyData.DiscoverTv.normalResponse()
            val tvshow = repository.fetchDiscoverTvShow(lang)
            assertNotNull(tvshow)
            assert(tvshow.isNotEmpty())
            assert(tvshow.first().title == "TV Show 1")
        }
    }

}