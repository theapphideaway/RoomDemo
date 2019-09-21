package com.ianschoenrock.roomdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.book_list_item.view.*

class BookAdapter(val context: Context): RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    var bookList: List<Book> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(context).inflate(R.layout.book_list_item, parent, false)
        return BookViewHolder(inflater)
    }

    override fun getItemCount() = bookList.count()

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]
        holder.setData(book)
    }

    fun setBooks(books: List<Book>) {
        bookList = books
        notifyDataSetChanged()
    }

    inner class BookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun setData(book: Book){
            itemView.tv_author.text = book.author
            itemView.tv_title.text = book.bookName
        }
    }
}