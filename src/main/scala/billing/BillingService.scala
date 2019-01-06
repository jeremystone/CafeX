package billing

import model.{MenuItem, Money}

/**
  * Provides services related to billing
  */
class BillingService(menuService: MenuService) {

  /**
    * Calculates the bill from item names
    *
    * @param itemNames the names of the items
    * @return the total to appear on the bill
    */
  def calculateBillTotalFromNames(itemNames: List[String]): Money = {
    val items = itemNames
      .map(menuService.menuItemByName)
      .collect { case Some(item) => item }

    calculateBillTotal(items)
  }

  private def calculateBillTotal(items: List[MenuItem]): Money =
    items.map(_.price).fold(Money.Zero)(_ + _)
}
