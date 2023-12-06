import android.os.Bundle
import android.appcompat.app.AppCompatActivity
import android.fragment.app.Fragment
import android.fragment.app.FragmentManager
import android.fragment.app.FragmentPagerAdapter
import android.viewpager.widget.ViewPager
import com.example.task_8.R
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    
    private fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)

        setupViewPager(viewPager)
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun findViewById(tabLayout: Any): Any {

    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(AddObjectFragment(), "Add Object")
        adapter.addFragment(ViewObjectsFragment(), "View Objects")
        viewPager.adapter = adapter
    }

    private class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val fragmentList = ArrayList<Fragment>()
        private val fragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            fragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return fragmentTitleList[position]
        }
    }
}

private fun Any.onCreate(savedInstanceState: Bundle?) {

}
