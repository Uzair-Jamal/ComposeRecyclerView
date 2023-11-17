 package com.example.composerecyclerview

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composerecyclerview.data.TvShowList
import com.example.composerecyclerview.model.TvShow
import com.example.composerecyclerview.ui.theme.ComposeRecyclerViewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeRecyclerViewTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DisplayTvShows{
                        Toast.makeText(this, it.name,Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}

 @Composable
 fun DisplayTvShows(selectedItem: (TvShow) -> Unit){
     val tvShows = remember { TvShowList.tvShows }
     
     LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)){
         items(
             items = tvShows,
             itemContent = {
                 TvShowListItem(tvShow = it, selectedItem = selectedItem)
             }
         )
     }
 
 }

 @Composable
 fun TvShowListItem(tvShow: TvShow, selectedItem: (TvShow) -> Unit){
     Card(
         modifier = Modifier
             .padding(10.dp)
             .fillMaxSize(),
         elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
         shape = RoundedCornerShape(corner = CornerSize(10.dp))
     )
     {
        Row (
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .clickable { selectedItem(tvShow) },
            verticalAlignment = Alignment.CenterVertically
        ){
            TvShowImage(tvShow = tvShow)

            Column{
                Text(text = tvShow.name, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text =tvShow.overview, style = MaterialTheme.typography.bodySmall, maxLines = 3, overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(8.dp))
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(text = tvShow.year.toString(), style = MaterialTheme.typography.bodyMedium)
                    Text(text = tvShow.rating.toString(), style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
     }
 }


 @Composable
 private fun TvShowImage(tvShow: TvShow){
     Image(
         painter = painterResource(tvShow.imageId),
         contentDescription = null,
         contentScale = ContentScale.Crop,
         modifier = Modifier
             .padding(4.dp)
             .height(140.dp)
             .width(100.dp)
             .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
     )
 }
