class Tienda (
    val nombreTienda : String,
    val direccion : String,
    val ruc : Int,
    val telefono : Int,
    val propietario: String,
    var frutas : ArrayList<Fruta>

){
 init {
        this.nombreTienda; this.direccion; this.ruc; this.telefono; this.propietario;this.frutas
       print("Inicializando")
  }

    constructor(
            nombreTienda: String,
            direccion: String,
            ruc : Int,
            telefono: Int,
            propietario: String

    ) : this(
        nombreTienda,
        direccion,
        ruc,
        telefono,
        propietario,
        frutas = ArrayList<Fruta>()
    )


    fun añadirFruta(fruta: Fruta){
        this.frutas.add(fruta)
        print(frutas)
    }

}

class Fruta(
        val nombreFruta: String,
        val precio: Double,
        val cantidad: Int,
        var disponibilidad: Boolean,
        val familiaFruta: String
){
  init {
      this.nombreFruta; this.precio; this.cantidad; this.familiaFruta; this.disponibilidad
  }

//    constructor(
//            nombre: String,
//            precio: Double,
//            cantidad : Int,
//            familiaFruta : String,
//    ) : this(
//           nombre, precio, cantidad, familiaFruta,
//    ){
//        if (cantidad > 0){
//            this.disponibilidad = true
//        }
//    }

}

