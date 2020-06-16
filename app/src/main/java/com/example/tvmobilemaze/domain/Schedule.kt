package com.example.tvmobilemaze.domain

class Schedule(val time: String, val days: List<String>) {
    override fun toString(): String {
        var exhibitionDays = ""
        if (days.size == 1) {
            exhibitionDays = exhibitionDays.plus(days.first())
        } else {
            for (day in days) {
                exhibitionDays = when (day) {
                    days.last() -> {
                        exhibitionDays.plus(" and $day")
                    }
                    days.first() -> {
                        exhibitionDays.plus(day)
                    }
                    else -> {
                        exhibitionDays.plus(", $day")
                    }
                }
            }
        }

        return "Every $exhibitionDays at $time"
    }
}