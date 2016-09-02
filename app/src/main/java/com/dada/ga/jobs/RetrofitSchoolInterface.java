package com.dada.ga.jobs;

import com.dada.ga.models.SchoolItem;
import java.util.List;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Ben on 4/9/16.
 */
public interface RetrofitSchoolInterface {
    // synchronously
    @POST("/api/school/create")
    SchoolItem create(@Body SchoolItem schoolItem);

    @GET("/api/school/getByCategory/{categoryid}")
    List<SchoolItem> getAll(@Path("categoryid") int category);
}
