<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
    uri="http://www.springframework.org/security/tags"%>
<sec:authentication var="user" property="principal" />
<!DOCTYPE html>
<html ng-app="atmApplication">
    <head>
        <meta charset="utf-8">
        <title>ATM Application</title>
        <link rel="stylesheet"
            href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script
            src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular.min.js"></script>
        <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
        <script src="js/atm-app.js"></script>
    </head>
    <body ng-controller="atmController">
        <div class="container">
            Hello <b>${user.username}</b> (<a href="
            <c:url value="/logout" />
            ">Logout</a>)
            <h2>ATM Search Utility:</h2>
            City Name: <input type="text" ng-model="city" placeholder="City Name" /><br />
            Filter Type: 
            <select name="singleSelect" ng-init="type='option-1'"
                ng-model="type">
                <option value="option-1">Filter Remotely</option>
                <option value="option-2">Filter Locally</option>
            </select>
            <br /> API Version: 
            <select name="singleApiSelect" ng-init="api='1'"
                ng-model="api">
                <option value="1">API v1</option>
                <option value="2">API v2</option>
            </select>
            <br /> <input type="submit" ng-click="updateResults()" value="Search" />
            <div style="color: red">
                <p ng-if="atms.length == 0">No records</p>
            </div>
            <table class="table" ng-if="atms.length > 0">
                <thead class="thead-default">
                    <tr>
                        <th>City</th>
                        <th>Street</th>
                        <th>Type</th>
                        <th>Distance</th>
                        <th>Latitude</th>
                        <th>Longitude</th>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-repeat="atm in atms">
                        <td>{{atm.address.city}}</td>
                        <td>{{atm.address.street}}</td>
                        <td>{{atm.type}}</td>
                        <td>{{atm.distance}}</td>
                        <td>{{atm.address.geoLocation.lat}}</td>
                        <td>{{atm.address.geoLocation.lng}}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </body>
</html>