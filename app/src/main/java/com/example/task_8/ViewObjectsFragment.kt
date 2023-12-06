import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Task_8
import com.example.Task_8.DatabaseHelper
import com.example.Task_8.R

private val Any.fragment_view_objects: Any
    get() = Unit

class ViewObjectsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view_objects, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        databaseHelper = DatabaseHelper(requireContext())


        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = Task_8.ObjectAdapter(databaseHelper.getAllObjects())
        adapter.also { it.also { it -> recyclerView.adapter = it } }

        return view
    }
}

private fun LayoutInflater.inflate(
    fragmentViewObjects: Any,
    container: ViewGroup?,
    b: Boolean
): View? {
    TODO("Not yet implemented")
}
