for development tracking of project

# Routine Task
- [ ] progressive documentation

# Feature Level 0

- [x] entry message
- [x] exit message
- [x] exits gently

# Feature Level 1

## Test
- user input loop
    - [x] user should input as textCommand and app will echo back full text
    - [x] exit loop on textCommand "bye"


# Feature Level 2

## New Feature
- [x] Create abstract Class Task
    - [x] Create ToDo sub class of Task
    - [x] Aggregate class TaskManager of Task objects
- textCommand
    - [x] default textCommand: create task with textCommand as task description
    - [x] add textCommand `list` to list all tasks
  
# Feature Level 3

## New Feature

- textCommands
  - [x] `done [taskId]` - flag task as done

# Feature Level 4

## New Feature


- [x] Create Deadline extends Task
  - With deadline
- [x] Create Event extends Task
  - With start and end time
- Command
  - [x] `$ todo [task_description]` - add a ToDo \
  - [x] `$ deadline [task_description] /by [dateString]` - add a Deadline \
  - [x] `$ event [task_description] /at [fromDateString]-[toDateString]` - add an event \
  - [x] `*` - unknown request

## Feature Level 5

No feature changes (error handling)

# [Feature Level 6]

## New Feature

- [x] `$ delete [task_id]` - delete a task \


# [Feature Level 7]

## New Feature

- app will load task resource, if any
  - [x] load on start
  - [x] `save` - save tasks

# [Feature Level 8]

## New Feature

- [x] change dateStrings to localDateTime


# [Feature Level 9]

## New Feature

- [x] find by description
- [x] projection for next 30 days