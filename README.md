# Official style guides

* https://developer.android.com/kotlin/style-guide
* https://kotlinlang.org/docs/reference/coding-conventions.html

# Iterable

## The condition flag can be declared inside a do while loop.

```kotlin
do {
    var cont = true // The condition flag can be declared inside a do while loop!
    if (Random.nextBoolean()) cont = false
} while (cont)
```

## How to iterate through an Iterable with index

```kotlin
val xs: List<Char> = listOf('a', 'b', 'c')
for ((i, x) in xs.withIndex()) println("$i $x")

// Alternatively
xs.forEachIndexed { i, x -> println("$i $x") }

// Alternatively
xs.mapIndexed { i, x -> "$i $x" }.forEach(::println)
```

## How to filter an Iterable by index
```kotlin
val xs: List<Char> = listOf('a', 'b', 'c').filterIndexed { i, _ -> i != 1 } // [a, c]
```

# Array

## Type mapping between Kotlin and Java

Kotlin|Java
---|---
IntArray|int[]
Array\<Int>|Integer[]

## How to create an IntArray

```kotlin
val xs: IntArray = intArrayOf(1, 2, 3) // [1, 2, 3]
val ys: IntArray = IntArray(3) // [0, 0, 0]
val zs: IntArray = IntArray(3) { it * 2 } // [0, 2, 4]
```

## How to create an Array\<T>

```kotlin
val xs: Array<Int> = arrayOf(1, 2, 3) // [1, 2, 3]

// The lambda is not optional.
val ys: Array<Int> = Array(3) { it } // [0, 1, 2]
val zs: Array<String> = Array(3) { "item$it" } // ["item0", "item1", "item2"]
```

## How to convert an IntArray an to Array\<Int>

```kotlin
val xs: IntArray = intArrayOf(1, 2, 3)
val ys: Array<Int> = xs.toTypedArray()
```

## How to convert an Array\<Int> to an IntArray

```kotlin
val xs: Array<Int> = arrayOf(1, 2, 3)
val ys: IntArray = xs.toIntArray()
```

## How to iterate through an IntArray in reverse order

```kotlin
val xs: IntArray = intArrayOf(1, 2, 3)
for (i in xs.lastIndex downTo 0) println(xs[i]) // 3 2 1
```

## How to iterate through an Array\<T> in reverse order

```kotlin
val xs: Array<String> = arrayOf("a", "b", "c")
for (i in xs.lastIndex downTo 0) println(xs[i]) // c b a
```

## How to deep-copy an IntArray

```kotlin
val xs: IntArray = intArrayOf(1, 2, 3)
val ys: IntArray = xs.clone()
```

## How to deep-copy an Array\<T>

```kotlin
val xs: Array<String> = arrayOf("a", "b", "c")
val ys: Array<String> = xs.clone()
```

# List

## How to create a List

```kotlin
val a: List<Int> = emptyList() // equivalent of listOf(). [].
val b: List<Int> = listOf(1, 2, 3) // [1, 2, 3]
val c: List<Int> = listOfNotNull(1, null, 2) // [1, 2]
val d: List<Int> = List(3) { it } // The lambda is not optional. [0, 1, 2]
val e: List<String> = List(3) { "item$it" } // ["item0", "item1", "item2"]
```

## How to get the size of a List

```kotlin
val xs: List<Int> = listOf(1, 2, 3)
val size: Int = xs.size // 3. simpler than xs.count().
```

## How to iterate through a List in reverse order

```kotlin
val xs = listOf("a", "b", "c")
for (i in xs.lastIndex downTo 0) println(i) // 2 1 0
```

## How to add/remove an element to/from a List

```kotlin
 val xs: List<Int> = listOf(1, 2, 3)
val result1: List<Int> = xs.plus(4) // [1, 2, 3, 4]
val result2: List<Int> = xs.minus(2) // [1, 3]
```

## How to use `onEach` and `forEach`

```kotlin
listOf('a', 'b', 'c')
    .onEach { println(it) } // a b c
    .map(Char::toUpperCase)
    .forEach { println(it) } // A B C
```

## How to filter a List

```kotlin
val xs: List<Int> = listOf(1, 2, 3)
val result1: List<Int> = xs.filter { it == 2 } // [2]
val result2: List<Int> = xs.filterNot { it == 2 } // [1, 3]
val ys: List<Int?> = listOf(1, null, 2)
val result3: List<Int> = ys.filterNotNull() // [1, 2]
val result4: List<Int> = ys.mapNotNull { it.takeIf { it == 2 } } // [2]
```

## ifEmpty() / takeIf() / takeUnless()

```kotlin
val x: List<Int> = emptyList<Int>().ifEmpty { listOf(42) } // 42
val y: List<Int>? = listOf(1, 2, 3).takeIf { 0 in it } // null
val z: List<Int>? = listOf(1, 2, 3).takeUnless { 1 in it } // null
```

## How to work with two List(s)

```kotlin
val xs: List<Int> = listOf(1, 2, 3)
val ys: List<Int> = listOf(2, 3, 4)
val result1: List<Int> = xs + ys // [1, 2, 3, 2, 3, 4]
val result2: Set<Int> = xs union ys // [1, 2, 3, 4]
val result3: Set<Int> = xs intersect ys // [2, 3]
val result4: Set<Int> = xs subtract ys // [1]
```

## How to sort a List

```kotlin
data class Sample(val name: String)
```

```kotlin
val xs: List<Int> = listOf(1, 3, 2).sorted() // [1, 2, 3]
val ys: List<Int> = listOf(1, 3, 2).sortedDescending() // [3, 2, 1]
val zs: List<Sample> = listOf(
    Sample("c"),
    Sample("b"),
    Sample("a")
).sortedBy { it.name } // [Sample(name=a), Sample(name=b), Sample(name=c)]
```

## How to count the appearance of elements

```kotlin
val occurrences: Map<String, Int> =
    listOf("b", "c", "b", "c", "a", "c").groupingBy { it }.eachCount() // {a=1, b=2, c=3}
```

## How to convert a List? to a List

```kotlin
val xs: List<Int>? = null
val ys: List<Int> = x.orEmpty()
```

## How to convert a List to a Map

```kotlin
val xs: List<Char> = listOf('a', 'b', 'c')
val result1: Map<Char, Int> = xs.withIndex().associate { it.value to it.index } // {a=0, b=1, c=2}

// FYI, mapIndexed() returns a List instead of a Map.
// val result1: List<Pair<Char, Int>> = xs.mapIndexed { i, x -> x to i } // [(a, 0), (b, 1), (c, 2)]

val result2: Map<Char, Char> = xs.associateWith { it.toUpperCase() } // {a=A, b=B, c=C}
val result3: Map<Char, Char> = xs.associateBy { it.toUpperCase() } // {A=a, B=b, C=c}

val ys: List<String> = listOf("fruit" to "apple", "fruit" to "orange", "vegetable" to "carrot", "vegetable" to "potato")
val result4: Map<String, List<String>> =
    ys.groupBy({ it.first }, { it.second }) // {fruit=[apple, orange], vegetable=[carrot, potato]}

val zs: List<String> = listOf("key1", "key2", "key1", "key2")
val result5: Map<String, List<Int>> =
    zs.mapIndexed { i, x -> x to i }.groupBy({ it.first }, { it.second }) // {key1=[0, 2], key2=[1, 3]}
```

## How to convert two List(s) to a List

```kotlin
val xs: List<Int> = listOf(1, 2, 3)
val ys: List<String> = listOf("a", "b", "c")
val zs: List<String> = xs.zip(ys).map { (x, y) ->
    x.toString() + y
} // [1a, 2b, 3c]
```

## How to convert two List(s) to a Map

```kotlin
val xs: List<Int> = listOf(1, 2, 3)
val ys: List<String> = listOf("a", "b", "c")
val zs: Map<Int, String> = xs.zip(ys).toMap() // {1=a, 2=b, 3=c}
```

## How to convert a List to a Pair

```kotlin
val pair: Pair<String, String> = listOf("a", "b", "c").zipWithNext().first() // (a, b)
```

## How to deep-copy a MutableList

```kotlin
val xs: MutableList<String> = mutableListOf("a", "b")
val ys: MutableList<String> = xs.toMutableList()
ys[1] = "c"
println(xs) // [a, b]
println(ys) // [a, c]
```

## How to destruct a List to values

```kotlin
val (a, b) = listOf("a", "b", "c")
```

# Set

## How to create a Set

```kotlin
val set1: Set<String> = setOf("a", "b", "b") // [a, b]
val set2: Set<String> = setOfNotNull(null, "a", "b", "b", null) // [a, b]
```

## How to convert a List to a SortedSet

```kotlin
fun main() {
    val xs: SortedSet<String> = listOf("c", "b", "a").toSortedSet() // [a, b, c]

    val ys = sortedSetOf(
        compareBy { it.order },
        Sample("a", 3),
        Sample("b", 2),
        Sample("c", 1)
    ) // [(c,1), (b,2), (a,3)]

    val zs: SortedSet<Sample> = listOf(
        Sample("a", 3),
        Sample("b", 2),
        Sample("c", 1)
    ).toSortedSet(compareBy { it.order }) // [(c,1), (b,2), (a,3)]
}

class Sample(private val name: String, val order: Int) {
    override fun toString(): String {
        return "($name,$order)"
    }
}
```

# Map

## How to create a Map

```kotlin
val map: Map<String, String> = mapOf("a" to "x", "b" to "y", "c" to "z").withDefault { "default" }
val result1: Set<Map.Entry<String, String>> = map.entries // [a=x, b=y, c=z]
val result2: Set<String> = map.keys // [a, b, c]
val result3: Collection<String> = map.values // [x, y, z]
val result4: String? = map["d"] // null
val result5: String = map.getValue("d") // "default"
val result6: String? = map.getOrDefault("d", "not found") // not found
val result7: String? = map.getOrElse("d") { "not found" } // not found
```

## How to map a Map

```kotlin
val map: Map<Char, Int> = mapOf('a' to 10, 'b' to 20)
val result1: Map<Char, Int> = map.mapKeys { it.key.toUpperCase() } // {A=10, B=20}
val result2: Map<Char, Int> = map.mapValues { it.value * 10 } // {a=100, b=200}
val result3: Map<Char, Int> = map.map { it.key.toUpperCase() to it.value * 10 }.toMap() // {A=100, B=200}
```

## How to filter a Map

```kotlin
val map: Map<Char, Char> = mapOf('A' to 'a', 'B' to 'b')
val result1: Map<Char, Char> = map.filterKeys { it == 'A' } // {A=a}
val result2: Map<Char, Char> = map.filterValues { it == 'b' } // {B=b}
val result3: Map<Char, Char> = map.filter { it.key == 'B' && it.value == 'b' } // {B=b}
```

## How to sort a Map

```kotlin
val map: Map<Char, Char> = mapOf('A' to 'a', 'C' to 'c', 'B' to 'b')
val sorted: Map<Char, Char> = map.toSortedMap() // {A=a, B=b, C=c}
val sortedDescending: Map<Char, Char> = map.toSortedMap(reverseOrder()) // {C=c, B=b, A=a}
```

## How to sort a Map by value

```kotlin
val map: Map<Char, Int> = mapOf('a' to 2, 'b' to 1, 'c' to 3)
val sorted: Map<Char, Int> = map.toList().sortedBy { it.second }.toMap() // {b=1, a=2, c=3}
val sortedDescending: Map<Char, Int> = map.toList().sortedByDescending { it.second }.toMap() // {c=3, a=2, b=1}
```

## How to flatten a list of Map(s)

```kotlin
val map1: Map<Char, Char> = mapOf('A' to 'a')
val map2: Map<Char, Char> = mapOf('B' to 'b')
val maps: List<Map<Char, Char>> = listOf(map1, map2)
val map: Map<Char, Char> = maps.reduce { acc, x -> acc + x } // {A=a, B=b}
```

# String

## How to convert a String? to a String

```kotlin
val x: String? = null
val y: String = x.orEmpty()
```

## isBlank() / isEmpty() / ifBlank() / ifEmpty()

```kotlin
val a: Boolean = " ".isBlank() // true
val b: Boolean = "".isEmpty() // true
val c: String = " ".ifBlank { "default" } // "default"
val d: String = "".ifEmpty { "default" } // "default"
```

## How to get a substring

```kotlin
val s1: String = "a/b/c".substringBefore("/") // a
val s2: String = "a/b/c".substringAfter("/") // b/c
val s3: String = "a/b/c".substringBeforeLast("/") // a/b
val s4: String = "a/b/c".substringAfterLast("/") // c
```

## How to remove a substring

```kotlin
val s1: String = "abc".removePrefix("a") // bc
val s2: String = "abc".removeSuffix("c") // ab
val s3: String = "aaa".removeSurrounding("a") // a
val s4: String = "abc".removeSurrounding("a", "c") // b
```

## How to use joinToString(...)

```kotlin
val xs: String = listOf("a", "b", "c").joinToString(
    separator = ";",
    prefix = "{",
    postfix = "}",
    limit = 2,
    truncated = "..."
) {
    it.toUpperCase()
} // "{A;B;...}
```

## How to append strings

```kotlin
val s: String = buildString {
    appendLine("aaa")
    appendLine("bbb")
}
```

# Enum
## Enum without a value
```kotlin
enum class Fruit {
    APPLE, ORANGE, UNKNOWN;

    companion object {
        // Verbose for illustrative purposes
        fun fromOrdinal(ordinal: Int): Fruit = values()[ordinal]
        fun fromOrdinalOrNull(ordinal: Int): Fruit? = values().getOrNull(ordinal)
        fun fromOrdinalOrDefault(ordinal: Int): Fruit = values().getOrElse(ordinal) { UNKNOWN }
        fun fromName(name: String): Fruit = valueOf(name)
        fun fromNameIgnoreCase(name: String): Fruit = valueOf(name.uppercase())
        fun fromNameOrNull(name: String): Fruit? = values().find { it.name == name }
        fun fromNameIgnoreCaseOrNull(name: String): Fruit? = values().find { it.name == name.uppercase() }
    }
}
```

## Enum with a value
```kotlin
enum class MyColor(val value: Int) {
    BLACK(0x000000), WHITE(0xffffff), UNKNOWN(Int.MIN_VALUE);

    companion object {
        // Verbose for illustrative purposes
        fun fromOrdinal(ordinal: Int): MyColor = values()[ordinal]
        fun fromOrdinalOrNull(ordinal: Int): MyColor? = values().getOrNull(ordinal)
        fun fromOrdinalOrDefault(ordinal: Int): MyColor = values().getOrElse(ordinal) { UNKNOWN }
        fun fromName(name: String): MyColor = valueOf(name)
        fun fromNameIgnoreCase(name: String): MyColor = valueOf(name.uppercase())
        fun fromNameOrNull(name: String): MyColor? = values().find { it.name == name }
        fun fromNameIgnoreCaseOrNull(name: String): MyColor? = values().find { it.name == name.uppercase() }
        fun fromValue(value: Int): MyColor = values().first { it.value == value }
        fun fromValueOrNull(value: Int): MyColor? = values().find { it.value == value }
    }
}
```

## Enum with properties and functions
```kotlin
enum class Fruit {
    APPLE {
        override fun toString() = "🍎"
        override val producer: String = "👨‍🌾"
        override fun printSimilarFruit() {
            println("🍏")
        }
    },
    ORANGE {
        override fun toString() = "🍊"
        override val producer: String = "👩‍🌾"
        override fun printSimilarFruit() {
            println("🍋")
        }
    };

    abstract val producer: String
    abstract fun printSimilarFruit()
}

println(apple) // 🍎
println(apple.producer) // 👨‍🌾
apple.printSimilarFruit() // 🍏
```

# Function references / Constructor references
```kotlin
fun main() {
    val xs: List<Int> = listOf(1, 2)

    // Function reference
    val result1: List<Int> = xs.filter(::isOdd) // [true, false]

    // Qualified function reference
    val result2: List<String> = xs.map(Int::toString) // ["1", "2"]

    // Companion object function reference
    val result3: List<Int> = xs.map((Sample)::double) // [2, 4]
    val result4: List<Int> = xs.map(Sample.Companion::double) // [2, 4]

    // Constructor reference
    val result5: List<Sample> = xs.map(::Sample) // [Sample(1), Sample(2)]
}

fun isOdd(x: Int): Boolean = x % 2 != 0

data class Sample(val x: Int) {
    companion object {
        fun double(x: Int): Int {
            return x * 2
        }
    }
}
```

# Sealed interface
```kotlin
sealed interface Shape {
    val description: String
    val print: () -> Unit
}

data class Circle(
    val radius: Int,
    override val description: String,
    override val print: () -> Unit
) : Shape

data class Square(
    val perimeter: Int,
    override val description: String,
    override val print: () -> Unit
) : Shape

fun main() {
    val circle = Circle(1, "circle!") { println("circle!") }
    val square = Square(1, "square!") { println("square!") }
    println(circle) // Circle(radius=1, description=circle!, print=() -> kotlin.Unit)
    circle.print() // circle!
    println(square) // Square(perimeter=1, description=square!, print=() -> kotlin.Unit)
    square.print() // square!
}
```

# Sealed class
```kotlin
sealed class Shape(open val description: String, open val print: () -> Unit) {
    val commonDescription: String = "common!"
    val printCommon: () -> Unit = { println("common!") }
}

data class Circle(
    val radius: Int,
    override val description: String,
    override val print: () -> Unit
) : Shape("circle!", print)

data class Square(
    val perimeter: Int,
    override val description: String,
    override val print: () -> Unit
) : Shape("square!", print)

fun main() {
    val circle = Circle(1, "circle!") { println("circle!") }
    val square = Square(1, "square!") { println("square!") }
    println(circle) // Circle(radius=1, description=circle!, print=() -> kotlin.Unit)
    println(circle.commonDescription) // common!
    circle.printCommon() // common!
    circle.print() // circle!
    println(square) // Square(perimeter=1, description=square!, print=() -> kotlin.Unit)
    println(square.commonDescription) // common!
    square.printCommon() // common!
    square.print() // square!
}
```

# `<reified T>` (Reified type parameters)

enables you to use `is` and `as` on `T`.

```kotlin
inline fun <reified T> f(a: Any) {
    if (a is T) {
        // Do something
    }
    val t = a as T
}
```

https://kotlinlang.org/docs/reference/inline-functions.html#reified-type-parameters

# Delegate

## How to use Delegates.observable(...)

```kotlin
// The type can be omitted.
var observed: String by Delegates.observable("a") { _, old, new ->
    if (old == new) return@observable // In case you only want to react to the value change.
    println("$old -> $new")
}
println(observed) // "a"
observed = "b" // "a -> b"
observed = "b" // (none)
observed = "c" // "b -> c"
```

## How to use Delegates.vetoable(...)

```kotlin
// The type can be omitted.
var evenNumber: Int by Delegates.vetoable(1) { _, old, new ->
    new % 2 == 0
}

println(evenNumber) // 1. Though 1 is not an even number, the initial value is not validated.
evenNumber = 3 // vetoed because 3 is not a even number.
println(evenNumber) // 1
evenNumber = 2
println(evenNumber) // 2
```

## Comparison among `lazy`, `Delegates.notNull()`, and `lateinit`

&nbsp;|lazy|Delegates.notNull()|lateinit
--|--|--|--
val|supported|not supported|not supported
non-nullable|supported|supported|not supported primitive types|supported|supported|not supported performance|ok(※1)|ok(
※1)|good Dagger|supported|supported(※2)|supported

* (※1) A delegate object is created.
* (※2) Dagger's field injection is not supported but Dagger's method (setter) injection is.

# Regex

Construct|Matches|Greedy or reluctant
--|--|--
x?|0,1|greedy x??|0,1|reluctant x*|0+|greedy x*?|0+|reluctant
x+|1+|greedy x+?|1+|reluctant

Kotlin's regex pattern syntax is the same as Java's [Pattern class](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/util/regex/Pattern.html).

# How to print the name of the current function

```kotlin
fun sample() {
    println(object {}.javaClass.enclosingMethod?.name) // sample
}
```

# How to print when a property is accessed
```kotlin
var x = ""
    get() {
        println("${object {}.javaClass.enclosingMethod?.name}: $field")
        return field
    }
    set(value) {
        println("${object {}.javaClass.enclosingMethod?.name}: $value")
        field = value
    }
```

# How to get the name of the current thread

```kotlin
val name: String = Thread.currentThread().name
```

* The preceding code also shows the name of the current coroutine after the following preparation:
    * IntelliJ IDEA > Menu bar > Run > Edit Configurations > Configurations > VM options >
      add `-Dkotlinx.coroutines.debug`.

# Syntactic sugar for throwing exceptions

Function|Throws
--|--
check(Boolean)|IllegalStateException
checkNotNull(T?)|IllegalStateException
error(Any)|IllegalStateException
require(Boolean)|IllegalArgumentException
requireNotNull(T?)|IllegalArgumentException

# `fold` versus `reduce`

&nbsp;|Type of initial value|Type of element of input array|Return type
--|--|--|--
fold|R|T|R
reduce|n/a|T|T or one of T's ancestors

* https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/fold.html
* https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/reduce.html

# KDoc
## How to add an external link
```kotlin
/**
 * @see <a href="https://example.com">Example</a>
 */
```
Markdown also works. However, Intellij IDEA issues a warning that "Example" in `[Example]` is invalid.
```kotlin
/**
 * @see [Example](https://example.com)
 */
```
https://github.com/Kotlin/dokka/issues/518#issuecomment-744062184

# Best practices
[best-practices.md](markdown/best-practices.md)

# Coroutines and Flow
[coroutines-flow.md](markdown/coroutines-flow.md)

# ktlint
[ktlint.md](markdown/ktlint.md)

# Misc
[misc.md](markdown/misc.md)

# RxJava
[rxjava.md](markdown/rxjava.md)

# Scope functions
[scope-functions.md](markdown/scope-functions.md)
