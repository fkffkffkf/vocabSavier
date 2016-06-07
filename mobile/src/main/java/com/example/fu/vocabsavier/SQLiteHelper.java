package com.example.fu.vocabsavier;


        import java.util.LinkedList;
        import java.util.List;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    // database version
    private static final int database_VERSION = 1;
    // database name
    private static final String database_name = "WordDB";
    private static final String table_words = "words";
    private static final String word_id = "id";
    private static final String word_word = "word";
    private static final String word_explain = "explain";

    private static final String[] COLUMNS = { word_id, word_word, word_explain };

    public SQLiteHelper(Context context) {
        super(context, database_name, null, database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_WORD_TABLE = "CREATE TABLE IF NOT EXISTS words ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "word TEXT, " + "explain TEXT )";
        db.execSQL(CREATE_WORD_TABLE);
    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop books table if already exists
        //db.execSQL("DROP TABLE IF EXISTS words");
        //this.onCreate(db);
    }


    public void createWord(word word) {
        // get reference of the BookDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        ContentValues values = new ContentValues();
        values.put(word_word, word.getWord());
        values.put(word_explain, word.getExplain());

        // insert book
        db.insert(table_words, null, values);

        // close database transaction
        db.close();
    }

    public word readWord() {
        // get reference of the BookDB database
        SQLiteDatabase db = this.getReadableDatabase();

        // get book query
        Cursor cursor = db.query("words Order BY RANDOM() LIMIT 1",
                new String[] { "*" }, null, null, null, null, null);
        //Cursor cursor = db.query(table_words, // a. table
        //        COLUMNS, " id != ?", new String[] { String.valueOf(10) }, null, null, "RANDOM() limit 1");


        // if results !=null, parse the first one
        /*
        if (cursor != null)
        {
            cursor.moveToFirst();
        }
        */

        if (cursor.moveToPosition(0) != true) {
            //Log.e(TAG, "moveToPosition return fails, maybe table not created!!!");
            return null;
        }



        word word = new word(cursor.getString(1),cursor.getString(2));
        //word.setId(word.getId()+1);
        //word.setTitle(cursor.getString(1));
        //word.setAuthor(cursor.getString(2));

        return word;
    }

    /*
    public List getAllWords() {
        List books = new LinkedList();

        // select book query
        String query = "SELECT  * FROM " + table_BOOKS;

        // get reference of the BookDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        Book book = null;
        if (cursor.moveToFirst()) {
            do {
                book = new Book();
                book.setId(Integer.parseInt(cursor.getString(0)));
                book.setTitle(cursor.getString(1));
                book.setAuthor(cursor.getString(2));

                // Add book to books
                books.add(book);
            } while (cursor.moveToNext());
        }
        return books;
    }
    */


    // Deleting single book
    public void deleteWord(word word) {

        // get reference of the BookDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // delete book
        db.delete(table_words, word_word + " = ?", new String[] { String.valueOf(word.getWord()) });
        db.close();
    }
}
