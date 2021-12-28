package com.smolianinovasiuzanna.movielist.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smolianinovasiuzanna.movielist.data.NyResponse
import com.smolianinovasiuzanna.movielist.databinding.FragmentListBinding
import com.smolianinovasiuzanna.movielist.utils.ViewBindingFragment
import timber.log.Timber

class ListFragment : ViewBindingFragment<FragmentListBinding>(FragmentListBinding::inflate) {

    private lateinit var movieAdapter: MovieAdapter
    private val viewModel: ListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        bindViewModel()
        viewModel.loadList(0)
    }

    private fun initList(){
        movieAdapter = MovieAdapter()
        with(binding.movieList) {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            initPagination(this)
        }
    }

    private fun bindViewModel(){
        viewModel.movieList.observe(viewLifecycleOwner){
           movieAdapter.setMovieList(toListMovie(it))
        }
        viewModel.error.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), "Error: ${it.message}", Toast.LENGTH_SHORT).show()
            Timber.e(it)
        }
    }
    private fun toListMovie(data: NyResponse.MovieList): List<NyResponse.Movie>{
        return data.list
    }

    private fun initPagination(recyclerView: RecyclerView) {
        val isLastPage = false
        var isLoading = false
        val lm = recyclerView.layoutManager
        var pageCount = 0

        recyclerView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                val lastVisibleItemPosition: Int =
                    (lm as LinearLayoutManager).findLastVisibleItemPosition()
                if (lastVisibleItemPosition == movieAdapter.itemCount - 1)
                    if (!isLoading && !isLastPage) {
                        isLoading = true
                        viewModel.loadList(pageCount++)
                        isLoading = false
                    }
            }
        })
    }

}