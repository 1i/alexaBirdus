# Birdus Alexa Skill

See improved version at https://github.com/1i/ebirdus

Read a list of bird sightings, sort into counties by day and list the species seen for that day.  
  
- Defaults to all sightings yesterday sorted by counties.
- Allow users to say a county _Kerry_ and return only the sightings in Kerry.  
- Allow users to say a day _Monday_ sightings and return the sightings for that specific day.  
- Allow users to say a day _Monday_ and a location _Kerry_ sightings and return only the sightings in Kerry that specfic day.  


Lambda collects results -> saves to S3 & DynamoDb -> Alexa reads results.  
Relies on https://github.com/1i/dynamoBirdus to prepopulate the sightings, which reads from www.irishbirding.com . 

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

- Check which language is the app targeting UK or US english. Top left of the code section in Alexa console.
- App can be cloned into same language but different region.
- Implement the required Intents and the SessionEndHandler which is not documented well as a requirement.  
- When app returns <Audio message> sound but no logs when calling via simulator or real device, try renaming the invocation name.
- Innovation name has to be 2 words to be published but can be one word for development.
- SL4J logging mess. Uber Jar and other versions of L4J on classpath.


### Improvements

- If the requested day is not available fall back to most recent result.. How to along iterating back in days.. 
- eBird.org provide a RESTful API


### Testing

*Unit tests* though I am happy calling S3 inside a unit test instead of reading a local json file.  
*Integration tests* calling S3.  

*End to End test* this is where I would like to assert against the Alexa output see alexaResponse.json.  
The problem with E2E tests :
- Where are they run from, locally, CI or in AWS.
- How to authenicate to call the lambda.
- How to call lamdba directly or do I need APIGateway infront.  
