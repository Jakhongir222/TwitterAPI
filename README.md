# Twitter-API App

This is a nifty little app that provides convenient functionality for interacting with the Twitter API. With this app, users can follow, unfollow, and retrieve a list of followers and tweets for a specific user. You can even create, update, and delete tweets!

But wait, there's more! I'm especially passionate about authentication, and I've implemented a login feature for users. Unfortunately, I wasn't able to fully finish it due to my Google Cloud credit running out on the free tier. Fear not, though! I plan on replacing it with Okta very soon 😉

## Deployment
The app has been deployed on Railway, making it easily accessible for others to use and test. You can check out the app by following the link below. Don't forget to add the endpoints specified below. Go ahead, give it a spin and let me know what you think! 

Link: https://twitterapi-production.up.railway.app

## Installation

To run this app on your local machine, you will need to install the following dependencies:

* Java
* Maven

To get started, follow these steps:

1. Clone this repository to your local machine.
2. Navigate to the project directory.
3. Open the terminal and run `mvn clean install` to build the project.
3. Run the app by running `mvn spring-boot:run`

## Usage

The app offers RESTful endpoints to interact with the Twitter API. Upon running the app, an automatic default user, Jakhongir Burkhonov, is created - that's me! This makes it easier to test the app, but don't forget to create your own users and follow me too :). I have a method that generates auotamatic usernames just like Twitter. Check out some examples below on how to use these endpoints:



### Register a user, POST REQUEST
 
 http://localhost:8080/auth/register
 
 * JSON input
``` 
 {
"firstName" : "Elon",
"lastName" : "Musk",
"email" : "elonmusk@gmail.com",
}
``` 
 * Output result
``` 
 {
    "userId": 2,
    "firstName": "Elon",
    "lastName": "Musk",
    "email": "elonmusk@gmail.com",
    "username": "ElonMusk571056208",
    "phone": null,
    "tweets": [],
    "authorities": [
        {
            "roleId": 1,
            "authority": "USER"
        }
    ],
    "enabled": false
}
 ```
 ### Update a phone number, PUT REQUEST
 http://localhost:8080/auth/update/phone
  * JSON input
 ```
 {
    "username": "ElonMusk571056208",
    "phone": "+1415-123-4567"
}
 ```
 * Output result
 ```
 {
    "userId": 2,
    "firstName": "Elon",
    "lastName": "Musk",
    "email": "elonmusk@gmail.com",
    "username": "ElonMusk571056208",
    "phone": "+1415-123-4567",
    "tweets": [],
    "authorities": [
        {
            "roleId": 1,
            "authority": "USER"
        }
    ],
    "enabled": false
}
 ```
 ### Generate a verification code, POST REQUEST
 http://localhost:8080/auth/email
 ```
 {
    "username" : "ElonMusk571056208"
}
 ```
 ### Follow a user, POST REQUEST
  http://localhost:8080/users/{follower}/follow/{following}

 * Sample endpoint
 http://localhost:8080/users/ElonMusk571056208/follow/javageek
 * Result, Elon Must is following javageek, that is my username!
 ### Get user's following, GET REQUEST
 http://localhost:8080/users/{username}/following
 
  * Sample endpoint
 ```
 http://localhost:8080/users/ElonMusk571056208/following
 ```
 * Output result
 ```
 [
    {
        "userId": 1,
        "firstName": "Jakhongir",
        "lastName": "Burkhonov",
        "email": "burkhanovjakhongir@gmail.com",
        "username": "javageek",
        "phone": "+467077777777",
        "tweets": [],
        "authorities": [
            {
                "roleId": 1,
                "authority": "USER"
            }
        ],
        "enabled": true
    }
]
 ```
 ### Unfollow a user, POST REQUEST
 http://localhost:8080/users/{follower}/unfollow/{following}
 
 * Sample endpoint
 http://localhost:8080/users/ElonMusk571056208/unfollow/javageek
 
 ### Post a Tweet, POST REQUEST
 http://localhost:8080/tweets
 * JSON input
 ```
 {
"content": "Just tested my latest rocket prototype. It's so fast that even my Tesla feels slow now. Sorry, Elon Musk! 😜🚀🚗",
"userId": 2
}
 ```
 * Output result
 ```
 {
    "tweetId": 1,
    "content": "Just tested my latest rocket prototype. It's so fast that even my Tesla feels slow now. Sorry, Elon Musk! 😜🚀🚗",
    "date": "2023-03-24T08:58:43.023+00:00",
    "user": {
        "userId": 2,
        "firstName": null,
        "lastName": null,
        "email": null,
        "username": null,
        "phone": null,
        "tweets": [],
        "authorities": null,
        "enabled": false
    },
    "replyTo": null
}
 ```
 ### Delete a tweet, DELETE REQUEST
 http://localhost:8080/tweets/{tweetId}
 * Sample endpoint
 ```
 http://localhost:8080/tweets/15
 ```
### Get user's tweets, GET REQUEST
  http://localhost:8080/tweets/{userId}
 * Sample endpoint
 ```
 http://localhost:8080/tweets/2
 ```
 
## Login & Authentication
 I have created a login feature for my app. To ensure that users have a smooth experience, I have used Spring Security to implement several methods that check if the email provided is already in use. I believe that with one email, you should only have one account, and my code is set up to enforce this.

  
To take things further, I have also created several custom exception classes that check if the username or email exists in my database. This ensures that the data remains clean, and users can register without any issues.

  
Finally, I have included a method to update phone numbers and generate verification codes. The security of the user information is of the utmost importance to me, and I take great pride in creating code that is both secure and efficient.
  

## Testing

All of the endpoints have been tested using Postman. In addition, Automated tests have been provided for this app. To run the tests, run `mvn test` in the terminal

## Thank You

Thank you for taking the time to read through my readme! I appreciate your interest in my project and hope you found it informative and useful.

 
If you have any feedback, suggestions, or just want to say hi, feel free to reach out to me. I'm always open to new ideas and love connecting with like-minded people.

 
And if you happen to use my app in your project, please let me know. It would be awesome to see how my work is being used out there in the world.

 
Once again, thank you for your interest in my project. Keep on coding! ;)
