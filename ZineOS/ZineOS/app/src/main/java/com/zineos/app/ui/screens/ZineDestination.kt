package com.zineos.app.ui.screens

sealed class ZineDestination(val route: String) {
    data object Feed : ZineDestination("feed")
    data object Preferences : ZineDestination("preferences")
}
