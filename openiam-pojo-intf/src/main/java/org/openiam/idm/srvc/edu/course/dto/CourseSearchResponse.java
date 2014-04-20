/*
 * Copyright 2009, OpenIAM LLC 
 * This file is part of the OpenIAM Identity and Access Management Suite
 *
 *   OpenIAM Identity and Access Management Suite is free software: 
 *   you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License 
 *   version 3 as published by the Free Software Foundation.
 *
 *   OpenIAM is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   Lesser GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenIAM.  If not, see <http://www.gnu.org/licenses/>. *
 */

/**
 * 
 */
package org.openiam.idm.srvc.edu.course.dto;

import org.openiam.base.ws.Response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Response object from a course management request.
 * @author suneet
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CourseSearchResponse", propOrder = {
    "courseList",
    "course"
})
public class CourseSearchResponse extends Response  {

    protected List<CourseSearchResult> courseList;
    protected CourseSearchResult course;


	public CourseSearchResponse() {

	}

    public List<CourseSearchResult> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseSearchResult> courseList) {
        this.courseList = courseList;
    }

    public CourseSearchResult getCourse() {
        return course;
    }

    public void setCourse(CourseSearchResult course) {
        this.course = course;
    }
}
