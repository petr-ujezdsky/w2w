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
client_1   | 10:43:34,010 INFO  [com.foo.servlet.MyServlet] (default task-1) Processing request - start
client_1   | 10:43:34,168 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocation - before sendRequest
client_1   | 10:43:34,171 INFO  [com.foo.interceptor.MyContextDataInjectingInterceptor] (default task-1) Generated requestId d728cd10-ba2e-4d87-a211-db370b217bd1
client_1   | 10:43:34,402 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocation - after sendRequest
server_1   | 10:43:35,802 INFO  [com.foo.interceptor.AroundLoggingServerInterceptor] (default task-2) MyServiceBean#processText - start
server_1   | 10:43:35,803 INFO  [com.foo.interceptor.MyContextDataRetrievingServerInterceptor] (default task-2) Obtained requestId d728cd10-ba2e-4d87-a211-db370b217bd1
server_1   | 10:43:35,804 INFO  [com.foo.MyServiceBean] (default task-2) Doing textToUpper for text hello world
server_1   | 10:43:35,805 INFO  [com.foo.interceptor.AroundLoggingServerInterceptor] (default task-2) MyServiceBean#processText - end
client_1   | 10:43:35,856 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocationResult - before getResult
client_1   | 10:43:36,008 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocationResult - after getResult
client_1   | 10:43:36,043 INFO  [com.foo.servlet.MyServlet] (default task-1) Processing request - end
```

And web page shows
```
EJB call performed. Result:
HELLO WORLD
```

## Stop running Wildfly instances
To stop running Wildfly instances hit `Ctrl + C`.

