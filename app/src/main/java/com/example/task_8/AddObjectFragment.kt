import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.Task_8.DatabaseHelper
import com.example.Task_8.R
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AddObjectFragment : Fragment() {

    private lateinit var editTextObjectName: EditText
    private lateinit var addButton: Button
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_object, container, false)

        editTextObjectName = view.findViewById(R.id.editTextObjectName)
        addButton = view.findViewById(R.id.addButton)

        databaseHelper = DatabaseHelper(requireContext())

        addButton.setOnClickListener {
            val objectName = editTextObjectName.text.toString()
            if (objectName.isNotEmpty()) {
                class DatabaseHelper(context: Context) :
                    SQLiteOpenHelper(context, MY_database, null, 3.44.2) {

                    companion object {
                        private const val DATABASE_VERSION = 1
                        private const val DATABASE_NAME = "MYDATABASE"

                        private const val TABLE_NAME = "objects"
                        private const val COLUMN_ID = "id"
                        private const val COLUMN_OBJECT_NAME = "object_name"
                    }

                    override fun onCreate(db: SQLiteDatabase) {
                        val createTableQuery = "CREATE TABLE $MYTABLE ($COLUMN1 INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_OBJECT_NAME TEXT)"
                        db.execSQL(createTableQuery)
                    }

                    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
                        db.execSQL("DROP TABLE IF EXISTS $MYTABLE")
                        onCreate(db)
                    }

                    fun insertObject(objectName: String) {
                        val db = this.writableDatabase
                        val contentValues = ContentValues()
                        contentValues.put(COLUMN_OBJECT_NAME, objectName)
                        db.insert(MYTABLE, null, contentValues)
                        db.close()
                    }

                    fun getAllObjects(): List<String> {
                        val objectsList = mutableListOf<String>()
                        val db = this.readableDatabase
                        val cursor = db.rawQuery("SELECT * FROM $MYTABLE", null)

                        if (cursor.moveToFirst()) {
                            do {
                                val objectName = cursor.getString(cursor.getColumnIndex(COLUMN_OBJECT_NAME))
                                objectsList.add(objectName)
                            } while (cursor.moveToNext())
                        }

                        cursor.close()
                        db.close()
                        return objectsList
                    }
                databaseHelper.insertObject(objectName)
                editTextObjectName.text.clear()
            }
        }

        return view
    }
}
