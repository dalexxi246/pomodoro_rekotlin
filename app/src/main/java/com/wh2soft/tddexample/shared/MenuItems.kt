package com.wh2soft.tddexample.shared

import android.content.Context
import android.view.Menu
import androidx.annotation.StringRes
import com.wh2soft.tddexample.R

class MenuItem(val groupId: Int = Menu.NONE,
               val itemId: Int = Menu.NONE,
               val order: Int = Menu.NONE,
               @StringRes val titleResource: Int)

fun Menu.addItem(context: Context, item: MenuItem) {
    this.add(item.groupId, item.itemId, item.order, context.getString(item.titleResource))
}

object TaskDetailsMenuItems {
    val settings = MenuItem(itemId = R.id.menuitem_taskdetails_settings, titleResource = R.string.menuitem_settings)
}