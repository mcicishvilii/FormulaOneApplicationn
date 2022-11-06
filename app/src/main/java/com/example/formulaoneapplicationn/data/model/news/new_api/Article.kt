package com.example.formulaoneapplicationn.data.model.news.new_api


import com.example.formulaoneapplicationn.domain.model.ArticleDomain
import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("author")
    val author: String? = "",
    @SerializedName("content")
    val content: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("publishedAt")
    val publishedAt: String = "",
    @SerializedName("source")
    val source: Source = Source(),
    @SerializedName("title")
    val title: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("urlToImage")
    val urlToImage: String? = ""
)

fun Article.toArticleDomain(): ArticleDomain {
    return ArticleDomain(
        content, description, publishedAt, title, urlToImage,url
    )
}