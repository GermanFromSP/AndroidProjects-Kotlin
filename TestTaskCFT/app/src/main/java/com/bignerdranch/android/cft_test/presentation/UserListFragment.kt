package com.bignerdranch.android.cft_test.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.cft_test.R
import com.bignerdranch.android.cft_test.databinding.FragmentUserListBinding
import com.bignerdranch.android.cft_test.domain.User
import com.bignerdranch.android.cft_test.presentation.recycler_view.UserListAdapter
import com.google.android.material.snackbar.Snackbar
import java.lang.RuntimeException
import javax.inject.Inject

private const val SET_WELCOME_SCREEN = true
private const val REMOVE_WELCOME_SCREEN = false
private const val VIEW_BINDING_IS_NULL = "FragmentUserListBinding is null"
private const val FRAGMENT_NAME = "FragmentUserListBinding"


class UserListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as UserApplication).component
            .fragmentComponentFactory()
            .create()
    }

    private var _binding: FragmentUserListBinding? = null
    private val binding: FragmentUserListBinding
        get() = _binding ?: throw RuntimeException(VIEW_BINDING_IS_NULL)

    private val usersAdapter by lazy {
        UserListAdapter()
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[UserListViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        setupOnClickListeners()
    }

    private fun observeViewModel() {
        viewModel.usersList.observe(viewLifecycleOwner) { usersList ->
            setupRecyclerView(usersList)

            if (usersList.isEmpty()) {
                setWelcomeScreen(SET_WELCOME_SCREEN)
            } else {
                setWelcomeScreen(REMOVE_WELCOME_SCREEN)
            }
        }

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.ConnectionError -> {
                    showErrorMessage(getString(R.string.network_error))
                }

                is State.OtherError -> {
                    showErrorMessage(state.message)
                }
            }
        }
    }

    private fun showErrorMessage(message: String) {
        Snackbar.make(binding.frameLayoutAllUsers, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun setupOnClickListeners() {
        with(binding) {
            btnUpdateList.setOnClickListener {
                viewModel.updateUserList()
            }

            btnFindPeople.setOnClickListener {
                viewModel.updateUserList()
            }
        }
    }

    private fun setupRecyclerView(userList: List<User>) {
        usersAdapter.submitList(userList)
        binding.rvUsersList.adapter = usersAdapter

        usersAdapter.onUserItemClickListener = {
            launchUserInfoFragment(it.id)
        }
    }

    private fun launchUserInfoFragment(id: Int) {
        val fragment = UserInfoFragment.newInstance(id)
        parentFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, fragment)
            .addToBackStack(FRAGMENT_NAME)
            .commit()
    }

    private fun setWelcomeScreen(set: Boolean) {
        with(binding) {
            if (set) {
                rvUsersList.visibility = View.GONE
                btnFindPeople.visibility = View.VISIBLE
                tvWelcome.visibility = View.VISIBLE
                btnUpdateList.visibility = View.INVISIBLE
            } else {
                rvUsersList.visibility = View.VISIBLE
                btnFindPeople.visibility = View.GONE
                tvWelcome.visibility = View.GONE
                btnUpdateList.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}