import java.time.LocalDate
import java.time.Month
import java.time.Year


/**
 * A specialized class for handling business days in France, taking into account French holidays.
 *
 * @property additionalHolidays A function that provides additional holidays for a given year.
 *                             Defaults to an empty list if not specified.
 */
class BusinessDaysFrance(val additionalHolidays: (year: Year) -> Collection<LocalDate> = { emptyList() }) :
    BusinessDays({ year: Year ->
        listOf(
            newYear(year),
            easterMonday(year),
            laborDay(year),
            alliesVictory(year),
            ascension(year),
            pentecost(year),
            nationalDay(year),
            assomption(year),
            allSaintsDay(year),
            armistice(year),
            christmasDay(year)
        ) + additionalHolidays(year)
    }) {

    companion object {

        /**
         * Calculates the date of New Year's Eve for the given year.
         *
         * @param year The year for which to calculate New Year's Eve.
         * @return The date of New Year's Eve.
         */
        fun newYear(year: Year): LocalDate = LocalDate.of(year.value, Month.JANUARY, 1)

        /**
         * Calculates the date of Easter Monday for the given year.
         *
         * @param year The year for which to calculate Easter Monday.
         * @return The date of Easter Monday.
         */
        fun easterMonday(year: Year): LocalDate = easter(year).plusDays(1)

        /**
         * Calculates the date of Easter for the given year using the Anonymous Gregorian algorithm.
         *
         * The algorithm is based on the formula described in the Wikipedia article:
         * [Date of Easter - Anonymous Gregorian algorithm](https://en.wikipedia.org/wiki/Date_of_Easter#Anonymous_Gregorian_algorithm)
         *
         * @param year The year for which to calculate the date of Easter.
         * @return The date of Easter for the specified year.
         */
        private fun easter(year: Year): LocalDate {
            val a = year.value % 19
            val b = year.value / 100
            val c = year.value % 100
            val d = b / 4
            val e = b % 4
            val f = (b + 8) / 25
            val g = (b - f + 1) / 3
            val h = (19 * a + b - d - g + 15) % 30
            val i = c / 4
            val k = c % 4
            val l = (32 + 2 * e + 2 * i - h - k) % 7
            val m = (a + 11 * h + 22 * l) / 451
            val n = (h + l - 7 * m + 114) / 31
            val o = (h + l - 7 * m + 114) % 31

            val dayOfMonth = o + 1

            return LocalDate.of(year.value, n, dayOfMonth)
        }

        /**
         * Calculates the date of Labor Day (May 1st) for the given year.
         *
         * @param year The year for which to calculate Labor Day.
         * @return The date of Labor Day.
         */
        fun laborDay(year: Year): LocalDate = LocalDate.of(year.value, Month.MAY, 1)

        /**
         * Calculates the date of Victory in Europe Day (May 8th) for the given year.
         *
         * @param year The year for which to calculate Victory in Europe Day.
         * @return The date of Victory in Europe Day.
         */
        fun alliesVictory(year: Year): LocalDate = LocalDate.of(year.value, Month.MAY, 8)

        /**
         * Calculates the date of Ascension Day for the given year.
         *
         * @param year The year for which to calculate Ascension Day.
         * @return The date of Ascension Day.
         */
        fun ascension(year: Year): LocalDate = easter(year).plusDays(39)

        /**
         * Calculates the date of Pentecost for the given year.
         *
         * @param year The year for which to calculate Pentecost.
         * @return The date of Pentecost.
         */
        fun pentecost(year: Year): LocalDate = easter(year).plusDays(50)

        /**
         * Calculates the date of Bastille Day (July 14th) for the given year.
         *
         * @param year The year for which to calculate Bastille Day.
         * @return The date of Bastille Day.
         */
        fun nationalDay(year: Year): LocalDate = LocalDate.of(year.value, Month.JULY, 14)

        /**
         * Calculates the date of Assumption Day (August 15th) for the given year.
         *
         * @param year The year for which to calculate Assumption Day.
         * @return The date of Assumption Day.
         */
        fun assomption(year: Year): LocalDate = LocalDate.of(year.value, Month.AUGUST, 15)

        /**
         * Calculates the date of All Saints' Day (November 1st) for the given year.
         *
         * @param year The year for which to calculate All Saints' Day.
         * @return The date of All Saints' Day.
         */
        fun allSaintsDay(year: Year): LocalDate = LocalDate.of(year.value, Month.NOVEMBER, 1)

        /**
         * Calculates the date of Armistice Day (November 11th) for the given year.
         *
         * @param year The year for which to calculate Armistice Day.
         * @return The date of Armistice Day.
         */
        fun armistice(year: Year): LocalDate = LocalDate.of(year.value, Month.NOVEMBER, 11)

        /**
         * Calculates the date of Christmas Day (December 25th) for the given year.
         *
         * @param year The year for which to calculate Christmas Day.
         * @return The date of Christmas Day.
         */
        fun christmasDay(year: Year): LocalDate = LocalDate.of(year.value, Month.DECEMBER, 25)
    }
}
