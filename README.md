[![Build Status](https://app.travis-ci.com/vadimstr102/job4j_url_shortcut.svg?branch=main)](https://app.travis-ci.com/vadimstr102/job4j_url_shortcut)

# job4j_url_shortcut

* [Description](#description)
* [Technologies](#technologies)
* [Using REST API](#using-rest-api)
    * [Registration](#registration)
    * [Authorization](#authorization)
    * [Creating short link](#creating-short-link)
    * [Redirecting to the original url](#redirecting-to-the-original-url)
    * [Statistics](#statistics)
* [Author](#author)

## Description

The project is a service for creating short links, redirecting to the source URL and counting the number of clicks on each URL.

## Technologies

* Java 11
* Spring Boot 2
* Spring Data JPA
* Spring Security
* JWT authorization
* PostgreSQL
* Google Guava

## Using REST API

### Registration

#### Request

````
POST /registration
````

with body:

````
{"site": "example.com"}
````

#### Response

````
{"registration": "true/false", "login": "UNIQUE_CODE", "password": "UNIQUE_CODE"}
````

The registration flag indicates that registration has been completed or not, that is, the site is already in the system.

### Authorization

#### Request

````
POST /login
````

with body

````
{"login": "your_login", "password": "your_password"}
````

#### Response

in headers

````
Authorization: Bearer YOUR_TOKEN
````

### Creating short link

#### Request

````
POST /convert
````

with body

````
{"url": "https://example.com/users/profile/tasks/task/task-view/532"}
````

and header

````
Authorization: Bearer YOUR_TOKEN
````

#### Response

````
{"code": "UNIQUE_CODE"}
````

### Redirecting to the original url

#### Request

````
GET /redirect/UNIQUE_CODE
````

### Statistics

#### Request

````
GET /statistics
````

with header

````
Authorization: Bearer YOUR_TOKEN
````

#### Response

````
{
    {"url": "URL_1", "total": 5},
    {"url": "URL_2", "total": 105},
    ...
    {"url": "URL_N", "total": n}
}
````

## Author

Vadim Timofeev

Java developer

vadimstr102@gmail.com
