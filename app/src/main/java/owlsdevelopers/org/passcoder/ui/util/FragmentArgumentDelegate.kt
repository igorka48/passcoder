package owlsdevelopers.org.passcoder.ui.util

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment


class FragmentArgumentDelegate<T : Any> : kotlin.properties.ReadWriteProperty<Fragment, T> {

    var value: T? = null

    override operator fun getValue(thisRef: Fragment, property: kotlin.reflect.KProperty<*>): T {
        if (value == null) {
            val args = thisRef.arguments ?: throw IllegalStateException("Cannot read property ${property.name} if no arguments have been set")
            @Suppress("UNCHECKED_CAST")
            value = args.get(property.name) as T
        }
        return value ?: throw IllegalStateException("Property ${property.name} could not be read")
    }

    override operator fun setValue(thisRef: Fragment, property: kotlin.reflect.KProperty<*>, value: T) {
        var args = thisRef.arguments
        if (args == null) {
            args = Bundle()
            thisRef.arguments = args
        }

        val key = property.name

        args.putAll(bundleOf(key to value))
    }
}