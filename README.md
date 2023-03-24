### README

#### Assumptions
1. This application will do a simple authorization check but no authentication check.  In the real world, the X-Authorization request header will be the actual JWT token which the user received after authentication.
	- the authorization checks whether the following request header exists in the request: X-Authorization
2. This application will do an external web service lookup from the URL below to obtain the Country and Continent info; therefore, the assumption is that the external web service is functioning
	- https://countries.trevorblades.com/graphql
3. Rate limit is based on the Username provided in the X-Authorization request header
	- If the X-Authorization request header does not exists, then the IP address will be used as the rate limiter

#### Instructions
1. Clone this project.
2. Navigate to the root project folder where the gradlew.bat is located:  
└── <project folder>  
└──── gradlew

3. Run the following command to generate the spring boot jar file.
	- ./gradlew.bat build -x test
4. The output jar file will be in the <project folder>/build/libs folder  
build  
── libs  
   └── takehome-0.0.1-SNAPSHOT.jar
5. To run the application, open CMD and type:  
java -jar takehome-0.0.1-SNAPSHOT.jar

#### Test with browser
1. For testing, open the browser and navigate to http://localhost:8080/graphiql
2. For production, this should be disabled.

**Sample Request:**  
		query {
		  continent(codes: ["US","JP"]) {
		    countries
		    name
		    otherCountries
		  }
		}

#### Testing with REST
**Sample Request:**  
**URL:** http://localhost:8080/graphql  
**Method:** POST  
**POST Data:**  
{"query":"query {\n  continent(codes: [\"US\",\"JP\", \"CA\"]) {\n    countries\n    name\n    otherCountries\n  }\n}"}

#### To represent an authorized user, pass in the following request header:
X-Authorization: Username

where Username is any arbitrary username.


#### DOCKER
1. Navigate to the root project folder with the Dockerfile is
2. Run the following command to create an image
docker build --tag=takehome-mikehuynh:latest .
3. Run the following command to run the container
docker run -p8880:8080 takehome-mikehuynh:latest



