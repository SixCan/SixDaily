package ca.six.daily.view

import android.view.ViewGroup
import android.support.v7.widget.RecyclerView

abstract class OneAdapter<T>(val layoutResId: Int) : RecyclerView.Adapter<RvViewHolder>() {
    var data: List<T> = ArrayList()

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        val vh = RvViewHolder.createViewHolder(parent, layoutResId)
        return vh
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        if (data.size > position) {
            apply(holder, data[position], position)
        }
    }

    protected abstract fun apply(vh: RvViewHolder, t: T, position: Int)

}
