package ca.six.daily.biz.home.viewmodel

import ca.six.daily.R
import ca.six.daily.data.Story
import ca.six.daily.view.RvViewHolder
import ca.six.daily.view.ViewType

class ListItemViewModel(val name : String) : ViewType, Story(name) {

    override fun getViewType(): Int {
        return R.layout.item_daily_list
    }

    override fun bind(holder: RvViewHolder) {
        holder.setText(R.id.tvListItemTitle, name)
    }

}