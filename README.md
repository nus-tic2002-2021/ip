# Duke project template

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
