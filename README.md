

This is the code challenge completed. You can execute it using:
 - IDE like IntelliJ or Eclipse.
 - Using Docker executing in terminal: 'docker-compose up'.

To use it first you need to create Owners users and Guests users to use it.
 - /users/create_guest_user Creates the Guest user.
 - /users/create_owner_user Creates the Owner user.

These 2 endpoints and /login are the only ones that you can execute them without /login endpoint into the app.
For the rest of the endpoints you need to execute firts /login endpoint with username(email) and password, then get the 
access token and refresh token from Header, in Authorization value. They are together separated with a '/'. This is thought to use in a
web or mobile application where you need to use the first token until it expires. When this happens you need to use the 
second token (with more time to expire) to call /refresh_token endpoint to get both token refreshed.
This token is needed to be used as a Bearer Token (in Postman or the app that you want to use).

To simplify the code I haven't made some features:
 - The unit tests are missing to finish on time the challenge.
 - Some json response show more information that they should.
 - Some error messages are not added or showed correctly.


