# library-management

Use BookBorrow ,table to store data related to borrowed books.
boolean returned variable used to identify the already borrowd books.

Most of the APIs resturning string value as a success response. (this needs a changed with properly constructed output dtos).

RestException handler introduced to handle the execptions all over the project.

postman-collection attached to repository. This includes all the APIs created in the project.

Dockerizing
-----------
include Dockerfile  and add java 17 base image.
then after maven build jar file genrered in target folder. copy this inside the docker container
Expose deisred port
incude the one EntryPoint commands to run the jar file

CI/CD
-----
Assueme the docker image will be publised to dokcer repository and creating.
In the CI/CD pipleline, in CI stage build the source code and need to create the dokcer image and upload to dokcer repostory.
in the CD stage, need to mentioned the desired VM/EC2 , where we are going to deploy our dokcerized container.
Then after pulling the image to Server, we can start the dokcer conatiner
