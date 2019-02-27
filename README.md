# cms
This is the backend services for synectiks college management system.

This application was generated using JHipster 5.1.0, you can find documentation and help at [https://www.jhipster.tech/documentation-archive/v5.1.0](https://www.jhipster.tech/documentation-archive/v5.1.0).

# Why did we choose jhipster 
1. Generate the Model & Rest Services from ERD diagram and JDL. It generates stanbdardized code.
2. Reuse its  JWT authentication , localization , internationilaztion inbuilt functionalties.
3. Most imporatnatly use its inbuilt liquibase for upgrade.

## Service Architecture 

![Alt text](./backend-architecture.png?raw=true "Service Architecture")
# Structure of the Project

Since some part of its model &  rest part is generated from jhipster , it follows some common pattern of typical jhipster project. The jhipster specific source code & directories are as follows:

1. .mvn/wrapper - some custom jars that has some mvn commands written only for jhipster.
2. .jhipster -- This is area , where jhipster creates all the json file snapshots corresponding to the models. Jhipster maintains this area so that when we make changes in code 
3. webpack -- jhipster maintains webpack build scripts to generate the UI . We dont use the jhipster generated UI for production. We have our own UI maintained in  https://github.com/xformation/cms-ui.
4. Src --  All the java src code 

Some important locations of most of our code base is :

src/model/ -- This is the location for our model jdl file and some grafql query scripts.
model.jdl is the model file.

The graphql resolvers and mutation are in src/main/java/com/synectiks/cms/graphql/ folder.

Some of the common services , like academic subjects and syllabus setup etc are located in :

src/main/java/com/synectiks/cms/business/service/

## Development Environent 

Before you can build this project, you must install and configure the following dependencies on your machine:

1. [maven][]: We use maven for building dependencies. You can install maven from apache site.
2. [java] -- This is build and run by oracle jdk1.8 environment , you can install oracle jdk1.8 from oracle site
3. [Node.js][]: We use Node to run a development web server and build the project.
   Depending on your system, you can install Node either from source or as a pre-packaged bundle.
4. [Yarn][]: We use Yarn to manage Node dependencies.
   Depending on your system, you can install Yarn either from source or as a pre-packaged bundle.

# How the Application is organized 
After installing Node, you should be able to run the following command to install development tools.
You will only need to run this command when dependencies change in [package.json](package.json).

    yarn install

We use yarn scripts and [Webpack][] as our build system.

Run the following commands in two separate terminals to create a blissful development experience where your browser
auto-refreshes when files change on your hard drive.

    ./mvnw
    yarn start

[Yarn][] is also used to manage CSS and JavaScript dependencies used in this application. You can upgrade dependencies by
specifying a newer version in [package.json](package.json). You can also run `yarn update` and `yarn install` to manage dependencies.
Add the `help` flag on any command to see how you can use it. For example, `yarn help update`.

The `yarn run` command will list all of the scripts available to run for this project.

### Service workers

Service workers are commented by default, to enable them please uncomment the following code.

* The service worker registering script in index.html

```html
<script>
    if ('serviceWorker' in navigator) {
        navigator.serviceWorker
        .register('./service-worker.js')
        .then(function() { console.log('Service Worker Registered'); });
    }
</script>
```

Note: workbox creates the respective service worker and dynamically generate the `service-worker.js`

### Managing dependencies

For example, to add [Leaflet][] library as a runtime dependency of your application, you would run following command:

    yarn add --exact leaflet

To benefit from TypeScript type definitions from [DefinitelyTyped][] repository in development, you would run following command:

    yarn add --dev --exact @types/leaflet

Then you would import the JS and CSS files specified in library's installation instructions so that [Webpack][] knows about them:
Note: there are still few other things remaining to do for Leaflet that we won't detail here.

For further instructions on how to develop with JHipster, have a look at [Using JHipster in development][].



## Building for production

To optimize the cms application for production, run:

    ./mvnw -Pprod clean package

This will concatenate and minify the client CSS and JavaScript files. It will also modify `index.html` so it references these new files.
To ensure everything worked, run:

    java -jar target/*.war

Then navigate to [http://localhost:8080](http://localhost:8080) in your browser.

Refer to [Using JHipster in production][] for more details.

## Testing

To launch your application's tests, run:

    ./mvnw clean test

### Client tests

Unit tests are run by [Jest][] and written with [Jasmine][]. They're located in [src/test/javascript/](src/test/javascript/) and can be run with:

    yarn test

UI end-to-end tests are powered by [Protractor][], which is built on top of WebDriverJS. They're located in [src/test/javascript/e2e](src/test/javascript/e2e)
and can be run by starting Spring Boot in one terminal (`./mvnw spring-boot:run`) and running the tests (`yarn run e2e`) in a second one.
### Other tests

Performance tests are run by [Gatling][] and written in Scala. They're located in [src/test/gatling](src/test/gatling).

To use those tests, you must install Gatling from [https://gatling.io/](https://gatling.io/).

For more information, refer to the [Running tests page][].

## Using Docker to simplify development (optional)

You can use Docker to improve your JHipster development experience. A number of docker-compose configuration are available in the [src/main/docker](src/main/docker) folder to launch required third party services.

For example, to start a postgresql database in a docker container, run:

    docker-compose -f src/main/docker/postgresql.yml up -d

To stop it and remove the container, run:

    docker-compose -f src/main/docker/postgresql.yml down

You can also fully dockerize your application and all the services that it depends on.
To achieve this, first build a docker image of your app by running:

    ./mvnw verify -Pprod dockerfile:build dockerfile:tag@version dockerfile:tag@commit

Then run:

    docker-compose -f src/main/docker/app.yml up -d

For more information refer to [Using Docker and Docker-Compose][], this page also contains information on the docker-compose sub-generator (`jhipster docker-compose`), which is able to generate docker configurations for one or several JHipster applications.

## Continuous Integration (optional)

To configure CI for your project, run the ci-cd sub-generator (`jhipster ci-cd`), this will let you generate configuration files for a number of Continuous Integration systems. Consult the [Setting up Continuous Integration][] page for more information.

[JHipster Homepage and latest documentation]: https://www.jhipster.tech
[JHipster 5.1.0 archive]: https://www.jhipster.tech/documentation-archive/v5.1.0

[Using JHipster in development]: https://www.jhipster.tech/documentation-archive/v5.1.0/development/
[Using Docker and Docker-Compose]: https://www.jhipster.tech/documentation-archive/v5.1.0/docker-compose
[Using JHipster in production]: https://www.jhipster.tech/documentation-archive/v5.1.0/production/
[Running tests page]: https://www.jhipster.tech/documentation-archive/v5.1.0/running-tests/
[Setting up Continuous Integration]: https://www.jhipster.tech/documentation-archive/v5.1.0/setting-up-ci/

[Gatling]: http://gatling.io/
[Node.js]: https://nodejs.org/
[Yarn]: https://yarnpkg.org/
[Webpack]: https://webpack.github.io/
[Angular CLI]: https://cli.angular.io/
[BrowserSync]: http://www.browsersync.io/
[Jest]: https://facebook.github.io/jest/
[Jasmine]: http://jasmine.github.io/2.0/introduction.html
[Protractor]: https://angular.github.io/protractor/
[Leaflet]: http://leafletjs.com/
[DefinitelyTyped]: http://definitelytyped.org/
