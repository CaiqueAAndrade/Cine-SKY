package com.caique.cinesky

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.caique.cinesky.model.MoviesResponse
import com.caique.cinesky.viewmodel.MoviesListViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.mock
import kotlin.collections.ArrayList

@RunWith(JUnit4::class)
class MoviesListViewModelTest {

    lateinit var moviesListViewModel: MoviesListViewModel

    @Before
    fun setUp() {
        moviesListViewModel = mock(MoviesListViewModel::class.java)

    }

    fun setUpMovieList() : LiveData<ArrayList<MoviesResponse>> {
        val movies: ArrayList<MoviesResponse> = ArrayList()
        for (x in 1..5) {
            movies.add(
                MoviesResponse("Title Test","OverView Test",
                    "1:00:00", "2000",
                    "https://image.tmdb.org/t/p/w1280/dsAQmTOCyMDgmiPp9J4aZTvvOJP.jpg",
                    ArrayList(), "12345"
                ))
        }
        val value = MutableLiveData<ArrayList<MoviesResponse>>().apply { postValue(movies)}
        return value
    }

    @Test
    fun test() {
        Mockito.`when`(this.moviesListViewModel.getMoviesListRepository()).thenReturn(setUpMovieList())
        Assert.assertNotNull(this.moviesListViewModel.getMoviesListRepository())
    }
}