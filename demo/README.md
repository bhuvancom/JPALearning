# Note — This is just for learning purpose 

## Mapping in JPA

#### There are a few types of relationships:

- One-to-Many
- Many-to-One
- One-to-One
- Many-to-Many

#### One-to-Many/Many-to-One

One-to-Many and Many-to-One relationships, which are closely related. You could go ahead and say that they’re the
opposite sides of the same coin.
As its name implies, it’s a relationship that links one entity to many other entities.

In our example, this would be a Teacher and their Courses. A teacher can give multiple courses, but a course is given by
only one teacher (that's the Many-to-One perspective - many courses to one teacher).

Another example could be on social media — a photo can have many comments, but each of those comments belongs to that
one photo.

Before diving into the details of how to map this relationship, let’s create our entities:

```Java
@Entity
public class Teacher {
    private String firstName;
    private String lastName;
}
@Entity
    public class Course {
    private String title;
}
```

Now, the fields of the Teacher class should include a list of courses. Since we'd like to map this relationship in a database, which can't include a list of entities within another entity - we'll annotate it with a @OneToMany annotation:
```Java
@OneToMany
private List<Course> courses;

```

We’ve used a List as the field type here, but we could've gone for a Set or a Map.

How does JPA reflect this relationship in the database? Generally, for this type of relationship, we must use a foreign key in a table.

JPA does this for us, given our input on how it should handle the relationship. This is done via the ```@JoinColumn``` annotation:
```Java
@OneToMany
@JoinColumn(name = "TEACHER_ID", referencedColumnName = "ID")
    private List<Course> courses;
```

Using this annotation will tell JPA that the COURSE table must have a foreign key column TEACHER_ID that references the TEACHER table's ID column.
Let’s add some data to those tables:
```SQL
insert into TEACHER(ID, LASTNAME, FIRSTNAME) values(1, 'Doe', 'Jane');
insert into COURSE(ID, TEACHER_ID, TITLE) values(1, 1, 'Java 101');
insert into COURSE(ID, TEACHER_ID, TITLE) values(2, 1, 'SQL 101');
insert into COURSE(ID, TEACHER_ID, TITLE) values(3, 1, 'JPA 101');
```

### Owning Side and Bidirectionality
In the previous example, the Teacher class is called the owning side of the One-To-Many relationship. This is because it defines the join column between the two tables.

The Course is called the referencing side in that relationship.

We could’ve made Course the owning side of the relationship by mapping the Teacher field with @ManyToOne in the Course class instead:

```Java
@ManyToOne
@JoinColumn(name = "TEACHER_ID", referencedColumnName = "ID")
private Teacher teacher;
```

There’s no need to have a list of courses in the ```Teacher``` class now.

This time, we used the ```@ManyToOne``` annotation, in the same way we used ```@OneToMany```.

**Note:** It’s a good practice to put the owning side of a relationship in the class/table where the foreign key will be held.

So, in our case this second version of the code is better. But, what if we still want our ```Teacher``` class to offer access to its ```Course``` list?

We can do that by defining a bidirectional relationship:
```Java
@Entity
public class Teacher {
    // ...
    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;
}

@Entity
public class Course {
    // ...

    @ManyToOne
    @JoinColumn(name = "TEACHER_ID", referencedColumnName = "ID")
    private Teacher teacher;
}
```
We keep our ```@ManyToOne``` mapping on the Course entity. However, we also map a list of Courses to the Teacher entity.

What’s important to note here is the use of the ```mappedBy``` flag in the ```@OneToMany``` annotation on the referencing side.

Without it, we wouldn’t have a two-way relationship. We’d have two one-way relationships. Both entities would be mapping foreign keys for the other entity.

With it, we’re telling JPA that the field is already mapped by another entity. It’s mapped by the ```teacher``` field of the ```Course``` entity.

### Eager vs Lazy Loading
Another thing worth noting is eager and lazy loading. With all our relationships mapped, it’s wise to avoid impacting the software’s memory by putting too many entities in it if unnecessary.

Imagine that ```Course``` is a heavy object, and we load all ```Teacher``` objects from the database for some operation. We don't need to retrieve or use the courses for this operation, but they're still being loaded alongside the Teacher objects.

This can be devastating for the application’s performance. Technically, this can be solved by using the ```Data Transfer Object Design Pattern``` and retrieving ```Teacher``` information without the ```courses```.

However, this can be a massive overkill if all we’re gaining from the pattern is excluding the courses.

Thankfully, JPA thought ahead and made *One-to-Many* relationships load *lazily* by default.

>This means that the relationship won’t be loaded right away, but only when and if actually needed.

In our example, that would mean until we call on the ```Teacher#courses``` method, the courses are not being fetched from the database.

By contrast, *Many-to-One* relationships are *eager* by default, meaning the relationship is loaded at the same time the entity is.

We can change these characteristics by setting the ```fetch``` argument of both annotations:
```Java
@OneToMany(mappedBy = "teacher", fetch = FetchType.EAGER)
private List<Course> courses;
@ManyToOne(fetch = FetchType.LAZY)
private Teacher teacher;
```
That would inverse the way it worked initially. Courses would be loaded eagerly, as soon as we load a ```Teacher``` object. By contrast, the ```teacher``` wouldn't be loaded when we fetch ```courses``` if it's unneeded at the time.

### Optionality
Now, let’s talk about optionality.

>A relationship may be *optional* or *mandatory*.

Considering the *One-to-Many* side — it is always optional, and we can’t do anything about it. The *Many-to-One* side, on the other hand, offers us the option of making it *mandatory*.

By default, the relationship is optional, meaning we can save a ```Course``` without assigning it a teacher:
```Java
Course course = new Course("C# 101");
entityManager.persist(course);
```
Now, let’s make this relationship mandatory. To do that, we’ll use the ```optional``` argument of the ```@ManyToOne``` annotation and set it to ```false``` (it's ```true``` by default):
```Java
@ManyToOne(optional = false)
@JoinColumn(name = "TEACHER_ID", referencedColumnName = "ID")
private Teacher teacher;
```
Thus, we can no longer save a course without assigning a teacher to it:
```Java
Course course = new Course("C# 101");
assertThrows(Exception.class, () -> entityManager.persist(course));
```
But if we give it a teacher, it works fine again:
```Java
Teacher teacher = new Teacher();
teacher.setLastName("Doe");
teacher.setFirstName("Will");
Course course = new Course("C# 101");
course.setTeacher(teacher);
entityManager.persist(course);
```
Well, at least, it would seem so. If we had run the code, an exception would’ve been thrown:
```
javax.persistence.PersistenceException: 
org.hibernate.PersistentObjectException: detached entity passed to 
persist: com.fdpro.clients.stackabuse.jpa.domain.Course
```
Why is this? We’ve set a valid ```Teacher``` object in the ```Course``` object we're trying to persist. However, we haven't persisted the Teacher object before trying to persist the Course object.

Thus, the ```Teacher``` object isn't a managed entity. Let's fix that and try again:
```Java
Teacher teacher = new Teacher();
teacher.setLastName("Doe");
teacher.setFirstName("Will");
entityManager.persist(teacher);
Course course = new Course("C# 101");
course.setTeacher(teacher);
entityManager.persist(course);
entityManager.flush();
```
Running this code will persist both entities and perserve the relationship between them.

### Cascading Operations
However, we could’ve done another thing — we could’ve cascaded, and thus propagated the persistence of the ```Teacher``` object when we persist the ```Course``` object.

This makes more sense and works the way we’d expect it to like in the first example which threw an exception.

To do this, we’ll modify the ```cascade``` flag of the annotation:
```Java
@ManyToOne(optional = false, cascade = CascadeType.PERSIST)
@JoinColumn(name = "TEACHER_ID", referencedColumnName = "ID")
private Teacher teacher;
```
This way, Hibernate knows to persist the needed object in this relationship as well.

There are multiple types of cascading operations: ```PERSIST```, ```MERGE```, ```REMOVE```, ```REFRESH```, ```DETACH```, and ```ALL``` (that combines all the previous ones).

We can also put the cascade argument on the One-to-Many side of the relationship, so that operations are cascaded from teachers to their courses as well.

### One-to-One
Now that we’ve set up the foundations of relationship mapping in JPA through One-to-Many/Many-to-One relationships and their settings, we can move on to One-to-One relationships.

This time, instead of having a relationship between one entity on one side and a bunch of entities on the other, we’ll have a maximum of one entity on each side.

This is, for example, the relationship between a ```Course``` and its ```CourseMaterial```. Let's first map ```CourseMaterial```, which we haven't done yet:
```Java
@Entity
public class CourseMaterial {
    @Id
    private Long id;
    private String url;
}
```
The annotation for mapping a single entity to a single other entity is, unshockingly, ```@OneToOne```.

Before setting it up in our model, let’s remember that a relationship has an owning side — preferably the side which will hold the foreign key in the database.

In our example, that would be ```CourseMaterial``` as it makes sense that it references a ```Course``` (though we could go the other way around):
```Java
@OneToOne(optional = false)
@JoinColumn(name = "COURSE_ID", referencedColumnName = "ID")
private Course course;
```
There is no point in having material without a course to encompass it. That’s why the relationship is not ```optional``` in that direction.

Speaking of direction, let’s make the relationship bidirectional, so we can access the material of a course if it has one. In the ```Course``` class, let's add:
```Java
@OneToOne(mappedBy = "course")
private CourseMaterial material;
```
Here, we’re telling Hibernate that the material within a ```Course``` is already mapped by the course field of the ```CourseMaterial``` entity.

Also, there’s no optional attribute here as it's ```true``` by default, and we could imagine a course without material (from a very lazy teacher).

In addition to making the relationship bidirectional, we could also add cascading operations or make entities load eagerly or lazily.

### Many-to-Many
Now, last but not least: Many-to-Many relationships. We kept these for the end because they require a bit more work than the previous ones.

Effectively, in a database, a Many-to-Many relationship involves a middle table referencing **both** other tables.

Luckily for us, JPA does most of the work, we just have to throw a few annotations out there, and it handles the rest for us.

So, for our example, the Many-to-Many relationship will be the one between ```Student``` and ```Course``` instances as a student can attend multiple courses, and a course can be followed by multiple students.

In order to map a Many-to-Many relationship we’ll use the ```@ManyToMany``` annotation. However, this time around, we'll also be using a ```@JoinTable``` annotation to set up the table that represents the relationship:
```Java
@ManyToMany
@JoinTable(
    name = "STUDENTS_COURSES",
    joinColumns = @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID"),
    inverseJoinColumns = @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")
)
private List<Student> students;
```
Now, go over what’s going on here. The annotation takes a few parameters. First of all, we must give the table a name. We’ve chosen it to be ```STUDENTS_COURSES```.

After that, we’ll need to tell Hibernate which columns to join in order to populate ```STUDENTS_COURSES```. The first parameter, ```joinColumns``` defines how to configure the join column (foreign key) of the owning side of the relationship in the table. In this case, the owning side is a ```Course```.

On the other hand, the ```inverseJoinColumns``` parameter does the same, but for the referencing side (```Student```).

Let’s set up a data set with students and courses:
```Java
Student johnDoe = new Student();
johnDoe.setFirstName("John");
johnDoe.setLastName("Doe");
johnDoe.setBirthDateAsLocalDate(LocalDate.of(2000, FEBRUARY, 18));
johnDoe.setGender(MALE);
johnDoe.setWantsNewsletter(true);
johnDoe.setAddress(new Address("Baker Street", "221B", "London"));
entityManager.persist(johnDoe);

Student willDoe = new Student();
willDoe.setFirstName("Will");
willDoe.setLastName("Doe");
willDoe.setBirthDateAsLocalDate(LocalDate.of(2001, APRIL, 4));
willDoe.setGender(MALE);
willDoe.setWantsNewsletter(false);
willDoe.setAddress(new Address("Washington Avenue", "23", "Oxford"));
entityManager.persist(willDoe);

Teacher teacher = new Teacher();
teacher.setFirstName("Jane");
teacher.setLastName("Doe");
entityManager.persist(teacher);

Course javaCourse = new Course("Java 101");
javaCourse.setTeacher(teacher);
entityManager.persist(javaCourse);

Course sqlCourse = new Course("SQL 101");
sqlCourse.setTeacher(teacher);
entityManager.persist(sqlCourse);
```
Of course, this won’t work out of the box. We’ll have to add a method that allows us to add students to a course. Let’s modify the ```Course``` class a bit:
```Java
public class Course {
    private List<Student> students = new ArrayList<>();
    public void addStudent(Student student) {
        this.students.add(student);
    }
}
```
Now, we can complete our dataset:

```Java
Course javaCourse = new Course("Java 101");
javaCourse.setTeacher(teacher);
javaCourse.addStudent(johnDoe);
javaCourse.addStudent(willDoe);
entityManager.persist(javaCourse);

Course sqlCourse = new Course("SQL 101");
sqlCourse.setTeacher(teacher);
sqlCourse.addStudent(johnDoe);
entityManager.persist(sqlCourse);
```
Once this code has ran, it’ll persist our ```Course```, ```Teacher``` and ```Student``` instances as well as their relationships. For example, let's retrieve a student from a persisted course and check if everything's fine:

```Java
Course courseWithMultipleStudents = entityManager.find(Course.class, 1L);
assertThat(courseWithMultipleStudents).isNotNull();
assertThat(courseWithMultipleStudents.students()).hasSize(2)
.extracting(Student::firstName)
.containsExactly("John", "Will");
```
Of course, we can still map the relationship as bidirectional the same way we did for the previous relationships.

We can also cascade operations as well as define if entities should load lazily or eagerly (Many-to-Many relationships are lazy by default).