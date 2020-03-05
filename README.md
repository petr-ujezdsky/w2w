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
client_1   | 11:35:25,025 INFO  [com.foo.servlet.TestSimpleServlet] (default task-1) Processing request - start
client_1   | 11:35:25,137 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocation - before sendRequest
client_1   | 11:35:25,139 INFO  [com.foo.interceptor.MyContextDataInjectingInterceptor] (default task-1) Generated requestId 8b972aa5-9ef3-49b6-9b4c-0fc93e39ed8b
client_1   | 11:35:25,291 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocation - after sendRequest
server_1   | 11:35:26,417 INFO  [com.foo.interceptor.AroundLoggingServerInterceptor] (default task-2) MyServiceABean#processText - start
server_1   | 11:35:26,418 INFO  [com.foo.interceptor.MyContextDataRetrievingServerInterceptor] (default task-2) Obtained requestId 8b972aa5-9ef3-49b6-9b4c-0fc93e39ed8b
server_1   | 11:35:26,420 INFO  [com.foo.MyServiceABean] (default task-2) Doing "hello world".toUpperCase() inside method processText
server_1   | 11:35:26,421 INFO  [com.foo.interceptor.AroundLoggingServerInterceptor] (default task-2) MyServiceABean#processText - end
client_1   | 11:35:26,460 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocationResult - before getResult
client_1   | 11:35:26,609 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocationResult - after getResult
client_1   | 11:35:26,644 INFO  [com.foo.servlet.TestSimpleServlet] (default task-1) Processing request - end
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
client_1   | 11:40:20,263 INFO  [com.foo.servlet.TestExceptionServlet] (default task-1) Processing request - start
client_1   | 11:40:20,264 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocation - before sendRequest
client_1   | 11:40:20,264 INFO  [com.foo.interceptor.MyContextDataInjectingInterceptor] (default task-1) Generated requestId 90a2cdf2-ed67-443e-9ea1-9b1c6d0fb20f
client_1   | 11:40:20,265 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocation - after sendRequest
server_1   | 11:40:20,270 INFO  [com.foo.interceptor.AroundLoggingServerInterceptor] (default task-1) MyServiceABean#alwaysFail - start
server_1   | 11:40:20,272 INFO  [com.foo.interceptor.MyContextDataRetrievingServerInterceptor] (default task-1) Obtained requestId 90a2cdf2-ed67-443e-9ea1-9b1c6d0fb20f
server_1   | 11:40:20,273 INFO  [com.foo.MyServiceABean] (default task-1) Throwing exception
server_1   | 11:40:20,274 INFO  [com.foo.interceptor.AroundLoggingServerInterceptor] (default task-1) MyServiceABean#alwaysFail - end (exception - Intended exception for test)
client_1   | 11:40:20,284 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocationResult - before getResult
client_1   | 11:40:20,289 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocationResult - after getResult (exception - Intended exception for test)
client_1   | 11:40:20,290 INFO  [com.foo.servlet.TestExceptionServlet] (default task-1) Ejb call threw exception: java.lang.Exception: Intended exception for test
client_1   |    at com.foo.MyServiceABean.alwaysFail(MyServiceABean.java:37)
client_1   |    at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
...
client_1   | 11:40:20,292 INFO  [com.foo.servlet.TestExceptionServlet] (default task-1) Processing request - end
```

And web page shows
```
EJB call performed.
```

### Perform EJB call whose implementation calls some remote method
Navigate to http://localhost:8080/testA2ARemoteCall

Note that the server interceptors are not called again for the remote method invocation.
The console output is then following:
```
client_1   | 11:41:47,619 INFO  [com.foo.servlet.TestA2ARemoteCallServlet] (default task-1) Processing request - start
client_1   | 11:41:47,620 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocation - before sendRequest
client_1   | 11:41:47,620 INFO  [com.foo.interceptor.MyContextDataInjectingInterceptor] (default task-1) Generated requestId 27bcccb1-b575-4855-a082-9b4be2117cce
client_1   | 11:41:47,621 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocation - after sendRequest
server_1   | 11:41:47,626 INFO  [com.foo.interceptor.AroundLoggingServerInterceptor] (default task-1) MyServiceABean#callsARemoteProcessText - start
server_1   | 11:41:47,627 INFO  [com.foo.interceptor.MyContextDataRetrievingServerInterceptor] (default task-1) Obtained requestId 27bcccb1-b575-4855-a082-9b4be2117cce
server_1   | 11:41:47,627 INFO  [com.foo.MyServiceABean] (default task-1) Invoking remote MyServiceABean#processText
server_1   | 11:41:47,627 INFO  [com.foo.MyServiceABean] (default task-1) Doing "hello world".toUpperCase() inside method processText
server_1   | 11:41:47,628 INFO  [com.foo.interceptor.AroundLoggingServerInterceptor] (default task-1) MyServiceABean#callsARemoteProcessText - end
client_1   | 11:41:47,631 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocationResult - before getResult
client_1   | 11:41:47,631 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocationResult - after getResult
client_1   | 11:41:47,632 INFO  [com.foo.servlet.TestA2ARemoteCallServlet] (default task-1) Processing request - end
```

And web page shows
```
EJB call performed. Result:
HELLO WORLD
```

### Perform EJB call whose implementation calls some local method
Navigate to http://localhost:8080/testA2ALocalCall

Note that the server interceptors are not called again for the local method invocation.
The console output is then following:
```
client_1   | 11:42:29,974 INFO  [com.foo.servlet.TestA2ALocalCallServlet] (default task-1) Processing request - start
client_1   | 11:42:29,974 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocation - before sendRequest
client_1   | 11:42:29,974 INFO  [com.foo.interceptor.MyContextDataInjectingInterceptor] (default task-1) Generated requestId cbd30f21-e1dc-4955-80fe-2a75db39e61f
client_1   | 11:42:29,975 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocation - after sendRequest
server_1   | 11:42:29,980 INFO  [com.foo.interceptor.AroundLoggingServerInterceptor] (default task-1) MyServiceABean#callsALocal - start
server_1   | 11:42:29,981 INFO  [com.foo.interceptor.MyContextDataRetrievingServerInterceptor] (default task-1) Obtained requestId cbd30f21-e1dc-4955-80fe-2a75db39e61f
server_1   | 11:42:29,981 INFO  [com.foo.MyServiceABean] (default task-1) Invoking local MyServiceABean#localMethod
server_1   | 11:42:29,981 INFO  [com.foo.MyServiceABean] (default task-1) Doing "hello world".toUpperCase() inside method localMethod
server_1   | 11:42:29,981 INFO  [com.foo.interceptor.AroundLoggingServerInterceptor] (default task-1) MyServiceABean#callsALocal - end
client_1   | 11:42:29,984 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocationResult - before getResult
client_1   | 11:42:29,984 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocationResult - after getResult
client_1   | 11:42:29,985 INFO  [com.foo.servlet.TestA2ALocalCallServlet] (default task-1) Processing request - end
```

And web page shows
```
EJB call performed. Result:
HELLO WORLD
```

### Perform EJB call whose implementation calls method from another EJB service
Navigate to http://localhost:8080/testA2BRemoteCall

Note that the server interceptors are called again for the another EJB service remote method invocation. It uses new
`InvocationContext`. The `contextData` map is not transferred from the first EJB call (`No contextData found`).
The console output is then following:
```
client_1   | 12:04:00,623 INFO  [com.foo.servlet.TestA2BRemoteCallServlet] (default task-1) Processing request - start
client_1   | 12:04:00,625 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocation - before sendRequest
client_1   | 12:04:00,625 INFO  [com.foo.interceptor.MyContextDataInjectingInterceptor] (default task-1) Generated requestId 03b35e2c-c020-47a8-81f3-7341c2e6894b
client_1   | 12:04:00,626 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocation - after sendRequest
server_1   | 12:04:00,634 INFO  [com.foo.interceptor.AroundLoggingServerInterceptor] (default task-1) MyServiceABean#callsBRemoteProcessText - start
server_1   | 12:04:00,635 INFO  [com.foo.interceptor.MyContextDataRetrievingServerInterceptor] (default task-1) Obtained requestId 03b35e2c-c020-47a8-81f3-7341c2e6894b
server_1   | 12:04:00,637 INFO  [com.foo.MyServiceABean] (default task-1) Invoking remote MyServiceB$$$view1#processText
server_1   | 12:04:00,639 INFO  [com.foo.interceptor.AroundLoggingServerInterceptor] (default task-1) MyServiceBBean#processText - start
server_1   | 12:04:00,639 INFO  [com.foo.interceptor.MyContextDataRetrievingServerInterceptor] (default task-1) No contextData found
server_1   | 12:04:00,639 INFO  [com.foo.MyServiceBBean] (default task-1) Doing "hello world".toLowerCase() inside method processText
server_1   | 12:04:00,641 INFO  [com.foo.interceptor.AroundLoggingServerInterceptor] (default task-1) MyServiceBBean#processText - end
server_1   | 12:04:00,642 INFO  [com.foo.interceptor.AroundLoggingServerInterceptor] (default task-1) MyServiceABean#callsBRemoteProcessText - end
client_1   | 12:04:00,646 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocationResult - before getResult
client_1   | 12:04:00,646 INFO  [com.foo.interceptor.AroundLoggingClientInterceptor] (default task-1) #2 handleInvocationResult - after getResult
client_1   | 12:04:00,648 INFO  [com.foo.servlet.TestA2BRemoteCallServlet] (default task-1) Processing request - end
```

And web page shows
```
EJB call performed. Result:
hello world
```

## Stop running Wildfly instances
To stop running Wildfly instances hit `Ctrl + C`.
