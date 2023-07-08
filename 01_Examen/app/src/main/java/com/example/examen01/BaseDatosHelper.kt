package com.example.examen01

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDatosHelper(contexto : Context?):
    SQLiteOpenHelper(
    contexto,
    "tiendas", //nombre bdd
    null,
    1
    ){
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaTiendas =
            """
                CREATE TABLE TIENDAS(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombreTienda VARCHAR(50),
                direccion VARCHAR(50),
                ruc VARCHAR(15),
                propietario VARCHAR(50)
                )
            """.trimIndent()
        db!!.execSQL(scriptSQLCrearTablaTiendas)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun crearTienda(
        nombre: String,
        direccion: String,
        ruc: String,
        propietario : String

    ):Boolean{
        val baseDatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombreTienda", nombre)
        valoresAGuardar.put("direccion", direccion)
        valoresAGuardar.put("ruc", ruc)
        valoresAGuardar.put("propietario",propietario)

        val resultadoGuardar = baseDatosEscritura
            .insert(
                "TIENDAS", //nombre tabla
                null,
                valoresAGuardar, //valores
            )
        baseDatosEscritura.close()
        return if (resultadoGuardar.toInt() === 1) false else true
    }

    fun eliminarTienda(id: Int):Boolean {
        val conexionEscritura = writableDatabase
        // where ID = ?
        val parametrosConsultaDelete = arrayOf(id.toString())
        val resultadoEliminacion = conexionEscritura.delete(
            "TIENDAS",// Nombre tabla
            "id=?", // consulta where
            parametrosConsultaDelete
        )
        conexionEscritura.close()
        return if(resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarTienda(id: Int, nombre: String, descripcion: String, direccion: String, ruc : String, propietario: String):Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("direccion", direccion)
        valoresAActualizar.put("ruc", ruc)
        valoresAActualizar.put("propietario",propietario)
        // where ID = ?
        val parametrosConsultaActualizar = arrayOf(id.toString())
        val resultadoActualizar = conexionEscritura.update(
            "TIENDAS",// Nombre tabla
            valoresAActualizar, //valores
            "id=?", // consulta where
            parametrosConsultaActualizar
        )
        conexionEscritura.close()
        return if(resultadoActualizar.toInt() == -1) false else true
    }


    fun consultarTiendaPorId(id: Int): Tienda{
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM TIENDA WHERE ID = ?
        """.trimIndent()
        val parametrosConsutlaLectura = arrayOf(id.toString())
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura, //Consulta
            parametrosConsutlaLectura //Parametros
        )
        //logica busqueda
        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        val usuarioEncontrado = Tienda("","","","")
        val arreglo = arrayListOf<Tienda>()
        if (existeUsuario){
            do{
                val id = resultadoConsultaLectura.getInt(0) //Indice 0
                val nombre= resultadoConsultaLectura.getString(1)
                val direccion = resultadoConsultaLectura.getString(2)
                val ruc = resultadoConsultaLectura.getString(3)
                val propietario = resultadoConsultaLectura.getString(4)

                if (id != null){
                    usuarioEncontrado.id = id
                    usuarioEncontrado.nombreTienda = nombre
                    usuarioEncontrado.direccion = direccion
                    usuarioEncontrado.ruc = ruc
                    usuarioEncontrado.propietario = propietario
                }
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado


    }

    fun mostrarDatos() : ArrayList<Tienda>? {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """SELECT * FROM TIENDA""".trimIndent()
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura, null) //Consulta)
        //logica busqueda
        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        val arreglo = arrayListOf<Tienda>()
        if (existeUsuario){
            val usuarioEncontrado = Tienda("","","","")
            do{
                val id = resultadoConsultaLectura.getInt(0) //Indice 0
                val nombre= resultadoConsultaLectura.getString(1)
                val direccion = resultadoConsultaLectura.getString(2)
                val ruc = resultadoConsultaLectura.getString(3)
                val propietario = resultadoConsultaLectura.getString(4)

                if (id != null){
                    usuarioEncontrado.id = id
                    usuarioEncontrado.nombreTienda = nombre
                    usuarioEncontrado.direccion = direccion
                    usuarioEncontrado.ruc = ruc
                    usuarioEncontrado.propietario = propietario
                    arreglo.add(usuarioEncontrado)
                }
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return arreglo
    }


}
