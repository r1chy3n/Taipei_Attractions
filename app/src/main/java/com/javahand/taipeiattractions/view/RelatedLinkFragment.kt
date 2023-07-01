package com.javahand.taipeiattractions.view

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.ClientCertRequest
import android.webkit.HttpAuthHandler
import android.webkit.RenderProcessGoneDetail
import android.webkit.SafeBrowsingResponse
import android.webkit.SslErrorHandler
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.javahand.taipeiattractions.databinding.FragmentRelatedLinkBinding

class RelatedLinkFragment: Fragment() {

    private var _binding: FragmentRelatedLinkBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRelatedLinkBinding.inflate(
            inflater,
            container,
            false
        ) // invoke

        return binding.root
    } // fun onCreateView( LayoutInflater, ViewGroup?, Bundle?)

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString("linkSrc")?.let {
            binding.webView.settings.javaScriptEnabled = true
            binding.webView.webViewClient = WebViewClient()
//            binding.webView.webViewClient = object: WebViewClient() {
//                override fun shouldOverrideUrlLoading(
//                    view: WebView?,
//                    url: String?
//                ): Boolean {
//
//Log.d("JavaHand", "shouldOverrideUrlLoading - String?")
//                    return super.shouldOverrideUrlLoading(view, url)
//                }
//
//                override fun shouldOverrideUrlLoading(
//                    view: WebView?,
//                    request: WebResourceRequest?
//                ): Boolean {
//
//Log.d("JavaHand", "shouldOverrideUrlLoading - WebResourceRequest?")
//                    return super.shouldOverrideUrlLoading(view, request)
//                }
//
//                override fun onPageStarted(
//                    view: WebView?,
//                    url: String?,
//                    favicon: Bitmap?
//                ) {
//                    super.onPageStarted(view, url, favicon)
//Log.d("JavaHand", "onPageStarted")
//                }
//
//                override fun onPageFinished(view: WebView?, url: String?) {
//                    super.onPageFinished(view, url)
//Log.d("JavaHand", "onPageFinished")
//                }
//
//                override fun onLoadResource(view: WebView?, url: String?) {
//                    super.onLoadResource(view, url)
//Log.d("JavaHand", "onLoadResource")
//                }
//
//                override fun onPageCommitVisible(view: WebView?, url: String?) {
//                    super.onPageCommitVisible(view, url)
//Log.d("JavaHand", "onPageCommitVisible")
//                }
//
//                override fun shouldInterceptRequest(
//                    view: WebView?,
//                    url: String?
//                ): WebResourceResponse? {
//Log.d("JavaHand", "shouldInterceptRequest - String?")
//                    return super.shouldInterceptRequest(view, url)
//                }
//
//                override fun shouldInterceptRequest(
//                    view: WebView?,
//                    request: WebResourceRequest?
//                ): WebResourceResponse? {
//Log.d("JavaHand", "shouldInterceptRequest - WebResourceRequest?")
//                    return super.shouldInterceptRequest(view, request)
//                }
//
//                override fun onTooManyRedirects(
//                    view: WebView?,
//                    cancelMsg: Message?,
//                    continueMsg: Message?
//                ) {
//                    super.onTooManyRedirects(view, cancelMsg, continueMsg)
//Log.d("JavaHand", "onTooManyRedirects")
//                }
//
//                override fun onReceivedError(
//                    view: WebView?,
//                    errorCode: Int,
//                    description: String?,
//                    failingUrl: String?
//                ) {
//                    super.onReceivedError(
//                        view,
//                        errorCode,
//                        description,
//                        failingUrl
//                    )
//Log.d("JavaHand", "onReceivedError - Int")
//                }
//
//                override fun onReceivedError(
//                    view: WebView?,
//                    request: WebResourceRequest?,
//                    error: WebResourceError?
//                ) {
//                    super.onReceivedError(view, request, error)
//Log.d("JavaHand", "onReceivedError - WebResourceRequest?")
//                }
//
//                override fun onReceivedHttpError(
//                    view: WebView?,
//                    request: WebResourceRequest?,
//                    errorResponse: WebResourceResponse?
//                ) {
//                    super.onReceivedHttpError(view, request, errorResponse)
//Log.d("JavaHand", "onReceivedHttpError")
//                }
//
//                override fun onFormResubmission(
//                    view: WebView?,
//                    dontResend: Message?,
//                    resend: Message?
//                ) {
//                    super.onFormResubmission(view, dontResend, resend)
//Log.d("JavaHand", "onFormResubmission")
//                }
//
//                override fun doUpdateVisitedHistory(
//                    view: WebView?,
//                    url: String?,
//                    isReload: Boolean
//                ) {
//                    super.doUpdateVisitedHistory(view, url, isReload)
//Log.d("JavaHand", "doUpdateVisitedHistory")
//                }
//
//                override fun onReceivedSslError(
//                    view: WebView?,
//                    handler: SslErrorHandler?,
//                    error: SslError?
//                ) {
//                    super.onReceivedSslError(view, handler, error)
//Log.d("JavaHand", "onReceivedSslError")
//                }
//
//                override fun onReceivedClientCertRequest(
//                    view: WebView?,
//                    request: ClientCertRequest?
//                ) {
//                    super.onReceivedClientCertRequest(view, request)
//Log.d("JavaHand", "onReceivedClientCertRequest")
//                }
//
//                override fun onReceivedHttpAuthRequest(
//                    view: WebView?,
//                    handler: HttpAuthHandler?,
//                    host: String?,
//                    realm: String?
//                ) {
//                    super.onReceivedHttpAuthRequest(view, handler, host, realm)
//Log.d("JavaHand", "onReceivedHttpAuthRequest")
//                }
//
//                override fun shouldOverrideKeyEvent(
//                    view: WebView?,
//                    event: KeyEvent?
//                ): Boolean {
//Log.d("JavaHand", "shouldOverrideKeyEvent")
//                    return super.shouldOverrideKeyEvent(view, event)
//                }
//
//                override fun onUnhandledKeyEvent(
//                    view: WebView?,
//                    event: KeyEvent?
//                ) {
//                    super.onUnhandledKeyEvent(view, event)
//Log.d("JavaHand", "onUnhandledKeyEvent")
//                }
//
//                override fun onScaleChanged(
//                    view: WebView?,
//                    oldScale: Float,
//                    newScale: Float
//                ) {
//                    super.onScaleChanged(view, oldScale, newScale)
//Log.d("JavaHand", "onScaleChanged")
//                }
//
//                override fun onReceivedLoginRequest(
//                    view: WebView?,
//                    realm: String?,
//                    account: String?,
//                    args: String?
//                ) {
//                    super.onReceivedLoginRequest(view, realm, account, args)
//Log.d("JavaHand", "onReceivedLoginRequest")
//                }
//
//                override fun onRenderProcessGone(
//                    view: WebView?,
//                    detail: RenderProcessGoneDetail?
//                ): Boolean {
//Log.d("JavaHand", "onRenderProcessGone")
//                    return super.onRenderProcessGone(view, detail)
//                }
//
//                override fun onSafeBrowsingHit(
//                    view: WebView?,
//                    request: WebResourceRequest?,
//                    threatType: Int,
//                    callback: SafeBrowsingResponse?
//                ) {
//                    super.onSafeBrowsingHit(view, request, threatType, callback)
//Log.d("JavaHand", "onSafeBrowsingHit")
//                }
//            } // object WebViewClient
            binding.webView.loadUrl(it)
            binding.webView.postVisualStateCallback(
                0,
                object: WebView.VisualStateCallback() {
                    override fun onComplete(p0: Long) {
                        if (binding.webView.title != "\${meta.title}") {
                            (activity as AppCompatActivity)
                                .supportActionBar?.let {
                                it.title = binding.webView.title
                            }
                        } // if

                        binding.progressRelatedLink.visibility = View.GONE
                    } // fun onComplete( Long )
                } // lambda
            ) // invoke
        } // let
    } // fun onViewCreated(View, Bundle?)
} // class WebViewFragment
