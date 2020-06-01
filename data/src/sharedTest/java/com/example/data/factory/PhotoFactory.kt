package com.example.data.factory
import com.example.data.entity.PhotoInfo

object PhotoFactory : AbstractFactory<PhotoInfo>() {
    private const val SOME_STRING = "some string"
    override fun getRandomModel(): PhotoInfo {
        return PhotoInfo(countedId, SOME_STRING)
    }
}