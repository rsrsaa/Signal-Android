/*
 * Copyright 2026 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.thoughtcrime.securesms.help.refactor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
    Column (
      modifier = Modifier
        .padding(paddingValues)
        .fillMaxSize()
        .horizontalGutters(),
    ) {
      Text(
        text = stringResource(id = R.string.HelpFragment__contact_us),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface,
      )

      TextField(
        modifier = Modifier
          .fillMaxWidth()
          .defaultMinSize(minHeight = 144.dp)
          .padding(top = 12.dp),
        value = "",
        label = { Text(text = stringResource(id = R.string.HelpFragment__tell_us_whats_going_on)) },
        onValueChange = {
          // TODO: impl
        },
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
        singleLine = false,
      )

      Text(
        modifier = Modifier
          .padding(top = 16.dp),
        text = stringResource(id = R.string.HelpFragment__tell_us_why_youre_reaching_out),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface,
      )

      Text(
        modifier = Modifier
          .padding(top = 16.dp),
        text = stringResource(id = R.string.HelpFragment__how_do_you_feel),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface,
      )

      Column {
        Rows.ToggleRow(
          modifier = Modifier
            .padding(top = 16.dp),
          checked = state.includeDebugLog,
          text = stringResource(R.string.HelpFragment__include_debug_log),
          onCheckChanged = {
            // TODO: impl
          }
        )

        Text(
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

      Spacer(modifier = Modifier.weight(1f))

      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
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
