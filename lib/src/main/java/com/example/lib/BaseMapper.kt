package com.example.lib

abstract class BaseMapper<From, To> {

    abstract fun map(entity: From): To

    fun map(entities:List<From>):List<To>{
        val list: MutableList<To> = mutableListOf()
        entities.forEach{
            list.add(map(it))
        }
        return list
    }

}