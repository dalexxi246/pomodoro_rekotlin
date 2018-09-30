package com.wh2soft.tddexample.redux

import com.wh2soft.tddexample.redux.state.Project
import org.rekotlin.Action

sealed class ProjectAction : Action {
    class CreateProject(val project: Project) : ProjectAction()
    class RemoveProject(val idForRemovalProject: Long) : ProjectAction()
}

class UselessAction : Action