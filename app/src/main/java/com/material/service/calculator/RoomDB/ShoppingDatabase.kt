package com.material.service.calculator.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.material.service.calculator.other.Constants.SHOPPING_DATABASE

@Database(entities =[ShoppingItem::class],
    version = 1
)
abstract class ShoppingDatabase:RoomDatabase() {

    abstract fun shoppingDao():ShoppingDao

    companion object{
        @Volatile
        private var INSTANCE:ShoppingDatabase?=null

        fun getDatabase(context: Context):ShoppingDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShoppingDatabase::class.java,
                    SHOPPING_DATABASE
                ).build()
                INSTANCE=instance
                instance
            }
        }
    }
}