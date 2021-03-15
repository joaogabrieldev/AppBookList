package tads.eaj.ufrn.minhaprova

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class BookDBOpenner(context: Context) : SQLiteOpenHelper(context, BookContract.DATABASE_NAME,
null, BookContract.DATABASE_VERSION) {

    val SQL_CREATE_TABLE =
        "CREATE TABLE ${BookContract.BookEntry.TABLE_NAME} " +
                "( ${BaseColumns._ID} INTEGER PRIMARY KEY, " +
                "${BookContract.BookEntry.NOME} TEXT, " +
                "${BookContract.BookEntry.AUTOR} TEXT, " +
                "${BookContract.BookEntry.ANO} INTEGER, " +
                "${BookContract.BookEntry.NOTA} REAL ) "

    val SQL_DROP_TABLE =
        "DROP TABLE ${BookContract.BookEntry.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion != newVersion) {
            db?.execSQL(SQL_DROP_TABLE)
            db?.execSQL(SQL_CREATE_TABLE)
        }
    }

    fun createBook (b:Book){
        val bd: SQLiteDatabase = writableDatabase
        try {
            var values = ContentValues()
            values.put(BookContract.BookEntry.NOME, b.nome)
            values.put(BookContract.BookEntry.AUTOR, b.autor)
            values.put(BookContract.BookEntry.ANO, b.ano)
            values.put(BookContract.BookEntry.NOTA, b.nota)

            bd.insert(BookContract.BookEntry.TABLE_NAME, null, values)
        } finally {
            bd.close()
        }
    }

    fun findBookID (id: Int): Book {
        var db: SQLiteDatabase = readableDatabase
        try {
            var selection = "${BaseColumns._ID} = ?"
            var whereArgs = arrayOf("${id}")
            val cursor:Cursor = db.query(BookContract.BookEntry.TABLE_NAME, null, selection, whereArgs, null, null, null, null)

            cursor.moveToFirst()

            var b = Book()

            b.id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            b.nome = cursor.getString(cursor.getColumnIndex(BookContract.BookEntry.NOME))
            b.autor = cursor.getString(cursor.getColumnIndex(BookContract.BookEntry.AUTOR))
            b.ano = cursor.getInt(cursor.getColumnIndex(BookContract.BookEntry.ANO))
            b.nota = cursor.getFloat(cursor.getColumnIndex(BookContract.BookEntry.NOTA))

            return b

        } finally {
            db.close()
        }
    }

    fun findBookAll(): ArrayList<Book>{
        var db: SQLiteDatabase = readableDatabase

        try {
            val cursor: Cursor = db.query(BookContract.BookEntry.TABLE_NAME, null, null, null, null, null, null, null)
            var listBook = ArrayList<Book>()

            while (cursor.moveToNext()) {
                var b = Book()
                b.id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
                b.nome = cursor.getString(cursor.getColumnIndex(BookContract.BookEntry.NOME))
                b.autor = cursor.getString(cursor.getColumnIndex(BookContract.BookEntry.AUTOR))
                b.ano = cursor.getInt(cursor.getColumnIndex(BookContract.BookEntry.ANO))
                b.nota = cursor.getFloat(cursor.getColumnIndex(BookContract.BookEntry.NOTA))

                listBook.add(b)
            }

            return listBook

        } finally {
            db.close()
        }
    }


}