package `fun`.triplan.ui.common

import `fun`.triplan.R
import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout


/**
 * バリデーション付きTextInputLayout
 */
class ValidationTextInputLayout @JvmOverloads constructor(context: Context,
                                                          attrs: AttributeSet? = null,
                                                          defStyleAttr: Int = 0) : TextInputLayout(context, attrs, defStyleAttr) {
    init {
        init(context, attrs)
    }

    private var required: Boolean = false
    private var requiredText: String? = null

    private fun init(context: Context, attrs: AttributeSet?) {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it,
                    R.styleable.ValidationTextInputLayout)
            required = typedArray.getBoolean(R.styleable.ValidationTextInputLayout_required, false)
            if (required) {
                requiredText = context.getString(R.string.validation_required)
            }
            typedArray.recycle()
        }
    }

    fun validate(): Boolean {
        val text = editText?.text?.toString()
        if (required && text.isNullOrBlank()) {
            error = requiredText!!
            return false
        }
        error = null
        return true
    }
}