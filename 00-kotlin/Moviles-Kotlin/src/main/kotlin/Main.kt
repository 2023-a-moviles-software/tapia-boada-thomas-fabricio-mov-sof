import java.util.*

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
}
    //println(esSoltero)
    //println(coqueteo)

    // void -> unit

    //nombre de la funcion, nombre variable, tipo variable entrada, tipo variable salida
    fun imprimirNombre (nombre: String) : Unit {
        println("Nombre :${nombre}") //template Strings
    }

    fun calcularSueldo (
      sueldo : Double, //Requerido
      tasa : Double = 12.00, //Opcional (defecto)
      bonoEspecial : Double? = null, //Opcion null -> nullable
    ) : Double{
        //Int -> Int?(nullable)
        //String -> String?(nullable)
        //Date -> Date?(nullable)
        if (bonoEspecial == null){
            return sueldo * (100/tasa)
        }else{
            return sueldo * (100/tasa) + bonoEspecial
        }
    }

    abstract class NumerosJava{
        protected val numeroUno : Int
        private val numeroDos : Int
    constructor(
        uno: Int,
        dos: Int
    ){ //Bloque de codigo del constructor
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializado")
        }
    }

    abstract class Numeros(//Constructor PRIMARIO
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


    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
