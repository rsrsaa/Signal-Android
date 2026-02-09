/*
 * Copyright 2026 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.thoughtcrime.securesms.help.refactor

interface HelpScreenCallbacks {
  fun onNavigationClick() = Unit

  object Empty : HelpScreenCallbacks
}
