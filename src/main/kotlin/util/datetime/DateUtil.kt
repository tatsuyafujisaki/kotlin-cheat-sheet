package util.datetime

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

object DateUtil {
    /**
     * @param date yyyy-mm-dd
     */
    fun convertToDate(date: String) = runCatching {
        LocalDate.parse(date)
    }.getOrNull()

    fun formatToJapaneseDate(date: LocalDate = LocalDate.now()): String =
            date.format(DateTimeFormatter.ofPattern("y年M月d日"))

    fun japaneseDatePassed(date1: LocalDate, date2: LocalDate): String {
        val diffInDays = ChronoUnit.DAYS.between(date1, date2)
        return if (diffInDays > 0) "${diffInDays}日前" else "本日"
    }

    private fun getMonth(date: LocalDate = LocalDate.now(), monthDelta: Long = 0) = if (monthDelta >= 0) {
        date.plusMonths(monthDelta)
    } else {
        date.minusMonths(-1 * monthDelta)
    }.monthValue

    fun getMonthExample() {
        println("Next month: ${getMonth(monthDelta = -1)}")
        println("Current month: ${getMonth()}")
        println("Previous month: ${getMonth(monthDelta = 1)}")
    }
}
