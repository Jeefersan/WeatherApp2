package com.jeefersan.util

sealed class Result<out R> {
    class Success<out T>(val data: T) : Result<T>()
    class Failure(val throwable: Throwable?) : Result<Nothing>()

}
