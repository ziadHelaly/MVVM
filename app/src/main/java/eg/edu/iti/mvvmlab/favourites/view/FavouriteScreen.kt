package eg.edu.iti.mvvmlab.favourites.view


import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import eg.edu.iti.mvvmlab.favourites.viewmodel.FavouritesViewModel
import eg.edu.iti.mvvmlab.model.models.Product

@Composable
fun FavouritesScreen(
    viewModel: FavouritesViewModel,
    onClick: (Product) -> Unit
) {
    viewModel.getFavourites()
    val products = viewModel.productList.observeAsState()
    val isEmpty = products.value?.isEmpty() ?: true
    val message = viewModel.message.observeAsState()
    val context = LocalContext.current
    if (isEmpty) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No Items add some products to display")
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(products.value!!) { prod ->
                ProductItem(prod, onClick)
            }
        }
    }
    LaunchedEffect(message.value) {
        if (message.value?.isNotEmpty() == true) {
            Toast.makeText(context, message.value, Toast.LENGTH_SHORT).show()
            viewModel.resetMessage()

        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun ProductItem(
    product: Product,
    onClick: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp)
    ) {
        GlideImage(
            model = product.thumbnail,
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(150.dp)
                .clip(CircleShape)
        )
        Column {
            Text(product.title ?: "", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier.size(16.dp))
            Text(product.category ?: "")
            Spacer(modifier.size(16.dp))
            Button(
                onClick = { onClick(product) }
            ) {
                Text("Remove")
            }
        }
    }

}

