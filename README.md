#Event Manager API: A Robust Solution for Community & Event Organization (Java + Spring)

Empower Your Event Management System with Comprehensive Functionality

This meticulously crafted Java-based (17 or 21) event management API, built upon the powerful Spring Boot framework, offers a comprehensive suite of features to streamline the management of communities, events, tickets, and associated images. Its RESTful interface fosters seamless integration into your existing infrastructure.

️ Project Structure (Modular for Maintainability)

The project embraces a well-defined, modular structure, enhancing maintainability and scalability:

eventomanager/
├── src/main/java/
│   ├── com.eddyemb.EventoManager
│   │   ├── controller/
│   │   │   ├── CommunityController.java
│   │   │   └── EventController.java
│   │   │   └── ImageController.java
│   │   │   └── TicketController.java
│   │   ├── dto.request
│   │   │   └── response
│   │   │       ├── CommunityResponse.java
│   │   │       ├── EventResponse.java
│   │   │       ├── TicketResponse.java
│   │   │   ├── CommunityRequest.java
│   │   │   ├── EventRequest.java
│   │   │   └── TicketRequest.java
│   │   ├── exception/
│   │   │   └── handle
│   │   │       └── EventoManagerExceptionHandler.java
│   │   │   ├── BadRequestExceptiont.java
│   │   │   ├── EntityNotFoundException
│   │   │   └── StandardError.java
│   │   ├── mapper/
│   │   │   ├── Mapper.java
│   │   │   ├── ModelMapperConfig.java
...(Still Working on it) - (Other resource files) :)
├── src/main/resources/
│   │       ├── static.EventoWeb
│   │       │   └── src
│   │       │        ├── css
│   │       │        ├── img
│   │       │        └── js
│   │       └── index.html
│   ├── application.properties (Spring configuration | DB Configuration)
│   └── ... (Other resource files)
├── pom.xml (Maven project configuration)
└── ... (Other project files)

#API Endpoints: Delivering Fine-Grained Control

- Communities (CRUD Operations):
    - POST /api/v1/communities: Create a new community
    - GET /api/v1/communities: Retrieve all communities
    - GET /api/v1/communities/{id}: Fetch a specific community by ID
    - PUT /api/v1/communities/{id}: Update an existing community
    - DELETE /api/v1/communities/{id}: Delete a community

- Events (CRUD Operations):
    - POST /api/v1/events: Create a new event
    - GET /api/v1/events: Retrieve all events (optional filtering by community ID)
    - GET /api/v1/events/{id}: Fetch a specific event by ID
    - PUT /api/v1/events/{id}: Update an existing event
    - DELETE /api/v1/events/{id}: Delete an event

- Tickets (CRUD Operations):
    - POST /api/v1/events/{eventId}/tickets: Create tickets for a specific event
    - GET /api/v1/events/{eventId}/tickets: Retrieve all tickets associated with an event
    - GET /api/v1/tickets/{id}: Fetch a specific ticket by ID

#Dependencies: Carefully Selected for Optimal Performance
    Spring Boot (https://spring.io/quickstart) for rapid application development
    Spring Data JPA (https://spring.io/projects/spring-data-jpa) for convenient database interaction

