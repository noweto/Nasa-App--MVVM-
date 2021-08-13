package com.noweto.nasaclone.core.data.remote

import com.noweto.nasaclone.core.utils.Resource
import retrofit2.Response

abstract class BaseRemoteDataSource {

   suspend fun <T> getResults(call: suspend () ->Response<T>) : Resource<T>{

      try {

         val response = call()
         if(response.isSuccessful){
            val body = response.body()
            if (body!=null)
               return Resource.remoteSuccess(body)
         }

         return Resource.error("${response.code()}${response.message()}")

      }catch (e:Exception){
         return Resource.error("${e.message}")
      }

   }

}