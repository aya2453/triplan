package `fun`.triplan.ui.common

import `fun`.triplan.R
import android.content.Context
import android.util.AttributeSet
import androidx.annotation.StringRes
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern


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
    private val requiredText: String = context.getString(R.string.validation_required)
    private var errorText: String? = ""
    lateinit var validateType: ValidationType

    private fun init(context: Context, attrs: AttributeSet?) {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it,
                    R.styleable.ValidationTextInputLayout)
            validateType = ValidationType.valueOf(typedArray.getInt(R.styleable.ValidationTextInputLayout_validationType, ValidationType.NULL.index))
            errorText = typedArray.getString(R.styleable.ValidationTextInputLayout_errorText)
            required = typedArray.getBoolean(R.styleable.ValidationTextInputLayout_required, false)
            typedArray.recycle()
        }
    }

    fun validate(): Boolean {
        val text = editText?.text?.toString()
        val validationResult = ValidationType.validate(text, required, validateType, requiredText,
                context.getString(validateType.defaultErrorText), errorText)

        return when (validationResult) {
            ValidationResult.Success -> {
                error = null
                true
            }
            is ValidationResult.Failed -> {
                error = validationResult.errorText
                false
            }
        }
    }


    enum class ValidationType(val index: Int, @StringRes val defaultErrorText: Int) {
        EMAIL(0, R.string.validation_mail) {
            override fun validate(value: String): Boolean {
                return Pattern.compile(
                        "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
                ).matcher(value).matches()
            }
        },
        PASSWORD(1, R.string.validation_password) {
            override fun validate(value: String): Boolean {
                return Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=\\S+\$).{6,10}").matcher(value).matches()
            }
        },
        NULL(-1, -1) {
            override fun validate(value: String): Boolean {
                return true
            }
        };

        abstract fun validate(value: String): Boolean

        companion object {
            fun valueOf(index: Int): ValidationType {
                return values().associateBy(ValidationType::index)[index] ?: NULL
            }

            fun validate(text: String?, required: Boolean, validateType: ValidationType, requiredText: String, defaultErrorText: String, errorText: String?): ValidationResult {
                if (!required && text.isNullOrBlank()) {
                    return ValidationResult.Success
                }

                // 必須&&未入力
                if (required && text.isNullOrBlank()) {
                    return ValidationResult.Failed(requiredText)
                }

                // 各種バリデーション
                if (!validateType.validate(text!!)) {
                    val error = if (errorText.isNullOrBlank()) defaultErrorText else errorText
                    return ValidationResult.Failed(error!!)
                }

                return ValidationResult.Success
            }
        }

    }

    sealed class ValidationResult {
        object Success : ValidationResult()
        data class Failed(val errorText: String) : ValidationResult()

    }
}