import fixtures.FrenchHolidaysFixture
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.time.LocalDate
import java.time.Month
import java.time.Year

class BusinessDaysFranceTest {
    private val defaultBusinessDaysFrance = BusinessDaysFrance()

    @Test
    fun `given default BusinessDaysFrance, check if all holiday of 2024 are present`() {
        with(FrenchHolidaysFixture) {
            expectThat(defaultBusinessDaysFrance.holidays(Year.of(2024))).isEqualTo(allHolidaysOf2024)
        }
    }


    @Test
    fun `should return new years Eve of 2023`() {
        val newYearsEve2023 = BusinessDaysFrance.newYear(Year.of(2023))
        expectThat(newYearsEve2023).isEqualTo(LocalDate.of(2023, Month.JANUARY, 1))
    }

    @Test
    fun `should return easterMonday of every year`() {
        expectThat(BusinessDaysFrance.easterMonday(Year.of(2023))).isEqualTo(LocalDate.of(2023, Month.APRIL, 10))
        expectThat(BusinessDaysFrance.easterMonday(Year.of(2024))).isEqualTo(LocalDate.of(2024, Month.APRIL, 1))
        expectThat(BusinessDaysFrance.easterMonday(Year.of(2025))).isEqualTo(LocalDate.of(2025, Month.APRIL, 21))
        expectThat(BusinessDaysFrance.easterMonday(Year.of(2026))).isEqualTo(LocalDate.of(2026, Month.APRIL, 6))
        expectThat(BusinessDaysFrance.easterMonday(Year.of(2027))).isEqualTo(LocalDate.of(2027, Month.MARCH, 29))
        expectThat(BusinessDaysFrance.easterMonday(Year.of(2028))).isEqualTo(LocalDate.of(2028, Month.APRIL, 17))
        expectThat(BusinessDaysFrance.easterMonday(Year.of(2029))).isEqualTo(LocalDate.of(2029, Month.APRIL, 2))
        expectThat(BusinessDaysFrance.easterMonday(Year.of(2030))).isEqualTo(LocalDate.of(2030, Month.APRIL, 22))
        expectThat(BusinessDaysFrance.easterMonday(Year.of(2031))).isEqualTo(LocalDate.of(2031, Month.APRIL, 14))
        expectThat(BusinessDaysFrance.easterMonday(Year.of(2032))).isEqualTo(LocalDate.of(2032, Month.MARCH, 29))
        expectThat(BusinessDaysFrance.easterMonday(Year.of(2033))).isEqualTo(LocalDate.of(2033, Month.APRIL, 18))
        expectThat(BusinessDaysFrance.easterMonday(Year.of(2034))).isEqualTo(LocalDate.of(2034, Month.APRIL, 10))
        expectThat(BusinessDaysFrance.easterMonday(Year.of(2035))).isEqualTo(LocalDate.of(2035, Month.MARCH, 26))
        expectThat(BusinessDaysFrance.easterMonday(Year.of(2036))).isEqualTo(LocalDate.of(2036, Month.APRIL, 14))
        expectThat(BusinessDaysFrance.easterMonday(Year.of(2037))).isEqualTo(LocalDate.of(2037, Month.APRIL, 6))
        expectThat(BusinessDaysFrance.easterMonday(Year.of(2038))).isEqualTo(LocalDate.of(2038, Month.APRIL, 26))
        expectThat(BusinessDaysFrance.easterMonday(Year.of(2039))).isEqualTo(LocalDate.of(2039, Month.APRIL, 11))
        expectThat(BusinessDaysFrance.easterMonday(Year.of(2040))).isEqualTo(LocalDate.of(2040, Month.APRIL, 2))
    }


    @Test
    fun `should return labor Day of 2023`() {
        val laborDay2023 = BusinessDaysFrance.laborDay(Year.of(2023))
        expectThat(laborDay2023).isEqualTo(LocalDate.of(2023, Month.MAY, 1))
    }


    @Test
    fun `should return allies victory day of 2023`() {
        val alliesVictory2023 = BusinessDaysFrance.alliesVictory(Year.of(2023))
        expectThat(alliesVictory2023).isEqualTo(LocalDate.of(2023, Month.MAY, 8))
    }

    @Test
    fun `should return national day of 2023`() {
        val nationalDay2023 = BusinessDaysFrance.nationalDay(Year.of(2023))
        expectThat(nationalDay2023).isEqualTo(LocalDate.of(2023, Month.JULY, 14))
    }

    @Test
    fun `should return assomption day of 2023`() {
        val assomption2023 = BusinessDaysFrance.assomption(Year.of(2023))
        expectThat(assomption2023).isEqualTo(LocalDate.of(2023, Month.AUGUST, 15))
    }


    @Test
    fun `should return all Saints day of 2023`() {
        val allSaintsDay2023 = BusinessDaysFrance.allSaintsDay(Year.of(2023))
        expectThat(allSaintsDay2023).isEqualTo(LocalDate.of(2023, Month.NOVEMBER, 1))
    }


    @Test
    fun `should return armistice day of 2023`() {
        val armistice2023 = BusinessDaysFrance.armistice(Year.of(2023))
        expectThat(armistice2023).isEqualTo(LocalDate.of(2023, Month.NOVEMBER, 11))
    }

    @Test
    fun `should return christmas day of 2023`() {
        val christmas2023 = BusinessDaysFrance.christmasDay(Year.of(2023))
        expectThat(christmas2023).isEqualTo(LocalDate.of(2023, Month.DECEMBER, 25))
    }
}


