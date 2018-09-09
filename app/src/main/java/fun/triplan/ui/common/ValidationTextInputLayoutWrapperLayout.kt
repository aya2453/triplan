package `fun`.triplan.ui.common

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

class ValidationTextInputLayoutWrapperLayout @JvmOverloads constructor(context: Context,
                                                                       attrs: AttributeSet? = null,
                                                                       defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var validationTextInputLayouts: ArrayList<ValidationTextInputLayout> = arrayListOf()


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        for (i in 0..childCount) {
            val view = getChildAt(i)
            if (view is ValidationTextInputLayout) {
                validationTextInputLayouts.add(view)
            }
        }
        super.onLayout(changed, left, top, right, bottom)
    }

    fun valid(): Boolean = validationTextInputLayouts.all {
        it.validate()
    }
}