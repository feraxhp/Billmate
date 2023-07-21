package com.feraxhp.billmate.controllers.dependencies

import com.feraxhp.billmate.logic_database.database.entities.Categories

class CategoryStructure(
    val self: Categories,
    val father: CategoryStructure? = null,
    var sons: MutableList<CategoryStructure> = mutableListOf()
){
    val deep: Int = father?.deep?.plus(1)?: 0
    private val maxDeep = 3

    fun getSon(index: Int): Categories {
        return this.sons[index].self
    }
    fun getAllSons(): List<CategoryStructure> {
        return this.sons
    }
    fun getCategorySons(): List<Categories> {
        return this.sons.map { it.self }
    }
    fun addSon(son: Categories) {
        if (this.deep > maxDeep) throw Exception("This category is too deep")
        this.sons.add(CategoryStructure(son, this))
    }
    fun hasSons(): Boolean {
        return this.sons.isNotEmpty()
    }
    fun isTooDeep(): Boolean {
        return this.deep > maxDeep
    }
}