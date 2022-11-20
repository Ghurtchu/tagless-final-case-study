package initial


sealed abstract class Expression extends Product with Serializable

object Expression {

  object boolean {
    final case class Literal(value: Boolean)                  extends Expression
    final case class Or(left: Expression, right: Expression)  extends Expression
    final case class And(left: Expression, right: Expression) extends Expression
    final case class Not(expression: Expression)              extends Expression

    def eval(expression: Expression): Boolean = expression match {
      case Literal(value)   => value
      case Or(left, right)  => eval(left) || eval(right)
      case And(left, right) => eval(left) && eval(right)
      case Not(expression)  => !eval(expression)
    }

    def show(expression: Expression): String = expression match {
      case Literal(value)   => s"$value"
      case Or(left, right)  => s"${eval(left)} || ${eval(right)}"
      case And(left, right) => s"${eval(left)} && ${eval(right)}"
      case Not(expression)  => s"!${eval(expression)}"
    }
  }

  // Problem => won't work with other types such as: Int, String etc..
  // eval method becomes really nasty and unsafe..
  // if you use "Any" return type you'll end up unsafe casts which is really nasty..
  // otherwise you'd create completely new set of classes like object int { ... }

  def main(args: Array[String]): Unit = {

    import Expression.boolean._

    // this is so called AST (Abstract Syntax Tree)
    // false || false
    val falseOrFalse: Expression = Or(
      And(
        Literal(false),
        Literal(true)
      ),
      Not(
        Literal(true)
      )
    )

    val trueAndTrue: Expression = And(
      Literal(true),
      Not(
        Literal(false)
      )
    )

    println(show(falseOrFalse))
    println(show(trueAndTrue))

  }
}
