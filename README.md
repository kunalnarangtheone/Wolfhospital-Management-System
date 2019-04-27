# Wolf Hospital Management System (CSC 540 Project)

WHMS is a CLI utility implemented in Java.

## Building

WHMS uses Maven to produce executable jar files. Just run `mvn package`.

## Using the CLI

Several application parameters may be set via environment variables.

| Name | Default | Description |
| ---- | ------- | ----------- |
| `WHMS_HOSTNAME` | `localhost` | Network name for the database host. | 
| `WHMS_PORT`     | `3306`      | Database connection port. | 
| `WHMS_DATABASE` | `csc540`    | Database name. | 
| `WHMS_USERNAME` | `root`      | Connection username. | 
| `WHMS_PASSWORD` |             | Connection password. | 

## Executing the CLI

To execute the CLI, run

```
java -jar target/whms-0.1.0.jar
```

On executing the jar file, you will find a list of commands, for example a 
sample output of the above command is given below:

```
Usage: whms [-hV] [COMMAND]
  -h, --help      Show this help message and exit.
  -V, --version   Print version information and exit.
Commands:
  init
  select
  current-bed-usage
  medical-records-in-span
  patients-per-month
  occupied-capacity
  patients-under-care
  staff-by-role
  find-available-bed
  assign-available-bed
  release-bed
  create-billing-record
  update-billing-record
  create-billing-statement
  update-billing-statement
  create-patient
  update-patient
  delete-patient
  create-staff
  update-staff
  delete-staff
  create-test
  update-test
  create-treatment
  update-treatment
  create-visit
  update-visit
  create-ward
  update-ward
  delete-ward
```
  
To run a specific command, specify the name from the list of available 
commands. For example, if we need to find an available bed, use the following 
command:

```
java -jar target/whms-0.1.0.jar find-available-bed
```
