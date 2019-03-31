package `fun`.triplan.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import timber.log.Timber

open class BaseFragment : DaggerFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("$this@${this.javaClass.simpleName}: onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.d("$this@${this.javaClass.simpleName}: onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d("$this@${this.javaClass.simpleName}: onViewCreated")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Timber.d("$this@${this.javaClass.simpleName}: onActivityCreated")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onPause() {
        Timber.d("$this@$this@${this.javaClass.simpleName}: onPause")
        super.onPause()
    }

    override fun onDestroyView() {
        Timber.d("$this@${this.javaClass.simpleName}: onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Timber.d("$this@${this.javaClass.simpleName}: onDestory")
        super.onDestroy()
    }

    override fun onDetach() {
        Timber.d("$this@${this.javaClass.simpleName}: onDetach")
        super.onDetach()
    }

    override fun onResume() {
        Timber.d("$this@${this.javaClass.simpleName}: onResume")
        super.onResume()
    }
}