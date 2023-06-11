
import java.util.Scanner;
fun main(args: Array<String>) {

    //Variable de opciones
    var opcion : Int
    var salir = false
    val input = Scanner(System.`in`) //Scanner datos por consola

    println("Ingrese el nombre de la tienda: ")
    val nombre = input.nextLine()
    println("Ingrese la ubicacion de la tienda: ")
    val ubicacion = input.nextLine()
    println("Ingrese el RUC: ")
    val RUC = Integer.parseInt(input.nextLine())
    println("Ingrese el telefono: ")
    val telefono = Integer.parseInt(input.nextLine())
    println("Ingrese el nombre del propietario: ")
    val nombrePropietario = input.nextLine()
    val tienda = Tienda(nombre, ubicacion, RUC, telefono, nombrePropietario)

    fun menu() {
        println("*" + nombre + "*")
        println("-------Menu-------")
        println("1. Agregar Fruta")
        println("2. Mostrar Frutas")
        println("3. Eliminar Frutas")
        println("0. Salir")
    }


    while (!salir){
        menu()
        opcion = Integer.parseInt(input.nextLine())
        when (opcion){
            1 -> {
                println("Hola")
            }
            2 -> {

            }
            3 -> {
                
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