package com.example.formulaone.domain.repository

import com.example.formulaoneapplicationn.domain.model.ArticleDomain

// not using yet. for displaying the full list of current driver standings
interface NewsRepository {
    suspend fun getNews(): List<ArticleDomain>
}