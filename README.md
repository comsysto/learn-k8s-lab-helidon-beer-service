
# Helidon Example: quickstart-se

This example implements a simple REST service.

## Prerequisites

1. Maven 3.5 or newer
2. Java SE 8 or newer
3. Docker 17 or newer to build and run docker images
4. Kubernetes cluster
5. Kubectl 1.7.4 or newer to deploy to Kubernetes

Verify prerequisites
```
java -version
mvn --version
docker --version
kubectl version --short
```

## Build

```
mvn package
```

## Start the application

```
java -jar target/quickstart-se.jar
```

## Exercise the application

```
curl -X GET http://localhost:8080/greet
{"message":"Hello World!"}

curl -X GET http://localhost:8080/greet/Joe
{"message":"Hello Joe!"}

curl -X PUT -H "Content-Type: application/json" -d '{"greeting" : "Hola"}' http://localhost:8080/greet/greeting

curl -X GET http://localhost:8080/greet/Jose
{"message":"Hola Jose!"}

curl -X GET http://localhost:8080/beers
[{"id":"0001-201902","name":"Augustiner","type":"Heles","strength":5}]
```

## Try health and metrics

```
curl -s -X GET http://localhost:8080/health
{"outcome":"UP",...
. . .

# Prometheus Format
curl -s -X GET http://localhost:8080/metrics
# TYPE base:gc_g1_young_generation_count gauge
. . .

# JSON Format
curl -H 'Accept: application/json' -X GET http://localhost:8080/metrics
{"base":...
. . .

```

## Build the Docker Image

```
docker build -t quickstart-se target
```

## Start the application with Docker

```
docker run --rm -p 8080:8080 quickstart-se:latest
```

Exercise the application as described above

## Deploy the application to Kubernetes

```
kubectl cluster-info                # Verify which cluster
kubectl get pods                    # Verify connectivity to cluster
kubectl create -f target/app.yaml   # Deply application
kubectl get service quickstart-se   # Get service info
```

## Exercise the Application on Kubernetes
Start the Kubernetes proxy server so you can connect to your service via localhost:
```
kubectl proxy
```
Next get the service’s info.
```
kubectl get service quickstart-se
```
Note the PORTs. You can now exercise the application as you did before but use the second port number (the NodePort) instead of 8080. For example:
```
curl -X GET http://localhost:31431/greet
curl -X GET http://localhost:31431/beers
```
After you’re done, cleanup.

## Remove the application from Kubernetes
```
kubectl delete -f target/app.yaml
```