# Springboot-JPA-foodtruckapp

### Technologies :
* Springboot(v1.5.8.Release)
* JPA
* MySQL(version 5.5)
* Java(v1.8.0)
* Maven(v3.3.3)

This app is hosted on an EC2 instance : 
* Host : ec2-34-207-208-150.compute-1.amazonaws.com
* OS : Redhat Linux(Amazon Linux AMI)
* Database used : MySQL

### Operations : 
This app provides an API which allows following operations on this(https://data.sfgov.org/d/rqzj-sfat) dataset -  
#### 1. Search by name of applicant :
  * Request Type : GET
  * URL : http://ec2-34-207-208-150.compute-1.amazonaws.com:8080/foodtrucks/applicant/{applicant}
  * Example : `http://ec2-34-207-208-150.compute-1.amazonaws.com:8080/foodtrucks/applicant/Mora Taco Truck`
  
#### 2. Search by name of street name :
  * Request Type : GET
  * URL : http://ec2-34-207-208-150.compute-1.amazonaws.com:8080/foodtrucks/street/{streetName}
  * Example : `http://ec2-34-207-208-150.compute-1.amazonaws.com:8080/foodtrucks/street/1133 MISSION ST`
  
#### 3. Search by expiration date, to find whose permits have expired : 
  * Request Type : POST
  * URL: http://ec2-34-207-208-150.compute-1.amazonaws.com:8080/foodtrucks/expirydate
  * Content-Type: application/json;charset=UTF-8
  * Request Body: {"searchdate":"*expiryDateToSearch*"}
  * Request Body Example: `{"searchdate":"02/15/2025 12:00:00 AM"}`
  
#### 4. Add new foodtruck entry to the dataset :
  * Request Type : POST
  * URL: http://ec2-34-207-208-150.compute-1.amazonaws.com:8080/foodtrucks
  * Content-Type: application/json;charset=UTF-8
  * Request Body: {"*anyFieldNameInDataSet*":"*fieldValue*"}
  * Request Body Example: 
  `{ 
    "locationid" : 12345678,
    "applicant" : "JSN hotdogs",
    "*anyOtherFieldName*":"*otherFieldValue*"
   }`
  
#### 5. Delete a foodtruck from dataset(this is done using id(the primary key of the database)) : 
  * Request Type : DELETE
  * URL : http://ec2-34-207-208-150.compute-1.amazonaws.com:8080/foodtrucks/{id}
  * Example : `http://ec2-34-207-208-150.compute-1.amazonaws.com:8080/foodtrucks/699`
  
#### 6. Given one or multiple locations, predict which truck will be the best one to assign the job(based on nearest truck by distance):
  * Request Type : POST
  * URL: http://ec2-34-207-208-150.compute-1.amazonaws.com:8080/foodtrucks/location
  * Content-Type: application/json;charset=UTF-8
  * Request Body: {"locations"[{"latitude":"*latitudeValue*","longitude":"*longitudeValue*"}]}
  * Request Body Example: 
  `{ "locations": [
        {
          "latitude": 37.7265904795194,
          "longitude": -122.381752211406
        },
        {
          "latitude": 37.7698977488961,
          "longitude": -122.391098279635
        }
      ]
  }`
  
#### 7. Auto expiry of licenses : 
  * This is a check that runs with every request if any new licenses have expired based on the expiration date assigned to the foodtrucks.
