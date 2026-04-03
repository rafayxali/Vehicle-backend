# Vehicle-backend
# Dealer & Vehicle Inventory Module (Modular Monolith)

## Architecture
- **Clean Architecture:** Separation of Controller, Service, DTO, Domain, and Repository.
- **Multi-Tenancy:** All queries are strictly scoped using `X-Tenant-Id`.
- **Dynamic Filtering:** Implemented using JPA Specifications for scalable search (Price, Model, Status).

## Endpoints
- **Dealers/Vehicles:** Standard CRUD with Pagination/Sorting.
- **Admin:** `/admin/dealers/countBySubscription` 
  - *Note: This count is currently scoped per-tenant to ensure data isolation.*

## How to Run
1. Configure Neon DB credentials in `application.properties` or environment variables.
2. Run `mvn spring-boot:run`.
3. Access Swagger Documentation at `http://localhost:8080/swagger-ui/index.html`.
