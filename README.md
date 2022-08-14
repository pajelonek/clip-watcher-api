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
mvn spring-boot:run -D"spring-boot.run.profiles"="LOCAL" -D"spring-boot.run.jvmArguments"="-DTWITCH_CLIENT_ID=[YOUR_TWITCH_CLIENT_ID],-DTWITCH_CLIENT_SECRET=[YOUR_TWITCH_CLIENT_SECRET]"      
```

To run application with enabled security:
```shell
mvn spring-boot:run -D"spring-boot.run.profiles"="DEV" -D"spring-boot.run.jvmArguments"="-DTWITCH_CLIENT_ID=[YOUR_TWITCH_CLIENT_ID],-DTWITCH_CLIENT_SECRET=[YOUR_TWITCH_CLIENT_SECRET]"      
```
and fill properties with values in src/main/resources/application-DEV.yml
```yml
authentication:
  user: ${AUTH_USERNAME}
  password: ${AUTH_PASSWORD}
```
NOTE! Remember to put your password in format '{noop}[PASSWORD]' where [PASSWORD] is your own password for basic authentication. 

## How to get client id and client secret for TWITCH API
- go to https://dev.twitch.tv
- sign in or register
- click 'Your Console'
![Your console Twitch](.github/imgs/console.png?raw=true "Your Console Twitch")
- click 'Register your application'
  ![Register App Twitch](.github/imgs/register.png?raw=true "Register App Twitch")
- Fill in the form, you can provide any redirect URL as for now application doesn't allow login
  ![Fill in the form Twitch](.github/imgs/application_form.png?raw=true "Fill in the form Twitch")
- Click 'Create', you will be redirected to https://dev.twitch.tv/console/apps/
- Click 'Manage' next to your newly registered application
  ![Manage created App Twitch](.github/imgs/manage.png?raw=true "Manage created App Twitch")
- Copy your Client ID from the form and save it
  ![Client ID Twitch](.github/imgs/client_id.png?raw=true "Client ID Twitch")
- Click 'New Secret' Button, it will instantly show your secret, save it next to your client id
  ![Client Secret Twitch](.github/imgs/client_secret.png?raw=true "Client Secret Twitch")


### Now you can use those strings to run application. Instruction how to do it is in 'Running the application locally' section.
## Swagger

Swagger v3 is avaiable under URL: 

```text
https://clip-watcher-api-oaknr.ondigitalocean.app/swagger-ui/index.html#/
```