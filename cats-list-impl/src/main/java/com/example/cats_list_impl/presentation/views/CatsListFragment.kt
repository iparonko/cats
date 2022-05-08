package com.example.cats_list_impl.presentation.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cats_list_impl.databinding.FragmentCatsListBinding
import com.example.cats_list_impl.di.components.DaggerCatsListComponent
import com.example.cats_list_impl.presentation.CatListAdapter
import com.example.cats_list_impl.presentation.state.CatsListFragmentState
import com.example.cats_list_impl.presentation.viewmodels.CatsListViewModel
import com.example.core_di_impl.dependensies.AppDependencies
import com.example.ui_kit.recyclerview.PaginationListener
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CatsListFragment : Fragment() {

    private lateinit var catListAdapter: CatListAdapter

    private val component by lazy {
        DaggerCatsListComponent
            .factory()
            .create(
                module = com.example.cats_list_impl.di.modules.CatsListModule,
                appDependencies = AppDependencies.instance
            )
    }

    private val viewModel by lazy {
        ViewModelProvider(
            owner = this,
            factory = CatsListViewModel.getFactory(
                getImages = component.getImages,
                saveCat = component.saveCat,
                removeCat = component.removeCat
            )
        )[CatsListViewModel::class.java]
    }

    private var _binding: FragmentCatsListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fragmentState.onEach { updateContentState(it) }.launchIn(lifecycleScope)
        initRecycleView()
        initLiveData()
    }

    private fun initRecycleView() {
        catListAdapter = CatListAdapter()
        catListAdapter.callbackCatListAdapter = object : CatListAdapter.CallbackCatListAdapter {
            override fun onFavoriteIconClicked(catId: String?, imageUrl: String?, state: Boolean) {
                viewModel.isFavoriteClicked(
                    catId = catId,
                    imageUrl = imageUrl,
                    state = state
                )
            }
        }
        with(binding.catsRecyclerView) {
            apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = catListAdapter
                itemAnimator = null
                this.addOnScrollListener(
                    getRecycleOnScrollListener(binding.catsRecyclerView.layoutManager as LinearLayoutManager)
                )
            }
        }
    }

    private fun getRecycleOnScrollListener(layoutManager: LinearLayoutManager): PaginationListener =
        object : PaginationListener(layoutManager) {
            override val isLoading: Boolean
                get() = viewModel.isLoading

            override fun loadMoreItems() {
                viewModel.loadMoreItems()
            }
        }

    private fun initLiveData() {
        with(viewModel) {
            cats.observe(viewLifecycleOwner) {
                catListAdapter.submitList(it)
            }
        }
    }

    private fun updateContentState(state: CatsListFragmentState) = when (state) {
        is CatsListFragmentState.ShowShimmers -> binding.showContent(false)
        is CatsListFragmentState.ShowContent -> binding.showContent(true)
        is CatsListFragmentState.ShowError -> TODO("Show error")
    }

    private fun FragmentCatsListBinding.showContent(visible: Boolean) {
        this.catsRecyclerView.isVisible = visible
        this.shimmerView.isVisible = visible.not()
    }
}