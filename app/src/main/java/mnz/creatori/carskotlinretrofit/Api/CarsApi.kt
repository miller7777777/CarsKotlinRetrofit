package mnz.creatori.carskotlinretrofit.Api

import io.reactivex.Observable
import mnz.creatori.carskotlinretrofit.Model.CarsResponse
import retrofit2.Call
import retrofit2.http.GET


interface CarsApi {

    @GET("1tW53X")
//    fun getData(): Call<CarsResponse>
    fun getData(): Observable<CarsResponse>
}