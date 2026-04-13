/*
 * Copyright 2026 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.thoughtcrime.securesms.help.refactor

import android.widget.ImageView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import org.signal.core.ui.compose.Buttons
import org.signal.core.ui.compose.CircularProgressWrapper
import org.signal.core.ui.compose.DayNightPreviews
import org.signal.core.ui.compose.Previews
import org.signal.core.ui.compose.Scaffolds
import org.signal.core.ui.compose.SignalIcons
import org.thoughtcrime.securesms.R
import org.thoughtcrime.securesms.components.emoji.EmojiImageView

@Composable
fun HelpScreen(
  state: HelpScreenState,
  callbacks: HelpScreenCallbacks,
  startCategoryIndex: Int = 0,
) {
  Scaffolds.Settings(
    title = stringResource(R.string.preferences__help),
    onNavigationClick = callbacks::onNavigationClick,
    navigationIcon = SignalIcons.ArrowStart.imageVector,
  ) { paddingValues ->

    val categories = stringArrayResource(R.array.HelpFragment__categories_6).toList()

    LaunchedEffect(startCategoryIndex) {
      callbacks.onCategorySelected(startCategoryIndex)
    }

    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues),
    ) {
      Column(
        modifier = Modifier
          .weight(1f)
          .verticalScroll(rememberScrollState())
          .padding(horizontal = 16.dp),
      ) {
        Text(
          modifier = Modifier.padding(top = 8.dp),
          text = stringResource(id = R.string.HelpFragment__contact_us),
          style = MaterialTheme.typography.bodyMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
          modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 144.dp),
          value = state.problemText,
          onValueChange = { callbacks.onProblemTextChanged(it) },
          placeholder = {
            Text(text = stringResource(id = R.string.HelpFragment__tell_us_whats_going_on))
          },
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
          maxLines = Int.MAX_VALUE,
          shape = RoundedCornerShape(8.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
          text = stringResource(id = R.string.HelpFragment__tell_us_why_youre_reaching_out),
          style = MaterialTheme.typography.bodyMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(modifier = Modifier.height(8.dp))

        CategoryDropdown(
          categories = categories,
          selectedIndex = state.categoryIndex,
          onCategorySelected = callbacks::onCategorySelected,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
          text = stringResource(id = R.string.HelpFragment__how_do_you_feel),
          style = MaterialTheme.typography.bodyMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(modifier = Modifier.height(12.dp))

        EmojiRatingRow(
          selectedFeeling = state.selectedFeeling,
          onFeelingSelected = callbacks::onFeelingSelected,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
          modifier = Modifier.fillMaxWidth(),
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Checkbox(
            checked = state.includeDebugLog,
            onCheckedChange = { callbacks.onDebugLogsToggled(it) },
          )
          Text(
            text = stringResource(id = R.string.HelpFragment__include_debug_log),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
          )
          TextButton(onClick = callbacks::onWhatIsDebugLogClick) {
            Text(
              text = stringResource(id = R.string.HelpFragment__whats_this),
              color = MaterialTheme.colorScheme.primary,
            )
          }
        }

        Spacer(modifier = Modifier.height(8.dp))
      }

      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 16.dp, start = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Text(
          modifier = Modifier
            .weight(1f),
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

        CircularProgressWrapper(
          isLoading = state.isSubmitting,
        ) {
          Buttons.LargeTonal(
            modifier = Modifier.padding(end = 16.dp),
            onClick = callbacks::onNextClick,
            enabled = state.isFormValid,
          ) {
            Text(stringResource(R.string.HelpFragment__next))
          }
        }
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CategoryDropdown(
  categories: List<String>,
  selectedIndex: Int,
  onCategorySelected: (Int) -> Unit,
) {
  var expanded by remember { mutableStateOf(false) }

  ExposedDropdownMenuBox(
    expanded = expanded,
    onExpandedChange = { expanded = !expanded },
  ) {
    OutlinedTextField(
      modifier = Modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable, true),
      value = categories.getOrElse(selectedIndex) { "" },
      onValueChange = {},
      readOnly = true,
      trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
    )
    ExposedDropdownMenu(
      expanded = expanded,
      onDismissRequest = { expanded = false },
    ) {
      categories.forEachIndexed { index, category ->
        DropdownMenuItem(
          text = { Text(category) },
          onClick = {
            onCategorySelected(index)
            expanded = false
          },
        )
      }
    }
  }
}

@Composable
private fun EmojiRatingRow(
  selectedFeeling: Feeling?,
  onFeelingSelected: (Feeling) -> Unit,
) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(8.dp),
  ) {
    Feeling.entries.forEach { feeling ->
      EmojiButton(
        feeling = feeling,
        isSelected = feeling == selectedFeeling,
        onClick = { onFeelingSelected(feeling) },
      )
    }
  }
}

@Composable
private fun EmojiButton(
  feeling: Feeling,
  isSelected: Boolean,
  onClick: () -> Unit,
) {
  val backgroundColor = if (isSelected) {
    Color(0xFF2C6BED)
  } else {
    Color(0xFFE9E9E9)
  }

  Box(
    modifier = Modifier
      .size(48.dp)
      .background(backgroundColor, shape = CircleShape)
      .padding(4.dp)
      .clickable(onClick = onClick),
    contentAlignment = Alignment.Center,
  ) {
    AndroidView(
      factory = { context ->
        EmojiImageView(context).apply {
          scaleType = ImageView.ScaleType.FIT_CENTER
        }
      },
      update = { view ->
        view.setImageEmoji(feeling.emojiCode)
      },
      modifier = Modifier.fillMaxSize()
    )
  }
}

enum class Feeling(val emojiCode: String, val labelRes: Int) {
  ECSTATIC(emojiCode = "\ud83d\ude00", labelRes = R.string.HelpFragment__emoji_5),
  HAPPY(emojiCode    = "\ud83d\ude42", labelRes = R.string.HelpFragment__emoji_4),
  AMBIVALENT(emojiCode = "\ud83d\ude10", labelRes = R.string.HelpFragment__emoji_3),
  UNHAPPY(emojiCode  = "\ud83d\ude41", labelRes = R.string.HelpFragment__emoji_2),
  ANGRY(emojiCode    = "\ud83d\ude20", labelRes = R.string.HelpFragment__emoji_1),
}

@DayNightPreviews
@Composable
private fun HelpScreenPreview() {
  Previews.Preview {
    HelpScreen(
      state = HelpScreenState(),
      callbacks = HelpScreenCallbacks.Empty,
    )
  }
}
