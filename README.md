# Spring Boot Application Demo - News

## DevGuid

## How to create a new flyway migration
Every change in the database schema MUST be documented with a flyway migration script.
Create a unique migration script with gradle in the project root:
```
    gradle addNewMigration -PmigrationName=add_foo_bar
```