import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomLayout(Names: List<String>,
                 Icons: List<ImageVector>,
                 modifier: Modifier = Modifier,
                 ButtonFocus: Int = 0
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier.fillMaxWidth()
    ) {
        Names.forEach {
            var Visible_ = 0f
            if (Names.indexOf(it) == ButtonFocus) Visible_ = 1f
            IconButton(
                onClick = { /*TODO*/ },
                text = it,
                backgroundVisible = Visible_,
                modifier = Modifier.weight(1F),
                buttonIcon = Icons[Names.indexOf(it)]
            )
        }
    }
}