package ca.six.daily.biz.home.model

import ca.six.daily.R
import ca.six.daily.view.RvViewHolder
import ca.six.daily.view.ViewType

class ListTitleViewModel(val title : String) : ViewType {

    override fun getViewType(): Int {
        return R.layout.item_title
    }

    override fun bind(holder: RvViewHolder) {
        holder.setText(R.id.tvTitleItem, title)
    }

}