package ir.androidcoder.pagingrecyclerviewlibrary.basaAdapter

import androidx.annotation.LayoutRes
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ir.androidcoder.pagingrecyclerviewlibrary.PagingRecyclerView
import koleton.api.hideSkeleton
import koleton.api.loadSkeleton
import kotlinx.coroutines.launch

abstract class BasePagingAdapter<T : Any, VH : RecyclerView.ViewHolder>(
    diffCallback: DiffUtil.ItemCallback<T>,
    private val recyclerView: PagingRecyclerView,
    @LayoutRes private val layout : Int
) : PagingDataAdapter<T, VH>(diffCallback) {

    private var externalLoadState: ((CombinedLoadStates) -> Unit)? = null
    var skeletonEnable: Boolean = true
        set(value) {
            field = value
        }
        get() = field

    init {
        handleLoadState()
    }

    private fun handleLoadState() {
        addLoadStateListener { loadStates ->
            if (skeletonEnable)
                when (loadStates.refresh) {
                    is LoadState.Loading -> {
                        recyclerView.getRecyclerView().loadSkeleton(layout){
                            itemCount(10)
                        }
                    }

                    is LoadState.NotLoading -> {
                        recyclerView.getRecyclerView().hideSkeleton()
                    }

                    is LoadState.Error -> {

                    }
                }
            externalLoadState?.invoke(loadStates)
        }
    }

    fun loadStateListener(listener: (CombinedLoadStates) -> Unit) {
        externalLoadState = listener
    }


    fun removeItem(position: Int, lifecycleScope: LifecycleCoroutineScope) {
        if (position != RecyclerView.NO_POSITION) {
            val currentItems = snapshot().items.toMutableList()
            if (position in currentItems.indices) {
                currentItems.removeAt(position)
                val newPagingData = PagingData.from(currentItems)
                lifecycleScope.launch {
                    submitData(newPagingData)
                }
            }
        }
    }

    fun addItem(position: Int, newData: T, lifecycleScope: LifecycleCoroutineScope) {
        val currentItems = snapshot().items.toMutableList()
        currentItems.add(position, newData)
        val newPagingData = PagingData.from(currentItems)
        lifecycleScope.launch {
            submitData(newPagingData)
        }
    }

    fun removeAllItem(lifecycleScope: LifecycleCoroutineScope) {
        val currentItems = snapshot().items.toMutableList()
        currentItems.clear()
        val newPagingData = PagingData.from(currentItems)
        lifecycleScope.launch {
            submitData(newPagingData)
        }
    }

}