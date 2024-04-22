package com.boiko.soundvibe.domain.usecases.app_entry

import com.boiko.soundvibe.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}