# BusinessDays

The **BusinessDays** project provides utility classes for working with business days, taking into account weekends and holidays. It includes a general-purpose class, `BusinessDays`, and a specialized class for handling business days in France, `BusinessDaysFrance`.

## BusinessDays Class

### Overview

The `BusinessDays` class is a utility for working with business days, considering weekends and holidays. It includes functionality to check if a given date is a holiday, a business day, and methods to add or subtract business days from a given date.

### Usage

```kotlin
// Initialize BusinessDays with default holidays (empty list)
val businessDays = BusinessDays()

// Check if a date is a business day
val isBusinessDay = businessDays.isBusinessDay(LocalDate.now())

// Add 5 business days to the current date
val newDate = businessDays.businessDaysAdd(LocalDate.now(), 5)
```

### Methods

- `isHoliday(date: LocalDate): Boolean`: Checks if a given date is a holiday.
- `isBusinessDay(date: LocalDate): Boolean`: Checks if a given date is a business day, considering both weekdays and holidays.
- `businessDaysAdd(date: LocalDate, days: Long): LocalDate`: Adds a specified number of business days to the given date.
- `businessDaysSubtract(date: LocalDate, days: Long): LocalDate`: Subtracts a specified number of business days from the given date.

### Companion Object Methods

- `isWeekendDay(date: LocalDate): Boolean`: Checks if a given date is a weekend day (Saturday or Sunday).
- `isWeekday(date: LocalDate): Boolean`: Checks if a given date is a weekday (Monday through Friday).

## BusinessDaysFrance Class

### Overview

The `BusinessDaysFrance` class is a specialization of `BusinessDays` tailored for handling business days in France. It includes additional French holidays such as New Year's Eve, Easter Monday, Labor Day, and more.

### Usage

```kotlin
// Initialize BusinessDaysFrance with default additional holidays (empty list)
val businessDaysFrance = BusinessDaysFrance()

// Check if a date is a business day in France
val isBusinessDayFrance = businessDaysFrance.isBusinessDay(LocalDate.now())
```

### Additional Holidays in France

The class includes the following additional holidays:

- New Year's Day
- Easter Monday
- Labor Day
- Victory in Europe Day
- Ascension Day
- Pentecost
- Bastille Day
- Assumption Day
- All Saints' Day
- Armistice Day
- Christmas Day

### Companion Object Methods

- Methods to calculate the date of specific French holidays.

## Customization

Both classes allow customization through optional parameters. For example, you can provide a custom list of holidays when initializing the classes.

```kotlin
val customHolidays = listOf(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 7, 4))
val customBusinessDays = BusinessDays(holidays = { customHolidays })
```

Feel free to explore and utilize these utility classes for managing business days in your projects!

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
