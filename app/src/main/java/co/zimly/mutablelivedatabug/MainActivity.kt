package co.zimly.mutablelivedatabug

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.zimly.mutablelivedatabug.ui.theme.MutableLiveDataBugTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MutableLiveDataBugTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

object MyModel : ViewModel() {
    val content = MutableLiveData<@Composable () -> Unit>({ Text("Hello!") })
}

@Composable
fun Greeting(name: String) {
    val content by MyModel.content.observeAsState({})

    Text(text = "Hello $name!")
    content()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MutableLiveDataBugTheme {
        Greeting("Android")
    }
}