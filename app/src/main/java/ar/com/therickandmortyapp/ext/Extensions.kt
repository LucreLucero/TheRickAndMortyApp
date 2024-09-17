package ar.com.therickandmortyapp.ext

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavGraph
import androidx.navigation.fragment.findNavController

fun Fragment.navigate(@IdRes resId: Int, args: Bundle? = null) {
    val destinationId =
        findNavController().currentDestination?.getAction(resId)?.destinationId.orEmpty()
    findNavController().currentDestination?.let { node ->
        val currentNode = when (node) {
            is NavGraph -> node
            else -> node.parent
        }
        if (destinationId != 0) {
            currentNode?.findNode(destinationId)?.let { findNavController().navigate(resId, args) }
        }
    }
}

fun Fragment.navigate(direction: NavDirections) {
    navigate(direction.actionId, direction.arguments)
}

fun Int?.orEmpty(default: Int = 0): Int {
    return this ?: default
}
