docker stop mysql-server
docker stop movie-api
docker rm mysql-server
docker rm movie-api
docker build -t movie-spring-api .
pause


