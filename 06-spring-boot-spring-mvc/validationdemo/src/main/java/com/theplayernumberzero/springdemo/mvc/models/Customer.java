package com.theplayernumberzero.springdemo.mvc.models;

import com.theplayernumberzero.springdemo.mvc.validation.CourseCode;
import jakarta.validation.constraints.*;

public class Customer {

    private String firstName;

    @NotNull(message = "this field can't be null")
    @Size(min = 1, message = "min 1 character required")
    private String lastName;

    @NotNull(message = "this field can't be null")
    @Min(value = 0, message = "must be equal to zero or greater")
    @Max(value = 10, message = "must be equal to ten or less")
    private Integer freePass;

    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "only 5 char/digits")
    private String postalCode;

    @CourseCode(value = "LUV", message = "code must start with 'LUV'")
    private String courseCode;

    public Customer(){}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getFreePass() {
        return freePass;
    }

    public void setFreePass(Integer freePass) {
        this.freePass = freePass;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
