import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Year


private val WEEKEND_DAYS = arrayOf(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)

/**
 * A utility class for working with business days, taking into account weekends and given holidays.
 *
 * @property holidays A function that provides a collection of holidays for a given year.
 *                   Defaults to an empty list if not specified.
 */
open class BusinessDays(val holidays: (year: Year) -> Collection<LocalDate> = { emptyList() }) {

    /**
     * Checks if a given date is a holiday.
     *
     * @param date The date to check.
     * @return `true` if the date is a holiday, `false` otherwise.
     */
    fun isHoliday(date: LocalDate): Boolean = date in holidays(Year.of(date.year))

    /**
     * Checks if a given date is a business day, considering both weekdays and holidays.
     *
     * @param date The date to check.
     * @return `true` if the date is a business day, `false` otherwise.
     */
    fun isBusinessDay(date: LocalDate): Boolean = isWeekday(date) && !isHoliday(date)

    companion object {

        /**
         * Checks if a given date is a weekend day (Saturday or Sunday).
         *
         * @param date The date to check.
         * @return `true` if the date is a weekend day, `false` otherwise.
         */
        fun isWeekendDay(date: LocalDate): Boolean = date.dayOfWeek in WEEKEND_DAYS

        /**
         * Checks if a given date is a weekday (Monday through Friday).
         *
         * @param date The date to check.
         * @return `true` if the date is a weekday, `false` otherwise.
         */
        fun isWeekday(date: LocalDate): Boolean = date.dayOfWeek !in WEEKEND_DAYS
    }
}
