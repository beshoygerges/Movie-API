docker stop mysql-server
docker stop movie-api
docker rm mysql-server
docker rm movie-api
docker build -t movie-spring-api .
docker run --name=mysql-server -e MYSQL_ROOT_PASSWORD=P@ssw0rd -e MYSQL_DATABASE=movieapi -d mysql:8
timeout 20 >nul
docker run -p 8080:8080 --name movie-api  -d --link mysql-server:mysql  movie-spring-api
pause


