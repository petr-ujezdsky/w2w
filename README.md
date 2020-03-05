# Wildfly to Wildfly EJB calls

This project demonstrates EJB calls between 2 running Wildfly instances.

## Prerequisites
* Docker
* Any browser or `wget`
* Internet connection for docker to download images

## Build
To build the project run 
```
$ ./build.sh
```

## Run
To run the project run
```
$ ./run.sh
```
It will start the `docker-compose` and show their console outputs.

## Perform EJB call
To perform example EJB call navigate to http://localhost:8080/test.
The console output is then following:
```
client_1   | 10:36:49,015 INFO  [com.foo.servlet.MyServlet] (default task-1) Processing request - start
client_1   | 10:36:49,129 INFO  [com.foo.interceptor.MyContextDataInjectingInterceptor] (default task-1) RequestId a44643d9-ea3b-42af-8237-ed445f05e0d2
client_1   | 10:36:49,130 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocation - before sendRequest
client_1   | 10:36:49,295 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocation - after sendRequest
server_1   | 10:36:50,329 INFO  [com.foo.interceptor.MyContextDataRetrievingInterceptor] (default task-2) RequestId a44643d9-ea3b-42af-8237-ed445f05e0d2
server_1   | 10:36:50,330 INFO  [com.foo.interceptor.MyServerInterceptor] (default task-2) MyServiceBean#processText - start
server_1   | 10:36:50,331 INFO  [com.foo.MyServiceBean] (default task-2) Doing textToUpper for text hello world
server_1   | 10:36:50,331 INFO  [com.foo.interceptor.MyServerInterceptor] (default task-2) MyServiceBean#processText - end
client_1   | 10:36:50,371 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocationResult - before getResult
client_1   | 10:36:50,483 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocationResult - after getResult
client_1   | 10:36:50,519 INFO  [com.foo.servlet.MyServlet] (default task-1) Processing request - end
```

And web page shows
```
EJB call performed. Result:
HELLO WORLD
```

## Stop running Wildfly instances
To stop running Wildfly instances hit `Ctrl + C`.

