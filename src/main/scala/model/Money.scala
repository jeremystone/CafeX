package model

/**
  * Represents money
  *
  * @param amountPounds Pounds with Pence as a decimal
  */
case class Money(amountPounds: Double) {
  /**
    * Adds money
    *
    * @param that money to add
    * @return the total money
    */
  def +(that: Money): Money = Money(this.amountPounds + that.amountPounds)

  /**
    * Adds money
    *
    * @param that money to add
    * @return the total money
    */
  def -(that: Money): Money = Money(this.amountPounds - that.amountPounds)

  /**
    * Multiplies money by a factor
    *
    * @param multiple factor to multiply by
    * @return the total money
    */
  def *(multiple: Double): Money = Money(this.amountPounds * multiple)

  /**
    * Rounds the value to the nearest penny
    *
    * @return
    */
  def roundToNearestPenny: Money =
    Money((amountPounds * 100).round / 100.0)

}

object Money {
  val Zero = Money(0.0)

  implicit val ordering: Ordering[Money] = Ordering.by(_.amountPounds)
}