package com.investoteam.investo.utils;

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import com.investoteam.investo.R
import io.github.muddz.styleabletoast.StyleableToast

fun Fragment.showToast(message: Any?) {
    StyleableToast.makeText(requireContext(), "$message", R.style.toastStyle).show()
}

fun View.visibilityGone() {
    this.visibility = View.GONE
}

fun View.visibilityVisible() {
    this.visibility = View.VISIBLE
}

// to prevent multiple clicks on the back button multiple times
val NavHostController.canGoBack: Boolean
    get() = this.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED

fun NavController.navigateSafely(
    @IdRes resID: Int,
    args: Bundle? = null,
    navOptions: NavOptions? = null,
    navExtra: Navigator.Extras? = null
) {
    val action = currentDestination?.getAction(resID) ?: graph.getAction(resID)
    if (action != null && currentDestination?.id != action.destinationId) {
        navigate(resID, args, navOptions, navExtra)
    }
}
