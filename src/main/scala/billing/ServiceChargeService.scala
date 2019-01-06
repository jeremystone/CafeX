package billing

import model.{MenuItem, Money}

import scala.Ordering.Implicits._

/**
  * Calculates the service charge
  */
trait ServiceChargeService {
  /**
    * Calculates the service charge to apply to a bill.
    *
    * @param rawBillTotal the bill total without the service charge applied
    * @param items        the items on the bill
    * @return the service charge to apply
    */
  def calculateServiceCharge(rawBillTotal: Money, items: List[MenuItem]): Money
}

object NoServiceChargeService extends ServiceChargeService {
  override def calculateServiceCharge(rawBillTotal: Money, items: List[MenuItem]): Money = Money.Zero
}


class StandardServiceChargeService extends ServiceChargeService {

  private val foodChargeRate = 0.1
  private val hotFoodChargeRate = 0.2

  private val serviceChargeMax = Money(20.0)


  override def calculateServiceCharge(rawBillTotal: Money, items: List[MenuItem]): Money = {
    def includesFood = items.exists(_.food)

    def includesHotFood = items.exists(item => item.food && item.hot)

    val serviceCharge =
      if (includesHotFood)
        rawBillTotal * hotFoodChargeRate
      else if (includesFood)
        rawBillTotal * foodChargeRate
      else Money.Zero

    if (serviceCharge > serviceChargeMax)
      serviceChargeMax
    else
      serviceCharge.roundToNearestPenny
  }
}