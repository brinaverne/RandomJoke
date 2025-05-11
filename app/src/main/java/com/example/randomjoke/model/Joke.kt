package com.example.randomjoke.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Joke")
class Joke {

    @ColumnInfo(name = "type")
    var type: String = ""

    @ColumnInfo(name = "setup")
    var setup: String = ""

    @ColumnInfo(name = "punchline")
    var punchline: String = ""

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}