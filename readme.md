## Opening Hours

This is a dockerized application that converts opening hours to human-readable version.
Application is build on the clean code architecture.

## Installation

```sh
run init.sh
```
Please make sure that Maven is installed on you computer. Application will start in port 8080.

[Postman Collection](OpenHours.postman_collection.json) </br>
[Swagger UI](http://localhost:8080/swagger-ui.html#/open-hours-controller)

## Improvement Areas
Input json is good for frontend, but it is hard to process it in backend side since variable names cannot be accessed without using reflection.
Instead of giving day name to variable name, we can put name property which holds name of the day.
Input json would be:


```yaml
  {
    "days": {
        [
            {
                "name": "SUNDAY",
                "hours": [
                    {
                        "value": 123,
                        "type": "open"
                    },
                    {
                        "value": 123,
                        "type": "closed"
                    }
                ]
            }
        ]
    }
}