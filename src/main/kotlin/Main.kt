package org.example
import java.io.File

const val PERCENTAGE_HUNDRED = 100

data class Word(
    val originalWord: String,
    val translatedWord: String,
    val correctAnswersCount: Int = 0
)

fun main() {

    val dictionary = loadDictionary()
    var learnedWords: Int
    var learnedPercent: Int

    while (true) {

        print(
            "1 - Учить слова\n" +
                    "2 - Статистика\n" +
                    "0 - Выход\n" +
                    "Ввод: "
        )

        val choseInput = readln()
        when(choseInput) {
            "1" -> println("Вы выбрали учить слова")
            "2" -> {
                learnedWords = dictionary.filter { it.correctAnswersCount >= 3 }.size
                learnedPercent = (learnedWords.toDouble() / dictionary.size * PERCENTAGE_HUNDRED).toInt()
                println(
                    "Общее количество слов в словаре | ${dictionary.size}\n" +
                    "Выучено $learnedWords из ${dictionary.size} слов | $learnedPercent%\n"
                )
            }
            "0" -> return
            else -> println("Неверный выбор. Введите число 1, 2 или 0")
        }

    }

}

fun loadDictionary() : List<Word> {
    val wordsFile = File("words.txt")
    val dictionary = mutableListOf<Word>()
    val fileLines = wordsFile.readLines()

    fileLines.forEach { line ->
        val splitLine = line.split("|")
        dictionary.add(
            Word(
                originalWord = splitLine[0],
                translatedWord = splitLine[1],
                correctAnswersCount = splitLine.getOrNull(2)?.toIntOrNull() ?: 0
            )
        )
    }

    return dictionary
}