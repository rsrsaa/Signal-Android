/*
 * Copyright 2026 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.thoughtcrime.securesms.help.refactor

interface HelpScreenCallbacks {
  fun onNavigationClick() = Unit

  fun onProblemTextChanged(text: String) = Unit

  fun onCategorySelected(index: Int) = Unit

  fun onFeelingSelected(feeling: Feeling) = Unit

  fun onWhatIsDebugLogClick() = Unit
  fun onDebugLogsToggled(include: Boolean) = Unit

  fun onFaqClick() = Unit
  fun onNextClick() = Unit

  object Empty : HelpScreenCallbacks
}
