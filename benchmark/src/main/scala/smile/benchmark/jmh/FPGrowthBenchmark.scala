package smile.benchmark.jmh

import java.util

import org.openjdk.jmh.annotations._
import smile.association.{FPGrowth, ItemSet}

@State(Scope.Benchmark)
class FPGrowthBenchmark {

  private val itemset20 = Array(Array(1, 2, 3, 4, 5, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20))
  private val itemset24 = Array(Array(1, 2, 3, 4, 5, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24))
  private val itemset28 = Array(Array(1, 2, 3, 4, 5, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28))
  private val itemset30 = Array(Array(1, 2, 3, 4, 5, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30))

  private def callFPGrowth(itemsets: Array[Array[Int]], recursive: Boolean) = {
    // Calls FP-Growth with a dummy list.
    // FP-Growth can produce enormous amount of frequent itemsets in these benchmarks which requires big amount of
    // memory to store them. High memory usage can also cause GC pauses and so weird benchmark outputs.
    // In this benchmark we are not interested in the frequent itemsets, just the speed of producing it so that we use
    // a dummy list implementation.

    val dummyList = new util.List[ItemSet] {
      override def remove(o: scala.Any): Boolean = ???
      override def remove(index: Int): ItemSet = ???
      override def iterator(): util.Iterator[ItemSet] = ???
      override def removeAll(c: util.Collection[_]): Boolean = ???
      override def get(index: Int): ItemSet = ???
      override def toArray: Array[AnyRef] = ???
      override def toArray[T](a: Array[T with Object]): Array[T with Object] = ???
      override def indexOf(o: scala.Any): Int = ???
      override def add(e: ItemSet): Boolean = true
      override def add(index: Int, element: ItemSet): Unit = ???
      override def subList(fromIndex: Int, toIndex: Int): util.List[ItemSet] = ???
      override def set(index: Int, element: ItemSet): ItemSet = ???
      override def containsAll(c: util.Collection[_]): Boolean = ???
      override def clear(): Unit = ???
      override def isEmpty: Boolean = ???
      override def lastIndexOf(o: scala.Any): Int = ???
      override def contains(o: scala.Any): Boolean = ???
      override def size(): Int = ???
      override def addAll(c: util.Collection[_ <: ItemSet]): Boolean = ???
      override def addAll(index: Int, c: util.Collection[_ <: ItemSet]): Boolean = ???
      override def listIterator(): util.ListIterator[ItemSet] = ???
      override def listIterator(index: Int): util.ListIterator[ItemSet] = ???
      override def retainAll(c: util.Collection[_]): Boolean = ???
    };

    new FPGrowth(itemsets, 1, recursive).learn(dummyList);
  }

  @Benchmark
  @BenchmarkMode(Array(Mode.AverageTime))
  @Fork(3)
  @Warmup(iterations = 5)
  @Measurement(iterations = 5)
  def measureFPGrowth_20_Recursive() = callFPGrowth(itemset20, true)

  @Benchmark
  @BenchmarkMode(Array(Mode.AverageTime))
  @Fork(3)
  @Warmup(iterations = 5)
  @Measurement(iterations = 5)
  def measureFPGrowth_20_NonRecursive() = callFPGrowth(itemset20, false)

  @Benchmark
  @BenchmarkMode(Array(Mode.AverageTime))
  @Fork(3)
  @Warmup(iterations = 5)
  @Measurement(iterations = 5)
  def measureFPGrowth_24_Recursive() = callFPGrowth(itemset24, true)

  @Benchmark
  @BenchmarkMode(Array(Mode.AverageTime))
  @Fork(3)
  @Warmup(iterations = 5)
  @Measurement(iterations = 5)
  def measureFPGrowth_24_NonRecursive() = callFPGrowth(itemset24, false)

  @Benchmark
  @BenchmarkMode(Array(Mode.AverageTime))
  @Fork(3)
  @Warmup(iterations = 5)
  @Measurement(iterations = 5)
  def measureFPGrowth_28_Recursive() = callFPGrowth(itemset28, true)

  @Benchmark
  @BenchmarkMode(Array(Mode.AverageTime))
  @Fork(3)
  @Warmup(iterations = 5)
  @Measurement(iterations = 5)
  def measureFPGrowth_28_NonRecursive() = callFPGrowth(itemset28, false)

  @Benchmark
  @BenchmarkMode(Array(Mode.AverageTime))
  @Fork(3)
  @Warmup(iterations = 5)
  @Measurement(iterations = 5)
  def measureFPGrowth_30_Recursive() = callFPGrowth(itemset30, true)

  @Benchmark
  @BenchmarkMode(Array(Mode.AverageTime))
  @Fork(3)
  @Warmup(iterations = 5)
  @Measurement(iterations = 5)
  def measureFPGrowth_30_NonRecursive() = callFPGrowth(itemset30, false)

}
