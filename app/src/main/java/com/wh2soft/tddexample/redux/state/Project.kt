package com.wh2soft.tddexample.redux.state

data class Project(
        val id: Long = 0L,
        val description: String = "",
        val color: Color? = null,
        val tasks: List<Task> = emptyList()
)

enum class Color { RED, BLUE, GREEN, ORANGE, YELLOW, TEAL, BLACK, GRAY, PURPLE, PINK }
