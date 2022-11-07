package com.example.formulaoneapplicationn.data.model.news

import com.example.formulaoneapplicationn.domain.model.NewsDtItemDomain

data class NewsDtItem(
    val imgsrc: String,
    val link: String,
    val shortdesc: String,
    val title: String
)

fun NewsDtItem.toNewsItemDomain(): NewsDtItemDomain {
    return NewsDtItemDomain(
        imgsrc, shortdesc, title
    )
}