# Birdus Alexa Skill

Read a list of bird sightings, sort into counties by day and list the species seen for that day.  
  
- Defaults to all sightings yesterday sorted by counties.
- Allow users to say a county _Kerry_ and return only the sightings in Kerry.  
- Allow users to say a day _Monday_ sightings and return the sightings for that specific day.  
- Allow users to say a day _Monday_ and a location _Kerry_ sightings and return only the sightings in Kerry that specfic day.  


Lambda collects results -> saves to S3 & DynamoDb -> Alexa reads results.  
Relies on https://github.com/1i/dynamoBirdus to prepopulate the sightings, whichs read from www.irishbirding.com . 

### Update the lambda  
```
maven clean install
aws lambda update-function-code --function-name birdus-alexa --zip-file fileb://./alexa-1.0-SNAPSHOT.jar
```

### Pom
AWS dependencies are available on the class path.  
Maven shade plugin is used to include extra dependencies in the jar for Lambda 
with the setting for _createDependencyReducedPom_ to false.



### Learnings

- Check which UK or US english in the top left of the code section in Alexa console.
- App can be cloned into same language but different region.
- Implement the required Intents and the SessionEndHandler which is not documented well as a requirement.  
- When app returns <Audio message> sound but no logs when calling via simulator or real device, rename the invocation name.
- Invovation name has to be 2 words to be published but can be one word for development.