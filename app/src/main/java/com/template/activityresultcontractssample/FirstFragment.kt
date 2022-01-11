package com.template.activityresultcontractssample

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(R.layout.fragment_first) {
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            Log.d("FirstFragment", "resultCode: " + it.resultCode)
            Log.d("FirstFragment", "data: " + it.data?.getStringExtra("key"))
        }
    private val launcher2 = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        Log.d("FirstFragment", "permission grant: $it")
        if (it) {
            Toast.makeText(requireContext(), "permission grant: $it", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        view.findViewById<Button>(R.id.button_launch_second_activity).setOnClickListener {
            launcher.launch(Intent(requireContext(), SecondActivity::class.java))
        }
        view.findViewById<Button>(R.id.button_request_permission).setOnClickListener {
            launcher2.launch(Manifest.permission.READ_CONTACTS)
        }
    }
}