package tagless_initial_typed

// generic type argument as a Tag, instead of Tagging with String
sealed trait Expression[+A]

object Expression {
  final case class Literal(b: Boolean)                                        extends Expression[Boolean]
  final case class Or(left: Expression[Boolean], right: Expression[Boolean])  extends Expression[Boolean]
  final case class And(left: Expression[Boolean], right: Expression[Boolean]) extends Expression[Boolean]
  final case class Not(expression: Expression[Boolean])                       extends Expression[Boolean]
  final case class I(int: Int)                                                extends Expression[Int]
  final case class Sum(left: Expression[Int], right: Expression[Int])         extends Expression[Int]

  // better but still having need of doing unsafe casts which is not good
  // could be improved
  def eval[A](expression: Expression[A]): A = expression match {
    case Literal(b) => b.asInstanceOf[A]
    case I(int) => int.asInstanceOf[A]
    case Or(left, right) => (eval(left) || eval(right)).asInstanceOf[A]
    case And(left, right) => (eval(left) && eval(right)).asInstanceOf[A]
    case Not(expression) => (!eval(expression)).asInstanceOf[A]
    case Sum(left, right) => (eval(left) + eval(right)).asInstanceOf[A]
  }

  def main(args: Array[String]): Unit = {

    val boolExp: Boolean = eval(
      And(
        Or(
          Literal(true),
          Literal(false)
        ),
        Not(
          Literal(false)
        )
      )
    )

    println(boolExp)

    val intExp: Int = eval(
      Sum(
        I(5),
        Sum(
          I(10),
          I(20)
        )
      )
    )

    println(intExp)

  }
}
