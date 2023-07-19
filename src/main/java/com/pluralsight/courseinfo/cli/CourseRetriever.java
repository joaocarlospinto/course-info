package com.pluralsight.courseinfo.cli;

import com.pluralsight.courseinfo.cli.service.CourseRetrievalService;
import com.pluralsight.courseinfo.cli.service.PluralsightCourse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Predicate;

public class CourseRetriever {
    // Declare de LOG to write de messages - dependency slf4j
    private static final Logger LOG = LoggerFactory.getLogger(CourseRetriever.class);

    public static void main(String... args) {
        LOG.info("CourseRetriever Starting!");
        if (args.length == 0) {
            LOG.warn("Please provide an author name as first argument");
            return;
        }
        try {
            // call the method to retrieve the author name passed in args/prompt
            retrieveCourses(args[0]);

        } catch (Exception e) {
            LOG.error("Unexpected Error", e);
        }
    }

    private static void retrieveCourses(String authorId) {
        LOG.info("Retrieving courses for author: '{}'", authorId);
        // create the object from class CourseRetrievalService
        CourseRetrievalService courseRetrievalService = new CourseRetrievalService();
        // Call the method getCourseFor passing the author name - it returns a List of PluralSightCourse Objects
        List<PluralsightCourse> coursesToStore = courseRetrievalService.getCoursesFor(authorId)
                .stream()
             // .filter(course -> !course.isRetired())
                .filter(Predicate.not(PluralsightCourse::isRetired))
                .toList();
        LOG.info("Retrieved the following {} courses: {}", coursesToStore.size(), coursesToStore);

    }

}
