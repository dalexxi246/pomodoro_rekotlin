package com.wh2soft.tddexample.redux.epics

import com.wh2soft.tddexample.redux.Epic

val exampleEpic : Epic = { actions, store -> actions.map { it } }