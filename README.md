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
client_1   | 16:20:52,580 INFO  [com.foo.interceptor.MyContextDataInjectingInterceptor] (default task-1) RequestId 43106e41-16a6-469a-a4f2-7e4a53df2f84
client_1   | 16:20:52,580 INFO  [com.foo.interceptor.MyClientInterceptor] (default task-1) handleInvocation - before sendRequest, 2
client_1   | 16:20:52,581 INFO  [com.foo.interceptor.MyClientInterceptor] (default task-1) handleInvocation - after sendRequest
server_1   | 16:20:52,590 INFO  [com.foo.interceptor.MyContextDataRetrievingInterceptor] (default task-1) RequestId 43106e41-16a6-469a-a4f2-7e4a53df2f84
server_1   | 16:20:52,591 INFO  [com.foo.interceptor.MyServerInterceptor] (default task-1) MyServiceBean#processText - start
server_1   | 16:20:52,592 INFO  [com.foo.MyServiceBean] (default task-1) Doing textToUpper for text hello world
server_1   | 16:20:52,594 INFO  [com.foo.interceptor.MyServerInterceptor] (default task-1) MyServiceBean#processText - end
client_1   | 16:20:52,600 INFO  [com.foo.interceptor.MyClientInterceptor] (default task-1) handleInvocationResult - before getResult
client_1   | 16:20:52,600 INFO  [com.foo.interceptor.MyClientInterceptor] (default task-1) handleInvocationResult - after getResult
client_1   | 16:20:52,601 INFO  [com.foo.MyServlet] (default task-1) Processing request - end
```

And web page shows
```
EJB call performed. Result:
HELLO WORLD
```

## Stop running Wildfly instances
To stop running Wildfly instances hit `Ctrl + C`.

