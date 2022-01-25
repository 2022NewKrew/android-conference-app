package com.survivalcoding.ifkakao.domain.usecase

abstract class BaseUseCase {
    inline fun <T> result(block: () -> T): Result<T> {
        return try {
            Result.Success(block.invoke())
        } catch (t: Throwable) {
            Result.Error(t)
        }
    }
}

sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error<T>(val error: Throwable) : Result<T>()
}