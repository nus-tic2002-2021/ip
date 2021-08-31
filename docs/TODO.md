for development: tracking of tasks and project design

# See [Epic](https://www.notion.so/Duke-Coronet-fc4c509a3abd42ffa0d7bcbd93a8114c)

# Routine Task
- [ ] progressive documentation


# [Feature Level 0](https://www.notion.so/Level-0-Greet-71a7be8614a440169e7041813d1f943e)

- [x] entry message
- [x] exit message
- [x] exits gently

# [Feature Level 1](https://www.notion.so/Level-1-Greet-Echo-Exit-2b73e7e61b944e878b8150f937218f88)

## Test
- [ ] refine display
  - [ ] each request-response cycle to be distinguishable.

# [Feature Level 2](https://www.notion.so/Level-2-Add-List-ec463c120b9447a6be16443b3ecf3d5c)

## New Feature
- [ ] Create abstract Class Task
  - [ ] Create Todo sub class of Task
  - [ ] In-memory storage of Task objects (Need to create suitable collection)
- [ ] command `list` list all

## Feature Change
- [ ] command `*` default response change from echo command to add task

# [Feature Level 3](https://www.notion.so/Level-3-Mark-as-Done-b1fc5a70549d4f099631a786241d7ce5)

## New Feature

- Task object attributes
  - "done" status
    - [ ] add "done" status
  - [ ] add "active" status
  - [ ] added a HashMap\<taskId:int,task:Task\> in TaskList for constant time object access.

## Feature Change

- Command
  - [ ] `list` - add display "done" status column
  - [ ] `add [task_description]` - add a ToDo
  - [ ] `done [task_id]` - flag task as done
  - [ ] `*` - default command change from add task to unknown request.

# [Feature Level 4](https://www.notion.so/Level-4-ToDos-Events-Deadlines-7ed23a1dcfb5464f9262e5e13f61f028)

## New Feature
 

- [ ] Show Task Type in list
- [ ] Create Deadline sub class of Task
  - With deadline
- [ ] Create Event sub class of Task
  - With start and end time
- Command
  - [ ] `$ todo [task_description]` - add a ToDo \
  - [ ] `$ deadline [task_description] /by [dateString]` - add a Deadline \
  - [ ] `$ event [task_description] /at [fromDateString]-[toDateString]` - add an event \
  - [ ] `$ add ` - subsumed by default

# [Feature Level 6](https://www.notion.so/Level-6-Delete-01e01049c8ef41748ba3be9b035b0e5d)

## New Feature

- [ ] `$ delete [task_id]` - delete a task \

# [Feature Level 7]

## New Feature

- app will load task resource, if any
  - [ ] load on start
  - [ ] save command

# [Feature Level 8]

## New Feature

- [ ] change dateStrings to localDateTime
