package com.noweto.nasaclone.core.utils

data class Resource <T> ( val status:Status , val data:T? , val message:String?){

    enum class Status{
        REMOTE_SUCCESS,LOCAL_SUCCESS,ERROR,LOADING
    }

    companion object{

        fun <T> localSuccess(data:T? = null):Resource<T>{
            return Resource(Status.LOCAL_SUCCESS,data,null)
        }

        fun <T> remoteSuccess(data:T?=null):Resource<T>{
            return Resource(Status.REMOTE_SUCCESS,data,null)
        }

        fun <T> error(message:String?,data:T? = null):Resource<T>{

            return Resource(Status.ERROR,data,message)
        }

        fun <T> loading(data: T?=null):Resource<T>{
            return Resource(Status.LOADING,data,null)
        }

    }



}