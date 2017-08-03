package com.jedsada.transitionitemlist

import com.google.gson.annotations.SerializedName
import org.parceler.Parcel
import org.parceler.ParcelConstructor
import java.math.BigDecimal

sealed class MovieData {
    data class Success(var movieDao: MovieDao) : MovieData()
    data class Failure(val str: String) : MovieData()

    companion object {
        fun retrieveMovieSuccess(body: MovieDao?) = MovieData.Success(body!!)
        fun retrieveMovieFailure(msg: String = "Sorry, somethings error.") = MovieData.Failure(msg)
    }
}

data class MovieDao(@SerializedName("page") var page: Int,
                    @SerializedName("total_result") val totalResult: Long,
                    @SerializedName("total_pages") val totalPages: Long,
                    @SerializedName("results") val result: MutableList<ResultDetail>)

@Parcel(Parcel.Serialization.BEAN)
data class ResultDetail @ParcelConstructor
constructor(@SerializedName("vote_count") var voteCount: Int,
            @SerializedName("id") var id: Long,
            @SerializedName("video") var video: Boolean,
            @SerializedName("vote_average") var voteAverage: BigDecimal,
            @SerializedName("title") var title: String,
            @SerializedName("popularity") var popularity: BigDecimal,
            @SerializedName("poster_path") var posterPath: String,
            @SerializedName("original_language") var originalLanguage: String,
            @SerializedName("genre_ids") var genreIds: List<String>,
            @SerializedName("backdrop_path") var backdropPath: String,
            @SerializedName("adult") var adult: Boolean,
            @SerializedName("overview") var overview: String,
            @SerializedName("release_date") var releaseDate: String)