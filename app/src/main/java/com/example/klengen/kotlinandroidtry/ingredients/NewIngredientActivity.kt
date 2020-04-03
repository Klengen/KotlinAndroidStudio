package com.example.klengen.kotlinandroidtry.ingredients

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.example.klengen.kotlinandroidtry.R

/**
 * Activity for entering a word.
 */

class NewIngredientActivity : AppCompatActivity() {

    private lateinit var editIngredientView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_ingredient)
        editIngredientView = findViewById(R.id.ingredient_name_input)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editIngredientView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val ingredient = editIngredientView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, ingredient)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}