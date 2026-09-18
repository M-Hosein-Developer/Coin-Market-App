package ir.androidcoder.pagingrecyclerviewlibrary

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import ir.androidcoder.pagingrecyclerviewlibrary.basaAdapter.BasePagingAdapter


class PagingRecyclerView @JvmOverloads constructor(context : Context, attrs : AttributeSet? = null) : ConstraintLayout(context, attrs) {

    private val recyclerView: RecyclerView = RecyclerView(context).apply {
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )
    }

    private var skeletonEnableXml: Boolean = true
        set(value) {
            field = value
        }
        get() = field

    init {
        addView(recyclerView)
        initAttributes(attrs)
    }

    @SuppressLint("Recycle", "CustomViewStyleable")
    private fun initAttributes(attrs: AttributeSet?){

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.PagingRecyclerView,
            0,
            0
        ).apply {

            attrs?.let {

                val typedArray = context.obtainStyledAttributes(it , R.styleable.PagingRecyclerView , 0 , 0)

                skeletonEnableXml = typedArray.getBoolean(R.styleable.PagingRecyclerView_prv_activatedSkeleton, true)

                typedArray.recycle()
            }

        }

    }

    //adapter
    fun setAdapter(adapter: BasePagingAdapter<*, *>): PagingRecyclerView {
        recyclerView.adapter = adapter.apply {
            skeletonEnable = skeletonEnableXml
        }
        return this
    }

    //layout manager
    fun setLayoutManager(layoutManager : LayoutManager) : PagingRecyclerView {
        recyclerView.layoutManager = layoutManager
        return this
    }

    //recycler
    fun getRecyclerView() : RecyclerView = recyclerView

}