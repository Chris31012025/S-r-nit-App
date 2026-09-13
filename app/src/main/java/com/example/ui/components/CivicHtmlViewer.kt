package com.example.ui.components

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun CivicHtmlViewer(
    htmlContent: String,
    modifier: Modifier = Modifier
) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = false
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                setBackgroundColor(0x00000000)
            }
        },
        update = { webView ->
            val styledHtml = """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <style>
                        body {
                            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, sans-serif;
                            color: #2D312E;
                            background: transparent;
                            margin: 0;
                            padding: 16px;
                            line-height: 1.6;
                        }
                        h1, h2, h3 {
                            color: #1F3324;
                            margin-top: 0.8em;
                            margin-bottom: 0.4em;
                        }
                        p {
                            margin: 0.6em 0;
                            font-size: 15px;
                        }
                        .hero-img {
                            width: 100%;
                            max-height: 220px;
                            object-fit: cover;
                            border-radius: 16px;
                            box-shadow: 0 4px 16px rgba(0,0,0,0.08);
                            margin: 12px 0;
                        }
                        .badge {
                            display: inline-block;
                            padding: 4px 10px;
                            background-color: #E2ECE5;
                            color: #1F3324;
                            border-radius: 9999px;
                            font-weight: 600;
                            font-size: 12px;
                            margin-bottom: 8px;
                        }
                        .quote-box {
                            background: #F3F4F1;
                            border-left: 4px solid #1F3324;
                            padding: 12px 16px;
                            border-radius: 0 12px 12px 0;
                            font-style: italic;
                            margin: 12px 0;
                        }
                    </style>
                </head>
                <body>
                    $htmlContent
                </body>
                </html>
            """.trimIndent()
            webView.loadDataWithBaseURL(null, styledHtml, "text/html", "UTF-8", null)
        },
        modifier = modifier
    )
}
