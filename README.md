# Birdus Alexa Skill

Read a list of bird sightings, sort into counties by day and list the species seen for that day.  
  
- Defaults to all sightings yesterday sorted by counties.
- Allow users to say a county _Kerry_ and return only the sightings in Kerry.  
- Allow users to say a day _Monday_ sightings and return the sightings for that specific day.  
- Allow users to say a day _Monday_ and a location _Kerry_ sightings and return only the sightings in Kerry that specfic day.  


Lambda collects results -> saves to S3 & DynamoDb -> Alexa reads results.  
Relies on https://github.com/1i/dynamoBirdus to prepopulate the sightings, whichs read from www.irishbirding.com . 

### 
Update the lambda  
```
maven clean install
aws lambda update-function-code --function-name birdus-alexa --zip-file fileb://./alexa-1.0-SNAPSHOT.jar
```

### Pom
Maven shade plugin is used to include extra dependencies in the jar for Lambda 
with the setting for _createDependencyReducedPom_ to false.
