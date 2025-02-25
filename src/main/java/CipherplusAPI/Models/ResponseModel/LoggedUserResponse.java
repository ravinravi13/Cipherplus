package CipherplusAPI.Models.ResponseModel;

import CipherplusAPI.BaseService.BaseService;

import java.util.List;

public class LoggedUserResponse extends BaseService {
    
   
        public int statusCode;
        public boolean isSuccess;
        public String errorMessage;
        public Result result;



    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }


        public static class Result {
            public int employeeId;
            public String name;
            public String email;
            public int pointsEarned;
            public int lifetimePoints;
            public String graphId;
            public int adminLevel;
            public String location;
            public String manager;
            public boolean managerLevel;
            public String unitName;
            public int pointsForDistribution;
            public boolean functionalHeadLevel;
            public boolean isActive;
            public String empCode;
            public String address;
            public int superAdminLevel;
            public int access;
            public String design;
            public String passport_Size_Photo_downloadUrl;


            public Result(){

            }





            public int getEmployeeId() {
                return employeeId;
            }

            public void setEmployeeId(int employeeId) {
                this.employeeId = employeeId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public int getPointsEarned() {
                return pointsEarned;
            }

            public void setPointsEarned(int pointsEarned) {
                this.pointsEarned = pointsEarned;
            }

            public int getLifetimePoints() {
                return lifetimePoints;
            }

            public void setLifetimePoints(int lifetimePoints) {
                this.lifetimePoints = lifetimePoints;
            }

            public String getGraphId() {
                return graphId;
            }

            public void setGraphId(String graphId) {
                this.graphId = graphId;
            }

            public int getAdminLevel() {
                return adminLevel;
            }

            public void setAdminLevel(int adminLevel) {
                this.adminLevel = adminLevel;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getManager() {
                return manager;
            }

            public void setManager(String manager) {
                this.manager = manager;
            }

            public boolean isManagerLevel() {
                return managerLevel;
            }

            public void setManagerLevel(boolean managerLevel) {
                this.managerLevel = managerLevel;
            }

            public String getUnitName() {
                return unitName;
            }

            public void setUnitName(String unitName) {
                this.unitName = unitName;
            }

            public int getPointsForDistribution() {
                return pointsForDistribution;
            }

            public void setPointsForDistribution(int pointsForDistribution) {
                this.pointsForDistribution = pointsForDistribution;
            }

            public boolean isFunctionalHeadLevel() {
                return functionalHeadLevel;
            }

            public void setFunctionalHeadLevel(boolean functionalHeadLevel) {
                this.functionalHeadLevel = functionalHeadLevel;
            }

            public boolean isActive() {
                return isActive;
            }

            public void setActive(boolean active) {
                isActive = active;
            }

            public String getEmpCode() {
                return empCode;
            }

            public void setEmpCode(String empCode) {
                this.empCode = empCode;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getSuperAdminLevel() {
                return superAdminLevel;
            }

            public void setSuperAdminLevel(int superAdminLevel) {
                this.superAdminLevel = superAdminLevel;
            }

            public int getAccess() {
                return access;
            }

            public void setAccess(int access) {
                this.access = access;
            }

            public String getDesign() {
                return design;
            }

            public void setDesign(String design) {
                this.design = design;
            }

            public String getPassport_Size_Photo_downloadUrl() {
                return passport_Size_Photo_downloadUrl;
            }

            public void setPassport_Size_Photo_downloadUrl(String passport_Size_Photo_downloadUrl) {
                this.passport_Size_Photo_downloadUrl = passport_Size_Photo_downloadUrl;
            }

        }
    }






























