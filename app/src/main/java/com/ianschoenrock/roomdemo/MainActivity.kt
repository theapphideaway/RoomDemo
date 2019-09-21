package com.ianschoenrock.roomdemo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val bookListAdapter = BookAdapter(this)
        rv_book_recycler_view.adapter = bookListAdapter
        rv_book_recycler_view.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener { view ->
            val intent = Intent(this, DetailActivity::class.java)
            startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE)
        }

        fab_delete.setOnClickListener {
            viewModel.deleteAll()
            bookListAdapter.notifyDataSetChanged()
        }

        viewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)

        viewModel.allBooks.observe(this, Observer{books ->
            books?.let{
                bookListAdapter.setBooks(books)
            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            val id = UUID.randomUUID().toString()
            val author = data?.getStringExtra(DetailActivity.AUTHOR)
            val title = data?.getStringExtra(DetailActivity.BOOK)

            val book = Book(id, author!!, title!!)

            viewModel.insert(book)

            Toast.makeText(applicationContext,
                "Saved",
                Toast.LENGTH_LONG).show()
        }
    }

    companion object{
        private const val NEW_NOTE_ACTIVITY_REQUEST_CODE = 1
    }
}
