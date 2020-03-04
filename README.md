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
client_1   | 16:00:39,354 INFO  [com.foo.MyServlet] (default task-1) Processing request - start
client_1   | 16:00:39,456 INFO  [com.foo.MyClientInterceptor] (default task-1) handleInvocation - before sendRequest, 2
client_1   | 16:00:39,626 INFO  [com.foo.MyClientInterceptor] (default task-1) handleInvocation - after sendRequest
server_1   | 16:00:40,771 INFO  [com.foo.MyServerInterceptor] (default task-2) MyServiceBean#processText - start
server_1   | 16:00:40,772 INFO  [com.foo.MyServiceBean] (default task-2) Doing textToUpper for text hello world
server_1   | 16:00:40,772 INFO  [com.foo.MyServerInterceptor] (default task-2) MyServiceBean#processText - end
client_1   | 16:00:40,813 INFO  [com.foo.MyClientInterceptor] (default task-1) handleInvocationResult - before getResult
client_1   | 16:00:40,838 INFO  [com.foo.MyClientInterceptor] (default task-1) handleInvocationResult - after getResult
client_1   | 16:00:40,867 INFO  [com.foo.MyServlet] (default task-1) Processing request - end
```

And web page shows
```
EJB call performed. Result:
HELLO WORLD
```

## Stop running Wildfly instances
To stop running Wildfly instances hit `Ctrl + C`.

