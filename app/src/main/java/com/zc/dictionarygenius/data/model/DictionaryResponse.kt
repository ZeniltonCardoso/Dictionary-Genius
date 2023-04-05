package com.zc.dictionarygenius.data.model

import com.google.gson.annotations.SerializedName

data class DictionaryResponse(
    @SerializedName("word")
    var word : String = "",
    @SerializedName("phonetics")
    var phonetics : List<Phonetics>? = listOf(),
    @SerializedName("meanings")
    var meanings : List<Meanings>? = listOf(),
    @SerializedName("license")
    var license : License? = License(),
    @SerializedName("sourceUrls")
    var sourceUrls: List<String> = listOf("")
)

data class Phonetics(
    @SerializedName("text")
    var text : String = "",
    @SerializedName("audio")
    var audio : String = "",
    @SerializedName("sourceUrl")
    var sourceUrl : String = "",
    @SerializedName("license")
    var license : License? = License()
)

data class License(
    @SerializedName("name")
    var name : String = "",
    @SerializedName("url")
    var url : String = ""
)

data class Meanings(
    @SerializedName("partOfSpeech")
    var partOfSpeech : String = "",
    @SerializedName("definitions")
    var definitions : List<Definitions>?,
    @SerializedName("synonyms")
    var synonyms : List<String> = listOf()
)

data class Definitions(
    @SerializedName("definition")
    var definition : String = "",
    @SerializedName("example")
    var example : String = ""
)
