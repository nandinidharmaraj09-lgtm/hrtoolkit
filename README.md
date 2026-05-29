# HR Toolkit Backend

A Spring Boot backend application for HR management system with support for Categories, Templates, Subscriptions, and Users.

## Technology Stack

- **Java 17**
- **Spring Boot 3.2.0**
- **MySQL 8.0**
- **Maven**
- **JPA/Hibernate**
- **Lombok**

## Entities

1. **Category** - HR-related categories
2. **Template** - Templates for different categories
3. **Subscription** - Subscription plans
4. **User** - System users with subscription support

## Prerequisites

- Java 17 or higher
- MySQL 8.0 or higher
- Maven 3.6+

## Setup Instructions

### 1. Create Database

```sql
CREATE DATABASE hr_toolkit;
```

### 2. Clone Repository

```bash
git clone https://github.com/nandinidharmaraj09-lgtm/hrtoolkit.git
cd hrtoolkit
```

### 3. Update Database Credentials

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hr_toolkit
spring.datasource.username=root
spring.datasource.password=Nandu!@
```

### 4. Build Project

```bash
mvn clean install
```

### 5. Run Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### Categories
- `GET /api/categories` - Get all categories
- `GET /api/categories/{id}` - Get category by ID
- `POST /api/categories` - Create new category
- `PUT /api/categories/{id}` - Update category
- `DELETE /api/categories/{id}` - Delete category

### Templates
- `GET /api/templates` - Get all templates
- `GET /api/templates/active` - Get active templates
- `GET /api/templates/{id}` - Get template by ID
- `GET /api/templates/category/{categoryId}` - Get templates by category
- `POST /api/templates` - Create new template
- `PUT /api/templates/{id}` - Update template
- `DELETE /api/templates/{id}` - Delete template

### Subscriptions
- `GET /api/subscriptions` - Get all subscriptions
- `GET /api/subscriptions/active` - Get active subscriptions
- `GET /api/subscriptions/{id}` - Get subscription by ID
- `POST /api/subscriptions` - Create new subscription
- `PUT /api/subscriptions/{id}` - Update subscription
- `DELETE /api/subscriptions/{id}` - Delete subscription

### Users
- `GET /api/users` - Get all users
- `GET /api/users/active` - Get active users
- `GET /api/users/{id}` - Get user by ID
- `GET /api/users/email/{email}` - Get user by email
- `POST /api/users` - Create new user
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

## Database Schema

The application uses JPA with Hibernate ORM. Tables are automatically created based on entity definitions.

### Relationships

- **Template** → **Category** (Many-to-One)
- **User** → **Subscription** (Many-to-One)

## Development

### IDE Setup

**IntelliJ IDEA:**
1. Open the project folder
2. Maven will auto-download dependencies
3. Run `HRToolkitApplication.java`

**VS Code:**
1. Install Extension Pack for Java
2. Run with `Debug` or `Run`

## Testing

Use Postman or any REST client to test endpoints:

```bash
# Example: Create a Category
curl -X POST http://localhost:8080/api/categories \
  -H "Content-Type: application/json" \
  -d '{"name": "Leave Management", "description": "Leave related templates"}'
```

## Troubleshooting

### Database Connection Error
- Ensure MySQL is running
- Check credentials in `application.properties`
- Verify database exists: `SHOW DATABASES;`

### Port Already in Use
- Change port in `application.properties`: `server.port=8081`

### Hibernate Mapping Error
- Clear Maven cache: `mvn clean`
- Restart IDE

## Future Enhancements

- Authentication & Authorization (JWT)
- Email notifications
- File upload support
- Advanced filtering & pagination
- Rate limiting
- API documentation (Swagger/OpenAPI)

## License

MIT License

## Author

Nandini Dharmaraj
