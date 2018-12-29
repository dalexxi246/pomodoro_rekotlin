package com.wh2soft.tddexample.shared.uicomponents

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.view.ViewManager
import android.widget.FrameLayout
import android.widget.RelativeLayout
import com.wh2soft.tddexample.R
import com.wh2soft.tddexample.shared.Drawable
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView

class TitleListItemComponent(val title: String) : AnkoComponent<Context> {

    override fun createView(ui: AnkoContext<Context>): View {
        return with(ui) {
            relativeLayout {
                background = Drawable.taskDetailsTitleDrawableFactory().build()
                lparams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT) {
                    margin = 20
                    padding = 70
                    centerHorizontally()
                }
                textView(title) {
                    text = title
                    textColorResource = android.R.color.black
                    textSizeDimen = R.dimen.textsize_title_list_item
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
            }
        }
    }
}

inline fun ViewManager.titleListItemView(title: String): TitleListItemView = titleListItemView(title) {}
inline fun ViewManager.titleListItemView(title: String, init: TitleListItemView.() -> Unit): TitleListItemView = ankoView({ TitleListItemView(it, title) }, 0, init)

class TitleListItemView(ctx: Context, val title: String) : FrameLayout(ctx) {

    // TODO: Añadir propiedades al View o al componente para accederlos desde cualquier parte.

    init {
        addView(TitleListItemComponent(title).createView(AnkoContext.create(ctx)))
    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
    }

}