package com.bmnmrls.mynotes.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import com.bmnmrls.mynotes.databinding.ActivitySplashBinding
import com.bmnmrls.mynotes.home.views.activity.HomeActivity
import timber.log.Timber

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        Timber.tag(TAG).d("setupUI() called")
        binding.splashMotionLayout.setTransitionListener(
                object : MotionLayout.TransitionListener {
                    override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) = Unit

                    override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) = Unit

                    override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                        HomeActivity.launch(this@SplashActivity)
                        finish()
                    }

                    override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) = Unit
                }
        )
    }

    companion object {
        private val TAG = SplashActivity::class.java.name
    }

}