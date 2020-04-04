package com.example.data.store.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entity.PhotoInfo
import io.reactivex.Single
import retrofit2.http.DELETE

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photoinfo")
    fun getAllPhotos(): Single<List<PhotoInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(list: List<PhotoInfo>)

    @Query("SELECT * FROM photoinfo ORDER BY ROWID ASC LIMIT 1")
    fun getFirstPhotoInfo(): Single<PhotoInfo>

    @Query("DELETE FROM photoinfo")
    fun clearTable()

}