package com.example.eduempoweryd.settings.student;

public class StudentInfo {
    String DateOfBirth;
    String Education;
    String Email;
    String Gender;
    String Password;
    String Phone;
    String Username;

    public StudentInfo() {
    }

    public StudentInfo(String dateOfBirth, String education, String email, String gender, String password, String phone, String username) {
        DateOfBirth = dateOfBirth;
        Education = education;
        Email = email;
        Gender = gender;
        Password = password;
        Phone = phone;
        Username = username;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getEducation() {
        return Education;
    }

    public void setEducation(String education) {
        Education = education;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
