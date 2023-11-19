package fixtures

import java.time.LocalDate
import java.time.Month

object FrenchHolidaysFixture {
    // https://www.service-public.fr/particuliers/actualites/A15406
    private val newYearsDay2024 = LocalDate.of(2024, Month.JANUARY, 1)
    private val easterMonday2024 = LocalDate.of(2024, Month.APRIL, 1)
    private val laborDay2024 = LocalDate.of(2024, Month.MAY, 1)
    private val victory2024 = LocalDate.of(2024, Month.MAY, 8)
    private val ascensionDay2024 = LocalDate.of(2024, Month.MAY, 9)
    private val whitMonday2024 = LocalDate.of(2024, Month.MAY, 20)
    private val nationalDat2024 = LocalDate.of(2024, Month.JULY, 14)
    private val assumption2024 = LocalDate.of(2024, Month.AUGUST, 15)
    private val allSaintsDay2024 = LocalDate.of(2024, Month.NOVEMBER, 1)
    private val ArmisticeDay2024 = LocalDate.of(2024, Month.NOVEMBER, 11)
    private val christmasDay2024 = LocalDate.of(2024, Month.DECEMBER, 25)

    val allHolidaysOf2024 = listOf(
        newYearsDay2024,
        easterMonday2024,
        laborDay2024,
        victory2024,
        ascensionDay2024,
        whitMonday2024,
        nationalDat2024,
        assumption2024,
        allSaintsDay2024,
        ArmisticeDay2024,
        christmasDay2024
    )
}