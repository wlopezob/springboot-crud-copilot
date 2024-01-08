# Project Person API with Copilot Chat

> ##  Prompts:

### Class Entity
- Create the entity class PersonEntity corresponding to the person table
- Add Lombok annotations : @Getter, @Setter, @NoArgsConstructor , @AllArgsConstructor and @Builder
- Add JPA annotations @Column with column name, to map the  PersonEntity class to a database table person
- Use the following JSON structure to define the field of the PersonEntity {
  "id": "Long",
  "dni": "String",
  "name": "String",
  "lastname": "String",
  "fathername": "String",
  "age": "int"
  }, in the structure each key represents the name of the attribute, and its value represents the data type of the field
- the id field is the primary key and is autoincrement

### Class Repository
- Create the interface PersonRepository that extends JpaRepository using PersonEntity

### Class Dto
- Add Lombok annotations : @Getter, @Setter, @NoArgsConstructor , @AllArgsConstructor and @Builder
- Create the class PersonRequestDto containing the same fields as the class PersonEntity
- the fields dni, name and lastname are required and add message error

### Class Dto
- Add Lombok annotations : @Getter, @Setter, @NoArgsConstructor , @AllArgsConstructor, @Builder and @JsonInclude(JsonInclude.Include.NON_NULL)
- Create the class PersonResponseDto containing the same fields as the class PersonEntity

### Class Service
- Create the interface PersonService
- Add the following methods:
    - savePerson: this method should take a parameter of type PersonRequestDto and return an instance of PersonResponseDto
    - updatePerson: this method should take two parametes, a Long(id) and PersonRequestDto and return an instance of PersonResponseDto
    - deletePerson: this method should take a parameter of type Long(id)
    - listPerson: this method return PersonResponseDto list


### Class Mapper
- Create interface MapStruct PersonMapper
- Use @Mapper(componentModel = "spring") annotation to define it as a mapper component
- Create method to convert PersonRequestDto to PersonEntity
- Create method to convert PersonEntity to PersonResponseDto
- Create method for updating PersonEntity with PersonRequestDto, updating all fields and ignore the field id
- 
### Method savePerson
- Create the class PersonServiceImpl that implements PersonService
- Add the @Service annotation
- Add the Lombok annotations: @Sl4j, @RequiredArgsConstructor
- Use functional programming techniques to streamline the process, focusing on immutable data and pure functions for better maintainability and testability.
- In the  savePerson method, consider the following steps:
  - Use PersonRepository to search record by dni, if record exist throw a new Exception, show the method to search record by dni in class PersonRepository
  - Use the mapper to convert PersonRequestDto to PersonEntity
  - Use PersonRepository to save PersonEntity
  - Convert the saved entity back to PersonResponseDto using the mapper and return this dto.

### Method savePerson in contoller
- Create the class PersonController
- Add the @RestController annotation
- Add the Lombok annotations: @Sl4j, @RequiredArgsConstructor
- Utilize Spring Webflux in the implementation.
- Use request path "/person"
- Create the savePerson endpoint to save a person, consider the following steps:
  - The endpoint should be a POST and valid the request
  - Invoke the savePerson method of PersonService
  - Return Mono<ResponseEntity<PersonResponseDto>>


