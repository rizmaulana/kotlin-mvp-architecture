package id.rizmaulana.movie.data.model.api

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Rizki Maulana on 19/03/19.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */
data class MovieRequest(
    val api_key: String = "873ada4562d98a6b94f21a5041e3f2cb",
    val sort_by: String = "popularity.desc",
    var page: Int = 0,
    var query: String = ""
) {
    fun toMap() = Gson().fromJson<HashMap<String, String>>(
        Gson().toJson(this),
        object : TypeToken<java.util.HashMap<String, String>>() {}.type
    )

}