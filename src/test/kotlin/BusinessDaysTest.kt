import fixtures.DaysFixture
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.all
import strikt.assertions.isEqualTo
import java.time.LocalDate
import java.time.Month

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
        with(DaysFixture){
            val businessDays = BusinessDays(holidays = { setOf(tuesday5ofJanuary) })

            expectThat(businessDays.businessDaysAdd(monday4ofJanuary, 1)).isEqualTo(wednesday6ofJanuary)
            expectThat(businessDays.businessDaysAdd(monday4ofJanuary, 2)).isEqualTo(thursday7ofJanuary)
            expectThat(businessDays.businessDaysAdd(monday4ofJanuary, 3)).isEqualTo(friday8ofJanuary)

            expectThat(businessDays.businessDaysAdd(monday4ofJanuary, 4)).isEqualTo(monday11ofJanuary)
        }

    }

    @Test
    fun `given BusinessDays with a holiday, when retrieving next day, should skip holiday and weekends`() {
        with(DaysFixture){
            val businessDays = BusinessDays(holidays = { setOf(tuesday5ofJanuary) })
            expectThat(businessDays.nextBusinessDay(monday4ofJanuary)).isEqualTo(wednesday6ofJanuary)
            expectThat(businessDays.nextBusinessDay(tuesday5ofJanuary)).isEqualTo(wednesday6ofJanuary)
            expectThat(businessDays.nextBusinessDay(wednesday6ofJanuary)).isEqualTo(thursday7ofJanuary)
            expectThat(businessDays.nextBusinessDay(thursday7ofJanuary)).isEqualTo(friday8ofJanuary)
            expectThat(businessDays.nextBusinessDay(friday8ofJanuary)).isEqualTo(monday11ofJanuary)
            expectThat(businessDays.nextBusinessDay(saturday9ofJanuary)).isEqualTo(monday11ofJanuary)
            expectThat(businessDays.nextBusinessDay(sunday10ofJanuary)).isEqualTo(monday11ofJanuary)
        }
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

    @Test
    fun `given BusinessDays with a holiday, when retrieving previous day, should skip holiday and weekends`() {
        with(DaysFixture){
            val businessDays = BusinessDays(holidays = { setOf(tuesday5ofJanuary) })
            expectThat(businessDays.previousBusinessDay(tuesday5ofJanuary)).isEqualTo(monday4ofJanuary)
            expectThat(businessDays.previousBusinessDay(wednesday6ofJanuary)).isEqualTo(monday4ofJanuary)
            expectThat(businessDays.previousBusinessDay(thursday7ofJanuary)).isEqualTo(wednesday6ofJanuary)
            expectThat(businessDays.previousBusinessDay(friday8ofJanuary)).isEqualTo(thursday7ofJanuary)
            expectThat(businessDays.previousBusinessDay(saturday9ofJanuary)).isEqualTo(friday8ofJanuary)
            expectThat(businessDays.previousBusinessDay(sunday10ofJanuary)).isEqualTo(friday8ofJanuary)
            expectThat(businessDays.previousBusinessDay(monday11ofJanuary)).isEqualTo(friday8ofJanuary)
        }
    }

    @Test
    fun `given BusinessDays with a holiday, when calculating business days between dates, should skip holiday and weekends`() {
        with(DaysFixture){
            val businessDays = BusinessDays(holidays = { setOf(wednesday6ofJanuary) })

            expectThat(businessDays.businessDaysBetween(monday4ofJanuary, monday4ofJanuary)).isEqualTo(0)
            expectThat(businessDays.businessDaysBetween(monday4ofJanuary, tuesday5ofJanuary)).isEqualTo(1)
            expectThat(businessDays.businessDaysBetween(monday4ofJanuary, wednesday6ofJanuary)).isEqualTo(1)
            expectThat(businessDays.businessDaysBetween(monday4ofJanuary, thursday7ofJanuary)).isEqualTo(2)
            expectThat(businessDays.businessDaysBetween(monday4ofJanuary, friday8ofJanuary)).isEqualTo(3)
            expectThat(businessDays.businessDaysBetween(monday4ofJanuary, saturday9ofJanuary)).isEqualTo(3)
            expectThat(businessDays.businessDaysBetween(monday4ofJanuary, sunday10ofJanuary)).isEqualTo(3)
            expectThat(businessDays.businessDaysBetween(monday4ofJanuary, monday11ofJanuary)).isEqualTo(4)
        }
    }

    @Test
    fun `given BusinessDays with a holiday and start date being grater than end date, when calculating business days between dates , should skip holiday and weekends`() {
        with(DaysFixture){
            val businessDays = BusinessDays(holidays = { setOf(wednesday6ofJanuary) })

            expectThat(businessDays.businessDaysBetween(monday4ofJanuary, monday4ofJanuary)).isEqualTo(0)
            expectThat(businessDays.businessDaysBetween(tuesday5ofJanuary, monday4ofJanuary)).isEqualTo(-1)
            expectThat(businessDays.businessDaysBetween(wednesday6ofJanuary, monday4ofJanuary)).isEqualTo(-2)
            expectThat(businessDays.businessDaysBetween(thursday7ofJanuary, monday4ofJanuary)).isEqualTo(-2)
            expectThat(businessDays.businessDaysBetween(friday8ofJanuary, monday4ofJanuary)).isEqualTo(-3)
            expectThat(businessDays.businessDaysBetween(saturday9ofJanuary, monday4ofJanuary)).isEqualTo(-4)
            expectThat(businessDays.businessDaysBetween(sunday10ofJanuary, monday4ofJanuary)).isEqualTo(-4)
            expectThat(businessDays.businessDaysBetween(monday11ofJanuary, monday4ofJanuary)).isEqualTo(-4)
        }
    }
}


