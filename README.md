In this project I tried to implement Phone Contacts using Spring Boot, Spring Data + Hibernate, Spring Web, Spring Security, a little Junit.

minuses: there are not enough tests, there is no function to delete inactive phones, emails and names, but I have ideas how to do this

pluses: I understood very well how to work with Spring Security, successfully created jwt token and interacted with the RESTful program, all for the first time

To check how it is working you need:
1. Configure your database in application.properties
2. Run application and you can use this commands:
- For creating new user: POST http://localhost:8080/register with body

```json
{
    "login": "yourLogin",
    "password": "yourPassword"
}
```

- For authenticating already existed user: POST http://localhost:8080/auth with body

```json
{
    "login": "yourLogin",
    "password": "yourPassword"
}
```

And you will receive authentication token that need to use in all other endpoints.

To use this token, you need to choose **Bearer token** and insert there token that you received after registration or login.

- For getting all contacts: GET http://localhost:8080/contacts

And you will get all information about contacts, for example:

```json
[
  {
    "emails": [
      "first1@gmail.com",
      "first2@gmail.com"
    ],
    "name": "first",
    "phones": [
      "+380666666660",
      "+380666666661"
    ]
  },
  {
    "emails": [
      "second2@gmail.com",
      "second1@gmail.com"
    ],
    "name": "second",
    "phones": [
      "+380777777771",
      "+380777777770"
    ]
  }
]
```

- For add new contact: POST http://localhost:8080/contacts with body

```json
{
    "name": "contactName",
    "emails": ["contact.first@email.com", "contact.second@email.com"],
    "phones": ["+380666666660", "+380666666661"]
}
```

- For edit existing contact: PUT http://localhost:8080/contacts with body

```json
{
    "name": "contactName",
    "emails": ["new.email@email.com", "contact.second@email.com"],
    "phones": ["+380666666660", "+380666666661", "+380666666662"]
}
```

- For delete contact: DELETE http://localhost:8080/contacts with body

```json
{
    "name": "contactName"
}
```

