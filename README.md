## Birdus Alexa Skill

Read a list of bird sightings from S3, sort into counties and list the species seen for that day.  
  
- allow users to say a county _Kerry_ and return only the sightings in Kerry.  
- allow users to say a date _yesterdays_ sightings and return only the sightings for yesterday.  

### 
Update the lambda  
``
aws lambda update-function-code --function-name birdus-alexa --zip-file fileb://./alexa-1.0-SNAPSHOT.jar
``


## Pom
Maven shade plugin is used to include extra dependencies in the jar for Lambda 
with the setting for _createDependencyReducedPom_ to false.