package com.example.shoplist.presentation

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.shoplist.databinding.FragmentItemBinding
import com.example.shoplist.domain.ShopItem
import javax.inject.Inject
import kotlin.concurrent.thread

class ShopItemFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as ShopListApp).component
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ShopItemViewModel::class.java]
    }
    private var _binding: FragmentItemBinding? = null
    private var screenMode: String = MODE_UNKNOWN
    private var shopItemId: Int = ShopItem.NOT_DEFINED_ID
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentShopItemBinding == null")
    private lateinit var onEditingFinishedListener: OnEditingFinishedListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
        if (context is OnEditingFinishedListener) {
            onEditingFinishedListener = context
        } else {
            throw RuntimeException("Activity must implement OnEditingFinishedListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.noteViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        launchRightMode()
        onTextChanged()
        observeInputErrorsAndReadiness()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchRightMode() {
        when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
    }

    interface OnEditingFinishedListener {
        fun onEditingFinished()
    }

    private fun onTextChanged() {
        with(viewModel) {
            with(binding) {
                etName.doOnTextChanged { text, start, before, count -> resetErrorInputName() }
                etCount.doOnTextChanged { text, start, before, count -> resetErrorInputCount() }
            }
        }
    }

    private fun observeInputErrorsAndReadiness() {
        viewModel.itemIsAlready.observe(viewLifecycleOwner) {
            onEditingFinishedListener.onEditingFinished()
        }

    }

    private fun launchEditMode() {
        with(viewModel) {
            getShopItem(shopItemId)
            with(binding) {
                tvTitle.text = "Edit note:"
                btnSave.setOnClickListener {
                    editShopItem(etName.text.toString(), etCount.text.toString())
                }
            }
        }
    }

    private fun launchAddMode() {
        with(binding) {
            tvTitle.text = "New note:"
            btnSave.setOnClickListener {
                viewModel.addShopItem(etName.text.toString(), etCount.text.toString())
            }
        }
    }


    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Unknown screen mode ")
        }
        val mode = args.getString(EXTRA_SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("Unknown screen mode: $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!args.containsKey(EXTRA_SHOP_ITEM_ID)) {
                throw RuntimeException("Params shop item id is not found ")
            }
            shopItemId = args.getInt(EXTRA_SHOP_ITEM_ID, ShopItem.NOT_DEFINED_ID)
        }
    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = ""
        private const val LOG = "Fragment"

        fun newInstanceAddItem(): ShopItemFragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_SCREEN_MODE, MODE_ADD)
                }
            }
        }

        fun newInstanceEditItem(shopItemId: Int): ShopItemFragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_SCREEN_MODE, MODE_EDIT)
                    putInt(EXTRA_SHOP_ITEM_ID, shopItemId)
                }
            }
        }
    }
}