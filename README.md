gradle build
docker build -t query-app ./query
docker run -p 8080:8080 query-app