package com.smolianinovasiuzanna.movielist.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils.loadAnimation
import androidx.navigation.fragment.findNavController
import com.smolianinovasiuzanna.movielist.R
import com.smolianinovasiuzanna.movielist.databinding.FragmentSplashBinding
import com.smolianinovasiuzanna.movielist.utils.ViewBindingFragment


class SplashFragment : ViewBindingFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAnimations()
        navigate()
    }
    private fun initAnimations(){
        binding.fullscreenContent.animation = loadAnimation(requireContext(), R.anim.myscale)
        binding.roundedImageView.animation = loadAnimation(requireContext(), R.anim.myalpha)
    }

    private fun navigate(){
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToListFragment())
                                                    }, 3000)
    }
}