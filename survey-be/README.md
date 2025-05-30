# Survey Backend

This is for demo purposes only.

## Prerequisites

- JDK 24 [https://www.oracle.com/sg/java/technologies/downloads/]
- Maven
- OCI cloud account
- OCI API keys set up (download private key, copy config)
- OCI logging service Log Groups
- OCI logging service Logs
- Docker
- WSL2 (for windows)

## Installation

1. Navigate to survey-be folder
2. Create a file called config (no extension) within same directory as Dockerfile, copy the API key config generated from OCI
3. Copy and paste your OCI private key to same directory as Docker file, rename to private.pem
4. Edit config.yml, update ociLogId to use your own logid value -> retrieve from OCI > Logging > Logs > 'Your Log Name' > OCID value
5. Run `mvn clean install` at same folder level as pom.xml

## Usage

Build container : `docker build -t survey-demo .`

Run container : `docker run -p 8080:8080 survey-demo`

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

[MIT](https://choosealicense.com/licenses/mit/)