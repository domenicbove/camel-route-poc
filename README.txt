## Camel Route Proof of Concept
## By MunChul Shin with help from Domenic Bove

Goals:
1. Dynamically create Camel Routes via Database
2. Create routes with JMS/AMQ Endpoints
3. Create routes with parameters

To Run Initially:
1. From project's root directory run-
mvn clean install

2. Start Fuse-
$fuse_home/bin/fuse

3. Install the app, check the routes
features:addurl mvn:com.example/features/0.0.1/xml/features
features:install ms ms-commons

To refresh the app (Working on script for this):
1. Rebuild it
mvn clean install

2. Reinstall it
features:uninstall ms ms-commons
features:removeurl mvn:com.example/features/0.0.1/xml/features
features:addurl mvn:com.example/features/0.0.1/xml/features
features:install ms ms-commons
