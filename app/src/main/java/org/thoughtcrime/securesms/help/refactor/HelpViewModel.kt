/*
 * Copyright 2026 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.thoughtcrime.securesms.help.refactor

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HelpViewModel : ViewModel() {

  private val _state = MutableStateFlow(HelpScreenState())
  val state = _state.asStateFlow()

  fun onProblemChanged(text: String) {
    _state.update { it.copy(problemText = text) }
  }

  fun onCategorySelected(index: Int) {
    _state.update { it.copy(categoryIndex = index) }
  }

  fun onFeelingSelected(feeling: Feeling) {
    _state.update { current ->
      current.copy(selectedFeeling = if (current.selectedFeeling == feeling) null else feeling)
    }
  }

  fun onDebugLogsToggled(include: Boolean) {
    _state.update { it.copy(includeDebugLog = include) }
  }

  fun onNextClick() {
    // TODO: implement
  }
}
