package mnz.creatori.carskotlinretrofit.Model


data class Car(val name: String,
               val image: String,
               val vin: String)

data class CarsResponse(val cars: List<Car>)