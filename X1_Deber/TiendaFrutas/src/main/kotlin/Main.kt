fun main(args: Array<String>) {
    val tienda1 = Tienda("Fruteria Thomas","Quito",1234567890,983359387,"Thomas Tapia")
    val fruta1 = Fruta("Pera",0.25,10,"Manzana")
    val fruta2 = Fruta("Manzana",0.15,5,"Manzana")
    tienda1.añadirFruta(fruta1)
    tienda1.añadirFruta(fruta2)
    tienda1.frutas.forEach{println(it.nombreFruta +" " + it.disponibilidad)}
    tienda1.comprarFruta(1,10)
    tienda1.comprarFruta(2,2)
    tienda1.finalizarCompra()
    tienda1.añadirCantidadFruta(1,1)
    tienda1.comprarFruta(1,3)
    tienda1.comprarFruta(2,1)
    tienda1.finalizarCompra()

//    tienda1.eliminarFruta(2)
//    tienda1.frutas.forEach{println(it.nombreFruta +" " + it.disponibilidad)}

}