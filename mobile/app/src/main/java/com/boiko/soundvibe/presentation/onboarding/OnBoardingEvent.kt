package com.boiko.soundvibe.presentation.onboarding

sealed class OnBoardingEvent {
    data object SignUp: OnBoardingEvent()
    data object LogIn: OnBoardingEvent()
}