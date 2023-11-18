import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Year


private val WEEKEND_DAYS = arrayOf(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)

open class BusinessDays(val holidays: (year: Year) -> Collection<LocalDate> = { emptyList() }) {
    fun isHoliday(date: LocalDate): Boolean = date in holidays(Year.of(date.year))

    fun isBusinessDay(date: LocalDate): Boolean = isWeekday(date) && !isHoliday(date)

    companion object {
        fun isWeekendDay(date: LocalDate): Boolean = date.dayOfWeek in WEEKEND_DAYS

        fun isWeekday(date: LocalDate): Boolean = date.dayOfWeek !in WEEKEND_DAYS
    }
}