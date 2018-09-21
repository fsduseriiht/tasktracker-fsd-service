# tasktracker-fsd-service



### useful links related to docker:
<br> https://docs.docker.com/docker-cloud/builds/push-images/
<br> https://docs.docker.com/docker-hub/builds/
<br> https://hub.docker.com/r/amitabhadockerwork/

### Docker build commands
<br>  docker pull amitabhadockerwork/ui-layer-fsd
<br>  docker pull amitabhadockerwork/service-layer-fsd



Docker for Service Layer - Task Tracker Project
-----------------------------------------------------------------------
General Commands to be executed in Docker related to push/build/run- 
=======================================================================
	docker build -t your_image_name your_repo_url
	docker tag your_image_name docker_username/your_image_name
	docker push your_image_name docker_username/your_image_name
	docker run --rm -i -t -d -p 8080:8080/tcp -p 8080:8080/udp --name your_image_name your_image_id_value

Example for push/build/run- 
=======================================================================
	docker build -t service-layer-fsd https://github.com/fsduseriiht/tasktracker-fsd-service.git
	docker tag service-layer-fsd amitabhadockerwork/service-layer-fsd
	docker push amitabhadockerwork/service-layer-fsd
	docker run --rm -i -t -d -p 8080:8080/tcp -p 8080:8080/udp --name service-layer-fsd_running 6a35e567faf4
