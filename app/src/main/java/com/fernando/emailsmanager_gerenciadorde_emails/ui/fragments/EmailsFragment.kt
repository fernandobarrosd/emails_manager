package com.fernando.emailsmanager_gerenciadorde_emails.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fernando.emailsmanager_gerenciadorde_emails.databinding.FragmentEmailsBinding
import com.fernando.emailsmanager_gerenciadorde_emails.ui.adapters.EmailAdapter
import com.fernando.emailsmanager_gerenciadorde_emails.ui.viewmodels.EmailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmailsFragment : Fragment() {
    private var binding: FragmentEmailsBinding? = null
    private val emailsViewModel: EmailsViewModel by viewModels()

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
        emailsViewModel.getAllEmails()
    }

    private fun initObservers() {
        emailsViewModel.emails.observe(viewLifecycleOwner) { emails ->
            binding?.let { binding ->
                if (emails.isEmpty()) {
                    showWarningFragment(binding)
                    return@let
                }
                val emailAdapter = EmailAdapter(emails) {}
                binding.emailRecyclerView.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = emailAdapter
                }
            }
        }
    }

    private fun showWarningFragment(binding: FragmentEmailsBinding) {
        parentFragmentManager.commit {
            replace(binding.warningFragment.id, WarningFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}