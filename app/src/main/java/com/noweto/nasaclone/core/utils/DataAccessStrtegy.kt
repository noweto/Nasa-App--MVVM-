package com.noweto.nasaclone.core.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.noweto.nasaclone.ui.home.model.NewsModel
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

//~~ get latest news strategy
fun <T,A> performGetNewsOperations(
    dataBaseQuery: ()->LiveData<T>,
    networkCall:suspend ()->Resource<A>,
    saveCallResults:suspend (A)->Unit
    ):LiveData<Resource<T>> =

    liveData (Dispatchers.IO){
        //~~ start with loading
        emit(Resource.loading())
        //~~ make local db as a default data base
        val source = dataBaseQuery.invoke().map { Resource.localSuccess(it) }
        emitSource(source)
        //~~ call api
        val responseStatus = networkCall.invoke()

        if (responseStatus.status==Resource.Status.REMOTE_SUCCESS){
            //~~ save api result

            saveCallResults(responseStatus.data!!)
        }else if (responseStatus.status==Resource.Status.ERROR)
        //~~ show error message
            emit(Resource.error(responseStatus.message!!))
        //~~ set default data source [ Local Db ]
            emitSource(source)
    }



fun <T> performGetToDayPhotoOperation(
    networkCall: suspend () -> Resource<T>
    ):LiveData<Resource<T>> =

    liveData (Dispatchers.IO) {

        // Call the api ..
        val source = networkCall.invoke()
        emit(Resource.remoteSuccess(source.data))


    }








//~~ Under Testing ~~ //

//fun <T> performGetSavedItem(
//        dataBaseQuery:()->LiveData<T>
//        ):LiveData<Resource<T>> =
//
//    liveData (Dispatchers.IO) {
//        val storedDb = dataBaseQuery.invoke().map { Resource.localSuccess(it) }
//        emitSource(storedDb)
//    }
//
//
