package eg.edu.iti.mvvmlab.search.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import eg.edu.iti.mvvmlab.search.view.ui.theme.MVVMLabTheme
import eg.edu.iti.mvvmlab.search.viewmodel.SearchViewMode

class SearchActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MVVMLabTheme {
                SearchScreen()
            }
        }
    }
}

@Composable
fun SearchScreen(viewModel: SearchViewMode = viewModel()) {
    val searchText = remember { mutableStateOf("") }
    val namesList by viewModel.filteredNames.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextField(
            value = searchText.value,
            onValueChange = {
                searchText.value = it
                viewModel.setSearchQuery(it)
            },
            label = { Text("Search by first letter") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn  {
            items(namesList) { name ->
                Text(
                    text = name,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}



