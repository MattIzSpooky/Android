package com.avans.assessment.ui

class Routes {
    companion object {
        const val CONTACTS = "contacts"
        const val HOME = "home"
        const val FAVORITES = "favorites"
        const val DETAIL = "detail/{id}"
        const val SEARCH = "search"

        fun compoundUrl(route: String, param: String, variable: String): String {
            return route.replace("{$param}", variable)
        }
    }
}