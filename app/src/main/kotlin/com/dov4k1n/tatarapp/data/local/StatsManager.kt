package com.dov4k1n.tatarapp.data.local

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.res.stringResource
import androidx.core.content.FileProvider
import androidx.core.content.edit
import com.dov4k1n.tatarapp.R
import java.io.File
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class StatsManager(context: Context) {



    private val stats = context.getSharedPreferences(
        "stats",
        Context.MODE_PRIVATE
    )

    fun clearAllData(context: Context, msg: String) {
        stats.edit {
            clear()
        }
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
        saveDayFirstLaunch()
    }

    @Throws(IOException::class)
    fun exportSharedPreferences(context: Context): Uri {
        val outputFile = File(context.cacheDir, "stats_backup.json")

        ObjectOutputStream(outputFile.outputStream()).use { oos ->
            oos.writeObject(stats.all)
        }

        return FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", outputFile)
    }

    fun shareSharedPreferencesBackup(context: Context) {
        try {
            val uri = exportSharedPreferences(context)

            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, uri)
                type = "application/octet-stream"
                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            }

            context.startActivity(
                Intent.createChooser(shareIntent, "Share statistics backup")
            )
        } catch (e: Exception) {
            Toast.makeText(context, "Export failed: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    fun importSharedPreferences(context: Context, uri: Uri) {
        try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val prefsMap = ObjectInputStream(inputStream).readObject() as Map<String, *>

            stats.edit {
                clear()
                prefsMap.forEach { (key, value) ->
                    when (value) {
                        is String -> putString(key, value)
                        is Int -> putInt(key, value)
                        is Boolean -> putBoolean(key, value)
                        is Float -> putFloat(key, value)
                        is Long -> putLong(key, value)
                        else -> {} // ignore unsupported types
                    }
                }
            }

            Toast.makeText(context, "Import successful", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(context, "Import failed: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }



    fun getTicksTotal(): Int {
        return stats.getInt(StatsKey.TicksTotal.key, 0)
    }
    private fun saveTicksToTotal(ticks: Int) {
        val prev = getTicksTotal()
        val new = prev + ticks
        stats.edit {
            putInt(StatsKey.TicksTotal.key, new)
        }
    }

    fun getTicks(exercise: StatsKey): Int {
        return stats.getInt(exercise.key, 0)
    }
    fun saveTicks(exercise: StatsKey, ticks: Int) {
        val prev = getTicks(exercise)
        val new = prev + ticks
        stats.edit {
            putInt(exercise.key, new)
        }
        saveTicksToTotal(ticks)
    }

    fun getTicksCategory(cat: StatsKey): Int {
        return stats.getInt(cat.key, 0)
    }
    fun saveTicksToCat(cat: StatsKey, ticks: Int) {
        val prev = getTicksCategory(cat)
        val new = prev + ticks
        stats.edit {
            putInt(cat.key, new)
        }
    }


    fun getAnsCorrectTotal(): Int {
        return stats.getInt(StatsKey.AnsCorrectTotal.key, 0)
    }
    private fun saveAnsCorrectToTotal(ansCorrect: Int) {
        val prev = getAnsCorrectTotal()
        val new = prev + ansCorrect
        Log.d("StatsManager", "Ans Correct Total: $new")
        stats.edit {
            putInt(StatsKey.AnsCorrectTotal.key, new)
        }
    }

    fun getAnsCorrect(exercise: StatsKey): Int {
        return stats.getInt(exercise.key, 0)
    }
    fun saveAnsCorrect(exercise: StatsKey, ansCorrect: Int) {
        val prev = getAnsCorrect(exercise)
        val new = prev + ansCorrect
        stats.edit {
            putInt(exercise.key, new)
        }
        saveAnsCorrectToTotal(ansCorrect)
    }

    fun getAnsCorrectCategory(cat: StatsKey): Int {
        return stats.getInt(cat.key, 0)
    }
    fun saveAnsCorrectToCat(cat: StatsKey, ansCorrect: Int) {
        val prev = getAnsCorrectCategory(cat)
        val new = prev + ansCorrect
        stats.edit {
            putInt(cat.key, new)
        }
    }



    fun getAnsWrongTotal(): Int {
        return stats.getInt(StatsKey.AnsWrongTotal.key, 0)
    }
    private fun saveAnsWrongToTotal(ansWrong: Int) {
        val prev = getAnsWrongTotal()
        val new = prev + ansWrong
        Log.d("StatsManager", "Ans Wrong Total: $new")
        stats.edit {
            putInt(StatsKey.AnsWrongTotal.key, new)
        }
    }

    fun getAnsWrong(exercise: StatsKey): Int {
        return stats.getInt(exercise.key, 0)
    }
    fun saveAnsWrong(exercise: StatsKey, ansWrong: Int) {
        val prev = getAnsWrong(exercise)
        val new = prev + ansWrong
        stats.edit {
            putInt(exercise.key, new)
        }
        saveAnsWrongToTotal(ansWrong)
    }

    fun getAnsWrongCategory(cat: StatsKey): Int {
        return stats.getInt(cat.key, 0)
    }
    fun saveAnsWrongToCat(cat: StatsKey, ansWrong: Int) {
        val prev = getAnsWrongCategory(cat)
        val new = prev + ansWrong
        stats.edit {
            putInt(cat.key, new)
        }
    }



    fun getDayFirstLaunch(): String {
        val res = stats.getString(StatsKey.DayFirstLaunch.key, "null")
            .toString()
        return res
    }
    fun saveDayFirstLaunch() {
        if (getDayFirstLaunch() != "null") return
        val today = getCurrentDateFull()
        stats.edit {
            putString(StatsKey.DayFirstLaunch.key, today)
        }
    }



    fun getDaysPassedCount(): Int {
        return stats.getInt(StatsKey.DaysPassedCount.key, 0)
    }
    fun saveDaysPassedCount() {
        val launchDate = getDayFirstLaunch()
        val today = getCurrentDateFull()
        val count = daysBetween(launchDate, today)?.toInt()
        if (count == null) return
        stats.edit {
            putInt(StatsKey.DaysPassedCount.key, count)
        }
    }



    fun getDayLastPractice(): String {
        return stats.getString(StatsKey.DayLastPractice.key, "null").toString()
    }
    fun saveDayLastPractice() {
        val today = getCurrentDate()
        stats.edit {
            putString(StatsKey.DayLastPractice.key, today)
        }
    }



    fun getDaysPracticedCount(): Int {
        return stats.getInt(StatsKey.DaysPracticedCount.key, 0)
    }
    fun updateDaysPracticedCount() {
        val last = getDayLastPractice()
        val today = getCurrentDate()
        if (last != "null") {
            val diff = daysBetween(last, today, "EEE, d MMM yyyy")?.toInt()
            if (diff == null || diff < 1) return
        }
        val prev = getDaysPracticedCount()
        stats.edit {
            putInt(StatsKey.DaysPracticedCount.key, prev + 1)
        }
        saveDayLastPractice()
    }



}

sealed class StatsKey(val key: String) {

    object DayFirstLaunch : StatsKey("day_first_launch")
    object DaysPassedCount : StatsKey("days_passed_count")
    object DayLastPractice : StatsKey("day_last_practiced")
    object DaysPracticedCount : StatsKey("days_practiced_count")
    object Null : StatsKey("null")



    object TicksTotal : StatsKey("ticks_total")
    object TicksPhonetics : StatsKey("ticks_phonetics")
    object TicksLexicon : StatsKey("ticks_lexicon")
    object TicksMorphology : StatsKey("ticks_morphology")
    object TicksSyntax : StatsKey("ticks_syntax")
    object TicksCulture : StatsKey("ticks_culture")

    object TicksVerbPresent : StatsKey("ticks_verb_present")
    object TicksVerbDefinitePast : StatsKey("ticks_verb_definite_past")
    object TicksVerbIndefinitePast : StatsKey("ticks_verb_indefinite_past")
    object TicksVerbPastContinuous : StatsKey("ticks_verb_past_continuous")
    object TicksVerbPastPerfect : StatsKey("ticks_verb_past_perfect")
    object TicksVerbDefiniteFuture : StatsKey("ticks_verb_definite_future")
    object TicksVerbIndefiniteFuture : StatsKey("ticks_verb_indefinite_future")
    object TicksVerbInfinitive : StatsKey("ticks_verb_infinitive")
    object TicksVerbGerund : StatsKey("ticks_verb_gerund")
    object TicksVerbFutureInThePast : StatsKey("ticks_verb_future_in_the_past")

    object TicksNounPlural : StatsKey("ticks_noun_plural")



    object AnsCorrectTotal : StatsKey("ans_correct_total")
    object AnsCorrectPhonetics : StatsKey("ans_correct_phonetics")
    object AnsCorrectLexicon : StatsKey("ans_correct_lexicon")
    object AnsCorrectMorphology : StatsKey("ans_correct_morphology")
    object AnsCorrectSyntax : StatsKey("ans_correct_syntax")
    object AnsCorrectCulture : StatsKey("ans_correct_culture")

    object AnsCorrectVerbPresent : StatsKey("ans_correct_verb_present")
    object AnsCorrectVerbDefinitePast : StatsKey("ans_correct_verb_definite_past")
    object AnsCorrectVerbIndefinitePast : StatsKey("ans_correct_verb_indefinite_past")
    object AnsCorrectVerbPastContinuous : StatsKey("ans_correct_verb_past_continuous")
    object AnsCorrectVerbPastPerfect : StatsKey("ans_correct_verb_past_perfect")
    object AnsCorrectVerbDefiniteFuture : StatsKey("ans_correct_verb_definite_future")
    object AnsCorrectVerbIndefiniteFuture : StatsKey("ans_correct_verb_indefinite_future")
    object AnsCorrectVerbInfinitive : StatsKey("ans_correct_verb_infinitive")
    object AnsCorrectVerbGerund : StatsKey("ans_correct_verb_gerund")
    object AnsCorrectVerbFutureInThePast : StatsKey("ans_correct_verb_future_in_the_past")

    object AnsCorrectNounPlural : StatsKey("ans_correct_noun_plural")



    object AnsWrongTotal : StatsKey("ans_wrong_total")
    object AnsWrongPhonetics : StatsKey("ans_wrong_phonetics")
    object AnsWrongLexicon : StatsKey("ans_wrong_lexicon")
    object AnsWrongMorphology : StatsKey("ans_wrong_morphology")
    object AnsWrongSyntax : StatsKey("ans_wrong_syntax")
    object AnsWrongCulture : StatsKey("ans_wrong_culture")

    object AnsWrongVerbPresent : StatsKey("ans_wrong_verb_present")
    object AnsWrongVerbDefinitePast : StatsKey("ans_wrong_verb_definite_past")
    object AnsWrongVerbIndefinitePast : StatsKey("ans_wrong_verb_indefinite_past")
    object AnsWrongVerbPastContinuous : StatsKey("ans_wrong_verb_past_continuous")
    object AnsWrongVerbPastPerfect : StatsKey("ans_wrong_verb_past_perfect")
    object AnsWrongVerbDefiniteFuture : StatsKey("ans_wrong_verb_definite_future")
    object AnsWrongVerbIndefiniteFuture : StatsKey("ans_wrong_verb_indefinite_future")
    object AnsWrongVerbInfinitive : StatsKey("ans_wrong_verb_infinitive")
    object AnsWrongVerbGerund : StatsKey("ans_wrong_verb_gerund")
    object AnsWrongVerbFutureInThePast : StatsKey("ans_wrong_verb_future_in_the_past")

    object AnsWrongNounPlural : StatsKey("ans_wrong_noun_plural")



}

fun addressToCatTicks(address: Int): StatsKey = when (address) {
    R.string.verb_present -> StatsKey.TicksMorphology
    R.string.verb_definite_past -> StatsKey.TicksMorphology
    R.string.verb_indefinite_past -> StatsKey.TicksMorphology
    R.string.verb_past_continuous -> StatsKey.TicksMorphology
    R.string.verb_past_perfect -> StatsKey.TicksMorphology
    R.string.verb_definite_future -> StatsKey.TicksMorphology
    R.string.verb_indefinite_future -> StatsKey.TicksMorphology
    R.string.verb_infinitive -> StatsKey.TicksMorphology
    R.string.verb_gerund -> StatsKey.TicksMorphology
    R.string.verb_future_in_the_past -> StatsKey.TicksMorphology
    R.string.noun_plural -> StatsKey.TicksMorphology
    else -> StatsKey.Null
}

fun addressToCatAnsCorrect(address: Int): StatsKey = when (address) {
    R.string.verb_present -> StatsKey.AnsCorrectMorphology
    R.string.verb_definite_past -> StatsKey.AnsCorrectMorphology
    R.string.verb_indefinite_past -> StatsKey.AnsCorrectMorphology
    R.string.verb_past_continuous -> StatsKey.AnsCorrectMorphology
    R.string.verb_past_perfect -> StatsKey.AnsCorrectMorphology
    R.string.verb_definite_future -> StatsKey.AnsCorrectMorphology
    R.string.verb_indefinite_future -> StatsKey.AnsCorrectMorphology
    R.string.verb_infinitive -> StatsKey.AnsCorrectMorphology
    R.string.verb_gerund -> StatsKey.AnsCorrectMorphology
    R.string.verb_future_in_the_past -> StatsKey.AnsCorrectMorphology
    R.string.noun_plural -> StatsKey.AnsCorrectMorphology
    else -> StatsKey.Null
}

fun addressToCatAnsWrong(address: Int): StatsKey = when (address) {
    R.string.verb_present -> StatsKey.AnsWrongMorphology
    R.string.verb_definite_past -> StatsKey.AnsWrongMorphology
    R.string.verb_indefinite_past -> StatsKey.AnsWrongMorphology
    R.string.verb_past_continuous -> StatsKey.AnsWrongMorphology
    R.string.verb_past_perfect -> StatsKey.AnsWrongMorphology
    R.string.verb_definite_future -> StatsKey.AnsWrongMorphology
    R.string.verb_indefinite_future -> StatsKey.AnsWrongMorphology
    R.string.verb_infinitive -> StatsKey.AnsWrongMorphology
    R.string.verb_gerund -> StatsKey.AnsWrongMorphology
    R.string.verb_future_in_the_past -> StatsKey.AnsWrongMorphology
    R.string.noun_plural -> StatsKey.AnsWrongMorphology
    else -> StatsKey.Null
}

fun tabAddressToCatTicks(address: Int): StatsKey = when (address) {
    R.string.bottom_bar_phonetics -> StatsKey.TicksPhonetics
    R.string.bottom_bar_lexicon -> StatsKey.TicksLexicon
    R.string.bottom_bar_morphology -> StatsKey.TicksMorphology
    R.string.bottom_bar_syntax -> StatsKey.TicksSyntax
    R.string.bottom_bar_culture -> StatsKey.TicksCulture
    else -> StatsKey.Null
}

fun tabAddressToCatAnsCorrect(address: Int): StatsKey = when (address) {
    R.string.bottom_bar_phonetics -> StatsKey.AnsCorrectPhonetics
    R.string.bottom_bar_lexicon -> StatsKey.AnsCorrectLexicon
    R.string.bottom_bar_morphology -> StatsKey.AnsCorrectMorphology
    R.string.bottom_bar_syntax -> StatsKey.AnsCorrectSyntax
    R.string.bottom_bar_culture -> StatsKey.AnsCorrectCulture
    else -> StatsKey.Null
}

fun tabAddressToCatAnsWrong(address: Int): StatsKey = when (address) {
    R.string.bottom_bar_phonetics -> StatsKey.AnsWrongPhonetics
    R.string.bottom_bar_lexicon -> StatsKey.AnsWrongLexicon
    R.string.bottom_bar_morphology -> StatsKey.AnsWrongMorphology
    R.string.bottom_bar_syntax -> StatsKey.AnsWrongSyntax
    R.string.bottom_bar_culture -> StatsKey.AnsWrongCulture
    else -> StatsKey.Null
}

fun addressToTicks(address: Int): StatsKey = when (address) {
    R.string.verb_present -> StatsKey.TicksVerbPresent
    R.string.verb_definite_past -> StatsKey.TicksVerbDefinitePast
    R.string.verb_indefinite_past -> StatsKey.TicksVerbIndefinitePast
    R.string.verb_past_continuous -> StatsKey.TicksVerbPastContinuous
    R.string.verb_past_perfect -> StatsKey.TicksVerbPastPerfect
    R.string.verb_definite_future -> StatsKey.TicksVerbDefiniteFuture
    R.string.verb_indefinite_future -> StatsKey.TicksVerbIndefiniteFuture
    R.string.verb_infinitive -> StatsKey.TicksVerbInfinitive
    R.string.verb_gerund -> StatsKey.TicksVerbGerund
    R.string.verb_future_in_the_past -> StatsKey.TicksVerbFutureInThePast
    R.string.noun_plural -> StatsKey.TicksNounPlural
    else -> StatsKey.Null
}

fun addressToAnsCorrect(address: Int): StatsKey = when (address) {
    R.string.verb_present -> StatsKey.AnsCorrectVerbPresent
    R.string.verb_definite_past -> StatsKey.AnsCorrectVerbDefinitePast
    R.string.verb_indefinite_past -> StatsKey.AnsCorrectVerbIndefinitePast
    R.string.verb_past_continuous -> StatsKey.AnsCorrectVerbPastContinuous
    R.string.verb_past_perfect -> StatsKey.AnsCorrectVerbPastPerfect
    R.string.verb_definite_future -> StatsKey.AnsCorrectVerbDefiniteFuture
    R.string.verb_indefinite_future -> StatsKey.AnsCorrectVerbIndefiniteFuture
    R.string.verb_infinitive -> StatsKey.AnsCorrectVerbInfinitive
    R.string.verb_gerund -> StatsKey.AnsCorrectVerbGerund
    R.string.verb_future_in_the_past -> StatsKey.AnsCorrectVerbFutureInThePast
    R.string.noun_plural -> StatsKey.AnsCorrectNounPlural
    else -> StatsKey.Null
}

fun addressToAnsWrong(address: Int): StatsKey = when (address) {
    R.string.verb_present -> StatsKey.AnsWrongVerbPresent
    R.string.verb_definite_past -> StatsKey.AnsWrongVerbDefinitePast
    R.string.verb_indefinite_past -> StatsKey.AnsWrongVerbIndefinitePast
    R.string.verb_past_continuous -> StatsKey.AnsWrongVerbPastContinuous
    R.string.verb_past_perfect -> StatsKey.AnsWrongVerbPastPerfect
    R.string.verb_definite_future -> StatsKey.AnsWrongVerbDefiniteFuture
    R.string.verb_indefinite_future -> StatsKey.AnsWrongVerbIndefiniteFuture
    R.string.verb_infinitive -> StatsKey.AnsWrongVerbInfinitive
    R.string.verb_gerund -> StatsKey.AnsWrongVerbGerund
    R.string.verb_future_in_the_past -> StatsKey.AnsWrongVerbFutureInThePast
    R.string.noun_plural -> StatsKey.AnsWrongNounPlural
    else -> StatsKey.Null
}
