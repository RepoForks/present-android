package com.larry.present.network.course;

import com.larry.present.bean.course.Course;
import com.larry.present.network.base.BaseCallModeal;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/*
*    
* 项目名称：present-android      
* 类描述： 课程的相关接口
* 创建人：Larry-sea   
* 创建时间：2017/4/25 22:29   
* 修改人：Larry-sea  
* 修改时间：2017/4/25 22:29   
* 修改备注：   
* @version    
*    
*/
public interface Icourse {


    /**
     * 老师获取所有的课程
     *
     * @param teacherId 老师id
     * @return 返回老师所教学的课程
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("teacherGetAllCourse")
    Observable<BaseCallModeal<List<Course>>> teacherGetAllCourse(@Body RequestBody teacherId);


    /**
     * 学生获取所有的班级id
     *
     * @param studentId 学生id
     * @return 返回学生所在班级的所有课程
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("studentGetAllCourse")
    Observable<BaseCallModeal<List<Course>>> studentGetAllCourse(@Body RequestBody studentId);


    /**
     * 添加课程接口
     *
     * @param courseName 课程名
     * @param teacherId  老师id
     * @return
     */
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("addCourse")
    Observable<BaseCallModeal<String>> addCourse(@Body RequestBody body);

}