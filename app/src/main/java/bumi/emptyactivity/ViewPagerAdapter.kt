package bumi.emptyactivity

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.gms.dynamic.SupportFragmentWrapper

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                BioFragment()
            }
            1 -> {
                MuroFragment()
            }

            else -> BioFragment()
        }!!
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return 5
    }
}