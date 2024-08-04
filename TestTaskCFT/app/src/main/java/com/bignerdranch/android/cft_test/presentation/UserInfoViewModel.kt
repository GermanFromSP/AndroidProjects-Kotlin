package com.bignerdranch.android.cft_test.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bignerdranch.android.cft_test.di.annotations.IdQualifier
import com.bignerdranch.android.cft_test.domain.GetUserUseCase
import com.bignerdranch.android.cft_test.domain.User
import javax.inject.Inject

class UserInfoViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    @IdQualifier id: Int,
) : ViewModel() {

    val userFromDb: LiveData<User> = getUserUseCase.invoke(id).asLiveData()
}
