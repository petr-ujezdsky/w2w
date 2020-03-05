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

## EJB call demos
Project contains several endpoints to demonstrate EJB calls.

### Perform simple EJB call
Navigate to http://localhost:8080/testSimple

The console output is then following:
```
client_1   | 10:52:42,534 INFO  [com.foo.servlet.TestSimpleServlet] (default task-1) Processing request - start
client_1   | 10:52:42,649 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocation - before sendRequest
client_1   | 10:52:42,653 INFO  [com.foo.interceptor.MyContextDataInjectingInterceptor] (default task-1) Generated requestId bb094a14-f5d5-4155-b5f1-c94f9267129b
client_1   | 10:52:42,812 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocation - after sendRequest
server_1   | 10:52:43,860 INFO  [com.foo.interceptor.AroundLoggingServerInterceptor] (default task-2) MyServiceBean#processText - start
server_1   | 10:52:43,861 INFO  [com.foo.interceptor.MyContextDataRetrievingServerInterceptor] (default task-2) Obtained requestId bb094a14-f5d5-4155-b5f1-c94f9267129b
server_1   | 10:52:43,862 INFO  [com.foo.MyServiceBean] (default task-2) Doing textToUpper for text hello world
server_1   | 10:52:43,862 INFO  [com.foo.interceptor.AroundLoggingServerInterceptor] (default task-2) MyServiceBean#processText - end
client_1   | 10:52:43,904 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocationResult - before getResult
client_1   | 10:52:44,041 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocationResult - after getResult
client_1   | 10:52:44,080 INFO  [com.foo.servlet.TestSimpleServlet] (default task-1) Processing request - end
```

And web page shows
```
EJB call performed. Result:
HELLO WORLD
```

### Perform EJB call that throws exception
Navigate to http://localhost:8080/testException

The console output is then following:
```
client_1   | 11:03:23,194 INFO  [com.foo.servlet.TestExceptionServlet] (default task-1) Processing request - start
client_1   | 11:03:23,341 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocation - before sendRequest
client_1   | 11:03:23,344 INFO  [com.foo.interceptor.MyContextDataInjectingInterceptor] (default task-1) Generated requestId c569db6d-69e6-4cc7-ae06-8de0e3de7606
client_1   | 11:03:23,699 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocation - after sendRequest
server_1   | 11:03:25,041 INFO  [com.foo.interceptor.AroundLoggingServerInterceptor] (default task-2) MyServiceBean#alwaysFail - start
server_1   | 11:03:25,042 INFO  [com.foo.interceptor.MyContextDataRetrievingServerInterceptor] (default task-2) Obtained requestId c569db6d-69e6-4cc7-ae06-8de0e3de7606
server_1   | 11:03:25,043 INFO  [com.foo.interceptor.AroundLoggingServerInterceptor] (default task-2) MyServiceBean#alwaysFail - end
client_1   | 11:03:25,099 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocationResult - before getResult
client_1   | 11:03:25,248 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocationResult - after getResult (exception - Intended exception for test)
client_1   | 11:03:25,251 INFO  [com.foo.servlet.TestExceptionServlet] (default task-1) Ejb call threw exception: java.lang.Exception: Intended exception for test
client_1   |    at com.foo.MyServiceBean.alwaysFail(MyServiceBean.java:31)
client_1   |    at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
...
client_1   | 11:03:25,304 INFO  [com.foo.servlet.TestExceptionServlet] (default task-1) Processing request - end
```

And web page shows
```
EJB call performed.
```

## Stop running Wildfly instances
To stop running Wildfly instances hit `Ctrl + C`.

