package com.damiankwasniak.interview.extensions

import android.widget.EditText

fun EditText.moveCursorToTheEnd() {
    this.setSelection(this.text.length)
}