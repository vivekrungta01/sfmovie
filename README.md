# Project - SF Movies

Create a service that shows on a map where movies have been filmed in San Francisco. The user should be able to filter the view using autocompletion search.



>- The project is hosted on [heroku][1].
>- The source code is located on [github][2].


# Technology stack

>- Language: java (proficient experience)
>- Backend framework: Spring (proficient experience)
>- Database: mongo (proficient experience)
>- Cache: redis  (proficient experience)
>- Frontend framework: (Beginner experince)

# Reasoning behind Technical choices

**Java** and **Spring Framework** -  I have good Experience in Java and Spring also it seemed to nicely fit the scale of the project while being very flexible and easy to set up. So I used Java Stack and spring framework.

**Mongo Database**  - I decided to use MongoDB, because it is a production-level, deployment friendly, easy-to-use noSQL storage system and schemaLess architecture.

**Redis Cache**  - To improve performance, I used Redis to cache search queries. I precomputed the movies + directors names +year+location+lat+lng into redis when the application first hit comes. I set ttl for cache in redis - 10 min .. after that it refresh..

**Frontend framework** - I used different plugin api for frontend - google api for fetching google map,jquery autocomplete api for autocomplete search because this is the most well-known map API that contains many features .

# Backend Architecture

- Implemented the back-end as a REST service for getting all movie details to frontend
   
- A [Importer script][4] is used to retrieve the DataSF api json file from local storage and loop through all results (calling Google Maps Geocode API to retrieve latitude and longitude for all unique locations) to properly fill movie_location collection in mongo.

- Used spring mongo data to query in mongo ..

- to improve performance, I used Redis to cache search queries. I precomputed the movies + directors names +year+location+lat+lng into redis when the application first hit comes. 

- In addition, I used Heroku to deploy the app. I normally use Heroku to deploy basic web apps mainly because it is simple/fast to deploy, and because the app restarts every so often (easy mechanism to handle crashes).

- For this project I used Mongo  from [mLab][5] and Redis from [redisLab][6]


# Frontend Architecture

- The autocompletion search is only applied on the titles of the movies .. For This I used Autocomplete search plugin.

- Used the Google Maps API 3 to show the map and add the markers for every movie location.

- After getting result from backend displayed all movie to Google Map and also added AutoComplete Search based on Title.

- Slider added to filer out based on Movie release year. 




# Link to your resume or public profile
[Linked - Vivek Rungta][3]

[1]: https://quiet-scrubland-61429.herokuapp.com/sfmovie/
[2]: https://github.com/vivekrungta01/sfmovie
[3]: https://www.linkedin.com/in/vivek-rungta-7a84a120/
[4]: https://github.com/vivekrungta01/sfmovie/blob/master/sfmovie-dao/src/main/java/org/sfmovie/dao/Importer.java
[5]: https://mlab.com/databases/sfmovie
[6]: https://app.redislabs.com/

