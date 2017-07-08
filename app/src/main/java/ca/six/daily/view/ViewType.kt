package ca.six.daily.view

interface ViewType {
    fun getViewType() : Int
    fun bind(holder: RvViewHolder)
}