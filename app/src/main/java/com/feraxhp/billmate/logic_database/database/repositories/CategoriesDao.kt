package com.feraxhp.billmate.logic_database.database.repositories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.feraxhp.billmate.logic_database.database.entities.Categories

@Dao
interface CategoriesDao {
    @Query("SELECT * FROM Categories")
    suspend fun getAllCategories(): List<Categories>

    @Query("SELECT * FROM Categories WHERE id = :id")
    suspend fun getCategorieById(id: Long): Categories

    @Insert
    suspend fun insertCategorie(Categorie: Categories)

    @Insert
    suspend fun insertCategories(Categories: List<Categories>)

    @Update
    suspend fun updateCategorie(Categorie: Categories)

    @Query("DELETE FROM Categories WHERE id = :id")
    suspend fun removeCategorie(id: Long)
}
