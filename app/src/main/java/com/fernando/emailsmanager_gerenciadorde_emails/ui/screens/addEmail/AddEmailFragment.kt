package com.fernando.emailsmanager_gerenciadorde_emails.ui.screens.addEmail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.fernando.emailsmanager_gerenciadorde_emails.R
import com.fernando.emailsmanager_gerenciadorde_emails.databinding.FragmentAddEmailBinding
import com.fernando.emailsmanager_gerenciadorde_emails.models.Email
import com.fernando.emailsmanager_gerenciadorde_emails.ui.extensions.observeLiveData
import com.fernando.emailsmanager_gerenciadorde_emails.ui.extensions.getNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEmailFragment : Fragment() {
    private val addEmailViewModel: AddEmailViewModel by viewModels()
    private var binding: FragmentAddEmailBinding? = null
    private val navController : NavController by getNavController()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentAddEmailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initListeners()
    }

    private fun initListeners() {
        binding?.btnAddEmail?.setOnClickListener {
            val name = binding?.nameEditText?.getText().toString()
            val emailText = binding?.emailEditText?.getText().toString()
            val email = Email(name, emailText)

            addEmailViewModel.saveEmail(email)
        }
    }

    private fun backToEmailsScreen() {
        findNavController().navigate(R.id.fromAddFragmentToHomeFragment)
    }

    private fun initObservers() {
        observeLiveData(addEmailViewModel.invalidNameErrorMessage) { invalidNameErrorMessage ->
            if (invalidNameErrorMessage == null) {
                binding?.nameEditText?.hideError()
                return@observeLiveData
            }

            binding?.nameEditText?.showError()
            binding?.nameEditText?.setErrorText(invalidNameErrorMessage)
        }
        observeLiveData(addEmailViewModel.invalidEmailErrorMessage) { invalidEmailErrorMessage ->
            if (invalidEmailErrorMessage == null) {
                binding?.emailEditText?.hideError()
                return@observeLiveData
            }
            binding?.emailEditText?.showError()
            binding?.emailEditText?.setErrorText(invalidEmailErrorMessage)
        }
        observeLiveData(addEmailViewModel.errorText) { errorText ->
            if (errorText == null) {
                binding?.addEmailErrorText?.visibility = View.INVISIBLE
                return@observeLiveData
            }
            binding?.addEmailErrorText?.visibility = View.VISIBLE
            binding?.addEmailErrorText?.text = requireContext().getString(errorText)
        }
        observeLiveData(addEmailViewModel.isSavedWithSuccess) { isSavedWithSuccess ->
            if (isSavedWithSuccess) {
                AlertDialog.Builder(requireContext())
                    .setMessage("E-mail salvo com sucesso")
                    .setPositiveButton("Ok") { dialog, _ ->
                        dialog.dismiss()
                        backToEmailsScreen()
                    }
                    .show()
            }
        }
    }
}