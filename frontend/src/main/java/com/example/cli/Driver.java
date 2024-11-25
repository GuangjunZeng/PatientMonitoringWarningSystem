package com.example.cli;



import com.example.cli.entity.Hospital;
import com.example.cli.entity.Monitor;
import com.example.cli.entity.User;
import com.example.cli.entity.UserHospital;

import com.example.cli.HttpClientUtil;
import com.example.cli.InputValidator;







import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Driver {

    private static Scanner in = new Scanner(System.in);
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static User currentLoginUser = null;


    /**
     * Login user (Admin/Patient) + http
     */
    public static void login() {
        System.out.print("User Role (Admin/Patient): ");
        String userRole = in.nextLine();
        System.out.print("User Name: ");
        String userName = in.nextLine();
        System.out.print("User Password: ");
        String userPassword = in.nextLine();

        // 构造 JSON 请求体
        String jsonInputString = String.format(
                "{\"userRole\": \"%s\", \"userName\": \"%s\", \"userPassword\": \"%s\"}",
                userRole, userName, userPassword
        );

        try {
            URL url = new URL("http://localhost:8080/api/users/login");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // 发送请求数据
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // 处理响应
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Login successfully!\n");
                // 登录成功后，接收当前用户信息
                try (Scanner responseScanner = new Scanner(conn.getInputStream())) {
                    String response = responseScanner.useDelimiter("\\A").next();
                    // 解析 JSON，设置当前用户
                    currentLoginUser = new ObjectMapper().readValue(response, User.class);
                    String currentLoginUserRole = currentLoginUser.getUserRole();
                    if (currentLoginUserRole.equals("Admin")) {
                        adminMenu();
                    } else if (currentLoginUserRole.equals("Patient")) {
                        patientMenu();
                    }
                }
            } else {
                System.out.println("User Role/name/password is incorrect, login failed!");
            }
        } catch (Exception e) {
            System.out.println("Error occurred during login: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * Register new user (Admin/Patient) + http
     */
    public static void register() throws ParseException {
        System.out.print("User Role (Admin/Patient): ");
        String userRole = in.nextLine();
        System.out.print("User Name: ");
        String userName = in.nextLine();
        System.out.print("User Password: ");
        String userPassword = in.nextLine();
        System.out.print("User Gender (Male/Female/Unknown): ");
        String userGender = in.nextLine();
        System.out.print("User DOB (yyyy-MM-dd): ");
        Date userDob = sdf.parse(in.nextLine());
        System.out.print("User Address: ");
        String userAddress = in.nextLine();
        System.out.print("User Contact: ");
        String userContact = in.nextLine();

        // 构造 JSON 请求体
        String jsonInputString = String.format(
                "{\"userRole\": \"%s\", \"userName\": \"%s\", \"userPassword\": \"%s\", \"userGender\": \"%s\", \"userDob\": \"%s\", \"userAddress\": \"%s\", \"userContact\": \"%s\"}",
                userRole, userName, userPassword, userGender, sdf.format(userDob), userAddress, userContact
        );

        try {
            URL url = new URL("http://localhost:8080/api/users");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // 发送请求数据
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // 处理响应
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Register successfully!");
            } else {
                System.out.println("Register failed! Server responded with status code: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("Error occurred during registration: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * Delete user (Admin) +http
     */
    public static void deleteUser() {
        if (currentLoginUser == null) {
            System.out.println("You should login first!");
            return;
        }

        searchUser();

        System.out.print("User Id to be deleted: ");
        Long deleteUserId = Long.parseLong(in.nextLine());

        try {
            URL url = new URL("http://localhost:8080/api/users/" + deleteUserId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Delete user with user Id = " + deleteUserId + " successfully!");
            } else {
                System.out.println("User with user Id = " + deleteUserId + " does not exist, delete failed!");
            }
        } catch (Exception e) {
            System.out.println("Error occurred while deleting user: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * Update user profile (Admin for all users) + http
     */
    public static void updateUserProfile() throws ParseException {
        // 检查用户是否登录
        if (currentLoginUser == null) {
            System.out.println("You should login first!");
            return;
        }

        // Check if the current user is an Admin
        if (currentLoginUser == null || !currentLoginUser.getUserRole().equals("Admin")) {
            System.out.println("You need to be an Admin to update any user profile!");
            return;
        }

        // Admin selects and inputs the user ID to update
        System.out.print("User Id to be updated: ");
        Long updateUserId = Long.parseLong(in.nextLine());

        // Enter updated user information
        System.out.print("Updated User Name: ");
        String userName = in.nextLine();
        System.out.print("Updated User Password: ");
        String userPassword = in.nextLine();
        System.out.print("Updated User Gender (Male/Female/Unknown): ");
        String userGender = in.nextLine();
        System.out.print("Updated User DOB (yyyy-MM-dd): ");
        Date userDob = sdf.parse(in.nextLine());
        System.out.print("Updated User Address: ");
        String userAddress = in.nextLine();
        System.out.print("Updated User Contact: ");
        String userContact = in.nextLine();

        // Create JSON request body
        String jsonInputString = String.format(
                "{\"userId\": %d, \"userName\": \"%s\", \"userPassword\": \"%s\", \"userGender\": \"%s\", \"userDob\": \"%s\", \"userAddress\": \"%s\", \"userContact\": \"%s\"}",
                updateUserId, userName, userPassword, userGender, sdf.format(userDob), userAddress, userContact
        );

        // Send HTTP request to update user profile
        try {
            URL url = new URL("http://localhost:8080/api/users/" + updateUserId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Send JSON data
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Handle response
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Updated user with user Id = " + updateUserId + " successfully!");
            } else {
                System.out.println("Failed to update user profile. Server responded with status code: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("Error occurred while updating user profile: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * Update user profile (Patient for himself) + http
     */
    public static void updateUserProfilePatient() throws ParseException {
        // Check if the current user is a Patient
        if (currentLoginUser == null || !currentLoginUser.getUserRole().equals("Patient")) {
            System.out.println("You need to be logged in as a Patient to update your profile!");
            return;
        }

        // Patients can only update their own profile
        Long updateUserId = currentLoginUser.getUserId();

        // Enter updated information
        System.out.print("Updated User Name: ");
        String userName = in.nextLine();
        System.out.print("Updated User Password: ");
        String userPassword = in.nextLine();
        System.out.print("Updated User Gender (Male/Female/Unknown): ");
        String userGender = in.nextLine();
        System.out.print("Updated User DOB (yyyy-MM-dd): ");
        Date userDob = sdf.parse(in.nextLine());
        System.out.print("Updated User Address: ");
        String userAddress = in.nextLine();
        System.out.print("Updated User Contact: ");
        String userContact = in.nextLine();

        // Create JSON request body
        String jsonInputString = String.format(
                "{\"userId\": %d, \"userName\": \"%s\", \"userPassword\": \"%s\", \"userGender\": \"%s\", \"userDob\": \"%s\", \"userAddress\": \"%s\", \"userContact\": \"%s\"}",
                updateUserId, userName, userPassword, userGender, sdf.format(userDob), userAddress, userContact
        );

        // Send HTTP request to update own profile
        try {
            URL url = new URL("http://localhost:8080/api/users/" + updateUserId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Send JSON data
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Handle response
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Updated your profile successfully!");
            } else {
                System.out.println("Failed to update your profile. Server responded with status code: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("Error occurred while updating your profile: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * Search user (Admin for all users) + http
     */
    public static void searchUser() {
        if (currentLoginUser == null) {
            System.out.println("You should login first!");
            return;
        }

        try {
            // Get User data
            URL userUrl = new URL("http://localhost:8080/api/users");
            HttpURLConnection userConn = (HttpURLConnection) userUrl.openConnection();
            userConn.setRequestMethod("GET");
            userConn.setRequestProperty("Authorization", "Bearer your-auth-token");

            int userResponseCode = userConn.getResponseCode();
            if (userResponseCode == HttpURLConnection.HTTP_OK) {
                try (Scanner userScanner = new Scanner(userConn.getInputStream())) {
                    String userResponse = userScanner.useDelimiter("\\A").next();
                    System.out.println("All User Data: " + userResponse);
                }
            } else {
                System.out.println("Failed to retrieve user data. Server responded with status code: " + userResponseCode);
                return;
            }

            // Get Monitor data
            URL monitorUrl = new URL("http://localhost:8080/api/monitors");
            HttpURLConnection monitorConn = (HttpURLConnection) monitorUrl.openConnection();
            monitorConn.setRequestMethod("GET");
            monitorConn.setRequestProperty("Authorization", "Bearer your-auth-token");

            int monitorResponseCode = monitorConn.getResponseCode();
            if (monitorResponseCode == HttpURLConnection.HTTP_OK) {
                try (Scanner monitorScanner = new Scanner(monitorConn.getInputStream())) {
                    String monitorResponse = monitorScanner.useDelimiter("\\A").next();
                    System.out.println("All Monitor Data: " + monitorResponse);
                }
            } else {
                System.out.println("Failed to retrieve monitor data. Server responded with status code: " + monitorResponseCode);
            }

            // Get UserHospital data
            URL userHospitalUrl = new URL("http://localhost:8080/api/user-hospitals");
            HttpURLConnection userHospitalConn = (HttpURLConnection) userHospitalUrl.openConnection();
            userHospitalConn.setRequestMethod("GET");
            userHospitalConn.setRequestProperty("Authorization", "Bearer your-auth-token");

            int userHospitalResponseCode = userHospitalConn.getResponseCode();
            if (userHospitalResponseCode == HttpURLConnection.HTTP_OK) {
                try (Scanner userHospitalScanner = new Scanner(userHospitalConn.getInputStream())) {
                    String userHospitalResponse = userHospitalScanner.useDelimiter("\\A").next();
                    System.out.println("All User Hospital Data: " + userHospitalResponse);
                }
            } else {
                System.out.println("Failed to retrieve user hospital data. Server responded with status code: " + userHospitalResponseCode);
            }

        } catch (Exception e) {
            System.out.println("Error occurred while searching user data: " + e.getMessage());
            e.printStackTrace();
        }
    }



    /**
     * Search user and related data (Admin for Patient for himself) + http
     */
    public static void searchUserPatient() {
        // Check if the current user is a Patient
        if (currentLoginUser == null || !currentLoginUser.getUserRole().equals("Patient")) {
            System.out.println("You need to be logged in as a Patient to search for your own data!");
            return;
        }

        try {
            Long userId = currentLoginUser.getUserId();

            // Get User data
            URL userUrl = new URL("http://localhost:8080/api/users/" + userId);
            HttpURLConnection userConn = (HttpURLConnection) userUrl.openConnection();
            userConn.setRequestMethod("GET");
            userConn.setRequestProperty("Authorization", "Bearer your-auth-token");

            int userResponseCode = userConn.getResponseCode();
            if (userResponseCode == HttpURLConnection.HTTP_OK) {
                try (Scanner userScanner = new Scanner(userConn.getInputStream())) {
                    String userResponse = userScanner.useDelimiter("\\A").next();
                    System.out.println("Your User Data: " + userResponse);
                }
            } else {
                System.out.println("Failed to retrieve your user data. Server responded with status code: " + userResponseCode);
                return;
            }

            // Get Monitor data
            URL monitorUrl = new URL("http://localhost:8080/api/monitors/" + userId);
            HttpURLConnection monitorConn = (HttpURLConnection) monitorUrl.openConnection();
            monitorConn.setRequestMethod("GET");
            monitorConn.setRequestProperty("Authorization", "Bearer your-auth-token");

            int monitorResponseCode = monitorConn.getResponseCode();
            if (monitorResponseCode == HttpURLConnection.HTTP_OK) {
                try (Scanner monitorScanner = new Scanner(monitorConn.getInputStream())) {
                    String monitorResponse = monitorScanner.useDelimiter("\\A").next();
                    System.out.println("Your Monitor Data: " + monitorResponse);
                }
            } else {
                System.out.println("Failed to retrieve your monitor data. Server responded with status code: " + monitorResponseCode);
            }

            // Get UserHospital data
            URL userHospitalUrl = new URL("http://localhost:8080/api/user-hospitals/" + userId);
            HttpURLConnection userHospitalConn = (HttpURLConnection) userHospitalUrl.openConnection();
            userHospitalConn.setRequestMethod("GET");
            userHospitalConn.setRequestProperty("Authorization", "Bearer your-auth-token");

            int userHospitalResponseCode = userHospitalConn.getResponseCode();
            if (userHospitalResponseCode == HttpURLConnection.HTTP_OK) {
                try (Scanner userHospitalScanner = new Scanner(userHospitalConn.getInputStream())) {
                    String userHospitalResponse = userHospitalScanner.useDelimiter("\\A").next();
                    System.out.println("Your User Hospital Data: " + userHospitalResponse);
                }
            } else {
                System.out.println("Failed to retrieve your user hospital data. Server responded with status code: " + userHospitalResponseCode);
            }

        } catch (Exception e) {
            System.out.println("Error occurred while searching your user data: " + e.getMessage());
            e.printStackTrace();
        }
    }





    /**
     * Add hospital (Admin) + http
     */
    public static void addHospital() {
        if (currentLoginUser == null) {
            System.out.println("You should login first!");
            return;
        }

        System.out.print("Hospital Name: ");
        String hospitalName = in.nextLine();
        System.out.print("Hospital Level (High/Medium/Low): ");
        String hospitalLevel = in.nextLine();
        System.out.print("Hospital Address: ");
        String hospitalAddress = in.nextLine();
        System.out.print("Hospital Contact: ");
        String hospitalContact = in.nextLine();

        String jsonInputString = String.format(
                "{\"hospitalName\": \"%s\", \"hospitalLevel\": \"%s\", \"hospitalAddress\": \"%s\", \"hospitalContact\": \"%s\"}",
                hospitalName, hospitalLevel, hospitalAddress, hospitalContact
        );

        try {
            URL url = new URL("http://localhost:8080/api/hospitals");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Add hospital successfully!");
            } else {
                System.out.println("Add hospital failed!");
            }
        } catch (Exception e) {
            System.out.println("Error occurred while adding hospital: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * Delete hospital (Admin) + http
     */
    public static void deleteHospital() {
        if (currentLoginUser == null) {
            System.out.println("You should login first!");
            return;
        }

        searchHospital();

        System.out.print("Hospital Id to be deleted: ");
        Long deleteHospitalId = Long.parseLong(in.nextLine());

        try {
            URL url = new URL("http://localhost:8080/api/hospitals/" + deleteHospitalId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Delete hospital with hospital Id = " + deleteHospitalId + " successfully!");
            } else {
                System.out.println("Hospital with hospital Id = " + deleteHospitalId + " does not exist, delete failed!");
            }
        } catch (Exception e) {
            System.out.println("Error occurred while deleting hospital: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * Update hospital (Admin)
     */
    public static void updateHospital() {
        if (currentLoginUser == null) {
            System.out.println("You should login first!");
            return;
        }

        searchHospital();

        System.out.print("Hospital Id to be updated: ");
        Long updateHospitalId = Long.parseLong(in.nextLine());
        System.out.print("Updated Hospital Name: ");
        String hospitalName = in.nextLine();
        System.out.print("Updated Hospital Level (High/Medium/Low): ");
        String hospitalLevel = in.nextLine();
        System.out.print("Updated Hospital Address: ");
        String hospitalAddress = in.nextLine();
        System.out.print("Updated Hospital Contact: ");
        String hospitalContact = in.nextLine();

        String jsonInputString = String.format(
                "{\"hospitalId\": %d, \"hospitalName\": \"%s\", \"hospitalLevel\": \"%s\", \"hospitalAddress\": \"%s\", \"hospitalContact\": \"%s\"}",
                updateHospitalId, hospitalName, hospitalLevel, hospitalAddress, hospitalContact
        );

        try {
            URL url = new URL("http://localhost:8080/api/hospitals/" + updateHospitalId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Update hospital successfully!");
            } else {
                System.out.println("Update hospital failed!");
            }
        } catch (Exception e) {
            System.out.println("Error occurred while updating hospital: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * Search hospital (Admin) + http
     */
    public static void searchHospital() {
        if (currentLoginUser == null) {
            System.out.println("You should login first!");
            return;
        }

        try {
            URL url = new URL("http://localhost:8080/api/hospitals");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (Scanner responseScanner = new Scanner(conn.getInputStream())) {
                    String response = responseScanner.useDelimiter("\\A").next();
                    System.out.println("All Hospitals");
                    System.out.println("Hospital ID|Hospital Name|Hospital Level|Hospital Address|Hospital Contact");
                    System.out.println(response);
                }
            } else {
                System.out.println("Failed to fetch hospitals. Server responded with status code: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("Error occurred while fetching hospitals: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * Make hospitalization for patient (Admin) + http
     */
    public static void makeHospitalization() {
        if (currentLoginUser == null) {
            System.out.println("You should login first!");
            return;
        }

        searchUser();
        searchHospital();

        System.out.print("User Id: ");
        Long userId = Long.parseLong(in.nextLine());
        System.out.print("Hospital Id: ");
        Long hospitalId = Long.parseLong(in.nextLine());
        Date hospitalizationDate = new Date();
        Date dischargeDate = null;

        // 构造 JSON 数据
        String jsonInputString = String.format(
                "{\"userId\": %d, \"hospitalId\": %d, \"hospitalizationDate\": \"%s\", \"dischargeDate\": null}",
                userId, hospitalId, new SimpleDateFormat("yyyy-MM-dd").format(hospitalizationDate)
        );

        try {
            URL url = new URL("http://localhost:8080/api/user-hospitals");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // 发送数据
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // 处理响应
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Make hospitalization successfully!");
            } else {
                System.out.println("Failed to make hospitalization. Server responded with status code: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("Error occurred while making hospitalization: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * Search all hospitalizations (Admin) + http
     */
    public static void searchHospitalization() {
        try {
            URL url = new URL("http://localhost:8080/api/user-hospitals");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 处理响应
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (Scanner responseScanner = new Scanner(conn.getInputStream())) {
                    String response = responseScanner.useDelimiter("\\A").next();
                    System.out.println("All Hospitalizations:");
                    System.out.println("User Hospital ID|User ID|Hospital ID|Hospitalization Date|Discharge Date");
                    System.out.println(response); // 显示结果
                }
            } else {
                System.out.println("Failed to retrieve hospitalizations. Server responded with status code: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("Error occurred while searching hospitalizations: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * Update hospitalization for patient (Admin) + http
     */
    public static void updateHospitalization() throws ParseException {
        if (currentLoginUser == null) {
            System.out.println("You should login first!");
            return;
        }

        searchHospitalization();

        System.out.print("User Hospital Id to be updated: ");
        Long updateUserHospitalId = Long.parseLong(in.nextLine());
        System.out.print("Updated Discharge Date (yyyy-MM-dd): ");
        Date dischargeDate = sdf.parse(in.nextLine());

        // 构造 JSON 数据
        String jsonInputString = String.format(
                "{\"userHospitalId\": %d, \"dischargeDate\": \"%s\"}",
                updateUserHospitalId, sdf.format(dischargeDate)
        );

        try {
            URL url = new URL("http://localhost:8080/api/user-hospitals/" + updateUserHospitalId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // 发送数据
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // 处理响应
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Update user hospital successfully!");
            } else {
                System.out.println("Failed to update user hospital. Server responded with status code: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("Error occurred while updating hospitalization: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * Check whether monitor data is valid
     */
     public static boolean checkMonitor(Monitor monitor) {
         double height = monitor.getHeight();
         if (height < 0.00 || height > 3.00) {
             return false;
         }
         double weight = monitor.getWeight();
         if (weight < 0.00 || weight > 200.00) {
             return false;
         }
         double bodyTemperature = monitor.getBodyTemperature();
         if (bodyTemperature < 35.0 || bodyTemperature > 43.0) {
             return false;
         }
         int bloodPressureHigh = monitor.getBloodPressureHigh();
         if (bloodPressureHigh < 0 || bloodPressureHigh > 200) {
             return false;
         }
         int bloodPressureLow = monitor.getBloodPressureLow();
         if (bloodPressureLow < 0 || bloodPressureLow > 200) {
             return false;
         }
         double bloodOxygen = monitor.getBloodOxygen();
         if (bloodOxygen < 0 || bloodOxygen > 100) {
             return false;
         }
         double bloodGlucose = monitor.getBloodGlucose();
         if (bloodGlucose < 0 || bloodGlucose > 20) {
             return false;
         }
         double bloodLipid = monitor.getBloodLipid();
         return bloodLipid >= 0 && bloodLipid <= 20;
     }

    /**
     * Add monitor data for patient (Admin) + http
     */
    public static void addMonitorData() {
        if (currentLoginUser == null) {
            System.out.println("You should login first!");
            return;
        }

        searchHospitalization();

        System.out.print("User Hospital Id: ");
        Long userHospitalId = Long.parseLong(in.nextLine());

        System.out.print("Height(m, 0.00~3.00): ");
        double height = Double.parseDouble(in.nextLine());
        System.out.print("Weight(kg, 0.00~200.00): ");
        double weight = Double.parseDouble(in.nextLine());
        System.out.print("Body temperature(35.0~43.0): ");
        double bodyTemperature = Double.parseDouble(in.nextLine());
        System.out.print("Heart rate(0~200): ");
        int heartRate = Integer.parseInt(in.nextLine());
        System.out.print("Blood pressure high(0~200): ");
        int bloodPressureHigh = Integer.parseInt(in.nextLine());
        System.out.print("Blood pressure low(0~200): ");
        int bloodPressureLow = Integer.parseInt(in.nextLine());
        System.out.print("Blood oxygen(0~100): ");
        double bloodOxygen = Double.parseDouble(in.nextLine());
        System.out.print("Blood glucose(mmol/L, 0~20): ");
        double bloodGlucose = Double.parseDouble(in.nextLine());
        System.out.print("Blood lipid(mmol/L, 0~20): ");
        double bloodLipid = Double.parseDouble(in.nextLine());
        Date monitorTime = new Date();

        // 构造 JSON 请求体
        String jsonInputString = String.format(
                "{\"userHospitalId\": %d, \"monitorTime\": \"%s\", \"height\": %.2f, \"weight\": %.2f, \"bodyTemperature\": %.2f, \"heartRate\": %d, \"bloodPressureHigh\": %d, \"bloodPressureLow\": %d, \"bloodOxygen\": %.2f, \"bloodGlucose\": %.2f, \"bloodLipid\": %.2f}",
                userHospitalId, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(monitorTime),
                height, weight, bodyTemperature, heartRate, bloodPressureHigh, bloodPressureLow,
                bloodOxygen, bloodGlucose, bloodLipid
        );

        try {
            URL url = new URL("http://localhost:8080/api/monitors");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Add monitor data successfully!");
            } else {
                System.out.println("Failed to add monitor data. Server responded with status code: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("Error occurred while adding monitor data: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * Search monitor data for patient (Admin) + http
     */
    public static void searchMonitorData() {
        if (currentLoginUser == null) {
            System.out.println("You should login first!");
            return;
        }

        try {
            URL url = new URL("http://localhost:8080/api/monitors");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (Scanner responseScanner = new Scanner(conn.getInputStream())) {
                    String response = responseScanner.useDelimiter("\\A").next();
                    System.out.println("All Monitor Data");
                    System.out.println("Monitor ID|User Hospital ID|Monitor Time|Height|Weight|Body Temperature|Heart Rate|Blood Pressure High|Blood Pressure Low|Blood Oxygen|Blood Glucose|Blood Lipid");
                    System.out.println(response);
                }
            } else {
                System.out.println("Failed to fetch monitor data. Server responded with status code: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("Error occurred while fetching monitor data: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * Update monitor data for patient (Admin) + http
     */
    public static void updateMonitorData() {
        if (currentLoginUser == null) {
            System.out.println("You should login first!");
            return;
        }

        searchMonitorData();

        System.out.print("Monitor Data Id: ");
        Long monitorId = Long.parseLong(in.nextLine());
        System.out.print("Height(m, 0.00~3.00): ");
        double height = Double.parseDouble(in.nextLine());
        System.out.print("Weight(kg, 0.00~200.00): ");
        double weight = Double.parseDouble(in.nextLine());
        System.out.print("Body temperature(35.0~43.0): ");
        double bodyTemperature = Double.parseDouble(in.nextLine());
        System.out.print("Heart rate(0~200): ");
        int heartRate = Integer.parseInt(in.nextLine());
        System.out.print("Blood pressure high(0~200): ");
        int bloodPressureHigh = Integer.parseInt(in.nextLine());
        System.out.print("Blood pressure low(0~200): ");
        int bloodPressureLow = Integer.parseInt(in.nextLine());
        System.out.print("Blood oxygen(0~100): ");
        double bloodOxygen = Double.parseDouble(in.nextLine());
        System.out.print("Blood glucose(mmol/L, 0~20): ");
        double bloodGlucose = Double.parseDouble(in.nextLine());
        System.out.print("Blood lipid(mmol/L, 0~20): ");
        double bloodLipid = Double.parseDouble(in.nextLine());
        Date monitorTime = new Date();

        // 构造 JSON 请求体
        String jsonInputString = String.format(
                "{\"monitorId\": %d, \"monitorTime\": \"%s\", \"height\": %.2f, \"weight\": %.2f, \"bodyTemperature\": %.2f, \"heartRate\": %d, \"bloodPressureHigh\": %d, \"bloodPressureLow\": %d, \"bloodOxygen\": %.2f, \"bloodGlucose\": %.2f, \"bloodLipid\": %.2f}",
                monitorId, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(monitorTime),
                height, weight, bodyTemperature, heartRate, bloodPressureHigh, bloodPressureLow,
                bloodOxygen, bloodGlucose, bloodLipid
        );

        try {
            URL url = new URL("http://localhost:8080/api/monitors/" + monitorId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Update monitor data successfully!");
            } else {
                System.out.println("Failed to update monitor data. Server responded with status code: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("Error occurred while updating monitor data: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * Hospital statistics, including total number of patients, and number of hospitalizing patients
     */
    public static void hospitalStatistics() {
        if (currentLoginUser == null) {
            System.out.println("You should login first!");
            return;
        }

        try {
            URL url = new URL("http://localhost:8080/api/user-hospitals/statistics");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (Scanner responseScanner = new Scanner(conn.getInputStream())) {
                    String response = responseScanner.useDelimiter("\\A").next();
                    // Assuming response is a JSON array that can be deserialized into a List of statistics
                    List<List<String>> statisticsList = new ObjectMapper().readValue(response, List.class);

                    System.out.println("User Hospital Statistics");
                    System.out.println("Hospital ID|Num Hospital Patients|Num Hospitalizing Patients");
                    for (List<String> statistics : statisticsList) {
                        String hospitalId = statistics.get(0);
                        String numHospitalPatients = statistics.get(1);
                        String numHospitalizingPatients = statistics.get(2);
                        System.out.println(hospitalId + "|" + numHospitalPatients + "|" + numHospitalizingPatients);
                    }
                }
            } else {
                System.out.println("Failed to fetch hospital statistics. Server responded with status code: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("Error occurred while fetching hospital statistics: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Main menu of patient
     */
    public static void patientMenu() throws ParseException {
        while (true)
            for (int i = 1; i <= 30; i++) {
                System.out.print("*");
            }
            System.out.println();
            System.out.println("Patient Monitoring Warning System - Patient");
            System.out.println("[1] Update Patient Profile");
            System.out.println("[2] Search Patient's Information");
            System.out.println("[0] Exit");
            for (int i = 1; i <= 30; i++) {
                System.out.print("*");
            }
            System.out.println();
            System.out.print("Please input your choice [0-2]: ");
            int choice = Integer.parseInt(in.nextLine());
            if (choice == 1) {  // Update User Profile
                updateUserProfile();
            } else if (choice == 2) {  // Search User
                searchUser();
            } else if (choice == 0) {  // Exit
                System.out.println("You have returned to the main menu! ");
                break;
            }
            System.out.println();
        }
    }

    /**
     * Main menu of admin
     */
    public static void adminMenu() throws ParseException {
        while (true) {
            for (int i = 1; i <= 30; i++) {
                System.out.print("*");
            }
            System.out.println();
            System.out.println("Patient Monitoring Warning System - Admin");
            System.out.println("[1] Delete User");
            System.out.println("[2] Update User Profile");
            System.out.println("[3] Search User");
            System.out.println("[4] Add Hospital");
            System.out.println("[5] Delete Hospital");
            System.out.println("[6] Update Hospital");
            System.out.println("[7] Search Hospital");
            System.out.println("[8] Make Hospitalization");
            System.out.println("[9] Update Hospitalization");
            System.out.println("[10] Add Monitor Data");
            System.out.println("[11] Update Monitor Data");
            System.out.println("[12] Search Monitor Data");
            System.out.println("[13] Hospital Statistics");
            System.out.println("[0] Return to Main Menu");
            for (int i = 1; i <= 30; i++) {
                System.out.print("*");
            }
            System.out.println();
            System.out.print("Please input your choice [0-13]: ");
            int choice = Integer.parseInt(in.nextLine());
            if (choice == 1) {  // Delete User
                deleteUser();
            } else if (choice == 2) {  // Update User Profile
                updateUserProfile();
            } else if (choice == 3) {  // Search User
                searchUser();
            } else if (choice == 4) {  // Add Hospital
                addHospital();
            } else if (choice == 5) {  // Delete Hospital
                deleteHospital();
            } else if (choice == 6) {  // Update Hospital
                updateHospital();
            } else if (choice == 7) {  // Search Hospital
                searchHospital();
            } else if (choice == 8) {  // Make Hospitalization
                makeHospitalization();
            } else if (choice == 9) {  // Update Hospitalization
                updateHospitalization();
            } else if (choice == 10) {  // Add Monitor Data
                addMonitorData();
            } else if (choice == 11) {  // Update Monitor Data
                updateMonitorData();
            } else if (choice == 12) {  // Search Monitor Data
                searchMonitorData();
            } else if (choice == 13) {  // Hospital Statistics
                hospitalStatistics();
            } else if (choice == 0) {  // Returned to Main Menu
                System.out.println("You have returned to main menu! ");
                break;
            }
            System.out.println();
        }
    }

    /**
     * Main menu of the system
     */
    public static void mainMenu() throws ParseException {
        while (true) {
            for (int i = 1; i <= 30; i++) {
                System.out.print("*");
            }
            System.out.println();
            System.out.println("Welcome to the patient monitoring warning system");
            System.out.println("[1] Login");
            System.out.println("[2] Register");
            System.out.println("[0] Exit");
            for (int i = 1; i <= 30; i++) {
                System.out.print("*");
            }
            System.out.println();
            System.out.print("Please input your choice [0-2]: ");
            int choice = Integer.parseInt(in.nextLine());
            if (choice == 1) {  // Login
                login();
            } else if (choice == 2) {  // Register
                register();
            } else if (choice == 0) {  // Exit
                System.out.println("You have exited the system! ");
                break;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws ParseException {
        mainMenu();
    }

}
