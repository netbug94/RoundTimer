package com.example.roundtimer.presentation.setting_screens.about_screen

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.roundtimer.ui.theme.customColorScheme

@Composable
fun SendFeedbackForm(
    onSwipeBack: () -> Unit
) {
    var subject by remember { mutableStateOf("") }
    var messageBody by remember { mutableStateOf("") }
    var attachmentUris by remember { mutableStateOf<List<Uri>>(emptyList()) }  // To hold multiple attachments
    val context = LocalContext.current

    // File picker launcher for multiple attachments (up to 2 files, both images and videos)
    val pickFilesLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = { uris: List<Uri> ->
            if (uris.size > 2) {
                Toast.makeText(context, "You can only attach up to 2 files.", Toast.LENGTH_LONG).show()
            } else {
                attachmentUris = uris
            }
        }
    )

    BackHandler {
        onSwipeBack()
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .systemBarsPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Card containing the form fields
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TextField(
                    value = subject,
                    onValueChange = { subject = it },
                    label = { Text("Subject") },
                    modifier = Modifier.fillMaxWidth(),

                )

                TextField(
                    value = messageBody,
                    onValueChange = { messageBody = it },
                    label = { Text("Message") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )

                // Button to pick multiple attachments (images or videos)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = { pickFilesLauncher.launch("image/*|video/*") },  // Allow both images and videos
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Attach Files (up to 2)")
                    }

                    // Display text if attachments are selected
                    if (attachmentUris.isNotEmpty()) {
                        Text(
                            text = "${attachmentUris.size} files attached",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Send button at the bottom, styled as a primary action button
        ElevatedButton(
            onClick = {
                // Create the email intent
                val intent = Intent(Intent.ACTION_SEND_MULTIPLE).apply {
                    type = "*/*"  // Allow any file type for attachment
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("netbug94@gmail.com")) // Recipient email
                    putExtra(Intent.EXTRA_SUBJECT, subject)
                    putExtra(Intent.EXTRA_TEXT, messageBody)
                    if (attachmentUris.isNotEmpty()) {
                        putParcelableArrayListExtra(Intent.EXTRA_STREAM, ArrayList(attachmentUris))  // Attach multiple files
                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    }
                }
                try {
                    context.startActivity(Intent.createChooser(intent, "Send email..."))
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(context, "No email client installed.", Toast.LENGTH_LONG).show()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
        ) {
            Text("Send", color = customColorScheme().customTextColor)
        }
    }
}
