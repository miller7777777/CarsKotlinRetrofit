package mnz.creatori.carskotlinretrofit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import mnz.creatori.carskotlinretrofit.Api.CarsApi
import mnz.creatori.carskotlinretrofit.Model.Car
import mnz.creatori.carskotlinretrofit.Model.CarsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    var carlist = ArrayList<Car>()
    val compositeDisposable: CompositeDisposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://goo.gl/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()


        val carsApi: CarsApi = retrofit.create(CarsApi::class.java)


        compositeDisposable.add(

        carsApi.getData().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    result ->
//                    tv.text(result.cars[0].toString())
                    tv.setText(result.cars[0].toString())
                }, {
                    error ->
//                    tv.text{error.printStackTrace().toString()}
                    tv.setText(error.printStackTrace().toString())
                })

        )




//        carsApi.getData().enqueue(object : Callback<CarsResponse>{
//            override fun onResponse(call: Call<CarsResponse>, response: Response<CarsResponse>) {
//
//
//                if (response.isSuccessful) {
//                    val carsResponse: CarsResponse = response.body()!!
//
////                    Log.d("CARS", carsResponse.carslist.size.toString())
//
//                    carlist.addAll(carsResponse!!.cars)
//
//
//                    tv.text = carlist[0].toString()
//                    //                    tv.setText("1");
//                }
//            }
//
//            override fun onFailure(call: Call<CarsResponse>, t: Throwable) {
//
//                tv.text = "Some Error"
//            }
//
//        })


    }

    override fun onDestroy() {

        compositeDisposable.clear()
        super.onDestroy()
    }
}
