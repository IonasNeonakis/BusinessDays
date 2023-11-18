import fixtures.DaysFixture
import org.junit.jupiter.api.Nested
import strikt.api.expectThat
import strikt.assertions.all
import strikt.assertions.isEqualTo
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
        fun `given default BusinessDays, when calling 'isWeedDay' with dates in week should return true`() {
            with(DaysFixture) {
                expectThat(aMondaysToFridaysWeek).all {
                    get { BusinessDays.isWeekday(this) }.isEqualTo(true)
                }
            }
        }

        @Test
        fun `given default BusinessDays, when calling 'isWeedDay' with dates in weekend should return false`() {

            BusinessDays

            with(DaysFixture) {
                expectThat(aWeekend).all {
                    get { BusinessDays.isWeekday(this) }.isEqualTo(false)
                }
            }
        }
    }
}


