package com.bignerdranch.android.cft_test.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.cft_test.R
import com.bignerdranch.android.cft_test.databinding.FragmentUserInfoBinding
import com.bignerdranch.android.cft_test.domain.User
import com.squareup.picasso.Picasso
import javax.inject.Inject

private const val VIEW_BINDING_IS_NULL = "FragmentUserInfoBinding is null"
private const val ARG_USER_ID = "user_id"
private const val PHONE_CALL_URI_SCHEME = "tel:"
private const val MAP_URI_SCHEME_WITH_COORDINATE = "geo:"
private const val MAP_URI_SCHEME_WITH_ADDRESS = "geo:0,0?q="
private const val EMAIL_URI_SCHEME = "mailto:"

class UserInfoFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        arguments?.getInt(ARG_USER_ID)?.let {
            (requireActivity().application as UserApplication).component
                .fragmentComponentFactory()
                .create(it)
        }
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[UserInfoViewModel::class.java]
    }
    private var _binding: FragmentUserInfoBinding? = null
    private val binding: FragmentUserInfoBinding
        get() = _binding ?: throw RuntimeException(VIEW_BINDING_IS_NULL)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component?.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        setupOnClickListeners()
    }

    private fun observeViewModel() {
        viewModel.userFromDb.observe(viewLifecycleOwner) { user ->
            setupValueInHeaderBlock(user)
            setupValueInRegistrationDataBlock(user)
            setupValueInContactsBlock(user)
            setupValueInLocationBlock(user)
        }
    }

    private fun setupOnClickListeners() {

        with(binding.contactsInfoBlock) {
            tvEmail.setOnClickListener { getEmailDeal(tvEmail.text.toString()) }
            tvPhone.setOnClickListener { getPhoneCallDeal(tvPhone.text.toString()) }
            tvCellphone.setOnClickListener { getPhoneCallDeal(tvCellphone.text.toString()) }
        }

        with(binding.locationInfoBlock) {

            tvCityStateStreet.setOnClickListener {
                getMapDealForAddress(tvCityStateStreet.text.toString())
            }
            tvCoordinates.setOnClickListener {
                getMapDealForCoordinates(tvCoordinates.text.toString())
            }
        }
    }

    private fun setupValueInHeaderBlock(user: User) {
        with(binding.headerBlock) {
            ivProfilePicture.apply {
                Picasso.get()
                    .load(user.profilePictures?.largeSize)
                    .into(this)
            }
            tvFullName.text = String.format(
                requireContext().getString(R.string.user_list_full_name),
                user.userName?.title,
                user.userName?.firstName,
                user.userName?.lastName,
            )

            tvGeneralInfo.text = String.format(
                requireContext().getString(R.string.general_info),
                user.gender,
                user.birthdayDate?.age,
                user.birthdayDate?.birthDate?.substringBefore("T")
            )
        }
    }

    private fun setupValueInRegistrationDataBlock(user: User) {
        with(binding.registerDateBlock) {
            tvRegistrationDate.text = String.format(
                requireContext().getString(R.string.registration_date_profile),
                user.registrationDate?.yearsAgo,
                user.registrationDate?.registerDate?.substringBefore("T"),
            )
        }
    }

    private fun setupValueInContactsBlock(user: User) {
        with(binding.contactsInfoBlock) {
            tvLogin.text = user.login?.username
            tvEmail.text = user.email
            tvPhone.text = user.phone
            tvCellphone.text = user.cell
        }
    }

    private fun setupValueInLocationBlock(user: User) {
        with(binding.locationInfoBlock) {
            tvTimezone.text = user.userLocation?.timezone?.offset

            tvCountry.text = user.userLocation?.country
            tvCityStateStreet.text = String.format(
                requireContext().getString(
                    R.string.city_state_street_value,
                    user.userLocation?.city,
                    user.userLocation?.state,
                    user.userLocation?.street?.number.toString(),
                    user.userLocation?.street?.name,
                )
            )
            tvCityStateStreet.isSelected = true

            tvCoordinates.text = String.format(
                requireContext().getString(R.string.coordinates_value),
                user.userLocation?.coordinates?.latitude,
                user.userLocation?.coordinates?.longitude,
            )
        }
    }

    private fun getPhoneCallDeal(number: String) {
        Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse(PHONE_CALL_URI_SCHEME + number)
            startActivity(this)
        }
    }

    private fun getMapDealForCoordinates(location: String) {
        Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(MAP_URI_SCHEME_WITH_COORDINATE + location)
            startActivity(this)
        }
    }

    private fun getMapDealForAddress(location: String) {
        Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(MAP_URI_SCHEME_WITH_ADDRESS + location)
            startActivity(this)
        }
    }

    private fun getEmailDeal(email: String) {
        Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse(EMAIL_URI_SCHEME + email)
            startActivity(this)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance(userId: Int) =
            UserInfoFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_USER_ID, userId)
                }
            }
    }
}