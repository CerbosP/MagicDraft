package com.example.magicdraft.view.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.magicdraft.viewmodel.MagicViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class MagicViewModelFragment: Fragment() {
    protected val viewModel: MagicViewModel by activityViewModels()
}