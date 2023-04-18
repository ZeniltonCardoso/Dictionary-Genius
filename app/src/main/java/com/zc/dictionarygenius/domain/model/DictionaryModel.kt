package com.zc.dictionarygenius.domain.model

data class DictionaryModel(
    var word : String = "",
    var phonetics : List<Phonetics>? = listOf(),
    var meanings : List<Meanings>? = listOf(),
    var license : License? = License(),
    var sourceUrls: List<String> = listOf("")
)

data class Phonetics(
    var text : String = "",
    var audio : String = "",
    var sourceUrl : String = "",
    var license : License? = License()
)

data class License(
    var name : String = "",
    var url : String = ""
)

data class Meanings(
    var partOfSpeech : String = "",
    var definitions : List<Definitions>?,
    var synonyms : List<String> = listOf()
)

data class Definitions(
    var definition : String = "",
    var example : String = ""
)
