/*
 * Copyright 2026 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.thoughtcrime.securesms.help.refactor

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.thoughtcrime.securesms.R
import org.thoughtcrime.securesms.compose.ComposeFragment

class HelpFragment : ComposeFragment() {

  private val viewModel: HelpViewModel by viewModels()

  @Composable
  override fun FragmentContent() {

    val callbacks = remember { Callbacks() }
    val state by viewModel.state.collectAsStateWithLifecycle()

    HelpScreen(
      callbacks = callbacks,
      state = state,
    )
  }

  private inner class Callbacks : HelpScreenCallbacks {
    override fun onNavigationClick() {
      requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    override fun onWhatIsDebugLogClick() {
      val intent = Intent(Intent.ACTION_VIEW)
      intent.setData(Uri.parse(getString(R.string.HelpFragment__link__debug_info)))
      startActivity(intent)
    }

    override fun onFaqClick() {
      val intent = Intent(Intent.ACTION_VIEW)
      intent.setData(Uri.parse(getString(R.string.HelpFragment__link__faq)))
      startActivity(intent)
    }
  }
}
