import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun topLayout() {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun bottomLayout(Names: List<String>, Icons: List<ImageVector>, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier.fillMaxWidth()
    ) {
        Names.forEach {
            IconButton(
                onClick = { /*TODO*/ },
                text = it,
                modifier = Modifier.weight(1F),
                buttonIcon = Icons[Names.indexOf(it)]
            )
        }
    }
}