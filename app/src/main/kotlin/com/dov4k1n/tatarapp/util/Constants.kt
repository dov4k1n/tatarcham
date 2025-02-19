package com.dov4k1n.tatarapp.util

val tatarAlphabet = "аәбвгдеёжҗзийклмнңоөпрстуүфхһцчшщъыьэюя"
val personalPronouns = listOf("мин", "син", "ул", "без", "сез", "алар")

val hardVowels = listOf('а', 'о', 'у', 'ы')
val softVowels = listOf('ә', 'ө', 'ү', 'и', 'э')
val mutableVowels = listOf('е', 'ю', 'я')
val vowels = softVowels + hardVowels + mutableVowels

val unvoicedConsonants = listOf('п', 'ф', 'к', 'т', 'ш', 'с', 'ч', 'щ', 'ц', 'х', 'һ')
/*val voicedConsonants = listOf('б', 'в', 'г', 'д', 'ж', 'з')
val sonorousConsonants = listOf('м', 'н', 'ң')
val consonants = listOf(
    'б', 'в', 'г', 'д', 'ж', 'җ', 'з', 'й', 'к', 'л', 'м', 'н',
    'ң', 'п', 'р', 'с', 'т', 'ф', 'х', 'һ', 'ц', 'ч', 'ш', 'щ'
)*/

val diftongs = mapOf(
    "йа" to "я",
    "йә" to "я",
    "йя" to "я",
    "йу" to "ю",
    "йү" to "ю",
    "йю" to "ю",
    "йе" to "е",
    "йэ" to "е",
    "йы" to "е",
)

enum class VowelType {
    SOFT, HARD
}