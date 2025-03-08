package ir.androidcoder.pagingrecyclerviewlibrary.basaAdapter

import androidx.recyclerview.widget.DiffUtil

class BaseDiffCallback<T : Any>(
    private val areItemsTheSameCallback: (oldItem: T, newItem: T) -> Boolean,
    private val areContentsTheSameCallback: (oldItem: T, newItem: T) -> Boolean
) : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return areItemsTheSameCallback(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return areContentsTheSameCallback(oldItem, newItem)
    }

}