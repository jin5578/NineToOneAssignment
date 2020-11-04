package com.tistory.jeongs0222.ninetooneassignment.data.pagingsource

import androidx.paging.PagingSource
import com.tistory.jeongs0222.ninetooneassignment.model.kakao.Document
import com.tistory.jeongs0222.ninetooneassignment.service.ApiService
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException


class LocationPagingSource(
    private val apiService: ApiService,
    private val query: String,
    private val longitude: String,
    private val latitude: String
) : PagingSource<Int, Document>() {

    companion object {
        private const val PARAMS_RADIUS = 20000
        private const val PARAMS_SIZE = 10
        private const val PARAMS_SORT = "distance"
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Document> {
        return try {
            val nextPageNumber = params.key ?: 1

            val response = apiService.searchKeywordLocationAsync(
                query,
                longitude,
                latitude,
                PARAMS_RADIUS,
                nextPageNumber,
                PARAMS_SIZE,
                PARAMS_SORT
            ).await()
            val keywordLocation = response.body()!!

            Timber.e(keywordLocation.toString())

            LoadResult.Page(
                data = keywordLocation.documents,
                prevKey = null,
                nextKey = (if (keywordLocation.meta.isEnd) null else nextPageNumber + 1)
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

}