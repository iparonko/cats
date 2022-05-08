package com.example.cats_favorites_impl.presentation.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cats_favorites_impl.databinding.FragmentFavoriteListBinding
import com.example.cats_favorites_impl.di.components.DaggerCatsFavoriteListComponent
import com.example.cats_favorites_impl.presentation.CatFavoriteListAdapter
import com.example.cats_favorites_impl.presentation.state.CatsFavoriteListFragmentState
import com.example.cats_favorites_impl.presentation.viewmodels.CatsFavoriteListViewModel
import com.example.core_di_impl.dependensies.AppDependencies
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CatsFavoriteListFragment : Fragment() {

    private lateinit var catFavoriteListAdapter: CatFavoriteListAdapter

    private val component by lazy {
        DaggerCatsFavoriteListComponent
            .factory()
            .create(
                appDependencies = AppDependencies.instance
            )
    }

    private val viewModel by lazy {
        ViewModelProvider(
            owner = this,
            factory = CatsFavoriteListViewModel.getFactory(
                removeCat = component.removeCat,
                getAllFavoriteCats = component.getAllFavoriteCats
            )
        )[CatsFavoriteListViewModel::class.java]
    }

    private var _binding: FragmentFavoriteListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fragmentState.onEach { updateContentState(it) }.launchIn(lifecycleScope)
        initRecycleView()
        initLiveData()
    }

    private fun initRecycleView() {
        catFavoriteListAdapter = CatFavoriteListAdapter()
        catFavoriteListAdapter.callbackFavoriteListAdapter =
            object : CatFavoriteListAdapter.CallbackFavoriteListAdapter {
                override fun onFavoriteIconClicked(
                    catId: String?,
                    imageUrl: String?
                ) {
                    viewModel.isFavoriteClicked(
                        catId = catId,
                        imageUrl = imageUrl
                    )
                }
            }
        with(binding.catsRecyclerView) {
            apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = catFavoriteListAdapter
                itemAnimator = null
            }
        }
    }

    private fun initLiveData() {
        with(viewModel) {
            cats.observe(viewLifecycleOwner) {
                catFavoriteListAdapter.submitList(it)
            }
        }
    }

    private fun updateContentState(state: CatsFavoriteListFragmentState) = when (state) {
        is CatsFavoriteListFragmentState.ShowShimmers -> binding.showContent(false)
        is CatsFavoriteListFragmentState.ShowContent -> binding.showContent(true)
        is CatsFavoriteListFragmentState.ShowError -> TODO("Show error")
    }

    private fun FragmentFavoriteListBinding.showContent(visible: Boolean) {
        this.catsRecyclerView.isVisible = visible
        this.shimmerView.isVisible = visible.not()
    }

    companion object {
        fun newInstance(): CatsFavoriteListFragment {
            return CatsFavoriteListFragment()
        }
    }
}