# University Project: TreasureHunter

This project is a proof-of-concept web game called "Treasure Hunter".
Inspired by real-world geocaching games, players hide treasures in physical locations and use unique codes to mark them as found.
It was developed for a university web development course.

## Features
- Players can create accounts and log in.
- Users can create, view, and manage treasures and their locations.
- Players can mark treasures as found by submitting the correct unlock code.
- The backend uses clear layers (controller, service, repository) for maintainability.

## Tech
- Java 17
- Spring Boot (Spring Web, Spring Data JPA and Spring Security)
- JWT for user authentication
- H2 Database (In-Memory)
- Thymeleaf (Server-Side Templating)

## Important
- For ease of testing JWT security filters are disabled.
- The original Thymeleaf frontend from the university project is now in the /_archived_frontend folder. The primary focus of this project is the REST API backend.

## Before running the API create the properties file from the template
```bash
cp application.properties.example application.properties
```
Then, open application.properties and add your own unique secret key.

## API Endpoints
**Authentication (/api/v1/auth)**
- POST /signup: Registers a new user.
- POST /signin: Authenticates a user and returns a JWT.

**Locations (/api/location)**
- GET /get-locations: Returns a list of all locations.
- GET /get-location/{id}: Returns a location by its ID.
- POST /add-location: Creates a new location.
- PUT /update-location/{id}: Updates an existing location.
- DELETE /delete-location/{id}: Deletes a location.
- GET /sample-locations: (For testing) Populates the database with sample locations.

**Treasures (/api/treasure)**
- GET /get-treasures: Returns a list of all treasures.
- GET /get-treasure/{id}: Returns a treasure by its ID.
- GET /get-treasures-by-location/{id}: Gets all treasures for a specific location.
- POST /add-treasure/{locationId}: Creates a new treasure linked to a location.
- PUT /update-treasure/{id}: Updates an existing treasure.
- POST /mark-as-found/{id}: Marks a treasure as found using its unlockId.
- DELETE /delete-treasure/{id}: Deletes a treasure.
- GET /sample-treasures: (For testing) Populates the database with sample treasures.