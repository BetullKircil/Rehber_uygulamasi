package com.betulkircil.rehberuygulamasi

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.betulkircil.rehberuygulamasi.entity.Kisiler

@Dao
interface KisilerDao {
    @Query("SELECT * FROM kisiler")
    suspend fun TumKisileriGetir(): List<Kisiler>

    @Insert
    suspend fun KisiEkle(kisiler: Kisiler)

    @Update
    suspend fun KisiGuncelle(kisiler: Kisiler)

    @Delete
    suspend fun KisiSil(kisiler: Kisiler)

    @Query("SELECT * FROM kisiler WHERE kisi_ad like '%' || :arananKelime|| '%'")
    suspend fun KisiAra(arananKelime: String): List<Kisiler>
}