import fixtures.DaysFixture
import org.junit.jupiter.api.Nested
import strikt.api.expectThat
import strikt.assertions.all
import strikt.assertions.isEqualTo
import java.time.LocalDate
import java.time.Month
import kotlin.test.Test

class BusinessDaysTest {
    private val defaultBusinessDays = BusinessDays()

    @Test
    fun `given default BusinessDays, when calling 'isHoliday' then should always return false`() {
        with(DaysFixture) {
            expectThat(aMondaysToFridaysWeek + aWeekend).all {
                get { defaultBusinessDays.isHoliday(this) }.isEqualTo(false)
            }
        }
    }

    @Test
    fun `given default BusinessDays, when calling 'isBusinessDay' with dates on mondays to fridays then should always return true`() {
        with(DaysFixture) {
            expectThat(aMondaysToFridaysWeek).all {
                get { defaultBusinessDays.isBusinessDay(this) }.isEqualTo(true)
            }
        }
    }


    @Test
    fun `given default BusinessDays, when calling 'isBusinessDay' with dates on a weekend then should always return false`() {
        with(DaysFixture) {
            expectThat(aWeekend).all {
                get { defaultBusinessDays.isBusinessDay(this) }.isEqualTo(false)
            }
        }
    }


    @Nested
    inner class IsWeekendDay {
        @Test
        fun `given default BusinessDays, when calling 'isWeekendDay' with dates in weekend should return true`() {
            with(DaysFixture) {
                expectThat(aWeekend).all {
                    get { BusinessDays.isWeekendDay(this) }.isEqualTo(true)
                }
            }
        }

        @Test
        fun `given default BusinessDays, when calling 'isWeekendDay' with dates in week should return false`() {
            with(DaysFixture) {
                expectThat(aMondaysToFridaysWeek).all {
                    get { BusinessDays.isWeekendDay(this) }.isEqualTo(false)
                }
            }
        }
    }

    @Nested
    inner class IsWeekday {
        @Test
        fun `given default BusinessDays, when calling 'isWeekDay' with dates in week should return true`() {
            with(DaysFixture) {
                expectThat(aMondaysToFridaysWeek).all {
                    get { BusinessDays.isWeekday(this) }.isEqualTo(true)
                }
            }
        }

        @Test
        fun `given default BusinessDays, when calling 'isWeekDay' with dates in weekend should return false`() {
            with(DaysFixture) {
                expectThat(aWeekend).all {
                    get { BusinessDays.isWeekday(this) }.isEqualTo(false)
                }
            }
        }
    }


    @Test
    fun `given BusinessDays with a holiday, when adding days, should skip holiday and weekends`() {
        val monday4ofJanuary = LocalDate.of(2021, Month.JANUARY, 4)
        val businessDays = BusinessDays(holidays = { setOf(monday4ofJanuary.plusDays(1)) })

        expectThat(businessDays.businessDaysAdd(monday4ofJanuary, 1)).isEqualTo(LocalDate.of(2021, Month.JANUARY, 6))
        expectThat(businessDays.businessDaysAdd(monday4ofJanuary, 2)).isEqualTo(LocalDate.of(2021, Month.JANUARY, 7))
        expectThat(businessDays.businessDaysAdd(monday4ofJanuary, 3)).isEqualTo(LocalDate.of(2021, Month.JANUARY, 8))

        expectThat(businessDays.businessDaysAdd(monday4ofJanuary, 4)).isEqualTo(LocalDate.of(2021, Month.JANUARY, 11))
        expectThat(businessDays.businessDaysAdd(monday4ofJanuary, 10)).isEqualTo(LocalDate.of(2021, Month.JANUARY, 19))
    }

    @Test
    fun `given BusinessDays with a holiday, when subtracting days, should skip holiday and weekends`() {
        with(DaysFixture){
            val businessDays = BusinessDays(holidays = { setOf(LocalDate.of(2021, Month.JANUARY, 1)) })

            expectThat(businessDays.businessDaysSubtract(monday4ofJanuary, 1)).isEqualTo(LocalDate.of(2020, Month.DECEMBER, 31))
            expectThat(businessDays.businessDaysSubtract(monday4ofJanuary, 2)).isEqualTo(LocalDate.of(2020, Month.DECEMBER, 30))
            expectThat(businessDays.businessDaysSubtract(monday4ofJanuary, 3)).isEqualTo(LocalDate.of(2020, Month.DECEMBER, 29))
            expectThat(businessDays.businessDaysSubtract(monday4ofJanuary, 4)).isEqualTo(LocalDate.of(2020, Month.DECEMBER, 28))

            expectThat(businessDays.businessDaysSubtract(monday4ofJanuary, 5)).isEqualTo(LocalDate.of(2020, Month.DECEMBER, 25))
            expectThat(businessDays.businessDaysSubtract(monday4ofJanuary, 6)).isEqualTo(LocalDate.of(2020, Month.DECEMBER, 24))
            expectThat(businessDays.businessDaysSubtract(monday4ofJanuary, 7)).isEqualTo(LocalDate.of(2020, Month.DECEMBER, 23))
        }


    }
}


