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

    /**
     * Adds a specified number of business days to the given date.
     *
     * @param date The starting date.
     * @param days The number of business days to add.
     * @return The resulting date after adding the specified business days.
     */
    fun businessDaysAdd(date: LocalDate, days: Long): LocalDate {
        val sign: Long = if (days >= 0) 1 else -1
        var result = date
        var remainingDays = days
        while (remainingDays != 0L) {
            result = result.plusDays(sign)
            if (isBusinessDay(result)) {
                remainingDays -= sign
            }
        }
        return result
    }

    /**
     * Subtracts a specified number of business days from the given date.
     *
     * @param date The starting date.
     * @param days The number of business days to subtract.
     * @return The resulting date after subtracting the specified business days.
     */
    fun businessDaysSubtract(date: LocalDate, days: Long): LocalDate = businessDaysAdd(date, -days)


    /**
     * Calculates the number of business days between two dates.
     * The result is positive if the end date is after the start date, negative if the end date is before the start date.
     * The result does not include the start date, but does include the end date.
     *
     * @param start The starting date.
     * @param end The ending date.
     * @return The number of business days between the two dates.
     */
    fun businessDaysBetween(start: LocalDate, end: LocalDate): Long {
        val sign: Long = if (end.isAfter(start)) 1 else -1
        var result = 0L
        var current = start
        while (current != end) {
            current = current.plusDays(sign)
            if (isBusinessDay(current)) {
                result += sign
            }
        }
        return result
    }

    fun nextBusinessDay(date: LocalDate): LocalDate = businessDaysAdd(date, 1)

    fun previousBusinessDay(date: LocalDate): LocalDate = businessDaysSubtract(date, 1)

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
