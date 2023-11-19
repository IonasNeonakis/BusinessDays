package fixtures

import java.time.LocalDate
import java.time.Month

object DaysFixture {
    val monday4ofJanuary: LocalDate = LocalDate.of(2021, Month.JANUARY, 4)
    val tuesday5ofJanuary: LocalDate = LocalDate.of(2021, Month.JANUARY, 5)
    val wednesday6ofJanuary: LocalDate = LocalDate.of(2021, Month.JANUARY, 6)
    val thursday7ofJanuary: LocalDate = LocalDate.of(2021, Month.JANUARY, 7)
    val friday8ofJanuary: LocalDate = LocalDate.of(2021, Month.JANUARY, 8)
    val saturday9ofJanuary: LocalDate = LocalDate.of(2021, Month.JANUARY, 9)
    val sunday10ofJanuary: LocalDate = LocalDate.of(2021, Month.JANUARY, 10)
    val monday11ofJanuary: LocalDate = LocalDate.of(2021, Month.JANUARY, 11)

    val aMondaysToFridaysWeek = listOf(monday4ofJanuary, tuesday5ofJanuary, wednesday6ofJanuary, thursday7ofJanuary, friday8ofJanuary)
    val aWeekend = listOf(saturday9ofJanuary, sunday10ofJanuary)
}