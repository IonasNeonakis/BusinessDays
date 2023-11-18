import java.time.LocalDate
import java.time.Month
import java.time.Year


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

        fun newYear(year: Year): LocalDate = LocalDate.of(year.value, Month.JANUARY, 1)

        fun easterMonday(year: Year): LocalDate = easter(year).plusDays(1)

        private fun easter(year: Year): LocalDate {
            //https://en.wikipedia.org/wiki/Date_of_Easter#Anonymous_Gregorian_algorithm
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

        fun laborDay(year: Year): LocalDate = LocalDate.of(year.value, Month.MAY, 1)

        fun alliesVictory(year: Year): LocalDate = LocalDate.of(year.value, Month.MAY, 8)

        fun ascension(year: Year): LocalDate = easter(year).plusDays(39)

        fun pentecost(year: Year): LocalDate = easter(year).plusDays(50)

        fun nationalDay(year: Year): LocalDate = LocalDate.of(year.value, Month.JULY, 14)

        fun assomption(year: Year): LocalDate = LocalDate.of(year.value, Month.AUGUST, 15)

        fun allSaintsDay(year: Year): LocalDate = LocalDate.of(year.value, Month.NOVEMBER, 1)

        fun armistice(year: Year): LocalDate = LocalDate.of(year.value, Month.NOVEMBER, 11)

        fun christmasDay(year: Year): LocalDate = LocalDate.of(year.value, Month.DECEMBER, 25)
    }
}
