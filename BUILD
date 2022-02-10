load("@rules_java//java:defs.bzl", "java_binary", "java_test")

java_binary(
    name = "TerminalDuke", # Production Build.
    srcs = glob([
        "src/main/java/com/lockarhythm/**/*.java",
    ]),
    deps = [
        "@maven//:org_apache_commons_commons_lang3",
        "@maven//:com_google_code_gson_gson",
        "@maven//:org_ocpsoft_prettytime_prettytime_nlp",
    ],
)

java_binary(
    name = "TerminalDukeQA", # QA Build. has assertions enabled.
    main_class = "com.lockarhythm.application.TerminalDuke",
    srcs = glob([
        "src/main/java/com/lockarhythm/**/*.java",
    ]),
    deps = [
        "@maven//:org_apache_commons_commons_lang3",
        "@maven//:com_google_code_gson_gson",
        "@maven//:org_ocpsoft_prettytime_prettytime_nlp",
    ],
    jvm_flags = [
        "-enableassertions",
    ],
)

[
  java_test(
      name = class_name,
      size = "small",
      srcs = glob([
          "src/test/java/com/lockarhythm/**/*.java",
          "src/main/java/com/lockarhythm/**/*.java",
      ]),
    deps = [
        "@maven//:org_apache_commons_commons_lang3",
        "@maven//:com_google_code_gson_gson",
        "@maven//:org_ocpsoft_prettytime_prettytime_nlp",
        ]
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
