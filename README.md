# urlparse

play example project by Rong Zhai, skype: rong.zhai, Homepage: zhairong.info, email: zhairong1975@gmail.com

Example - microservice - (c) Springer Nature 2016


Environment
===========

I. For developer

There are two ways you can run the service locally on your machine.

Using activator,

  ```
    ./activator run
  ```

II. Building a package for manual deployment to AWS instance.

  To package the service, you need to use activator to build and
  package the app that can easily be transferred to our instances.
  
  ```
    ./activator clean dist
  ```
  
  After successfully completing the process above, you can go to the
  directory where the package is stored. In our case, it can be found
  in universal directory where the app is compressed in a zip file.
  
  ```
    $cd target/universal
    $ls
    
   ---  parser-1.0.zip
  ```
  
  In this directory, you can rsync or scp it to the instance where it hosts
  the other microservices. 
  
  Just make sure you have the keys to access our instance via ssh. 
  
   
    Running the services
    ==================== 
    
    The last thing you need to do after everything is set and done is to run the service in the instance. 
    
    You need to go inside the directory of the service so you can quickly get into its bin directory where the
    executables are stored. We usually do the following:
    
    ```
        nohup ./bin/parser -Dhttp.port=8075 -J-Xms128M -J-Xmx256m &
    ```
    
    If you need to deploy a service alongside with the PRD, you just need to change the port (ie 8076). Just 
    make sure the port is accessible from the outside. This can be configured inside AWS under Security groups.
    Expose the port you've decided to use (ie 8076).

III. Testing with postman. 
```
    Just start postman and import test script urlparse.postman_collection.json. 
    you can test urlparse with POST /v1/urlparse, protocol like "http, https, ftp" has to be set otherwise you get Http.400["bad request"].
```
===
