package com.example.data.factory

abstract class AbstractFactory<T> {
    abstract fun getRandomModel(): T
    fun getRandomListModel(): List<T>{
        return listOf(getRandomModel(), getRandomModel())
    }
    protected var countedId:Int = 0
    get() = field++
}