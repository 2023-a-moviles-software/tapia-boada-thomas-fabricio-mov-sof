import kotlinx.serialization.json.Json
import java.io.File;
import java.util.Scanner;
fun main(args: Array<String>) {

    //Variable de opciones
    var opcion : Int
    var salir = false
    val input = Scanner(System.`in`) //Scanner datos por consola
    var ejecutar = false
    var tienda:Tienda? = null;
    try{
        tienda = Json.decodeFromString(File("tiendita.json").readText())
    }
    catch (){
        println("Ingrese el nombre de la tienda: ")
        val nombre = input.nextLine()
        println("Ingrese la ubicacion de la tienda: ")
        val ubicacion = input.nextLine()
        println("Ingrese el RUC: ")
        val RUC = input.nextLine()
        println("Ingrese el telefono: ")
        val telefono = input.nextLine().toInt()
        println("Ingrese el nombre del propietario: ")
        val nombrePropietario = input.nextLine()
        tienda = Tienda(nombre, ubicacion, RUC, telefono, nombrePropietario)
    }




    fun menu() {
        println("*" + nombre + "*")
        println("-------Menu-------")
        println("1. Agregar Fruta")
        println("2. Mostrar Frutas")
        println("3. Eliminar Frutas")
        println("4. Añadir Cantidad de Fruta")
        println("5. Realizar Compra")
        println("0. Salir")
    }


    while (!salir){
        menu()
        opcion = input.nextLine().toInt()
        when (opcion){
            1 -> {
                println("*Agregar Fruta*")
                println("Nombre de la Fruta: ")
                var nombreFruta = input.nextLine()
                println("Precio de la Fruta: ")
                var precioFruta = input.nextLine().toDouble()
                println("Cantidad de la Fruta: ")
                var cantidadFruta = input.nextLine().toInt()
                println("Familia Fruta: ")
                var familiaFruta = input.nextLine()
                var fruta = Fruta(nombreFruta, precioFruta, cantidadFruta, familiaFruta)
                tienda.añadirFruta(fruta)
                println("*Se añadio fruta con exito*")
            }
            2 -> {
                println("*Mostrar Frutas*")
                tienda.frutas.forEach { println(it.nombreFruta + " Cantidad: ${it.cantidad}") }
                println("*--------------*")
            }
            3 -> {
                println("*Eliminar Fruta*")
                println("*--Frutas--*")
                var contadorFruta = 0
                tienda.frutas.forEach{ contadorFruta += 1;println( "$contadorFruta." + it.nombreFruta) }
                println("*--------------*")
                println("Número de la fruta a eliminar: ")
                var frutaEliminar = input.nextLine().toInt()
                val nombreFrutaEliminada = tienda.eliminarFruta(frutaEliminar)
                println("Se elimino la fruta $nombreFrutaEliminada con exito")
            }
            4 -> {
                println("*Añadir Cantidad Fruta*")
                println("*--Frutas--*")
                var contadorFruta = 0
                tienda.frutas.forEach{ contadorFruta += 1;println( "$contadorFruta." + it.nombreFruta + " Cantidad: ${it.cantidad}") }
                println("*--------------*")
                println("Número de la fruta a añadir cantidad: ")
                var frutaAñadir = input.nextLine().toInt()
                println("Cantidad a añadir: ")
                var cantidadAñadir = input.nextLine().toInt()
                val añadirFrutas = tienda.añadirCantidadFruta(frutaAñadir,cantidadAñadir)
                println(añadirFrutas)
            }
            5 -> {
                var salirCompra = false
                while(!salirCompra) {
                    println("*Comprar Frutas*")
                    println("*--Frutas--*")
                    var contadorFruta = 0
                    tienda.frutas.forEach{ contadorFruta += 1;println( "$contadorFruta." + it.nombreFruta + " Cantidad: ${it.cantidad} Precio: ${it.precio}") }
                    println("*--------------*")
                    println("Número de Fruta para comprar: ")
                    var numeroFrutaComprar = input.nextLine().toInt()
                    println("Cantidad de Fruta para comprar: ")
                    var cantidadFrutaComprar = input.nextLine().toInt()
                    var precioActual = tienda.comprarFruta(numeroFrutaComprar, cantidadFrutaComprar)
                    println("Valor Actual de la Compra: $precioActual$ ")
                    println("Desea continuar: Y/N")
                    var respuesta = input.nextLine()
                    if (respuesta.equals("N") || respuesta.equals("n")){
                        salirCompra = true
                    }
                }
                var totalVenta = tienda.finalizarCompra()
                println("El valor total es de: $totalVenta$")
            }
            0 -> {
                salir = true
            }
        }
    }



//    val tienda1 = Tienda("Fruteria Thomas","Quito",1234567890,983359387,"Thomas Tapia")
//    val fruta1 = Fruta("Pera",0.25,10,"Manzana")
//    val fruta2 = Fruta("Manzana",0.15,5,"Manzana")
//    tienda1.añadirFruta(fruta1)
//    tienda1.añadirFruta(fruta2)
//    tienda1.frutas.forEach{println(it.nombreFruta +" " + it.disponibilidad)}
//    tienda1.comprarFruta(1,10)
//    tienda1.comprarFruta(2,2)
//    tienda1.finalizarCompra()
//    tienda1.añadirCantidadFruta(1,1)
//    tienda1.comprarFruta(1,3)
//    tienda1.comprarFruta(2,1)
//    tienda1.finalizarCompra()

//    tienda1.eliminarFruta(2)
//    tienda1.frutas.forEach{println(it.nombreFruta +" " + it.disponibilidad)}

}