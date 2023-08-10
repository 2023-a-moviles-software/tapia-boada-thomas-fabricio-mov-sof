import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement

class SQLite {
    val kClass = Class.forName("org.sqlite.JDBC").kotlin
    private val connection : Connection = DriverManager.getConnection("jdbc:sqlite:X3_Deber.db")
    private val statement : Statement = connection.createStatement()

    fun createTable(){
        val checkTableTienda = "SELECT name FROM sqlite_master WHERE type='table' AND name='TIENDA';"
        val checkTableFruta = "SELECT name FROM sqlite_master WHERE type='table' AND name='FRUTA'"

        val tiendaTableExist = statement.executeQuery(checkTableTienda).next()
        val frutaTableExist = statement.executeQuery(checkTableFruta).next()

        if (!tiendaTableExist){
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

        if (!frutaTableExist){
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

        fun close(){
            statement.close()
            connection.close()
        }

    }
}