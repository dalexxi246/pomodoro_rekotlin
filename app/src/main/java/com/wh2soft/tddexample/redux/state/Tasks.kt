package com.wh2soft.tddexample.redux.state

import java.util.*

data class Task(
        val description: String = "",
        val estimatedPomodoros: Int = 0,
        val dueDate: Date? = null,
        val completed: Boolean = false,
        val priority: Priority? = null
)

enum class Priority { IMPORTANT_URGENT, IMPORTANTE, URGENT, IRRELEVANT }
