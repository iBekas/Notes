package simple.clever.notes

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class Navigation(private val fragmentManager: FragmentManager) {

    fun addFragment(fragment: Fragment, useBackStack: Boolean) {
        // Открыть транзакцию
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.enter_fragment, R.anim.exit_fragment)
        fragmentTransaction.replace(R.id.main, fragment)
        if (useBackStack) {
            fragmentTransaction.addToBackStack(null)
        }
        // Закрыть транзакцию
        fragmentTransaction.commit()
    }
}
