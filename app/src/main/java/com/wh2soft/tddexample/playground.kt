package com.wh2soft.tddexample

import arrow.syntax.function.curried
import arrow.syntax.function.memoize

fun curryfiedFuncion(x: Int): (Int) -> (Int) -> (Int) -> Int {
    return { y -> { z -> { aa -> x + y + z + aa } } }
}

fun curryfiedChainedFuncion(a: Int):
        (Int, Int) ->
        (Int, Int, Int) ->
        (Int, Int, Int, Int) -> Int {
    return { b, c ->
        { d, e, f ->
            { g, h, i, j ->
                a + b + c + d + e + f + g + h + i + j
            }
        }
    }
}

fun main() {
    curryfiedFuncion(1)(1)(1)(1)
    curryfiedChainedFuncion(1)(2, 3)(4, 5, 6)(7, 8, 9, 10)
    val curryfun = { a: Int, b: Int, c: (Int, Int) -> Boolean, d: Int -> c(a, b) }.curried()
    val dkldjfs = { a: Int, b: Int -> true }
    val dfdf = curryfun(1)(4)() { a, b -> a == b }
    val dklfhjks = dfdf(5)
}