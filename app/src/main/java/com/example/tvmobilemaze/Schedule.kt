package com.example.tvmobilemaze

class Schedule(val time: String, val days: List<String>) {
    override fun toString(): String {
        var exhibitionDays = ""
        for (day in days) {
            exhibitionDays = if (day == days.last()) {
                exhibitionDays.plus(" and $day")
            } else {
                exhibitionDays.plus(day)
            }
        }

        return "Every $exhibitionDays at $time"
    }
}