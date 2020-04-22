docker run -p 8080:8080 --name movie-api  -d --link mysql-server:mysql  movie-spring-api
pause