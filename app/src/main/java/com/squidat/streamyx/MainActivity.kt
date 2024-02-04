package com.squidat.streamyx

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.bumble.appyx.navigation.integration.NodeActivity
import com.bumble.appyx.navigation.platform.AndroidLifecycle
import com.bumble.appyx.navigation.integration.NodeHost
import com.squidat.streamyx.nodes.RootNode
import com.squidat.streamyx.ui.theme.StreamyxTheme

class MainActivity : NodeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StreamyxTheme {
                NodeHost(
                    lifecycle = AndroidLifecycle(LocalLifecycleOwner.current.lifecycle),
                    integrationPoint = appyxV2IntegrationPoint,
                    factory = { RootNode(it) }
                )
            }
        }
    }
}