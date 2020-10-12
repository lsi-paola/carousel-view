package com.app.gallery.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.app.gallery.R
import com.app.gallery.databinding.FragmentGalleryBinding
import com.synnapps.carouselview.ImageClickListener
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.custom_dialog.view.*
import kotlinx.android.synthetic.main.fragment_gallery.*


class GalleryFragment : Fragment() {

    var images = arrayListOf(
        R.drawable.image_1, R.drawable.image_2, R.drawable.image_3
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val binding = DataBindingUtil.inflate<FragmentGalleryBinding>(
            inflater,
            R.layout.fragment_gallery, container, false
        )
        binding.lifecycleOwner = this

        binding.carouselView.pageCount = images.size
        binding.carouselView.setImageListener(imageListener)
        binding.carouselView.setImageClickListener(carouselOnClick)

        return binding.root
    }

    private val imageListener =
        ImageListener { position, imageView -> imageView?.setImageResource(images[position]) }

    private val carouselOnClick = ImageClickListener {
        carouselView.setImageClickListener { position ->
            val builder: AlertDialog.Builder = AlertDialog.Builder(context!!)

            //Ok Button
            builder.setPositiveButton(
                "Ok"
            ) { dialog, _ ->
                dialog.dismiss()
            }

            val inflater = layoutInflater
            val dialogLayout: View = inflater.inflate(R.layout.custom_dialog, null)
            dialogLayout.imageView.setImageResource(images[position])

            builder.setView(dialogLayout)
            builder.show()
        }
    }
}


