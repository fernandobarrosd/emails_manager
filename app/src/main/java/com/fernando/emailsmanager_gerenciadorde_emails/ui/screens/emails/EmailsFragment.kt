package com.fernando.emailsmanager_gerenciadorde_emails.ui.screens.emails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fernando.emailsmanager_gerenciadorde_emails.R
import com.fernando.emailsmanager_gerenciadorde_emails.databinding.FragmentEmailsBinding
import com.fernando.emailsmanager_gerenciadorde_emails.ui.extensions.getNavController
import com.fernando.emailsmanager_gerenciadorde_emails.ui.extensions.observeLiveData
import com.fernando.emailsmanager_gerenciadorde_emails.ui.extensions.setVisibilityToGone
import com.fernando.emailsmanager_gerenciadorde_emails.ui.extensions.setVisibilityToVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmailsFragment : Fragment() {
    private var binding: FragmentEmailsBinding? = null
    private val emailsViewModel: EmailsViewModel by viewModels()

    private val navController: NavController by getNavController()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentEmailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initListeners()
        emailsViewModel.getAllEmails()
    }

    private fun initListeners() {
        binding?.btnAddEmail?.setOnClickListener { goToAddEmailScreen() }
    }

    private fun goToAddEmailScreen() {
        navController.navigate(R.id.fromHomeFragmentToAddEmailFragment)
    }


    private fun initObservers() {
        observeLiveData(emailsViewModel.emails) { emails ->
            val emailAdapter = EmailAdapter(emails) {
                Toast.makeText(requireContext(), it.name, Toast.LENGTH_LONG).show()
            }
            binding?.emailRecyclerView?.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = emailAdapter
            }
        }
        observeLiveData(emailsViewModel.showingState) { showingState ->
            if (showingState == ShowingState.WARNING) {
                hideEmailsRecyclerView()
                showWarningFragment()
                return@observeLiveData
            }
            hideWarningFragment()
            showEmailsRecyclerView()
        }
        observeLiveData(emailsViewModel.isLoading) { isLoading ->
            when(isLoading) {
                true -> {
                    binding?.loadingProgressBar?.visibility = View.VISIBLE
                    hideWarningFragment()
                    hideEmailsRecyclerView()
                }
                false -> {
                    binding?.loadingProgressBar?.visibility = View.INVISIBLE
                }
            }
        }
    }
    private fun showEmailsRecyclerView() {
        binding?.emailRecyclerView?.setVisibilityToVisible()
    }

    private fun hideEmailsRecyclerView() {
        binding?.emailRecyclerView?.setVisibilityToGone()
    }

    private fun showWarningFragment() {
        binding?.warningFragment?.setVisibilityToVisible()
    }

    private fun hideWarningFragment() {
        binding?.warningFragment?.setVisibilityToGone()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}