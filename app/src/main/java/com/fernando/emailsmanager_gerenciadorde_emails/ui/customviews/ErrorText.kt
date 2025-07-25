package com.fernando.emailsmanager_gerenciadorde_emails.ui.customviews

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.fernando.emailsmanager_gerenciadorde_emails.R
import javax.annotation.Nullable

class ErrorText(context: Context, @Nullable attributeSet: AttributeSet?)
    : AppCompatTextView(context, attributeSet) {

        init {
            setTextColor(context.getColor(R.color.red))
        }
}