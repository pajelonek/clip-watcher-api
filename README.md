# CLIP WATCHER API

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=pajelonek_clip-watcher-api&metric=alert_status)](https://sonarcloud.io/dashboard?id=pajelonek_clip-watcher-api)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=pajelonek_clip-watcher-api&metric=bugs)](https://sonarcloud.io/dashboard?id=pajelonek_clip-watcher-api)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=pajelonek_clip-watcher-api&metric=code_smells)](https://sonarcloud.io/dashboard?id=pajelonek_clip-watcher-api)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=pajelonek_clip-watcher-api&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=pajelonek_clip-watcher-api)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=pajelonek_clip-watcher-api&metric=coverage)](https://sonarcloud.io/summary/new_code?id=pajelonek_clip-watcher-api)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=pajelonek_clip-watcher-api&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=pajelonek_clip-watcher-api)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=pajelonek_clip-watcher-api&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=pajelonek_clip-watcher-api)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=pajelonek_clip-watcher-api&metric=security_rating)](https://sonarcloud.io/dashboard?id=pajelonek_clip-watcher-api)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=pajelonek_clip-watcher-api&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=pajelonek_clip-watcher-api)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=pajelonek_clip-watcher-api&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=pajelonek_clip-watcher-api)

## Requirements

For building and running the application you need:

- [JDK 17](https://www.oracle.com/java/technologies/downloads/#java17)
- [Maven 3](https://maven.apache.org)

## About

This is backend part of the Master’s Degree Project Clip Watcher.
It handles communication to TWITCH API from frontend hosted on 

```text
https://pajelonek.github.io/clip-watcher-ui/
```
## Running the application locally

There are several ways to run this application on your local machine.
One is to run with Maven command.

To run application with disabled security:
```shell
mvn spring-boot:run -D"spring-boot.run.profiles"="LOCAL" -D"spring-boot.run.jvmArguments"="-DTWITCH_CLIENT_ID=cwnkzd3n6zm6jgenoe3d9w9a8mruv2,-DTWITCH_CLIENT_SECRET=jcde6htcthfx1bkw6zo9z2bqnjxpx5,-DTWITCH_CLIENT_BEARER=opy9v7k4wtwduk5kuhbs0hfnykvilt"      
```

To run application with enabled security:
```shell
mvn spring-boot:run -D"spring-boot.run.profiles"="DEV" -D"spring-boot.run.jvmArguments"="-DTWITCH_CLIENT_ID=cwnkzd3n6zm6jgenoe3d9w9a8mruv2,-DTWITCH_CLIENT_SECRET=jcde6htcthfx1bkw6zo9z2bqnjxpx5,-DTWITCH_CLIENT_BEARER=opy9v7k4wtwduk5kuhbs0hfnykvilt"      
```
and fill properties with values in application-DEV.yml
```yml
authentication:
  user: ${AUTH_USERNAME}
  password: ${AUTH_PASSWORD}
```
NOTE! Remember to put your password in format '{noop}[PASSWORD]' where [PASSWORD] is your own password for basic authentication. 

## Swagger

Swagger v3 is avaiable under URL: 

```text
https://clip-watcher-api-oaknr.ondigitalocean.app/swagger-ui/index.html#/
```