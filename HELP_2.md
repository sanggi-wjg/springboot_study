### 포트 이미 사용 중 일때
Identify and stop the process that's listening on port 8080 or configure this application to listen on another port.
```
## For Ubuntu/Linux ## 

Step 1: Find the process id that is using the port 8080

netstat -lnp | grep 8080
or
ps -aef | grep tomcat
Step 2: Kill the process using process id in above result

kill -9 process_id

## For Windows ## 

Step 1: Find the process id

netstat -ano | findstr 8080
Step 2: Open command prompt as administrator and kill the process

taskkill /F /pid 1088
```