# docker pfx 

Docker hosting the Java package PFX by andrea.bono@ingv.it (based on JavaFX to be compiled with MAVEN or directly run as jar): PFX repo https://gitlab.rm.ingv.it/caravel/pfx

## Building the docker image
From `pfx` folder, run:
```sh
docker build -t pfx:latest -f docker/Dockerfile .
```

## Runnig PFX in docker

First be sure to have allowed connections to X11 (Xquartz on mac os X).

Either in the XQuartx-\>Preferences-\>Security (MAC OSX) or with xhost + (Linux) or both (MAC OSX)

<!-- General based on ifconfig-->
The following instructions must be given on command line.
```sh
MYIP="`ifconfig | grep -w inet | egrep -v -w "127.0.0.1" | awk '{print $2}' | head -n 1`"
xhost +${MYIP} || exit
docker run --rm -it -e DISPLAY=${MYIP}:0 --mount type=bind,source=/tmp/.X11-unix,target=/tmp/.X11-unix --name pfx_latest pfx:latest java -jar /app/target/pfx-1.9.0-develop-SNAPSHOT.jar
```

Otherwise the script `./run_docker_PFX.sh` can be used:
```sh
./run_docker_PFX.sh java -jar /app/target/pfx-1.9.0-develop-SNAPSHOT.jar
```

## contacts/developers 
- raffaele.distefano@ingv.it
- valentino.lauciani@ingv.it
- andrea.bono@ingv.it
