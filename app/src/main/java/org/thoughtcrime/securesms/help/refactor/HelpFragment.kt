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
import org.signal.core.ui.compose.ComposeFragment
import org.thoughtcrime.securesms.R

class HelpFragment : ComposeFragment() {

  private val viewModel: HelpViewModel by viewModels()

  @Composable
  override fun FragmentContent() {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val callbacks = remember { Callbacks() }

    val startCategoryIndex = arguments?.getInt(START_CATEGORY_INDEX, 0) ?: 6

    HelpScreen(
      state = state,
      callbacks = callbacks,
      startCategoryIndex = startCategoryIndex,
      viewModel = viewModel,
    )
  }

  private inner class Callbacks : HelpScreenCallbacks {
    override fun onNavigationClick() {
      requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    override fun onProblemTextChanged(text: String) {
      viewModel.onProblemChanged(text)
    }

    override fun onCategorySelected(index: Int) {
      viewModel.onCategorySelected(index)
    }

    override fun onFeelingSelected(feeling: Feeling) {
      viewModel.onFeelingSelected(feeling)
    }

    override fun onWhatIsDebugLogClick() {
      val intent = Intent(Intent.ACTION_VIEW)
      intent.setData(Uri.parse(getString(R.string.HelpFragment__link__debug_info)))
      startActivity(intent)
    }

    override fun onDebugLogsToggled(include: Boolean) {
      viewModel.onDebugLogsToggled(include)
    }

    override fun onFaqClick() {
      val intent = Intent(Intent.ACTION_VIEW)
      intent.setData(Uri.parse(getString(R.string.HelpFragment__link__faq)))
      startActivity(intent)
    }

    override fun onNextClick() {
      viewModel.onNextClick()
    }
  }

  companion object {
    const val START_CATEGORY_INDEX = "start_category_index"
    // TODO: don't forget to switch to this constants instead of old once dev done
    const val PAYMENT_INDEX        = 6
    const val DONATION_INDEX       = 7
    const val REMOTE_BACKUPS_INDEX = 8
  }
}
