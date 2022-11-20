package tagless_final

trait Expression[A] {
  def value: A
}

object Expression {

  def b(bool: Boolean): Expression[Boolean] = new Expression[Boolean] {
    override def value: Boolean = bool
  }

  def i(int: Int): Expression[Int] = new Expression[Int] {
    override def value: Int = int
  }

  def add(left: Expression[Int], right: Expression[Int]): Expression[Int] = new Expression[Int] {
    override def value: Int = left.value + right.value
  }

  def or(left: Expression[Boolean], right: Expression[Boolean]): Expression[Boolean] = new Expression[Boolean] {
    override def value: Boolean = left.value || right.value
  }

  def and(left: Expression[Boolean], right: Expression[Boolean]): Expression[Boolean] = new Expression[Boolean] {
    override def value: Boolean = left.value && right.value
  }

  def not(expression: Expression[Boolean]): Expression[Boolean] = new Expression[Boolean] {
    override def value: Boolean = !expression.value
  }

  def main(args: Array[String]): Unit = {

    val boolExp: Boolean = not(
      and(
        b(true),
        b(false)
      )
    ).value

    val intExp: Int = add(
      i(10),
      add(
        i(20),
        i(30)
      )
    ).value

    println(boolExp)
    println(intExp)

  }
}
