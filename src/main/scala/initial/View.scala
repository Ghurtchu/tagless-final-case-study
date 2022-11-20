package initial


object View {

  def apply(expression: Expression): String = expression match {
    case Expression.Literal(n)  => s"$n"
    case Expression.Negate(e)   => s"(-${apply(e)})"
    case Expression.Add(e1, e2) => s"(${apply(e1)} + ${apply(e2)})"
  }

}
