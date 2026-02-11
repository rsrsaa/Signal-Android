/*
 * Copyright 2026 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.thoughtcrime.securesms.help.refactor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.signal.core.ui.compose.Buttons
import org.signal.core.ui.compose.Previews
import org.signal.core.ui.compose.Rows
import org.signal.core.ui.compose.Scaffolds
import org.signal.core.ui.compose.SignalIcons
import org.signal.core.ui.compose.horizontalGutters
import org.thoughtcrime.securesms.R
import org.thoughtcrime.securesms.compose.rememberStatusBarColorNestedScrollModifier

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
    LazyColumn(
      modifier = Modifier
        .padding(paddingValues)
        .then(rememberStatusBarColorNestedScrollModifier())
    ) {
      item {
        TextField(
          modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 144.dp)
            .padding(top = 16.dp, bottom = 12.dp, start = 20.dp, end = 28.dp),
          value = "",
          label = { Text(text = stringResource(id = R.string.HelpFragment__tell_us_whats_going_on)) },
          onValueChange = {
            // TODO: impl
          },
          keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
          singleLine = false,
        )
      }

      item {
        Column {
          Rows.ToggleRow(
            checked = state.includeDebugLog,
            text = stringResource(R.string.HelpFragment__include_debug_log),
            onCheckChanged = {
              // TODO: impl
            }
          )

          Text(
            modifier = Modifier
              .horizontalGutters(),
            text = buildAnnotatedString {
              withLink(
                link = LinkAnnotation.Clickable(
                  "view-debug-log",
                  linkInteractionListener = {
                    callbacks.onWhatIsDebugLogClick()
                  },
                  styles = TextLinkStyles(style = SpanStyle(color = MaterialTheme.colorScheme.primary))
                )
              ) {
                append(stringResource(R.string.HelpFragment__whats_this))
              }
            },
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
          )
        }
      }

      item {
        Row(
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier
            .fillMaxWidth()
            .horizontalGutters()
            .padding(top = 32.dp, bottom = 24.dp)
        ) {
          Text(
            text = buildAnnotatedString {
              withLink(
                link = LinkAnnotation.Clickable(
                  "view-faq",
                  linkInteractionListener = {
                    callbacks.onFaqClick()
                  },
                  styles = TextLinkStyles(style = SpanStyle(color = MaterialTheme.colorScheme.primary))
                )
              ) {
                append(stringResource(R.string.HelpFragment__have_you_read_our_faq_yet))
              }
            },
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
          )

          Buttons.LargeTonal(
            enabled = false,
            onClick = {
              // TODO: impl
            }
          ) {
            Text(text = stringResource(R.string.HelpFragment__next))
          }
        }
      }
    }
  }
}

@Preview
@Composable
private fun HelpScreenPreview() {
  Previews.Preview {
    HelpScreen(
      callbacks = HelpScreenCallbacks.Empty,
      state = HelpScreenState(),
    )
  }
}
