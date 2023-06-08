class Tienda (
    protected val nombreTienda : String,
    protected val direccion : String,
    protected val ruc : Int,
    protected val telefono : Int,
    protected val propietario: String,
    protected var frutas : ArrayList<Fruta>?

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

    ) : this(nombreTienda, direccion, ruc, telefono, propietario)


    fun añadirFruta(fruta: Fruta){
        this.frutas?.add(fruta)
        print(frutas)
    }

}

class Fruta(
        protected val nombreFruta: String,
        protected val precio: Double,
        protected val cantidad: Int,
        protected var disponibilidad: Boolean,
        protected val familiaFruta: String
){
  init {
      this.nombreFruta; this.precio; this.cantidad; this.familiaFruta; this.disponibilidad
  }

//    constructor(
//            //Segundo constructor
//            nombre: String, //Parametro
//            precio: Double,  //Parametro
//            cantidad : Int,
//            familiaFruta : String,
//    ) : this(
//           nombre, precio, cantidad, (if (cantidad > 0) this.disponibilidad = true else false) as Boolean, familiaFruta
//    )

}

