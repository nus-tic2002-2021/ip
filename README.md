             _                   _                                _             
            | |                 | |                              | |                
            | |_    __ _   ___  | | __  _ __ ___     __ _   ___  | |_    ___    _ __ 
            | __|  / _` | / __| | |/ / | '_ ` _ \   / _` | / __| | __|  / _  \ | '__|
            | |_  | (_| | \__ \ |   <  | | | | | | | (_| | \__ \ | |_  |   __/ | | 
             \__|  \__,_| |___/ |_|\_\ |_| |_| |_|  \__,_| |___/  \__|  \___|  |_|  

### See
[Changelog](/docs/CHANGELOG.md) \
[Dev Milestones / Development Guide](/docs/TODO.md)

## Run

Orchestrators are entry points of project stages.

## Incremental
- [Level 0. Greet](https://nus-tic2002-2021.github.io/website/se-book-adapted/projectDuke/index.html#level-0-greet) \
  Entry Point: ```duke.coronet.orchestra.OrchestratorLevel00.main()``` \
  Test: ```duke.coronet.level.Increment_00_Test```

- [Level 1. Greet, Echo, Exit](https://nus-tic2002-2021.github.io/website/se-book-adapted/projectDuke/index.html#level-1-greet-echo-exit) \
  Entry Point:  ```duke.coronet.orchestra.OrchestratorLevel01.main()``` \
  Test: ```duke.coronet.level.Increment_01_Test```

- [Level 2. Add, List](https://nus-tic2002-2021.github.io/website/se-book-adapted/projectDuke/index.html#level-2-add-list) \
  Entry Point:  ```duke.coronet.orchestra.OrchestratorLevel02.main()``` \
  Test: ```duke.coronet.level.Increment_02_Test```

- [Level 3. Mark as Done](https://nus-tic2002-2021.github.io/website/se-book-adapted/projectDuke/index.html#level-3-mark-as-done) \
  Entry Point:  ```duke.coronet.orchestra.OrchestratorLevel03.main()``` \
  Test: ```duke.coronet.level.Increment_03_Test```

# Production Build (Maven)

``` mvn test ``` test from root test source \
``` mvn install ``` install dependencies \
``` mvn package ``` build jar \
``` mvn compile ``` compile from root test source \
``` mvn verify ``` check build integrity\
``` java -cp target/[package].jar [some][.domain][.EntryClass]``` specify path of jar and entry class
