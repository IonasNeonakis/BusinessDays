package fixtures

import java.time.LocalDate
import java.time.Month

object DaysFixture {
    val monday4ofJanuary: LocalDate = LocalDate.of(2021, Month.JANUARY, 4)
    private val tuesday5ofJanuary: LocalDate = LocalDate.of(2021, Month.JANUARY, 5)
    private val wednesday6ofJanuary: LocalDate = LocalDate.of(2021, Month.JANUARY, 6)
    private val thursday7ofJanuary: LocalDate = LocalDate.of(2021, Month.JANUARY, 7)
    private val friday8ofJanuary: LocalDate = LocalDate.of(2021, Month.JANUARY, 8)
    private val saturday9ofJanuary: LocalDate = LocalDate.of(2021, Month.JANUARY, 9)
    private val sunday10ofJanuary: LocalDate = LocalDate.of(2021, Month.JANUARY, 10)

    val aMondaysToFridaysWeek = listOf(monday4ofJanuary, tuesday5ofJanuary, wednesday6ofJanuary, thursday7ofJanuary, friday8ofJanuary)
    val aWeekend = listOf(saturday9ofJanuary, sunday10ofJanuary)
}