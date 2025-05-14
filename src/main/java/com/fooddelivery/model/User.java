// src/main/java/com/fooddelivery/model/User.java
package com.fooddelivery.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//public class User  {
//    private String username;
//    private String password;
//    private String email;
//
//    public User() {}
//
//    public User(String username, String password, String email) {
//        this.username = username;
//        this.password = password;
//        this.email = email;
//    }
//
//    public String getUsername() { return username; }
//    public void setUsername(String username) { this.username = username; }
//    public String getPassword() { return password; }
//    public void setPassword(String password) { this.password = password; }
//    public String getEmail() { return email; }
//    public void setEmail(String email) { this.email = email; }






    public class User implements Serializable {
        private String userId;
        private String username;
        private String password;
        private String email;
        private String fullName;
        private String phoneNumber;
        private String address;
        private String avatarUrl;
        private String joinDate;
        private boolean isAdmin;

        public User() {
            this.joinDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            this.avatarUrl = "/images/logo.jpg";
            this.isAdmin = false;
        }

        public User(String userId, String username, String password, String email,
                    String fullName, String phoneNumber, String address) {
            this();
            this.userId = userId;
            this.username = username;
            this.password = password;
            this.email = email;
            this.fullName = fullName;
            this.phoneNumber = phoneNumber;
            this.address = address;
        }

        public User(String id, String username, String password, String email, String admin) {
        }

        // Getters and Setters (add these for new fields)
        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public String getJoinDate() {
            return joinDate;
        }

        public void setJoinDate(String joinDate) {
            this.joinDate = joinDate;
        }

        public boolean isAdmin() {
            return isAdmin;
        }

        public void setAdmin(boolean admin) {
            isAdmin = admin;
        }

        // Update toFileString and fromFileString methods
        public String toFileString() {
            return String.join(",",
                    userId, username, password, email, fullName,
                    phoneNumber, address, avatarUrl, joinDate, String.valueOf(isAdmin)
            );
        }

        public static User fromFileString(String line) {
            String[] parts = line.split(",");
            if (parts.length >= 10) {
                User user = new User(
                        parts[0], parts[1], parts[2], parts[3],
                        parts[4], parts[5], parts[6]
                );
                user.setAvatarUrl(parts[7]);
                user.setJoinDate(parts[8]);
                user.setAdmin(Boolean.parseBoolean(parts[9]));
                return user;
            }
            return null;
        }

        @Override
        public String toString() {
            return "User{" +
                    "userId='" + userId + '\'' +
                    ", username='" + username + '\'' +
                    ", email='" + email + '\'' +
                    ", fullName='" + fullName + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", address='" + address + '\'' +
                    ", avatarUrl='" + avatarUrl + '\'' +
                    ", joinDate='" + joinDate + '\'' +
                    ", isAdmin=" + isAdmin +
                    '}';
        }

        public Object getUserId() {
            return null;
        }
    }

//package com.fooddelivery.model;
//
//import java.io.Serializable;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//public class User implements Serializable {
//    private String userId;
//    private String username;
//    private String password;
//    private String email;
//    private String fullName;
//    private String phoneNumber;
//    private String address;
//    private String avatarUrl;
//    private String joinDate;
//    private boolean isAdmin;
//
//    public User() {
//        this.joinDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        this.avatarUrl = "/images/avatars/default.png";
//        this.isAdmin = false;
//    }
//
//    public User(String userId, String username, String password, String email,
//                String fullName, String phoneNumber, String address) {
//        this();
//        this.userId = userId;
//        this.username = username;
//        this.password = password;
//        this.email = email;
//        this.fullName = fullName;
//        this.phoneNumber = phoneNumber;
//        this.address = address;
//    }
//
//    // Getters and Setters (add these for new fields)
//    public String getAvatarUrl() {
//        return avatarUrl;
//    }
//
//    public void setAvatarUrl(String avatarUrl) {
//        this.avatarUrl = avatarUrl;
//    }
//
//    public String getJoinDate() {
//        return joinDate;
//    }
//
//    public void setJoinDate(String joinDate) {
//        this.joinDate = joinDate;
//    }
//
//    public boolean isAdmin() {
//        return isAdmin;
//    }
//
//    public void setAdmin(boolean admin) {
//        isAdmin = admin;
//    }
//
//    // Update toFileString and fromFileString methods
//    public String toFileString() {
//        return String.join(",",
//                userId, username, password, email, fullName,
//                phoneNumber, address, avatarUrl, joinDate, String.valueOf(isAdmin)
//        );
//    }
//
//    public static User fromFileString(String line) {
//        String[] parts = line.split(",");
//        if (parts.length >= 10) {
//            User user = new User(
//                    parts[0], parts[1], parts[2], parts[3],
//                    parts[4], parts[5], parts[6]
//            );
//            user.setAvatarUrl(parts[7]);
//            user.setJoinDate(parts[8]);
//            user.setAdmin(Boolean.parseBoolean(parts[9]));
//            return user;
//        }
//        return null;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "userId='" + userId + '\'' +
//                ", username='" + username + '\'' +
//                ", email='" + email + '\'' +
//                ", fullName='" + fullName + '\'' +
//                ", phoneNumber='" + phoneNumber + '\'' +
//                ", address='" + address + '\'' +
//                ", avatarUrl='" + avatarUrl + '\'' +
//                ", joinDate='" + joinDate + '\'' +
//                ", isAdmin=" + isAdmin +
//                '}';
//    }
//
//    public Object getUserId() {
//        return null;
//    }
//}