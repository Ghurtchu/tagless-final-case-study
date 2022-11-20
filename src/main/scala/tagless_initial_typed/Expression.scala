package tagless_initial_typed

// generic type argument as a Tag, instead of Tagging with String
sealed trait Expression[A]

object Expression {
  final case class Literal(b: Boolean)                                        extends Expression[Boolean]
  final case class Or(left: Expression[Boolean], right: Expression[Boolean])  extends Expression[Boolean]
  final case class And(left: Expression[Boolean], right: Expression[Boolean]) extends Expression[Boolean]
  final case class Not(expression: Expression[Boolean])                       extends Expression[Boolean]
  final case class I(int: Int)                                                extends Expression[Int]
  final case class Sum(left: Expression[Int], right: Expression[Int])         extends Expression[Int]

  def eval[A](expression: Expression[A]): A = expression match {
    case Literal(b) => b
    case I(int) => int
    case Or(left, right) => ???
    case And(left, right) => ???
    case Not(expression) => ???
    case Sum(left, right) => ???
  }
}
