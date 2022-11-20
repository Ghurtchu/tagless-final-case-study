package typeclass

// algebra
trait Algebra[F[_]] {
  def b(bool: Boolean): F[Boolean]
  def i(int: Int): F[Int]
  def or(left: F[Boolean], right: F[Boolean]): F[Boolean]
  def and(left: F[Boolean], right: F[Boolean]): F[Boolean]
  def sum(left: F[Int], right: F[Int]): F[Int]
  def not(exp: F[Boolean]): F[Boolean]
}

final case class SimpleExpression[A](value: A)

object Algebra {

  implicit val simpleExpressionAlgebra: Algebra[SimpleExpression] = new Algebra[SimpleExpression] {
    override def b(bool: Boolean): SimpleExpression[Boolean] = SimpleExpression(bool)
    override def i(int: Int): SimpleExpression[Int] = SimpleExpression(int)
    override def or(left: SimpleExpression[Boolean], right: SimpleExpression[Boolean]): SimpleExpression[Boolean] =
      SimpleExpression(left.value || right.value)
    override def and(left: SimpleExpression[Boolean], right: SimpleExpression[Boolean]): SimpleExpression[Boolean] =
      SimpleExpression(left.value && right.value)
    override def sum(left: SimpleExpression[Int], right: SimpleExpression[Int]): SimpleExpression[Int] =
      SimpleExpression(left.value + right.value)
    override def not(exp: SimpleExpression[Boolean]): SimpleExpression[Boolean] =
      SimpleExpression(!exp.value)
  }

  def evaluate[F[_]](implicit algebra: Algebra[F]): F[Boolean] = {
    import algebra._

    or(
      and(
        b(true),
        b(false)
      ),
      b(true)
    )
  }

  def main(args: Array[String]): Unit = {
    println(evaluate[SimpleExpression])
  }

}
