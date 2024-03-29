import android.content.ContentValues.TAG
import android.graphics.fonts.FontStyle
import android.service.autofill.OnClickAction
import android.util.Log
import android.view.View.OnClickListener
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.recipeappusingapi.models.Recipe
import com.example.recipeappusingapi.models.SingleRecipe
import com.example.recipeappusingapi.viewModels.MainViewModel


@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    navController: NavController
) {
    val recipesState by viewModel.recipes.observeAsState()

    Box(modifier = Modifier
        .padding(15.dp)
        .fillMaxSize()) {
        LazyColumn {
            items(recipesState?.recipes.orEmpty()) { recipe ->

                Card(
                    modifier = Modifier
                        .padding(top = 18.dp)
                        .padding(horizontal = 8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFF004A)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    val imageModel = recipe.image
                    Box(modifier = Modifier.fillMaxSize()) {
                        AsyncImage(
                            model = imageModel,
                            alpha = 0.95f,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    val singleRecipe = SingleRecipe(
                                        id = recipe.id,
                                        image = recipe.image,
                                        instructions = recipe.instructions,
                                        ingredients = recipe.ingredients,
                                        name = recipe.name
                                    )
                                    navController.navigate("detailScreen/${singleRecipe.id}") {
                                        launchSingleTop = true
                                    }
                                }
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.BottomStart)
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(Color.Transparent, Color.Black),
                                        startY = 200f,
                                        endY = 1200f // Adjust as needed for the gradient length
                                    )
                                )
                        )

                        Text(
                            text = recipe.name,
                            color = Color.White,
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(start = 16.dp, bottom = 8.dp), // Adjust as needed for the text position
                            fontSize = 25.sp,
                            fontWeight = Bold
                        )
                    }
                }
            }
        }
    }
}
