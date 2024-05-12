# Expenses Tracker

## Setup

```bash
# Pull postgres Docker image
export POSTGRES_VERSION=14.5
docker pull postgres:${POSTGRES_VERSION}
# Run postgres instance
docker run --detach --restart=always --name postgres -e POSTGRES_PASSWORD=password -p 5432:5432 postgres:${POSTGRES_VERSION}
# Enter container
docker exec -ti postgres psql -U postgres
# create database and user
CREATE DATABASE expenses;
CREATE USER expenses WITH PASSWORD 'password123';
# Grant permissions
GRANT CONNECT ON DATABASE expenses TO expenses;
GRANT ALL PRIVILEGES ON DATABASE expenses TO expenses;
# Run application
mvn spring-boot:run
```
