package com.template.activityresultcontractssample

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    // StartActivityResultの実装
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            // コールバックとしてActivity Resultを受け取れる.
            // 呼び出し先のActivityが閉じたときに呼び出される.
            Log.d("andoleo", "resultCode: " + it.resultCode)
            Log.d("andoleo", "data: " + it.data?.getStringExtra("key"))
        }
    private val launcher2 = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        Log.d("andoleo", "permission grant: $it")
        if (it) {
            Toast.makeText(requireContext(), "xxここで権限が必要な処理を行う.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
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
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_CONTACTS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(requireContext(), "----ここで権限が必要な処理を行う.", Toast.LENGTH_SHORT).show()
            } else {
                launcher2.launch(Manifest.permission.READ_CONTACTS)
            }
        }
    }
}