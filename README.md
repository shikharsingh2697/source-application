
# Rudderstack Source Application

This is a Java Spring Boot application that uses MySQL and Redis. It's a simple backend application that helps create source form templates and source for Rudderstack Data Pipeline.


## Prerequisites
Before you can run this application, you'll need to have the following software installed on your computer:

    1. Java
    2. MySQL
    3. Redis
## Getting Started
To get started, follow these steps:
```
1. Clone this repository to your local machine.
2. Open the project in your Java IDE.
3. Set up your MySQL database by running the 'data.sql' script located in the db/ directory.
4. Configure the application by editing the application.yaml file located in the src/main/resources directory. You'll need to set the values for datasource, redis. Start the mySQL and redis servers.
5. Start the application by running the SourceApplication.java file located in the src/main/java/com.rudderstack.sourceapplication directory.
```

## API Reference

- Endpoint: /v1/source-form-templates
- Method: POST
- Request Headers:
```
    userId: 1,
    Content-Type: application/json
```
- Request Body:
```
{
    "type": "source1",
    "fields": {
        "apiKey": {
            "type": "INPUT",
            "label": "API key",
            "regexErrorMessage": "Invalid api key",
            "placeholder": "e.g: 1234asdf",
            "regex": "^[a-zA-Z0-9_]*$",
            "required": true
        },
        "useHTTP": {
            "type": "CHECKBOX",
            "required": false,
            "label": "Enable HTTP"
        },
        "category": {
            "type": "SINGLE_SELECT",
            "label": "Select category",
            "required": true,
            "options": [
                {
                    "label": "Android",
                    "value": "android"
                },
                {
                    "label": "IOS",
                    "value": "ios"
                }
            ]
        }
    }
}
```
- Response
```
HTTP/1.1 201 Created
Content-Type: application/json

{
    "type": "source1",
    "fields": {
        "apiKey": {
            "type": "INPUT",
            "label": "API key",
            "regexErrorMessage": "Invalid api key",
            "placeholder": "e.g: 1234asdf",
            "regex": "^[a-zA-Z0-9_]*$",
            "required": true
        },
        "useHTTP": {
            "type": "CHECKBOX",
            "required": false,
            "label": "Enable HTTP"
        },
        "category": {
            "type": "SINGLE_SELECT",
            "label": "Select category",
            "required": true,
            "options": [
                {
                    "label": "Android",
                    "value": "android"
                },
                {
                    "label": "IOS",
                    "value": "ios"
                }
            ]
        }
    }
}
```
----
- Endpoint: /v1/sources
- Method: POST
- Request Headers:
```
    userId: 1,
    Content-Type: application/json
```
- Request Body:
```
{
    "sourceType": "source1",
    "values": {
        "apiKey": "123qewqre",
        "useHTTP": false,
        "category": "android"
    }
}
```
- Response
```
HTTP/1.1 201 Created
Content-Type: application/json

{
    "sourceType": "source6",
    "values": {
        "apiKey": "123qewqre",
        "useHTTP": false,
        "category": "android"
    }
}
```
----
- Endpoint: /v1/source-form-templates
- Method: GET
- Request Params:
```
    type: source1
```
- Response
```
HTTP/1.1 200 OK

{
    "type": "source1",
    "fields": {
        "apiKey": {
            "type": "INPUT",
            "label": "API key",
            "regex": "^[a-zA-Z0-9_]*$",
            "required": true,
            "placeholder": "e.g: 1234asdf",
            "regexErrorMessage": "Invalid api key"
        },
        "useHTTP": {
            "type": "CHECKBOX",
            "label": "Enable HTTP",
            "required": false
        },
        "category": {
            "type": "SINGLE_SELECT",
            "label": "Select category",
            "options": [
                {
                    "label": "Android",
                    "value": "android"
                },
                {
                    "label": "IOS",
                    "value": "ios"
                }
            ],
            "required": true
        }
    }
}
```
----
- Endpoint: /source-form-templates/source-types
- Method: GET
- Response
```
HTTP/1.1 200 OK

[
    {
        "value": "source1",
        "label": "source1"
    },
    {
        "value": "source2",
        "label": "source2"
    },
    {
        "value": "source3",
        "label": "source3"
    },
    {
        "value": "source0",
        "label": "source0"
    },
    {
        "value": "source5",
        "label": "source5"
    },
    {
        "value": "source6",
        "label": "source6"
    }
]
```