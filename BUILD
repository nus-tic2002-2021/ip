load("@rules_java//java:defs.bzl", "java_binary", "java_test")

java_binary(
    name = "TerminalDuke",
    srcs = glob([
        "src/main/java/com/lockarhythm/**/*.java",
    ]),
    deps = ["@maven//:org_apache_commons_commons_lang3"]
)

[
  java_test(
      name = class_name,
      size = "small",
      srcs = glob([
          "src/test/java/com/lockarhythm/**/*.java",
          "src/main/java/com/lockarhythm/**/*.java",
      ]),
  )
  for class_name in [
      "TestTerminalDuke",
      "TestEchoResponder",
      "TestExitResponder",
      "TestListResponder",
      "TestMarkAsDoneResponder",
      "TestTodoTask",
      "TestTaskList",
  ]
]
