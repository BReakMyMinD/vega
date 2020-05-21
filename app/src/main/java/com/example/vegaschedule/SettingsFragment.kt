package com.example.vegaschedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_settings.*

/**
 * A simple [Fragment] subclass.
 */
class SettingsFragment(private val activity: MainActivity) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }
    companion object{
        @JvmStatic
        fun newInstance(param: MainActivity) = SettingsFragment(param)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(spinner3 != null) {
            val adapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, activity.scheduleInstance.getGroups())
            spinner3.adapter = adapter
        }
        if(spinner4 != null) {
            val adapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, activity.scheduleInstance.getSubgroups())
            spinner4.adapter = adapter
        }

        spinner3.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                activity.settingsStorage.saveGroup(selectedItem)
            }
        }
        spinner4.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position) as Int
                activity.settingsStorage.saveSubgroup(selectedItem)
            }
        }
    }
}
