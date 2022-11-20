package tagless_initial_typeunsafe

// add a small Tag which denotes the type of expression (Int, Boolean etc.._
sealed abstract class Expression(val tag: String)

object Expression {
  final case class Literal(b: Boolean)                      extends Expression("Boolean")
  final case class And(left: Expression, right1: Expression) extends Expression("Boolean")
  final case class Or(left: Expression, right: Expression)  extends Expression("Boolean")
  final case class Not(expression: Expression)              extends Expression("Boolean")
  final case class I(int: Int)                              extends Expression("Integer")
  final case class Add(left: Expression, right: Expression) extends Expression("Integer")

  // it is type unsafe
  // plus the tags can be different which makes the implementation really really verbose
  def eval(expression: Expression): Any = expression match {
    case Literal(b)       => b
    case And(left, right) => { // No one likes this :)
      if (left.tag != "Boolean" || right.tag != "Boolean")
        throw new IllegalArgumentException("improper argument type")
      else eval(left).asInstanceOf[Boolean] || eval(right).asInstanceOf[Boolean]
    }
    case Or(left, right)  => {
      assert(left.tag == "Boolean" && right.tag == "Boolean")
      eval(left).asInstanceOf[Boolean] && eval(right).asInstanceOf[Boolean]
    }
    case Not(expression)  => ??? // you get the idea
    case I(int)           => ???
    case Add(left, right) => ???
  }
}
