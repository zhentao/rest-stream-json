An example to illustrate how to use jackson streaming api to stream json response in restful web services.

1. set up mysql
2. Start tomcat:

mvn clean tomcat7:run

Then access the following url:

streaming:
http://localhost:8080/rest/creatives/stream?after=2000-01-01 23:10:10

non-streaming
http://localhost:8080/rest/creatives?after=2000-01-01 23:10:10

class TestStreaming shows how to stream the response programmatically.