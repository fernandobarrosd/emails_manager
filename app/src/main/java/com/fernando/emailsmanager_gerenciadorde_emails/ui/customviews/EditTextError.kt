package com.fernando.emailsmanager_gerenciadorde_emails.ui.customviews

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.InputType
import android.util.AttributeSet
import android.widget.EditText
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import com.fernando.emailsmanager_gerenciadorde_emails.R
import com.fernando.emailsmanager_gerenciadorde_emails.databinding.CustomViewEditTextErrorBinding
import com.fernando.emailsmanager_gerenciadorde_emails.ui.extensions.getLayoutInflater
import com.fernando.emailsmanager_gerenciadorde_emails.ui.extensions.setVisibilityToInvisible
import com.fernando.emailsmanager_gerenciadorde_emails.ui.extensions.setVisibilityToVisible
import javax.annotation.Nullable

class EditTextError(
    context: Context,
    @Nullable attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    private var binding: CustomViewEditTextErrorBinding = CustomViewEditTextErrorBinding.inflate(
        context.getLayoutInflater(),
        this,
        true
    )

    init {

        setupLayout(attrs)
    }

    private fun setupLayout(attrs: AttributeSet?) {
        context.withStyledAttributes(attrs, R.styleable.EditTextError) {
            val labelText = getString(R.styleable.EditTextError_labelText) ?: ""
            val hasError = getBoolean(R.styleable.EditTextError_error, false)
            val errorText = getString(R.styleable.EditTextError_errorText) ?: ""
            val iconDrawable = getDrawable(R.styleable.EditTextError_iconSrc)
            val inputType = getInt(R.styleable.EditTextError_android_inputType, InputType.TYPE_CLASS_TEXT)

            binding.editText.setDrawableStart(iconDrawable)
            setLabelText(labelText)
            binding.editTextError.text = errorText
            binding.editText.inputType = inputType

            if (hasError) {
                showError()
            }
            else {
                hideError()
            }
        }
    }

    private fun setLabelText(labelText: String) {
        binding.editText.hint = labelText
    }

    private fun EditText.setDrawableStart(drawable: Drawable?) {
        setCompoundDrawablesWithIntrinsicBounds(
            drawable,
            null,
            null,
            null
        )
    }

    fun hideError() {
        with(binding) {
            editTextError.setVisibilityToInvisible()
            editTextError.text = ""
            editText.setBackgroundResource(R.drawable.background_edit_text)
        }
    }

    fun showError() {
        with(binding) {
            editTextError.setVisibilityToVisible()
            editText.setBackgroundResource(R.drawable.background_edit_text_error)
        }
    }

    fun setErrorText(@StringRes errorResId: Int) {
        binding.editTextError.setText(errorResId)
    }

    fun getText() : Editable? {
        return binding.editText.text
    }
}