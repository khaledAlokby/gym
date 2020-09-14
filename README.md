If you read this, then you already unzipped the jar file called gym-1.0.0.

to run the jar go to the folder where the jar is located, then run:
$ java -jar gym-1.0.0.jar

after spring up is up, go to http://localhost:8080/swagger-ui.html

In the swagger ui you can see and test all the endpoints,
if you try one of the POST requests, the body will be something like this:
{
  "id": 202854232232,
  "fullName": "khaled",
  "name": "Zumba",
  "email": "alaeddingenie@gmail.com"
} 








**Things to fix/improve**
- handle concurrency
- handle exceptions
- write tests
-refactoring and comments
-when sending a notification: i used hardcoded instead of @Value from properties.


* the body in the post request included "name" variable, whitch is the className variable
, ideally, i suppose to send it as a @PathParam (/{name}), but,
swagger doesn't work with both pathparam and body, so, for your comfort i change it, so you don't need to run curl or go to postman (under hte hood swagger run curl command) 