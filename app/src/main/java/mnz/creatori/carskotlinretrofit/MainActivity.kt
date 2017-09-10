package mnz.creatori.carskotlinretrofit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://goo.gl/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()


        val carsApi: CarsApi = retrofit.create(CarsApi::class.java)


        carsApi.getData().enqueue(object : Callback<CarsResponse>{
            override fun onResponse(call: Call<CarsResponse>, response: Response<CarsResponse>) {


                if (response.isSuccessful) {
                    val carsResponse: CarsResponse = response.body()!!

//                    Log.d("CARS", carsResponse.carslist.size.toString())

                    carlist.addAll(carsResponse!!.carslist)


                    tv.text = carsResponse.carslist[0].toString()
                    //                    tv.setText("1");
                }
            }

            override fun onFailure(call: Call<CarsResponse>, t: Throwable) {

                tv.text = "Some Error"
            }

        })


    }
}
