# Note — This is just for learning purpose 

# Microservices
#### Microservices is an architecture pattern that is realized through a set of patterns and technologies. This article sets the foundation for our series which helps in understanding each of these patterns along with their sample implementations. Our exercise-driven learning series is based on Spring Boot, Spring Cloud, and the related technologies.

### What is Microservices?

The new architecture pattern is been adopted by almost all sizes of organizations, be it small, medium, or large. Organizations have started realizing the value of it. In spite of such widespread adoption of this pattern, it's unfortunate, there is no consistent definition of Microservices.

One of the early adopters of Microservices Architecture — Netflix describes it as nothing but a fine-grained SOA (Service-oriented Architecture). Martin Fowler, the pioneer of software development, says — the microservice architectural style is an approach to develop a single application as a suite of small services, each running in its own process and communicating with lightweight mechanisms, often an HTTP resource API. These services are built around business capabilities and independently deployable by fully automated deployment machinery.

Chris Richardson from https://microservices.io/ has laid out the characteristics of a Microservice more concretely I feel. He refers to it as an architectural style that structures an application as a collection of services that are

* Highly maintainable and testable
* Loosely coupled
* Independently deployable
* Organized around business capabilities
* Owned by a small team 

### What do Microservices Offer?

As services become smaller, the benefits are realized in multiple aspects
* 
* The foremost advantage of it is improved maintainability. Each service is relatively small and so is easier to understand, modify, and test.
* As each service is independent, you are free to choose a different technology stack for its development. This eliminates any long-term commitment to technology.
* As services are smaller, they can be independently tested in a comparatively less time frame. We do not need to wait for all the other modules to be validated before we push it for the next stage of deployment.
* One of the best offerings is independent deployment. This helps in faster delivery through continuous deployment.
* Software development can be more organized into smaller, autonomous teams. “small team” is a relative term though. Typically it consists of 5–10 people.
* Our application becomes more robust in terms of fault tolerance. If one service is down, it will not bring down other services (as was the case with monolith applications). Our application will still continue to serve other requests with increased availability.
* As the services can be deployed independently, we can scale-up or scale-down the service instances based on traffic. This improves the overall scalability and availability of our application significantly.


### Microservices Architecture

Though the Microservice characteristics look simple, it does not make the implementation easy. The existence of multiple services brings additional complexity in terms of interaction, testing, deployment, and other aspects. This demands a new ecosystem, with advanced patterns, frameworks, and technologies. Here is the list of some widely used patterns in the microservices world —

* Service decomposition — This is the first step that helps in breaking down the monolith application into smaller services.
* Service Discovery — This pattern helps in discovering the service instances at runtime, as the instances carry dynamically assigned network addresses.
* API gateway — An API gateway separates external public APIs From internal microservice APIs
* Microservices Chassis — Deals with cross-cutting concerns like configuration, logging, health check, distributed tracing, etc
* Containerization — This deals with the docker containers to create a more isolated environment for deployment.
* Circuit Breaker — This pattern ensures the service failure does not propagate to the other services.
* Service Mesh — The pattern provides a separate infrastructure layer for microservices communication, implementing multiple patterns underneath including service discovery, load balancing, circuit breaker, gateway, and many others.
* Choreography — The pattern is used to facilitate the complex interaction of services among themselves, especially in data pipelines.
* Saga — The pattern helps in implementing a single transaction spanned across multiple services.
* Auto-scalability — This helps in automatically scaling up or scaling down a particular microservice. Load balancing, Fault Tolerance adds up to the complexity.
* Access Token — Services interact with each other through access token validations.
