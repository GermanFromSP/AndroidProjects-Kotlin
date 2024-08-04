package com.bignerdranch.android.cft_test.domain

import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(id: Int) = repository.getUser(id)
}