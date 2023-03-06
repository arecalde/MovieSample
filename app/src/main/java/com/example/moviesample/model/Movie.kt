package com.example.moviesample.model

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviesample.R
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso


class Movie (
  @SerializedName("adult"             ) var adult            : Boolean?       = null,
  @SerializedName("backdrop_path"     ) var backdropPath     : String?        = null,
  @SerializedName("genre_ids"         ) var genreIds         : ArrayList<Int> = arrayListOf(),
  @SerializedName("id"                ) var id               : Int?           = null,
  @SerializedName("original_language" ) var originalLanguage : String?        = null,
  @SerializedName("original_title"    ) var originalTitle    : String?        = null,
  @SerializedName("overview"          ) var overview         : String?        = null,
  @SerializedName("popularity"        ) var popularity       : Double?        = null,
  @SerializedName("poster_path"       ) var posterPath       : String?        = null,
  @SerializedName("release_date"      ) var releaseDate      : String?        = null,
  @SerializedName("title"             ) var title            : String?        = null,
  @SerializedName("video"             ) var video            : Boolean?       = null,
  @SerializedName("vote_average"      ) var voteAverage      : Double?        = null,
  @SerializedName("vote_count"        ) var voteCount        : Int?           = null
) {

    private val gson = Gson()

    private val _drawable = MutableLiveData<Drawable?>()
    val drawable: LiveData<Drawable?> = _drawable
    private fun getSharedPreferences(context: Context) =
      context.getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)

    private fun getMovieIds(context: Context) : List<Int> {
        val favoritesJson = getSharedPreferences(context).getString("favorites","")
        val typeToken = object: TypeToken<MutableList<Int>>() {}.type
        return if (favoritesJson.isNullOrEmpty()) {
            listOf()
        } else {
            gson.fromJson<MutableList<Int>>(favoritesJson, typeToken)
        }
    }

    private fun setMovieIds(context: Context, movieIds: List<Int>) {
      val editor = getSharedPreferences(context).edit()
      editor.putString("favorites", gson.toJson(movieIds))
      editor.apply()
    }
    fun favoriteMovie(context: Context) {
      val movieIdList = getMovieIds(context).toMutableList()
      if (!isFavorite(context)) {
        movieIdList.add(id ?: 0)
      } else {
        movieIdList.remove(id ?: 0)
      }
      setMovieIds(context, movieIdList)
        _drawable.value = getDrawable(context)


    }
    fun getDrawable(context: Context) = if (isFavorite(context)) {
        AppCompatResources.getDrawable(context, R.drawable.baseline_star_24)
    } else {
        AppCompatResources.getDrawable(context, R.drawable.baseline_star_border_24)
    }
    fun isFavorite(context: Context) = getMovieIds(context).contains(id)
    fun getUrl(): String = "https://www.themoviedb.org/t/p/w1280/"+posterPath
}

@BindingAdapter("imageUrl")
fun loadImage(view : View, url : String?){
  if (url.isNullOrEmpty()) return
  Picasso.get().load(url).into((view as ImageView))
}
