package com.javahand.taipeiattractions.view

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.javahand.taipeiattractions.R
import com.javahand.taipeiattractions.databinding.FragmentAttractionBinding
import com.javahand.taipeiattractions.databinding.InfoRowBinding
import com.javahand.taipeiattractions.model.Category
import com.javahand.taipeiattractions.model.Friendly
import com.javahand.taipeiattractions.model.Service
import com.javahand.taipeiattractions.model.Target
import com.javahand.taipeiattractions.viewmodel.AllAttractionsViewModel
import com.javahand.taipeiattractions.viewmodel.AllAttractionsViewModelFactory

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AttractionFragment : Fragment() {

    private var _binding: FragmentAttractionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAttractionBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel by activityViewModels<AllAttractionsViewModel>(
            factoryProducer = {
                AllAttractionsViewModelFactory.provide(this)
            } // lambda
        ) // invoke

        viewModel.attraction.value?.peekContent()?.run {
            // ActionBar 上的景點名稱
            (activity as AppCompatActivity).supportActionBar?.let {
                it.title = name
            } // let

            // Image Pager2
            val imagesAdapter = ImagesAdapter(
                this@AttractionFragment,
                images.map { it.src }
            ) // constructor
            binding.pager2Images.adapter = imagesAdapter
            TabLayoutMediator(
                binding.tabImages,
                binding.pager2Images
            ) { _, _ -> }.attach()

            // 景點名稱
            binding.textName.text = name

            // 標題：景點介紹
            binding.subjIntro.setOnClickListener {
                subjectTextClicked(binding.textIntro, binding.subjIntro)
            } // setOnClickListener

            // 景點介紹內容
            binding.textIntro.text = introduction

            // 標題：景點資訊
            binding.subjInfo.setOnClickListener {
                subjectTextClicked(binding.panelInfo.root, binding.subjInfo)
            } // setOnClickListener

            // 景點資訊：主題分類
            setupInfoRow(
                category,
                binding.panelInfo.rowThemes,
                R.string.caption_themes
            ) // invoke

            // 景點資訊：友善認證
            setupInfoRow(
                friendly,
                binding.panelInfo.rowFriendly,
                R.string.caption_friendly
            ) // invoke

            // 景點資訊：服務設施
            setupInfoRow(
                service,
                binding.panelInfo.rowService,
                R.string.caption_service
            ) // invoke

            // 景點資訊：推薦對象
            setupInfoRow(
                target,
                binding.panelInfo.rowSubject,
                R.string.caption_subject
            ) // invoke

            // 景點資訊：推薦月份
            setupInfoRow(
                months,
                binding.panelInfo.rowMonths,
                R.string.caption_months
            ) // invoke

            // 景點資訊：開放時間
            setupInfoRow(
                openTime,
                binding.panelInfo.rowOpening,
                R.string.caption_opening
            ) // invoke

            // 景點資訊：建議停留時間
            setupInfoRow(
                stayTime,
                binding.panelInfo.rowStay,
                R.string.caption_stay
            ) // invoke

            // 景點資訊：收費門票
            setupInfoRow(
                ticket,
                binding.panelInfo.rowTickets,
                R.string.caption_tickets
            ) // invoke

            // 景點資訊：電話
            setupInfoRow(
                tel,
                binding.panelInfo.rowPhone,
                R.string.caption_phone
            ) // invoke

            // 景點資訊：傳真
            setupInfoRow(
                fax,
                binding.panelInfo.rowFax,
                R.string.caption_fax
            ) // invoke

            // 景點資訊：電子郵件
            setupInfoRow(
                email,
                binding.panelInfo.rowEmail,
                R.string.caption_email
            ) // invoke

            // 景點資訊：地址
            setupInfoRow(
                address,
                binding.panelInfo.rowAddr,
                R.string.caption_addr
            ) // invoke

            binding.panelInfo.rowAddr.textContent.setOnClickListener {
                val gmmString = "geo:$northLatitude,$eastLongitude?z=17"
                val gmmUri = Uri.parse(gmmString)
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            } // setOnClickListener

            // 區塊：小叮嚀
            if (remind.isEmpty()) {
                binding.blockReminder.visibility = View.GONE
            } else {
                // 標題：小叮嚀
                binding.subjReminder.setOnClickListener {
                    subjectTextClicked(
                        binding.textReminder,
                        binding.subjReminder
                    ) // invoke
                } // setOnClickListener

                // 小叮嚀內容
                binding.textReminder.text = remind
            } // if - else

            // 標題：相關連結
            binding.subjLinks.setOnClickListener {
                subjectTextClicked(binding.panelLinks, binding.subjLinks)
            } // setOnClickListener

            // 區塊：相關連結
            if (officialSite.isEmpty()
                && facebook.isEmpty()
                && links.isEmpty()
                && url.isEmpty()
            ) {
                binding.blockLinks.visibility = View.GONE
            } else {
                setupLink(officialSite, R.string.caption_official)
                setupLink(facebook, R.string.caption_facebook)
                links.forEach {
                    setupLink(it.src, it.subject)
                } // forEach
                setupLink(url, R.string.caption_taipei)
            } // if - else
        } // run
    } // fun onViewCreated( View, Bundle?)

    private fun setupLink(linkSrc: String, nameOrId: Any) {
        if (linkSrc.isNotEmpty()) {
            binding.panelLinks.addView(
                TextView(context).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    ).also {
                        it.topMargin = dx2Px(10)
                        it.leftMargin = dx2Px(26)
                    } // also
                    background = ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.shape_link_background,
                        null
                    ) // invoke
                    text = SpannableString(
                        if (nameOrId is Int) getString(
                            nameOrId
                        ) else nameOrId as String
                    ).also {
                        it.setSpan(
                            UnderlineSpan(),
                            0,
                            it.length,
                            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
                        ) // invoke
                    } // also
                    textSize = 15F
                    setCompoundDrawablesRelativeWithIntrinsicBounds(
                        null,
                        null,
                        ResourcesCompat.getDrawable(
                            resources,
                            if (
                                nameOrId == R.string.caption_facebook
                            ) R.drawable.ic_action_open_in_new
                            else R.drawable.ic_action_home,
                            null
                        ),
                        null
                    ) // invoke
                    setOnClickListener {
                        if (nameOrId is Int
                            && nameOrId == R.string.caption_facebook) {
                            val uri = Uri.parse(linkSrc)
                            startActivity(Intent(Intent.ACTION_VIEW, uri))
                        } else {
                            val bundle = bundleOf("linkSrc" to linkSrc)
                            findNavController().navigate(
                                R.id.action_AttractionFragment_to_RelatedLinkFragmen,
                                bundle
                            ) // invoke
                        } // if - else
                    } // setOnClickListener
                    setTextColor(Color.parseColor("#4444EE"))
                } // also
            ) // invoke
        } // if
    } // fun setupLink( String )

    private fun dx2Px(dx: Int) = (dx * resources.displayMetrics.density).toInt()

    private fun setupInfoRow(
        rowData: String,
        infoRowBinding: InfoRowBinding,
        stringId: Int
    ) {
        if (rowData.isEmpty()) {
            infoRowBinding.root.visibility = View.GONE
        } else {
            infoRowBinding.textCaption.text = resources.getString( stringId )
            infoRowBinding.textContent.text = if (
                stringId == R.string.caption_months
                && rowData == "01,07,02,08,03,09,04,10,05,11,06,12"
            ) {
                resources.getString(R.string.text_all_year)
            } else if (stringId == R.string.caption_phone) {
                "$rowData ☏"
            } else if (stringId == R.string.caption_email) {
                "$rowData ✉"
            } else if (stringId == R.string.caption_addr) {
                "$rowData ⌖"
            } else {
                rowData
            } // if - else
        } // if - else
    } // fun setupInfoRow(InfoRowBinding)

    private fun setupInfoRow(
        rowData: List<Any>,
        infoRowBinding: InfoRowBinding,
        stringId: Int
    ) {
        if (rowData.isEmpty()) {
            infoRowBinding.root.visibility = View.GONE
        } else {
            infoRowBinding.textCaption.text = resources.getString( stringId )
            infoRowBinding.textContent.text =
                rowData.joinToString(separator = "、") {
                    when (it) {
                        is Category -> "⁂ ${it.name}"
                        is Target -> "\uD83E\uDDCD${it.name}"
                        is Friendly -> "♡ ${it.name}"
                        is Service -> "☑ ${it.name}"
                        else -> ""
                    } // when
                } // joinToString
        } // if - else
    } // fun setupInfoRow(InfoRowBinding)

    private fun subjectTextClicked(panelView: View, subjectText: TextView ) {
        if (panelView.isVisible) {
            subjectText.compoundDrawablesRelative[0].level = 1
            panelView.visibility = View.GONE
        } else {
            subjectText.compoundDrawablesRelative[0].level = 0
            panelView.visibility = View.VISIBLE
        } // if - else
    } // fun subjectTextClicked()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}