Changelog

## Feature Level 0

Implement an initial skeletal version of the Duke that simply greets the user and exits. \
![level 0](/docs/screenshots/branch-level-0.png)

## Feature Level 1

`bye` exit input loop
`[text]` echo \

Implement an initial skeletal version of the Duke that simply greets the user and exits. \
![level 1](/docs/screenshots/branch-level-1.gif)

## Feature Level 2

`bye` exit input loop \
`list` list tasks \
`[description]` add String \

Add the ability to store whatever text entered by the user and display them back to the user when requested.
![level 2](/docs/screenshots/branch-level-2.gif)

## Feature Level 3

`bye` exit input loop \
`list` list tasks \
`[description]` add ToDo \
`done [id]` set task as done \

![level 3](/docs/screenshots/branch-level-3.gif)

## Feature Level 4

`bye` exit input loop \
`list` list tasks \
`todo [description]` add ToDo \
`deadline [description] /by [date]` add Deadline \
`event [description] /at [from]-[to]` add Event \
`done [id]` set task as done \

![level 4](/docs/screenshots/branch-level-4.gif)


## Feature Level 5

No feature changes (error handling)


## Feature Level 6

`bye` exit input loop \
`list` list tasks \
`todo [description]` add ToDo \
`deadline [description] /by [date]` add Deadline \
`event [description] /at [from]-[to]` add Event \
`done [id]` set task as done \
`delete [id]` delete task by id \

![level 6](/docs/screenshots/branch-level-6.gif)


## Feature Level 7

`bye` exit input loop \
`list` list tasks \
`todo [description]` add ToDo \
`deadline [description] /by [date]` add Deadline \
`event [description] /at [from]-[to]` add Event \
`done [id]` set task as done \
`delete [id]` delete task by id \
`save` save tasks to file \

![level 7](/docs/screenshots/branch-level-7.gif)

``` 
[
    {
        "taskId": "0",
        "description": "this is a ToDo"
    },
    {
        "taskId": "1",
        "description": "this is a Deadline",
        "deadline": "some deadline"
    },
    {
        "taskId": "2",
        "description": "this is an event",
        "from" : "from day",
        "to" : "to day"
    },
]
```

### level 8 Date and Times

`$ *`   - unknown command \

`todo [description]` add ToDo \
`deadline [description] /by [date]` add Deadline \
`event [description] /at [from]-[to]` add Event \

- dateOption formats
    - Input year, month and day : "20201231"
    - Input year, month, day, hour, minutes : "20201231 23:59"


`delete [id]` delete task by id \
`done [id]` set task as done \

`list` list tasks \
`bye` exit input loop \
`save` save tasks to file \


### level 9 Find

`$ *`   - unknown command \

`todo [description]` add ToDo \
`deadline [description] /by [date]` add Deadline \
`event [description] /at [from]-[to]` add Event \

- dateOption formats
  - Input year, month and day : "20201231"
  - Input year, month, day, hour, minutes : "20201231 23:59"


`delete [id]` delete task by id \
`done [id]` set task as done \

`list` list tasks \
`find [string_in_description]` query tasks with string in description \
`bye` exit input loop \
`save` save tasks to file \


## Additional

`$ *`   - unknown command \

`todo [description]` add ToDo \
`deadline [description] /by [date]` add Deadline \
`event [description] /at [from]-[to]` add Event \

- dateOption formats
  - Input year, month and day : "20201231"
  - Input year, month, day, hour, minutes : "20201231 23:59"

`delete [id]` delete task by id \
`done [id]` set task as done \
`undone [id]` set task as not done \

`list` list tasks \
`find [string_in_description]` query tasks with string in description \
`bye` exit input loop \
`save` save tasks to file \
