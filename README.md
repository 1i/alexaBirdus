## Birdus Alexa Skill


Update the lambda  
``
aws lambda update-function-code --function-name birdus-alexa --zip-file fileb://./alexa-1.0-SNAPSHOT.jar
``


## Pom
Maven shade plugin is used to include extra dependencies in the jar for Lambda.  
createDependencyReducedPom