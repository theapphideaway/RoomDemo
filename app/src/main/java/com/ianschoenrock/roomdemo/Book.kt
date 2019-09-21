package com.ianschoenrock.roomdemo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(@PrimaryKey
                val id: String,
                @ColumnInfo(name = "author")
                val author: String,
                val bookName: String)