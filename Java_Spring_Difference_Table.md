# Java, Spring & Microservices — 53 "Difference" Tables (Interview Ready)

This file contains 53 separate, interview-ready difference tables. Each section compares a pair or related concepts (Java, Spring, Spring Boot, JPA, Microservices) using a consistent set of feature rows so you can quickly scan differences and use them as flashcards or study notes.

---

## 1. ArrayList vs LinkedList

| Feature                     | ArrayList                                                                 | LinkedList                                                          |
|---------------------------- |-------------------------------------------------------------------------- |---------------------------------------------------------------------|
| **Short description**       | Resizable array-based list                                                | Doubly-linked list implementation of List                           |
| **Internal mechanism**      | Backed by an Object[] (array), resizes by copying                         | Nodes with prev/next references                                      |
| **Ordering / access**       | Maintains insertion order; O(1) random access by index                     | Maintains insertion order; O(n) random access                        |
| **Insertion / removal**     | Fast at end (amortized O(1)), slow in middle (shifts O(n))                | Fast at head/tail and when using iterator (O(1) for node ops)        |
| **Iteration performance**   | Fast due to contiguous memory (better locality)                           | Slightly slower due to pointer hops                                  |
| **Memory overhead**         | Lower (one array, contiguous)                                              | Higher (Node objects + two references per element)                   |
| **When to use**             | Read-heavy, random-access scenarios                                       | Frequent add/remove in middle or head/tail; queues/deques           |
| **Null support / extras**   | Allows null elements                                                       | Allows null elements                                                  |

---

## 2. HashMap vs Hashtable

| Feature                     | HashMap                                                                     | Hashtable                                                              |
|---------------------------- |-----------------------------------------------------------------------------|------------------------------------------------------------------------|
| **Short description**       | Non-synchronized hash-based Map                                              | Legacy synchronized hash-based Map                                     |
| **Internal mechanism**      | Hash table with array + buckets (since Java 8 TreeNodes for bins)           | Hash table with synchronized methods                                   |
| **Thread-safety / concurrency** | Not thread-safe (needs external sync)                                     | Thread-safe (synchronized methods) — coarse-grained                    |
| **Null keys / values**      | Allows one null key and multiple null values                                | Does **not** allow null keys or null values                            |
| **Performance**             | Generally faster in single-threaded usage                                   | Slower due to synchronization overhead                                 |
| **Iteration behavior**      | Fail-fast iterator (ConcurrentModificationException on structural change)   | Fail-fast iterator; thread-safety may give illusions of safety         |
| **Use case**                | Default Map in most apps; single-threaded or externally synchronized        | Legacy code; rarely used in modern apps — prefer ConcurrentHashMap     |

---

## 3. HashMap vs ConcurrentHashMap

| Feature                     | HashMap                                                                     | ConcurrentHashMap                                                       |
|---------------------------- |-----------------------------------------------------------------------------|-------------------------------------------------------------------------|
| **Short description**       | Non-thread-safe hash-based Map                                               | Thread-safe concurrent Map with fine-grained concurrency                |
| **Internal mechanism**      | Array of buckets; Java 8 uses tree bins for high-collision buckets          | CAS/volatile bin-level updates and internal concurrency mechanisms      |
| **Thread-safety / concurrency** | Not safe for concurrent access                                              | Designed for concurrent reads/writes without global locking             |
| **Null keys / values**      | Allows one null key, allows null values                                      | Does **not** allow null keys or null values                             |
| **Performance**             | Fast single-threaded; will fail or behave unpredictably under concurrency    | Scales well under concurrency; lock contention minimized               |
| **Iteration behavior**      | Fail-fast iterator                                                          | Weakly consistent iterator (no CME, may not reflect all updates)        |
| **Use case**                | Use when no concurrent access or external synchronization provided          | Use for multi-threaded caches and maps accessed by many threads         |

---

## 4. TreeMap vs HashMap

| Feature                     | TreeMap                                                                    | HashMap                                                                  |
|---------------------------- |----------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Red-black tree based SortedMap                                               | Hash table based Map (unsorted)                                          |
| **Internal mechanism**      | Red-black self-balancing binary search tree                                  | Array of buckets with linked lists/trees in bins                          |
| **Ordering**                | Sorted by natural order or Comparator                                       | No order (insertion order not guaranteed)                                |
| **Operations complexity**   | O(log n) for get/put                                                         | O(1) average for get/put (O(n) worst-case)                               |
| **Null keys**               | Does not allow null key (Comparator/Natural ordering)                       | Allows one null key                                                        |
| **Use case**                | Need sorted keys, range queries, navigable operations                       | Fast lookup/insert where order isn't important                           |

---

## 5. HashSet vs TreeSet

| Feature                     | HashSet                                                                     | TreeSet                                                                 |
|---------------------------- |------------------------------------------------------------------------------|-------------------------------------------------------------------------|
| **Short description**       | Hash table backed Set                                                        | Red-black tree backed SortedSet                                         |
| **Internal mechanism**      | Uses HashMap internally                                                      | Uses TreeMap internally                                                  |
| **Ordering**                | No guaranteed order                                                          | Sorted order (natural or comparator)                                     |
| **Operations complexity**   | O(1) average for add/contains                                                | O(log n) for add/contains                                                |
| **Null elements**           | Allows one null element                                                      | Allows null only if comparator supports comparison (usually NPE)         |
| **Use case**                | Fast uniqueness checks                                                       | Maintain sorted unique set or range-operations                           |

---

## 6. List vs Set

| Feature                     | List                                                                         | Set                                                                       |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Ordered collection that may contain duplicates                               | Collection that contains no duplicate elements                           |
| **Ordering**                | Preserves order (insertion or index-based)                                   | Some implementations preserve order (LinkedHashSet), some don't          |
| **Duplicates**              | Allows duplicates                                                            | Does not allow duplicates                                                 |
| **Indexing / access**       | Supports index-based access (get(i))                                         | No index-based access                                                      |
| **Common implementations**  | ArrayList, LinkedList                                                         | HashSet, LinkedHashSet, TreeSet                                           |
| **Use case**                | When order and duplicates matter (e.g., logs, history)                       | When uniqueness required (e.g., set of IDs)                               |

---

## 7. Queue vs Deque

| Feature                     | Queue                                                                        | Deque                                                                     |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | FIFO-oriented collection for holding elements before processing              | Double-ended queue supporting insertion/removal at both ends             |
| **Operations**              | offer/poll/peek (head-based)                                                 | addFirst/addLast, removeFirst/removeLast plus queue operations           |
| **Supported behaviors**     | Queue (FIFO) semantics                                                        | FIFO, LIFO (stack-like), and double-ended operations                     |
| **Common implementations**  | LinkedList, ArrayDeque, PriorityQueue                                         | ArrayDeque, LinkedList                                                     |
| **When to use**             | Producer-consumer queues                                                      | When you need both stack and queue behavior or deque semantics           |

---

## 8. BlockingQueue vs ConcurrentLinkedQueue

| Feature                     | BlockingQueue                                                                | ConcurrentLinkedQueue                                                   |
|---------------------------- |------------------------------------------------------------------------------|-------------------------------------------------------------------------|
| **Short description**       | Queue supporting blocking operations (put/take)                               | Non-blocking concurrent FIFO queue                                      |
| **Blocking behavior**       | put() / take() can block when full/empty                                     | Operations return immediately (non-blocking)                            |
| **Concurrency model**       | Thread-safe with internal locking or concurrency mechanisms                  | Lock-free, thread-safe via CAS                                         |
| **Use case**                | Producer-consumer coordination (bounded/unbounded)                           | Low-latency non-blocking queue where blocking/waiting not needed        |
| **Examples**                | ArrayBlockingQueue, LinkedBlockingQueue                                      | ConcurrentLinkedQueue                                                    |

---

## 9. Iterator vs ListIterator

| Feature                     | Iterator                                                                     | ListIterator                                                             |
|---------------------------- |------------------------------------------------------------------------------|-------------------------------------------------------------------------|
| **Short description**       | Simple forward-only iterator for Collections                                  | Bi-directional iterator for Lists with modification capabilities        |
| **Direction**               | Forward-only                                                                  | Forward and backward (previous/hasPrevious)                             |
| **Modification support**    | remove() supported                                                            | add(), set(), remove() supported                                        |
| **Applicable collections**  | Any Collection                                                                | Only List implementations                                               |
| **Index info**              | No index information                                                          | nextIndex() / previousIndex()                                           |

---

## 10. Fail-fast iterator vs Fail-safe iterator

| Feature                     | Fail-fast Iterator                                                          | Fail-safe Iterator                                                      |
|---------------------------- |------------------------------------------------------------------------------|-------------------------------------------------------------------------|
| **Short description**       | Detects structural modification and throws ConcurrentModificationException  | Iterates over a snapshot or weakly consistent view, no CME              |
| **Typical implementations** | ArrayList iterator, HashMap iterator                                         | CopyOnWriteArrayList, ConcurrentHashMap (weakly consistent)             |
| **Consistency guarantee**   | Strong (throws on concurrent structural modification)                        | Weak (may not reflect latest changes or iterates over a snapshot)       |
| **Use case**                | Single-threaded or externally synchronized iteration                         | Multi-threaded iteration where stability is desired                     |

---

## 11. Collections.synchronizedList vs CopyOnWriteArrayList

| Feature                     | Collections.synchronizedList                                               | CopyOnWriteArrayList                                                     |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Wrapper that synchronizes access to an existing List                         | Thread-safe list that copies underlying array on write                   |
| **Concurrency model**       | Coarse-grained synchronization (all access serialized)                       | Copy-on-write: reads are lock-free; writes create a new array             |
| **Performance**             | Good for balanced read/write under external synchronization                   | Excellent for many readers and few writers; writes are expensive         |
| **Iteration semantics**     | Must manually synchronize during iteration to avoid CME                      | Iterators iterate over snapshot; never throw CME                         |
| **Use case**                | When you need a synchronized view and frequent writes/reads                  | Read-heavy scenarios with infrequent writes                              |

---

## 12. String vs StringBuilder vs StringBuffer

| Feature                     | String                                                                       | StringBuilder                                                           |
|---------------------------- |------------------------------------------------------------------------------|-------------------------------------------------------------------------|
| **Short description**       | Immutable sequence of characters                                             | Mutable sequence of characters (not synchronized)                       |
| **Thread-safety**           | Immutable (thread-safe by nature)                                            | Not thread-safe                                                         |
| **Performance (concat)**    | Creating new objects on concat (expensive)                                   | Fast for single-threaded concatenation                                  |
| **Alternative**             | StringBuffer: like StringBuilder but synchronized (thread-safe)              |                                                                         |
| **Use case**                | Use for constants, keys, and when immutability desired                       | Use for building strings in single-threaded contexts                    |

---

## 13. == vs equals()

| Feature                     | == (double equals)                                                           | equals() method                                                         |
|---------------------------- |------------------------------------------------------------------------------|-------------------------------------------------------------------------|
| **Short description**       | Operator checking primitive equality or reference equality for objects      | Method checking logical equality, can be overridden                      |
| **For primitives**          | Compares values directly                                                     | N/A (primitives don't have equals)                                      |
| **For objects**             | Compares references (same instance?)                                         | Compares logical state per implementation                               |
| **Customization**           | Cannot be overridden                                                         | Can override equals() to define equality behavior                       |
| **When to use**             | Use == for primitives or to check if two references are same object         | Use equals() to check value equality (e.g., strings, domain objects)    |

---

## 14. equals() vs hashCode()

| Feature                     | equals()                                                                    | hashCode()                                                               |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Determines logical equality between objects                                  | Returns integer hash used by hash-based collections                       |
| **Contract**                | If equals() true then hashCode() must be same                                 | Equal objects must have equal hashCode; unequal can have collision        |
| **When invoked**            | Called by callers or collections (e.g., List.contains)                       | Called by hash-based collections (HashMap/HashSet)                        |
| **Implementation guidance** | Implement together when overriding                                           | Ensure stable hash and distribute well across buckets                    |

---

## 15. transient vs volatile

| Feature                     | transient                                                                    | volatile                                                                  |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Serialization modifier — field not serialized                                | Concurrency modifier — visibility & ordering for variable                 |
| **Primary purpose**         | Exclude fields from Java serialization                                       | Ensure visibility of changes across threads                              |
| **Persistence effect**      | Field ignored during default serialization                                   | No effect on serialization by itself                                     |
| **Atomicity**               | N/A                                                                          | Guarantees visibility but not atomicity for compound ops                  |
| **Use case**                | Skip derived or sensitive data from serialization                            | Use for flags and simple state shared across threads                      |

---

## 16. final vs finally vs finalize

| Feature                     | final                                                                        | finally                                                                   |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Keyword — for variables, methods, classes (prevents change/override)         | Block — executes after try/catch for cleanup                              |
| **Usage**                   | final int x = 5; final class C {}                                            | try { } finally { }                                                       |
| **Behavior**                | Final prevents reassignment/extension                                        | finally always executes (except System.exit()/crash), used for cleanup    |
| **finalize()**              | N/A                                                                          | finalize() is a method (Object.finalize) — deprecated (GC hook)           |

---

## 17. Checked exception vs Unchecked exception

| Feature                     | Checked Exception                                                           | Unchecked Exception                                                     |
|---------------------------- |------------------------------------------------------------------------------|-------------------------------------------------------------------------|
| **Short description**       | Exceptions that must be declared or handled at compile-time                  | RuntimeException/Error subclasses — not required to be declared         |
| **Examples**                | IOException, SQLException                                                     | NullPointerException, IllegalArgumentException, OutOfMemoryError       |
| **Handling requirement**    | Must be caught or declared with throws                                      | Optional to catch/declare                                                |
| **Use case**                | Represent recoverable conditions and expected error scenarios                | Represent programming errors or unrecoverable conditions                |

---

## 18. throw vs throws

| Feature                     | throw                                                                        | throws                                                                   |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Statement to actually throw an exception instance                            | Method signature clause declaring exceptions a method may throw          |
| **Usage example**           | throw new IllegalArgumentException("x");                                      | void foo() throws IOException { ... }                                     |
| **Effect**                  | Immediate runtime transfer of control to nearest catch                       | Compile-time contract for caller to handle or declare                     |

---

## 19. try-catch-finally vs try-with-resources

| Feature                     | try-catch-finally                                                            | try-with-resources                                                       |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Traditional exception handling with optional finally block                   | Introduced in Java 7; auto-closes resources implementing AutoCloseable  |
| **Resource management**     | Manual close in finally (prone to errors)                                     | Automatic close of declared resources                                   |
| **Exception suppression**   | Harder to manage suppressed exceptions                                       | Automatically manages suppressed exceptions                              |
| **Best practice**           | Use finally if try-with-resources not applicable                              | Prefer try-with-resources for streams, DB connections, etc.              |

---

## 20. Abstract class vs Interface

| Feature                     | Abstract Class                                                               | Interface                                                                |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Class that may contain abstract and concrete members                         | Contract type; since Java 8 can have default/static methods              |
| **State / fields**          | Can have instance fields and constructors                                     | Cannot have instance state (only static/final fields); no constructors   |
| **Multiple inheritance**    | Single inheritance (extends one class)                                        | A class can implement multiple interfaces                                |
| **Use case**                | Share code and state among related classes                                    | Define capability/contract across unrelated classes                      |

---

## 21. Interface default/static methods vs Abstract class methods

| Feature                     | Interface default/static methods                                            | Abstract class methods                                                   |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Allow method bodies in interfaces (since Java 8)                              | Fully implemented methods inside a class                                |
| **State**                   | Interfaces cannot keep instance state                                        | Abstract classes can have instance fields and constructors               |
| **Purpose**                 | Evolve APIs without breaking implementors; share behavior without state      | Share code + state among subclasses                                      |
| **When to use**             | Add small shared behavior across many classes                                | Share heavy implementation details and state among a family of classes   |

---

## 22. Method overloading vs Method overriding

| Feature                     | Method Overloading                                                            | Method Overriding                                                         |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Same method name, different parameter lists in same class                    | Subclass provides specific implementation for superclass method          |
| **Binding**                 | Compile-time (static) binding                                                 | Runtime (dynamic) binding                                                 |
| **Use case**                | Provide convenience methods (different parameters)                             | Provide polymorphic behavior in subclasses                               |

---

## 23. Composition vs Inheritance

| Feature                     | Composition                                                                  | Inheritance                                                               |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | "Has-a" relationship — objects contain other objects                         | "Is-a" relationship — subclass extends parent                             |
| **Coupling**                | Looser coupling, better encapsulation                                         | Tighter coupling between child and parent                                 |
| **When to prefer**          | Favor composition for maintainability and testability                         | Use inheritance for polymorphism or when extending base behavior          |

---

## 24. Encapsulation vs Abstraction

| Feature                     | Encapsulation                                                                | Abstraction                                                               |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Hiding internal state and requiring access via methods                        | Hiding complexity by exposing only relevant features                      |
| **Mechanism**               | Private fields, getters/setters                                               | Abstract classes, interfaces, public APIs                                 |

---

## 25. Static member vs Instance member

| Feature                     | Static Member                                                                | Instance Member                                                           |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Belongs to class; one copy shared across all instances                        | Belongs to individual object instances                                   |
| **Use case**                | Constants, utility methods                                                    | Object state and instance behavior                                       |

---

## 26. Stack memory vs Heap memory

| Feature                     | Stack Memory                                                                 | Heap Memory                                                               |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Thread-local memory storing method frames, local primitives/references       | Shared runtime area for objects and arrays                                |
| **Allocation**              | LIFO automatic allocation/deallocation                                      | Managed by GC, lifetime not LIFO                                          |
| **Errors**                  | StackOverflowError possible                                                   | OutOfMemoryError possible                                                 |

---

## 27. JVM vs JRE vs JDK

| Feature                     | JVM                                                                          | JRE                                                                       |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Java Virtual Machine — executes bytecode                                     | Java Runtime Environment — JVM + core runtime libraries                   |
| **Includes**                | Execution engine, JIT, GC, class loader                                      | JVM + standard libraries                                                  |
| **Developer tools**         | N/A                                                                           | JRE does not include compilers or dev tools                              |
| **JDK**                     | JDK = JRE + development tools (javac, javadoc, debugger)                      | JDK used by developers; JRE for running apps                             |

---

## 28. Process vs Thread

| Feature                     | Process                                                                      | Thread                                                                     |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | OS-level execution unit with separate memory space                           | Lightweight unit of execution within a process                            |
| **Memory isolation**        | Processes have separate address spaces                                       | Threads share process memory (heap)                                       |
| **Creation cost**           | Expensive (higher overhead)                                                   | Cheaper to create and context-switch                                     |

---

## 29. Runnable vs Thread

| Feature                     | Runnable                                                                     | Thread                                                                     |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Functional interface representing a task to run                              | Represents a thread of control; can be subclassed                         |
| **Use with Executors**      | Runnable used with ExecutorService for thread pooling                         | Directly creating Thread less flexible than executor frameworks           |
| **Return/result**           | No result (use Callable for result)                                           | Thread.run() also no direct result                                        |

---

## 30. synchronized (intrinsic lock) vs ReentrantLock

| Feature                     | synchronized                                                                 | ReentrantLock                                                             |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Built-in monitor lock via keyword                                             | java.util.concurrent.locks.Lock implementation                            |
| **Features**                | Simpler syntax, automatic release on scope exit                               | tryLock, lockInterruptibly, fairness, Condition support                   |
| **When to use**             | Prefer synchronized for simple locking                                       | Use ReentrantLock for advanced features                                   |

---

## 31. wait() vs sleep()

| Feature                     | wait()                                                                       | sleep()                                                                   |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Causes current thread to release monitor and wait until notify/notifyAll     | Causes current thread to pause for specified time (doesn't release lock)  |
| **Requires**                | Must be in synchronized block on the object's monitor                         | Can be called from anywhere (static Thread.sleep)                        |

---

## 32. notify() vs notifyAll()

| Feature                     | notify()                                                                     | notifyAll()                                                               |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Wakes up a single thread waiting on the object's monitor                      | Wakes up all threads waiting on the object's monitor                      |
| **Risk**                    | Can cause missed signals if wrong thread is notified                          | Safer in complex conditions (avoids missed notifications)                 |

---

## 33. Future vs CompletableFuture

| Feature                     | Future                                                                       | CompletableFuture                                                        |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Represents result of async computation (blocking get)                         | Extended Future with completion, chaining, and async callbacks           |
| **Non-blocking features**   | Limited — mostly blocking get()                                               | thenApply/thenCompose/handle, non-blocking composition                   |
| **When to use**             | Simple async tasks with ExecutorService                                       | Complex async pipelines and composition                                  |

---

## 34. ExecutorService vs ForkJoinPool

| Feature                     | ExecutorService                                                              | ForkJoinPool                                                              |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | General-purpose thread pool API                                               | Specialized pool for fork/join tasks with work-stealing                   |
| **Task model**              | Runnable/Callable tasks queued and executed                                   | ForkJoinTask (RecursiveAction/RecursiveTask) with splitting tasks         |
| **Best suited**             | Independent tasks, server workloads                                            | Divide-and-conquer parallel algorithms                                    |

---

## 35. ForkJoinPool vs ThreadPoolExecutor

| Feature                     | ForkJoinPool                                                                  | ThreadPoolExecutor                                                       |
|---------------------------- |-------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Work-stealing pool optimized for fork/join tasks                               | General-purpose thread pool with configurable queue and policies         |
| **Task granularity**        | Suited for many small subtasks that can be split                              | Suited for independent tasks, potentially larger and blocking            |

---

## 36. Shallow copy vs Deep copy

| Feature                     | Shallow Copy                                                                  | Deep Copy                                                                  |
|---------------------------- |------------------------------------------------------------------------------|----------------------------------------------------------------------------|
| **Short description**       | Copies top-level object, references to nested objects are shared              | Recursively copies object and all nested objects                           |
| **Mutation behavior**       | Changes to nested objects in one copy reflect in the other                    | Copies independent; changes do not affect other copy                       |

---

## 37. Serialization vs Deserialization

| Feature                     | Serialization                                                                 | Deserialization                                                            |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Converting object to byte stream for persistence/transport                    | Reconstructing object from byte stream                                    |
| **Security concerns**       | Serialized data can be tampered with; avoid deserializing untrusted data      | Deserialization gadgets can cause RCE — validate and use safe formats     |

---

## 38. JDBC Statement vs PreparedStatement

| Feature                     | Statement                                                                     | PreparedStatement                                                         |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Executes static SQL statements                                                 | Precompiled SQL with parameter placeholders                              |
| **SQL injection**           | Vulnerable if concatenating user input                                         | Prevents SQL injection via parameter binding                             |
| **Performance**             | No precompilation; repeated execution less efficient                           | Precompiled on DB side; better for repeated execution                    |

---

## 39. DriverManager vs DataSource

| Feature                     | DriverManager                                                                 | DataSource                                                                 |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Traditional JDBC driver loading and connection factory                        | Preferred factory abstraction for connections (supports pooling/JNDI)     |
| **Connection pooling**      | No pooling built-in (manual or 3rd-party needed)                               | DataSource implementations often support connection pooling              |

---

## 40. JDBC vs ORM (e.g., Hibernate)

| Feature                     | JDBC                                                                          | ORM (Hibernate, JPA)                                                      |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Low-level SQL-based database access                                            | Object-relational mapping abstracting SQL into entities                   |
| **Control vs productivity** | Maximum control over SQL and performance                                       | Higher developer productivity; reduces boilerplate                       |
| **Use case**                | Performance-critical queries, complex custom SQL                              | CRUD-heavy domain models and rapid development                           |

---

## 41. JPA vs Hibernate

| Feature                     | JPA                                                                          | Hibernate                                                                  |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Java Persistence API — specification                                          | Hibernate — popular JPA implementation with extra features                |
| **Nature**                  | API/specification, vendor-neutral                                              | Implementation with extensions (Criteria, HQL, caching)                   |
| **When to choose**          | Code to JPA for portability                                                   | Use Hibernate when you need its specific capabilities                     |

---

## 42. Spring (Framework) vs Spring Boot

| Feature                     | Spring Framework                                                              | Spring Boot                                                               |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Modular application framework providing DI, AOP, data support, MVC, etc.      | Opinionated framework that simplifies Spring setup via auto-configuration|
| **Configuration**           | Requires explicit configuration (XML/Java annotations)                         | Auto-configures based on classpath and starters                          |
| **Use case**                | Building libraries or very custom systems                                      | Rapid application development and microservices                          |

---

## 43. Spring MVC vs Spring WebFlux

| Feature                     | Spring MVC                                                                    | Spring WebFlux                                                            |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Servlet-based, blocking I/O web framework                                      | Reactive, non-blocking web framework (Reactor/Flux)                       |
| **I/O model**               | Thread-per-request (blocking)                                                  | Event-loop / non-blocking (async backpressure capable)                    |
| **When to use**             | Typical request/response apps                                                  | High concurrency, streaming, or backpressure-sensitive services          |

---

## 44. Bean scopes: singleton vs prototype

| Feature                     | singleton (Spring)                                                            | prototype (Spring)                                                       |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | One shared bean instance per Spring IoC container                             | New instance created for each request to container                       |
| **Lifecycle management**    | Fully managed by container (creation, post-processors, destruction callbacks) | Container creates but does not manage complete lifecycle (no destroy)     |
| **Use case**                | Services, repositories, controllers (stateless)                               | Stateful helpers, form backing objects, or per-request data               |

---

## 45. BeanPostProcessor vs BeanFactoryPostProcessor

| Feature                     | BeanPostProcessor                                                              | BeanFactoryPostProcessor                                                   |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Post-process bean instances after creation (instance-level)                   | Modify bean definitions before beans are instantiated (metadata-level)    |
| **When executed**           | After bean instantiation and dependency injection                              | During container startup, before beans are created                        |

---

## 46. Constructor injection vs Setter injection

| Feature                     | Constructor Injection                                                         | Setter Injection                                                          |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Dependencies provided through constructor                                    | Dependencies provided via setter methods                                  |
| **Immutability**            | Supports final fields; enforces required dependencies at creation             | Allows optional dependencies; object mutable after construction           |
| **When to use**             | Prefer for mandatory dependencies                                             | Use for optional or configurable dependencies                            |

---

## 47. Field injection vs Constructor injection

| Feature                     | Field Injection                                                               | Constructor Injection                                                     |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | @Autowired on fields (reflection-based)                                       | Dependencies passed via constructor                                      |
| **Testability**             | Harder to unit test (need reflection or container)                            | Easier to test, explicit dependencies                                     |
| **Immutability**            | Cannot use final fields easily                                                 | Allows final fields, better immutability                                  |

---

## 48. @Autowired vs @Inject/@Resource

| Feature                     | @Autowired (Spring)                                                           | @Inject (JSR-330) / @Resource (JSR-250)                                    |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Spring-specific DI annotation                                                  | Standard Java DI annotations (@Inject) and resource injection (@Resource) |
| **Resolution strategy**     | ByType by default; supports @Qualifier and required=false                     | @Inject similar to @Autowired; @Resource resolves by name first           |
| **Portability**             | Spring-specific                                                                 | @Inject/@Resource are standard — more portable across DI containers       |

---

## 49. @Controller vs @RestController

| Feature                     | @Controller                                                                   | @RestController                                                           |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | MVC controller that typically returns view names                              | Specialized controller that returns response bodies (JSON/XML)           |
| **Stereotype**              | @Controller + view resolution                                                  | @Controller + @ResponseBody on every method                              |
| **When to use**             | Server-side rendered web pages (Thymeleaf/JSP)                                 | RESTful APIs returning JSON/XML                                         |

---

## 50. Spring AOP (proxy-based) vs AspectJ

| Feature                     | Spring AOP (proxy-based)                                                      | AspectJ (compile/load-time weaving)                                       |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Runtime proxy-based AOP limited to Spring-managed beans and method-level join points | Full-fledged AOP with bytecode weaving, supports many more join points    |
| **Weaving time**            | Proxy creation at runtime                                                      | Compile-time or load-time weaving                                         |
| **Join points**             | Method execution join points on Spring beans                                   | Method execution, field access, constructors, etc.                        |
| **When to use**             | Common cross-cutting concerns (transactions, logging)                          | Use AspectJ for advanced aspects or weaving into classes outside Spring   |

---

## 51. Microservice Architecture vs Monolithic Architecture

| Feature                     | Microservice Architecture                                                    | Monolithic Architecture                                                  |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Application split into small, independently deployable services             | Single unified application containing all functionality                  |
| **Deployment**              | Independent deployment per service                                           | Single deployment artifact                                                |
| **Scalability**             | Scale services independently (per-service scaling)                            | Scale entire application as a whole                                      |
| **Fault isolation**         | Faults can be contained to a service                                         | Fault in one module can affect whole app                                 |
| **Complexity**              | Higher operational complexity (service discovery, distributed tracing)      | Lower operational complexity                                              |
| **Data management**         | Decentralized data per service recommended                                   | Single shared database easier to manage                                   |
| **Development velocity**    | Teams can work independently, polyglot stacks possible                       | Easier to develop initially, harder to scale team autonomy                |
| **Testing**                 | Requires integration testing, contract testing between services             | Easier local end-to-end testing                                           |
| **Latency & performance**   | Network calls introduce latency                                              | In-process calls are faster                                               |
| **Use case**                | Large, complex systems needing independent scaling and team autonomy        | Small to medium apps, MVPs, or when low operational overhead desired      |

---

## 52. JPA vs JDBC

| Feature                     | JPA (Java Persistence API)                                                   | JDBC                                                                      |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | High-level ORM specification mapping Java objects to DB tables               | Low-level SQL API interacting with database via SQL                      |
| **Abstraction**             | Object-relational mapping, entity lifecycle, caching                         | Direct SQL execution, ResultSet mapping handled manually                 |
| **Boilerplate**             | Reduces boilerplate (CRUD via EntityManager/repositories)                    | More boilerplate (prepare statements, map ResultSet to objects)         |
| **Control / performance**   | May hide SQL complexity and lead to N+1 queries unless tuned                | Full control over SQL and optimizations                                  |
| **Transactions**            | Integrated with EntityManager and JTA                                          | Transactions via Connection, manual control                              |
| **Use case**                | Domain-driven apps with CRUD and relationships                               | Performance-critical queries or complex SQL requiring fine tuning        |
| **Portability**             | Portable across JPA providers                                                | Portable but low-level SQL may vary across DBs                           |

---

## 53. Spring Boot vs Spring Cloud

| Feature                     | Spring Boot                                                                  | Spring Cloud                                                               |
|---------------------------- |------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| **Short description**       | Opinionated framework to create Spring applications quickly                 | Collection of tools to build distributed systems on top of Spring Boot     |
| **Primary focus**           | Auto-configuration, starters, embedded servers, runnable jars               | Service discovery, configuration management, circuit breakers, routing    |
| **Typical components**      | spring-boot-starter-web, spring-boot-starter-data-jpa                       | Netflix OSS (Eureka, Hystrix), Config Server, Gateway, Sleuth, Zipkin     |
| **When to use**             | Bootstrapping Spring applications and microservices                          | When building/resiliency/observability/deployment aspects in distributed apps |
| **Dependency**              | Base for application setup and core features                                 | Builds on Spring Boot; provides distributed system patterns                |
| **Use case**                | Startups, microservices, simple REST services                                | Large microservice ecosystems needing discovery, config, routing, tracing |

---

