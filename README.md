# O Povo
## O Povo Api
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-%23ED8B00.svg?style=for-the-badge&logo=mysql&logoColor=white&color=%233d6ab2)
[![Licence](https://img.shields.io/github/license/Ileriayo/markdown-badges?style=for-the-badge)](./LICENSE)

This project is an API built using **Java, Java Spring and MySQL.**

## Table of Contents

- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Database](#database)
- [Contributing](#contributing)

## Installation

1. Clone the repository:

```bash
git clone https://github.com/mtbassi/opovo-api.git
```

2. Install dependencies with Maven

3. Create a configuration with your runtime environment variables with your Data Base Credentials that are used in `application.properties`

```yaml
#Database settings
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/${DATABASE_NAME}
spring.datasource.driverClassName=com.mysql.jdbc.Driver
spring.datasource.username=${MYSQL_USERNAME}
spring.datasource.password=${MYSQL_PASSWORD}
#Token secret
api.security.token.secret=${JWT_SECRET:my-secret-key}
```

**Config Values**

```yaml
DATABASE_NAME=VALUE;MYSQL_USERNAME=VALUE2;MYSQL_PASSWORD=VALUE3;JWT_SECRET=VALUE4
```
## Usage

1. Start the application with Maven
2. The API will be accessible at http://localhost:8080/api
3. The Swagger will be accessible at http://localhost:8080/api/swagger-ui/index.html

## API Endpoints
The API provides the following endpoints:

**Auth**
```markdown
POST /login - Authenticates the user.
```

**Journalist**
```markdown
POST /register - Register a journalist.
GET  /me       - Retrieves information from the journalist.
```

**News**
```markdown
POST   /news/create        - Create news.
GET    /news/me            - List news.
GET    /news/type/{typeId} - List news by type of news.
PUT    /news/update/{id}   - Update news.
DELETE /news/delete/{id}   - Delete news.
```

**News type**
```markdown
POST   /type/create        - Create news type.
GET    /type/me            - List news type.
PUT    /type/update/{id}   - Update news type.
DELETE /type/delete/{id}   - Delete news type.
```

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request to the repository.

When contributing to this project, please follow the existing code style, [commit conventions](https://www.conventionalcommits.org/en/v1.0.0/), and submit your changes in a separate branch.
