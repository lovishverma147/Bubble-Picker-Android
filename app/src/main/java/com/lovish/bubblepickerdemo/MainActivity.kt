package com.lovish.bubblepickerdemo

import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.lovish.bubblepicker.BubblePickerListener
import com.lovish.bubblepicker.adapter.BubblePickerAdapter
import com.lovish.bubblepicker.model.BubbleGradient
import com.lovish.bubblepicker.model.PickerItem
import com.lovish.bubblepickerdemo.databinding.ActivityMainBinding

/**
 * Created by lovish on 22/03/24.
 */

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val boldTypeface by lazy { Typeface.createFromAsset(assets, ROBOTO_BOLD) }
    private val mediumTypeface by lazy { Typeface.createFromAsset(assets, ROBOTO_MEDIUM) }
    private val regularTypeface by lazy { Typeface.createFromAsset(assets, ROBOTO_REGULAR) }

    companion object {
        private const val ROBOTO_BOLD = "roboto_bold.ttf"
        private const val ROBOTO_MEDIUM = "roboto_medium.ttf"
        private const val ROBOTO_REGULAR = "roboto_regular.ttf"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.apply {
            titleTextView.typeface = mediumTypeface
            subtitleTextView.typeface = boldTypeface
            hintTextView.typeface = regularTypeface
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                subtitleTextView.letterSpacing = 0.06f
                hintTextView.letterSpacing = 0.05f
            }

            val titles = resources.getStringArray(R.array.countries)
            val colors = resources.obtainTypedArray(R.array.colors)
            val images = resources.obtainTypedArray(R.array.images)

            picker.adapter = object : BubblePickerAdapter {

                override val totalCount = titles.size

                override fun getItem(position: Int): PickerItem {
                    return PickerItem().apply {
                        title = titles[position]
                        gradient = BubbleGradient(colors.getColor((position * 2) % 8, 0),
                            colors.getColor((position * 2) % 8 + 1, 0), BubbleGradient.VERTICAL)
                        typeface = mediumTypeface
                        textColor = ContextCompat.getColor(this@MainActivity, android.R.color.white)
                        backgroundImage = ContextCompat.getDrawable(this@MainActivity, images.getResourceId(position, 0))
                    }
                }
            }

            colors.recycle()
            images.recycle()

            picker.bubbleSize = 20
            picker.listener = object : BubblePickerListener {
                override fun onBubbleSelected(item: PickerItem) = toast("${item.title} selected")

                override fun onBubbleDeselected(item: PickerItem) = toast("${item.title} deselected")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.picker.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.picker.onPause()
    }

    private fun toast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

}