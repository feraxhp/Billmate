import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.feraxhp.billmate.ui.theme.BillmateTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.feraxhp.billmate.layauts.Bars.MyNavigationBar
import com.feraxhp.billmate.layauts.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PrimaryUi() {
    val (selectedItemValue,getSelectedItem) = remember { mutableStateOf(0) }
    BillmateTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Scaffold(
                topBar = {
                    TopBar()
                },
                bottomBar = {
                    MyNavigationBar(selectedItem = selectedItemValue, onItemClick = getSelectedItem)
                }
            ) {

            }

        }
    }
}

@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun Preview() {
    PrimaryUi()
}