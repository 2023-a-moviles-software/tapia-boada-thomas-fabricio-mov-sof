package com.example.movilessoftware2023a

class BBaseDatosMemoria {
    companion object {
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador
                .add(
                    BEntrenador(1,"Thomas","a@a.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(2, "Fabricio","b@b.com")
                    )
            arregloBEntrenador
                .add(
                    BEntrenador(3, "Angeline", "c@c.com")
                )
        }
    }
}