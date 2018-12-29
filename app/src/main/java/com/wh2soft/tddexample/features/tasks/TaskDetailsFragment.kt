package com.wh2soft.tddexample.features.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.wh2soft.tddexample.R
import com.wh2soft.tddexample.shared.TaskDetailsMenuItems
import com.wh2soft.tddexample.shared.addItem
import com.wh2soft.tddexample.shared.uicomponents.titleListItemView
import org.jetbrains.anko.*

class TaskDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = TaskDetailsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val viewModel = ViewModelProviders.of(this).get(TaskDetailsViewModel::class.java)
        return Component(viewModel).createView(AnkoContext.create(context!!, this))
    }

    class Component(private val viewModel: TaskDetailsViewModel) : AnkoComponent<TaskDetailsFragment> {

        lateinit var toolbar: Toolbar

        override fun createView(ui: AnkoContext<TaskDetailsFragment>): View {
            return with(ui) {
                verticalLayout {
                    toolbar = themedToolbar(android.R.style.ThemeOverlay_Material_Dark_ActionBar) {
                        backgroundColorResource = R.color.colorPrimary
                        titleResource = R.string.text_taskdetails_title
                        elevation = 10f
                        navigationIconResource = android.R.drawable.ic_menu_more
                        menu.addItem(ctx, TaskDetailsMenuItems.settings)
                        setOnMenuItemClickListener {
                            when (it.itemId) {
                                R.id.menuitem_taskdetails_settings -> {
                                    findNavController().navigate(R.id.authenticateUser)
                                    true
                                }
                                else -> true
                            }
                        }
                    }
                    scrollView {
                        verticalLayout {
                            for (i in 1..10) {
                                titleListItemView("Row $i") {
                                    setOnClickListener { toast(title) }
                                }
                            }
                            button {
                                textResource = R.string.button_taskdetails_save
                                setOnClickListener {
                                    findNavController().navigateUp()
                                }
                            }
                        }
                    }.lparams {
                        width = LinearLayout.LayoutParams.MATCH_PARENT
                        height = LinearLayout.LayoutParams.WRAP_CONTENT
                    }
                }
            }.applyRecursively {

            }.also {
                viewModel.liveData.observe(ui.owner, Observer { })
            }
        }
    }

}
