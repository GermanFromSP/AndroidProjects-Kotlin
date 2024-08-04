package com.bignerdranch.android.cft_test.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.cft_test.domain.GetUserListUseCase
import com.bignerdranch.android.cft_test.domain.UpdateUserListUseCase
import com.bignerdranch.android.cft_test.domain.User
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "UsersListViewModel"

class UserListViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase,
    private val updateUserListUseCase: UpdateUserListUseCase,
    private val networkConnectivityChecker: NetworkConnectivityChecker,
) : ViewModel() {

    private var _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    val usersList: LiveData<List<User>> = getUserListUseCase.invoke().asLiveData()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _state.postValue(
            if (!networkConnectivityChecker.checkInternetConnection()) {
                State.ConnectionError
            } else {
                State.OtherError(throwable.message.toString())
            }
        )
        Log.d(TAG, throwable.message.toString())
    }

    fun updateUserList() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            updateUserListUseCase.invoke()
        }
    }
}