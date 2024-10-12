# Vergil

## Description
It is a monolithic web application that allows you to manage and log collectible sets by profile.

## Requirements
- [VSCode](https://code.visualstudio.com)
- [JDK 21](https://jdk.java.net/21/)
- [Maven](https://maven.apache.org/download.cgi)
- [NodeJs](https://nodejs.org/en)
- [Lombok VSCode](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-lombok)
- [Vite VSCode](https://marketplace.visualstudio.com/items?itemName=antfu.vite)

## Build development environment
```
podman run --name mongodb -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=user -e MONGO_INITDB_ROOT_PASSWORD=pass -d
```
```
npm install
```
```
mvn dependency:resolve
```
```
mvn clean
```

## Build test/prod
```
chmod +x ./kube/script.sh
```
```
./kube/script.sh
```

## Tests

## Web pages
### Web
Ejemplo [localhost:8080](http://localhost:8080/#/)
```
http://{host}:{port}/#/
```
### Apis
```
http://{host}:{port}/api/v1/<path>

```
### Swagger
Ejemplo [localhost:8080](http://localhost:8080/webjars/swagger-ui/index.html)
```
http://{host}:{port}/webjars/swagger-ui/index.html
```
