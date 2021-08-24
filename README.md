<img src="https://user-images.githubusercontent.com/88638946/130622078-e049680c-7895-4725-ace0-49869574887c.png" width="50%" align="right">

# Duke

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Build
Prerequisites: Have `bazel` installed.

To build for local development on macOS:
```bash
# bazel makes a wrapper script around the jar file.
bazel build //:Duke && ./bazel-bin/Duke

# or simply
bazel run //:Duke
```

To build jar file for deployment purposes:
```bash
bazel build //:Duke_deploy.jar

java -jar ./bazel-bin/Duke_deploy.jar
```

## Testing
For unit tests:
```bash
bazel test --test_output=all //...
```
For terminal ui tests (`text-ui-test`):
```bash
cd text-ui-test
./runtest.sh
```

## Code Formatting & Style
Use [google-java-format](https://github.com/google/google-java-format):
```bash
google-java-format --replace **/*.java # executes recursively
```
