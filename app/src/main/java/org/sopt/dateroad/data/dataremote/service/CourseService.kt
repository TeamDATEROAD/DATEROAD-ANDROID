package org.sopt.dateroad.data.dataremote.service

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.sopt.dateroad.data.dataremote.model.response.ResponseCourseDetailDto
import org.sopt.dateroad.data.dataremote.model.response.ResponseCoursesDto
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.API
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.CITY
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.COST
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.COUNTRY
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.COURSE
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.COURSES
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.COURSE_ID
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.LIKES
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.PLACES
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.SORT
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.SORT_BY
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.TAGS
import org.sopt.dateroad.data.dataremote.util.ApiConstraints.VERSION
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface CourseService {
    @DELETE("$API/$VERSION/$COURSES/{$COURSE_ID}")
    suspend fun deleteCourse(
        @Path(COURSE_ID) courseId: Int
    )

    @DELETE("$API/$VERSION/$COURSES/{$COURSE_ID}/$LIKES")
    suspend fun deleteCourseLike(
        @Path(COURSE_ID) courseId: Int
    )

    @GET("$API/$VERSION/$COURSES/{$COURSE_ID}")
    suspend fun getCourseDetail(
        @Path(COURSE_ID) courseId: Int
    ): ResponseCourseDetailDto

    @GET("$API/$VERSION/$COURSES")
    suspend fun getFilteredCourses(
        @Query(COUNTRY) country: String?,
        @Query(CITY) city: String?,
        @Query(COST) cost: Int?
    ): ResponseCoursesDto

    @GET("$API/$VERSION/$COURSES/$SORT")
    suspend fun getSortedCourses(
        @Query(SORT_BY) sortBy: String
    ): ResponseCoursesDto

    @Multipart
    @POST("$API/$VERSION/$COURSES")
    suspend fun postCourse(
        @Part images: List<MultipartBody.Part>,
        @Part(COURSE) course: RequestBody,
        @Part(TAGS) tags: RequestBody,
        @Part(PLACES) places: RequestBody
    )

    @POST("$API/$VERSION/$COURSES/{$COURSE_ID}/$LIKES")
    suspend fun postCourseLike(
        @Path(COURSE_ID) courseId: Int
    )
}
