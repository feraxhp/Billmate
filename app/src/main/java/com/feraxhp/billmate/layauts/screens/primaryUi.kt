import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.feraxhp.billmate.ui.theme.BillmateTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PrimaryUi() {
    BillmateTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Scaffold(
                bottomBar = {
                    bottomLayout(
                        Names = listOf("Home", "Accounts", "Debts", "Overview"),
                        Icons = listOf(Icons.Rounded.Home, Icons.Rounded.Person, Icons.Rounded.Lock, Icons.Rounded.Face),
                        modifier = Modifier.background(MaterialTheme.colorScheme.surfaceVariant)
                    )
                }
            ) {

            }

        }
    }
}

@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun preview() {
    PrimaryUi()
}