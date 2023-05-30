import java.util.*
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
    println("Hello World!")

    //INMUTABLE (No se reasign "=")
    val inmutable: String = "Thomas";
    // inmutable = "Thomas";

    //MUTABLE (Re Asignar)
    var mutable: String = "Fabricio";
    mutable = "Thomas";


    //VAL > VAR Minimizar errores

    //Duck Typing
    var ejemploVariable = " Thomas Tapia ";
    val edadEjemplo: Int = 12;
    ejemploVariable.trim() //Elimina espacios al inicio y final

    //Variables primitivas
    val nombreProfesor: String = "Adrian Eguez"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'C'
    val mayorEdad: Boolean = true
    //Clases Java (Se puede usar mientras este importado)
    val fechaNacimiento: Date = Date()

    //SWITCH
    val estdoCivilWhen = "C"
    when (estdoCivilWhen) {
        ("C") -> {  // El parentesis es opcional
            println("Casado")
        }

        "S" -> { //Utiliza arrow functions
            println("Soltero")
        }

        else -> {
            println("No sabemos")
        }
    }

    val esSoltero = (estdoCivilWhen == "S")
    val coqueteo = if (esSoltero) "Si" else "No"

    calcularSueldo(10.00)
    calcularSueldo(10.00, 12.00, 20.00)
    calcularSueldo(10.00, bonoEspecial = 20.00)
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00)
    val sumaUno = Suma(1, 1)
    val sumaDos = Suma(null, 1)
    val sumaTres = Suma(1, null)
    val sumaCuatro = Suma(null, null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)

    //ARREGLOS

    //Tipos de arreglos

    //Arreglo Estatico
    val arregloEstatico : Array<Int> = arrayOf<Int>(1,2,3)
    print(arregloEstatico)

    //Arreglo Dinamico
    val arregloDinamico : ArrayList<Int> = arrayListOf<Int>(1,2,3,4,5,6,7,8,9,10)
    print(arregloDinamico)

    //OPERADORES -> Sirve para los arreglos estaticos y dinamicos

    //FOR EACH -> Unit
    //Iterar un arreglo

    val respuestaForEach : Unit = arregloDinamico
        .forEach{ valorActual : Int ->
            println("valor Actual : ${valorActual}")
        }
    arregloDinamico.forEach{ println(it)} //it (eso en Ingles) significa el elemento iterado
    print(respuestaForEach)

    arregloEstatico
        .forEachIndexed{indice : Int, valorActual : Int ->
            println("Valor: ${valorActual} Indice: ${indice}")
        }

    //MAP -> Muta el arreglo (Cambia el arreglo)
    // 1) Enviemos el nuevo valor de la iteracion
    // 2) Nos devuelve es un NUEVO ARREGLO con los valores modificados

    val respuestaMap : List<Double> = arregloDinamico
        .map {valorActual : Int ->
            return@map valorActual.toDouble() + 100.00
        }

    print(respuestaMap)
    val respuestaMapDos = arregloDinamico.map {it + 15}
    print(respuestaMapDos)

    //FILTER -> Filtrar el Arreglo
    // 1) Devolver una expresion (TRUE o FALSE)
    // 2) Nuevo Arreglo filtrado

    val respuestaFilter : List<Int> = arregloDinamico
        .filter { valorActual : Int ->
            val mayoresACinco : Boolean = valorActual > 5 //Expresion Condicion
            return@filter mayoresACinco
        }

    val respuestaFilterDos = arregloDinamico.filter { it <= 5 }
    println(respuestaFilter)
    println(respuestaFilterDos)

    //OR AND
    //OR -> ANY (Alguno cumple?)
    //AND -> ALL (Todos Cumple?)

    val respuestaAny : Boolean = arregloDinamico
        .any{valorActual : Int ->
            return@any (valorActual > 5)
        }
    println(respuestaAny) //True

    val respuestaAll : Boolean = arregloDinamico
        .all { valorActual : Int ->
            return@all (valorActual > 5)
        }
    println(respuestaAll) //False

    //REDUCE -> Valor Acumulado
    //Valor acumulado = 0 (Siempre 0 en lenguaje Kotlin)
    // [1, 2, 3, 4, 5] -> Sumeme todos los valores agregados
    // ValorIteracion1 = valorEmpieza + 1 = 0 + 1 = 1 -> Iteracion1
    // ValorIteracion2 = valorIteracion1 + 2 = 1 + 2 = 3 -> Iteracion2
    // ValorIteracion3 = valorIteracion2 + 3 = 3 + 3 = 6 -> Iteracion3
    // ValorIteracion4 = valorIteracion3 + 4 = 6 + 4 = 10 -> Iteracion4
    // ValorIteracion5 = valorIteracion4 + 5 = 10 + 5 = 15 -> Iteracion5

    val respuestaReduce : Int = arregloDinamico
        .reduce {// acumulado = 0 -> SIEMPRE EMPIEZA EN 0
            acumulado : Int, valorActual : Int ->
            return@reduce (acumulado + valorActual) // -> Logica negocio
        }
    println(respuestaReduce) //78


}
//println(esSoltero)
//println(coqueteo)

// void -> unit

//nombre de la funcion, nombre variable, tipo variable entrada, tipo variable salida
fun imprimirNombre(nombre: String): Unit {
    println("Nombre :${nombre}") //template Strings
}

fun calcularSueldo(
    sueldo: Double, //Requerido
    tasa: Double = 12.00, //Opcional (defecto)
    bonoEspecial: Double? = null, //Opcion null -> nullable
): Double {
    //Int -> Int?(nullable)
    //String -> String?(nullable)
    //Date -> Date?(nullable)
    if (bonoEspecial == null) {
        return sueldo * (100 / tasa)
    } else {
        return sueldo * (100 / tasa) + bonoEspecial
    }
}

abstract class NumerosJava {
    protected val numeroUno: Int
    private val numeroDos: Int

    constructor(
        uno: Int,
        dos: Int
    ) { //Bloque de codigo del constructor
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializado")
    }
}

abstract class Numeros(
//Constructor PRIMARIO
    // Ejemplo:
    // uno: Int , (Parametro (sin modificador de acceso))
    // private var uno: Int, //Propuedad Publica clase numeros.uno
    // var uno: Int, //Propiedad de la clase (por defecto es Public)
    // public var uno : Int,
    protected val numeroUno: Int, //Propiedad de la clase protected numeros.numeroUno
    protected val numeroDos: Int, //Propiedad de la clase protected numeros.numeroDos
) {
    // var cedula: String = "" (public por defecto)
    // private valorCalculado: Int = 0 (private)
    init {// bloque constructor primario
        this.numeroUno; this.numeroDos; //this es opcional
        numeroUno; numeroDos; //sin el this, es lo mismo
        println("Inicializando")
    }
}

class Suma(
    //Constructor Primario Suma
    unoParametro: Int, //Parametro
    dosParametro: Int, //Parametro
) : Numeros(unoParametro, dosParametro) {// Extendiendo y mandando los parametros (super)

    init { //Constructor primario
        this.numeroUno
        this.numeroDos
    }

    constructor(
        //Segundo constructor
        uno: Int?, //Parametro
        dos: Int,  //Parametro
    ) : this(
        if (uno == null) 0 else uno, dos
    )

    constructor(
// Segundo Constructor)
        uno: Int,
        dos: Int?,
    ) : this(
        uno, if (dos == null) 0 else dos,
    )

    constructor(
// Tercer Constructor
        uno: Int?,
        dos: Int?,
    ) : this(
        if (uno == null) 0 else uno,
        if (dos == null) 0 else dos,
    )

    public fun sumar(): Int {
        val total = numeroUno + numeroDos
        agregarHistorial(total)
        return total
    }

    companion object { // Atributos y medoso "Compartidos" Singletons o Static de esta clase
        // Todas las instancias de esta clase comparten estos atributos y metodos
        //dentro del companion Object
        val pi = 3.14

        fun elevarAlCuadrado(num: Int): Int {
            return num * num
        }

        val historialSumas = ArrayList<Int>()
        fun agregarHistorial(valorNuevaSuma: Int) {
            historialSumas.add(valorNuevaSuma)
        }


    }

}

// Try adding program arguments via Run/Debug configuration.
// Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
