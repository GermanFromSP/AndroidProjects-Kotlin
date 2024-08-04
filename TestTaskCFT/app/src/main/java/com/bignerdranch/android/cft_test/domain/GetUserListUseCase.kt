package com.bignerdranch.android.cft_test.domain

import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke() = repository.getUserList()
}