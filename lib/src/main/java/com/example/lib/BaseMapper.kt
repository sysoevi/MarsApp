package com.example.lib

abstract class BaseMapper<From, To> {

    abstract fun map(entity: From): To

    abstract fun map(entities:List<From>):List<To>

}