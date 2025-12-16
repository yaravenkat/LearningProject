| Type           | What it Does                                         | When to Use                                           |
| -------------- | ---------------------------------------------------- | ----------------------------------------------------- |
| **Creational** | Controls **how objects are created**                 | When object creation is complex or must be controlled |
| **Structural** | Defines **how classes/objects are connected**        | When integrating or simplifying system structure      |
| **Behavioral** | Manages **communication & behavior** between objects | When behavior varies at runtime                       |

| Pattern              | What it Does                 | When to Use              | Key Benefit       | Real-World Example |
| -------------------- | ---------------------------- | ------------------------ | ----------------- | ------------------ |
| **Singleton**        | Ensures single instance      | Shared resources         | Controlled access | Logger, Config     |
| **Factory Method**   | Delegates object creation    | Multiple implementations | Loose coupling    | Payment factory    |
| **Abstract Factory** | Creates families of objects  | Product families         | Consistency       | UI themes          |
| **Builder**          | Step-by-step object creation | Complex objects          | Readability       | `StringBuilder`    |
| **Prototype**        | Clone existing objects       | Costly creation          | Performance       | Object cloning     |


| Pattern       | What it Does              | When to Use           | Key Benefit       | Real-World Example |
| ------------- | ------------------------- | --------------------- | ----------------- | ------------------ |
| **Adapter**   | Converts interface        | Legacy integration    | Compatibility     | Power adapter      |
| **Bridge**    | Separates abstraction     | Independent changes   | Flexibility       | Shape + Color      |
| **Composite** | Tree structure            | Hierarchies           | Uniform treatment | File system        |
| **Decorator** | Adds behavior dynamically | Feature extension     | OCP compliant     | Java IO            |
| **Facade**    | Simplifies subsystem      | Reduce complexity     | Easy usage        | Service facade     |
| **Flyweight** | Shares objects            | Memory optimization   | Performance       | String pool        |
| **Proxy**     | Controls access           | Security/lazy loading | Control           | Hibernate proxy    |


| Pattern                     | What it Does                             | When to Use                      | Key Benefit                    | Real-World Example                   |
| --------------------------- | ---------------------------------------- | -------------------------------- | ------------------------------ | ------------------------------------ |
| **Strategy**                | Encapsulates interchangeable algorithms  | Behavior varies at runtime       | Removes `if–else`, follows OCP | Payment methods (Card/UPI)           |
| **Observer**                | Notifies dependent objects automatically | Event-driven updates             | Loose coupling                 | UI updates, Kafka consumers          |
| **Command**                 | Encapsulates request as object           | Undo/redo, request queues        | Decouples sender & receiver    | Button click, job queue              |
| **Chain of Responsibility** | Passes request along handler chain       | Multiple processors              | Flexible flow                  | Servlet filters, Spring interceptors |
| **Template Method**         | Defines algorithm skeleton               | Fixed process steps              | Code reuse                     | JDBC template                        |
| **State**                   | Changes behavior based on state          | State-dependent logic            | Removes state `if–else`        | Order lifecycle                      |
| **Iterator**                | Sequential access to collection          | Hide internal structure          | Uniform traversal              | Java Iterator                        |
| **Mediator**                | Centralizes object interaction           | Complex object communication     | Reduced coupling               | Chat application                     |
| **Memento**                 | Saves & restores object state            | Undo functionality               | Encapsulation preserved        | Editor undo                          |
| **Visitor**                 | Adds behavior without modifying objects  | Operations over object structure | Open/Closed principle          | Report generation                    |
| **Interpreter**             | Defines grammar & interpretation         | Rule or expression parsing       | Flexible grammar               | Regex engine                         |

Java 8 

| Feature               | What it Does (Detailed)                                | Why It Matters                 | Real-World Usage    |
| --------------------- | ------------------------------------------------------ | ------------------------------ | ------------------- |
| Lambda Expressions    | Enables inline implementation of functional interfaces | Removes boilerplate            | Sorting, filtering  |
| Streams API           | Declarative data processing pipeline                   | Readable & parallel processing | Data analytics      |
| Optional              | Wrapper to represent optional value                    | Avoids NullPointerException    | API responses       |
| Default Methods       | Allows adding methods to interfaces                    | Backward compatibility         | API evolution       |
| Functional Interfaces | Single abstract method interfaces                      | Lambda compatibility           | Predicate, Function |
| Date & Time API       | Immutable, thread-safe date/time                       | Fixes legacy Date issues       | Financial apps      |


Java 11

| Feature                | What it Does (Detailed)       | Why It Matters       | Real-World Usage     |
| ---------------------- | ----------------------------- | -------------------- | -------------------- |
| `var` in Lambdas       | Type inference inside lambdas | Cleaner syntax       | Functional pipelines |
| New String APIs        | Utility methods for strings   | Less custom code     | Input validation     |
| Files API Enhancements | Read/write files easily       | Reduced boilerplate  | Config loading       |
| HTTP Client            | Standard HTTP client          | Replaces Apache HTTP | REST calls           |
| Java EE Removal        | Removed unused modules        | Smaller JDK          | Microservices        |
| Flight Recorder        | JVM performance monitoring    | Production debugging | Ops monitoring       |

Java 17

| Feature                       | What it Does (Detailed)          | Why It Matters     | Real-World Usage   |
| ----------------------------- | -------------------------------- | ------------------ | ------------------ |
| Records                       | Auto-generated immutable classes | DTO simplification | REST APIs          |
| Sealed Classes                | Restricts subclassing            | Domain safety      | Business rules     |
| Pattern Matching `instanceof` | Combines type check & cast       | Cleaner logic      | Condition handling |
| Switch Expressions            | Switch returns value             | Less error-prone   | Decision logic     |
| Text Blocks                   | Multi-line string literals       | Clean JSON/SQL     | Queries, configs   |
| Enhanced Random API           | Pluggable random generators      | Better performance | Simulations        |

Java 21 

| Feature                   | What it Does (Detailed)            | Why It Matters       | Real-World Usage   |
| ------------------------- | ---------------------------------- | -------------------- | ------------------ |
| **Virtual Threads**       | Lightweight threads managed by JVM | Massive concurrency  | High-load APIs     |
| Pattern Matching `switch` | Type-safe switch logic             | Eliminates casting   | Command routing    |
| Record Patterns           | Deconstruct record values          | Clean extraction     | DTO handling       |
| Sequenced Collections     | Unified ordered collection APIs    | Consistency          | Ordered data       |
| Scoped Values             | Safe contextual data sharing       | Replaces ThreadLocal | Security context   |
| Foreign Function API      | Safe native memory access          | JNI alternative      | System integration |
| String Templates ⚠️       | Inline expression in strings       | Readability          | Logging            |
| Unnamed Classes ⚠️        | No class boilerplate               | Fast prototyping     | Scripts            |


