# Spring Boot Application Demo - News





## Builds

Genie builds are run on Travis CI [here](https://travis-ci.org/Netflix/genie).

| Branch |                                                     Build                                                     |                                                                 Coverage (coveralls.io)                                                                |                                                        Coverage (codecov.io)                                                       |
|:------:|:-------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------------------------------:|
| master | [![Build Status](https://travis-ci.org/sekaiser/spring-boot-blueprint.svg?branch=master)](https://travis-ci.org/sekaiser/spring-boot-blueprint) | [![Coverage Status](https://coveralls.io/repos/github/Netflix/genie/badge.svg?branch=master)](https://coveralls.io/github/Netflix/genie?branch=master) | [![codecov](https://codecov.io/gh/Netflix/genie/branch/master/graph/badge.svg)](https://codecov.io/gh/Netflix/genie/branch/master) |



## DevGuid

## How to create a new flyway migration
Every change in the database schema MUST be documented with a flyway migration script.
Create a unique migration script with gradle in the project root:
```
    gradle addNewMigration -PmigrationName=add_foo_bar
```