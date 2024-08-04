package com.bignerdranch.android.cft_test.domain

import javax.inject.Inject

class UpdateUserListUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke() = repository.updateUserList()
}