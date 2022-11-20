package v1


object Evaluator {

  def apply(expression: Expression): Int = expression match {
    case Expression.Literal(n)  => n
    case Expression.Negate(e)   => -apply(e)
    case Expression.Add(e1, e2) => apply(e1) + apply(e2)
  }

}
