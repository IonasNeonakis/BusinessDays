package fixtures

import java.time.LocalDate
import java.time.Month

object DaysFixture {
    private val aMonday = LocalDate.of(2021, Month.JANUARY, 4)
    private val aTuesday = LocalDate.of(2021, Month.JANUARY, 5)
    private val aWednesday = LocalDate.of(2021, Month.JANUARY, 6)
    private val aThursday = LocalDate.of(2021, Month.JANUARY, 7)
    private val aFriday = LocalDate.of(2021, Month.JANUARY, 8)
    private val aSaturday = LocalDate.of(2021, Month.JANUARY, 9)
    private val aSunday = LocalDate.of(2021, Month.JANUARY, 10)

    val aMondaysToFridaysWeek = listOf(aMonday, aTuesday, aWednesday, aThursday, aFriday)
    val aWeekend = listOf(aSaturday, aSunday)
}