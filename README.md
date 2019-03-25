# orderBoard
Live Order Board Application

### To run application:
- Build docker image
`./gradlew dockerBuildImage`  this should build docker image named *deepankurtyagi-order*

- Run docker container
`docker run  deepankurtyagi-order`

*App doesn't have any persistance layer, sample order stored in memory will be printed in the console.*

Remember to stop the container using `docker ps -aq | xargs docker rm -f`
