package com.example.examen01

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESQLiteHelper(contexto: Context?):
    SQLiteOpenHelper(
    contexto,
    "Tiendas",
    null,
        1
) {
    override fun onCreate(db: SQLiteDatabase?) {

        val createTiendaTable = """
                CREATE TABLE TIENDA (
                id_tienda           INTEGER   PRIMARY KEY AUTOINCREMENT,
                nombre_tienda       VARCHAR(20),
                ruc                 VARCHAR(13),
                telefono            INTEGER,
                nombre_propietario  VARCHAR(50)
                )
            """.trimIndent()
        db?.execSQL(createTiendaTable)

        val createFrutaTable = """
                CREATE TABLE FRUTA (
                id_fruta        INTEGER    PRIMARY KEY AUTOINCREMENT,
                id_tienda       INTEGER    NOT NULL,
                nombre_fruta    VARCHAR(20),
                precio_fruta    DOUBLE,
                cantidad        INTEGER,
                FOREIGN KEY (id_tienda) REFERENCES TIENDA (id_tienda)
                )
            """.trimIndent()
        db?.execSQL(createFrutaTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    //Funciones Tienda

    fun crearTienda(
        nombreTienda: String,
        ruc: String,
        telefono: Int,
        nombrePropietario: String
    ): Boolean {
        val baseDatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre_tienda", nombreTienda)
        valoresAGuardar.put("ruc", ruc)
        valoresAGuardar.put("telefono", telefono)
        valoresAGuardar.put("nombre_propietario", nombrePropietario)
        val resultadoGuardar = baseDatosEscritura
            .insert(
                "TIENDA", //nombre tabla
                null,
                valoresAGuardar, //valores
            )
        baseDatosEscritura.close()
        return if (resultadoGuardar.toInt() === 1) false else true
    }

    fun eliminarTiendaPorID(idTienda: Int): Boolean {
        val conexionEscritura = writableDatabase
        // where ID = ?
        val parametrosConsultaDelete = arrayOf(idTienda.toString())
        val resultadoEliminacion = conexionEscritura.delete(
            "TIENDA",// Nombre tabla
            "id_tienda=?", // consulta where
            parametrosConsultaDelete
        )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarTiendaPorId(
        idTienda: Int,
        nombreTienda: String,
        ruc: String,
        telefono: Int,
        nombrePropietario: String
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre_tienda", nombreTienda)
        valoresAActualizar.put("ruc", ruc)
        valoresAActualizar.put("telefono", telefono)
        valoresAActualizar.put("nombre_propietario", nombrePropietario)
        // where ID = ?
        val parametrosConsultaActualizar = arrayOf(idTienda.toString())
        val resultadoActualizar = conexionEscritura.update(
            "TIENDA",// Nombre tabla
            valoresAActualizar, //valores
            "id_tienda=?", // consulta where
            parametrosConsultaActualizar
        )
        conexionEscritura.close()
        return if (resultadoActualizar.toInt() == -1) false else true
    }

    fun consultarTiendas() : ArrayList<Tienda>{
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM TIENDA
        """.trimIndent()
        val parametros = null
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura, //Consulta
            parametros
        )
        var tiendas = ArrayList<Tienda>()
        while (resultadoConsultaLectura.moveToNext()){
            tiendas.add(Tienda(resultadoConsultaLectura.getInt(0),resultadoConsultaLectura.getString(1), resultadoConsultaLectura.getString(2),
                resultadoConsultaLectura.getInt(3), resultadoConsultaLectura.getString(4)))
        }
        return tiendas

    }

    fun consultarTiendaPorId(idTienda: Int) : Tienda{
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM TIENDA WHERE id_tienda = ?
        """.trimIndent()
        val parametrosConsutlaLectura = arrayOf(idTienda.toString())
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura, //Consulta
            parametrosConsutlaLectura //Parametros
        )
        //logica busqueda
        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        val tiendaEncontrada = Tienda(0,"","",0,"")
        val arreglo = arrayListOf<Tienda>()
        if (existeUsuario){
            do{
                val id = resultadoConsultaLectura.getInt(0) //Indice 0
                val nombreTienda= resultadoConsultaLectura.getString(1)
                val ruc  = resultadoConsultaLectura.getString(2)
                val telefono = resultadoConsultaLectura.getInt(3)
                val nombrePropietario = resultadoConsultaLectura.getString(4)
                if (id != null){
                    tiendaEncontrada.idTienda = id
                    tiendaEncontrada.nombreTienda = nombreTienda
                    tiendaEncontrada.ruc = ruc
                    tiendaEncontrada.telefono = telefono
                    tiendaEncontrada.nombrePropietario = nombrePropietario
                }
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return tiendaEncontrada
    }


    }