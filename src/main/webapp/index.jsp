<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html ng-app="atmApplication">
<head>
<meta charset="utf-8">
<title>ATM Application</title>

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script src="js/atm-app.js"></script>

</head>
<body ng-controller="atmController">
  <div class="container">
    <h2>ATM Search Utility:</h2>

    <input type="text" ng-model="city" /> 
    
    <select name="singleSelect" ng-model="type" >
      <option value="option-1">Filter Remotely</option>
      <option value="option-2">Filter Locally</option>
    </select>
    
    <input type="submit" ng-click="updateResults()" value="Search" />

    <p ng-if="atms.length == 0">No records</p>

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
