package com.ianschoenrock.roomdemo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        btn_save.setOnClickListener {
            val intent = Intent()
            if(TextUtils.isEmpty(et_author.text)||
                    TextUtils.isEmpty(et_book.text)){
                setResult(Activity.RESULT_CANCELED, intent)
            }else{
                val author = et_author.text.toString()
                val book = et_book.text.toString()

                intent.putExtra(AUTHOR, author)
                intent.putExtra(BOOK, book)
                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }
    }

    companion object{
        const val AUTHOR = "new_author"
        const val BOOK = "new_book"
    }
}
