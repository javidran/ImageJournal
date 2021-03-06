package com.javidran.imagejournal.view.dashboard

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.javidran.imagejournal.model.Album
import com.javidran.imagejournal.model.DataSource

class AlbumViewModel(val dataSource: DataSource) : ViewModel() {

    val albumsLiveData = dataSource.getAlbumList()

    /* If the name and description are present, create new Flower and add it to the datasource */
    fun insertAlbum(title: String?, emoji: String?) {
        if (title.isNullOrEmpty() || emoji.isNullOrEmpty()) {
            return
        }

        val album = Album(title, emoji)
        dataSource.addAlbum(album)
    }

    fun getNextNumber(album: Album): Int {
        return dataSource.getNextNumberForAlbum(album.title)
    }
}

class AlbumViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlbumViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AlbumViewModel(
                dataSource = DataSource.getDataSource(context)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}