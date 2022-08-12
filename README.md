
# FamilyApp - Dockerized Spring Boot Microservices with Database

## Introduction
The aim of the task is to create an application in a distributed architecture. The application should consist of
the following components:
- Database component - Postgres - containing tables for data storage.
- Two components with services (REST services) written in SpringBoot technology

## Architecture
The application uses a microservice architecture and consists of three components:
- Database component (two different schemas - FamilyDB schema, FamilyMemberDB schema)
- Application component issuing REST services to support the FamilyApp family
- Application component issuing REST services to support family members - FamilyMemberApp


## Installation
To run docker application run docker-compose command in the root folder:
```
docker-compose up
```
This command will also build your containers if you don't have them yet.


## Usage and Functionality

The application is used to store and retrieve information about the family. \
There are two use cases:

### Add new family
```
POST localhost:8080/api/family
```
#### JSON response body Example:
```
{
    "familyName": "Kowalski",
    "nrOfInfants": "0",
    "nrOfChildren": "0",
    "nrOfAdults": "2",
    "familyMembers": [
        {
            "givenName": "Tom",
            "familyName": "Kowalski",
            "age": "24"
        },
        {
            "givenName": "Jane",
            "familyName": "Kowalska",
            "age": "22"
        }
    ]
}
```
#### Response: Generated family id

### Retrieve family by id
```
GET localhost:8080/api/family/{id}
```
where **id** is the unique id of the family.
#### Response: Family data
#### Example response:
```
{
    "id": 1,
    "familyName": "Kowalski",
    "nrOfInfants": "0",
    "nrOfChildren": "0",
    "nrOfAdults": "2",
    "familyMembers": [
        {
            "id": 1
            "familyId": 1
            "givenName": "Tom",
            "familyName": "Kowalski",
            "age": "24"
        },
        {
            "id": 2
            "familyId": 1
            "givenName": "Jane",
            "familyName": "Kowalska",
            "age": "22"
        }
    ]
}
```
