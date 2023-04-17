package com.material.service.calculator.RoomDB

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.material.service.calculator.other.Constants.ENTITY_NAME

@Entity(tableName = ENTITY_NAME)
data class ShoppingItem(var name:String, var amount:Int, var price:Float, var imageUrl:String,
                        @PrimaryKey(autoGenerate = true)
                        val id:Int?=null)
