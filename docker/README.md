# docker sit 

Docker hosting the Java package SIT by andrea.bono@ingv.it (based on JavaFX to be compiled with MAVEN or directly run as jar): SIT repo https://github.com/andreabono/SIT

## Building the docker image
From `sit` folder, run:
```sh
docker build -t sit:latest -f docker/Dockerfile .
```

## Runnig SIT in docker

First be sure to have allowed connections to X11 (Xquartz on mac os X).

Either in the XQuartx-\>Preferences-\>Security (MAC OSX) or with xhost + (Linux) or both (MAC OSX)

<!-- General based on ifconfig-->
The following instructions must be given on command line.
```sh
MYIP="`ifconfig | grep -w inet | egrep -v -w "127.0.0.1" | awk '{print $2}' | head -n 1`"
xhost +${MYIP} || exit
docker run --rm -it -e DISPLAY=${MYIP}:0 --mount type=bind,source=/tmp/.X11-unix,target=/tmp/.X11-unix --name sit_latest sit:latest java -jar /app/target/sit-1.9.0-develop-SNAPSHOT.jar
```

Otherwise the script `./run_docker_sit.sh` can be used:
```sh
./run_docker_sit.sh java -jar /app/target/sit-1.9.0-develop-SNAPSHOT.jar
```

## contacts/developers 
- andrea.bono@ingv.it
