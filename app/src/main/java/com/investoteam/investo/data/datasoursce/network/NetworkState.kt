package com.investoteam.investo.data.datasoursce.network


import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

class NetworkState constructor(val status: Status, val msg: Any? = null, val data: Any? = null) {

    enum class Status {
        RUNNING, FAILED, SUCCESS
    }

    companion object {
        fun getLoaded(dataSuccess: Any?): NetworkState {
            return NetworkState(Status.SUCCESS, data = dataSuccess)
        }

        var LOADING: NetworkState = NetworkState(Status.RUNNING)

        fun getErrorMessage(throwable: Throwable): NetworkState {
            return when (throwable) {
                is IOException -> {
                    NetworkState(Status.FAILED, "No Connection ")
                }
                is SocketTimeoutException -> {
                    NetworkState(Status.FAILED, "Bad Connection")
                }
                is HttpException -> {

                    NetworkState(Status.FAILED, "HttpException")
                }
                else -> {
                    NetworkState(Status.FAILED, "Error")
                }
            }
        }

        fun getErrorMessage(massage: String): NetworkState {
            return NetworkState(Status.FAILED, massage)

        }

    }
} // class of NetworkState