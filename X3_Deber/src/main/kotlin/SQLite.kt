import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement

class SQLite {
    val kClass = Class.forName("org.sqlite.JDBC").kotlin
    private val connection : Connection = DriverManager.getConnection("jdbc:sqlite:X3_Deber.db")
    private val statement : Statement = connection.createStatement()

    fun createTable() {
        val checkTableTienda = "SELECT name FROM sqlite_master WHERE type='table' AND name='TIENDA';"
        val checkTableFruta = "SELECT name FROM sqlite_master WHERE type='table' AND name='FRUTA'"

        val tiendaTableExist = statement.executeQuery(checkTableTienda).next()
        val frutaTableExist = statement.executeQuery(checkTableFruta).next()

        if (!tiendaTableExist) {
            val createTiendaTable = """
                CREATE TABLE TIENDA (
                id_tienda           INTEGER   PRIMARY KEY NOT NULL,
                nombre_tienda       VARCHAR(20),
                ruc                 VARCHAR(13),
                telefono            INTEGER,
                nombre_propietario  VARCHAR(50)
                )
            """.trimIndent()
            statement.executeQuery(createTiendaTable)
        }

        if (!frutaTableExist) {
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
            statement.executeQuery(createFrutaTable)
        }
    }
        fun close(){
            statement.close()
            connection.close()
        }

        //Funciones para tiendas
        fun crearTienda(idTienda : Int, nombreTienda:String, ruc:String, telefono:Int, nombrePropietario:String):Boolean {
            val query = """
                INSERT INTO TIENDA (id_tienda, nombre_tienda, ruc, telefono, nombre_propietario)
                VALUES ('$idTienda','$nombreTienda','$ruc','$telefono','$nombrePropietario')
            """.trimIndent()
            try {
                statement.executeQuery(query)
                return true
            } catch (e: Exception){
                return false
            }
        }

        fun consultarTiendas() : ArrayList<Tienda>{
            var tiendas = ArrayList<Tienda>()
            val query = """
                SELECT * FROM TIENDA
            """.trimIndent()
            try {
                val result = statement.executeQuery(query)
                while (result.next()){
                    tiendas.add(Tienda(result.getInt(1),result.getString(2),
                        result.getString(3), result.getInt(4),result.getString(5)))
                }
                return tiendas
                } catch (e: Exception){
                return  tiendas
                }
        }

        fun eliminarTiendaPorId(idTienda: Int):Boolean {
            val query = """
                DELETE FROM TIENDA
                WHERE id_tienda = '$idTienda'
            """.trimIndent()
            try {
                statement.executeQuery(query)
                return true
            } catch (e : Exception){
                return false
            }
        }

        fun actualizarTienda(idTienda: Int, nuevoNombreTienda: String?, nuevoRuc: String?, nuevoTelefono: Int?, nuevoNombrePropietario: String?): Boolean {
            val query = """
                UPDATE TIENDA
                SET nombre_tienda = ?, ruc = ?, telefono = ?, nombre_propietario = ?
                WHERE id_tienda = ?
            """.trimIndent()
            try {
                val preparedStatement = connection.prepareStatement(query)
                preparedStatement.setString(1,nuevoNombreTienda)
                preparedStatement.setString(2,nuevoRuc)
                preparedStatement.setInt(3,nuevoTelefono ?: 0)
                preparedStatement.setString(4,nuevoNombrePropietario)
                preparedStatement.setInt(5, idTienda)

                preparedStatement.executeUpdate()
                preparedStatement.close()
                return true
            } catch (e: Exception){
                return false
            }
        }


}