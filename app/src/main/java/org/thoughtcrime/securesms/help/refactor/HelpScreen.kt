/*
 * Copyright 2026 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.thoughtcrime.securesms.help.refactor

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import org.signal.core.ui.compose.DayNightPreviews
import org.signal.core.ui.compose.Previews
import org.signal.core.ui.compose.Scaffolds
import org.signal.core.ui.compose.SignalIcons
import org.thoughtcrime.securesms.R

@Composable
fun HelpScreen(
  callbacks: HelpScreenCallbacks,
  state: HelpScreenState,
) {
  Scaffolds.Settings(
    title = stringResource(R.string.preferences__help),
    onNavigationClick = callbacks::onNavigationClick,
    navigationIcon = SignalIcons.ArrowStart.imageVector,
  ) { paddingValues ->

  }
}

@DayNightPreviews
@Composable
private fun HelpScreenPreview() {
  Previews.Preview {
    HelpScreen(
      callbacks = HelpScreenCallbacks.Empty,
      state = HelpScreenState(),
    )
  }
}
