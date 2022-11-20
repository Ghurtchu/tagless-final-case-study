package initial


sealed abstract class Expression extends Product with Serializable

object Expression {

  final case class Literal(n: Int)                     extends Expression
  final case class Negate(e: Expression)               extends Expression
  final case class Add(e1: Expression, e2: Expression) extends Expression

  object ExpressionOps {

    implicit class ShowOps[A](self: A) {
      def show(): Unit = println(self)
    }

    implicit class ExpressionSyntax(self: Expression) extends ShowOps {
      def asString: String = View apply self
      def evaluate: Int = Evaluator apply self
    }
  }
}
